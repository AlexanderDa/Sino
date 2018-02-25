create view nomina_curso as 
	select c.docente, c.periodo, a.cedula, a.apellido, a.nombre from ciclo ci
		inner join alumno a on a.cedula = ci.alumno
		inner join curso c on c.id = ci.curso;
		
COMMENT ON VIEW public.nomina_curso IS 'Se obtiene la nomina de alumnos por curso';		

create view materias_curso as 
	select c.docente, c.periodo,m.id as id_materia, m.nombre, m.dominio from curso c
		inner join materia m on m.id=c.materia;
		
COMMENT ON VIEW public.materias_curso IS 'Se obtiene las materias de un curso.';			
