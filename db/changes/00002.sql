-- There appears to be no way in postgres to add a new column after an existing one (i.e. it would be nice to put this
-- column after the 'kj' column)
ALTER TABLE journal_entry ADD COLUMN protein DECIMAL;