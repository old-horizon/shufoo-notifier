(ns shufoo-notifier.core
  (:gen-class
   :implements [com.amazonaws.services.lambda.runtime.RequestStreamHandler])
  (:require [clj-http.client :as client]
            [clojure.string :as str]
            [clojure.tools.logging :as log]
            [environ.core :as environ]
            [shufoo-notifier.s3 :as s3]
            [shufoo-notifier.shufoo :as shufoo]
            [shufoo-notifier.line :as line]))

(defn- load-config [{:keys [bucket-name channel-token receiver-id shop-id-count] :as env}]
  (let [shop-ids (->> shop-id-count
                      Integer/parseInt
                      range
                      (map #(keyword (str "shop-id-" %)))
                      (map env))]
    {:bucket-name bucket-name :channel-token channel-token
     :receiver-id receiver-id :shop-ids shop-ids}))

(defn- not-uploaded? [id s3-objects]
  (->> s3-objects
       (filter #(str/starts-with? % (str id "/")))
       empty?))

(defn- fetch->put [url bucket-name key content-type]
  (let [{:keys [status body]} (client/get (.toString url) {:as :byte-array})]
    (if (not (= status 200))
      (throw (ex-info (str "Download failed: " url)))
      (s3/put-public-object bucket-name key body content-type))))

(defn- upload-images [bucket-name {:keys [id thumb images]}]
  (let [content-type "image/jpeg"
        put-thumb (fetch->put thumb bucket-name (str id "/thumb.jpg") content-type)
        put-images (->> images
                        (map-indexed
                         #(fetch->put %2 bucket-name (str id "/" %1 ".jpg") content-type)))]
    {:thumb put-thumb :images put-images}))

(defn- flyer->message [bucket-name {:keys [id store title period thumb images] :as flyer}]
  (let [put-images (upload-images bucket-name flyer)]
    {:id id :store store :title title :period period
     :thumb (:thumb put-images) :images (:images put-images)}))

(defn get-expireds [flyer-ids  s3-objects]
  (let [regex (->> flyer-ids
                   (map #(str "^" % "/.*"))
                   (str/join "|")
                   re-pattern)]
    (->> s3-objects
         (filter #(not (re-matches regex %))))))

(defn- notify-new-flyers [{:keys [bucket-name channel-token receiver-id shop-ids]}]
  (log/infof "Target shops: %s" (pr-str shop-ids))
  (let [flyers (->> shop-ids
                    (map shufoo/get-flyers)
                    flatten)
        objects (s3/list-objects bucket-name)
        new-flyers (->> flyers
                        (filter #(not-uploaded? (:id %) objects)))]
    (log/infof "%d new flyers found." (count new-flyers))
    (doseq [expired-object (get-expireds (map :id flyers) objects)]
      (s3/delete-object bucket-name expired-object))
    (dorun
     (->> new-flyers
          (map #(do (log/infof "New flyer: %s" %) %))
          (map #(flyer->message bucket-name %))
          (map #(line/push-message channel-token receiver-id %))))))

(defn -main [& args]
  (notify-new-flyers (load-config environ/env)))

(defn -handleRequest [this input output ctx]
  (-main))

