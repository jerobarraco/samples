class Cliente:
	#lista cabecera
	cod = 0
	nombre = ""
	saldo = 0.0
	#enlaces
	ops = None #operaciones
	sig = None

class Operacion:
	#lista auxiliar
	numero = 0
	tipo = ""
	fecha = ""
	importe = 0.0
	sig = None

def agregaCli(inicio, nombre, codigo):
	ncli = Cliente()#RCeldaCli
	ncli.nombre = nombre
	ncli.codigo = codigo # GCeldaCli(ncli, nombre, codigo, saldo, operaciones, sig)
	ncli.saldo = 0.0
	ncli.sig = None
	ncli.ops = None
	if inicio == None:
		inicio = ncli
	else:
		reco = inicio
		while (reco != None):
			sig = reco.sig#LCeldaCli(reco, .... , sigcli, sigop)
			if (sig == None):
				reco.sig = ncli #GCeldaCli(reco, ...., ncli, sigop)
			reco = sig
		#finmientras
	#finsi
	return inicio
#fin

def agregarOperacion(cli, tipo, monto, fecha, numero):
	op = Operacion()
	op.numero = numero
	op.tipo = tipo
	op.importe = monto
	op.fecha = fecha
	op.sig = None
	
	saldo = cli.saldo #LCeldaCli(cli, saldo, ....)
	if tipo == "venta":
		saldo += monto
	else:
		saldo -= monto
	cli.saldo = saldo #GCeldaCli(cli, saldo, ...)
	
	recoop = cli.ops
	if (recoop == None):
		cli.ops = op #GCeldaCli(cli, ...., 
	else:
		while (recoop != None):
			sigop = recoop.sig #LCeldaOp(reco, ..., sigop)
			if (sigop == None):
				recoop.sig = op
				break
			#fin
			recoop = sigop
		#finmientras
	#finmientras
	return
	
#puntero al inicio del listado de clientes
clientes = None
clientes = agregaCli(clientes, "Juan Perez", 203)
clientes = agregaCli(clientes, "Martin Martinez", 204)
clientes = agregaCli(clientes, "Pedro Gomez", 205)

for codigo, tipo, monto, fecha, numero in ( (203, "venta", 10, "hoy", 1), (204, "venta", 10, "ayer", 2), (203, "pago", 9, "hoy", 3) ):
	reco = clientes
	while (reco != None):
		if (reco.codigo == codigo):
			agregarOperacion(reco, tipo, monto, fecha, numero)
			break
		reco = reco.sig
		
http://pythontutor.com/visualize.html#code=class+Cliente%3A%0A%09%23lista+cabecera%0A%09cod+%3D+0%0A%09nombre+%3D+%22%22%0A%09saldo+%3D+0.0%0A%09%23enlaces%0A%09ops+%3D+None+%23operaciones%0A%09sig+%3D+None%0A%0Aclass+Operacion%3A%0A%09%23lista+auxiliar%0A%09numero+%3D+0%0A%09tipo+%3D+%22%22%0A%09fecha+%3D+%22%22%0A%09importe+%3D+0.0%0A%09sig+%3D+None%0A%0Adef+agregaCli(inicio,+nombre,+codigo)%3A%0A%09ncli+%3D+Cliente()%23RCeldaCli%0A%09ncli.nombre+%3D+nombre%0A%09ncli.codigo+%3D+codigo+%23+GCeldaCli(ncli,+nombre,+codigo,+saldo,+operaciones,+sig)%0A%09ncli.saldo+%3D+0.0%0A%09ncli.sig+%3D+None%0A%09ncli.ops+%3D+None%0A%09if+inicio+%3D%3D+None%3A%0A%09%09inicio+%3D+ncli%0A%09else%3A%0A%09%09reco+%3D+inicio%0A%09%09while+(reco+!%3D+None)%3A%0A%09%09%09sig+%3D+reco.sig%23LCeldaCli(reco,+....+,+sigcli,+sigop)%0A%09%09%09if+(sig+%3D%3D+None)%3A%0A%09%09%09%09reco.sig+%3D+ncli+%23GCeldaCli(reco,+....,+ncli,+sigop)%0A%09%09%09reco+%3D+sig%0A%09%09%23finmientras%0A%09%23finsi%0A%09return+inicio%0A%23fin%0A%0Adef+agregarOperacion(cli,+tipo,+monto,+fecha,+numero)%3A%0A%09op+%3D+Operacion()%0A%09op.numero+%3D+numero%0A%09op.tipo+%3D+tipo%0A%09op.importe+%3D+monto%0A%09op.fecha+%3D+fecha%0A%09op.sig+%3D+None%0A%09%0A%09saldo+%3D+cli.saldo+%23LCeldaCli(cli,+saldo,+....)%0A%09if+tipo+%3D%3D+%22venta%22%3A%0A%09%09saldo+%2B%3D+monto%0A%09else%3A%0A%09%09saldo+-%3D+monto%0A%09cli.saldo+%3D+saldo+%23GCeldaCli(cli,+saldo,+...)%0A%09%0A%09recoop+%3D+cli.ops%0A%09if+(recoop+%3D%3D+None)%3A%0A%09%09cli.ops+%3D+op+%23GCeldaCli(cli,+....,+%0A%09else%3A%0A%09%09while+(recoop+!%3D+None)%3A%0A%09%09%09sigop+%3D+recoop.sig+%23LCeldaOp(reco,+...,+sigop)%0A%09%09%09if+(sigop+%3D%3D+None)%3A%0A%09%09%09%09recoop.sig+%3D+op%0A%09%09%09%09break%0A%09%09%09%23fin%0A%09%09%09recoop+%3D+sigop%0A%09%09%23finmientras%0A%09%23finmientras%0A%09return%0A%09%0A%23puntero+al+inicio+del+listado+de+clientes%0Aclientes+%3D+None%0Aclientes+%3D+agregaCli(clientes,+%22Juan+Perez%22,+203)%0Aclientes+%3D+agregaCli(clientes,+%22Martin+Martinez%22,+204)%0Aclientes+%3D+agregaCli(clientes,+%22Pedro+Gomez%22,+205)%0A%0Afor+codigo,+tipo,+monto,+fecha,+numero+in+(+(203,+%22venta%22,+10,+%22hoy%22,+1),+(204,+%22venta%22,+10,+%22ayer%22,+2),+(203,+%22pago%22,+9,+%22hoy%22,+3)+)%3A%0A%09reco+%3D+clientes%0A%09while+(reco+!%3D+None)%3A%0A%09%09if+(reco.codigo+%3D%3D+codigo)%3A%0A%09%09%09agregarOperacion(reco,+tipo,+monto,+fecha,+numero)%0A%09%09%09break%0A%09%09reco+%3D+reco.sig&mode=edit&origin=opt-frontend.js&cumulative=false&heapPrimitives=false&drawParentPointers=false&textReferences=true&showOnlyOutputs=false&py=2&rawInputLstJSON=%5B%5D