# ADR-0001: STP

Adapted from the `cloud-itonami-iso3166-ago` reference implementation.
Flagship check `commercial-registration-missing` (DGRN commercial/company
registration via the Guiché Único para Empresas). **Six** governor checks,
not AGO's seven -- the São Tomé and Príncipe dossier does not ground a
distinct tax-ID/corporate-number scheme separate from DGRN commercial
registration, so there is no analog of AGO's `nif-unverified` check.

This repo's original scaffold claimed a named regulator "ARMP" for São
Tomé and Príncipe; that claim could not be independently verified and has
been removed from `src/marketentry/facts.cljc`, `organization.edn`,
`README.md` and `docs/`. See `docs/business-model.md` Trust Controls for
the research basis.
