-- slightly modified from hibernate auto-ddl
create table measurement
(
  id           bigserial not null
    constraint measurement_pkey
    primary key,
  boat_name    varchar(255),
  core_id      varchar(255),
  name         varchar(255),
  published_at timestamp with time zone,
  value        decimal
);
