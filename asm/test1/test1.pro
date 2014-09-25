TEMPLATE = app
#CONFIG += console
CONFIG -= app_bundle
CONFIG -= qt

SOURCES +=

include(deployment.pri)
qtcAddDeployment()

OTHER_FILES += \
    main.asm \
    main2.asm \
    asm3.asm

