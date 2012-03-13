# escribir_hosts.py
from mizodb import MiZODB, transaction
from modelo import Host
db = MiZODB('./Data.fs')
dbroot = db.raiz

#Crea una instancia del objeto Host
host1 = Host('www.example.com', '192.168.7.2', ['eth0', 'eth1'])
#Asigna la instancia creada a la clave "www.example.com" 
#en la raiz de la base de datos
dbroot['www.example.com'] = host1

#Realiza lo mismo con otra instancia
host2 = Host('dns.example.com', '192.168.7.4', ['eth0', 'gige0'])
dbroot['dns.example.com'] = host2

#acepta la transaccion
transaction.commit()
db.close()