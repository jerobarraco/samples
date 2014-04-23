#!/bin/bash
source /home/nande/BB/ndk/bbndk-env_10_2_0_1155.sh
rm milib.so
rm avg.o
qcc -Vgcc_ntox86 -shared -fPIC -c avg.c
qcc -Vgcc_ntox86 -shared -o milib.so avg.o