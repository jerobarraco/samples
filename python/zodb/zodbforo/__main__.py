# -*- coding: utf-8 -*-
"""
Created on Tue Jan 17 21:45:05 2012

@author: Jerónimo Barraco Mármol
"""
#Incluimos todo lo necesario
#el modulo web pertenece al proyecto web.py es necesario para todo lo que tiene que ver con web
import web
#datetime es una clase de python para el manejo del tiempo
import datetime
#clases creadas en este proyecto
#Thread (llamado fThread para evitar confusiones con Threading)
#from fthread import fthread, tEdit, tDelete
import fthread
#clase post
import post
#from post import post
#usuario
import user
#from user import Edit as uedit, Avatar, UView, UList, UBan
#proyecto
import project

#urls es una tupla, conteniendo una secuencia de urls y la clase manejadora que corresponde

urls = (
	'/', 'main',
	'/logout/', 'logout',
	'/register/', 'user.Register',
	'/thread/', 'fthread.fthread',
	'/thread/list/', 'fthread.tList',
	'/thread/edit/', 'fthread.tEdit',
	'/thread/delete/', 'fthread.tDelete',
	'/post/edit/', 'post.post',
	'/user/edit/', 'user.Edit',
	'/user/avatar/', 'user.Avatar',
	'/user/view/', 'user.View',
	'/user/list/', 'user.List',
	'/user/ban', 'user.Ban',
	'/project/edit/', 'project.Edit',
	'/project/delete/', 'project.Delete',
	'/project/list/', 'project.List',
	'/project/list/published/', 'project.ListPublished',
	'/project/publish/', 'project.Publish'
)

#crea la instancia de la aplicacion con la urls y las variables cargadas
app = web.application(urls, globals())
#crea el objeto sesion
if web.config.get('_session') is None:
	session = web.session.Session(app, web.session.DiskStore('sessions'),
		initializer={'user': 'anonymous'})
	web.config._session = session
else:
	session = web.config._session


from web import form
from mizodb import zodb, render, plain_render

#crea el formulario de login, con sus validadores
signin_form = form.Form(
	form.Textbox('username',
		#el validador verifica que el nombre exista en el diccionario de usuarios
		form.Validator("You don't exist.", lambda x: x in zodb.users.keys()),
		description='Username:'
	),
	form.Password('password', description='Password:'),
	validators = [
		#el validador verifica que el password sea correcto (es importante)
		form.Validator("Username and password didn't match.",
			lambda x: zodb.users[x.username].checkPassword(x.password)),
		#el validador verifica que el usuario no este baneado
		form.Validator("YOU'RE BANNED!!! (ask for forgiveness to the admin)",
			lambda x: not zodb.users[x.username].banned)
	]
)

#clase principal
class main:
	def GET(self):
		#si el usuario esta logueado, muestra la pagina principal
		#si no lo esta, le muestra el formulario de login
		my_signin = signin_form()
		uname = web.config._session.user
		if uname and uname!="anonymous":
			return render.main()
		else:
			#notar el uso de plain_render
			return plain_render.login(my_signin)

	def POST(self):
		#esta funcion responde a la peticion POST (normalmente el logueo del usuario)
		my_signin = signin_form()
		#valida el formulario
		val = my_signin.validates()
		#toma el nombre de usuario en el formulario
		uname = my_signin['username'].value
		#verifica que es valido y no es anonymous
		if val and uname != "anonymous":
			#lo almacena en la sesion
			session.user = uname
			#actualiza la fecha de logueo
			zodb.users[uname].last_login = datetime.datetime.utcnow()
			#actualiza la base de datos
			zodb.commit(uname, "logged")
			#redirecciona al / para que se realice un GET sobre / y eso devuelva el main
			raise web.seeother('/')
		else:
			#si no es valido, reintentara el login
			return plain_render.login(my_signin)

#Esta clase maneja el deslogueo
class logout:
	def GET(self):
		#vacia la sesion
		session.kill()
		#redirije al /
		raise web.seeother('/')

if __name__ == "__main__":
	#si se intento ejecutar este script y no importarlo, 
	#ejecuta la instancia de la aplicacion
	app.run()
