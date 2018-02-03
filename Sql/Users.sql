
CREATE USER profesor WITH PASSWORD 'prop4$';
GRANT all ON table alumno to profesor;


GRANT all(quimestre,descripcion, tarea, individual,grupal,nota_parcial) ON table parcial to profesor;
GRANT all(ciclo, quimestral, nota_cualitativa, laborados, justificados, injustificados) ON table quimestre to profesor;
GRANT all(curso,alumno) ON table ciclo to profesor;
GRANT all ON table curso to profesor;
GRANT all ON table materia to profesor;
GRANT all ON table docente to profesor;
GRANT all ON table alumno to profesor;
GRANT all ON table periodo to profesor;






CREATE USER admin WITH PASSWORD 'adm!np4$';
GRANT all ON table alumno to admin;


GRANT all(quimestre,descripcion, tarea, individual,grupal,nota_parcial) ON table parcial to admin;
GRANT all(ciclo, quimestral, nota_cualitativa, laborados, justificados, injustificados) ON table quimestre to admin;
GRANT select ON table ciclo to admin;
GRANT select ON table curso to admin;
GRANT select ON table materia to admin;
GRANT all ON table docente to admin;
GRANT select ON table alumno to admin;
GRANT all ON table periodo to admin;


/*
GRANT UPDATE (nombre,apellido)
     ON TABLE personas
     TO usuario;
*/
