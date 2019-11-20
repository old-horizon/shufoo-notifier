(ns shufoo-notifier.line
  (:import [com.linecorp.bot.client LineMessagingClient]
           [com.linecorp.bot.model PushMessage]
           [com.linecorp.bot.model.action URIAction]
           [com.linecorp.bot.model.action URIAction$AltUri]
           [com.linecorp.bot.model.message FlexMessage]
           [com.linecorp.bot.model.message.flex.component Image]
           [com.linecorp.bot.model.message.flex.component Image$ImageAspectRatio]
           [com.linecorp.bot.model.message.flex.component Image$ImageAspectMode]
           [com.linecorp.bot.model.message.flex.component Box]
           [com.linecorp.bot.model.message.flex.component Button]
           [com.linecorp.bot.model.message.flex.component Button$ButtonStyle]
           [com.linecorp.bot.model.message.flex.component Button$ButtonHeight]
           [com.linecorp.bot.model.message.flex.component Text]
           [com.linecorp.bot.model.message.flex.component Text$TextWeight]
           [com.linecorp.bot.model.message.flex.container Bubble]
           [com.linecorp.bot.model.message.flex.unit FlexFontSize]
           [com.linecorp.bot.model.message.flex.unit FlexLayout]
           [com.linecorp.bot.model.message.flex.unit FlexMarginSize]))

(defn- hero [thumb]
  (-> (Image/builder)
      (.url thumb)
      (.aspectRatio Image$ImageAspectRatio/R1TO1)
      (.aspectMode Image$ImageAspectMode/Cover)
      .build))

(defn- pair [key value]
  (let [key-text (-> (Text/builder)
                     (.text key)
                     (.color "#aaaaaa")
                     (.size FlexFontSize/SM)
                     (.flex (int 2))
                     (.wrap true)
                     .build)
        value-text (-> (Text/builder)
                       (.text value)
                       (.color "#666666")
                       (.size FlexFontSize/SM)
                       (.flex (int 4))
                       (.wrap true)
                       .build)]
    (-> (Box/builder)
        (.layout FlexLayout/BASELINE)
        (.spacing FlexMarginSize/SM)
        (.contents (list key-text value-text))
        .build)))

(defn- body [store title period]
  (let [title-text (-> (Text/builder)
                       (.text title)
                       (.weight Text$TextWeight/BOLD)
                       (.size FlexFontSize/LG)
                       (.wrap true)
                       .build)
        store-box (pair "店舗" store)
        period-box (pair "掲載期間" period)]
    (-> (Box/builder)
        (.layout FlexLayout/VERTICAL)
        (.contents (list title-text store-box period-box))
        .build)))

(defn- link-button [caption url]
  (let [action (URIAction. caption url (URIAction$AltUri. url))]
    (-> (Button/builder)
        (.style Button$ButtonStyle/LINK)
        (.height Button$ButtonHeight/SMALL)
        (.action action)
        .build)))

(defn- footer [images]
  (let [buttons (map-indexed #(link-button (format "%d ページ目" (inc %1)) %2) images)]
    (-> (Box/builder)
        (.layout FlexLayout/VERTICAL)
        (.spacing FlexMarginSize/SM)
        (.contents buttons)
        .build)))

(defn push-message [channel-token receiver-id {:keys [store title period thumb images]}]
  (let [hero (hero thumb)
        body (body store title period)
        footer (footer images)
        bubble (-> (Bubble/builder)
                   (.hero hero)
                   (.body body)
                   (.footer footer)
                   .build)
        alt-text (format "%s (%s)" title store)
        flex-message (-> (FlexMessage/builder)
                         (.contents bubble)
                         (.altText alt-text)
                         .build)
        push-message (PushMessage. receiver-id flex-message)
        client (-> (LineMessagingClient/builder channel-token)
                   .build)]
    (-> (.pushMessage client push-message)
        .get)))

