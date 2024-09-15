CREATE TABLE IF NOT EXISTS owner
(
    id        UUID PRIMARY KEY,
    user_id   UUID        NOT NULL UNIQUE,
    createdon timestamptz NOT NULL DEFAULT now(),
    updatedon timestamptz NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS wallet
(
    id        UUID PRIMARY KEY,
    owner_id  UUID        not null,
    createdon TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updatedon TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    FOREIGN KEY (owner_id) references owner (id)
);

CREATE TABLE IF NOT EXISTS money
(
    id UUID PRIMARY KEY,
    wallet_id UUID,
    amount BIGINT NOT NULL DEFAULT 0,
    currency_code VARCHAR(3) NOT NULL,
    createdon TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updatedon TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    FOREIGN KEY (wallet_id) REFERENCES wallet (id) 
);

CREATE TABLE IF NOT EXISTS outbox
(
    id UUID PRIMARY KEY,
    event_id UUID NOT NULL UNIQUE,
    event_type varchar(255) NOT NULL,
    payload varchar(5000) NOT NULL,
    processed BOOLEAN DEFAULT FALSE,
    processed_at TIMESTAMPTZ DEFAULT NULL,
    createdon TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_processed ON outbox (processed, processed_at);

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






