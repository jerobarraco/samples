CONNECT "C:\tp.fdb" user 'SYSDBA' password 'masterkey';

SET term ^ ;

--requerimiento del practico, llevar cuenta  de los instrumentos disponibles
create or alter trigger tr_revision for revisiones
active after insert or update position 0
as
begin
    update instrumentos
        set disponible = iif( new.fin is null, 0, 1)
        where new.id_instrumento = instrumentos.id;
end^

create or alter trigger tr_reparacion for reparaciones
active after insert or update position 0
as
begin
    update instrumentos
        set disponible = iif( new.fin is null, 0, 1)
        where new.id_instrumento = instrumentos.id;
end^

-- a) los triggers para los indices autoincrementales se encuentran en ddl.sql

-- b) Implementar un/os trigger/s que administre/n la regla de negocio “Es necesario llevar registro de los
--instrumentos para los cuales fue contratado cada músico en la orquesta. Nunca puede ser contratado para un
--instrumento que no sabe interpretar”.
--este verifica que no toque algo que no sabe
create exception EX_REGISTRO 'El musico no esta capacitado para este instrumento'^
create trigger TR_Registro_Negocio for registro
active
before insert or update  position 0
as begin
    if (not exists(
        Select
            m.id
        from
            musicos m
            join habilidades h on m.id = h.id_musico
            join tipos_instrumento ti on ti.id = h.id_tipo_inst
            join instrumentos i on i.id_tipo_inst = ti.id
        where
            m.id = new.id_musico
            and i.id = new.id_inst
        )
    ) then begin
       exception EX_REGISTRO;
    end
end^
--este verifica que no lo contraten para algo que no sabe
create trigger TR_Registro_Negocio_contrato for musicos
active
before update  position 0
as 
begin
	if(not exists( select * from musicos m 
			join habilidades h on (m.id = h.id_musico)
			where h.id_tipo_inst = new.id_t_i_contratado
	)) then begin
		exception EX_REGISTRO;
	end
end^

-- c) Implementar un/os trigger/s que administren la historia de los cambios de niveles de un músico en el tiempo.
-- se crea la tabla junto al trigger, ya que su existencia queda vinculada al trigger
create table REG_HABILIDADES(
    id_tipo_inst integer not null,
    id_musico integer not null,
    nivel integer not null,
    fecha timestamp not null,
    foreign key (id_tipo_inst) references instrumentos(id),
    foreign key (id_musico) references musicos(id)
) ^

create trigger TR_REG_HABILIDADES for habilidades
active before insert or update
as begin
	insert into reg_habilidades(id_tipo_inst, id_musico, nivel , fecha)
		values(new.id_tipo_inst, new.id_musico, new.nivel, current_timestamp);
end^

--d) Crear un esquema básico genérico que permita registrar la auditoria de una tabla del sistema en cuanto a alta,
--ultima modificación. De estos eventos se debe conocer el instante de tiempo en que se produjo el evento y el
--usuario de base de datos. Implemente un trigger “modelo” para una tabla. Ampliado en el punto siguiente.
create table auditoria (tiempo timestamp, usuario varchar(31), accion varchar(10), tabla varchar(20))^

create trigger tr_audit_musico for musicos 
active after insert or update
as
declare variable acciones varchar(10);
begin
    if(inserting)then acciones = 'insert';
    else acciones = 'update';
    insert into auditoria (tiempo, usuario, accion, tabla) values (current_timestamp, current_user, :acciones, 'MUSICOS');
end^

set term ; ^

insert into habilidades (id_tipo_inst, id_musico, nivel) values (1, 1, 3);
insert into habilidades (id_tipo_inst, id_musico, nivel) values (2, 2, 3);

insert into registro(id_musico, id_inst, fecha) values (1, 1, current_date);
insert into registro(id_musico, id_inst, fecha) values (2, 2, current_date);

insert into musicos (nombre, apellido, tipo_doc, numero_doc, domic, alta, id_pais)
    values('Oscar', 'Rabiola', 'DNI', '12444666', 'Strada L Oro', current_date, 1);
update musicos set id_t_i_contratado=1 where id = 1;
update musicos set id_t_i_contratado=2 where id = 2;
