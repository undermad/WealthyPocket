CREATE TABLE IF NOT EXISTS wallet
(
    id        UUID PRIMARY KEY,
    owner_id  UUID        not null unique,
    money     BIGINT      not null default 0,
    createdon TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updatedon TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    FOREIGN KEY (owner_id) references owner (id)
);

CREATE TABLE IF NOT EXISTS owner
(
    id        UUID PRIMARY KEY,
    user_id   UUID        NOT NULL UNIQUE,
    createdon timestamptz NOT NULL DEFAULT now(),
    updatedon timestamptz NOT NULL DEFAULT now()
);




