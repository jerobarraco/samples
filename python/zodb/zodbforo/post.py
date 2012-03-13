# -*- coding: utf-8 -*-
#Clase que administra las respuestas web para los posts

from mizodb import zodb, render, currentUser
from models import Post
import web

#form para crear un post
t_form = web.form.Form(
	web.form.Textbox('title',
		#Validador para el titulo
		web.form.Validator('Empty title', lambda x: x ),
		description='Title:'
	),web.form.Textarea("text",
		#validador para el contenido del post
		web.form.Validator("Say something", lambda x: x ))
	,
	#aca van los parametros ocultos del thread y el post
	web.form.Hidden("thread"),
	web.form.Hidden("post")
)

#Clase que permite crear o editar un post
class post:
	def GET(self):
		user = currentUser()
		f = t_form()
		#para tomar el thread y el post que vienen por el get
		i = web.input(post=None, thread=None)
		thread = i['thread']
		post = i['post']
		if thread is None:
			return render.not_allowed("Invalid thread")
		#carga el thread y el post de la base de datos
		t = zodb.threads[int(thread)]
		
		#Si el post es nulo, se esta creando
		if post is None:
			#carga el thread en los parametros ocultos del form
			#Toma el titulo del ultimo post 
			title = t.posts[-1].title
			if title[:2] != 'Re':
				title = 'Re: ' + title
			#y verifica si posee RE sino lo agrega
			f.fill({'thread':thread, 'title':title})
		else:
			#si el post esta asignado, esta editando
			
			p = t.posts[int(post)]
			#Verifica si el usuario puede editar el post
			#Notar como se utiliza logica de negocio almacenado en la clase models.User
			if user.canEdit(p):
				#carga los datos en el formulario
				f.fill({'thread':thread, 'post':post, 'text':p.text, 'title':p.title})
			else:
				return render.not_allowed("You can't edit this!")
		return render.do_post(f)

	def POST(self):
		#Maneja el posteo del el formulario de los posts
		user = currentUser()
		f = t_form()
		val  = f.validates()

		#Comprueba la validez del formulario, reintenta si no lo es
		if not val :
			return render.do_post(f)
		
		tn = f['thread'].value
		postn = f['post'].value
		title = f['title'].value
		text = f['text'].value

		if not tn:
			return render.not_allowed("Invalid thread")
		
		#Carga el thread de la base de datos
		thread = zodb.threads[int(tn)]
		#Si existe el post, lo esta editando
		if postn:
			#Carga el post
			post = thread.posts[int(postn)]
			#Verifica permisos
			if user.canEdit(post):
				#Actualiza los datos
				post.text = text
				post.title = title
			else:
				#Si no puede editar da error
				return render.not_allowed("You can't edit this!")
		else:
			#Se esta creando un post
			#Crea una instancia de Post con los parametro necesarios
			p = Post(title, text, user)
			#Agrega el post al thread
			thread.posts.append(p)
			#Y agrega el post al usuario correcto
			user.posts.append(p)
		#Acepta la transaccion	
		zodb.commit(user.name, "post created")
		raise web.seeother('/thread/?number='+str(tn))

