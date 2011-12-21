CONNECT "C:\tp.fdb" user 'SYSDBA' password 'masterkey';

CREATE OR ALTER VIEW V_REVISIONES_FULL(
    TIPO_REV,
    INICIO,
	anio,
	mes,
    FIN,
	DURACION,
    COSTO_REV,
    ID_LUTHIER_REV,
    ID_INST,
    N_SERIE,
    DISPONIBLE,
    ID_TIPO_INST,
    TIPO_INST,
    DIAS_REV_PERIODICA,
    COSTO_REV_PERIODICA,
    ID_DEFAULT_LUTHIER,
    NOM_LUTHIER,
    DNI_LUTHIER)
AS
select
    r.tipo, r.inicio, extract(year from r.inicio), extract(month from r.inicio), r.fin, 
	r.fin-r.inicio, -- a proposito, dara null si fin es null por ende se pueden contar cuantas se terminaron y cuantas no o sumar las que si terminaron
	r.costo, r.id_luthier, r.id_instrumento,
    i.n_serie, i.disponible, i.id_tipo_inst,
    ti.tipo, ti.dias_revision, ti.costo_rev, ti.id_luthier, 
    l.nom_completo, l.dni

    from revisiones r
    join instrumentos i on (i.id = r.id_instrumento)
    join tipos_instrumento ti on (ti.id = i.id_tipo_inst)
    join luthieres l on (l.id = r.id_luthier)
    ;
	-- todo agregar año y mes por separado (extract)