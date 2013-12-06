CREATE TABLE weight_log (
  id      SERIAL PRIMARY KEY,
  date    TIMESTAMP NOT NULL,
  weight  DECIMAL NOT NULL
);