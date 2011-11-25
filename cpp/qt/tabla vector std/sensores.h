#ifndef SENSORES_H
#define SENSORES_H
#include <ctime>
#include <QTableWidget>
#include <QString>
#include <vector>

using namespace std;
class Sensores
{
    //int datos[500][500];
    vector< vector<int> > datos;
    QTableWidget *tabla;
    int x, y;
public:
    Sensores();
    void Generar();
    QString Mostrar(QTableWidget *t);
    QString promd();
    QString valor(int,int);
};

#endif // SENSORES_H
