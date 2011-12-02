#include <QtCore/QCoreApplication>

#include "aeropuerto.h"
#include "avion.h"

int main(int argc, char *argv[])
{
    aeropuerto ezeiza, parana;
    avion b747(12), avioneta(4);

    //el 747 hace viajes de ezeiza a parana
    ezeiza.appendAvion(b747);
    parana.appendAvion(b747);

    //la avioneta es solo para paseos cortos en el radio del aeropuerto
    parana.appendAvion(avioneta);

}
