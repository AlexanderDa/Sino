create view v_ciclo as select 
	c.grado, c.paralelo, a.apellido || ' ' || a.nombre as alumno, m.nombre as materia, ci.promedio from ciclo ci
	inner join curso c on c.id = ci.curso 
	inner join alumno a on a.cedula = c.alumno
	inner join materia m on m.id = c.materia;

