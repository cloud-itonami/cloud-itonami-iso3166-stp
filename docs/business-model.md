# Business model — STP

Independent public-sector market-entry & procurement compliance service for
São Tomé and Príncipe: helps a market-entry operator assemble and track the
evidence a Lei nº 8/2009 public-works/goods/services filing needs, draft a
filing package, and (with human sign-off) submit it -- never itself an
official government registry or portal.

## What this actor does

1. **Engagement intake** -- records the operator engagement (client,
   jurisdiction, fee terms).
2. **Jurisdiction assessment** -- looks up São Tomé and Príncipe's own
   required-evidence checklist (`marketentry.facts`) and proposes it,
   citing the specific official source.
3. **Filing draft** -- prepares an unsigned filing-draft record (an
   append-only book-of-record entry, not a real portal submission).
4. **Filing submit** -- prepares the filing-submit record. This is the
   real-world act of actually submitting a registration/filing; it is
   ALWAYS gated on human approval (see Trust Controls below).

## Grounding: what is and is not verified for São Tomé and Príncipe

This actor's dossier is intentionally **thinner and more conservative**
than sibling jurisdictions in this actor family (compare
`cloud-itonami-iso3166-ago`). What is verified:

- **Lei nº 8/2009** (Empreitadas de Obras Públicas) is the legal basis for
  public works/goods/services contracting procedures, including
  contract-value-based procedure thresholds (Art. 71/73) and the Ajuste
  Direto/direct-award procedure (Art. 88). Source: the hosted PDF text on
  `minsaude.st` (a `.st` government-ministry domain).
- **DGRN** (Direcção Geral dos Registos e do Notariado), via the **Guiché
  Único para Empresas**, handles commercial/company registration. Source:
  `dgrn.gov.st`.

What is NOT verified, and is therefore NOT claimed:

- A single named public-procurement regulatory agency for São Tomé and
  Príncipe. (Contrast Angola's SNCP, which IS independently verifiable.)
  This actor's own `:owner-authority` field says so explicitly: "the
  contracting authorities/ministries themselves administer their own
  procedures ... no independently-verifiable single named procurement
  regulator."
- A national transactional e-procurement portal. (Contrast Angola's SNCP
  e-procurement system.)
- A tax-ID/corporate-number verification scheme distinct from ordinary
  DGRN commercial registration. (Contrast Angola's NIF.)

## Trust Controls

- **Every jurisdiction requirement this actor states traces to an
  official source cited in `marketentry.facts`.** A proposal that cannot
  cite one is a HARD governor violation (`:no-spec-basis`) -- a false or
  fabricated regulatory-requirement claim is a HARD hold, unconditionally.
  This is why this repo's original "ARMP" claim was removed rather than
  encoded: it could not be independently verified, so it is not a
  spec-basis this actor may cite.
- **Any actual filing draft or filing submission requires Market-Entry
  Compliance Governor clearance and always escalates to human sign-off**
  -- `:filing/draft`/`:filing/submit` are permanently absent from every
  rollout phase's auto-commit set (`marketentry.phase`), and the
  governor's own high-stakes gate (`marketentry.governor`) independently
  enforces the same rule. Two layers, not one, agree that actuation is
  always a human call.
- **Missing DGRN commercial registration is an unoverridable HARD hold**
  when the engagement declares it is required
  (`commercial-registration-missing`, the STP analog of the AGO reference
  implementation's flagship `ao-entity-missing` check).
- **A claimed engagement fee that does not equal the independently
  recomputed `base-fee + monthly-rate x monitoring-months` is an
  unoverridable HARD hold** (`engagement-fee-mismatch`).
- **This is a SIX-check governor, not the AGO reference's seven.** The
  São Tomé and Príncipe dossier does not ground a distinct tax-ID/
  corporate-number verification scheme separate from DGRN commercial
  registration, so there is no analog of AGO's `nif-unverified` check
  here. Padding the check count to match a sibling actor would itself be
  a fabrication -- honesty about a thinner dossier is a feature of this
  actor's design, not a gap to paper over.
- **Double-actuation is structurally prevented**: `:drafted?`/
  `:submitted?` dedicated facts (never a `:status` value) make drafting
  or submitting the same engagement twice an unoverridable HARD hold.
- **Every governor decision -- commit OR hold -- is written to an
  append-only audit ledger.** Nothing is silently dropped.
