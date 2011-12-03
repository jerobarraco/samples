#include "secuencia.h"
#include <QString>
#ifndef SECUENCIAA_H
#define SECUENCIAA_H

class SecuenciaA : public Secuencia
{
public:
    SecuenciaA(int m, int n);
    void actualizarEstado();
    QString getNombre(){
        return "Random";
    }
};

#endif // SECUENCIAA_H
