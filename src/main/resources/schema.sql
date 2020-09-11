drop table if exists lessons;
drop table if exists teachers_subjects;
drop table if exists teachers;
drop table if exists subjects;
drop table if exists students;
drop table if exists groups;
drop table if exists classrooms;
drop table if exists teacher_daily_timetable;
drop table if exists group_daily_timetable;
drop table if exists teacher_monthly_timetable;
drop table if exists group_monthly_timetable;

create table teachers (
id serial not null primary key,
first_name varchar(20) not null,
last_name varchar(20) not null,
age integer not null
);

create table subjects(
id serial not null primary key,
name varchar(20) not null
);

create table teachers_subjects(
constraint id primary key (teacher_id, subject_id), 
teacher_id integer references teachers (id) on delete cascade,
subject_id integer references subjects (id) on delete cascade
);

create table classrooms(
id serial not null primary key,
name varchar(40) not null,
size integer not null
);

create table groups (
id serial not null primary key,
name varchar(20) not null
);

create table students (
id serial not null primary key,
first_name varchar(20) not null,
last_name varchar(20) not null,
age integer not null,
group_id integer references groups(id) on delete cascade
);

create table teacher_daily_timetable(
id serial not null primary key,
date Date
);

create table group_daily_timetable(
id serial not null primary key,
date Date
);

create table teacher_monthly_timetable(
id serial not null primary key,
year integer not null, 
month integer not null
);

create table group_monthly_timetable(
id serial not null primary key,
year integer not null,
month integer not null
);

create table lessons(
id serial not null primary key,
classroom_id integer references classrooms(id) on delete cascade,
teacher_id integer not null,
subject_id integer not null,
constraint teacher_subject_id
foreign key (teacher_id, subject_id) references teachers_subjects(teacher_id, subject_id) on delete cascade,
group_id integer references groups(id)  on delete cascade,
start_time timestamp
);

insert into teachers (id, first_name, last_name, age) values
(1, 'Bob', 'Jordan', 41),
(2, 'Jennifer', 'May', 50),
(3, 'Robert', 'Smith', 36),
(4, 'Jennifer', 'Watson', 45);

insert into subjects (id, name) values
(1, 'Mathematics'),
(2, 'Biology'),
(3, 'Astronomy'),
(4, 'Engineering');

insert into teachers_subjects (teacher_id, subject_id) values
(1,1),
(1,2),
(1,3),
(2,2),
(2,3),
(3,1),
(4,3),
(4,4);

insert into classrooms (id, name, size) values
(1, 'Class of Mathematics', 30),
(2, 'Class of Biology', 20),
(3, 'Class of Astronomy', 25),
(4, 'Class of Engineering', 15);

insert into groups (id, name) values
(1, 'sd-45'),
(2, 'zz-33'),
(3, 'fg-55'),
(4, 'an-23'),
(5, 'af-11');

insert into students (id, first_name, last_name, age, group_id) values
(1, 'Jack', 'Bobson', 22, 1),
(2, 'Ann', 'May', 19, 2),
(3, 'William', 'Shakespeare', 23, 4),
(4, 'Mary', 'Jay', 22, 1);

insert into lessons (id, classroom_id, teacher_id, subject_id, group_id, start_time) values
(1, 3, 1, 1, 2, '2020-09-03 08:00'),
(2, 2, 1, 2, 3, '2020-09-03 09:00'),
(3, 1, 3, 1, 4, '2020-09-03 10:00'),
(4, 4, 4, 3, 1, '2020-09-03 11:00'),
(5, 4, 4, 3, 1, '2020-09-03 12:00'),
(6, 3, 3, 1, 2, '2020-09-04 08:00'),
(7, 2, 1, 3, 3, '2020-09-04 09:00'),
(8, 1, 2, 2, 4, '2020-09-04 10:00'),
(9, 4, 1, 1, 1, '2020-09-04 11:00'),
(10, 4, 4, 3, 1, '2020-09-04 12:00'),
(11, 3, 3, 1, 2, '2020-09-07 08:00'),
(12, 2, 1, 3, 3, '2020-09-07 09:00'),
(13, 1, 2, 2, 4, '2020-09-07 10:00'),
(14, 4, 1, 2, 5, '2020-09-07 11:00'),
(15, 4, 4, 3, 1, '2020-09-07 12:00'),
(16, 3, 1, 1, 2, '2020-10-05 08:00'),
(17, 1, 2, 2, 4, '2020-10-05 10:00'),
(18, 4, 1, 2, 5, '2020-10-06 11:00'),
(19, 4, 4, 3, 1, '2020-10-06 12:00');

commit;
