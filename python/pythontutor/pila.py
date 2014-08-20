#!/bin/python
#coding:utf-8
#Implementacion de pilas en python para visualizar con pythontutor.com
#para entender como funcionan en c++
#Copyright (c) 2014 Jerónimo Barraco Mármol GPLv3  (moongate.com.ar)

# Para ver en python tutor abrir este link
# http://pythontutor.com/visualize.html#code=%23!/bin/python%0D%0A%23coding%3Autf-8%0D%0A%23Implementacion+de+pilas+en+python+para+visualizar+con+pythontutor.com%0D%0A%23para+entender+como+funcionan+en+c%2B%2B%0D%0A%23Copyright+(c)+2014+Jer%C3%B3nimo+Barraco+M%C3%A1rmol+GPLv3+(moongate.com.ar)%0D%0A%0D%0Aclass+Celda%3A%0D%0A%09%23una+celda,+cuando+se+crea+se+inicializa+solo+con+dato+%3D+0+y+enlace+%3D+None%0D%0A%09dato+%3D+0%0D%0A%09enlace+%3D+None%0D%0A%09%09%0D%0Adef+push(tope,+dato)%3A%0D%0A%09%23Esta+es+la+funcion+%22insertar%22+de+una+pila,+en+la+pila+los+datos+nuevos+van+siempre+primero,+moviendo+los+datos+que+ya+estaban+para+despues.%0D%0A%09%23tambien+conocido+como+LIFO+(Last+In+First+Out+(ultimo+en+entrar,+primero+en+salir))%0D%0A%09%23Empuja+un+nuevo+dato+en+la+parte+superior+de+la+pila%0D%0A%09%0D%0A%09%23RCelda()+reserva+la+memoria+para+una+celda+nueva%0D%0A%09ncelda+%3D+Celda()+%0D%0A%09%0D%0A%09%23Asignamos+los+datos+simil+a+GCelda(ncelda,+dato,+tope)%0D%0A%09ncelda.dato+%3D+dato%0D%0A%09ncelda.enlace+%3D+tope%0D%0A%09%23actualizamos+el+tope+para+que+%22apunte%22+a+la+nueva+celda%0D%0A%09tope+%3D+ncelda%0D%0A%09%0D%0A%09return+tope+%23no+hay+pasaje+por+referencia+(de+variables+en+python)%0D%0A%09%0D%0Adef+pop(tope)%3A%0D%0A%09%23Pop+es+el+equivalente+a+quitar+Y+leer+de+una+pila%0D%0A%09%23por+definici%C3%B3n+las+pilas+solo+pueden+leerse+al+quitarse+los+elementos,+o+sea,+no+se+la+pede+recorrer+sin+quitar+los+elementos+de+la+pila.+En+otras+palabras,+al+leer+un+elemento+hay+que+quitarlo+tambien.%0D%0A%09%23Si+bien+t%C3%A9cnicamente+es+posible+recorrer+una+pila+sin+quitar+los+elementos,+la+definici%C3%B3n+de+pila+es+esta.%0D%0A%09%0D%0A%09%23leemos+los+datos+de+la+celda+en+el+tope+antes+de+eliminarla%0D%0A%09%23Simil+a+LCelda(tope,+dato,+enlace)%0D%0A%09enlace+%3D+tope.enlace%0D%0A%09dato+%3D+tope.dato%0D%0A%09%0D%0A%09%23eliminamos+la+celda+liberando+la+memoria%0D%0A%09%23LibCelda(tope)%0D%0A%09del+tope%23no+es+necesario+en+python+pero+es+para+simbolizar+la+eliminacion+en+c%2B%2B%0D%0A%09%0D%0A%09%23actualizamos+el+puntero+al+tope%0D%0A%09tope+%3D+enlace%0D%0A%09%0D%0A%09return+tope,+dato%0D%0A%0D%0A%23ejemplo+de+insercion%0D%0Atope+%3D+None%0D%0Afor+i+in+(10,+2,+5,+20,+30)%3A%0D%0A%09tope+%3D+push(tope,+i)%0D%0A%0D%0A%09%0D%0A%23ejemplo+de+extraccion%0D%0Asuma+%3D+0%0D%0Acant+%3D+0%0D%0Awhile+tope!%3D+None%3A%0D%0A%09tope,+dato+%3D+pop(tope)%0D%0A%09cant+%2B%3D+1%0D%0A%09suma+%2B%3D+dato%0D%0A%09print+(dato)%0D%0Aprint+(%22suma+es+%22%2B+str(suma))&mode=display&origin=opt-frontend.js&cumulative=false&heapPrimitives=false&drawParentPointers=false&textReferences=false&showOnlyOutputs=false&py=3&rawInputLstJSON=%5B%5D&curInstr=114

class Celda:
	#una celda, cuando se crea se inicializa solo con dato = 0 y enlace = None
	dato = 0
	enlace = None
		
def push(tope, dato):
	#Esta es la funcion "insertar" de una pila, en la pila los datos nuevos van siempre primero, moviendo los datos que ya estaban para despues.
	#tambien conocido como LIFO (Last In First Out (ultimo en entrar, primero en salir))
	#Empuja un nuevo dato en la parte superior de la pila
	
	#RCelda() reserva la memoria para una celda nueva
	ncelda = Celda() 
	
	#Asignamos los datos simil a GCelda(ncelda, dato, tope)
	ncelda.dato = dato
	ncelda.enlace = tope
	#actualizamos el tope para que "apunte" a la nueva celda
	tope = ncelda
	
	return tope #no hay pasaje por referencia (de variables en python)
	
def pop(tope):
	#Pop es el equivalente a quitar Y leer de una pila
	#por definición las pilas solo pueden leerse al quitarse los elementos, o sea, no se la pede recorrer sin quitar los elementos de la pila. En otras palabras, al leer un elemento hay que quitarlo tambien.
	#Si bien técnicamente es posible recorrer una pila sin quitar los elementos, la definición de pila es esta.
	
	#leemos los datos de la celda en el tope antes de eliminarla
	#Simil a LCelda(tope, dato, enlace)
	enlace = tope.enlace
	dato = tope.dato
	
	#eliminamos la celda liberando la memoria
	#LibCelda(tope)
	del tope#no es necesario en python pero es para simbolizar la eliminacion en c++
	
	#actualizamos el puntero al tope
	tope = enlace
	
	return tope, dato

#ejemplo de insercion
tope = None
for i in (10, 2, 5, 20, 30):
	tope = push(tope, i)

	
#ejemplo de extraccion
suma = 0
cant = 0
while tope!= None:
	tope, dato = pop(tope)
	cant += 1
	suma += dato
	print (dato)
print ("suma es "+ str(suma))