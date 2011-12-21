CONNECT "C:\tp.fdb" user 'SYSDBA' password 'masterkey';

-- Punto 2 a)
--- indices
create index ix_musicos on musicos (baja, nombre, apellido);

--b
--- No es necesario crear indices para las columnas con constraint PK o UNIQUE ya la base de datos crea indices implicitos en estos casos