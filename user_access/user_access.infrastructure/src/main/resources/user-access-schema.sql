CREATE TABLE IF NOT EXISTS users
(
    id       UUID PRIMARY KEY,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    born_date DATE NOT NULL,
    createdon TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updatedon TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS devices_fingerprints
(
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    fingerprint UUID NOT NULL,
    ip_address VARCHAR(255) NOT NULL,
    user_agent VARCHAR(255) NOT NULL,
    operating_system VARCHAR(255) NOT NULL,
    device VARCHAR(255) NOT NULL,
    last_login TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    is_verified BOOLEAN NOT NULL DEFAULT false,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS roles 
(
    id      INTEGER PRIMARY KEY,
    name    VARCHAR(255) NOT NULL UNIQUE
);

INSERT INTO roles (id, name) 
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_ACTIVE_USER'),
       (3, 'ROLE_INACTIVE_USER'),
       (4, 'ROLE_AUTHENTICATED_USER')
ON CONFLICT (id) DO NOTHING;

CREATE TABLE IF NOT EXISTS user_roles 
(
    user_id UUID NOT NULL,
    role_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) references users (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) references roles (id) ON DELETE CASCADE
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

