#include "mainwindow.h"
#include "ui_mainwindow.h"
void agregar(nodo* &pini, int idProc, int prio){
    nodo *ant=NULL, *act=NULL, *nuevo;

    //1º creamos el nodo
    nuevo = new nodo;
    nuevo->idProc = idProc;
    nuevo->prio = prio;
    nuevo->sig = NULL;
    //(*nuevo).idProc // no hay que olvidarse que la flechita indica que estoy accediendo al valor

    //2º lo insertamos en la lista
    // puede pasar que no haya nada
    if(pini == NULL){
        pini = nuevo;
    }else{//o que tenga algo
        //en el caso que quede primero
        if(pini->prio<prio){
            nuevo->sig = pini;
            pini = nuevo;
        }else{//sino
            //vamos a tener que recorrerlo
            ant = pini;
            act = ant->sig;
            while(ant != NULL){
                if(act==NULL){//lo asigno al final
                    ant->sig = nuevo;
                    break; //salimos del bucle porque ya terminamos
                }else if (act->prio < nuevo->prio){
                    nuevo->sig = act;
                    ant->sig = nuevo;
                    break;
                }
                //adelantamos los punteros
                ant = act;
                act = act->sig;
            }
        }
    }
}
MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow){
    ui->setupUi(this);
    base = NULL;
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_pushButton_clicked()
{
    int idProc, prio;
    idProc = ui->spinBox->value();
    prio = ui->spinBox_2->value();
    agregar(base,idProc, prio );
}

void MainWindow::on_pushButton_2_clicked()
{
    ui->listWidget->clear();
    nodo *q =base;
    while(q!=NULL){
        QString item = "Prio " + QString::number(q->prio) + ", idProc " + QString::number(q->idProc);
        ui->listWidget->addItem(item);
        q = q->sig;
    }
}
