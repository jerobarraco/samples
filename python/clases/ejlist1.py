pers = []
mens = "Ingrese un nombre o nada para terminar: "
mensg = "Ingrese M para masculino o F para femenino: "
nom = input(mens).lower()
while nom:
	gen = input(mensg).lower()
	p = (nom, gen)
	pers.append(p)
	nom = input(mens).lower()

for nom, gen in pers:
	pre = "Querido" if gen == 'm' else "Querida"
	msg = "%s %s vote por mi" % (pre, nom)
	print(msg)
