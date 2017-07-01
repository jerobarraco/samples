import datetime
#with love from peter and nande

class TimedMemoize():
	def __init__(self, minutes=5, capacity=10):
		self.minutes = minutes
		self.capacity = capacity
		self.mem = {}
		
	def __call__(self, original_func):
		self.f = original_func
		def wrappee( *args, **kwargs):
			k = str((args, str(kwargs)))
			#str is actually not a good idea as if an object is an instance it wont compare the value
			#but if otherway we could end up holding a reference to an object
			now = datetime.datetime.now()
			if k in self.mem:
				timeout, val = self.mem[k]
				if timeout > now:
					return val
				#else
				print ("timedout, delenting")
				self.mem.pop(k)
			if len(self.mem)>self.capacity:
				print ("overcapacity, deleting some")
				self.mem.popitem()#do a search by time and pop the most near timeout if you want, i'm lazy
			#if not valid value then
			nval = self.f(*args, **kwargs)
			print ("Storing")
			self.mem[k] = (now+datetime.timedelta(minutes=self.minutes), nval)
			return nval
		return wrappee
	
if __name__=='__main__':
	import random
	@TimedMemoize(1, capacity=2)
	def Test(param):
		v = random.random()
		print ("testing %s -> %s"%(param,v))
		return v
	v = 1
	print ("input values, 0 to exit")
	while v>0:
		v = int(input(">>>"))	
		print ("Got", Test(v))