# -*- coding: utf-8 -*-
#Este modulo se encarga de manejar los threads

from mizodb import zodb, render, currentUser
from models import Thread
import web

#formulario para crear un thread
t_form = web.form.Form(
	web.form.Textbox('name',
		#valida que el nombre del thread no exista
		web.form.Validator('Thread already exists',
			lambda x: x not in [t.name for t in zodb.threads]),
		description='Name:'
	),
	web.form.Hidden("number")
)

#Esta clase permite ver un thread
class fthread:
	def GET(self):
		#toma el parametro number del request, por defecto es None
		number = web.input(number=None).number
		#toma el usuario actual
		user = currentUser()
		#si el numero es valido lo mostrara
		if number is not None:
			#lee el thread de la base de datos
			thread = zodb.threads[int(number)]
			#y devuelve el template procesado
			return render.thread_see(number, thread.name, thread.posts, user)
		else:
			return render.not_allowed("Invalid thread")
		
#crea/edita un thread
class tEdit:
	def GET(self):
		number = web.input(number=None).number
		user = currentUser()
		#verifica que el usuario sea admin
		if user.admin:
			f = t_form()
			if number is not None:
				#si el thread existe carga los datos en el formulario para poder editarlo
				thread = zodb.threads[int(number)]
				f.fill({'number':number, 'name':thread.name})
			return render.thread(f)
		else:
			return render.not_allowed("you can't create/edit projects")
		
	def POST(self):
		#Esta funcion se encarga de los request POST 
		#usados para crear o editar un thread
		f = t_form()
		val  = f.validates()
		user = currentUser()
		#verifica el usuario
		if not user.admin:
			return render.not_allowed("you can't create/edit projects")
		#verifica que el formulario sea valido, si no lo es, reintenta
		if not val:
			return render.thread(f)
		
		#ahora intenta crear/editar el thread
		tn = f['number'].value
		#verifica si es un thread nuevo o uno existente
		if tn:
			#si es existente lo lee de la base de datos y cambia su nombre
			thread = zodb.threads[int(tn)]
			thread.name = f['name'].value
		else:
			#si es un thread nuevo, lo crea y le asigna su nombre
			zodb.threads.append(Thread(f['name'].value))
		#y acepta la transaccion
		zodb.commit(user.name, "Thread created/modified")
		raise web.seeother('/thread/list/')
	
class tDelete:
	def GET(self):
		#Borra un thread
		number = web.input(number=None).number
		user = currentUser()
		#verifica que el numero exista y el usuario sea admin
		if number and user.admin:
			#elimina el thread de la lista
			del zodb.threads[int(number)]
			zodb.commit(user.name, "Thread deleted")
		raise web.seeother("/thread/list/")
	
class tList:
	def GET(self):
		#devuelve la lista de threads
		user = currentUser()
		return render.thread_list(user.admin, zodb.threads)