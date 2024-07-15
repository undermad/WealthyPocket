CREATE SCHEMA IF NOT EXISTS user_access;

CREATE TABLE IF NOT EXISTS user_access.users
(
    id       UUID PRIMARY KEY,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    createdon TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updatedon TIMESTAMPTZ NOT NULL DEFAULT NOW()
);


CREATE TABLE IF NOT EXISTS user_access.roles 
(
    id      INTEGER PRIMARY KEY,
    name    VARCHAR(255) NOT NULL UNIQUE
);

INSERT INTO user_access.roles (id, name) 
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER')
ON CONFLICT (id) DO NOTHING;

CREATE TABLE IF NOT EXISTS user_access.user_roles 
(
    user_id UUID NOT NULL,
    role_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) references user_access.users (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) references user_access.roles (id) ON DELETE CASCADE
)

