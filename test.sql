drop database test
create database test
use database test
create table ttt2 (a Int PRIMARY KEY, b Float not null, c Long not null, d Double not null, e VARCHAR(10), unique(b, c, d))
