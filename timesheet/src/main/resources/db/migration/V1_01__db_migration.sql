CREATE TABLE IF NOT EXISTS programs(
    program_id SERIAL PRIMARY KEY,
    program_name varchar(255),
    start_date date,
    end_date date,
    working_hours float
);

CREATE TABLE IF NOT EXISTS psp (
    psp_id SERIAL PRIMARY KEY,
    name varchar(255),
    program_id int REFERENCES programs (program_id)
);

CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    first_name varchar(255),
    last_name varchar(255),
    username varchar(255) UNIQUE,
    password varchar(255),
    role int,
    program_id int references programs (program_id)
);

CREATE TABLE IF NOT EXISTS booking (
    booking_id SERIAL PRIMARY KEY,
    day date,
    user_id int references users (user_id)
);

CREATE TABLE IF NOT EXISTS booking_detail (
    booking_detail_id SERIAL PRIMARY KEY,
    start_hour time,
    end_hour time,
    description varchar(255),
    status int,
    psp_id int references PSP (psp_id),
    booking_id int references booking (booking_id)
);