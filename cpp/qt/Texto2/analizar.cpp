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

void Analizar::procesar()
    {
    if (tabla == NULL){
                      return;
                      }

    tabla->setColumnCount(1);
    tabla->setRowCount(0);
    int cont=0,pos=0;

    QChar c;

    QString tex;

    for (int i=0;i<texto.size();i++)
        {
            c = texto.at(i);

            if (c.isSpace() || i==(texto.size()-1) ){
                if (i==texto.size()-1) cont++;
                tex = texto.mid(pos, cont);//si usas i-pos, el cont es innecesario

                int cFilas = tabla->rowCount();

                tabla->setRowCount(cFilas+1);
                tabla->setItem(cFilas, 0, new QTableWidgetItem(tex));

                pos = i+1;
                cont = 0;
            }else
                cont++;
        }
    }


QStringList Analizar::procesar2(){
    QStringList retorno = texto.split(" ");
    return retorno;

}
