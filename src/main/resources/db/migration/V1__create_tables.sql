CREATE TABLE patrons (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE booths (
    id BIGSERIAL PRIMARY KEY,
    number INT UNIQUE NOT NULL,
    capacity INT NOT NULL,
    zone VARCHAR(50) NOT NULL
);

CREATE TABLE shifts (
    id BIGSERIAL PRIMARY KEY,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL
);

CREATE TABLE reservations (
    id BIGSERIAL PRIMARY KEY,
    patron_id BIGINT REFERENCES patrons(id),
    booth_id BIGINT REFERENCES booths(id),
    shift_id BIGINT REFERENCES shifts(id),
    guests_count INT NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    version BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uq_booth_shift UNIQUE (booth_id, shift_id)
);