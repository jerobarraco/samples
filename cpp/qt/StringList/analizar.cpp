#include "analizar.h"

Analizar::Analizar(){
                    tabla = NULL;
                    }

Analizar::~Analizar(){}

void Analizar::setString(QString texto){
                                       this->texto = texto.toLower();
                                       }

void Analizar::setTabla(QTableWidget *ptbl){
                                           tabla = ptbl;
}

QStringList Analizar::procesar()
{
    QStringList retorno = texto.split(" ");

    return retorno;
}
