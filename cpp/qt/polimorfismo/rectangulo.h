#include "figura.h"
#ifndef RECTANGULO_H
#define RECTANGULO_H

class rectangulo : public figura
{
public:
    rectangulo();
    int area(){
        return x*y;
    }
};

#endif // RECTANGULO_H
