-----------------------------------------------------------------
create table administrador(
	id serial not null,
	usuario character varying(30) unique not null,
	clave character varying(32) not null,
	nombre text not null,
	apellido text not null,
	constraint pk_administrador primary key(id)
);
-----------------------------------------------------------------


create table periodo(
	id serial not null,
	fecha_inicio date not null,
	fecha_fin date not null,
	director text not null,
	subdirector text not null,
	coordinador text not null,
	constraint pk_periodo primary key(id)
);


create table docente(
	cedula identification unique,
	usuario character varying(30) unique not null,
	clave character varying(32) not null,
	nombre text not null,
	apellido text not null,
	constraint pk_docente primary key(cedula)
);

create table curso(
	id serial not null,
	periodo integer not null,
	docente identification,	
	grado character varying(15),
	paralelo character,
	constraint pk_curso primary key(id),
	constraint fk_curso_docente foreign key(docente) references docente(cedula),
	constraint fk_curso_periodo foreign key(periodo) references periodo(id)
);

-----------------------------------------------------------------
create table alumno(
	cedula identification unique,
	apellido text not null,
	nombre text not null,
	constraint pk_alumno primary key(cedula)
);


create table materia(
	id serial not null,
	nombre character varying(30) not null,
	dominio character varying(50) not null,
	constraint pk_materia primary key(id)
);

create table asignatura_curso(
	id serial not null,
	curso integer not null,	
	materia integer not null,
	constraint pk_asignatura_curso primary key(id),
	constraint fk_asignaturas_curso_curso foreign key(curso) references curso(id),
	constraint fk_asignaturas_curso_materia foreign key(materia) references materia(id)
);

create table ciclo(
	id serial not null,
	asignatura_curso integer not null,
	alumno identification,
	promedio note,
	constraint pk_ciclo primary key(id),
	constraint fk_ciclo_asignatura_asignada foreign key(asignatura_curso) references asignatura_curso(id),
	constraint fk_ciclo_alumno foreign key(alumno) references alumno(cedula)
);
-----------------------------------------------------------------


create table quimestre(
	id serial not null,  
	ciclo integer not null,
	descripcion character varying(20) NOT NULL, 
	promedio_parcial note,
	nota_parcial note,
	quimestral note,
	nota_quimestral note,
	promedio note,
	nota_cualitativa character(10),
	laborados day,
	justificados day ,
	injustificados day ,
	atrasos day ,
	constraint pk_quimestre primary key(id) ,
	constraint fk_quimestre foreign key(ciclo) references ciclo(id)
);


create table parcial(
	id serial not null,
	quimestre integer not null,   
	descripcion character varying(15) NOT NULL,  
	tarea note,
	individual note,
	grupal note,
	promedio_evaluacion note,
	nota_parcial note,
	promedio note,
	constraint pk_parcial primary key(id),
	constraint fk_quimestre foreign key(quimestre) references quimestre(id)
);


