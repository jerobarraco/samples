#include <QString>

#ifndef PERSONA_H
#define PERSONA_H

class Persona
{

public:
    Persona();
    Persona(QString);
    QString toString();
    QString nombre;
    QString direccion;
    int numero;
    long dni;

};

#endif // PERSONA_H
