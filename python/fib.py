def fib(i):
	if i ==0: return 0
	if i ==1: return 1
	r = fib(i-1) + fib(i-2)
	#print(r)
	return r

for i in range (10): 
	print (fib(i))
