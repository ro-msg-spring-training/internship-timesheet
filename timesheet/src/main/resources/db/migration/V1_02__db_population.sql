INSERT INTO "programs" (program_name, start_date, end_date, working_hours) VALUES ( 'Internship ABAP 2020', '2020-03-01', '2020-05-30', 6),
       ( 'Summer School 2019', '2019-07-15', '2019-08-30', 6),
       ( 'Java Training Spring 2020', ' 2020-03-15', '2020-05-30', 6);

INSERT INTO users ( first_name, last_name, username, password, role, program_id) VALUES
    ( 'Andrei', 'Bercean', 'bandrei', '$2a$10$Tk9jij.zSUELn/ztjLI8tuME6psQW1hCq8hCrZCRWlEOoEOjvS6P6', 'USER', 3),
    ( 'Andrada', 'Vadean', 'vandrada', '$2a$10$yDdNSKGYucCCIGnRSGhY1uDKpNbTMowTFZsLsX056MU9SzRMsvCOi', 'USER', 3),
    ( 'Alina', 'Somcutean', 'salina', '$2a$10$vmGJBv0s7W3/O/UvITzmbeUOhQdOU1fzOAl.9jGoDafuZDwUJj6hm', 'USER', 3),
    ( 'Emanuela', 'Ionas', 'iema', '$2a$10$u8XDgRf1UDzo9.nOg1VdzuNERIXEFPo0Jm3VXKG9wvfAVzFnSRxgq', 'USER', 3),
    ( 'Stefan', 'Morar', 'mstefan', '$2a$10$4jaavlJW955RL4nmjqnTOec5jY8HBfUr5XedIeCY0CXhzhYA7vIhu', 'USER', 3),
    ( 'Patricia', 'Truta', 'tpatricia', '$2a$10$eVvQZSWx8VKoH1Pg3Vpm1uwIxl0sCiYP2YX.AN70ijS9z9/3ZzvI.', 'USER', 3),
    ( 'Elena', 'Pop', 'epop', '$2a$10$Tk9jij.zSUELn/ztjLI8tuME6psQW1hCq8hCrZCRWlEOoEOjvS6P6', 'USER', 1),
    ( 'Ioan', 'Popescu', 'ipopescu', '$2a$10$Tk9jij.zSUELn/ztjLI8tuME6psQW1hCq8hCrZCRWlEOoEOjvS6P6', 'USER', 1),
    ( 'Dragos', 'Filip', 'dfilip', '$2a$10$Tk9jij.zSUELn/ztjLI8tuME6psQW1hCq8hCrZCRWlEOoEOjvS6P6', 'USER', 1),
    ( 'Alex', 'Muresan', 'admin', '$2a$10$hJ5nb5D9F5RimHDK5bIJy.d/yucIsDLELdBMlkUhtlWTBr3Qyxmoq', 'ADMIN', 3);

INSERT INTO "psp" (name, program_id) VALUES( 'PSP Self Study',1),
       ('PSP Meeting', 1),
       ( 'PSP Development', 1),
       ( 'PSP Self Study',2),
       ( 'PSP Meeting', 2),
       ( 'PSP Development', 2),
       ( 'PSP Self Study',3),
       ( 'PSP Meeting', 3),
       ( 'PSP Development', 3);
INSERT INTO "booking" (day, user_id) VALUES ('2020-05-25', 1),
       ('2020-05-25', 2),
       ('2020-05-25', 3),
       ('2020-05-25', 4),
       ('2020-05-25', 5),
       ('2020-05-25', 6),
       ('2020-05-25', 7),
       ('2020-04-01', 1),
       ('2020-04-01', 2),
       ('2020-04-01', 3),
       ('2020-04-01', 4),
       ('2020-04-01', 5),
       ('2020-04-01', 6),
       ('2020-04-01', 7),
       ('2019-12-10', 5),
       ('2020-01-13', 5),
       ('2020-05-24', 5),
       ('2020-05-23', 5);
INSERT INTO "booking_detail" ( start_hour, end_hour, description, status, psp_id, booking_id)
                                         	VALUES ( '8:00', '9:00', 'Flyway Study',  1, 7, 1),
                                         	( '9:00', '10:00', 'Database Creation',  1, 9, 1),
                                         	( '11:00', '15:30', 'Final Meeting',  1, 8, 1),
                                         	( '8:00', '16:00', 'REST Api',  1, 7, 2),
                                         	( '8:00', '9:00', 'Daily Meeting',  1, 8, 3),
                                         	( '8:00', '16:00', 'Controller',  1, 9, 4),
                                         	( '8:00', '16:00', 'SAPUI5',  1, 7, 5),
                                         	( '8:00', '16:00', 'Unit testing',  1, 9, 6),
                                         	( '10:00', '12:00', 'Final presentation',  1, 8, 7),
                                         	( '11:00', '16:00', 'Repository',  1, 7, 8),
                                            ( '12:00', '16:00', 'Flyway study',  1, 7, 9),
                                            ( '8:00', '13:00', 'Final Meeting',  1, 8, 10),
                                            ( '9:00', '16:30', 'Integration',  1, 9, 11),
                                            ( '8:30', '16:30', 'SAPUI5',  1, 7, 12),
                                            ( '8:20', '16:00', 'Front-end',  1, 9, 13),
                                            ( '11:15', '16:15', 'Daily meeting',  1, 8, 14),
                                            ( '8:00', '9:00', 'Flyway Study',  1, 7, 15),
                                            ( '9:00', '10:00', 'Database Creation',  1, 9, 15),
                                            ( '11:00', '15:30', 'Final Meeting',  1, 8, 15),
                                            ( '8:00', '10:00', 'REST Api',  1, 7, 16),
                                            ( '11:30', '15:00', 'Daily Meeting',  1, 8, 16),
                                            ( '8:00', '13:00', 'Final Meeting',  1, 8, 17),
                                            ( '9:00', '16:30', 'Integration',  1, 9, 18);


