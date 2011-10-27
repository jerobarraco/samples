import firebirdsql as fb
con = None
def connect(url="localhost/3050:c:\\Svn\\pysnipps\\Java\\lab4\\Bd_guia1.FDB"):
	global con
	con = fb.connect(url, 'sysdba', 'masterkey', charset="iso8859_1")

def run (q):
	try:
		cur = con.cursor()
		cur.execute(q)
		try:
			for i in cur.rows:
				print i
		except:
			pass
		cur.close()
		con.commit()
	except Exception , e:
		print e
		print "rollingback"
		con.rollback()