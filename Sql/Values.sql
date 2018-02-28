insert into administrador(usuario,clave,nombre,apellido) 
	values('admin',md5('admin'),'admin','admin') ;


insert into periodo(fecha_inicio,fecha_fin,director,subdirector,coordinador) values
	('2016-9-1','2017-7-30','Kateryne Pazmino','Catalina Itriago','María José quintanilla'),
	('2017-9-1','2018-7-30','Juan Palacios','Catalina Itriago','María José quintanilla');	

insert into docente(cedula,usuario,clave,nombre,apellido) values
	('0604059741', 'alexanderda', md5('alip4$'), 'Alexander David', 'Bonilla Adriano'),
	('1400966402', 'mirejack', md5('mirep4$'), 'Mireya jackeline', 'Ortega Pazato'),
    ('8434566765', 'Juanca', md5('12345'), 'Juan Carlos', 'Perez Palacios'),
    ('2438674282', 'Pedin', md5('12345'), 'Pedro Pablo', 'Martinez quintana'),
    ('1084477473', 'Marye', md5('12345'), 'Mária Eva', 'Lara Romero');



insert into materia(nombre,dominio) values 
	('Estudios Sociales','Dominio Recreativo'),
	('Lengua y Literatura','Dominio Comunicación'),
	('Matemática','Dominio Vocacional'),
	('Ciencias Naturales','Dominio vida en el hogar salud y seguridad');

	
	
	
	
insert into alumno(apellido,nombre,cedula) values
	('CAGUANO','ERICK','0658385576'),
	('CAMACHO','MATEO','0564376594'),
	('CARPINTERO', 'LUIS','0412964908'),
	('CHIRAU','DEIFILIO','0489732095'),	
	('COLCHA','KATHERINE','0176529784'),
	('SANCHEZ','SAID','0490185326'),
	('SOCOY','BRYAN','0409512474'),

	('INCA','ADRIAN','4568973457'),
	('PALACIOS','SEBASTIAN','1247654989'),
	('OCAÑA','ALBERTO','0128765497'),
	('AGUAGALLO','ANDRES','1486456787'),
	('AMAGUAYA','MISHELL','0357645798'),
	('SUAREZ','JOSUE','3098456711'),
	('PAGUAY','ISRAEL','0567811937');


/*
------------------------------------------------------------------------	
insert into curso(periodo, docente, materia, grado, paralelo) values
	(1,'060405974-1',1,'Septimo','A'),
	(2,'140096640-2',1,'Primedo','A');



--NOTAS DE LENLUAJE DE CAGUANO ERICK
insert into ciclo(curso,alumno) values(1,'065838557-6');
insert into quimestre(ciclo,descripcion,quimestral) values (1,'PRIMER QUIMESTRE',9);

insert into parcial(quimestre, descripcion, tarea, individual, grupal, nota_parcial) 
	values(1,'PRIMER PARCIAL',7,8,10,8),
			(1,'SEGUNDO PARCIAL',10,8,7,8),
			(1,'TERCER PARCIAL',7,8,8,8);
			
insert into quimestre(ciclo,descripcion,quimestral) values (1,'SEGUNDO QUIMESTRE',9);
insert into parcial(quimestre, descripcion, tarea, individual, grupal, nota_parcial) 
	values	(2,'PRIMER PARCIAL',10,9,7,8),
			(2,'SEGUNDO PARCIAL',7,8,8,9),
			(2,'TERCER PARCIAL',10,8,10,10);



			
--NOTAS DE LENLUAJE DE CAMACHO MATEO 
insert into ciclo(curso,alumno) values(1,'056437659-4');

insert into quimestre(ciclo,descripcion,quimestral) values (2,'PRIMER QUIMESTRE',10);
insert into parcial(quimestre, descripcion, tarea, individual, grupal, nota_parcial) 
	values(3,'PRIMER PARCIAL',8,5,10,7),
			(3,'SEGUNDO PARCIAL',8,9,7,8),
			(3,'TERCER PARCIAL',8,9,8,8);
			
insert into quimestre(ciclo,descripcion,quimestral) values (2,'SEGUNDO QUIMESTRE',9);
insert into parcial(quimestre, descripcion, tarea, individual, grupal, nota_parcial) 
	values	(4,'PRIMER PARCIAL',10,9,7,8),
			(4,'SEGUNDO PARCIAL',8,9,8,7),
			(4,'TERCER PARCIAL',8,7,10,7);		
			
			


--NOTAS DE LENLUAJE DE CARPINTERO LUIS 
insert into ciclo(curso,alumno) values(1,'041296490-8');
insert into quimestre(ciclo,descripcion,quimestral) values (3,'PRIMER QUIMESTRE',8);

insert into parcial(quimestre, descripcion, tarea, individual, grupal, nota_parcial) 
	values(5,'PRIMER PARCIAL',9,10,10,9),
			(5,'SEGUNDO PARCIAL',9,8,7,8),
			(5,'TERCER PARCIAL',9,10,8,8);
			
insert into quimestre(ciclo,descripcion,quimestral) values (3,'SEGUNDO QUIMESTRE',8);
insert into parcial(quimestre, descripcion, tarea, individual, grupal, nota_parcial) 
	values	(6,'PRIMER PARCIAL',9,7,7,8),
			(6,'SEGUNDO PARCIAL',7,7,8,8),
			(6,'TERCER PARCIAL',10,10,10,10);						
			
------------------------------------------------------------------------

--MATRICULA DE CHIRAU DEIFILIO

insert into ciclo(curso,alumno) values(2,'048973209-5');
insert into quimestre(ciclo,descripcion,quimestral) VALUES(4,'PRIMER QUIMESTRE',9);
insert into parcial(quimestre,descripcion,tarea,individual,grupal,nota_parcial)
	values(7,'PRIMER PARCIAL',9,9,9,9);
*/
