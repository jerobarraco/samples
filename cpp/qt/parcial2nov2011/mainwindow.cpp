#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "secuenciaa.h"
#include "secuenciab.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    s = NULL;
    tipo_sec = -1;
    panel = 0;
    m=0;
    n=0;
}

MainWindow::~MainWindow()
{
    delete ui;
    if (s!=NULL){
        delete s;
    }
}

void MainWindow::on_pushButton_3_clicked()
{
    m = ui->spinBox_2->value();
    n = ui->spinBox_3->value();
    ui->tableWidget->setRowCount(m);
    ui->tableWidget->setColumnCount(n);

    if (s!= NULL){ delete s;}

    if (tipo_sec != 0){
        s = new SecuenciaA(m, n);
        tipo_sec = 0;

    }else{

        s = new SecuenciaB(m, n);
        tipo_sec = 1;
    }
    ui->statusBar->showMessage(s->getNombre());
    mostrar();
}

void MainWindow::mostrar(){
    if (s==NULL){ return;}
    for (int i=0; i<m; i++){
        for (int j= 0; j<n; j++){

            bool encendido =s->encendido(i, j);
            //QString texto = encendido ? "On": "Off";
            //QTableWidgetItem *twi = new QTableWidgetItem(texto);
            /*if (encendido){
                texto = "On";
            }else{
                texto = "Off";
            }*/
            QIcon ic(encendido ? ":/im/on" : ":im/off");
            QTableWidgetItem *twi = new QTableWidgetItem();
            twi->setIcon(ic);

            ui->tableWidget->setItem(i, j, twi);
        }
    }
}

void MainWindow::on_pushButton_2_clicked()
{
    //no sirve
    panel = ui->spinBox->value();
    mostrar();
}

void MainWindow::on_pushButton_clicked()
{
    if(s==NULL)
       return;

    s->actualizarEstado();
    mostrar();
}
