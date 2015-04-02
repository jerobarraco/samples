micropython = __import__("micropython")
@micropython.asm_thumb
def fun():
	movw(r0, 42)
print(fun())