# lista_y_diccionario.py
#Ejemplo de los objetos PersistentList y PersistentMapping provistos por ZODB
#Importa todo lo necesario
from mizodb import MiZODB, transaction
from persistent.list import PersistentList
from persistent.mapping import PersistentMapping

db = MiZODB('./Data.fs')
dbroot = db.raiz

#Imprime los valores iniciales
print "Lista al iniciar" , dbroot['lista']
print "Diccionario al iniciar", dbroot['diccionario']
#Convierte una lista nativa de python en una lista persistente de ZODB

lista = PersistentList(dbroot['lista'])
dbroot['lista'] = lista
#Comprueba que PersistentList cuenta con el metodo estandard append
lista.append(42)

#Convierte el diccionario nativo en un objeto PersistentMapping
dic = PersistentMapping(dbroot['diccionario'])
dbroot['diccionario'] = dic
dic[1920] = "Red sox"

transaction.commit()

#Imprime los valores luego de aceptar la transaccion
print "Lista al terminar", dbroot['lista']
print "Diccionario al terminar", dbroot['diccionario']

#podemos ver que conservan las facilidades de los objetos nativos de python
#eliminar un elemento de la lista
lista.pop()
#eliminar un elemento del diccionario
del dic[1920]

transaction.commit()
db.close()