# Operator guide — STP

Human-gated filing only.

## What runs automatically

- `:engagement/intake` may auto-commit at phase 3 when the governor is
  clean (no portal-facing risk).

## What always needs your sign-off

- `:jurisdiction/assess` -- always requires human approval, even when
  clean.
- `:filing/draft` -- always requires human approval. This prepares an
  unsigned filing-draft record; it does not itself submit anything to a
  government registry.
- `:filing/submit` -- always requires human approval. This is the
  real-world act of submitting a filing; there is no phase, no
  confidence score, and no governor state that lets this auto-commit.

## What will always be refused, with no override

- A jurisdiction proposal that cannot cite an official source
  (`marketentry.facts`) -- `:no-spec-basis`.
- A `:filing/draft`/`:filing/submit` proposal before the jurisdiction has
  a full evidence checklist on file -- `:evidence-incomplete`.
- A `:filing/submit` for an engagement that declares it requires DGRN
  commercial/company registration (Guiché Único para Empresas) but has
  not confirmed it -- `:commercial-registration-missing`.
- A `:filing/submit` whose claimed fee does not equal
  `base-fee + monthly-rate x monitoring-months` -- `:engagement-fee-mismatch`.
- Drafting or submitting the same engagement a second time --
  `:already-drafted` / `:already-submitted`.

## What this actor does NOT claim

There is no single named public-procurement regulator on file for São
Tomé and Príncipe (unlike some sibling jurisdictions in this actor
family) -- the operative legal basis is **Lei nº 8/2009**, administered
by the contracting authorities themselves. There is no national
e-procurement portal on file either. If you find a verifiable source for
either, extend `src/marketentry/facts.cljc`'s `catalog` -- do not
hand-edit a claim into this guide or any other doc without an official
source.
