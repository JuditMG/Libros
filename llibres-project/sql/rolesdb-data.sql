source rolesdb-schema.sql;

insert into users values('alicia', MD5('alicia'), 'Alicia', 'alicia@acme.com');
insert into user_roles values ('alicia', 'registered');

insert into users values('blas', MD5('blas'), 'Blas', 'blas@acme.com');
insert into user_roles values ('blas', 'registered');

insert into users values('admin', MD5('admin'), 'Admin', 'admin@acme.com');
insert into user_roles values ('admin', 'admin');

insert into users values('judit', MD5('judit'), 'Test', 'judit@acme.com');
insert into user_roles values ('judit', 'registered');