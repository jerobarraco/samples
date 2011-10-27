--1)
----tabla tipo
create table TIPOS (
    tipo integer primary key,
    puertas integer,
    motor varchar(100),
    combustible varchar(100)
);

---- porque si
create domain bool 
	as smallint not null
	-- default 0 -- no lo toma D:!
	check (value in (0, 1));

---- tabla auto
create table AUTOS(
	numero integer primary key,
	modelo varchar(100),
	tipo integer,
	Foreign key (tipo) references TIPOS(tipo),
	marca varchar(100),
	disponible bool
);
--jugando
--alter table tipos alter column purtas to puertas;
--alter table tipos alter column puertas type numeric(18,2);
--alter table tipos drop puertas ;
--alter table tipos add puertas integer;


-- cliente
create table CLIENTES(
	numero integer primary key,
	nombre varchar(100),
	direccion varchar(100),
	telefono varchar(100)
);

--sucursal
create table SUCURSALES(
	numero integer primary key,
	localidad varchar(100),
	provincia varchar(100),
	pais	varchar(100)
);

create table ALQUILA(
	n_cli integer,
	n_auto integer,
	n_sucursal integer not null,
	desde date not null,
	hasta date not null,
	primary key (n_cli, n_auto, desde),
	foreign key (n_auto) REFERENCES autos(numero),
	foreign key (n_cli) REFERENCES clientes(numero)
);
-- alter table alquila add column n_sucursal integer not null; -- no esta en el diagrama pero se pide mas abajo
--alter table alquila add foreign key (n_auto) references AUTOS(numero);
--alter table alquila add foreign key (n_cli) references CLIENTES(numero);

create table DEVUELVE(
	n_auto integer,
	n_sucursal integer,
	fecha date,
	estado varchar(100),
	primary key(n_auto, n_sucursal, fecha),
	foreign key (n_auto) references autos(numero),
	foreign key (n_sucursal) references sucursales(numero)
);
--generator para las pk (lo pongo por las dudas)
create generator gen_tipo;
create generator gen_auto;
create generator gen_cliente;
create generator gen_sucursal;

set generator gen_tipo to 0;