insert into administrador(usuario,clave,nombre,apellido) 
	values('admin',md5('admin'),'admin','admin') ;


insert into periodo(fecha_inicio,fecha_fin,director,subdirector,cordinador) values
	('2016-9-1','2017-7-30','Kateryne Pazmino','Catalina Itriago','María José quintanilla'),
	('2017-9-1','2018-7-30','Juan Palacios','Catalina Itriago','María José quintanilla');	

insert into docente(cedula,usuario,clave,nombre,apellido) values
	('060405974-1','mirejack',md5('alip4$'),'Alexander David','Bonilla Adriano'),
	('140096640-2','alexanderda',md5('mirep4$'),'Mireya jackeline','Ortega Pazato');



insert into materia(nombre,dominio) values 
	('Estudios Sociales','Dominio Recreativo'),
	('Lengua y Literatura','Dominio Comunicación');
	
	
	
insert into alumno(apellido,nombre,cedula) values
	('CAGUANO','ERICK','065838557-6'),
	('CAMACHO','MATEO','056437659-4'),
	('CARPINTERO', 'LUIS','041296490-8'),
	('CHIRAU','DEIFILIO','048973209-5'),	
	('COLCHA','KATHERINE','017652978-4'),
	('SANCHEZ','SAID','049018532-6'),
	('SOCOY','BRYAN','040951247-4'),

	('INCA','ADRIAN','456897345-7'),
	('PALACIOS','SEBASTIAN','124765498-9'),
	('OCAÑA','ALBERTO','012876549-7'),
	('AGUAGALLO','ANDRES','148645678-7'),
	('AMAGUAYA','MISHELL','035764579-8'),
	('SUAREZ','JOSUE','309845671-1'),
	('PAGUAY','ISRAEL','056781193-7');



------------------------------------------------------------------------	
insert into curso(periodo, docente, materia, grado, paralelo) values
	(1,'060405974-1',1,'Septimo','A'),
	(1,'060405974-1',1,'Septimo','A'),
	(1,'060405974-1',1,'Septimo','A');


--NOTAS DE LENLUAJE DE CAGUANO ERICK
insert into ciclo(curso,alumno) values(1,'065838557-6');
insert into quimestre(ciclo,quimestral) values (1,9);

insert into parcial(quimestre, descripcion, tarea, individual, grupal, nota_parcial) 
	values(1,'PRIMER PARCIAL',7,8,10,8),
			(1,'SEGUNDO PARCIAL',10,8,7,8),
			(1,'TERCER PARCIAL',7,8,8,8);
			
insert into quimestre(ciclo,quimestral) values (1,9);
insert into parcial(quimestre, descripcion, tarea, individual, grupal, nota_parcial) 
	values	(2,'PRIMER PARCIAL',10,9,7,8),
			(2,'SEGUNDO PARCIAL',7,8,8,9),
			(2,'TERCER PARCIAL',10,8,10,10);



			
--NOTAS DE LENLUAJE DE CAMACHO MATEO 
insert into ciclo(curso,alumno) values(2,'056437659-4');

insert into quimestre(ciclo,quimestral) values (2,10);
insert into parcial(quimestre, descripcion, tarea, individual, grupal, nota_parcial) 
	values(3,'PRIMER PARCIAL',8,5,10,7),
			(3,'SEGUNDO PARCIAL',8,9,7,8),
			(3,'TERCER PARCIAL',8,9,8,8);
			
insert into quimestre(ciclo,quimestral) values (2,9);
insert into parcial(quimestre, descripcion, tarea, individual, grupal, nota_parcial) 
	values	(4,'PRIMER PARCIAL',10,9,7,8),
			(4,'SEGUNDO PARCIAL',8,9,8,7),
			(4,'TERCER PARCIAL',8,7,10,7);		
			
			


--NOTAS DE LENLUAJE DE CARPINTERO LUIS 
insert into ciclo(curso,alumno) values(3,'041296490-8');
insert into quimestre(ciclo,quimestral) values (3,8);

insert into parcial(quimestre, descripcion, tarea, individual, grupal, nota_parcial) 
	values(5,'PRIMER PARCIAL',9,10,10,9),
			(5,'SEGUNDO PARCIAL',9,8,7,8),
			(5,'TERCER PARCIAL',9,10,8,8);
			
insert into quimestre(ciclo,quimestral) values (3,8);
insert into parcial(quimestre, descripcion, tarea, individual, grupal, nota_parcial) 
	values	(6,'PRIMER PARCIAL',9,7,7,8),
			(6,'SEGUNDO PARCIAL',7,7,8,8),
			(6,'TERCER PARCIAL',10,10,10,10);						
			
------------------------------------------------------------------------


/*
--NOTAS DE LENLUAJE DE CAGUANO ERICK
insert into ciclo(curso) values(1);
insert into quimestre(ciclo,quimestral) values (1,9);

insert into parcial(quimestre, descripcion, tarea, individual, grupal, nota_parcial) 
	values(1,'PRIMER PARCIAL'),
			(1,'SEGUNDO PARCIAL'),
			(1,'TERCER PARCIAL');
			
insert into quimestre(ciclo,quimestral) values (1,9);
insert into parcial(quimestre, descripcion, tarea, individual, grupal, nota_parcial) 
	values	(2,'PRIMER PARCIAL'),
			(2,'SEGUNDO PARCIAL'),
			(2,'TERCER PARCIAL');			
*/
