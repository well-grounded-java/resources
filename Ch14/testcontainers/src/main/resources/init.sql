CREATE TABLE IF NOT EXISTS prices (
   price decimal NOT NULL,
   seen_at timestamp NOT NULL DEFAULT NOW()
);
