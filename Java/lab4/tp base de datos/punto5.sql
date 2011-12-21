CONNECT "C:\tp.fdb" user 'SYSDBA' password 'masterkey';

SET term ^ ;

--A
create or alter procedure P_AUTOINCDDL(tabla varchar(16)) 
	returns (ddl varchar (255))
as
begin
	ddl = 'create generator id_' || tabla || '^  
		set generator id_' || tabla || ' to 0^ 
		create trigger TR_ID_' || tabla || ' for ' || tabla || ' 
		active before insert as begin
		if (new.id is null) then new.id = gen_id(id_' || tabla || ', 1);
		end^';
	suspend;
end^

--B
create or alter procedure P_AUDIT(tabla varchar(16)) 
    returns (ddl varchar (500))
as
begin
    ddl = 'create trigger tr_audit_' || tabla || ' for ' || tabla || ' 
    after insert or update as
declare acciones varchar(10);
begin
    if(inserting)then acciones=''insert'';
    else acciones=''update'';
    insert into auditoria(tiempo,usuario,accion,tabla) values (current_timestamp, current_user, :acciones, ''' || tabla || ''');
end';
    suspend;
end^

--- Devuelve todos los posibles musicos con instrumentos segun la necesidad de una obra, siempre que el instrumento este disponible
-- y no sea esa una de las ultimas 2 combinaciones
--C
CREATE GLOBAL TEMPORARY TABLE temp_elegidos
   (
   idm integer,
   idi integer)
   oN COMMIT DELETE ROWS ^
   
create or alter procedure rotacion(pid_obra integer) returns (vti integer, vniv integer, vmid integer, viid integer)
as
declare vaux integer;
declare vcant_nec integer;
declare vcant_ins integeR;
begin
    for select id_tipo_inst, nivel, cant --itera nivel/tipo
        from inst_necesarios where id_obra = :pid_obra
        into :vti, :vniv, :vcant_nec
    do begin
		--reiniciamos la basura necesaria para los ifs
		vcant_ins = 0;
        for select m.id, rand() pos --iteramos los musicos para cada nivel/tipo, el pos es para randomizar
            from
                musicos m
                join habilidades h on (m.id= h.id_musico) -- podriamos busar solo los contratados, pero eso reduce mucho las posibilidades
            where 
				m.baja is null and -- musicos que quieran trabajar
                h.nivel >= :vniv and
                h.id_tipo_inst = :vti and
                m.id not in (select idm from temp_elegidos) -- no lo hayamos elegido anteriormente
            order by pos 
            into :vmid, :vaux
        do begin 
			viid = null; 
            select first 1 i.id, rand() pos --nos fijamos si tiene un inst favorito para este tipo
            from 
                instrumentos i 
                join inst_favoritos inf on (
                        inf.id_musico = :vmid and
                        inf.id_inst = i.id
                    )
            where 
                i.id_tipo_inst = :vti and
                i.disponible = 1 and
                i.id not in (select idi from temp_elegidos) -- no lo este usando otro
			order by pos
            into :viid, :vaux;
            
            if (viid is null) then begin --si no tiene un favorito para este tipo...
				--necesito hacerlo en dos partes porque sino el select se hace muy largo y no tengo tipo_inst en la tabla instfavoritos
                select first 1
                    i.id, rand() pos
                from 
                    instrumentos i 
                where 
                    i.id_tipo_inst = :vti and
                    i.disponible = 1 and
                    i.id not in (select idi from temp_elegidos ) and
                    i.id not in ( --diferente a las ultimas 2 interpretaciones , notar que no lo hacemos con inst favorito porque no tiene sentido ya que todas las interpretaciones son iguales
                        select first 2 id_inst 
                        from registro
                        where 
                            id_musico = :vmid
                        order by fecha DESC
                    )
				order by pos
                into :viid, :vaux;
            end --fin sin favorito
            if (viid is not null) then begin --nunca fue tan complicado ponerla (sujeto tacito "informacion") (aunque se que son solo datos no info)
                insert into temp_elegidos (idm, idi) values (:vmid, :viid); --yay.....
                --deberia iterar la tabla elegidos al final... pero cometiendo esta atrocidad, puedo beneficiarme en cuanto al tiempo de retorno del sp y que quizas no se usen todos los datos retornados por este sp...
				vcant_ins = vcant_ins+1;
                suspend;
				if (vcant_ins >= vcant_nec) then break;
            end
        end    --fin musico/inst/nivel
		--if (vcant_ins < vcant_nec ) then begin -- no lanzo excepcion, para eos esta la otra funcion
		while (vcant_ins < vcant_nec) do begin -- solo para senialar que faltan musicos
			vmid = null; 
			viid = null;
			vcant_ins = vcant_ins +1;
			suspend;
		end
        --aca podria hacer un select count (1) from @elegidos where tipo  > cant y ver si tenemos tantos musicos como necesitamos
    end -- fin nivel/tipo        
    delete from temp_elegidos; -- por las dudas si hacen otro select en esta transaccion
end^

--d) Producir un sp con el nombre  init_tabla  que ejecute las DML necesarias para borrar las tablas en el orden 
--necesario para que no se produzcan excepciones de foreign key. Nota no debe borrar las tablas de tipo, ejemplo  
--tipo o grupos de instrumentos (en principio, vientos, cuerdas o percusión).
create or alter procedure init_tabla (a integer) as begin
	delete from luthieres;
	delete from musicos;
	delete from obras;
	delete from instrumentos;
	--el resto de las tablas no se borran por considerarse genericas para todos.
	--las demas tablas se borran por el constraint del foreign key. 
	--en tipos_intrumento tiene un "on delete" especial para evitar que se borre el dato.
end^


--e) Producir un sp que genere 5 insert como mínimo a fin de generar un ejemplar de la base para pruebas, que tenga 
--consistencia.
create or alter procedure generar_info_default as begin
		if(not exists(select nombre from paises where nombre = 'p')) then
			insert into paises (nombre) values ('p');
			--La consigna asume que la DB esta vacia, pero el if esta puesto como ejemplo de si no lo estuviera.
        insert into musicos (nombre, apellido, tipo_doc, numero_doc, domic, alta, id_pais)
			values ('p', 'p', 'DNI', 'p', 'p', current_date, (select id from paises where nombre = 'ARGENTINA'));
        insert into habilidades (id_tipo_inst, id_musico, nivel) 
			values (1, (select first 1 id from musicos where nombre = 'p'), 1);
		
		update musicos set id_t_i_contratado = 1 where id = (select first 1 id from musicos where nombre = 'p');
		
        insert into telefonos (id_musico, numero, tipo)
			values ((select id from musicos where nombre = 'p'), 'p', 'CASA');
        insert into instrumentos (n_serie, disponible, id_tipo_inst)
			values('p', 1, 1);
        insert into registro (id_musico, id_inst, fecha)
			values ((select id from musicos where nombre = 'p'), (select id from instrumentos where n_serie = 'p'), current_date);
		insert into luthieres (nombre, dni) values('p', 0);
			update tipos_instrumento set id_luthier = (select id from luthieres where nombre = 'p') where id = 0;
end^
SET term ; ^


