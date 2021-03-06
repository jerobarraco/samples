#coding:utf-8
# CopyRight 2014 Jeronimo Barraco Mármol (moongate.com.ar) GPL v3

import sys
#imports
import PySide
from PySide.QtGui import QGraphicsScene, QGraphicsView, QApplication, QPen, QBrush, QPixmap, QPainter
# http://qt-project.org/doc/qt-4.8/graphicsview.html
# http://srinikom.github.io/pyside-docs/PySide/QtGui/QGraphicsView.html
# https://deptinfo-ensip.univ-poitiers.fr/ENS/pyside-docs/PySide/QtGui/QGraphicsView.html?highlight=qgraphicsview#PySide.QtGui.QGraphicsView
# https://deptinfo-ensip.univ-poitiers.fr/ENS/pyside-docs/PySide/QtGui/QGraphicsScene.html?highlight=qgraphicsscene#PySide.QtGui.QGraphicsScene
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
	#crea un pixmap (algo asi como una imagen en memoria)
	pixmap = QPixmap(scene.width(), scene.height())
	
	painter = QPainter(pixmap)#crea un painter sobre ese pixmap (que es el que pasara lo del scene al pixmap)
	painter.setRenderHint(QPainter.Antialiasing)#le dice que use Antialiasing
	scene.render(painter)#le dice a la escena que se dibuje sobre el painter
	painter.end()#termina el painter
	pixmap.save(str(CUENTA)+".png")#lo guarda, usa cuenta que va contando de 0, 1, 2 etc
	CUENTA += 1

class View(QGraphicsView):#creo una clase solo para poder implementar el keyPressEvent
	def keyPressEvent(self, event):
		print("key")
		if event.key() == Qt.Key_P:
			print("lololol")
			grabar()
			
#crea la app, la ventana y el view y la escena
app = QApplication(sys.argv)
scene = QGraphicsScene()
#view = QGraphicsView(scene) #ni no usara la clase View con esto basta
view = View(scene)
view.show()


#dibuja algo en la escena
#rectangulo de 100x200 en 0,0 borde negro relleno verde
scene.addRect(QRectF(0, 0, 100, 200), QPen(Qt.black), QBrush(Qt.green))

#ejecuta la app
sys.exit(app.exec_())