#include "pc.h"

pc::pc()
{
}

double pc::precio()
{
    return micpu.precio + mimonitor.precio + mikit.precio;
}
