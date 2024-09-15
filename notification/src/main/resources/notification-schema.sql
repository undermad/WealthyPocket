
CREATE TABLE IF NOT EXISTS inbox
(
    id UUID PRIMARY KEY,
    event_id UUID NOT NULL UNIQUE,
    event_type varchar(255) NOT NULL,
    payload varchar(5000) NOT NULL,
    processed BOOLEAN DEFAULT FALSE,
    processed_at TIMESTAMPTZ DEFAULT NULL,
    createdon TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_processed ON inbox (processed, processed_at);