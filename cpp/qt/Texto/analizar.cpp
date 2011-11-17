#include "analizar.h"

Analizar::Analizar(){
                    tabla = NULL;
                    }

Analizar::~Analizar(){}

void Analizar::setString(QString texto){
                                      this->texto = texto.toLower();
                                      }

int Analizar::getCantLetras()
            {
            int cant=0;

            for(int i=0;i<texto.size();i++){
                                           QChar c = texto.at(i);

                                           if (c.isLetterOrNumber()){
                                                                    cant++;
                                                                    }
                                           }
            return cant;
            }

void Analizar::setTabla(QTableWidget *ptbl){
                                           tabla = ptbl;
                                           }

void Analizar::procesar()
    {
    if (tabla == NULL){ return;}
    int cant = getCantLetras();
//en ves de dos columnas coloque tres agrege una para los porcentajes
    tabla->setColumnCount(3);
    tabla->setRowCount(2);

    tabla->setItem(0,0,new QTableWidgetItem("Cantidad de Caracteres"));

    tabla->setItem(0,1, new QTableWidgetItem(QString::number(cant)));

    int cantv = getCantVocales();

    tabla->setItem(1, 0, new QTableWidgetItem("Cantidad de vocales"));

    tabla->setItem(1, 1, new QTableWidgetItem(QString::number(cantv)));

    calcularVocales();
    }

int Analizar::getCantVocales()
    {
    int cant=0;

    for(int i=0;i<texto.size();i++){
                                   QChar c = texto.at(i);

                                   switch (c.toLower().toAscii()){
                                                                 case 'a':
                                                                 case 'e':
                                                                 case 'i':
                                                                 case 'o':
                                                                 case 'u':
                                                                         cant++;
                                                                         break;

                                                                 default: break;
                                                                 }

                                   }

    return cant;
    }

void Analizar::calcularVocales()
    {
    char vocales[5] = {'a','e','i','o','u'};
    int cont;
    float porc;

    for (int i=0;i<5;i++)
        {
        cont = 0;

        for (int j=0;j<texto.size();j++){
                                        if (texto.at(j)==QChar(vocales[i])){
                                                                           cont++;
                                                                           }

                                        int cantv = getCantVocales();

                                        porc = (cont/(float)cantv)*100;
                                        }

        int cFilas = tabla->rowCount();

        tabla->setRowCount(cFilas+1);
        tabla->setItem(cFilas, 0, new QTableWidgetItem(QChar(vocales[i])));
        tabla->setItem(cFilas, 1, new QTableWidgetItem(QString::number(cont)));        
        tabla->setItem(cFilas, 2, new QTableWidgetItem(QString::number(porc) + " %" ));
        }
    }
