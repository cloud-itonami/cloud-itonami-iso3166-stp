# cloud-itonami-iso3166-stp

**`:implemented`** for **STP** (São Tomé and Príncipe): an "Actors" pattern
market-entry / public-procurement compliance service (Governor + LLM advisor
+ langgraph-clj StateGraph + append-only audit ledger + Store), adapted from
the `cloud-itonami-iso3166-ago` reference implementation.

Flagship check: `commercial-registration-missing` (DGRN commercial/company
registration via the Guiché Único para Empresas). **Six** governor checks,
not the AGO reference's seven -- see `src/marketentry/governor.cljk` for why.

```
clojure -M:dev:test
```

## Correction: this repo previously claimed "ARMP"

This repo's original scaffold said São Tomé and Príncipe's public-procurement
regulator was **"ARMP"**. That claim could **not be independently verified**:
web search found real ARMP procurement regulators in Cameroon, Senegal,
Côte d'Ivoire, DR Congo, Benin and Cape Verde, but found **no evidence** such
a body exists for São Tomé and Príncipe specifically, and a direct fetch of
São Tomé's own government registry site (`dgrn.gov.st`) made no mention of
any procurement regulatory body by that name or any other. This repo no
longer states that claim anywhere (`src/marketentry/facts.cljk`,
`organization.edn`, `docs/`). What IS verified, and is what this actor's
catalog now cites:

- **Procurement legal basis**: Lei nº 8/2009 (Empreitadas de Obras Públicas)
  -- contracting procedures for public works, supply of goods and provision
  of services to the state, hosted at
  `https://minsaude.st/wp-content/uploads/2022/08/Lei_8.2009-2.pdf` (a `.st`
  government-ministry domain).
- **Procurement administering authority**: no independently-verifiable
  single named regulator (unlike sibling jurisdictions in this actor
  family) -- the contracting authorities/ministries administer their own
  procedures under Lei nº 8/2009.
- **Commercial/company registration**: **DGRN** -- Direcção Geral dos
  Registos e do Notariado, via the **Guiché Único para Empresas** (Single
  Window for Businesses). Official site: `https://dgrn.gov.st/`.
- **National e-procurement**: no verified transactional portal for São Tomé
  and Príncipe (unlike Angola's SNCP e-procurement system) -- not invented.

AGPL-3.0-or-later.

## Culture catalog

Alongside the market-entry / statute catalogs, this repo carries a
**country-level regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products, beverages,
crafts, festivals and heritage sites for Sao Tome and Principe:

- `src/culture/facts.cljk` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.
