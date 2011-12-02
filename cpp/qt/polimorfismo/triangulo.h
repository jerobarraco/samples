#include "figura.h"
#ifndef TRIANGULO_H
#define TRIANGULO_H

class triangulo : public figura
{
public:
    triangulo();
    int area (){
        return x * 0.5 * y;
    }
};

#endif // TRIANGULO_H
