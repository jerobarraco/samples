TEMPLATE = app
CONFIG += console
CONFIG -= app_bundle
CONFIG -= qt

SOURCES += main.cpp
INCLUDEPATH += ../glfw-3.0.4/include

unix:!macx: LIBS += -L$$PWD/../glfw-3.0.4/src/ -lglfw3 -lGL -lGLEW -lX11 -lXxf86vm -lpthread  -lXrandr -lXi
INCLUDEPATH += $$PWD/../glfw-3.0.4/include
DEPENDPATH += $$PWD/../glfw-3.0.4/include

unix:!macx: PRE_TARGETDEPS += $$PWD/../glfw-3.0.4/src/libglfw3.a
