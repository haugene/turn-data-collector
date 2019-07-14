create table location
(
  id           bigserial not null
    constraint location_pkey
    primary key,
  boat_name    varchar(255),
  core_id      varchar(255),
  published_at timestamp with time zone,
  latitude     decimal,
  longitude    decimal,
  accuracy     decimal,
  message_id   varchar
);

ALTER TABLE measurement ADD COLUMN message_id varchar;