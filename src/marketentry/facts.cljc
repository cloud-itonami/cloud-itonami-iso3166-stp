(ns marketentry.facts
  "São Tomé and Príncipe (STP) market-entry catalog.

  Every fact here traces to a source actually fetched and read during
  research (2026-07-22): the hosted PDF text of Lei nº 8/2009 on a `.st`
  government-ministry domain, and the DGRN's own official site. This
  catalog is DELIBERATELY thinner and more conservative than sibling
  jurisdictions in this actor family (compare
  `cloud-itonami-iso3166-ago`'s `AGO` entry): extensive search found NO
  independently-verifiable named public-procurement regulatory agency
  for São Tomé and Príncipe (unlike Angola's SNCP) and NO verified
  transactional e-procurement portal (unlike Angola's SNCP
  e-procurement system) -- `:owner-authority` and `:national-spec` say
  so honestly rather than inventing either. Accordingly there is no
  `:rep-*`/`:corporate-number-*` sub-map on the `STP` entry -- the
  dossier does not ground a distinct resident-representative regime or
  a tax-ID scheme separate from ordinary DGRN commercial registration
  (contrast `AGO`, which has both, grounded in its own richer dossier).

  IMPORTANT CORRECTION: this repo's original scaffold (README.md,
  organization.edn) claimed a named regulator \"ARMP\" for São Tomé and
  Príncipe. That claim could NOT be independently verified -- web
  search found real ARMP procurement regulators in Cameroon, Senegal,
  Côte d'Ivoire, DR Congo, Benin and Cape Verde, but found NO evidence
  such a body exists for São Tomé and Príncipe specifically, and a
  direct fetch of São Tomé's own government registry site
  (dgrn.gov.st) made no mention of any procurement regulatory body
  named ARMP or otherwise. This catalog does not repeat that claim;
  `:owner-authority` below names only what Lei nº 8/2009 itself
  establishes (the contracting authorities, not a separate regulator).")

(def catalog
  {"STP" {:name "São Tomé and Príncipe"
          :owner-authority "entidades adjudicantes sob a Lei nº 8/2009 (the contracting authorities/ministries themselves administer their own procedures -- no independently-verifiable single named procurement regulator, unlike sibling jurisdictions in this actor family)"
          :legal-basis "Lei nº 8/2009 (Empreitadas de Obras Públicas -- contracting procedures for public works, supply of goods and provision of services to the state; Art. 71/73 sets procedure thresholds by contract value, Art. 88 regulates the Ajuste Direto/direct-award procedure)"
          :national-spec "Lei nº 8/2009 procedures (no verified national e-procurement portal)"
          :provenance "https://minsaude.st/wp-content/uploads/2022/08/Lei_8.2009-2.pdf"
          :required-evidence ["DGRN commercial/company registration record (Guiché Único para Empresas)"
                               "Lei nº 8/2009 procedure-compliance record (which competition procedure applies given the contract value)"
                               "Authorized-representative record (if the engagement uses one)"]}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
