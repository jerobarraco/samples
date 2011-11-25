#ifndef CPU_H
#define CPU_H
#include <QString>

class cpu
{
public:
    cpu();
    cpu(float fr, int r, int h, double p, QString n);
    float freq;
    int ram;
    int hd;
    double precio;
    QString nombre;
};

#endif // CPU_H
