CREATE TABLE journal_entry (
  id          SERIAL PRIMARY KEY,
  name        TEXT NOT NULL,
  kj          DECIMAL NOT NULL,
  entryTime   TIMESTAMP NOT NULL
);