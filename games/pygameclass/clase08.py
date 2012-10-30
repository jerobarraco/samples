import pygame
from pygame.locals import *
# Import the android module. If we can't import it, set it to None - this
# lets us test it, and check to see if we want android-specific behavior.
try:
    import android
except ImportError:
    android = None

FPS = 15
FPSCLOCK = pygame.time.Clock()

pygame.init()
if android:
	android.init()
	android.map_key(android.KEYCODE_BACK, pygame.K_ESCAPE)
	
size = (480, 320) #pixels in x, pixel in y
#creamos la pantalla y la guardamos en DISPLAYSURF
DISPLAYSURF = pygame.display.set_mode(size, 0, 32)
negro = pygame.Color(0, 0, 0)

# Event constant.800
#TIMEREVENT = pygame.USEREVENT
# The FPS the game runs at.
FPS = 30

class Cat(pygame.sprite.Sprite):
	def __init__(self):
		pygame.sprite.Sprite.__init__(self)
		self.image = pygame.image.load('cat.png').convert_alpha()
		self.rect = self.image.get_rect()
		self.rect.x = 10
		self.rect.y = size[1] - 50
		
	def movIzq(self):
		#Obliga al gato a moverse a la izquierda
		#el if permite mover SOLO si el gato esta a mas de 10 pixeles del borde izquierdo
		if self.rect.x > 10:
			self.rect.move_ip(-10, 0) #lo mismo que decir self.x = self.x -10 (o sea, decrementar en 10)
		
	def movDer(self):
		global size
		width = size[0]
		if self.rect.x < width:
			self.rect.move_ip(10, 0)

		

#esta es la CLASE (molde)
#Hereda de Sprite
class Rat(pygame.sprite.Sprite):
	#creamos una funcion para inciializar todos los valores
	muerta = False
	ancho = 40
	alto = 40
	cuadro_actual = 0
	cuadros = [
		pygame.Rect(ancho*1, alto*0, ancho, alto),
		pygame.Rect(ancho*1, alto*1, ancho, alto),
		pygame.Rect(ancho*1, alto*2, ancho, alto),
	]

	def __init__(self):
		pygame.sprite.Sprite.__init__(self)
		self.image = pygame.image.load('rat.png').convert_alpha()
		self.rect = self.image.get_rect()
		self.rect.x = size[0] - 20
		self.rect.y = size[1] - 50
			

	def movIzq(self):
		#Obliga al gato a moverse a la izquierda
		#el if permite mover SOLO si el gato esta a mas de 10 pixeles del borde izquierdo
		if self.rect.x > 10:
			self.rect.move_ip(-10,0) #lo mismo que decir self.x = self.x -10 (o sea, decrementar en 10)
		
	def movDer(self):
		global size
		width = size[0]
		if self.rect.x < width:
			self.rect.move_ip(10, 0)
			
	def update(self):
		#Esta funcion sera llamada por CADA cuadro
		#en cada cuadro queremos movernos un poco a la izq
		self.movIzq()
		if self.rect.x <= 10:
			self.kill()
		
#creo una instancia de gato
cat1 = Cat()
#creo el grupo para el gato
grupo_gato = pygame.sprite.Group()
#agrego el gato al grupo
grupo_gato.add(cat1)
#ratas es una VARIABLE GLOBAL que tiene asignada una lista vacia
ratas = pygame.sprite.Group()
 

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
	#y la agregamos al grupo de ratas 
	ratas.add(nueva_rata)

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
	ratas.update()
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
	grupo_gato.draw(DISPLAYSURF)
	ratas.draw(DISPLAYSURF)
	
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
