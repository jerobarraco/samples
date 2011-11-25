#include "cpu.h"
#include "monitor.h"
#include "kit.h"
#ifndef PC_H
#define PC_H

class pc
{
public:
    pc();
    double precio();
    cpu micpu;
    monitor mimonitor;
    kit mikit;
};

#endif // PC_H
