TEMPLATE = app
CONFIG += console
CONFIG -= app_bundle
CONFIG -= qt
QMAKE_CFLAGS += -std=c99
QMAKE_CFLAGS += -DPATH_MAX
QMAKE_CFLAGS += -DM_PI=3.1415
QMAKE_CFLAGS += -DSSIZE_MAX=32767

QMAKE_CFLAGS += -DUNIX
#QMAKE_CFLAGS += -ansi
INCLUDEPATH += /home/nande/dev/tests/micropython-master/unix
INCLUDEPATH += /home/nande/dev/tests/micropython-master/py
INCLUDEPATH += /home/nande/dev/tests/micropython-master/unix/build

SOURCES += main.cpp \
    ../../../../tests/micropython-master/py/malloc.c \
    ../../../../tests/micropython-master/py/gc.c \
    ../../../../tests/micropython-master/py/obj.c \
    ../../../../tests/micropython-master/py/objnone.c \
    ../../../../tests/micropython-master/py/lexerunix.c \
    ../../../../tests/micropython-master/py/lexer.c \
    ../../../../tests/micropython-master/py/parse.c \
    ../../../../tests/micropython-master/py/parsehelper.c \
    ../../../../tests/micropython-master/py/compile.c \
    ../../../../tests/micropython-master/py/qstr.c \
    ../../../../tests/micropython-master/py/runtime.c \
    ../../../../tests/micropython-master/py/repl.c \
    ../../../../tests/micropython-master/py/stackctrl.c \
    ../../../../tests/micropython-master/py/argcheck.c \
    ../../../../tests/micropython-master/py/asmarm.c \
    ../../../../tests/micropython-master/py/asmthumb.c \
    ../../../../tests/micropython-master/py/asmx64.c \
    ../../../../tests/micropython-master/py/asmx86.c \
    ../../../../tests/micropython-master/py/bc.c \
    ../../../../tests/micropython-master/py/binary.c \
    ../../../../tests/micropython-master/py/builtin.c \
    ../../../../tests/micropython-master/py/builtinevex.c \
    ../../../../tests/micropython-master/py/builtinimport.c \
    ../../../../tests/micropython-master/py/builtintables.c \
    ../../../../tests/micropython-master/py/emitbc.c \
    ../../../../tests/micropython-master/py/emitcommon.c \
    ../../../../tests/micropython-master/py/emitcpy.c \
    ../../../../tests/micropython-master/py/emitglue.c \
    ../../../../tests/micropython-master/py/emitinlinethumb.c \
    ../../../../tests/micropython-master/py/emitnative.c \
    ../../../../tests/micropython-master/py/emitpass1.c \
    ../../../../tests/micropython-master/py/formatfloat.c \
    ../../../../tests/micropython-master/py/lexerstr.c \
    ../../../../tests/micropython-master/py/map.c \
    ../../../../tests/micropython-master/py/modarray.c \
    ../../../../tests/micropython-master/py/modcmath.c \
    ../../../../tests/micropython-master/py/modcollections.c \
    ../../../../tests/micropython-master/py/modgc.c \
    ../../../../tests/micropython-master/py/modio.c \
    ../../../../tests/micropython-master/py/modmath.c \
    ../../../../tests/micropython-master/py/modmicropython.c \
    ../../../../tests/micropython-master/py/modstruct.c \
    ../../../../tests/micropython-master/py/modsys.c \
    ../../../../tests/micropython-master/py/mpz.c \
    ../../../../tests/micropython-master/py/nativeglue.c \
    ../../../../tests/micropython-master/py/nlrsetjmp.c \
    ../../../../tests/micropython-master/py/objarray.c \
    ../../../../tests/micropython-master/py/objbool.c \
    ../../../../tests/micropython-master/py/objboundmeth.c \
    ../../../../tests/micropython-master/py/objcell.c \
    ../../../../tests/micropython-master/py/objclosure.c \
    ../../../../tests/micropython-master/py/objcomplex.c \
    ../../../../tests/micropython-master/py/objdict.c \
    ../../../../tests/micropython-master/py/objenumerate.c \
    ../../../../tests/micropython-master/py/objexcept.c \
    ../../../../tests/micropython-master/py/objfilter.c \
    ../../../../tests/micropython-master/py/objfloat.c \
    ../../../../tests/micropython-master/py/objfun.c \
    ../../../../tests/micropython-master/py/objgenerator.c \
    ../../../../tests/micropython-master/py/objgetitemiter.c \
    ../../../../tests/micropython-master/py/objint_longlong.c \
    ../../../../tests/micropython-master/py/objint_mpz.c \
    ../../../../tests/micropython-master/py/objint.c \
    ../../../../tests/micropython-master/py/objlist.c \
    ../../../../tests/micropython-master/py/objmap.c \
    ../../../../tests/micropython-master/py/objmodule.c \
    ../../../../tests/micropython-master/py/objnamedtuple.c \
    ../../../../tests/micropython-master/py/objobject.c \
    ../../../../tests/micropython-master/py/objproperty.c \
    ../../../../tests/micropython-master/py/objrange.c \
    ../../../../tests/micropython-master/py/objreversed.c \
    ../../../../tests/micropython-master/py/objset.c \
    ../../../../tests/micropython-master/py/objslice.c \
    ../../../../tests/micropython-master/py/objstr.c \
    ../../../../tests/micropython-master/py/objstringio.c \
    ../../../../tests/micropython-master/py/objstrunicode.c \
    ../../../../tests/micropython-master/py/objtuple.c \
    ../../../../tests/micropython-master/py/objtype.c \
    ../../../../tests/micropython-master/py/objzip.c \
    ../../../../tests/micropython-master/py/opmethods.c \
    ../../../../tests/micropython-master/py/parsenum.c \
    ../../../../tests/micropython-master/py/parsenumbase.c \
    ../../../../tests/micropython-master/py/pfenv_printf.c \
    ../../../../tests/micropython-master/py/pfenv.c \
    ../../../../tests/micropython-master/py/scope.c \
    ../../../../tests/micropython-master/py/sequence.c \
    ../../../../tests/micropython-master/py/showbc.c \
    ../../../../tests/micropython-master/py/smallint.c \
    ../../../../tests/micropython-master/py/stream.c \
    ../../../../tests/micropython-master/py/unicode.c \
    ../../../../tests/micropython-master/py/vm.c \
    ../../../../tests/micropython-master/py/vstr.c
LIBS += -L/home/nande/dev/tests/micropython-master/unix/build
LIBS += -L/home/nande/dev/tests/micropython-master/unix/build/py

#LIBS += qstr.o

#LIBS += /home/nande/dev/tests/micropython-master/unix/build/py/qstr.o
#LIBS += /home/nande/dev/tests/micropython-master/unix/build/py/obj.o
#LIBS += /home/nande/dev/tests/micropython-master/unix/build/py/objnone.o
#LIBS += /home/nande/dev/tests/micropython-master/unix/build/py/objnone.o

OBJECTS_DIR += /home/nande/dev/tests/micropython-master/unix/build
#OBJECTS_DIR += /home/nande/dev/tests/micropython-master/unix/build/py
#OBJECTS_DIR += /home/nande/dev/tests/micropython-master/unix/build
#DEPENDPATH += /home/nande/dev/tests/micropython-master/unix/build/py
include(deployment.pri)
qtcAddDeployment()

HEADERS += \
    ../../../../tests/micropython-master/unix/input.h \
    ../../../../tests/micropython-master/unix/mpconfigport.h \
    ../../../../tests/micropython-master/py/obj.h \
    ../../../../tests/micropython-master/py/lexerunix.h \
    ../../../../tests/micropython-master/py/lexer.h \
    ../../../../tests/micropython-master/py/parsehelper.h \
    ../../../../tests/micropython-master/py/compile.h \
    ../../../../tests/micropython-master/py/nlr.h \
    ../../../../tests/micropython-master/py/qstr.h \
    ../../../../tests/micropython-master/py/runtime.h \
    ../../../../tests/micropython-master/py/repl.h \
    ../../../../tests/micropython-master/py/stackctrl.h \
    ../../../../tests/micropython-master/py/runtime0.h \
    ../../../../tests/micropython-master/py/asmarm.h \
    ../../../../tests/micropython-master/py/asmthumb.h \
    ../../../../tests/micropython-master/py/asmx64.h \
    ../../../../tests/micropython-master/py/asmx86.h \
    ../../../../tests/micropython-master/py/bc.h \
    ../../../../tests/micropython-master/py/bc0.h \
    ../../../../tests/micropython-master/py/binary.h \
    ../../../../tests/micropython-master/py/builtin.h \
    ../../../../tests/micropython-master/py/builtintables.h \
    ../../../../tests/micropython-master/py/emit.h \
    ../../../../tests/micropython-master/py/emitglue.h \
    ../../../../tests/micropython-master/py/formatfloat.h \
    ../../../../tests/micropython-master/py/gc.h \
    ../../../../tests/micropython-master/py/grammar.h \
    ../../../../tests/micropython-master/py/misc.h \
    ../../../../tests/micropython-master/py/mpconfig.h \
    ../../../../tests/micropython-master/py/mpz.h \
    ../../../../tests/micropython-master/py/objarray.h \
    ../../../../tests/micropython-master/py/objfun.h \
    ../../../../tests/micropython-master/py/objgenerator.h \
    ../../../../tests/micropython-master/py/objint.h \
    ../../../../tests/micropython-master/py/objlist.h \
    ../../../../tests/micropython-master/py/objmodule.h \
    ../../../../tests/micropython-master/py/objstr.h \
    ../../../../tests/micropython-master/py/objtuple.h \
    ../../../../tests/micropython-master/py/objtype.h \
    ../../../../tests/micropython-master/py/parse.h \
    ../../../../tests/micropython-master/py/parsenum.h \
    ../../../../tests/micropython-master/py/parsenumbase.h \
    ../../../../tests/micropython-master/py/pfenv.h \
    ../../../../tests/micropython-master/py/qstrdefs.h \
    ../../../../tests/micropython-master/py/scope.h \
    ../../../../tests/micropython-master/py/smallint.h \
    ../../../../tests/micropython-master/py/stream.h \
    ../../../../tests/micropython-master/py/unicode.h \
    ../../../../tests/micropython-master/py/vmentrytable.h

OTHER_FILES += \
    ../../../../tests/micropython-master/py/py-version.sh \
    ../../../../tests/micropython-master/py/nlrthumb.S \
    ../../../../tests/micropython-master/py/nlrx64.S \
    ../../../../tests/micropython-master/py/nlrx86.S \
    ../../../../tests/micropython-master/py/mkenv.mk \
    ../../../../tests/micropython-master/py/mkrules.mk \
    ../../../../tests/micropython-master/py/py.mk \
    ../../../../tests/micropython-master/py/makeqstrdata.py

