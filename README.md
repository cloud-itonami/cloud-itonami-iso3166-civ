# cloud-itonami-iso3166-civ

**`:implemented`** for **CIV**. Flagship `ci-entity-missing`, tax `rccm-unverified`.

```
clojure -M:dev:test
```

AGPL-3.0-or-later.

## Market-entry / statute catalogs

Governed public-sector market-entry compliance actor, same architecture
as `cloud-itonami-iso3166-tgo`/`-cmr`/`-ben` (fellow OHADA member
states):

- `src/marketentry/{facts,governor,phase,sim,operation,registry,store,
  marketentryllm}.cljc` -- the actor. `facts.cljc` cites ANRMP
  e-procurement / Code des marchés publics for procurement, and
  RCCM/CEPICI-DGI for business/tax registration. Note: this iteration
  independently re-tested both of those provenance URLs and found
  `anrmp.ci` now 301-redirects to `arcop.ci` -- ARCOP, WebFetch-
  confirmed as ANRMP's own successor/rename ('ARCOP (Ex ANRMP)') -- and
  `cepici.gouv.ci` 301-redirects to `cepici.ci`, which is live but sits
  behind a Sucuri/Cloudproxy JS bot-detection challenge this iteration
  did not attempt to solve. Both are honest, disclosed observations for
  a future maintainer of that file, not fixed here (out of this
  statute-catalog task's scope).
- `src/statute/facts.kotoba` -- general-law catalog, orthogonal to
  `marketentry.facts`: the OHADA Uniform Act on Commercial Companies
  (AUSCGIE, directly applicable, no domestic transposition act), the
  Code du Travail (Loi n°2015-532 du 20 juillet 2015, found in the
  Assemblée Nationale's own official register of laws voted), the
  personal-data-protection law (Loi n°2013-450 du 19 juin 2013, hosted
  on ARTCI's own site), and the investment code (Ordonnance n°2018-646
  du 1er août 2018, confirmed via UNCTAD's Investment Policy Hub). A
  DGI institutional-organization decree could not be independently
  confirmed this iteration -- an honest gap; see the namespace
  docstring.

Every citation is WebFetch/curl/pdftotext-verified against an official
or official-adjacent source (ohada.org, assnat.ci, artci.ci,
investmentpolicy.unctad.org). Where a primary site returned a
bot-detection-adjacent block (artci.ci returned a Sucuri/Cloudproxy JS
challenge; assnat.ci and droit-afrique.com each returned a plain HTTP
403 on a direct fetch), the Internet Archive Wayback Machine was used
as the documented fallback -- never a browser-automation bypass; see
each namespace's docstring for the full research trail and any
honestly-narrowed scope or disclosed gap.

## Culture catalog

Alongside the market-entry / statute catalogs, this repo carries a
**country-level regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products, beverages,
crafts, festivals and heritage sites for Côte d'Ivoire:

- `src/culture/facts.kotoba` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.
