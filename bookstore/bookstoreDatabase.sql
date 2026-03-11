--Esimerkki SQL scripti-tiedoston sisällöstä

-- Poistetaan taulut oikeassa järjestyksessä riippuvuuksien takia
--drop table if exists car;
drop table if exists owner;
drop table if exists application_user;

-- owner-taulu
create table owner (
   id        bigserial primary key,
   firstname varchar(100) not null,
   lastname  varchar(100) not null,
   city      varchar(50),
   ssn       varchar(15) not null,
   yob       int
);

insert into owner (
   firstname,
   lastname,
   city,
   ssn,
   yob
) values ( 'Maria',
           'Marison',
           'Manse',
           '150574-113I',
           1974 ),( 'Minnie',
                    'Minison',
                    'Turku',
                    '250170-111M',
                    1970 );
/*
-- car-taulu
create table car (
   id              bigserial primary key,
   brand           varchar(50) not null,
   model           varchar(50) not null,
   color           varchar(50) not null,
   register_number varchar(50) not null,
   production_year int,
   price           bigint,
   ownerid         bigint
      references owner ( id )
);

insert into car (
   brand,
   model,
   color,
   register_number,
   production_year,
   price,
   ownerid
) values ( 'Ford',
           'Taunus',
           'blue',
           'xxx-111',
           1979,
           12000,
           1 ),( 'Volkswagen',
                 'Golf',
                 'red',
                 'yyy-222',
                 1990,
                 7000,
                 1 );
*/

-- application_user-taulu
create table application_user (
   id        bigserial primary key,
   firstname varchar(100) not null,
   lastname  varchar(100) not null,
   role      varchar(100) not null,
   username  varchar(250) not null,
   password  varchar(250) not null
);

insert into application_user (
   firstname,
   lastname,
   username,
   password,
   role
) values ( 'Minna',
           'Pellikka',
           'user',
           '$2a$10$1DTvwpXVBArGFixHBuzVJObjTuXhIOkx5pse6KsYs8/C2ckxnGEou',
           'USER' ),( 'Admin',
                      'User',
                      'admin',
                      '$2a$10$cDZgyF4xaPMmmoRW3OVcmuf.8o2YSx8.M7CeRKqi.1PVw.t3E8uEC',
                      'ADMIN' );

-- Testihaut
select *
  from car;
select *
  from owner;
select *
  from application_user;