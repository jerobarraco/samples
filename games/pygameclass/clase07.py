import pygame
from pygame.locals import *
# Import the android module. If we can't import it, set it to None - this
# lets us test it, and check to see if we want android-specific behavior.
try:
    import android
except ImportError:
    android = None

FPS = 30
FPSCLOCK = pygame.time.Clock()

pygame.init()
if android:
	android.init()
	android.map_key(android.KEYCODE_BACK, pygame.K_ESCAPE)
	
size = (400, 240) #pixels in x, pixel in y
#creamos la pantalla y la guardamos en DISPLAYSURF
DISPLAYSURF = pygame.display.set_mode(size, 0, 32)
negro = pygame.Color(0, 0, 0)

# Event constant.800
#TIMEREVENT = pygame.USEREVENT
# The FPS the game runs at.
FPS = 30

class Cat:
	x = 10
	y = size[1] - 100
	img = pygame.image.load('cat.png').convert_alpha()
	
	def movIzq(self):
		#Obliga al gato a moverse a la izquierda
		#el if permite mover SOLO si el gato esta a mas de 10 pixeles del borde izquierdo
		if self.x > 10:
			self.x -= 10 #lo mismo que decir self.x = self.x -10 (o sea, decrementar en 10)
		
	def movDer(self):
		global size
		width = size[0]
		if self.x < width:
			self.x +=10	
		
	def pintar(self):
		global DISPLAYSURF
		pos = (self.x, self.y)
		#le decimos a LA PANTALLA que COPIE la imagen de catImg en la posicion que dijimos
		DISPLAYSURF.blit(self.img, pos)
		
cat1 = Cat()

class Rat: #esta es la CLASE (molde)
	x = size[0] - 20
	y = size[1] - 50
	img = pygame.image.load('Rat_Sprites.png').convert_alpha()
	muerta = False
	ancho = 40
	alto = 40
	cuadros = [
		pygame.Rect(ancho*1, alto*0, ancho, alto),
		pygame.Rect(ancho*1, alto*1, ancho, alto),
		pygame.Rect(ancho*1, alto*2, ancho, alto),
	]
	cuadro_actual = 0
	def movIzq(self):
		#Obliga al gato a moverse a la izquierda
		#el if permite mover SOLO si el gato esta a mas de 10 pixeles del borde izquierdo
		if self.x > 10:
			self.x -= 10 #lo mismo que decir self.x = self.x -10 (o sea, decrementar en 10)
		
	def movDer(self):
		global size
		width = size[0]
		if self.x < width:
			self.x +=10	
			
	def update(self):
		#Esta funcion sera llamada por CADA cuadro
		#en cada cuadro queremos movernos un poco a la izq
		self.movIzq()
		if self.x <= 10:
			self.matar()
		
	def pintar(self):
		global DISPLAYSURF
		pos = (self.x, self.y)
		#le decimos a LA PANTALLA que COPIE la imagen de catImg en la posicion que dijimos
		DISPLAYSURF.blit(self.img, pos, self.cuadros[self.cuadro_actual])
		self.cuadro_actual +=1
		if self.cuadro_actual >= len(self.cuadros):
			self.cuadro_actual = 0
		
	def matar(self):
		self.muerta = True

#ratas es una VARIABLE GLOBAL que tiene asignada una lista vacia
ratas = []

def teclaIzquierda():
	#Procesa los eventos para cuando se presiona la tecla izquierda
	#global nos permite acceder a una variable externa a la funcion que no viene por parametro (eso se llama cochinada)
	global cat1
	cat1.movIzq()
	
def teclaDerecha():
	global cat1
	cat1.movDer()

def mouseClic():
	global ratas
	#creamos una rata :D
	nueva_rata = Rat()
	#y la agregamos a la lista de ratas 
	ratas.append(nueva_rata)

def eventos():
	"""
	#######################
	## PRIMERO : EVENTOS ##
	#######################
	"""
	#le pedimos a pygame una lista de "lo que paso mientras dibujamos"
	eventos = pygame.event.get()
	#recorremos CADA evento en la lista
	for event in eventos:
		#si UNO de esos eventos es IGUAL a "QUIT"
		if event.type == QUIT:
			#SALIMOS de la funcion, devolviendo un valor falso
			return False
		#SINO, SI evento es una tecla apretada.
		elif event.type == KEYDOWN:
			#Si hay una tecla apretada Y es la flecha izquierda
			if event.key == K_LEFT:
				#si se presiono la tecla izquierda llamamos directamente a la funcion que lo maneja
				teclaIzquierda()
			elif event.key == K_RIGHT:
				#lo mismo con la tecla derecha
				teclaDerecha()
			elif event.key == K_ESCAPE:
				return False
		#Si el evento no es NI QUIT NI KEYDOWN, el if verifica SI es MOUSEUP
		elif event.type == MOUSEBUTTONUP:
			#Si es mouseup
			mouseClic()
	#Si llegamos hasta aqui, no encontro el return del quit, por lo que devolvemos True
	return True

def estados():
	"""
	#######################
	## SEGUNDO : ESTADO  ##
	#######################
	"""
	global ratas
	#recorremos la lista, y por cada rata:
	for rata in ratas[:]:
		#le decimos que actualice su propio estado
		rata.update()
		if rata.muerta:
			ratas.remove(rata)
	return True
	
def pintar():
	"""
	#######################
	## TERCERO : DIBUJAR ##
	#######################
	"""
	#usamos global para acceder las variables externas
	global DISPLAYSURF, cat1, ratas
	#"re-iniciamos" la pantalla
	DISPLAYSURF.fill(negro)
	#le decimos al gato que se pinte
	cat1.pintar()
	#por cada rata
	for rata in ratas:
		#dile que se pinte
		rata.pintar()
	#Actualizamos la pantalla
	pygame.display.flip()
	return False


def main():
	continuar = True
	while continuar:
		if android:
			if android.check_pause():
				android.wait_for_resume()
		#simplificamos los 3 pasos
		#1) procesar eventos 
		#Devuelve False en caso de encontrar un QUIT, lo guardamos en continuar,
		#Eso hara que salga del while si encuentra un QUIT
		continuar = eventos()
		#procesamos los estados
		estados()
		#pintamos TODO
		pintar()
		FPSCLOCK.tick(FPS)	

# This isn't run on Android.
if __name__ == "__main__":
    main()
