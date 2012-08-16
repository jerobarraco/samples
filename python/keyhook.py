import time
from ctypes import *
u32 = windll.user32
continuar = True

LLFUNC = CFUNCTYPE(c_long, c_int, c_uint, c_long)

def pMyLowLevelHook(code, wparam, lparam):
	print "keypress"
	u32.CallNextHookEx(code, wparam, lparam)
	
MyLowLevelHook = LLFUNC(pMyLowLevelHook)
hHook = None
WH_KEYBOARD_LL = 13 #there's a way to get constants.. but i forgot it
hHook = u32.SetWindowsHookExA( WH_KEYBOARD_LL, MyLowLevelHook, None, 0 )
print repr(hHook)
while continuar:
	time.sleep(1)
print repr(u32.UnhookWindowsHookEx(hHook))