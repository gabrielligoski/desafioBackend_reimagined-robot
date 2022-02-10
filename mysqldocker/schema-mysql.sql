use backend_challenge;

CREATE TABLE IF NOT EXISTS users
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    nickname          VARCHAR(255) NOT NULL,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE = INNODB;

CREATE TABLE IF NOT EXISTS events
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    event_type VARCHAR(255) NOT NULL,
    event_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE = INNODB;

insert into users (nickname)
VALUES ('Daniel'),
       ('Gabriel'),
       ('Thiago'),
       ('Ana'),
       ('Cristina');

insert into events (event_type)
VALUES ('type_A_event'),
       ('type_B_event'),
       ('type_C_event'),
       ('type_D_event'),
       ('type_E_event');

# select *
# from users;
#
# select *
# from events;
# SELECT VERSION();