(ns mikera.cljutils.test-logic
  (:use clojure.test)
  (:use [mikera.cljutils logic]))

(deftest test-xor
  (testing "XOR"
    (is (xor 1 nil))
    (is (xor 1 nil 1 1))
    (is (xor 1))
    (is (not (xor)))
    (is (not (xor true false false true false false true true)))))

(deftest test-nand
  (testing "NAND"
    (is (not (nand true true)))
    (is (nand nil false))
    (is (not (nand 1)))
    (is (not (nand 1 2)))
    (is (not (nand 1 2 3)))
    (is (not (nand 1 2 3 4))))
    (is (nand 1 2 nil 3 4)))

(deftest test-and*
  (testing "AND"
    (is (identical? true (and* 1)))
    (is (and* 1 2))
    (is (and* 1 1 1 1))
    (is (and* 1))
    (is (and*))
    (is (not (and* nil)))
    (is (not (and* 1 1 1 1 1 1 nil 1 1 1 1)))))

(deftest test-or*
  (testing "OR"
    (is (identical? false (or* nil)))
    (is (or* 1 2))
    (is (or* 1 1 1 1))
    (is (or* 1))
    (is (not (or*)))
    (is (not (or* nil)))
    (is (or* 1 1 1 1 1 1 nil 1 1 1 1))))
