TEMPLATE = app
CONFIG += console
CONFIG -= app_bundle
CONFIG -= qt

SOURCES += main.cpp \
    auto.cpp \
    rueda.cpp \
    motor.cpp \
    audia3.cpp

include(deployment.pri)
qtcAddDeployment()

HEADERS += \
    auto.h \
    rueda.h \
    motor.h \
    audia3.h

