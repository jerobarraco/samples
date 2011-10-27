from firebirdcon import run, connect
connect("localhost/3050:c:\\Svn\\pysnipps\\Java\\lab4\\Bd_guia1.FDB")

#1
run("select * from situaciones_iva")

#2
#run ("select * from situaciones_iva orderby DESCRIPCION desc")

#3
run ("select cuit, nombre, '........' as firma from clientes Order BY cuit")

#4
run ("SELECT * from facturas_items where factura_tipo=1 and factura_numero=12")

#5
"select count(1) from facturas_items where factura_tipo=1 and factura_numero=12"

#6
"select AVG(precio) as prom_precio from facturas_items where factura_tipo = 1 and factura_numero=12"

#7
"SELECT facturas_items.*, (cantidad*precio) as total_bruto_linea from facturas_items where factura_tipo=1 and factura_numero=12"

#7b
"""SELECT facturas_items.* , (cantidad*precio) as total_bruto_linea,
	(cantidad*precio)*(1- (descuento_porcentaje/100)) as total_bruto
 from facturas_items where factura_tipo=1 and factura_numero=12;"""

#8
"""SELECT facturas_items.* , (cantidad*precio) as total_bruto_linea,
	(cantidad*precio)*(1- (descuento_porcentaje/100)) as total_bruto
 from facturas_items where factura_tipo=1 and factura_numero=12
	ORDER BY total_bruto_linea
 ;"""

#9
"""
SELECT DISTINCT situacion_iva FROM clientes;
"""

#10 a
"SELECT nombre || '-' || calle || numero_puerta || ' Piso ' || piso || ' depto ' || dpto from clientes"

#10 b
"SELECT nombre || ' - ' || calle "

#11
#queda todo nulo

#12
"Select * from productos where lower(nombre) like '%e%'"

#13
"Select * from productos where lower(nombre) like 'pi%'"

#14
"Select * from productos where lower(nombre) like '_o%'"

#15
"select * from productos where upper(nombre) != nombre"

#16
"Select count(1), tipo_persona , situacion_iva from clientes group by tipo_persona, situacion_iva"

#16 b realice un query que liste tipo y numero de facturas y la cantidad de items obtenida por agrupacion
"SELECT f.numero, f.tipo, Count(1) as items FROM facturas f , facturas_items fi where f.tipo = fi.factura_tipo and f.numero = fi.factura_numero group by f.numero, f.tipo;"

#17
"SELECT f.numero, f.tipo, Count(1) as items FROM facturas f , facturas_items fi where f.tipo = fi.factura_tipo and f.numero = fi.factura_numero group by f.numero, f.tipo; having count(1) >1;"

#18
"insert into clientes (cuit, nombre, situacion_iva, tipo_persona, calle, numero_puerta, piso, dpto, codigo_postal, fecha_baja) values(31439577, 'jero', 'I', 'F', 'unacalle', '33', '', '', '8888', '01-01-2011');"

#19
"select clientes.* from clientes left join localidades on (clientes.codigo_postal=localidades.codigo_postal) where localidades.localidad is null;"

#20 a
"""
SELECT
	F.*,	FI.*, C.*, L.*, P.*, S.*
FROM
	facturas F
INNER JOIN facturas_items FI ON (FI.factura_tipo = F.tipo AND FI.factura_numero = F.numero)
INNER JOIN clientes C on (F.cuit = C.cuit)
INNER JOIN localidades L on (C.CODIGO_POSTAL = L.CODIGO_POSTAL)
INNER JOIN provincias P on (P.ID = L.PROVINCIA_ID)
INNER JOIN situaciones_iva S on (S.ABREVIATURA = C.SITUACION_IVA)

;
"""

#21
"""SELECT
	FI.*, F.*,	FI.*, C.*, L.*, P.*, S.*
FROM
	facturas_item FI
	LEFT JOIN facturas F ON (FI.factura_tipo = F.tipo AND FI.factura_numero = F.numero)
INNER JOIN clientes C on (F.cuit = C.cuit)
INNER JOIN localidades L on (C.CODIGO_POSTAL = L.CODIGO_POSTAL)
INNER JOIN provincias P on (P.ID = L.PROVINCIA_ID)
INNER JOIN situaciones_iva S on (S.ABREVIATURA = C.SITUACION_IVA)
;"""

#22
"Select F.*, C.* from facturas F join clientes C on (f.cuit = c.cuit)"

#23
"Select F.*, C.* from clientes C left join facturas F on (f.cuit = c.cuit)"

#24 a
"Select F.*, c.nombre from facturas F left join clientes (f.cuit = c.cuit)"

#24 b
"select F.*, c.nombre from facturas F , clientes C where (f.cuit = c.cuit)"

#24 c
"select F.*, (SELECT first 1 nombre from clientes where clientes.cuit = f.cuit) from facturas F;"

#25

"SELECT F.* FROM facturas F  WHERE f.cuit IN (select cuit from clientes where clientes.tipo_persona = 'F');"

#26
"DELETE FROM clientes where codigo_postal is null"

#27
"INSERT INTO facturas (tipo, numero, fecha, cuit) values (2, 13, '11-02-1985', (SELECT FIRST 1 cuit from clientes)); "

#28

"""Select f.* from facturas f left join facturas_items fi on (f.tipo = fi.factura_tipo and f.numero = fi.factura_numero) where fi.factura_numero is Null
UNION all
SELECT f.TIPO, f.NUMERO, f.FECHA, f.CUIT from facturas f inner join facturas_items fi on (f.tipo= fi.factura_tipo and f.numero = fi.factura_numero) group by  f.TIPO, f.NUMERO, f.FECHA, f.CUIT having count(f.tipo)=1;
"""

#29
"insert into provincias (id, nombre) values((SELECT MAX(id)+1 from provincias), 'BUENOS AIRES');"
"""
	insert into localidades (id, nombre, provincia_id) values
	(
		(select max(id)+1 from localidades),
		'SAN BERNARDO',
		(select first 1 id from provincias where nombre = 'BUENOS AIRES')
	);
"""
#30
"update provincias set nombre ='ENTRE RIOS' where id = 1"

#31
"update productos set precio = precio*1.10"

#32
"UPDATE facturas_items SET precio = (SELECT FIRST 1 precio FROM productos WHERE productos.codigo=facturas_items.producto_codigo);"

#33
"UPDATE clienteS SET nombre = UPPER(nombre);"

#34
"select * from facturas where fecha > CAST('NOW' as DATE);" #la forma mas fea que vi de conseguir la fecha actual (me imagino que es para poder conseguirla con diferentes tipos

#35
"select * from facturas where fecha > '2003/07/31'"

#36
"""select * from facturas where CAST(SUBSTRING( CAST(fecha AS varchar(30)) from 6 for 2) as integer) =8;"""#EL HORROR EL JJJJOOOORRRROOOOORRRR!
#si la db me llega a tirar la fecha de otra forma o con un solo caracter de mas o menos voy muerto.. esta es la morma mas sensata:
"select * from facturas where EXTRACT(MONTH from fecha) = 08;"

#37
"UPDATE clientes SET fechabaja = CAST('NOW' AS DATE) WHERE cuit = (SELECT MIN(cuit) FROM clientes);"
#38 consigna duplicada

#39
#"select * into clientes_baja from clientes where fecha_baja is not null;"
'insert into clientes_baja select * from clientes where fecha_baja is not null;'

#40
#'SELECT * FROM facturas JOIN clientes on facturas.cuit=clientes.cuit JOIN localidades on clientes.codigo_postal = localidades.codigo_postal;' forma normal, pero pide con subselect
"SELECT * FROM facturas JOIN clientes on facturas.cuit=clientes.cuit where clientes.codigo_postal = (SELECT codigo_postal FROM localidades where localidad ='PARANA');"

#41
"delete from facturas where numero=1050;" #mueren los items tamb

#42
"delete from clientes where cuit='20165851254'" #asi no me deja porque tiene una constraint de una foreing key

#43
"select * from facturas where exists (select * from facturas_items where facturas_items.numero_factura = facturas.numero and "
