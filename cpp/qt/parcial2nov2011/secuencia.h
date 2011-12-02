#include "panel.h"
#include <QString>

#ifndef SECUENCIA_H
#define SECUENCIA_H

class Secuencia
{
protected:
    Panel *panel;
    int m, n;
    void apagarTodo();
public:

    Secuencia(int m, int n);
    ~Secuencia();
    bool encendido(int i, int j);
    virtual void actualizarEstado()=0;
    virtual QString getNombre(){
        return "Base";
    }
};

#endif // SECUENCIA_H
