CONNECT "C:\tp.fdb" user 'SYSDBA' password 'masterkey';

-- triggers
SET term ^ ;

create trigger TR_ID_GENEROS for GENEROS ACTIVE before insert
as
begin
	if (new.id IS NULL) then new.id = gen_id(id_generos, 1);	
end ^


create trigger TR_ID_REPARACIONES for REPARACIONES active before insert
as
begin
    if (new.id is null) then new.id = gen_id(id_reparaciones, 1);
end^

create trigger TR_ID_OBRAS for OBRAS active before insert
as
begin
	if (new.id is null) then new.id = gen_id(id_obras, 1);
end ^

create trigger TR_ID_LUTHIERES for LUTHIERES ACTIVE before insert
as
begin
	if (new.id IS NULL) then new.id = gen_id(id_luthieres, 1);	
end^


create trigger TR_id_paises for paises ACTIVE before insert
as
begin
	if (new.id IS NULL) then new.id = gen_id(id_paises, 1);	
end^

create trigger tr_id_musicos for musicos active before insert position 0
 as
 begin
    if(new.id is null) then new.id = gen_id(id_musicos, 1);
 end^
 
create trigger TR_ID_TELEFONOS for TELEFONOS ACTIVE before insert
as
begin
	if (new.id is null) then new.id = gen_id(id_telefonos, 1);
end^

create trigger tr_id_titulos for titulos active before insert position 0
as
begin
   if(new.id is null) then new.id = gen_id(id_titulos, 1);
end^

create trigger tr_id_licencias for licencias active before insert position 0
as
begin
    if(new.id is null)then new.id = gen_id(id_licencias, 1);
end^

create trigger TR_ID_TIPOS_INSTRUMENTO for TIPOS_INSTRUMENTO active before insert
as 
begin
	if (new.id is null) then new.id = gen_id(id_tipos_instrumento, 1);
end^

create trigger tr_id_habilidades for habilidades active before insert position 0
as
begin
    if(new.id is null) then new.id=gen_id(id_habilidades,1);
end^

create trigger TR_ID_INST_NECESARIOS for INST_NECESARIOS active before insert
as
begin
    if (new.id is null) then new.id = gen_id(id_inst_necesarios, 1);
end^

create trigger TR_ID_INSTRUMENTOS for INSTRUMENTOS active before insert
as
begin
    if (new.id is null) then new.id = gen_id(id_instrumentos, 1);
end^

create trigger tr_id_inst_fav for inst_favoritos active before insert position 0
as
begin
    if(new.id is null) then new.id = gen_id(id_inst_favoritos,1);
end^

create trigger tr_id_registro for registro active before insert position 0
as
begin
    if(new.id is null) then new.id = gen_id(id_registro, 1);
end^

create trigger tr_id_AUTORES for AUTORES ACTIVE before insert
as
begin
	if (new.id IS NULL) then new.id = gen_id(ID_AUTORES, 1);	
end^

create trigger TR_ID_REVISIONES for REVISIONES active before insert
as
begin
    if (new.id is null) then new.id = gen_id(id_revisiones, 1);
end^

set term ; ^