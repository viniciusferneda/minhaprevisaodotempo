CREATE DATABASE minhaprevisaodotempo;

CREATE SCHEMA previsaodotempo;

CREATE TABLE previsaodotempo.city
(
  id bigserial NOT NULL PRIMARY KEY,
  name character varying(250) NOT NULL,
  country character varying(2) NOT NULL,
  lon numeric(8,6) NOT NULL,
  lat numeric(8,6) NOT NULL
);