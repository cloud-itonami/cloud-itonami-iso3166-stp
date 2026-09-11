(ns marketentry.facts-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.facts :as facts]))

(deftest stp-has-spec-basis
  (let [sb (facts/spec-basis "STP")]
    (is (some? sb))
    (is (string? (:provenance sb)))
    (is (seq (:required-evidence sb)))
    (is (= 3 (count (:required-evidence sb)))
        "thinner dossier than AGO's 4 required-evidence items -- honest, not padded")))

(deftest stp-has-no-rep-or-corporate-number-sub-map
  (testing "the dossier does not ground a distinct resident-rep regime or tax-ID scheme -- unlike AGO"
    (is (nil? (facts/rep-spec-basis "STP")))
    (is (nil? (facts/corporate-number-spec-basis "STP")))))

(deftest stp-owner-authority-does-not-claim-armp
  (testing "the corrected owner-authority text names Lei 8/2009 contracting authorities, not a named regulator"
    (let [sb (facts/spec-basis "STP")]
      (is (not (re-find #"ARMP" (:owner-authority sb))) "must not repeat the unverifiable ARMP claim")
      (is (re-find #"Lei nº 8/2009" (:owner-authority sb))))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest required-evidence-satisfied
  (let [sb (facts/spec-basis "STP")
        all (:required-evidence sb)]
    (is (true? (facts/required-evidence-satisfied? "STP" all)))
    (is (not (facts/required-evidence-satisfied? "STP" (take 1 all))))
    (is (nil? (facts/required-evidence-satisfied? "ATL" all)))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["STP" "ATL"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL"] (:missing-jurisdictions c)))))
