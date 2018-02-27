create view nomina_curso as 
	select c.docente, c.periodo, a.cedula, a.apellido, a.nombre from ciclo ci
		inner join alumno a on a.cedula = ci.alumno
		inner join curso c on c.id = ci.curso;
		
COMMENT ON VIEW public.nomina_curso IS 'Se obtiene la nomina de alumnos por curso';		

create view materias_curso as 
	select ac.curso, ac.materia as id_materia from asignatura_curso ac inner join curso c on c.id=ac.curso where c.docente= ? and c.periodo = ?;
		
COMMENT ON VIEW public.materias_curso IS 'Se obtiene las materias de un curso.';			


--Selecionar el ciclo de un curso
 select ci.id, ci.asignatura_curso, ci.alumno, ci.promedio  from ciclo ci 
 	inner join asignatura_curso ac on  ac.id= ci.asignatura_curso
 	where ac.curso=1;
 	order by id;

