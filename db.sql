create table "sector"
(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    description VARCHAR(255),
    created_date TIMESTAMP,
    updated_date TIMESTAMP
);
create table "collaborator"
(
    id SERIAL PRIMARY KEY NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    name VARCHAR(255),
    gender VARCHAR(2),
    birthdate TIMESTAMP,
    sector_id bigint,
    created_date TIMESTAMP,
    updated_date TIMESTAMP,
    CONSTRAINT fk_sector FOREIGN KEY(sector_id) REFERENCES sector(id)
);
insert into "sector" (name, description, created_date, updated_date) VALUES ('TI', 'Setor de TI', now(), now());
insert into "collaborator" (cpf, name, gender, birthdate, sector_id, created_date, updated_date) VALUES ('12345678910', 'lucas', 'm', '1998-06-07 10:46:17'
                                                                                                        , 1, now(), now());
insert into "collaborator" (cpf, name, gender, birthdate, sector_id, created_date, updated_date) VALUES ('98765432110', 'Jorgina', 'f', '1994-06-07 10:46:17',1,now(),now());
insert into "collaborator" (cpf, name, gender, birthdate, sector_id, created_date, updated_date) VALUES ('45632178910', 'Carla', 'f', '1990-03-07 10:46:17',1,now(),now());
insert into "collaborator" (cpf, name, gender, birthdate, sector_id, created_date, updated_date) VALUES ('78912345610', 'Larissa', 'f', '1990-03-07 10:46:17',1,now(),now());
insert into "collaborator" (cpf, name, gender, birthdate, sector_id, created_date, updated_date) VALUES ('10123456789', 'Edna', 'f', '1990-03-07 10:46:17',1,now(),now());
insert into "sector" (name, description, created_date, updated_date) VALUES ('WiFi', 'Setor de WiFi', now(), now());
insert into "collaborator" (cpf, name, gender, birthdate, sector_id, created_date, updated_date) VALUES ('22245678910', 'lara', 'f', '1998-06-07 10:46:17'
                                                                                                        , 2, now(), now());
insert into "collaborator" (cpf, name, gender, birthdate, sector_id, created_date, updated_date) VALUES ('09765432110', 'Jorgina', 'f', '1994-06-07 10:46:17',2,now(),now());
insert into "collaborator" (cpf, name, gender, birthdate, sector_id, created_date, updated_date) VALUES ('56632178910', 'Carla', 'f', '1990-03-07 10:46:17',2,now(),now());
insert into "collaborator" (cpf, name, gender, birthdate, sector_id, created_date, updated_date) VALUES ('98912345610', 'Larissa', 'f', '1990-03-07 10:46:17',2,now(),now());
insert into "collaborator" (cpf, name, gender, birthdate, sector_id, created_date, updated_date) VALUES ('50123456789', 'Edna', 'f', '1990-03-07 10:46:17',2,now(),now());


SELECT pct
from (SELECT s.id,
             (count(c.id) + 1) * 100.0 / (SELECT (count(c2.id) + 1)
                                          from sector s
                                                   inner join collaborator c2 on s.id = c2.sector_id
                                          where s.id = 1) as pct
      from sector s
               inner join collaborator c on s.id = c.sector_id
      where c.gender = 'm' and s.id = 1
      group by s.id) as sub;