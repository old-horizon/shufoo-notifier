(ns shufoo-notifier.s3
  (:require [clojure.java.io :as io]
            [amazonica.aws.s3 :as s3])
  (:import [java.net URI]))

(defn list-objects [bucket-name]
  (loop [acc [] continuation-token nil]
    (let [{:keys [truncated? next-continuation-token] :as res}
          (->> (cond-> {:bucket-name bucket-name}
                 continuation-token (assoc :continuation-token continuation-token))
               s3/list-objects-v2)
          keys (->> res
                    :object-summaries
                    (map :key)
                    (concat acc))]
      (if truncated? (recur keys next-continuation-token) keys))))

(defn put-public-object [bucket-name key body content-type]
  (s3/put-object {:bucket-name bucket-name
                  :key key
                  :input-stream (io/input-stream body)
                  :metadata {:content-length (count body)
                             :content-type content-type}
                  :access-control-list {:grant-permission ["AllUsers" "Read"]}})
  (URI/create (format "https://%s.s3.amazonaws.com/%s" bucket-name key)))

(defn delete-objects [bucket-name keys]
  (->> keys
       (map #(s3/delete-object {:bucket-name bucket-name :key %}))))

(defn delete-object [bucket-name key]
  (s3/delete-object {:bucket-name bucket-name :key key}))

