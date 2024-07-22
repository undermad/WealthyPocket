CREATE SCHEMA IF NOT EXISTS wallet;

CREATE TABLE IF NOT EXISTS wallet.wallet
(
    id       UUID PRIMARY KEY,
    owner_id UUID not null unique,
    money BIGINT not null default 0,
    createdon TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updatedon TIMESTAMPTZ NOT NULL DEFAULT NOW()
);