@echo off

set isql="C:\Archivos de programa\Firebird\Firebird_2_5\bin\isql.exe"
set DATABASE="C:\tp.fdb"
set usuario=SYSDBA
set pwd=masterkey

echo isql es %isql%
echo database es %DATABASE%

del %database%
echo Haciendo macana
echo Creando tablas ...
rem pongo las opciones largas para que se entienda
%isql% -user %usuario% -password %pwd% -q -i "punto1.sql"

echo Creando triggers ...
%isql% -user %usuario% -password %pwd% -q -i "punto1b.sql"

echo Insertando datos ...
%isql% -user %usuario% -password %pwd% -q -i "punto1c.sql"

echo Creando un indice ...
%isql% -user %usuario% -password %pwd% -q -i "punto2.sql"

echo Creando vistas ...
%isql% -user %usuario% -password %pwd% -q -i "punto3.sql"

echo Mas triggers ...
%isql% -user %usuario% -password %pwd% -q -i "punto4.sql"

echo Stored Procedures ... 
%isql% -user %usuario% -password %pwd% -q -i "punto5.sql"

echo Mas vistas ...
%isql% -user %usuario% -password %pwd% -q -i "punto6.sql"

echo Ultima vista ...
%isql% -user %usuario% -password %pwd% -q -i "punto7.sql"

echo Datos extra ...
%isql% -user %usuario% -password %pwd% -q -i "extra.sql"

echo Listo el pollo, se feliz :D