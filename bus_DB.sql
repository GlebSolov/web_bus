CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- удаление таблиц если они существуют

DROP TABLE IF EXISTS user_trip;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS schedule;
DROP TABLE IF EXISTS stations;
DROP TABLE IF EXISTS route;

-- теперь создадим таблицы

CREATE TABLE IF NOT EXISTS users
(
    user_id           serial PRIMARY KEY,
    is_admin          bool,
    user_name         text NOT NULL,
    user_contact_info text,
	passw             text NOT NULL
);



CREATE TABLE IF NOT EXISTS route(
    route_id        serial PRIMARY KEY
    ,route_name     text
    ,route_price    numeric
);

CREATE TABLE IF NOT EXISTS stations(
    station_name            text
    ,station_id             serial      PRIMARY KEY
    ,route_id               int         REFERENCES route (route_id)
    ,number_in_route        int
);


CREATE TABLE IF NOT EXISTS schedule(
    trip_id         serial              PRIMARY KEY
    ,route_id       int                 REFERENCES route (route_id)
    ,date_time      timestamp
    ,seats          int
)
;

CREATE TABLE IF NOT EXISTS user_trip(
    user_id             int           REFERENCES users (user_id),
    trip_id             int           REFERENCES schedule (trip_id),
    from_station_id     int           REFERENCES stations (station_id),
    to_station_id       int           REFERENCES stations (station_id),
    user_in_trip_id     serial        PRIMARY KEY
);

--заполнение
INSERT INTO users (is_admin, user_name, user_contact_info, passw) VALUES
    (TRUE, 'Соловьев Глеб Геннадьевич', '{"phone": "+7(122)444-44-44"}', '1234dvnj')
    ,(FALSE, 'Ким Сергей Суренович', '{"phone": "+7(111)555-55-55"}', '12edkmf')
    ,(FALSE, 'Васечкин Петр Олегович', '{"phone": "+7(333)666-66-66"}', '1ejnsck');

select * from route;
INSERT INTO route(route_id, route_name, route_price) VALUES
    (1, 'Маршрут А', 123),
    (2, 'Марщрут Б', 228),
    (3, 'Маршрут В', 69);

INSERT INTO stations(route_id, station_name, number_in_route) VALUES
    (1, 'озеро', 1),
    (1, 'камень', 2),
    (1, 'пляж', 3),
    (1, 'холм', 4),
    (1, 'лес', 5),
    (1, 'школа', 6),
    
    (2, 'университет', 1),
    (2, 'отель', 2),
    (2, 'дума', 3),
    (2, 'метро', 4),
    (2, 'перекосившийся дом', 5),
    (2, 'депо', 6),
    
    (3, 'стадион', 1),
    (3, 'театр', 2),
    (3, 'кинотеатр', 3),
    (3, 'цирк', 4),
    (3, 'суд', 5),
    (3, 'участок', 6),
    (3, 'поле', 7)
;

INSERT INTO schedule(route_id, date_time, seats)
VALUES  (1, '2021-8-21 20:15:00', 36)
        ,(3, '2021-6-28 22:44:00', 10)
        ,(2, '2021-9-2 12:43:56', 25)
        ,(3, '2021-9-17 12:34:00', 37)
        ,(2, '2022-02-10 14:30:00', 1)
;

INSERT INTO user_trip(user_id, trip_id, from_station_id, to_station_id)
VALUES (1, 1, 1, 2)
       ,(2, 1, 9, 11)
;