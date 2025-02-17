﻿@ECHO OFF
rem Tomado de acá https://gist.github.com/adamcaudill/2322221
rem colocá esto en algún lugar, y podés configurar windows para que lo ejecute solo http://windows.microsoft.com/en-au/windows/schedule-task#1TC=windows-7
rem si queres que no se frene y se cierre cuando termine quitale la ultima linea que dice PAUSE
rem en caso de necesitar modificar algo, estas 3 cosas siguientes son las mas importantes
SET CARPETA=D:\mis backups\db\
set ORIGEN=D:\documentos\Clases\*
SET AppExePath=c:\Archivos de Programa\7-Zip\7z.exe

set year=%date:~-4,4%
set month=%date:~-10,2%
set day=%date:~-7,2%
set hour=%time:~-11,2%
set hour=%hour: =0%
set min=%time:~-8,2%
rem otro formato de winblows
rem set year=%date:~6,4%
rem set month=%date:~0,2%
rem set day=%date:~3,2%
rem set hour=%time:~0,2%
rem set hour=%hour: =0%
rem set min=%time:~3,2% 
rem set pf=%time:~-4,3%

set ARCHIVO=%year%-%month%-%day%-%hour%%min%.zip
SET DESTINO="%CARPETA%%ARCHIVO%"
SET ORIGEN="%ORIGEN%"
SET AppExePath="%AppExePath%"

if not exist %AppExePath% set AppExePath="%ProgramFiles(x86)%\7-Zip\7z.exe"
if not exist %AppExePath% set AppExePath="%ProgramFiles%\7-Zip\7z.exe"
if not exist %AppExePath% goto notInstalled


>>registro.log (
	echo -------------------NUEVO BACKUP-------------------------------
	echo ------ Fecha %date% -- Hora %time%  -------------
	echo Haciendo backup de %ORIGEN% a %DESTINO%
	echo origen %ORIGEN%
	echo destino %DESTINO%
	%AppExePath% a -r -tzip -mmt %DESTINO% %ORIGEN%
	echo Backup de %ORIGEN% a %DESTINO% ha finalizado!
)
 
goto end
 
:notInstalled
 
echo No se encontró 7-Zip, instalelo desde:
echo  http://7-zip.org/
 
:end
PAUSE