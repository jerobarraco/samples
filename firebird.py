import sqlalchemy as sa
import sqlalchemy.orm as orm
import firebirdsql as fb
from sqlalchemy import Column, Integer, String, Sequence
from sqlalchemy.ext.declarative import declarative_base
Base = declarative_base()

#b = fb.connect("localhost/3050:c:\\lab4.fdb", 'sysdba',  'masterkey')
#cur = b.cursor()
#cur.execute ('select * from articulos')
#cur.fetchall()
#cur.close()
#b.close()

#b = fb.connect("localhost/3050:c:\\Svn\\pysnipps\\Java\\lab4\\Bd_guia1.FDB", 'sysdba', 'masterkey', charset="iso8859_1")

engine = sa.create_engine('firebird://SYSDBA:masterkey@localhost:3050/c:\\l4db3.fdb')
SM = orm.sessionmaker(bind=engine)


sess = SM()

class User(Base):
	__tablename__='users'
	id = Column(Integer, Sequence('user_id_seq'), primary_key=True)
	name = Column(String)

ed = User()
ed.name = 'John'
sess.add(ed)
sess.commit()
