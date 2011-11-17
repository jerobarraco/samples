#include <QString>
#include <QTableWidget>
#include <QTableWidgetItem>

#ifndef ANALIZAR_H
#define ANALIZAR_H

class Analizar
    {
    QString texto;
    QTableWidget *tabla;

    public:
        Analizar();
        ~Analizar();
        void setString(QString pstr);
        int getCantLetras();
        int getCantVocales();
        void calcularVocales();
        void setTabla(QTableWidget *ptbl);
        void procesar();
    };

#endif // ANALIZAR_H
