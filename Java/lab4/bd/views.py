from firebirdcon import run, connect
connect("localhost/3050:c:\\test.FDB")

"""
CREATE VIEW items
AS
SELECT facturas_items.* , (cantidad*precio) as total_bruto_linea,
	(cantidad*precio)*(1- (descuento_porcentaje/100)) as total_bruto
 from facturas_items;
"""

"""
CREATE VIEW precios
	AS
			SELECT factura_tipo, AVG(precio) as promedio
			FROM facturas_items
			GROUP BY factura_tipo
"""

"""Drop VIEW items"""

"""CREATE OR REPLACE items
AS
SELECT facturas_items.* , (cantidad*precio) as total_bruto_linea,
	(cantidad*precio)*(1- (descuento_porcentaje/100)) as total_bruto
 from facturas_items;
 """

