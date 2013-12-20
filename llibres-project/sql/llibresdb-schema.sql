drop database if exists llibresdb;
create database llibresdb;

use llibresdb;

create table users (
    username        		varchar(20) not null primary key,
    name                	varchar(70) not null,
    email               	varchar(255) not null
);

create table llibres (
        llibreid                         int not null auto_increment primary key,
        titulo                                 varchar(100) not null,
        autor                                varchar(100) not null,
        lengua                                varchar(50) not null,
        edicion                                varchar(100) not null,
        fecha_edicion                varchar(59) not null,
        fecha_impresion                varchar(59) not null,
        editorial                        varchar(100) not null,
        lastModified                        timestamp
);

create table resenas (
        resenaid                         int not null auto_increment primary key,
        llibreid                         int not null,
        username                         varchar(20) not null,
	realname			varchar(20),
        lastModified                        timestamp, 
        content                                varchar(500) not null,
        foreign key(username) references users(username),
        foreign key(llibreid) references llibres(llibreid)
);