drop database test
create database test
use database test
show databases
show database test


create table ttt (a Int PRIMARY KEY, b Float not null, c Long not null, d Double not null, e VARCHAR(10), unique(b, c, d))
show table ttt
drop table ttt
create table dept (dept_name VARCHAR(20) PRIMARY KEY, building VARCHAR(20) not null, budget Double not null)
create table inst (inst_name VARCHAR(20) PRIMARY KEY, dept_name VARCHAR(20) not null, salary Double not null)


insert into inst values ('Srinivasan', 'Comp. Sci.', 65000);
insert into inst values ('Wu', 'Finance', 90000);
insert into inst values ('Mozart', 'Music', 40000);
insert into inst values ('Einstein', 'Physics', 95000);
insert into inst values ('El Said', 'History', 60000);
insert into inst values ('Gold', 'Physics', 87000);
insert into inst values ('Katz', 'Comp. Sci.', 75000);
insert into inst values ('Califieri', 'History', 62000);
insert into inst values ('Singh', 'Finance', 80000);
insert into inst values ('Crick', 'Biology', 72000);
insert into inst values ('Brandt', 'Comp. Sci.', 92000);
insert into inst values ('Kim', 'Elec. Eng.', 80000);

insert into dept values ('Biology', 'Watson', 90000);
insert into dept values ('Comp. Sci.', 'Taylor', 100000);
insert into dept values ('Elec. Eng.', 'Taylor', 85000);
insert into dept values ('Finance', 'Painter', 120000);
insert into dept values ('History', 'Painter', 50000);
insert into dept values ('Music', 'Packard', 80000);
insert into dept values ('Physics', 'Watson', 70000);

delete from inst where salary >= 90000;
delete from dept where dept_name = 'Biology';
update inst set dept_name = 'Comp. Sci.' where dept_name = 'Biology';

select inst_name, salary from inst where dept_name = 'Comp. Sci.';

select inst_name, inst.dept_name, salary, building, budget from inst natural join dept where dept.building = 'Taylor'

exit