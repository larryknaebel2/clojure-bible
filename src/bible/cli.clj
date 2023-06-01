(ns bible.cli
  (:gen-class)
  (:require [bible.data :as data]
            [clojure.string :as str]))






(def commands "AVAILABLE COMMANDS: book chapter:verse - get verse, q - quit, ss - show state, st - set active translation, sb - set active book")


(defn set-active-translation
  []
  (doseq [[value index] (map vector data/translations (range 1 11))]
    (println index ":" value))
  (println "Current selection is" (inc (data/get-active-translation-index)))
  (println "Select Translation or b to go back")
  (let [in (read-line)]
    (case in
      "b" nil
      (do
        (println "********** " in)
        (data/set-active-translation (Integer/parseInt in)) (recur)))))



(defn set-active-book
  []
  ;(list-books)
  (doseq [[value index] (map vector data/books-of-bible (range 1 67))]
    (println index ":" value))
  (println "Current selection is" (if (data/get-active-book-index)
                                    (inc (data/get-active-book-index))
                                    "not set"))
  (println "Select Book or b to go back")
  (let [in (read-line)]
    (case in
      "b" nil
      (do
        (println in)
        (data/set-active-book-index (Integer/parseInt in)) (recur)))))

(defn main-loop []
  (let [in (str/trim (read-line))]
    (case in
      "q" nil
      "ss" (do (data/show-state) (println commands) (recur))
      "st" (do (set-active-translation) (println commands) (recur))
      "sb" (do (set-active-book) (println commands) (recur))
      (do (println (data/show-verse in)) (println commands) (recur)))))


(defn -main [& args] ; Get command line arguments
  ;; (if-not (empty? args)
  ;;   (doseq [arg args]
  ;;     (println arg))
  ;;   ; In case there are no command line arguments
  ;;   (throw (Exception. "Need at least one command line argument!")))
  (println "\n\n\n\n\n\nTen Bible Versions\n\n\n\n\n\n")
  (println commands)
  (main-loop))
