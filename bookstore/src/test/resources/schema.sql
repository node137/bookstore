create table category (
   id   bigserial primary key,
   name varchar(150) not null
);

create table book (
   id               bigserial primary key,
   title            varchar(150) not null,
   author           varchar(150) not null,
   publication_year int,
   category_id      bigint
      references category ( id )
);

create table applicationuser (
   id       bigserial primary key,
   role     varchar(100) not null,
   username varchar(150) not null,
   password varchar(250) not null
);