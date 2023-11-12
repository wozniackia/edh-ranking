alter table player drop column nickname;
alter table player add column username varchar(20) not null unique;
alter table player add column password varchar(20) not null;
