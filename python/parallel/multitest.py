import urllib2

def f(x):
	try:
		req = urllib2.urlopen(x)
		return req.getcode()
	except:
		return 0
	
	
def go():
	from multiprocessing import Pool
	p = Pool(processes=100)
	with open('urls.txt') as ff:
		vector = [url.strip() for url in ff.readlines()]
	result = p.map_async(f, vector)
	r = result.get(timeout=100)
	print (len(r)), r[:5]
	
if __name__=='__main__':
	go()