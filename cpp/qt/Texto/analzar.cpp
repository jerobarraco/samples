#include "analizar.h"
Analizar::Analizar()
{
    tabla = NULL;
}

Analizar::~Analizar(){
}

void Analizar::setString(QString pstr){
    this->texto = pstr;
}

int Analizar::getCantLetras()
{
    int cant=0;

    for (int i=0; i< texto.size();i++){
        QChar c = texto.at(i);
        if (c.isLetter())
            cant++;
    }
    return cant;
}

void Analizar::setTabla(QTableWidget *ptbl)
{
    tabla = ptbl;
}

void Analizar::procesar()
{
    if (tabla == NULL){ return;}
    int cant = getCantLetras();

    tabla->setColumnCount(2);
    tabla->setRowCount(2);

    QTableWidgetItem *titulo = new QTableWidgetItem("Cantidad de letras");
    tabla->setItem(0,0, titulo);

    QTableWidgetItem *valor = new QTableWidgetItem(QString::number(cant));
    tabla->setItem(0,1, valor);


    int cantv = getCantVocales();
    QTableWidgetItem *tit2 = new QTableWidgetItem("Cantidad de vocales");
    tabla->setItem(1, 0, tit2);

    QTableWidgetItem *val2 = new QTableWidgetItem(QString::number(cantv));
    tabla->setItem(1, 1, val2);

    calcularVocales();
}

int Analizar::getCantVocales()
{
    int cant=0;

    for (int i=0; i< texto.size();i++){
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
    for (int i = 0 ; i<5; i++){
        QChar vocal = QChar(vocales[i]);
        cont = 0;
        for (int j = 0 ; j<texto.size();j++){
            if (texto.at(j)==vocal){
                cont++;
            }
        }
        int cFilas = tabla->rowCount();
        tabla->setRowCount(cFilas+1);
        tabla->setItem(cFilas, 0, new QTableWidgetItem(vocal));
        tabla->setItem(cFilas, 1, new QTableWidgetItem(QString::number(cont)));
    }
}
