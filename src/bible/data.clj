(ns bible.data
  (:gen-class)
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defonce state (atom {:activetranslationindex 0}))

(def headingsS "Verse\tKing James Bible\tAmerican Standard Version\tDouay-Rheims Bible\tDarby Bible Translation\tEnglish Revised Version\tWebster Bible Translation\tWorld English Bible\tYoung's Literal Translation\tAmerican King James Version\tWeymouth New Testament")
(def headingsV (str/split headingsS #"\t"))

(def genesis1-1-10-file "genesis_1_10.txt")
(def genesis-file "genesis.txt")
(def bible-file  "bibles_lessheadings.txt")

(defn parse-line
  [line]
  (let [verseV (str/split line #"\t")
        referenceS (first verseV)
        verses (mapv #(hash-map  :id (clojure.string/lower-case (clojure.string/replace %3 #" " ""))
                                 ;:book (nth (re-matches #"(\S+)\s(\d+):(\d+)" %3) 1)
                                 :book (nth (re-matches #"(^.+)\s(\d+):(\d+)" %3) 1)
                                 ;:chapter (Integer/parseInt (nth (re-matches #"(\S+)\s(\d+):(\d+)" %3) 2))
                                 :chapter (Integer/parseInt (nth (re-matches #"(^.+)\s(\d+):(\d+)" %3) 2))
                                 ;:v (Integer/parseInt (nth (re-matches #"(\S+)\s(\d+):(\d+)" %3) 3))
                                 :v (Integer/parseInt (nth (re-matches #"(^.+)\s(\d+):(\d+)" %3) 3))
                                 :passage %3
                                 :verse %2
                                 :translation %1)
                     (rest headingsV) (rest verseV) (repeat 9 referenceS))]
    verses))

(defn read-file
  [file-name numlines]
  ;; io/resource will get file from classpath
  (with-open [r (io/reader (io/resource file-name))]
    (doall (map parse-line (take numlines (line-seq r))))))


(def bible (doall (flatten (read-file bible-file 32000))))

(defn find-passage
  [passage]
  (->> bible
       (filter #(= passage (:passage %)))))

(def translations (str/split "King James Bible	American Standard Version	Douay-Rheims Bible	Darby Bible Translation	English Revised Version	Webster Bible Translation	World English Bible	Young's Literal Translation	American King James Version	Weymouth New Testament" #"\t"))

(defn show-verse
  [passage]
  (let [fullversemap (filter #(= (get translations (:activetranslationindex @state)) (:translation %)) (find-passage passage))
        v (:verse (first fullversemap))]
    (str passage " " v)))


(defn get-translation-from-num
  [transnumber]
  (nth translations (dec transnumber)))



(def books-of-bible ["Genesis"
                     "Exodus"
                     "Leviticus"
                     "Numbers"
                     "Deuteronomy"
                     "Joshua"
                     "Judges"
                     "Ruth"
                     "1 Samuel"
                     "2 Samuel"
                     "1 Kings"
                     "2 Kings"
                     "1 Chronicles"
                     "2 Chronicles"
                     "Ezra"
                     "Nehemiah"
                     "Esther"
                     "Job"
                     "Psalms"
                     "Proverbs"
                     "Ecclesiastes"
                     "Song of Solomon"
                     "Isaiah"
                     "Jeremiah"
                     "Lamentations"
                     "Ezekiel"
                     "Daniel"
                     "Hosea"
                     "Joel"
                     "Amos"
                     "Obadiah"
                     "Jonah"
                     "Micah"
                     "Nahum"
                     "Habakkuk"
                     "Zephaniah"
                     "Haggai"
                     "Zechariah"
                     "Malachi"
                     "Matthew"
                     "Mark"
                     "Luke"
                     "John"
                     "Acts"
                     "Romans"
                     "1 Corinthians"
                     "2 Corinthians"
                     "Galatians"
                     "Ephesians"
                     "Philippians"
                     "Colossians"
                     "1 Thessalonians"
                     "2 Thessalonians"
                     "1 Timothy"
                     "2 Timothy"
                     "Titus"
                     "Philemon"
                     "Hebrews"
                     "James"
                     "1 Peter"
                     "2 Peter"
                     "1 John"
                     "2 John"
                     "3 John"
                     "Jude"
                     "Revelation"])

(defn list-books [] (doseq [[value index] (map vector books-of-bible (range 1 67))]
                      (println index ":" value)))


(defn get-book-from-num
  [booknum]
  (nth books-of-bible (dec booknum)))


(defn show-state
  []
  (println "CURRENT STATE: activetranslation=" (get translations (:activetranslationindex @state))
           ", activebook=" (if (:activebookindex @state)
                             (get books-of-bible (:activebookindex @state))
                             " Not set")))

(defn set-active-translation
  [transnum]
  (swap! state assoc :activetranslationindex (dec transnum)))

(defn get-active-translation-index
  []
  (:activetranslationindex @state))

(defn set-active-book-index
  [booknum]
  (swap! state assoc :activebookindex (dec booknum)))

(defn get-active-book-index
  []
  (:activebookindex @state))
