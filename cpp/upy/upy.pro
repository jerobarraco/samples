TEMPLATE = app
CONFIG += console
CONFIG -= app_bundle
CONFIG -= qt
SOURCES += main.cpp

include(deployment.pri)
qtcAddDeployment()

#INCLUDEPATH += ../../../tests/micropython-master/
INCLUDEPATH += ../../../tests/micropython-master/py
INCLUDEPATH += ../../../tests/micropython-master/unix
INCLUDEPATH += ../../../tests/micropython-master/unix/build
