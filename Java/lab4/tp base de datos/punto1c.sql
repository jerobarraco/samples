CONNECT "C:\tp.fdb" user 'SYSDBA' password 'masterkey';

--- inserts
insert into paises(nombre) values('ARGENTINA');
insert into paises(nombre) values('CHILE');

insert into generos (nombre) values ('clasico');
insert into autores (nombre) values('mozart');
insert into obras(nombre, duracion, id_autor, id_genero) 
	values ('Requiem', 75, 1 , 1);
insert into luthieres(nombre,apellido, dni) values ('jorge', 'ruiz', 312458);

insert into Tipos_instrumento (id, tipo, dias_revision, costo_rev, id_luthier) values (1 , 'flauta', 120, 30.5, 1);
insert into Tipos_instrumento (id, tipo, dias_revision, costo_rev, id_luthier) values (2 , 'piano', 300, 70, 1);

insert into INST_NECESARIOS(nivel, id_obra, id_tipo_inst, cant) 
	values (3, 1, 1, 1);
	
insert into INST_NECESARIOS(nivel, id_obra, id_tipo_inst, cant) 
	values (2, 1, 2, 1);
	
insert into INSTRUMENTOS(n_serie, disponible, id_tipo_inst) 
	values ('ASDLF123345', 1, 1);
	
insert into INSTRUMENTOS(n_serie, disponible, id_tipo_inst) 
	values ('9182323', 1, 2);

insert into INSTRUMENTOS(n_serie, disponible, id_tipo_inst) 
	values ('2833 14 K', 1, 2);
	
insert into INSTRUMENTOS(n_serie, disponible, id_tipo_inst) 
	values ('629824499', 1, 1);
	
insert into INSTRUMENTOS(n_serie, disponible, id_tipo_inst) 
	values ('YFL 381 HII', 1, 2);
	
insert into musicos(nombre, apellido, tipo_doc, numero_doc, domic, alta, id_pais) 
	values('Juan', 'Perez', 'DNI', '24114324', 'Irigoyen 399', '12/11/2011', 1);
insert into habilidades(id_tipo_inst, id_musico, nivel) values (1, 1, 5);
update musicos set id_t_i_contratado = 1 where id = 1;

insert into inst_favoritos(id_musico, id_inst) values(1, 1);

insert into telefonos (id_musico, numero, tipo) values (1, '12345', 'TRABAJO');
insert into titulos(nombre, fecha, id_musico) values ('Flautista', '10/15/2004', 1);
insert into licencias(id_musico, inicio, fin, causa) values (1, '01/25/2011','02/08/2011', 'VACACIONES');

insert into registro(id_musico, id_inst, fecha) values(1,1,'05/26/2011');

insert into REVISIONES(tipo, inicio, fin, costo, id_instrumento, id_luthier) 
	values ( 'PEDIDA', CURRENT_DATE, CURRENT_DATE +1 , 12.20, 1, 1);
	
insert into REPARACIONES(inicio, garantia, detalle, id_instrumento, id_luthier, id_revision) 
	values ( CURRENT_DATE, CURRENT_DATE+60, 'se rompio un boton', 1, 1, 1);

insert into musicos (nombre, apellido, tipo_doc, numero_doc, domic, alta, id_pais)
	values('Pedro', 'Quiroga', 'DNI', '23446557', 'loma altamira', current_date, 2);

insert into titulos(nombre, fecha, id_musico) values ('Pianista', '10/15/2004', 2);	
insert into habilidades (id_tipo_inst, id_musico, nivel) values(2, 2, 6);
update musicos set id_t_i_contratado = 2 where id = 2;