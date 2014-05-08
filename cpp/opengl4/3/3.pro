TEMPLATE = app
CONFIG += console
CONFIG -= app_bundle
CONFIG -= qt

SOURCES += main.cpp \
    gllog.cpp
#//We NEED glfw3 THREE! so we NEED to compile it ourselves

INCLUDEPATH += ../glfw-3.0.4/include
INCLUDEPATH += $$PWD/../glfw-3.0.4/include
DEPENDPATH += $$PWD/../glfw-3.0.4/include

unix:!macx: PRE_TARGETDEPS += $$PWD/../glfw-3.0.4/src/libglfw3.a
unix:!macx: LIBS += -L$$PWD/../glfw-3.0.4/src/ -lglfw3 -lGL -lGLEW -lX11 -lXxf86vm -lpthread  -lXrandr -lXi
#unix:!macx: LIBS += -lglfw3 -lGL -lGLEW -lX11 -lXxf86vm -lpthread  -lXrandr -lXi


HEADERS += \
    gllog.h
