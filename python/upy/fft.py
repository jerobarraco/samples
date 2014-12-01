import math
def ffft(nums, forward=True, scale=False):
    n = len(nums)
    m = int(math.log2(n))
    #n= 2**m #Calculate the number of points
    #Do the bit reversal
    i2 = n >> 1 
    j = 0
    for i in range(n-1):
        a, b = nums[i], nums[j]
        if a>b: nums[i], nums[j] = b, a
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
    

a=list(range(4))
ffft(a)
print(a)
time(ffft,a)
