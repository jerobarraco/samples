# To change this template, choose Tools | Templates
# and open the template in the editor.

__author__="Administrador"
__date__ ="$30/03/2011 00:29:52$"
from javax.swing import *

if __name__ == "__main__":
	print "Hello World"
	frame =	JFrame('HEllo python!')
	label = JLabel('Hello python', JLabel.CENTER)
	frame.add(label)
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
	frame.setSize(300, 300)
	frame.show()