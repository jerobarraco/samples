# -*- coding: utf-8 -*-
"""This module defines everything needed to use an object tree for any project and file handling.
It should only take care of handling data and xml, not on particular implementation of xml"""

from xml.sax import make_parser, ContentHandler, ErrorHandler

HEADER=u"""<?xml version="1.0" encoding="UTF-8"?>"""

class Empty:
	def __init__(self):
		self._value=''
		self._parent = None

def setlistitem(list, item ):
	if item not in list : list.append(item)
		
def normalize_whitespace(text):
	"Remove redundant whitespace from a string"
	return ' '.join(text.split())

class cHandler(ContentHandler, ErrorHandler):
	def __init__(self):
		self.__curobj = self.__object = Empty()

	#Various
	def GetObject(self):		return self.__object

	#Content Handling
	def startElement(self, name, attrs):
		"""This method is called whenever a element starts being parsed.
		name is the item where it belongs. attrs is the attributes."""
		if not (name in self.__curobj.__dict__ ):
			self.__curobj.__dict__[name] = []
		
		new = Empty()
		new.__dict__.update(attrs)
		new._parent = self.__curobj
		self.__curobj.__dict__[name].append(new)
		self.__curobj = new

	def endElement(self, name):
		self.__curobj._value = normalize_whitespace(self.__curobj._value)
		self.__curobj = self.__curobj._parent

	def characters(self, ch):
		"The characters() method is called for characters that aren't inside XML tags"
		#Forget about the default value, i simply hate it :D
		self.__curobj._value+=ch

	def startElementNS(self, (uri, localname), qname, attrs):
		"""Called if using namespace, though im not intrested right now"""
		pass

	def endElementNS(self,  (uri, localname, qname)):
		pass

	#ErrorHandling
	def error(self, exception):
		"Handle a recoverable error."
		print exception

	def fatalError(self, exception):
		"Handle a non-recoverable error."
		print exception

	def warning(self, exception):
		"Handle a warning."
		print exception

class cXml_Writer:
	def __init__(self):
		pass

	def __PrintItem(self, name,  object, level):
		"""Recursive Magic!"""
		self.__Write(u"	"*level)
		self.__Write(u"<"+ name)
		childs = []
		for (key,  val) in object.__dict__.items():
			if key[0] != '_':
				if type(val) == list:
					childs.append((key,  val))
				else : 
					self.__Write(u' '+ key + u'="' + val + u'"')
	
		if len(childs) ==0:
			self.__Write(u"/>\n")
		else:
			self.__Write( u">\n")
			for (name,  child) in childs:
				for item in child:
					self.__PrintItem(name,  item, level+1)
			self.__Write((u"	"*level) + u"</" + name + u">\n")
		
	def __Write(self,  string):
		self.__file.write(string.encode("UTF8"))

	def Save(self, object, fname=""):
		self.__file = open(fname, "w")
		self.__Write(HEADER)
		self.__Write(u'\n\n')
		self.__PrintItem(object._name,  object, 0 )
		self.__file.close()
		return None

class Dao:
	"""A class to manipulate the objects tree in a project.
	allows a transparent use of python object, and then
	you can save to or load it from an xml."""
	def __init__(self, rootTag,  fname=""):
		if fname :	self.LoadFromFile(fname)
		else:
			self.__object=None
			self.NewObject(rootTag)

	def LoadFromFile(self, fname=""):
		"""This method loads an object from a file in the xml format,
		fname can be a string with the file path, or a file object
		and returns that object"""
		if fname : self.__filename = fname
		if not self.__filename : return ("No file name provided",)

		self.__parser = make_parser()
		#self.__parser.setFeature(saxutils.feature_namespaces, False)
		handler = cHandler()
		#Is better for me to create a handler each time a file is parsed
		#so i can reuse the instance of Dao, (and handler will return a clean object
		# each time.)
		self.__parser.setContentHandler(handler)
		self.__parser.setErrorHandler(handler)
		self.__parser.parse(self.__filename)
		self.__object = handler.GetObject().proyecto[0]
		self.__object._name = 'proyecto'
		return self.__object

	def SaveToFile(self, fname=""):
		"""This method Saves an object to a file in the xml format,
		it will return false in case the filename is not set and the function is called without a filename.
		or it will return the state returned by writer.Save"""
		if fname == "":
			if self.__fname == "" :
				return False
		else :
			self.__fname=fname

		writer = cXml_Writer()
		ret = writer.Save(self.__object, self.__fname)
		if ret: print ret
		return ret

	#Due to the nature of python , and due to the fact that i specially choosed to use python builtin types and
	#functions, is not necesary at all that i provide an interface for modifing the object
	#The user just has to keep the structure,

	def NewObject(self,  tag):
		"""Creates a new object, and deletes any data of the object before, (if any)"""
		del self.__object
		self.__fname = ""
		self.__object = Empty()
		return self.__object

	def GetFileName(self):
		return self.__fname

	def Root(self):
		return self.__object


if __name__=='__main__':
	c = Dao('proyecto', '/home/nande/Dev/Svn/kafx/trunks/example.xml')
	c.SaveToFile('/home/nande/test.xml')
