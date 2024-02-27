-- Enable PostGIS extension
CREATE EXTENSION IF NOT EXISTS postgis;

-- drop tables
drop table if exists incident_reports;
drop table if exists report_groups;
drop table if exists incident_category_names;
drop table if exists incident_categories;
drop table if exists users;
drop table if exists roles;

-- drop group_status enum
drop type if exists group_status;

-- create table roles
create table roles
(
    id   serial primary key,
    title varchar(16) not null unique
);

-- create table users
create table users
(
    id         serial primary key,
    -- The maximum length of an email address is 254 characters according to the specification (RFC 5321).
    email      varchar(254) not null unique,
    password   varchar(60)  not null,
    first_name varchar(128) not null,
    last_name  varchar(128) not null,
    role_id    int          not null,
    created_at timestamp    not null default now(),
    foreign key (role_id) references roles (id)
);

-- create table incident_categories
create table incident_categories
(
    id                           serial primary key,
    init_search_radius_in_meters double precision not null
);

-- create table incident_category_names
create table incident_category_names
(
    category_id int          not null,
    language    char(2)      not null,
    name        varchar(128) not null,
    primary key (category_id, language),
    foreign key (category_id) references incident_categories (id)
);

-- create enum group_status
CREATE TYPE group_status AS ENUM ('PENDING', 'ACCEPTED', 'DECLINED');

-- create table report_groups
create table report_groups
(
    id                      serial primary key,
    category_id             int              not null,
    central_point           geometry(Point, 4326) not null,
    status                  group_status     not null default 'PENDING',
    search_radius_in_meters double precision not null,
    last_updated            timestamp        not null,
    foreign key (category_id) references incident_categories (id)
);

-- create table incident_reports
create table incident_reports
(
    id          serial primary key,
    user_id     int       not null,
    category_id int       not null,
    group_id    int       not null,
    location    geometry(Point, 4326) not null,
    description        text,
    image_path  varchar(255),
    created_at  timestamp not null,
    foreign key (category_id) references incident_categories (id),
    foreign key (user_id) references users (id),
    foreign key (group_id) references report_groups (id)
);