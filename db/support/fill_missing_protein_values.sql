-- Matches all normal UpNGos
UPDATE journal_entry SET protein = 17 WHERE type = 'MEAL' AND LOWER(name) = 'upngo' AND kj = 900;

-- Matches all large UpNGos
UPDATE journal_entry SET protein = 11 WHERE type = 'MEAL' AND LOWER(name) = 'upngo' AND kj = 1150;

-- Matches all tuna
UPDATE journal_entry SET protein = 16 WHERE type = 'MEAL' AND LOWER(name) = 'tuna';

-- Matches all sandwiches x2 (There are a variety of names used, and some with spelling mistakes... but they all contain x and 2)
UPDATE journal_entry SET protein = 15 WHERE type = 'MEAL' AND LOWER(name) LIKE 's%x%2%';

-- Matches all mince toasties
UPDATE journal_entry SET protein = 16 WHERE type = 'MEAL' AND LOWER(name) LIKE '%mince%';