#include "secuencia.h"
#include <QString>
#ifndef SECUENCIAB_H
#define SECUENCIAB_H

class SecuenciaB : public Secuencia
{
    int col;
public:
    SecuenciaB(int m, int n);
    void actualizarEstado();
    QString getNombre(){
        return "Columnas impares";
    }
};

#endif // SECUENCIAB_H
