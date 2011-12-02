#include <QString>
#include <QStringList>

#include "stdio.h"
#ifndef PERSONA_H
#define PERSONA_H

class Persona
{
public:
    Persona();
    char nombre[50];
    char direccion[50];
    int numero;
    long dni;

    void setNombre (QString n){
        strcpy(nombre, n.toStdString().c_str());
    }
    void setDireccion(QString d){
        strcpy(direccion, d.toStdString().c_str());
    }
    Persona(QString linea){
        QStringList items = linea.split(",");
        numero = items.at(0).toInt();
        setNombre(items.at(1));
        dni = items.at(2).toInt();
        setDireccion(items.at(3));
    }
};

#endif // PERSONA_H
