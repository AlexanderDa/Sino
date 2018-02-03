/********************************************************************
*				FUNCION PARA REDONDEAR A DOS CEDIMALES				*
********************************************************************/

CREATE OR REPLACE FUNCTION redondea(valor real) RETURNS REAL AS $BODY$
DECLARE
	entero integer;
    redondeado real;
begin
	entero = valor*100;
    redondeado = entero*0.01;
    return redondeado;
end;
$BODY$ language plpgsql;



/********************************************************************
*				     TRIGGER PARA CALCULAR PARCIALES				*
********************************************************************/
CREATE OR REPLACE FUNCTION fun_promedio_parcial() RETURNS TRIGGER AS $BODY$
DECLARE
	promedio real; 
begin	
    new.tarea=(select redondea(new.tarea));
    
    new.individual=(select redondea(new.individual));
    
    new.grupal=(select redondea(new.grupal));
    
    new.nota_parcial=(select redondea(new.nota_parcial));
    
    promedio = (new.tarea + new.individual+new.grupal)/3;
    new.promedio_evaluacion = (select redondea(promedio));
    
    promedio= (promedio +new.nota_parcial)/2;
    new.promedio =(select redondea(promedio));
    
    
    return new;
end;
$BODY$ LANGUAGE plpgsql;

create trigger promedio_parcial before insert or update
on parcial for each row execute procedure fun_promedio_parcial();


/********************************************************************
*    	CREAR TRIGGER PARA CALCULAR PROMEDIO DE LOS PARCIALES		*
********************************************************************/


CREATE OR REPLACE FUNCTION public.fun_promedio_parciales()  RETURNS trigger AS $BODY$

declare 
    parciales integer; 		--numero de parciales que pertenecen a un quimestre de un alumno
    suma_parciales real; 	--Suma el promedio de los parciales
begin
	--parciales = (select count(new.quimestre) from parcial where quimestre=new.quimestre);
	parciales = 3;
    suma_parciales =( select sum(promedio) from parcial where quimestre=new.quimestre);
    update quimestre set promedio_parcial = (suma_parciales/parciales) where id=new.quimestre;
    return null;
end;
$BODY$ language plpgsql;

create trigger promedio_parciales after insert or update
on parcial for each row execute procedure fun_promedio_parciales();



/********************************************************************
*				     TRIGGER PARA CALCULAR QUIMESTRES				*
********************************************************************/
CREATE OR REPLACE FUNCTION public.fun_promedio_quimestre() RETURNS trigger AS $BODY$
declare
    nota_porcentual real;
begin	
-- promedio_parcial | nota_parcial | quimestral | nota_quimestral | promedio | nota_cualitativa |

    new.promedio_parcial=(select redondea(new.promedio_parcial));
    
    nota_porcentual = new.promedio_parcial*0.8;
    new.nota_parcial=(select redondea(nota_porcentual));
	
    new.quimestral=(select redondea(new.quimestral));
    
    nota_porcentual = new.quimestral*0.2;
    new.nota_quimestral=(select redondea(nota_porcentual));
    
    new.promedio = new.nota_parcial+new.nota_quimestral;
    
    
    if new.promedio>=0 and new.promedio<5  then
		new.nota_cualitativa = 'PROXIMO';
	end if;
	
	if new.promedio>=5 and new.promedio<6.99  then
		new.nota_cualitativa = 'NO ALCANZA';
	end if;
	
	if new.promedio>=7 and new.promedio<8.99  then
		new.nota_cualitativa = 'ALCANZA';
	end if;
	
	if new.promedio>=8 and new.promedio<9.99  then
		new.nota_cualitativa = 'DOMINA';
	end if;
	
	if new.promedio=10  then
		new.nota_cualitativa = 'SUPERA';
	end if;
    
    return new;
end;

$BODY$ language plpgsql;

create trigger promedio_quimestre before insert or update
on quimestre for each row execute procedure fun_promedio_quimestre();





/********************************************************************
*    	CREAR TRIGGER PARA CALCULAR PROMEDIO DE LOS QUIMESTRES		*
********************************************************************/


CREATE OR REPLACE FUNCTION public.fun_promedio_quimestres()  RETURNS trigger AS $BODY$

declare 
    parciales real; 		--numero de parciales que pertenecen a un quimestre de un alumno
    suma_parciales real; 	--Suma el promedio de los parciales
begin
	--parciales = (select count(new.ciclo) from quimestre where id=new.quimestre);
	parciales=2;	
    suma_parciales =( select sum(promedio) from quimestre where ciclo=new.ciclo);
    update ciclo set promedio = (suma_parciales/parciales) where id=new.ciclo;
    return null;
end;
$BODY$ language plpgsql;

create trigger promedio_quimestres after insert or update
on quimestre for each row execute procedure fun_promedio_quimestres();

