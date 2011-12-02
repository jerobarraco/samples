#include <QList>
#include "avion.h"
#include <vector>
#ifndef AEROPUERTO_H
#define AEROPUERTO_H

class aeropuerto
{
public:
    aeropuerto();
    //Esto seria una agregacion, porque
    //El avion pertenece al aeropuerto
    //la vida de los aviones no depende del aeropuerto
    //Y un avion puede pertenecer a varios aeropuertos
    std::vector<avion> aviones;
    void appendAvion(avion a){
        aviones.push_back(a);
    }
};

#endif // AEROPUERTO_H
