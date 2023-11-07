CREATE SEQUENCE player_id_seq;

CREATE TABLE player (
    id integer not null primary key default nextval('player_id_seq'),
    nickname varchar(20),
    first_name varchar(20) not null,
    last_name varchar(20) not null,
    matches integer not null default 0,
    wins integer not null default 0
);

ALTER SEQUENCE player_id_seq OWNED BY player.id;

CREATE SEQUENCE commander_id_seq;

CREATE TABLE commander (
    id integer not null primary key default nextval('commander_id_seq'),
    c_name varchar(20) not null,
    image_url varchar(20) not null,
    matches integer not null default 0,
    wins integer not null default 0
);

ALTER SEQUENCE commander_id_seq OWNED BY commander.id;

