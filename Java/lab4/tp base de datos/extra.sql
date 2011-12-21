CONNECT "C:\tp.fdb" user 'SYSDBA' password 'masterkey';

--ojo que todo lo queponemos aca afecta a los registros por tantos triggers que pusimos, 
--pej, las revisiones que no tienen fin dejan a los instrumentos como no disponibles

INSERT INTO LUTHIERES (NOMBRE, DNI, APELLIDO) VALUES ('Ricardo', 2222, 'ruben');
INSERT INTO REVISIONES (TIPO, INICIO, COSTO, ID_INSTRUMENTO, ID_LUTHIER, FIN) VALUES ('PERIODICA', '12-DEC-2011', 333, 3, 2, NULL);
INSERT INTO REVISIONES (TIPO, INICIO, COSTO, ID_INSTRUMENTO, ID_LUTHIER, FIN) VALUES ('PERIODICA', '4-OCT-2011', 10, 5, 2, '24-NOV-2011');
INSERT INTO REVISIONES (ID, TIPO, INICIO, COSTO, ID_INSTRUMENTO, ID_LUTHIER, FIN) VALUES (6, 'PEDIDA', '29-OCT-2011', 55, 4, 1, '23-NOV-2011');
