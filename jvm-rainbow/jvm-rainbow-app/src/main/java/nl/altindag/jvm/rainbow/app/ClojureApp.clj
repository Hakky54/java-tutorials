(ns nl.altindag.jvm.rainbow.app.ClojureApp
  (:gen-class)
  (:require [nl.altindag.jvm.rainbow.service.ClojureService :as clojureService] )
  (:import
    [nl.altindag.jvm.rainbow.service JavaService]
    [nl.altindag.jvm.rainbow.service KotlinService]
    [nl.altindag.jvm.rainbow.service GroovyService]
    [nl.altindag.jvm.rainbow.service ScalaService]))

(defn -main
  [& args]
  (println (.hello (new JavaService))))
  (println (.hello (new ScalaService)))
  (println (.hello (new GroovyService)))
  (println (.hello (clojureService/reify-greetings-service)))
  (println (.hello (new KotlinService)))

(-main)