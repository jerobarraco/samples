CONNECT "C:\tp.fdb" user 'SYSDBA' password 'masterkey';

--a)Las partituras que no podrian ser interpretadas ya sea porque no se cuenta con  los musicos con la experiencia 
--requerida o porque no hay suficientes instrumentos para interpretarla. En este caso seria adecuado que ademas  
--se indique qur es lo que falta para poder interpretar la mencionada partitura. Para poder interpretar una 
--partitura el nivel en que un musico interpreta un instrumento debe coincidir exactamente con el requerido para ese 
--instrumento.	
create or alter view v_obras_imposibles (id, nombre, tipo, nivel, necesarios, disponibles, problema) as
	select o.id, o.nombre, t.tipo, n.nivel, n.cant, count(i.n_serie) as disponibles,
		'Se necesitan mas instrumentos' as msg
		from obras o
		join inst_necesarios n on o.id = n.id_obra
		join tipos_instrumento t on n.id_tipo_inst = t.id
		left join instrumentos i on i.id_tipo_inst = t.id
		group by o.id, o.nombre, t.tipo, n.cant, n.nivel, msg
		having count(i.id) < n.cant
	union
	select o.id, o.nombre, t.tipo, n.nivel, n.cant, count(h.nivel) as disponibles,
		'Se necesitan mas musicos' as msg
		from obras o
		join inst_necesarios n on o.id = n.id_obra
		join tipos_instrumento t on n.id_tipo_inst = t.id
		left join habilidades h on h.id_tipo_inst = t.id and h.nivel >= n.nivel
		group by o.id, o.nombre, t.tipo, n.cant, n.nivel, msg
		having count(h.nivel) < n.cant ;

--b)La cantidad de dias de licencia de cada tipo que solicitaron los músicos durante el último año.
--El siguiente dml es generico.
create or alter view v_tot_licencias (dias, causa) as
	select sum(l.fin - l.inicio) as dias, l.causa
		from licencias l
		where  extract (year from l.fin) = extract (year from current_date)
		group by causa;
	
--- Este es para el caso de querer ver las licencias de cada musico por separado (no esta explicito en la consigna)
create or alter view v_licencias (nombre, dias, causa) as
	select m.nom_completo, sum(l.fin - l.inicio) as dias, l.causa
		from licencias l join musicos m on l.id_musico = m.id
		where  extract (year from l.fin) = extract (year from current_date)
		group by  m.nom_completo, l.causa;

	
--c)Un registro auditable de las operaciones de Alta y Modificaciones de las reparaciones.

CREATE TABLE REG_AUDITABLE (
    ID_LUTHIER         INTEGER NOT NULL,
    NOM_COMPLETO_LUTH  VARCHAR(105) NOT NULL,
    ID_REVISION        INTEGER NOT NULL,
    TIPO_REV           DTREV NOT NULL, /* DTREV = VARCHAR(10) check (value in ('PERIODICA', 'PEDIDA')) */
    INICIO             DATE NOT NULL,
    FIN_REV            DATE,
    COSTO_REV          DECIMAL(18,4),
    ID_INSTRUMENTO     INTEGER NOT NULL,
    N_SERIE            VARCHAR(20) NOT NULL,
    ID_TIPO_INST       INTEGER NOT NULL,
    TIPO_INST          VARCHAR(20) NOT NULL,
    ID_REPARACION      INTEGER,
    FECHA              DATE,
    GARANTIA           DATE,
    FIN_REP            DATE,
    COSTO_REP          DECIMAL(18,4),
    ULTIMO_QUE_TOCO    VARCHAR(105),
    DNI_LUTH           INTEGER,
    FECHA_ULTIMO       DATE,
	FECHA_MODIFICACION TIMESTAMP
);

SET TERM ^ ;

CREATE OR ALTER TRIGGER TR_AUD_REP FOR REPARACIONES
	ACTIVE AFTER INSERT OR UPDATE POSITION 0
	AS
	DECLARE VARIABLE v_id_luth INTEGER;
	DECLARE VARIABLE v_dni_luth INTEGER;
	DECLARE VARIABLE v_nom_compl_luth VARCHAR(105);
	DECLARE VARIABLE v_id_rev INTEGER;
	DECLARE VARIABLE v_tip VARCHAR(10);
	DECLARE VARIABLE v_costo_reparacion DECIMAL(18,4);
	DECLARE VARIABLE v_costo_rev DECIMAL(18,4);
	DECLARE VARIABLE v_id_inst INTEGER;
	DECLARE VARIABLE v_ini_rev DATE;
	DECLARE VARIABLE v_fin_rev DATE;
	DECLARE VARIABLE v_n_serie_inst VARCHAR(20);
	DECLARE VARIABLE v_id_tipo_inst INTEGER;
	DECLARE VARIABLE v_tipo_inst VARCHAR(20);
	DECLARE VARIABLE v_id_rep INTEGER;
	DECLARE VARIABLE v_fecha_rep DATE;
	DECLARE VARIABLE v_garan_rep DATE;
	DECLARE VARIABLE v_fin_rep DATE;
	DECLARE VARIABLE v_ultimo_musico VARCHAR(105);
	declare variable v_fecha_ult date;
	BEGIN
		--luthier
		v_id_luth = NEW.id_luthier;
		select first 1 dni, nom_completo from luthieres 
			where id = :v_id_luth 
		into :v_dni_luth, :v_nom_compl_luth; 


		--revision
		v_id_rev = NEW.id_revision;
		select first 1 tipo, inicio, costo, fin from revisiones 
			where id = :v_id_rev
			into :v_tip, :v_ini_rev, :v_costo_rev, :v_fin_rev;
		
		--instrumento
		SELECT first 1 id_instrumento FROM revisiones
			WHERE id = :v_id_rev INTO :v_id_inst;
			
		SELECT first 1 n_serie FROM instrumentos
			WHERE id = :v_id_inst 
		into :v_n_serie_inst;
			
		SELECT first 1 t.tipo, t.id FROM tipos_instrumento t 
			JOIN instrumentos i ON (i.id_tipo_inst = t.id )
			where i.id = :v_id_inst	
		into :v_tipo_inst, :v_id_tipo_inst;

		--reparacion
		v_id_rep = NEW.id;
		v_fecha_rep = NEW.inicio;
		v_garan_rep = NEW.garantia;
		v_fin_rep = NEW.fin;
		v_costo_reparacion = NEW.costo;

		
		--musico que toco ultimo el instrumento
		SELECT first 1  m.nom_completo FROM musicos m
			JOIN registro re ON re.id_musico = m.id
			AND re.id_inst = :v_id_inst ORDER BY fecha DESC
			into :v_ultimo_musico;
							
		SELECT first 1  re.fecha FROM musicos m
			JOIN registro re ON re.id_musico = m.id
			AND re.id_inst = :v_id_inst ORDER BY fecha DESC 
			into :v_fecha_ult;

		insert into reg_auditable (
				id_luthier,
				dni_luth,
				nom_completo_luth,
				id_revision,
				tipo_rev,
				inicio,
				fin_rev,
				costo_rev,
				id_instrumento,
				n_serie,
				id_tipo_inst,
				tipo_inst,
				id_reparacion,
				fecha,
				garantia,
				fin_rep,
				costo_rep,
				ultimo_que_toco,
				fecha_ultimo
				)
		  values(
				:v_id_luth,
				:v_dni_luth,
				:v_nom_compl_luth,
				:v_id_rev,
				:v_tip,
				:v_ini_rev,
				:v_fin_rev,
				:v_costo_rev,
				:v_id_inst,
				:v_n_serie_inst,
				:v_id_tipo_inst,
				:v_tipo_inst,
				:v_id_rep,
				:v_fecha_rep,
				:v_garan_rep,
				:v_fin_rep,
				:v_costo_reparacion,
				:v_ultimo_musico,
				:v_fecha_ult
		);
	END	^
SET TERM ; ^

