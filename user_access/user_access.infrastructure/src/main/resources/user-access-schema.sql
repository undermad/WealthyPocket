
CREATE TABLE IF NOT EXISTS users
(
    id       UUID PRIMARY KEY,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    createdon TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updatedon TIMESTAMPTZ NOT NULL DEFAULT NOW()
);


CREATE TABLE IF NOT EXISTS roles 
(
    id      INTEGER PRIMARY KEY,
    name    VARCHAR(255) NOT NULL UNIQUE
);

INSERT INTO roles (id, name) 
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER')
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
    event_type varchar(255) NOT NULL,
    payload varchar(5000) NOT NULL,
    processed BOOLEAN DEFAULT FALSE,
    processed_at TIMESTAMPTZ DEFAULT NULL,
    createdon TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_processed ON outbox (processed, processed_at);

