CREATE DATABASE 'c:\tp.fdb' page_size 8192;
-- user 'sysdba' password 'masterkey';

create domain DTRev as varchar(10) check ( value in ('PERIODICA', 'PEDIDA') );
create domain DTTel as varchar(10) check ( value in ('CASA', 'TRABAJO', 'MOVIL', 'OTRO') );
create domain DTBool as smallint check (value in (0,1));
create domain DTCausa as varchar(15) check (value in ('VACACIONES', 'ENFERMEDAD', 'OTROS'));
CREATE DOMAIN DTDOC AS VARCHAR(30) CHECK (VALUE IN('LIBRETA', 'DNI', 'PASAPORTE','OTRO'));

--Tablas
create table GENEROS(
	id integer primary key,
	nombre varchar(50) unique not null
);
create generator id_generos;
set generator id_generos to 0;

CREATE TABLE AUTORES (
    ID      INTEGER primary key,
    NOMBRE  VARCHAR(50) NOT NULL
);
CREATE GENERATOR ID_AUTORES;
set generator id_autores to 0;

--- obra
create table OBRAS(
    id integer primary key,
    nombre varchar(20) not null unique,
    duracion integer not null,
    id_autor integer not null,
    id_genero integer not null
);
create generator id_obras;
set generator id_obras to 0;

---- LUTHIERES
create table LUTHIERES(
	id integer primary key,
	nombre varchar(50),	
	apellido varchar(50), 
	dni integer unique,
	nom_completo computed by(nombre || ', ' || apellido)
);
create generator id_luthieres;
set generator id_luthieres to 0;

-- paises
create table paises (
	id integer primary key,
	nombre varchar(50) not null
);
create generator id_paises;
set generator id_paises to 0;

--- musicos
create table musicos(
	id integer primary key,
	nombre varchar(50) not null,
	apellido varchar(50) not null,
	nom_completo computed by(nombre || ', ' || apellido),
	tipo_doc dtdoc not null,
	numero_doc varchar(20) not null,
	domic varchar(50) not null,
	alta date not null, 
	baja date,
	id_t_i_contratado integer, -- puede ser null para poder ingresar primero las habilidades y luego ser contratado, lo que dispara la comprobacion
	id_pais integer not null
);
create generator id_musicos;
set generator id_musicos to 0;

--- TELEFONO
create table TELEFONOS(
	id integer primary key,
	id_musico integer not null,
	numero varchar(50) unique not null,
	tipo DTTel not null
);
create generator id_telefonos;
set generator id_telefonos to 0;

--- TITULOS
create table titulos (
	id integer primary key,
	nombre varchar(40) not null,
	fecha date not null,
	id_musico integer not null
);
create generator id_titulos;
set generator id_titulos to 0;
	
----- Licencias
create table licencias (
	id integer primary key,
	inicio date not null,
	fin date,
	causa DTCausa not null, 
	id_musico integer not null
);
create generator id_licencias;
set generator id_licencias to 0;

----- tipoInstrumento
Create table TIPOS_INSTRUMENTO(
    id integer primary key,
    tipo varchar(20) not null unique,
    dias_revision integer not null,
    costo_rev decimal(18, 4) not null, --aka money
    id_luthier integer not null
);
create generator id_tipos_instrumento;
set generator id_tipos_instrumento to 0;

--- HABILIDADES
create table habilidades (
	id integer primary key,
	nivel smallint not null,
	id_tipo_inst integer not null,
	id_musico integer not null 
);
create generator id_habilidades;
set generator id_habilidades to 0;

---- Instrumentos necesarios
create table INST_NECESARIOS(
	id integer primary key,
	nivel smallint not null,
	id_obra integer not null,
	id_tipo_inst integer not null,
	cant integer not null
);
create generator id_inst_necesarios;
set generator id_inst_necesarios to 0;

--- Instrumento
create table INSTRUMENTOS(
	id integer primary key,
	n_serie varchar(30) not null unique,
	disponible DTBool not null,
	id_tipo_inst integer not null 
);
create generator id_instrumentos;
set generator id_instrumentos to 0;

---Instr
create table inst_favoritos(
	id integer primary key,
	id_musico integer not null,
	id_inst integer not null
);
create generator id_inst_favoritos;
set generator id_inst_favoritos to 0;

---REGISTRO
create table registro (
	id integer primary key, 
	id_musico integer not null, 
	id_inst integer not null,
	fecha date not null	
);
create generator id_registro;
set generator id_registro to 0;

---- revision
create table REVISIONES(
	id integer primary key,
	tipo DTREV not null,
	inicio DATE not null,
	fin DATE,
	costo DECIMAL(18,4) not null,
	id_instrumento integer not null,
	id_luthier integer not null
);
create generator id_revisiones;
set generator id_revisiones to 0;

---- reparacion
create table REPARACIONES(
	id integer primary key,
	inicio date not null,
	fin date,
	garantia date not null,
	costo DECIMAL(18,4), --el costo puede estar dado despues de la reparacion, por ende puede ser puesto al final. al terminar de reparar
	detalle varchar(50) not null,
	id_luthier integer not null,
	id_revision integer not null,
	id_instrumento integer not null
);
create generator id_reparaciones;
set generator id_reparaciones to 0;

--Foreign keys de todas las tablas.
--obras
alter table OBRAS add foreign key (id_autor) references AUTORES(id) on delete cascade on update cascade;
alter table OBRAS add foreign key (id_genero) references GENEROS(id) on delete cascade on update cascade;

--musicos
alter table musicos add foreign key (id_t_i_contratado) references tipos_instrumento(id) on delete set null on update cascade; 
-- si se llegara a borrar un tipo, no borramos el musico
alter table musicos add foreign key (id_pais) references paises(id) on delete set null on update cascade;	
--si llegaramos a borrar un pais no borramos al musico

--telefonos
alter table telefonos add foreign key (id_musico) references musicos(id) on delete cascade on update cascade;	
	
--titulos
alter table titulos add foreign key (id_musico) references musicos(id) on delete cascade on update cascade;

--licencias
alter table licencias add foreign key(id_musico) references musicos(id) on delete cascade on update cascade;

-- tipos instrumentos
alter table TIPOS_INSTRUMENTO add foreign key (id_luthier) references luthieres(id) on update cascade on delete set null;
--el set null es necesario, ya que podriamos borrar un luthier, pero no borraremos el tipo de instrumento, solo ponemos su luthier default a null

--habilidades 
alter table habilidades add foreign key(id_tipo_inst) references tipos_instrumento(id) on update cascade on delete cascade;
alter table habilidades add foreign key(id_musico) references musicos(id) on update cascade on delete cascade;

-- instrumentos necesarios
alter table inst_necesarios add foreign key (id_obra) references OBRAS(id) on delete cascade on update cascade;
alter table inst_necesarios add foreign key (id_tipo_inst) references TIPOS_INSTRUMENTO(id) on delete cascade on update cascade;

--instrumentos
alter table instrumentos add foreign key (id_tipo_inst) references TIPOS_INSTRUMENTO(id) on delete cascade on update cascade;

--instrumentos favoritos
alter table inst_favoritos add foreign key(id_musico) references musicos(id) on update cascade on delete cascade;
alter table inst_favoritos add foreign key(id_inst) references instrumentos(id) on update cascade on delete cascade;

--registro
alter table registro add foreign key(id_musico) references musicos(id) on update cascade on delete cascade;

--revisiones
alter table revisiones add foreign key (id_instrumento) references INSTRUMENTOS(id) on delete cascade on update cascade;
alter table revisiones add foreign key (id_luthier) references LUTHIERES(id) on delete cascade on update cascade;

--reparaciones
alter table reparaciones add foreign key (id_revision) references REVISIONES(id) on delete cascade on update cascade;
alter table reparaciones add foreign key (id_luthier) references LUTHIERES(id) on delete cascade on update cascade;
alter table reparaciones add foreign key (id_instrumento) references INSTRUMENTOS(id) on delete cascade on update cascade;	