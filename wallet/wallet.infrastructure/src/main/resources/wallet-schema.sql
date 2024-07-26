CREATE TABLE IF NOT EXISTS wallet
(
    id       UUID PRIMARY KEY,
    owner_id UUID not null unique,
    money BIGINT not null default 0,
    createdon TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updatedon TIMESTAMPTZ NOT NULL DEFAULT NOW()
);



CREATE TABLE IF NOT EXISTS inbox
(
    id UUID PRIMARY KEY,
    event_type varchar(255) NOT NULL,
    payload varchar(5000) NOT NULL,
    processed BOOLEAN DEFAULT FALSE,
    processed_at TIMESTAMPTZ DEFAULT NULL,
    createdon TIMESTAMPTZ NOT NULL DEFAULT NOW()
);