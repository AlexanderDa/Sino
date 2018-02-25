
/******************************************************************
*                      CREAR EL USUARIO ADMIN                     *
******************************************************************/

CREATE USER admin WITH PASSWORD 'adm!np4$';

grant all on sequence administrador_id_seq to admin;
grant all on sequence ciclo_id_seq         to admin;
grant all on sequence curso_id_seq         to admin;
grant all on sequence materia_id_seq       to admin;
grant all on sequence parcial_id_seq       to admin;
grant all on sequence periodo_id_seq       to admin;
grant all on sequence quimestre_id_seq     to admin;

GRANT all ON table parcial 		to admin;
GRANT all ON table quimestre 	to admin;
GRANT all ON table ciclo 		to admin;           
GRANT all ON table alumno 		to admin ;	
GRANT all ON table curso 		to admin;
GRANT all ON table materia 		to admin;
GRANT all ON table docente 		to admin;
GRANT all ON table alumno 		to admin;
GRANT all ON table periodo 		to admin;
GRANT all ON table administrador to admin;

--GRANT select ON nomina_curso to admin;
--GRANT select ON materias_curso to admin;






/******************************************************************
*                    CREAR EL USUARIO PROFESOR                    *
******************************************************************/

CREATE USER profesor WITH PASSWORD 'prop4$';


grant all on sequence ciclo_id_seq         to profesor;
grant all on sequence curso_id_seq         to profesor;
grant all on sequence materia_id_seq       to profesor;
grant all on sequence parcial_id_seq       to profesor;
grant all on sequence periodo_id_seq       to profesor;
grant all on sequence quimestre_id_seq     to profesor;



GRANT all ON table parcial 		to profesor;
GRANT all ON table quimestre 	to profesor;
GRANT all ON table ciclo 		to profesor;
GRANT select ON table alumno 	to profesor;
GRANT select ON table curso 	to profesor;
GRANT select ON table materia 	to profesor;
GRANT select ON table docente 	to profesor;
GRANT select ON table alumno 	to profesor;

--GRANT select ON nomina_curso   	to profesor;
--GRANT select ON materias_curso 	to profesor;





