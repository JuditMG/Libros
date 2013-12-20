source llibresdb-schema.sql;
insert into users values('alicia', 'Alicia', 'alicia@acme.com');
insert into users values('judit', 'judit', 'judit@acme.com');
insert into users values('blas', 'blas', 'blas@acme.com');

insert into llibres (llibreid,titulo, autor, lengua, edicion, fecha_edicion, fecha_impresion, editorial ) values (1,'el hobbit', 'J.R.R. TOLKIEN', 'castellano','12','2002-02-01','2002-09-03','minotaurio');
insert into llibres (llibreid,titulo, autor, lengua, edicion, fecha_edicion, fecha_impresion, editorial ) values (2,'LOS HIJOS DE HURIN', 'J.R.R. TOLKIEN', 'castellano','12','2002-02-01','2002-09-03','minotaurio');
insert into llibres (llibreid,titulo, autor, lengua, edicion, fecha_edicion, fecha_impresion, editorial ) values (3,'igual', 'J.R.R. TOLKIEN', 'castellano','2','2002-02-01','2002-09-03','minotaurio');
insert into llibres (llibreid,titulo, autor, lengua, edicion, fecha_edicion, fecha_impresion, editorial ) values (4, 'El señor de los anillos: las 2 torres', 'J.R.R. TOLKIEN', 'castellano','12','2002-02-01','2002-09-03','minotaurio');
insert into llibres (llibreid,titulo, autor, lengua, edicion, fecha_edicion, fecha_impresion, editorial ) values (5, 'infierno', 'Dan Brown', 'castellano','12','2010-03-11','2012-09-03','planeta');
insert into llibres (llibreid,titulo, autor, lengua, edicion, fecha_edicion, fecha_impresion, editorial ) values (6, 'el símbolo perdido', 'Dan Brown', 'castellano','3','2010-05-11','2012-11-03','planeta');
insert into llibres (llibreid,titulo, autor, lengua, edicion, fecha_edicion, fecha_impresion, editorial ) values (7, 'la fortaleza digital', 'Dan Brown', 'castellano','1','2010-03-11','2012-09-03','planeta');
insert into llibres (llibreid,titulo, autor, lengua, edicion, fecha_edicion, fecha_impresion, editorial ) values (8, 'la conspiración', 'Dan Brown', 'castellano','10','2009-03-11','2012-09-03','planeta');
insert into llibres (llibreid,titulo, autor, lengua, edicion, fecha_edicion, fecha_impresion, editorial ) values (9, 'prova', 'Dan Brown', 'cat','10','2002-03-11','2015-09-03','planeta');
insert into llibres (llibreid,titulo, autor, lengua, edicion, fecha_edicion, fecha_impresion, editorial ) values (8, 'prosxfk', 'Dan Brown', 'castellano','10','2109-03-11','2012-09-03','planeta');

insert into resenas (resenaid,llibreid, username, realname, content) values (1,1, 'blas', 'blas perez', 'muy interesante a la espera de la pelicula');
insert into resenas (resenaid,llibreid, username, realname, content) values (2,1, 'alicia', 'Alicia martin', 'Chulo!');
insert into resenas (resenaid,llibreid, username, realname, content) values (3,2, 'blas','blas perez', 'me gusto tanto que no pude dejar de leerlo');
insert into resenas (resenaid,llibreid, username, realname, content) values (4,6, 'blas', 'blas perez', 'Giros inesperados, finales apocalípticos, etc. Todas esas cosas las había leido de opiniones de lectores antes de sumergirme en la lectura de esta novela. Es cierto que la escritura sencilla y lectura fácil hace que leas el libro casi del tirón. Pero sigo viendo lo mismo de siempre en este autor.');
insert into resenas (resenaid,llibreid, username, realname, content) values (5,5, 'alicia', 'Alicia martin', 'En este libro trata un tema bastante bueno. La única pega es que el desarrollo del libro es bastante parecido a los anteriores. Y como siempre se aprende mucho mucho. te lo recomiendo.');
insert into resenas (resenaid,llibreid, username, realname, content) values (6,8, 'alicia', 'Alicia martin', 'En su linea. Si te gusta su forma de escribir no te defraudará, pero quizá sea prescindible.');
insert into resenas (resenaid,llibreid, username, realname, content) values (7,8, 'judit', 'judit mg', 'Puro Dan Brown: agil, entretenido, para pasar un buen rato.');

