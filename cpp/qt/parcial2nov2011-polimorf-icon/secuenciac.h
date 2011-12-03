#include "secuencia.h"
#ifndef SECUENCIAC_H
#define SECUENCIAC_H

class secuenciac : public Secuencia
{
    int fil ;
public:
    secuenciac(int m, int n);
    void actualizarEstado();
    QString getNombre(){
        return "Filas";
    }
};

#endif // SECUENCIAC_H
