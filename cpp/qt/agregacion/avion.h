#include "ala.h"
#include "rueda.h"
#ifndef AVION_H
#define AVION_H

class avion
{
public:
    avion (int cant_ruedas=4);
    ~avion();
    //Composicion:
    //1� el ala hace a la identida del avion, o sea, el avion necesita alas
    //2� la vida de las alas esta determinada por la vida del avion
    //3� Cada ala, pertenece unicamente a este avion.
    ala ala_izq;
    ala ala_der;
    //a las alas no las borro _explicitamente_ porque al declararlas
    //como variables de clase, se crean y destruyen _automaticamente_
    //junto con la instancia de avion. Es el mejor caso de composicion


    //Esto tambien es composicion (ver constructor y destructor de avion)
    rueda *ruedas;

};

#endif // AVION_H
