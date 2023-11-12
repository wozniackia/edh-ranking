CREATE SEQUENCE tournament_id_seq;

CREATE TABLE tournament (
    id integer not null primary key default nextval('tournament_id_seq'),
    tournament_date timestamp not null,
    tournament_name varchar(255) not null,
    tournament_location varchar(255) not null,
    tournament_price integer not null default 0,
    registered_participants integer[],
    available_participants integer not null default 0
);

ALTER SEQUENCE tournament_id_seq OWNED BY tournament.id;