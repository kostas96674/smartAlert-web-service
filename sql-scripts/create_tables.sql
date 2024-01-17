-- drop tables
drop table if exists incident_reports;
drop table if exists incidents;
drop table if exists users_roles;
drop table if exists users;
drop table if exists roles;


-- create table roles
create table roles
(
    id   serial primary key,
    name varchar(20) not null unique check ( name <> '' )
);

-- create table users
create table users
(
    id         serial primary key,
    username   varchar(30) not null unique check ( username <> '' ),
    password   varchar(60) not null check ( password <> '' ),
    email      varchar(50) not null unique check ( email <> '' ),
    first_name varchar(50) not null check ( first_name <> '' ),
    last_name  varchar(50) not null check ( last_name <> '' ),
    phone      varchar(20) not null unique check ( phone <> '' ),
    created_at timestamp   not null default now()
);

-- create table user_roles
create table users_roles
(
    user_id int not null,
    role_id int not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

-- create table incidents
create table incidents
(
    id   serial primary key,
    name varchar(50) not null unique check ( name <> '' )
);

-- create table incident_reports
create table incident_reports
(
    id                 serial primary key,
    incident_id        int              not null,
    user_id            int              not null,
    description        text,
    longitude          double precision not null,
    latitude           double precision not null,
    image_path         varchar(100),
    voice_message_path varchar(100),
    created_at         timestamp        not null default now(),
    foreign key (incident_id) references incidents (id),
    foreign key (user_id) references users (id)
);