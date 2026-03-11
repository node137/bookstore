-- tämä on testidataa, joka ladataan H2-tietokantaan ennen testien suorittamista
insert into category ( name ) values ( 'Fiktio' ),( 'dekkari' ),( 'dokumentti' ),( 'sarjakuva' );

insert into book (
   title,
   author,
   publication_year,
   category_id
) values ( 'Mökkimaailma',
           'Mari Marison',
           1974,
           1 ),( 'Puutarha',
                 'Minni Hiiri',
                 1970,
                 1 );


insert into applicationuser (
   username,
   password,
   role
) values ( 'user',
           '$2a$10$7Qy8n9s1mXo5l3u9v1zOeO8j5k6l7n8p9q0r1s2t3u4v',
           'USER' ),( 'admin',
                      '$2a$10$7Qy8n9s1mXo5l3u9v1zOeO8j5k6l7n8p9q0r1s2t3u4v',
                      'ADMIN' );