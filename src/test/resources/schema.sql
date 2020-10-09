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

commit;
