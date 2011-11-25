#include "monitor.h"
#include <QStringList>

monitor::monitor()
{
}
monitor::monitor(QString detalle)
{
    QStringList qsl = detalle.split(";");
    pulgadas = qsl.at(0).toDouble();
    precio = qsl.at(1).toDouble();
}
