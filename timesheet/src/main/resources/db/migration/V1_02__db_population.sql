INSERT INTO "timesheet"."programs" VALUES (1, 'Internship ABAP 2020', '2020-03-01', '2020-05-30', 6),
       (2, 'Summer School 2019', '2019-07-15', '2019-08-30', 6),
       (3, 'Java Training Spring 2020', ' 2020-03-15', '2020-05-30', 6);
INSERT INTO "timesheet"."users" VALUES (1, 'Andrei', 'Bercean', 'bandrei', 'bandrei', 0, 3),
       (2, 'Andrada', 'Vadean', 'vandrada', 'vandrada', 0, 3),
       (3, 'Alina', 'Somcutean', 'salina', 'salina', 0, 3),
       (4, 'Emanuela', 'Ionas', 'iema', 'iema', 0, 3),
       (5, 'Stefan', 'Morar', 'mstefan', 'mstefan', 0, 3),
       (6, 'Truta', 'Patricia', 'tpatricia', 'tpatricia', 0, 3),
       (7, 'Alex', 'Muresan', 'admin', 'admin', 1, 3);
INSERT INTO "timesheet"."psp" VALUES (1, 'PSP Self Study',1),
       (2, 'PSP Meeting', 1),
       (3, 'PSP Development', 1),
       (4, 'PSP Self Study',2),
       (5, 'PSP Meeting', 2),
       (6, 'PSP Development', 2),
       (7, 'PSP Self Study',3),
       (8, 'PSP Meeting', 3),
       (9, 'PSP Development', 3);
INSERT INTO "timesheet"."booking" VALUES (1, '2020-05-30',1),
       (2, '2020-05-30', 2),
       (3, '2020-05-30', 3),
       (4, '2020-05-30', 4),
       (5, '2020-05-30', 5),
       (6, '2020-05-30', 6),
       (7, '2020-05-30', 7);
INSERT INTO "timesheet"."booking_detail" VALUES (1, '8:00', '16:00', 'Value',  1, 1, 1);


