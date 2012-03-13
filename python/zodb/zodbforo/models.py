# -*- coding: utf-8 -*-
#Este modulo tiene los modelos de los datos que se utilizaran en el programa
from datetime import datetime
from hashlib import sha1
from persistent import Persistent
from persistent.list import PersistentList
from BTrees.OOBTree import OOBTree
from ZODB.blob import Blob
from mizodb import zodb

#Los niveles posibles, una lista de elementos
#Cada elemento define un nivel, debe ser una tupla
#El primer elemento es la experiencia minima, 
#el segundo elemento es la experiencia maxima por cada proyecto terminado
LEVELS = (
	#index = level number
	#(min xp, xp_per_project)
	(0, 10), #5 projects to level up = (0+(10*5)) #0 projects
	(50, 15), #10 projects to level up (50+(15*10)) #5 projects
	(200, 20), #20 projects to LU (200+(20*20)) #15 projects
	(800, 25) #35 projects
)

class User(Persistent):
	#Clase para el usuario
	#Variables basicas, preinicializadas
	#no es necesario poner las variables aca, pero evita problemas en caso de
	#agregar variables nuevas, y que las viejas instancias no las posean
	
	name = ""
	pwd = ""
	admin = False
	banned = False
	
	gender = ""
	web = "http://kafx.com.ar"
	age = ""
	country = ""
	channel = ""
	signature = "i haz no signature, so visit kafx.com.ar :)"
	#fecha del ultimo login
	last_login = datetime.utcnow()
	#nivel 
	level = 0
	#experiencia
	xp = 0
	job = "Leecher"
	#cantidad de proyectos cerrados
	closed_projects = 0
	picturename="no avatar.jpg"
	
	def __str__(self):
		#esta funcion devuelve una representacion a modo de String de la instancia
		#(Funcionamiento similar a toString() en java
		return self.name

	def __init__(self, name, password):
		#El constructor, recibe el nombre y el password por parametro
		#y crea los objetos necesarios
		self.name = name
		self.setPassword(password)
		#estos objetos se crean en este lugar para evitar problemas de mutabilidad
		self.picture = Blob()
		self.posts = PersistentList()
		self.projects = OOBTree()
		self.notifiactions = PersistentList()

	def setPassword(self, password):
		#Funcion que almacena el password encriptado con algoritmos sha1
		self.pwd = sha1(password).hexdigest()

	def checkPassword(self, password):
		#Devuelve true si el password pasado por parametro coincide con el almacenado
		return self.pwd == sha1(password).hexdigest()

	def canEdit(self, post):
		#devuelve True si el usuario puede editar el post pasado por parametro
		return self.admin or (self == post.user)

	def addPoints(self, points):
		#Agrega puntos a un usuario (pueden ser negativos)
		
		self.xp += points
		#verifica el nivel
		nl = 0
		#Itera los niveles y verifica a cual pertenece
		for i, l in enumerate(LEVELS):
			if self.xp >= l[0]: nl =i
			else: break #notar que estan ordenados ascendentemente
		
		if nl>self.level :
			#El usuario subio de nivel
			#TODO agregar notificacion
			pass
		elif nl<self.level:
			#El usuario bajo de nivel
			#TODO agregar notificacion
			pass
		self.level = nl

	def addNotification(self, notif):
		#TODO agrega una notificacion al usuario
		pass

	#Funciones para facilitar la vista,
	def getViewLink(self):
		#devuelve un enlace a la pagina que muestra el detalle del usuario
		return '<a href="/user/view/?name=%s">%s</a>'%(self.name, self.name)
	
	def getImage(self):
		#devuelve el codigo html para la imagen del usuario
		return '<img src="/user/avatar/?name=%s" />'%self.name


class Post(Persistent):
	#Clase que administra un post
	user = None
	text = ""
	title = ""
	
	def __init__(self, title, text, user):
		self.title = title
		self.text = text
		self.user = user

class Thread(Persistent):
	#Clase que administra un thread
	name = ""
	def __init__(self, name):
		self.posts = PersistentList()
		self.name = name

class Project(Persistent):
	#Clase que administra un proyecto
	key = -1
	#variables que indican los diferentes usuarios para los diferentes posiciones
	karaoke = None
	translation = None
	effect = None
	encode = None
	edition = None
	manager = None
	
	#puntos maximos para el nivel
	points_per_level = 10
	
	#done
	#porcentaje (0 a 1)
	done = 0.0
	#Flags de finalizacion de cada trabajo
	d_karaoke = False
	d_translation = False
	d_effect = False
	d_encode = False
	d_edition = False

	#puntos, discriminados por posicion para poder volverlos
	p_karaoke = 0
	p_translation = 0
	p_effect = 0
	p_encode = 0
	p_edition = 0
	p_manager = 0
	#datos del proyecto 
	#encoder
	width = 0
	height = 0
	duration = 0
	vcodec = "xvid"
	acodec = 'mp3'
	container = 'avi'
	#karaoke
	klines = 0
	#translation
	tlines = 0
	#effectss
	script_size = 0
	fxtool = 'other'

	def __init__(self, name, manager):
		#constructor recibe por parametro el nombre y el usuario que es el manager del proyecto
		#la clave se calcula a partir de la ultima asignada
		self.key = zodb.raiz['projects_id']	= zodb.raiz['projects_id']+1
		#Asigna el nombre
		self.name= name
		#Asigna el usuario manager
		#Notar que manager es un objeto persistido, y guardamos una referencia al mismo
		self.manager = manager
		#Almacena esta instancia en los proyectos globales
		zodb.projects[self.key] = self
		#Y asigna este proyecto al usuario manager
		self.manager.projects[self.key] = self
		#Asigna el tiempo de creacion
		self.start_time = datetime.utcnow()
		#Blob para el archivo de subtitulo
		self.ass = Blob()
		#Blob par el script
		self.script = Blob()
		
	def setTeam(self, karaoke=None, translation=None, effect=None, encode=None, edition=None ):
		#Esto se repite para cada integrante	
		#Verifica si el asignado es diferente del actual
		if self.karaoke != karaoke :
			#si el actual no es None
			if self.karaoke is not None:
				#quita el proyecto de su lista
				del self.karaoke.projects[self.key]
				
			#asigna el nuevo usuario
			self.karaoke = karaoke #can be None
			if karaoke is not None:
				#si el nuevo usuario existe le asigna el proyecto
				karaoke.projects[self.key] = self
				
		#Lo mismo para los demas
		if self.translation != translation :
			if self.translation is not None:
				del self.translation.projects[self.key]
			self.translation = translation
			if translation is not None:
				translation.projects[self.key] = self
			
		if self.effect != effect :
			if self.effect is not None:
				del self.effect.projects[self.key]
			self.effect = effect 
			if effect is not None:
				effect.projects[self.key] = self
			
		if self.encode != encode :
			if self.encode is not None:
				del self.encode.projects[self.key]
			self.encode = encode
			if encode is not None:
				encode.projects[self.key] = self
			
		if self.edition != edition :
			if self.edition is not None:
				del self.edition.projects[self.key]
			self.edition = edition
			if edition is not None:
				edition.projects[self.key] = self
				
	def getViewLink(self):
		#Devuelve un enlace a la vista del proyecto
		return '<a href="/project/view/?i=%s">%s</a>'%(self.key, self.name)

	def donePercent(self):
		#Recalcula el porcentaje terminado, y lo devuelve
		self.done = 0.0
		#pone la parte como terminada en caso que no haya nadie asignado
		if self.effect is None : self.d_effect = True
		if self.encode is None : self.d_encode = True
		if self.translation is None : self.d_translation = True
		if self.karaoke is None : self.d_karaoke = True
		if self.edition is None : self.d_edition = True
		
		#por cada parte terminada, aumenta el porcentaje
		self.done += self.d_effect and 0.2 or 0.0
		self.done += self.d_encode and 0.2 or 0.0
		self.done += self.d_translation and 0.2 or 0.0
		self.done += self.d_karaoke and 0.2 or 0.0
		self.done += self.d_edition and 0.2 or 0.0
		return self.done

	def publish(self):
		#Publica un proyecto
		#procede a cerrarlo (disparando toda la logica correspodiente
		self.delete(close=True)
		#y lo inserta en los proyectos completos
		zodb.complete_projects[self.key] = self
		#asigna los puntos
		self.setPoints()

	def delete(self, close=False):
		#elimina un proyecto, si el parametro close es verdadero, lo toma como un cierre
		#pequeño truco para iterar por cada uno de los usuarios en un proyecto
		for u in (self.karaoke, self.manager, self.effect, self.translation, self.encode):
			#si el usuario en cuestion es None, lo saltea
			if u is None : 
				continue
			#elimina el proyecto de los proyectos del usuario
			if self.key in u.projects:
				del u.projects[self.key]
			#si hay que cerrarlo, lo contamos como proyeto finalizado
			if close: 
				u.closed_projects +=1

		if self.key in zodb.projects:
			#lo borra de los proyectos normales
			del zodb.projects[self.key]
		
		#si no es eliminacion normal, lo elimina de los proyectos completos
		if not close:
			if self.key in zodb.complete_projects:
				del zodb.complete_projects[self.key]

	def reSetPoints(self):
		#restaura los puntos a 0
		if self.manager:
			self.manager.addPoints(-self.p_manager)
		if self.karaoke:
			self.karaoke.addPoints(-self.p_karaoke)
		if self.translation:
			self.translation.addPoints(-self.p_translation)
		if self.effect:
			self.effect.addPoints(-self.p_effect)
		if self.encode:
			self.karaoke.addPoints(-self.p_encode)
		self.p_manager = self.p_karaoke = self.p_translation = self.p_effect = self.p_encode= 0
		self.setPoints()

	def setPoints(self):
		#Asigna los puntos
		if self.manager :
			self.setManagerPoints()
		if self.karaoke :
			self.setKaraokePoints()
		if self.encode :
			self.setEncodePoints()
		if self.translation:
			self.setTranslationPoints()
		if self.effect:
			self.setEffectPoints()

	def setManagerPoints(self):
		#Asigna los puntos para el manager utilizando un algoritmo raro
		
		p = 0 #percent of the points per level
		cant = 0#different puntuations
		#1 point for every day before a month
		days = (datetime.utcnow() - self.start_time).days
		p = min(max(30-days, 0), 30)/30.0 #Max = 0 , min 30
		cant += 1

		#porque los demás trabajen a tiempo
		#porque no se le vayan del proyecto
		self.p_manager = (p/cant)*self.points_per_level
		self.manager.addPoints(self.p_manager)

	def setKaraokePoints(self):
		#Asigna los puntos al que hace los karaokes
		p = cant = 0
		#1 punto por cada linea de karaoke hasta 600
		p = min(max(self.klines, 0), 600)/600.0
		cant +=1
		#por timear kanjis
		#por no tardarse

		self.p_karaoke += (p/cant)*self.points_per_level
		self.karaoke.addPoints(self.p_karaoke)

	def setEncodePoints(self):
		p = cant = 0
		#puntos por tamaño de archivo
		#puntos por formato
		#puntos por resolucion
		res = self.width*self.height
		p += min(max(res-307200, 0), 1000)/1000.0 #1 punto por cada 100px encima de 640x480
		cant += 1

		#puntos por codec
		if self.codec == 'xvid':
			p += 0.7
		elif self.codec == 'x264':
			p += 0.9
		else:
			p += 0.3

		cant += 1
		#container,
		#audiocodec
		self.p_encode  += (p/cant)*self.points_per_level
		self.encode.addPoints(self.p_encode)

	def setTranslationPoints(self):
		p = cant = 0
		p += min(max(self.tlines, 0), 600)/600.0
		cant +=1
		self.p_encode  += (p/cant)*self.points_per_level
		self.translation.addPoints(self.p_translation)

	def setEffectPoints(self):
		p=cant=0

		#puntos de 60 a 240 segundos
		p += min(max(self.duration-60, 0), 180)/180.0
		cant +=1

		self.p_effect += (p/cant)*self.points_per_level
		self.effect.addPoints(self.p_effect)

#Clases no implementadas
class Group (Persistent):
	name = ""
	def __init__(self):
		self.users = PersistentList()

class Achievement (Persistent):
	name =""
	description = ""

class Notification(Persistent):
	description = ""