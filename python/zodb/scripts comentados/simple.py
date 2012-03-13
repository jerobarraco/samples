# simple.py - escribe algunos valores simples
from mizodb import MiZODB, transaction

#Crea una instancia de la base de datos
db = MiZODB('Data.fs')
#Toma la raiz
dbroot = db.raiz
#asigna el valor entero 3 a la clave "numero"
dbroot['numero'] = 3
#Asigna el valor de cadena "Gift" a la clave "string"
dbroot['string'] = 'Gift'
#Asigna una lista a la clave "lista"
dbroot['lista'] = [1, 2, 3, 5, 7, 12]
#Asigna un diccionario a la clave "diccionario"
dbroot['diccionario'] = { 1918: 'Red Sox', 1919: 'Reds' }
#Asigna un diccionario anidado, que contiene listas y tuplas, 
#a la clave "anidado"
dbroot['anidado'] = {
	1918: [ ('Red Sox', 4), ('Cubs', 2) ],
	1919: [ ('Reds', 5), ('White Sox', 3) ],
}
#Acepta la transaccion
transaction.commit()

db.close()
