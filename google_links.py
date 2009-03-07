# encoding: utf-8
from BeautifulSoup import BeautifulSoup
import re, httplib,  urllib,  sys
global con

print('iniciando')
def GetPage(url, params={}):
	q = url
	if params :
		q += '?'+ urllib.urlencode(params)
	print('pidiendo la pagina '+ q)
	con.request('GET', 	q)
	print('leyendola')
	return con.getresponse().read()

def GetLinks(soup):
	# return ref.get('href') for ref in soupt.find('div',  {'class':'med'}).findAll('a')
	print('buscando links en la sopa')
	d = soup.find('div',  {'class':'med'})
	return [ ref.get('href')  for ref in d.findAll('a')]
def GetNextQuery(soup):
	print('buscando el boton siguiente en la sopa')
	t = soup.findAll('td',  {'class':'b'})
	if (len(t)<2) or (not t[-1].a) : return None
	return t[-1].a.get('href')

query = raw_input("Texto a buscar: ").encode('utf-8')
#query = "site:megaupload.com ultrastar"
# GET method
# http://pleac.sourceforge.net/pleac_python/webautomation.html <<< xie xie ni
lfile= open('links.txt',  'w')
print('conectando a google')
con = httplib.HTTPConnection('www.google.com')
page = GetPage('/search',  {'q':query})

while True:
	print('parseando el html')
	html = BeautifulSoup(page)
	print 'escribiendo links en archivo'
	for link in GetLinks(html):
		sys.stdout.write('.')
		lfile.write(link.encode('utf-8')+"\n")
	print ''
	next = GetNextQuery(html)
	if not next :
		print('no hay nada mas, chau, termine')
		lfile.close()
		exit()
	page = GetPage(next)
