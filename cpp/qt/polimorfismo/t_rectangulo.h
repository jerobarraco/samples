#include "triangulo.h"
#ifndef T_RECTANGULO_H
#define T_RECTANGULO_H

class t_rectangulo: public triangulo
{
public:
    t_rectangulo();
    //el virtual en la clase figura, afecta tambie a la clase t_rectangulo
    int area(){
        //suponer que es escencialmente diferente
        return (x*y)/3;
    }
};

#endif // T_RECTANGULO_H
