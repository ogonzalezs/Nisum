
INSERT INTO USERS (id,name, email, password,created_at, updated_at, is_active) VALUES ('e4eaaaf2-d142-11e1-b3e4-080027620cdd','Juan Rodriguez', 'juan@rodriguez.org', 'hunter2', CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 1);
INSERT INTO USERS (id,name, email, password,created_at, updated_at, is_active) VALUES ('5f658089-a3e3-46e4-aa89-33e96b8fe824','Orlando Gonzalez', 'orlando@mail.com', 'Night', CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 1);


INSERT INTO PHONES (user_id, number, citycode, countrycode) VALUES ('e4eaaaf2-d142-11e1-b3e4-080027620cdd','1234567', '1', '57');

INSERT INTO PHONES (user_id, number, citycode, countrycode) VALUES ('5f658089-a3e3-46e4-aa89-33e96b8fe824','2437395', '1', '57');
