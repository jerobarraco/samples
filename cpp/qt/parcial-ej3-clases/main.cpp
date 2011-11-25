#include <QtCore/QCoreApplication>
#include "cpu.h"
#include "pc.h"
#include "monitor.h"
#include "kit.h"
#include "kitnormal.h"

#include "iostream"
int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);

    cpu uno (4000, 3, 150, 1500, "Pentium 9");
    cpu dos (4200, 3, 150, 1500 , "Amd algo");
    monitor muno;
    muno.precio = 500;
    muno.pulgadas = 14.2;

    monitor mdos = monitor("17.2;250.5");

    kitnormal mikit;
    mikit.precio = 50;

    pc mipc, mipc2;
    mipc.micpu = uno;
    mipc.mimonitor = mdos;
    mipc.mikit = mikit;


    std::cout << mipc.precio();
    return a.exec();

}
