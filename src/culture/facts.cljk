(ns culture.facts
  "Country-level regional-culture catalog for Sao Tome and Principe (STP) --
  national dishes, protected products, beverages, crafts, festivals and
  heritage sites, per ADR-2607171400 addendum 2 (cloud-itonami-
  municipality-culture-catalog Wave 1, in com-junkawasaki/root). Sibling
  namespace to `marketentry.facts` / `statute.facts` (ADR-2607141700);
  city-level counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Sao Tome and Principe is thinly documented on the sources this catalog
  is allowed to cite (Wikipedia English); this catalog reflects only what
  was actually verified there rather than padding to a target count. A
  candidate entry (Tchiloli, a musical dance-drama performance tradition
  described in the main `Sao Tome and Principe` article's Music section)
  was researched and dropped: it does not cleanly fit any of the
  :culture/kind values (:dish/:beverage/:product/:craft/:festival/
  :heritage) without stretching the taxonomy beyond what the source
  actually confirms.

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"STP"
   [{:culture/id "stp.dish.calulu"
     :culture/name "Calulu"
     :culture/country "STP"
     :culture/kind :dish
     :culture/summary "Traditional dish of Sao Tome and Principe prepared with grouper or smoked fish, prawns, tomato, okra, aubergine, onion and spices including grains of paradise."
     :culture/url "https://en.wikipedia.org/wiki/Cuisine_of_S%C3%A3o_Tom%C3%A9_and_Pr%C3%ADncipe"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "stp.dish.arroz-doce"
     :culture/name "Arroz doce"
     :culture/country "STP"
     :culture/kind :dish
     :culture/summary "Rice pudding in Sao Tomean cuisine, prepared with sweet corn and coconut."
     :culture/url "https://en.wikipedia.org/wiki/Cuisine_of_S%C3%A3o_Tom%C3%A9_and_Pr%C3%ADncipe"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "stp.dish.estufa-de-morcego"
     :culture/name "Estufa de morcego"
     :culture/country "STP"
     :culture/kind :dish
     :culture/summary "Bat stew, described as a delicacy in the cuisine of Sao Tome and Principe."
     :culture/url "https://en.wikipedia.org/wiki/Cuisine_of_S%C3%A3o_Tom%C3%A9_and_Pr%C3%ADncipe"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "stp.beverage.palm-wine"
     :culture/name "Palm wine"
     :culture/country "STP"
     :culture/kind :beverage
     :culture/summary "Considered a national drink of Sao Tome and Principe."
     :culture/url "https://en.wikipedia.org/wiki/Cuisine_of_S%C3%A3o_Tom%C3%A9_and_Pr%C3%ADncipe"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "stp.product.cocoa"
     :culture/name "Cocoa"
     :culture/country "STP"
     :culture/kind :product
     :culture/summary "Primary cash crop of Sao Tome and Principe, accounting for 54% of the country's exports in 2021."
     :culture/url "https://en.wikipedia.org/wiki/Cocoa_production_in_S%C3%A3o_Tom%C3%A9_and_Pr%C3%ADncipe"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "stp.heritage.obo-natural-park"
     :culture/name "Obo Natural Park"
     :culture/name-local "Parque Natural Obô de São Tomé"
     :culture/country "STP"
     :culture/kind :heritage
     :culture/summary "Natural park of Sao Tome and Principe covering 195 sq km of the island of Sao Tome."
     :culture/url "https://en.wikipedia.org/wiki/Parque_Natural_Ob%C3%B4_de_S%C3%A3o_Tom%C3%A9"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-stp culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "STP"))
                 " STP entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
