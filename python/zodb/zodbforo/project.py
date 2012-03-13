# -*- coding: utf-8 -*-
from mizodb import zodb, render, currentUser, not_allowed
from ZODB.blob import Blob
from models import Project
import web
#el formulario para crear un proyecto
form_manager = web.form.Form(
	#el campo i almacena el indice del proyecto
	web.form.Hidden("i"),
	web.form.Textbox('name',
			web.form.Validator('Empty name', lambda x: x ),
			description='Name:'
		)
)
#El formulario para un karaoker
form_karaoker = web.form.Form(
	web.form.Hidden("i", web.form.Validator("You can't create a project", lambda x: x) ),
	web.form.Textbox('name', description='Name:'	),
	#un checkbox que dice si esta finalizada la tarea
	web.form.Checkbox("done", value="True", description="Done"),
	web.form.Textbox("lines", description="Lines:")
)
#form para los efectos
form_effects = web.form.Form(
	web.form.Hidden("i", web.form.Validator("You can't create a project", lambda x: x) ),
	web.form.Textbox('name', description='Name:'	),
	web.form.Checkbox("done", value="True", description="Done"),
	web.form.Dropdown("fxtool",  ('Kafx', 'Lua', 'nyuFx', 'Template', 'other'), description="Tool used"),
	web.form.File('script', description="Script"),
	web.form.File('ass', description="Ass")
)

#formulario para el traductor
form_translator = web.form.Form(
	web.form.Hidden("i", web.form.Validator("You can't create a project", lambda x: x) ),
	web.form.Textbox('name', description='Name:'	),
	web.form.Checkbox("done", value="True", description="Done"),
	web.form.Textbox('lines', description="Lines")
)

#formulario para el editor
form_editor = web.form.Form(
	web.form.Hidden("i", web.form.Validator("You can't create a project", lambda x: x) ),
	web.form.Textbox('name', description='Name:'	),
	web.form.Checkbox("done", value="True", description="Done"),
)

#formulario para el encoder
form_encoder= web.form.Form(
	web.form.Hidden("i", web.form.Validator("You can't create a project", lambda x: x) ),
	web.form.Textbox('name', description='Name:'	),
	web.form.Checkbox("done", value="True", description="Done"),
	web.form.Textbox('width', description="Width"),
	web.form.Textbox('height', description="Height"),
	web.form.Textbox('duration', description="Duration (secs)"),
	#Creamos una lista de codecs posibles, para audio, video, y tambien una lista de containers
	web.form.Dropdown("acodec",  ('mp3', 'aac', 'wma', 'oga', 'other'), description="Audio Codec"),
	web.form.Dropdown("vcodec",  ('xvid', 'x264', 'wmv', 'ogv', 'other'), description="Video Codec"),
	web.form.Dropdown("container",  ('avi', 'mp4', 'mkv', 'wmv', 'other'), description="Container")
)


class BaseEdit:
	#Una clase base para manejar todos los formularios para los diferentes trabajos, 
	#el manager usa una clase aparte
	#las clases descendientes deben definir: createForm(), fillForm(), readForm()
	#pueden usar: self.form, self.num, self.user, self.project
	def __init__(self, fclass, multi_part=False):
		#constructor, toma la clase del formulario por parametro, 
		#y un parametro opcional que dice si es multipart
		self.fclass = fclass
		self.multi_part = multi_part
		
	def GET(self):
		#Responde al get, muestra el formulario para crear/editar
		self.num = web.input(i=None).i
		self.user = currentUser()
		if not self.num :
			#solo un manager puede crear
			return render.not_allowed("Bad request")

		self.num = int(self.num)
		#verifica que el usuario pertenezca al proyecto
		if self.num not in self.user.projects:
			return render.not_allowed("This is not your project")
		
		#Lee el proyecto de la base de datos (directamente desde el usuario)
		self.project = self.user.projects[self.num]
		#Crea el formulario
		self.form = self.fclass()
		#llama a la funcion para rellenar el formulario
		#esto se define en las clases hijas
		self.fillForm()
		#devuelve el html completo
		return render.project_edit(self.user, self.form, self.multi_part)

	def POST(self):
		#Maneja la creacion/edicion del proyecto
		self.user = currentUser()
		#crea el formulario
		self.form = self.fclass()
		#Verifica que los datos del formulario sean validos
		val = self.form.validates()
		if not val : 
			#Si no es valido, reenvia el formulario
			return render.project_edit(self.user, self.form, self.multi_part)
		
		#Lee los datos del formulario
		#script y ass necesario para que funcione el multipart
		self.inp = web.input(i=None, script={}, ass={})
		num = self.inp['i']
		if not num:
			return render.not_allowed("bad request")

		self.num = int(num)
		#Verifica que el proyecto este asignado al usuario
		if self.num not in self.user.projects:
			return render.not_allowed("This is not your project")
		
		#lee el proyecto
		self.project = self.user.projects[self.num]
		#Lee los datos del formulario
		self.readForm()
		#Recalcula el porcentaje
		self.project.donePercent()
		
		zodb.commit(self.user.name , "Project %s edited"%self.num)
		raise web.seeother("/project/list/")

class EditKaraoker(BaseEdit):
	#Administra la edicion de proyectos del editor
	#Hereda de BaseEdit
	def __init__(self):
		#Incializa la clase base, pasa por parametro la clase del formulario que usara
		#y le indica que no es un formulario multipart
		BaseEdit.__init__(self, form_karaoker, False)
		
	def fillForm(self):
		#Cuando sea necesario rellena el formulario con los datos del proyecto
		self.form.fill({
			'i':self.num,
			'name':self.project.name,
			'done':self.project.d_karaoke,
			'lines' : self.project.klines,
		})

	def readForm(self):
		#Lee los datos del formulario al proyecto
		self.project.klines = int(self.form['lines'].value)
		self.project.d_karaoke = self.form['done'].checked

#El resto de las clases son similares
class EditEffects(BaseEdit):
	def __init__(self):
		BaseEdit.__init__(self, form_effects, True)
		#Este formulario si es multiparte (para adjuntar archivos)
		
	def fillForm(self):
		self.form.fill({
			'i':self.num,
			'name':self.project.name,
			'done': self.project.d_effect,
			'script_size' : self.project.script_size,
			'fxtool': self.project.fxtool,
		})

	def readForm(self):
		#Al ser un formulario multiparte hay que tener cuidado con los parametros
		self.project.fxtool = self.inp['fxtool']
		self.project.d_effect = 'done' in self.inp
		#verifica si hay un archivo adjuntado
		if self.inp['script'].filename:
			#Reemplaza el script por uno nuevo
			self.project.script = Blob() 
			#Lo abre para escritura
			f = self.project.script.open("w")
			#Escribe los datos obtenidos del formulario
			f.write(self.inp['script'].value)
			
			f.close()
			#Calcula el tamanio
			self.project.script_size = len(self.inp['script'].value)/1024
			
		#Realiza lo mismo con el archivo de subtitulos
		if self.inp.ass.filename:
			self.project.ass = Blob()
			f = self.project.ass.open("w")
			f.write(self.inp.ass.value)
			f.close()

class EditTranslator(BaseEdit):
	def __init__(self):
		BaseEdit.__init__(self, form_translator, False)

	def fillForm(self):
		self.form.fill({
			'i':self.num,
			'name':self.project.name,
			'done': self.project.d_translation,
			'lines': self.project.tlines
		})

	def readForm(self):
		self.project.tlines = int(self.form['lines'].value)
		self.project.d_translation = self.form['done'].checked

class EditEncoder(BaseEdit):
	def __init__(self):
		BaseEdit.__init__(self, form_encoder, False)

	def fillForm(self):
		self.form.fill({
			'i':self.num,
			'name':self.project.name,
			'done': self.project.d_encode,
			'width' : self.project.width,
			'height' : self.project.height,
			'duration': self.project.duration,
			'acodec':self.project.acodec,
			'vcodec':self.project.vcodec,
			'container':self.project.container
		})

	def readForm(self):
		p = self.project
		p.d_encode = self.form['done'].checked
		p.width = int(self.form['width'].value)
		p.height = int(self.form['height'].value)
		p.duration = int(self.form['duration'].value)
		p.acodec = self.form['acodec'].value
		p.vcodec = self.form['vcodec'].value
		p.container = self.form['container'].value


class EditEditor(BaseEdit):
	def __init__(self):
		BaseEdit.__init__(self, form_editor, False)

	def fillForm(self):
		self.form.fill({
			'i':self.num,
			'name':self.project.name,
			'done': self.project.d_edition,
		})

	def readForm(self):
		self.project.d_edition = self.form['done'].checked

class EditManager:
	#Administra ediciones/creaciones para el manager
	def GET(self):
		user = currentUser()
		num = web.input(i=None).i
		#crea el formulario
		f = form_manager()
		#verifica si se crea o edita un proyecto
		if num:
			#Se esta intentando editar un proyecto
			
			#Verifica que el proyecto pertenezca al usuario
			num = int(num)
			if num not in user.projects:
				return render.not_allowed("This is not your project")
			#lee el proyecto
			p = user.projects[num]
			name = p.name
			karaokeval = (p.karaoke and p.karaoke.name or '')
			encodeval = p.encode and p.encode.name or ''
			translateval = p.translation and p.translation.name or ''
			effectval = p.effect and p.effect.name or ''
			editorval = p.edition and p.edition.name or ''
		else : 
			#Se esta creando un proyecto
			karaokeval = ""
			encodeval = ""
			translateval = ""
			effectval = ""
			editorval = ""
			name = ""
		#crea la lista de usuarios para cada trabajo
		#Esto es importante hacerlo al hacer el request para tener usuarios nuevos
		karaokers = zodb.usersForJob("Karaoker")
		effectors = zodb.usersForJob('Effects')
		translator = zodb.usersForJob('Translator')
		encoders = zodb.usersForJob('Encoder')
		editors = zodb.usersForJob('Editor')
		#Adiciona los dropdowns con las listas a los inputs el formulario
		#si el formulario actualmente tiene alguien asignado para ese cargo
		#lo asigna en la propiedad value
		f.inputs = tuple(list(f.inputs) + [
				web.form.Dropdown("karaoke", karaokers, value=karaokeval),
				web.form.Dropdown("effect", effectors, value=effectval),
				web.form.Dropdown("translation", translator,value=translateval),
				web.form.Dropdown("encode", encoders, value=encodeval),
				web.form.Dropdown("edition", editors, value=editorval )
		])
		f.fill({
			'i':num,
			'name':name,
		})
		return render.project_edit(user, f)

	def POST(self):
		u = currentUser()
		#crea y valida el formulario
		f = form_manager()
		val = f.validates()
		if not val : 
			#Si es invalido, reintenta
			return render.project_edit(u, f)
		
		#necesito el input porque en el form base no existen los elementos de users 
		#ya que son insertados dinamicamente
		inp = web.input(i=None)
		num = inp.i
		#Verifica si esta intentando crear o editar
		if num:
			num = int(num)
			#Si intenta editar
			#Verifica que el proyecto pertenezca al usuario
			if num not in u.projects:
				return render.not_allowed("This is not your project")
			#Lee el proyecto de la base de datos
			p = zodb.projects[num]
			print "proyecto cargado", p
		else:
			#Si desea crear un proyecto, verifica que el usuario sea Manager
			if u.job != 'Manager':				
				return render.not_allowed("You can't create projects")
			#Crea un proyecto nuevo
			p = Project(f['name'].value, u)
			print "proyecto creado", p
			
		#Asigna el nombre al proyecto
		p.name = f['name'].value
		#Asigna los integrantes al proyecto
		#Primero obtiene los datos de cada usuario
		#Esta linea hace lo siguiente:
		##1 toma el valor del formulario
		##2 Si es valido, toma el usuario de la base de datos
		##3 si no es valido devuelve None
		k = inp.karaoke and zodb.users[inp.karaoke] or None 
		t = inp.translation and zodb.users[inp.translation] or None
		ef = inp.effect and zodb.users[inp.effect] or None
		en = inp.encode and zodb.users[inp.encode] or None
		ed = inp.edition and zodb.users[inp.edition] or None
		p.setTeam(k, t, ef, en, ed)
		
		#Recalcula el porcentaje
		#basado en muchas cosas una de ellas es la cantidad de personas presentes
		#y pone como completados los puestos vacios
		p.donePercent()
		zodb.commit(u.name , "new project")
		raise web.seeother("/project/list/")
	

class Edit:
	#Clase que administra las ediciones/creaciones de proyectos segun el rol
	def __init__(self):
		#Constructor
		user = currentUser()
		job = user.job
		#Crea la instancia correcta segun el trabajo del usuario
		if job == 'Manager': self.edit = EditManager()
		elif job == 'Karaoker': self.edit= EditKaraoker()
		elif job == 'Translator' : self.edit= EditTranslator()
		elif job == 'Effects' : self.edit= EditEffects()
		elif job == 'Encoder' : self.edit= EditEncoder()
		elif job == 'Editor' : self.edit= EditEditor()
		else : self.edit = None

	def GET(self):
		#Maneja peticiones Get, pasando el control a la clase instanciada en el constructor
		if not self.edit : return render.not_allowed("You cant edit projects")
		else: return self.edit.GET()

	def POST(self):
		#Maneja peticiones POST
		if not self.edit : render.not_allowed("You cant edit projects")
		else: return self.edit.POST()

class List:
	#Lista los proyectos del usuario
	def GET(self):
		#Toma el usuario
		user = currentUser()
		#y procesa el template con los proyectos del usuario
		return render.project_list(user.projects.values(), user, True)

class Delete:
	#Elimina un proyecto
	def GET(self):
		num = int(web.input(i=None).i)
		user = currentUser()
		#Toma el proyecto de la base de datos
		p = zodb.projects[num]
		#verifica que el usuario sea el manager
		#Notar como zodb permite el uso de operadores de comparacion entre objetos
		#Una misma instancia posee su propia identidad, y muchos objetos pueden
		#referenciar a la misma instancia sin problemas
		if p.manager != user:
			return render.not_allowed("You can't delete this project")
		#Borra el proyecto, utilizando la logica codigicada en la misma clase
		p.delete()
		zodb.commit(user.name , "project deleted")
		raise web.seeother("/project/list/")

class Publish:
	#Publica un proyeto
	def GET(self):
		num=int(web.input().i)
		user = currentUser()
		#lee el proyecto de la base de datos
		p = zodb.projects[num]
		#verifica que el usuario sea el manager del proyecto
		if p.manager != user:
			return render.not_allowed("You can't publish this project")

		#publica el proyecto
		#Notar como la logica de la tarea está almacenada en la clase proyecto
		p.publish()
		
		zodb.commit(user.name, "project published")
		raise web.seeother("/project/list/published/")

class ListPublished:
	#Lista los proyectos finalizados
	def GET(self):
		user = currentUser()
		#Lee los proyectos finalizados de la base de datos
		projects = zodb.complete_projects.values()
		#procesa el template
		return render.project_list(projects, user )