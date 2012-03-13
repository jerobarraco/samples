# -*- coding: utf-8 -*-
#Este modulo administra al usuario
import web
from mizodb import zodb, render, currentUser,  plain_render
from ZODB.blob import Blob
import models

#Listado de trabajos posibles
JOBS = ("Karaoker", "Translator", "Effects", "Encoder", 'Editor', "Manager", "Leecher", 'Timmer' )

#Formulario para el usuario
t_form = web.form.Form(
	web.form.Textbox('web'),
	web.form.Textbox('gender'),
	web.form.Textbox('age'),
	web.form.Textbox('country'),
	web.form.Textbox('ytchannel', description="Youtube channel"),
	web.form.Textbox('signature'),
	web.form.Dropdown('job', JOBS, description="Class"),
	web.form.File('avatar', description="Avatar")
)

#Clase para ver los datos de un usuario
class View:
	def GET(self):
		u = web.input(name="").name
		#Toma el nombre de usuario como parametro y lo verifica
		if not u:
			raise web.seeother("/")
		#Lee el usuario de la base de datos
		user = zodb.users[u]
		c = 0
		#Crea un array de los ultimos posts
		posts = []
		#Recorre los threads de manera reversa
		for i, t in sorted(enumerate(zodb.threads), reverse=True):
			#Recorre los posts de manera inversa
			for j, p in sorted(enumerate(t.posts), reverse=True):
				#Verifica si el post pertenece al usuario
				if p.user == user:
					#Lo agrega a la lista de posts junto con los indices
					posts.append((i, j, p))
					#Al llegar al 6to post sale del bucle
					c+=1
					if c >=6 : break
		return render.user_view(user, posts)
			
#Edita un usuario
class Edit:
	def GET(self):
		#Peticion get del formulario de edicion
		f = t_form()
		#toma el usuario actual
		u = currentUser()
		#llena el formulario con los datos
		f.fill({'name':u.name, 'web':u.web,
			'gender':u.gender, 'age':u.age, 'country':u.country,
			'ytchannel':u.channel, 'signature':u.signature, 'job':u.job})
		return render.user_edit(u.name, f)

	def POST(self):
		#Peticion post para editar un usuario
		#Toma el usuario actual
		user = currentUser()
		#instancia el formulario
		form = t_form()
		#valida el formulario
		val = form.validates()
		if not val:
			#Si no valida, lo reintenta
			return render.user_edit(user.name, form)
		#Toma los datos del formulario
		#Utiliza el input al ser un formulario multipart
		x = web.input(avatar={},
			web=user.web, age=user.age, gender=user.gender,
			country=user.country, ytchannel=user.channel,
			signature=user.signature, job=user.job )
		#Toma los datos del formulario
		user.web = x['web']
		user.age = x['age']
		user.gender = x['gender']
		user.country = x['country']
		user.channel = x['ytchannel']
		user.signature = x['signature']
		user.job = x['job']
		#Si hay un avatar adjunto
		if x['avatar'].filename:
			#Elimina el anterior
			del user.picture #para ver si esto borra el archivo
			#Crea uno nuevo
			user.picture = Blob()
			#Lo abre para escritura
			f = user.picture.open("w")
			#Ecribe el contenido
			f.write(x['avatar'].value)
			f.close()
			#Asigna el nombre de archivo
			user.picturename = x['avatar'].filename

		zodb.commit(user.name, "profile updated")
		raise web.seeother('/user/list/')
	
#Devuelve el avatar para un usuario
#Notar que devuelve un binario diferente a html
class Avatar:
	#Diccionario con _algunos_ de los posibles tipos Mime para las diferentes extensiones
	cType = {
            "png":"images/png",
            "jpg":"image/jpeg",
            "gif":"image/gif",
            "ico":"image/x-icon"
					}
					
	def GET(self):
		uname = web.input(name="").name
		#Controla que el nombre sea valido
		if uname not in zodb.users.keys():
			return "error"
		#Toma el usuario de la base de datos
		user = zodb.users[uname]
		#Opera sobre el nombre de archivo para tomar la extension
		#Separa por ".", toma el ultimo elemento, y lo convierte a minuscula
		ext = user.picturename.split('.')[-1].lower()
		#Asigna un header de la respuesta http para asignar el tipo de contenido
		#Coloca el tipo mime, o "application/octet-stream" si no esta registrado
		web.header("Content-Type", self.cType.get(ext, "application/octet-stream"))
		web.header("Content-Disposition", "attachment; filename=%s" % user.picturename)
		#lee el avatar desde el usuario (toma el blob, lo abre para lectura, y obtiene los bytes)
		data = user.picture.open('r').read()
		#Si no hay avatar
		if len(data)==0:
			#coloca el contenido como jpg
			web.header("Content-Type", self.cType.get("jpg"))
			#Abre un avatar por defecto
			#b es importante, indica modo binario
			f = open("static/no avatar.jpg",'rb')
			#lee los datos
			data = f.read()
			#cierra el archivo
			f.close()
		#devuelve los datos
		return data

#Devuelve una lista de usuarios
class List():
	def GET(self):
		#Devuelve la lista completa de usuarios
		#Indica si el usuario actual es administrador
		return render.user_list(zodb.users, currentUser().admin)

#Banea a un usuario
class Ban():
	def GET(self):
		u = currentUser()
		#verifica que el usuario sea admin
		if not u.admin:
			return render.not_allowed("Cuak! :V I should ban you!")
		inputs = web.input(name="", ban=0)
		#lee el usuario de la base de datos
		user = zodb.users[inputs.name]
		#actualiza el flag de baneo al que se paso por parametro
		user.banned = bool(int(inputs.ban))
		zodb.commit()
		raise web.seeother("/user/list/")
	
#Formulario de registro
signup_form = web.form.Form(
	web.form.Textbox('username',
		#Verifica que el usuario no exista
		web.form.Validator('Username already exists.', lambda x: x not in zodb.users.keys()),
		description='Username:'
	),
	web.form.Password('password', description='Password:'),
	web.form.Password('password_again', description='Repeat your password:'),
	validators = [
		#Verifica que los passwords sean iguales
		web.form.Validator("Passwords didn't match.", lambda i: i.password == i.password_again)]
)


class Register:
	#Clase para registrar un usuario
	def GET(self):
		#Muestra el formulario de registro
		my_signup = signup_form()
		return plain_render.signup(my_signup)

	def POST(self):
		#Crea el formulario
		my_signup = signup_form()
		#Si el formulario no valida
		if not my_signup.validates():
			#Reintenta
			return plain_render.signup(my_signup)
		else:
			#Sino crea el usuario
			username = my_signup['username'].value
			password = my_signup['password'].value
			#Crea el usuario nuevo y lo inserta en la base de datos
			zodb.users[username] = models.User(username, password)
			zodb.commit("system", "New user "+username)
			raise web.seeother('/')