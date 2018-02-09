(ns starter.browser
  (:require [reagent.core :as reagent]
            ["react-flip-move" :as FlipMove]))

(defn app []
  (let [items-ref (reagent/atom (into [] (range 10)))]
    (fn [props]
      [:div
       [:> FlipMove {}
        (for [item @items-ref]
          ^{:key item}
          [:div
           {:on-click
            #(swap! items-ref shuffle)}
           (str "item #" item)])]])))

(defn start []
  ;; start is called by init and after code reloading finishes
  ;; this is controlled by the :after-load in the config
  (js/console.log "start")
  (reagent/render [app {}] (.getElementById js/document "app")))

(defn ^:export init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (js/console.log "init")
  (start))

(defn stop []
  ;; stop is called before any code is reloaded
  ;; this is controlled by :before-load in the config
  (js/console.log "stop"))
