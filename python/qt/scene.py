import sys
#imports
import PySide
from PySide.QtGui import QGraphicsScene, QGraphicsView, QApplication, QPen, QBrush, QPixmap, QPainter
# http://qt-project.org/doc/qt-4.8/graphicsview.html
# http://srinikom.github.io/pyside-docs/PySide/QtGui/QGraphicsView.html
# https://deptinfo-ensip.univ-poitiers.fr/ENS/pyside-docs/PySide/QtGui/QPen.html?highlight=qpen#PySide.QtGui.QPen
# https://deptinfo-ensip.univ-poitiers.fr/ENS/pyside-docs/PySide/QtGui/QBrush.html?highlight=qbrush#PySide.QtGui.QBrush
# https://deptinfo-ensip.univ-poitiers.fr/ENS/pyside-docs/PySide/QtGui/QPixmap.html?highlight=qpixmap#PySide.QtGui.QPixmap
# https://deptinfo-ensip.univ-poitiers.fr/ENS/pyside-docs/PySide/QtGui/QPainter.html?highlight=qpainter#PySide.QtGui.QPainter


from PySide.QtCore import QRectF, Qt
# https://deptinfo-ensip.univ-poitiers.fr/ENS/pyside-docs/PySide/QtCore/QRectF.html#PySide.QtCore.QRectF

CUENTA = 0
def grabar():
	#Defino una funcion para grabar porque la app tiene que haberse ejecutado para poder grabarlo creo
	# ojo que esto es horrendo, estoy usando el scene como "global" y el cuenta tambien
	
	#lo graba/imprime
	global CUENTA, scene#toma estas variables desde afuera de esta funcion, esto se considera feo,
	# http://developer.nokia.com/community/wiki/QPainter::begin:Paint_device_returned_engine_%3D%3D_0_(Known_Issue)
	# The solution is pretty simple and it's about defining a valid size for the QPixmap to paint. For instance defining it as QPixmap pix(100, 100) fix this issue.
	#es imprescindible poner widht y height
	pixmap = QPixmap(scene.width(), scene.height())
	
	painter = QPainter(pixmap)
	painter.setRenderHint(QPainter.Antialiasing)#le dice que use Antialiasing
	scene.render(painter)#le dice a la escena que se dibuje sobre el painter
	painter.end()#termina el painter
	pixmap.save(str(CUENTA)+".png")#lo guarda
	CUENTA += 1

class View(QGraphicsView):#creo una clase solo para poder implementar el keyPressEvent
	def keyPressEvent(self, event):
		print("key")
		if event.key() == Qt.Key_P:
			print("lololol")
			grabar()
			
#crea la app, la ventana y el view y la escena
app = QApplication(sys.argv)#si no creara la clase App con esto bastaria
scene = QGraphicsScene()
#view = QGraphicsView(scene)
view = View(scene)
view.show()


#dibuja algo en la escena
scene.addRect(QRectF(0, 0, 100, 200), QPen(Qt.black), QBrush(Qt.green));

#ejecuta la app 
app.exec_()

""" QGraphicsScene scene;
 scene.addRect(QRectF(0, 0, 100, 200), QPen(Qt::black), QBrush(Qt::green));

 QPixmap pixmap;
 QPainter painter(&pixmap);
 painter.setRenderHint(QPainter::Antialiasing);
 scene.render(&painter);
 painter.end();

 pixmap.save("scene.png");"""