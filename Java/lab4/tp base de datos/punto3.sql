CONNECT "C:\tp.fdb" user 'SYSDBA' password 'masterkey';

--Requerimiento del ejercicio, llevar cuenta de los titulos de los musicos argentinos
create or alter view v_Mus_arg 
(nombre, tipod, documento, titulo, fecha)
as select 
		m.nom_completo, m.tipo_doc, m.numero_doc, 
		t.nombre, t.fecha
		from musicos m
		join paises p on m.id_pais = p.id and p.nombre = 'ARGENTINA'
		join titulos t on t.id_musico = m.id;
 
 
-- a) Crear una vista que integre los músicos vigentes, los instrumentos que son contratados y en caso de ser
-- caprichosos el instrumento concreto que toca.
create or ALTER view v_musicos_inst
     (nombre, apellido, tipo, n_serie)
     as
     select m.nombre, m.apellido, t.tipo, ins.n_serie from musicos m
     join tipos_instrumento t on t.id = m.id_t_i_contratado
     LEFT join inst_favoritos i on i.id_musico = m.id
     LEFT join instrumentos ins on ins.id = m.id_t_i_contratado
     where m.baja is null;

-- b) Crear una vista que muestre los “musicos estrella”. En la orquesta llaman asi a los musicos que saben interpretar todos los instrumentos existentes en la orquesta.

create view v_estrellas ( id_musico, nombre, apellido, cant_instrumentos )  as
    select m.id, m.nombre, m.apellido, count (h.id) as cuenta from musicos m
        join habilidades h on (m.id = h.id_musico) 
    group by m.id, m.nombre, m.apellido
    having count(h.id)= (select count(1) from tipos_instrumento);

-- c) Generar una vista con la informacion de instrumentos revisados, que luthier lo tiene, y a que tipo pertenece.
CREATE OR ALTER VIEW V_LUTH_INST_REV(
    NOMBRE,
    DNI,
    TIPO_Revision,
    FECHA_inicio,
    COSTO,
    N_SERIE,
    TIPO_inst,
    COSTO_REV)
AS
    select l.nom_completo, l.dni, r.tipo, r.inicio, r.costo, ins.n_serie,
    t.tipo, t.costo_rev
    from luthieres l
        join revisiones r on r.id_luthier = l.id
        join instrumentos ins on ins.id = r.id_instrumento
        join tipos_instrumento t on t.id = ins.id_tipo_inst
        where r.fin is null;


--d) Generar una vista que contenga, una fila por cada tabla del sistema y 2 columnas, una con el nombre de tabla y 
--otra con la cantidad de filas. Nota: Vista estática, cuando se agrega una nueva tabla se debe agregar 
--explícitamente a la vista.

CREATE VIEW V_TABLAS
(NOMBRE, CUENTA) AS
select 'AUTORES' as nombre, count(1) as cuenta from autores
union
select 'GENEROS' as nombre, count(1) as cuenta from generos
union
select 'HABILIDADES' as nombre, count(1) as cuenta from HABILIDADES
union
select 'INSTRUMENTOS' as nombre, count(1) as cuenta from INSTRUMENTOS
union
select 'INST_FAVORITOS' as nombre, count(1) as cuenta from INST_FAVORITOS
union
select 'INST_NECESARIOS' as nombre, count(1) as cuenta from INST_NECESARIOS
union
select 'LICENCIAS' as nombre, count(1) as cuenta from LICENCIAS
union
select 'LUTHIERES' as nombre, count(1) as cuenta from LUTHIERES
union
select 'MUSICOS' as nombre, count(1) as cuenta from MUSICOS
union
select 'OBRAS' as nombre, count(1) as cuenta from OBRAS
union
select 'PAISES' as nombre, count(1) as cuenta from PAISES
union
select 'REGISTRO' as nombre, count(1) as cuenta from REGISTRO
union
select 'REPARACIONES' as nombre, count(1) as cuenta from REPARACIONES
union
select 'REVISIONES' as nombre, count(1) as cuenta from REVISIONES
union
select 'TELEFONOS' as nombre, count(1) as cuenta from TELEFONOS
union
select 'TIPOS_INSTRUMENTO' as nombre, count(1) as cuenta from TIPOS_INSTRUMENTO
union
select 'TITULOS' as nombre, count(1) as cuenta from TITULOS;