(ns clojure-fasttrack.futures)

; Wrapper on java.util.concurrent.future

(def f (future (+ 2 2)))
(type (future (+ 2 2)))

(realized? (future (+ 2 2)))

; @f

; promises - good for creating pipelines between threads
(def my-promise (promise))
(realized? my-promise)
(deref my-promise)

(deliver my-promise 42)
(deref my-promise)

; delays - for postponing and caching calculations
(def my-delay (delay (+ 2 2)))
(realized? my-delay)
; @my-delay
