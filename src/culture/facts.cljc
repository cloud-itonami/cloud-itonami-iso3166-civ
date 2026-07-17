(ns culture.facts
  "Country-level regional-culture catalog for Côte d'Ivoire (CIV) --
  national dishes, protected products, beverages, crafts, festivals and
  heritage sites, per ADR-2607171400 addendum 2 (cloud-itonami-
  municipality-culture-catalog Wave 1, in com-junkawasaki/root). Sibling
  namespace to `marketentry.facts` / `statute.facts` (ADR-2607141700);
  city-level counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"CIV"
   [{:culture/id "civ.dish.attieke"
     :culture/name "Attiéké"
     :culture/name-local "Acheke"
     :culture/country "CIV"
     :culture/kind :dish
     :culture/summary "Fermented-cassava dish popular and traditional in Côte d'Ivoire and Ghana; in December 2024 the skills of attiéké production in Côte d'Ivoire were recognised by UNESCO as intangible cultural heritage."
     :culture/url "https://en.wikipedia.org/wiki/Acheke"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "civ.dish.alloco"
     :culture/name "Alloco"
     :culture/country "CIV"
     :culture/kind :dish
     :culture/summary "West African fried-plantain snack, widely considered fast food and sold on the streets of Côte d'Ivoire; the name alloco originates from the Baoulé people."
     :culture/url "https://en.wikipedia.org/wiki/Fried_plantain"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "civ.dish.kedjenou"
     :culture/name "Kedjenou"
     :culture/country "CIV"
     :culture/kind :dish
     :culture/summary "Spicy stew of chicken or guinea hen slow-cooked in a sealed terra-cotta pot, a traditional and popular dish of the cuisine of Côte d'Ivoire."
     :culture/url "https://en.wikipedia.org/wiki/Kedjenou"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "civ.product.cocoa"
     :culture/name "Ivorian cocoa"
     :culture/country "CIV"
     :culture/kind :product
     :culture/summary "Ivory Coast has been the world's leading producer of cocoa beans since 1978, producing 45% of the world's cocoa as of 2024; the crop accounts for 40% of national export income."
     :culture/url "https://en.wikipedia.org/wiki/Cocoa_production_in_Ivory_Coast"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "civ.festival.abissa"
     :culture/name "Abissa"
     :culture/country "CIV"
     :culture/kind :festival
     :culture/summary "Traditional new-year festival of the Nzema people held between October and November in Grand-Bassam and other towns of Ivory Coast, celebrated since the 1800s."
     :culture/url "https://en.wikipedia.org/wiki/Abissa"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "civ.heritage.grand-bassam"
     :culture/name "Historic town of Grand-Bassam"
     :culture/country "CIV"
     :culture/kind :heritage
     :culture/summary "Former French colonial capital (1893-1900) east of Abidjan; its historic centre was inscribed as a UNESCO World Heritage Site in 2012 for the juxtaposition of the colonial town with a traditional Nzema village."
     :culture/url "https://en.wikipedia.org/wiki/Grand-Bassam"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "civ.heritage.tai-national-park"
     :culture/name "Taï National Park"
     :culture/country "CIV"
     :culture/kind :heritage
     :culture/summary "Primary-rainforest national park in southwestern Ivory Coast, inscribed as a UNESCO World Heritage Site in 1982 for the diversity of its flora and fauna."
     :culture/url "https://en.wikipedia.org/wiki/Taï_National_Park"
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
      :note (str "cloud-itonami-iso3166-civ culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "CIV"))
                 " CIV entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
