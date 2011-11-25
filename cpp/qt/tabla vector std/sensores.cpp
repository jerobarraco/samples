#include "sensores.h"
#include <QMessageBox>
#include <vector>
using namespace std;

Sensores::Sensores()
{
    datos = vector< vector<int> >(500, vector<int>(500, 0 ) );
}

void Sensores::Generar()
{
    vector< vector<int> >::iterator i;
    vector<int>::iterator j;
    for (i=datos.begin(); i!= datos.end(); i++ ){
        *i = vector<int>(500, 0);
        for (j = i->begin(); j!= i->end(); j++){
            *j = rand()%101 ;
        }
    }


    /*datos = vector< vector<int> > ();

      datos.push_back();
    for(int i=0 ; i<500 ; i++){
        datos.push_back(vector<int>());

        for(int j=0 ; j<500 ; j++){
            datos[i].push_back(rand%101);
        }
    }*/
    /*
    for(int i=0 ; i<500 ; i++){
        for(int j=0 ; j<500 ; j++){
            datos[i][j]= rand()%101;
        }
    }*/
}

QString Sensores::Mostrar(QTableWidget *t)
{
    tabla = t;

    int acum=0;
    float prom=0;
    int CantFil=1, CantCol=1;

    //tabla->setColumnCount(500);
    //tabla->setRowCount(500);

    for(int i=0 ; i<500 ; i++){//fila
        tabla->setRowCount(CantFil);
        for(int j=0 ; j<500 ; j++){//column
             tabla->setColumnCount(CantCol);
             tabla->setItem(CantFil-1, j,new QTableWidgetItem (QString::number((datos[i][j]))));
             acum+=datos[i][j];
             if(CantCol < 500 )
                CantCol ++;
         }
           CantFil++;
           //  CantFil =tabla->rowCount();

    }
    prom= acum/(float)250000;

    return QString::number(prom);

}

QString Sensores::promd()
{
    int acum2=0;

    for(int i=0 ; i<500 ; i++){
       acum2+=datos[i][i];
    }

    float prom2=0;
    prom2=acum2/(float)500;
    return QString::number(prom2);
}

QString Sensores::valor(int a, int b)
{
    this->x=a-1;
    this->y=b-1;
    if(x<0){
        QMessageBox msg;
        msg.setWindowTitle("Programa de Puntos");
        msg.setText("ERROR: No se a ingresado el valor de la fila a buscar");
        msg.addButton("Ok", QMessageBox::AcceptRole);
        msg.exec();
        return "";
    }
    else if(y<0){
        QMessageBox msg;
        msg.setWindowTitle("Programa de Puntos");
        msg.setText("ERROR: No se a ingresado el valor de la Columna a buscar");
        msg.addButton("Ok", QMessageBox::AcceptRole);
        msg.exec();
        return "";
    }
    else{
        return tabla->item(x, y)->text();
    }
}
