INSERT INTO "programs" (program_name, start_date, end_date, working_hours) VALUES ( 'Internship ABAP 2020', '2020-03-01', '2020-05-30', 6),
       ( 'Summer School 2019', '2019-07-15', '2019-08-30', 6),
       ( 'Java Training Spring 2020', ' 2020-03-15', '2020-05-30', 6);

INSERT INTO users ( first_name, last_name, username, password, role, program_id) VALUES
    ( 'Andrei', 'Bercean', 'bandrei', '$2a$10$Tk9jij.zSUELn/ztjLI8tuME6psQW1hCq8hCrZCRWlEOoEOjvS6P6', 1, 3),
    ( 'Andrada', 'Vadean', 'vandrada', '$2a$10$yDdNSKGYucCCIGnRSGhY1uDKpNbTMowTFZsLsX056MU9SzRMsvCOi', 1, 3),
    ( 'Alina', 'Somcutean', 'salina', '$2a$10$vmGJBv0s7W3/O/UvITzmbeUOhQdOU1fzOAl.9jGoDafuZDwUJj6hm', 1, 3),
    ( 'Emanuela', 'Ionas', 'iema', '$2a$10$u8XDgRf1UDzo9.nOg1VdzuNERIXEFPo0Jm3VXKG9wvfAVzFnSRxgq', 1, 3),
    ( 'Stefan', 'Morar', 'mstefan', '$2a$10$4jaavlJW955RL4nmjqnTOec5jY8HBfUr5XedIeCY0CXhzhYA7vIhu', 1, 3),
    ( 'Truta', 'Patricia', 'tpatricia', '$2a$10$eVvQZSWx8VKoH1Pg3Vpm1uwIxl0sCiYP2YX.AN70ijS9z9/3ZzvI.', 1, 3),
    ( 'Alex', 'Muresan', 'admin', '$2a$10$hJ5nb5D9F5RimHDK5bIJy.d/yucIsDLELdBMlkUhtlWTBr3Qyxmoq', 0, 3);

INSERT INTO "psp" (name, program_id) VALUES( 'PSP Self Study',1),
       ('PSP Meeting', 1),
       ( 'PSP Development', 1),
       ( 'PSP Self Study',2),
       ( 'PSP Meeting', 2),
       ( 'PSP Development', 2),
       ( 'PSP Self Study',3),
       ( 'PSP Meeting', 3),
       ( 'PSP Development', 3);
INSERT INTO "booking" (day, user_id) VALUES ('2020-05-30',1),
       ('2020-05-30', 2),
       ('2020-05-30', 3),
       ('2020-05-30', 4),
       ('2020-05-30', 5),
       ('2020-05-30', 6),
       ('2020-05-30', 7);
INSERT INTO "booking_detail" ( start_hour, end_hour, description, status, psp_id, booking_id)
                                         	VALUES ( '8:00', '16:00', 'Value',  1, 1, 1),
                                         	( '8:00', '16:00', 'Value',  1, 1, 2),
                                         	( '8:00', '16:00', 'Value',  1, 1, 3),
                                         	( '8:00', '16:00', 'Value',  1, 1, 4),
                                         	( '8:00', '16:00', 'Value',  1, 1, 5),
                                         	( '8:00', '16:00', 'Value',  1, 1, 6),
                                         	( '8:00', '16:00', 'Value',  1, 1, 7);


