(ns marketentry.facts "Côte d'Ivoire market-entry catalog.")
(def catalog
  {"CIV" {:name "Côte d'Ivoire"
          :owner-authority "ANRMP / e-procurement"
          :legal-basis "Code des marchés publics"
          :national-spec "e-procurement + RCCM/CC"
          :provenance "https://www.anrmp.ci/"
          :required-evidence ["RCCM record" "e-procurement registration record" "RCCM extract" "Authorized-representative record"]
          :rep-owner-authority "contracting authorities / ANRMP"
          :rep-legal-basis "Ivorian legal entity (RCCM) typically required for public awards"
          :rep-provenance "https://www.anrmp.ci/"
          :corporate-number-owner-authority "CEPICI / DGI"
          :corporate-number-legal-basis "RCCM / tax ID"
          :corporate-number-provenance "https://www.cepici.gouv.ci/"}
   "USA" {:name "United States" :owner-authority "GSA/SAM.gov" :legal-basis "FAR" :national-spec "SAM.gov" :provenance "https://sam.gov/"
          :required-evidence ["EIN record" "SAM.gov registration record" "State business registration record" "SAM UEI verification record"]}
   "SEN" {:name "Senegal" :owner-authority "ARMP" :legal-basis "Code des marchés publics" :national-spec "e-procurement" :provenance "https://www.armp.sn/"
          :required-evidence ["RCCM/NINEA record" "e-procurement registration" "RCCM extract" "Authorized-representative record"]}
   "GHA" {:name "Ghana" :owner-authority "PPA" :legal-basis "Public Procurement Act" :national-spec "GHANEPS" :provenance "https://www.ppaghana.org/"
          :required-evidence ["Registrar-General record" "GHANEPS registration" "TIN record" "Authorized-representative record"]}})

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
