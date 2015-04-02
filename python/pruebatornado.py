import tornado.web
import os
productos = (
	'Algo', "Ponele", 'Otro ALgo', 'mas algo', 'zapatos', 'zapatos franceses'
)
imagenes = (
	'static/nande_fallen.jpg',
	'static/nande_fallen.jpg',
	'static/nande_fallen.jpg',
	'static/nande_fallen.jpg',
	'static/nande_fallen.jpg',
	'static/nande_fallen.jpg',
	'static/nande_fallen.jpg',
	'static/nande_fallen.jpg',
	)
class Buscar(tornado.web.RequestHandler):
	def get(self, *args):
		palabra = self.get_argument('palabra', "").lower()
		self.write("<html> hola, bsucando.....</br>")
		self.write("se encontro:</br>")
		contador = 1
		for i, p in enumerate(productos):
			if palabra in p.lower():
				self.write(str(contador))
				self.write(") <a href='mostrar?code=")
				self.write(str(i))
				self.write("'>")
				self.write(p)
				self.write("</a></br>")
				contador +=1
		self.write('</html>')

class Mostrar(tornado.web.RequestHandler):
	def get(self, *args):
		code = int(self.get_argument('code', -1))
		self.write('<html>')
		if 0<= code < len(productos):
			self.write("pero mira que lindo este <b>")
			self.write(productos[code])
			self.write("</b></br><img src='")
			self.write(imagenes[code])
			self.write("'>")
		else:
			self.write('no seas payaso')
		self.write('</html>')
		
	
class Hola(tornado.web.RequestHandler):
	def get(self, *args, **kwargs):
		code = self.get_argument('search_code', None)
		nombre = self.get_argument('nombre', None)
		edad = self.get_argument('edad', 'te olvidaste la edad pelotudo')
		print code, nombre, edad
		msg = "<html> hola <b>"+nombre+"</b> ("+edad+") </br> \n&nbsp;&nbsp;somos groxos <a href='http://moongate.com.ar'>moongate</a> no te va a cargar porque no tenes internet jujujaja</br> viniste a buscar '"+code+"' pero tenemos paja de implementar la busqueda</html>"
		
		self.write(msg)
		"""
		self.write("<html> hola <b>")
		self.write(nombre)
		self.write("</b>")
		self.write(" (")
		self.write(edad)
		self.write(") </br> \n&nbsp;somos groxos <a href='http://moongate.com.ar'>moongate</a> no te va a cargar porque no tenes internet jujujaja</br>")"""
		
	def post(self, *args, **kw):
		self.write('no tenemos post')
		
pth = os.getcwd()
application = tornado.web.Application([
			(r'/hola(.*)', Hola),
			(r'/buscar(.*)', Buscar),
			(r'/mostrar(.*)', Mostrar),
		], static_path = pth)
application.listen(8080, '0.0.0.0')


tornado.ioloop.IOLoop.instance().start()
