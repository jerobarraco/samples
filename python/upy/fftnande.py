import math
#@micropython.bytecode
def time (f, *args):
    ta=pyb.millis()
    r = f(*args)
    tb =pyb.millis()
    tf = tb-ta
    print('lasted %sms'%tf)
    return r



import math
me = math.e
c = 299792458
#@micropython.native
def fft(x):
    n = len(x)
    if n < 2: return x
    k = -2j*math.pi/n
    other = ( me**(k*s)*o for s, o in enumerate(fft(x[1::2])) )
    data = tuple(zip( fft(x[::2]), other ))
    return tuple(e+o for e, o in data) + tuple( e-o for e, o in data)

#a=time(fft, list(range(256)))
ll = list(range(128))
a=fft(ll)
print(a)
		
def fft(x):
    n = len(x)
    if n <= 1: return x
    even = fft([x[i] for i in range(0,n,2)])
    odd = fft([x[i] for i in range(1,n,2)])
    c = lambda m: math.e**(-2j*math.pi*m/n)*odd[m]
    return [even[m] + c(m) for m in range(int(n/2))] + [even[m] - c(m) for m in range(int(n/2))]

#b=time(fft, list(range(256)))
#b=fft(list(range(512)))

'''
/*
   Modification of Paul Bourkes FFT code by Peter Cusack 
   to utilise the Microsoft complex type.

   This computes an in-place complex-to-complex FFT 
   x and y are the real and imaginary arrays of 2^m points.
   dir =  1 gives forward transform
   dir = -1 gives reverse transform 
*/
void FFT(int dir, long m, complex <double> x[])
{
   long i, i1, i2,j, k, l, l1, l2, n;
   complex <double> tx, t1, u, c;

   /*Calculate the number of points */
   n = 1;
   for(i = 0; i < m; i++) 
      n <<= 1;   

   /* Do the bit reversal */
   i2 = n >> 1;
   j = 0;

   for (i = 0; i < n-1 ; i++){
      if (i < j)
         swap(x[i], x[j]);

      k = i2;

      while (k <= j) 
	  {
         j -= k;
         k >>= 1;
      }

      j += k;
   }

   /* Compute the FFT */
   c.real(-1.0);
   c.imag(0.0);
   l2 = 1;
   for (l = 0; l < m; l++) 
   {
      l1 = l2;
      l2 <<= 1;
      u.real(1.0);
      u.imag(0.0);

      for (j = 0; j < l1; j++) 
	  {
         for (i = j; i < n; i += l2) 
		 {
            i1 = i + l1;
            t1 = u * x[i1];
            x[i1] = x[i] - t1; 
            x[i] += t1;
         }

         u = u * c;
      }

      c.imag(sqrt((1.0 - c.real()) / 2.0));
      if (dir == 1)
         c.imag(-c.imag());
      c.real(sqrt((1.0 + c.real()) / 2.0));
   }

   /* Scaling for forward transform */
   if (dir == 1) 
   {
      for (i = 0; i < n; i++)
         x[i] /= n;      
   }   
   return;
}
'''

def ffft(nums, forward=True, scale=False):
    n = len(nums)
    m = int(math.log2(n))
    #n= 2**m #Calculate the number of points
    #Do the bit reversal
    i2 = n >> 1 
    j = 0
    for i in range(n-1):
        if i<j: nums[i], nums[j] =  nums[j], nums[i]
        k = i2
        while (k <= j):
            j -= k
            k >>= 1
        j+=k
    #Compute the FFT
    c = 0j-1
    l2 = 1
    for l in range(m):
        l1 = l2
        l2 <<= 1
        u = 0j+1
        for j in range(l1):
            for i in range(j, n, l2):
                i1 = i+l1
                t1 = u*nums[i1]
                nums[i1] = nums[i] - t1
                nums[i] += t1
            u *= c
        ci = math.sqrt((1.0 - c.real) / 2.0)
        if forward: ci=-ci
        cr = math.sqrt((1.0 + c.real) / 2.0)
        c = cr + ci*1j#complex(cr,ci)
    # Scaling for forward transform 
    if (scale and forward):
        for i in range(n):
            nums[i] /= n 
    return nums
	

b = ffft(ll[:])
#time(ffft,a)
print(b)
