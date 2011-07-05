# -*- coding: utf-8 -*-
"""Gracias y dedicado a
Gervasio H. Barraco Mármol
por trabajo de consultoría sobre el tema :B
"""
activoP = [
	#las dio la profe
	'Caja',
	'FONDO FIJO (caja chica)',
	'Banco',
	'Banco caja de ahorro',
	'Banco Plazo Fijo',
	'Deudores (Por venta/Prendario/Morosos)',
	'Anticipo de/a proveedores',

	"Equipos de computacion",
	'Instalaciones',
	"Muebles y útiles",
	'Rodados'
	'Valores a DEPOSITAR',
	'Documentos a Cobrar',
	'Créditos',
	'IVA Crédito',
	'Anticipos Impuesto Ganancia',
	'Retenciones Ing Brutos',
	'Retenciones Municipales',
	'Bienes*',
]

activoNP = [
	'Bienes de cambio',
	'Fondo de oportunidades',
	'Inversiones temporales',
	'Clientes',
	'Documentos por cobrar',
	'Funcionarios y empleados',
	'IVA acreditable',
	'Inventarios',
	'Mercancía en tránsito',
	'Anticipo de impuestos',

	'Papelería y útiles',
	'Propaganda y publicidad',
	'Muestras m�dicas y papeler�a',
	'Primas de seguros y fianzas',
	'Rentas pagadas por anticipado',
	'Intereses pagados por anticipado',
	'Terrenos',
	'Edificios',
	'Maquinaria',
	'Mobiliario y equipo de oficina',
	'Equipo de transporte',
	'Equipo de entrega y reparto',
	'Derechos de autor',
	'Patentes',
	'Marcas registradas',
	'Nombres comerciales',
	'Crédito mercantil',
	'Gastos de investigaci�n y desarrollo',
	'Gastos de mercadotecnia',
	'Gastos preoperativos',
	'Descuento en emisi�n de obligaciones',
	'Gastos en colocaci�n de valores',
	'Gastos de constituci�n',
	'Gastos de organizaci�n',
	'Gastos de instalaci�n',
	'Papelería y Útiles',
	'Propaganda y publicidad',
	'Primas de seguros y fianzas',
	'Muestras m�dicas y literatura',
	'Rentas pagadas por anticipado',
	'Intereses pagados por anticipado',
	'Fondo de amortizaci�n de obligaciones',
	'Dep�sitos en garantía',
	'Inversiones en proceso',
	'Terrenos no utilizados',
	'Maquinaria no utilizada'
]

pasivoP = [
	#Las dio la profe
	'Deudas*'
	'* Algo a PAGAR',
	'Proveedores',
	'Anticipos de/a Clientes',
	'Acreedores',
	'Prestamos *',
	'Sueldos a PAGAR',
	'Cargas Sociales A PAGAR',
	'IVA A PAGAR',
	'Ingresos brutos A PAGAR',
	'Tasa municipal A PAGAR',
	'Impuesto a las ganancias A PAGAR',
	'Documentos por PAGAR',
]

pasivoNP= [
	'Acreedores bancarios',
	'Dividendos por pagar',
	'Impuestos y derechos por pagar',
	'Impuestos y derechos retenidos por enterar',
	'Impuesto sobre la renta (ISR) por pagar',
	'Participación a los trabajadores en las utilidades (PTU) por pagar',
	'Rentas cobradas por ANTICIPADO',
  'Intereses cobrados por ANTICIPADO',
	'Acreedores hipotecarios',
	'Acreedores bancarios',
	'Documentos por pagar a largo plazo',
	'Obligaciones en circulación',

]

respP = [
	'Venta *',
	'comisiones ganadas',
	'otros ingresos',
	'descuentos obtenidos',
	'intereses ganados',
	'diferencia de cambio',
	'intereses *',
]
respNP = []

resnP = [
	'Amortización',
	'costo vta mercaderia',
	'Intereses pagados',
	'Intereses bancarios',
	'Amortizacion ACUMULADA *',
	'Gastos *',
	'Costos *',
	'Servicios ',
	'sueldos comercializacion',
	'aportes patronales de comercializacion',
	'(gastos de) Publicidad',
	'alquiler local',
	'seguros comerciales',
	'luz y telefono',
	'Impuestos y tasas',
	'gastos varios',
	'Movilidad',
	'Sueldos',
	'Aportes patronales',
	'Honorarios'
]
resnNP=[

]

patnP = [
	'Aporte Propietarios',
	'Resultados no asignados',
	'Capital',
	'Reservas (técnicas, legal, etc)',
	'Resultados Acumulados/del ejercicio',
	'Ajuste Capital'
]
patnNP = []

import random

array = []

array.extend (
	[ (i, 'a') for i in activoP ]
)

array.extend(
	[(i, '-') for i in resnP]
)

array.extend(
	[(i, 'p') for i in pasivoP]
)

array.extend(
	[(i, '+') for i in respP]
)

array.extend(
	[(i, 'n') for i in patnP]
)

hard = raw_input('Elige tu nivel Hard o Normal (no hay easy :D)').strip().lower()=='h'

if hard :
	array.extend (
		[ (i, 'a') for i in activoNP ]
	)

	array.extend(
		[(i, '-') for i in resnNP]
	)

	array.extend(
		[(i, 'p') for i in pasivoNP]
	)

	array.extend(
		[(i, '+') for i in respNP]
	)

	array.extend(
		[(i, 'n') for i in patnNP]
	)


random.shuffle(array)

acertadas = 0
total = 0
for i, l in array:
	q = "Activo/Pasivo/ResPos/ResNeg/PatNet/Salir? [AP+-NS]\n %s"%i
	c = raw_input(q).lower().strip()
	if c == 's': break
	total +=1
	if c == l :
		acertadas +=1
		print "Bien\n"
	else:
		print "Mal, respuesta : %s\n"%c

print "Acertaste %s de %s (de un total de %s)"%(acertadas, total, len(array))
