--TODO create relationships and index
CREATE TABLE IF NOT EXISTS users
(
    id           varchar(36) PRIMARY KEY,
    email        varchar(80) NOT NULL,
    password     varchar(16) NOT NULL,
    access_token varchar(250) NOT NULL,
    created      timestamp NOT NULL,
    modified     timestamp,
    last_Login   timestamp,
    is_active    boolean
);

CREATE TABLE IF NOT EXISTS persons
(
    id     varchar(36) PRIMARY KEY,
    user_id varchar(36) NOT NULL,
    name   varchar(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS phones
(
    id      varchar(36) PRIMARY KEY,
    person_id varchar(36) NOT NULL,
    number       integer NOT NULL,
    cityCode     integer NOT NULL,
    countryCode  integer NOT NULL
);

