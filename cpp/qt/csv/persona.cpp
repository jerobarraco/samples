#include <QStringList>
#include <QString>

#include "persona.h"

Persona::Persona()
{
}
Persona::Persona(QString linea){
    QStringList valores = linea.split(",");
    numero = valores.at(0).toInt();
    nombre = valores.at(1);
    dni = valores.at(2).toLong();
    direccion = valores.at(3);
}

QString Persona::toString(){
    QStringList res;
    res.append(QString::number(numero));
    res.append(nombre);
    res.append(QString::number(dni));
    res.append(direccion);
    return res.join(",");
}
