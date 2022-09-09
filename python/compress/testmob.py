import os
os.chdir('/storage/emulated/0/qpython/projects3/lzmj')
import sys
sys.path.insert(0, os.getcwd())

arg = sys.argv
#arg.extend(('a', 'compnotes.txt',	'compnotes.jz'))
arg.extend(('a', 'plotinus.jpg',	'plotinus.jpg.jz'))

def SRFile(fname='', s=1):
    f = open(fname, 'rb')
    def read():
        return f.read(s)
    return iter(read, b'')
    # this is not much faster and it doesnt close the file immediately
def test():    
    for p in SRFile(arg[2]):
        print (p)

def SRFile2(fname, s=1):
    f = open(fname, 'rb')
    while True:
        c = f.read(s)
        if c == b'': break
        yield c
    f.close()

def test2():
    for p in SRFile2(arg[2]):
        print (p)
    
#import cProfile
#cProfile.run('test()')
#cProfile.run('test2()')
#exit()

#f = open(sys.argv[2], 'rb')
#it = (t for t in f.read(1) if t!=b'' and t is not None )
#for t in iter(f.read, None):
#for t in it:
#    print (t)
    
#print ('done')
#exit()


import main
main.main()

print('---')
arg[1] ='x'
arg[2] = 'compnotes.jz'
arg[3] ='compnotes.2.txt'

#arg[2] = 'plotinus.jpg.jz'
#arg[3] ='plotinus.2.jpg'
main.main()