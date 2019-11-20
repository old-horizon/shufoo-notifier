(ns shufoo-notifier.shufoo
  (:require [clojure.xml :as xml])
  (:import [java.net URI]))

(defn- find-tag [tag-name doc]
  (->> doc
       (filter (comp #{tag-name} :tag))
       (map :content)))

(defn- tag->map [xml]
  (reduce #(assoc %1 (:tag %2) (first (:content %2))) {} xml))

(defn- get-image-urls [contents-xml]
  (let [[[total-pages]] (->> contents-xml
                             xml/parse
                             xml-seq
                             (find-tag :totalPages))
        indices (range (Integer/parseInt total-pages))]
    (map #(->> (str "orig/" % ".jpg")
               (.resolve (URI/create contents-xml))
               (format "http://www.shufoo.net/oi.php?%s")
               URI/create) indices)))

(defn- to-flyer [shop-name {:keys [id thumb publishStartTime publishEndTime title contentsXml]}]
  {:id id :store shop-name :title title
   :period (str publishStartTime " - " publishEndTime)
   :thumb (URI/create thumb) :images (get-image-urls contentsXml)})

(defn get-flyers [shop-id]
  (let [shop-detail (->> shop-id
                      (format "http://www.shufoo.net/api/shopDetailNewXML/%s/?crosstype=portal&useUtf=true")
                      xml/parse
                      xml-seq)
        [[shop-name]] (find-tag :shopName shop-detail)
        chirashis (->> (find-tag :chirashi shop-detail)
                       (map tag->map))]
    (->> chirashis
         (map #(to-flyer shop-name %)))))

