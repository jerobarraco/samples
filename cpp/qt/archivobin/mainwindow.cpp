#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QDataStream>
#include <QFileDialog>
#include "ctime"
//#include "persona.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    srand(time(NULL));
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_pushButton_clicked()
{

    QString dir = QApplication::applicationDirPath();

    QString filtro = "Todos los archivos (*.*);;Archivos de texto (*.txt)";

    QString nombreAbrir = QFileDialog::getOpenFileName(
                            this,
                          "Abrir archivo",
                          dir,
                          filtro);
    //nombreAbrir contiene el nombre de archivo que eligio el usuario
    //si se presiona cancelar el nombre es NULL
    if (nombreAbrir == NULL )
        return;

    //le digo con que archivo vamos a trabajar
    archivo.setFileName(nombreAbrir);

    int suma = 0;


    //se abre el archivo y se le pasa las opciones con las que se quiere abrir
    if (archivo.open(QIODevice::Text | QIODevice::ReadOnly)){

        QDataStream data(&archivo);

        ui->listWidget->clear();

        while (!data.atEnd()){
            int valor ;
            data >> valor;
            //data.readRawData(&p, sizeof(p))


            ui->listWidget->addItem(QString::number(valor));
            suma += valor;
        }
        archivo.close();

        ui->label->setText(QString::number(suma));
    } else{

    }
}

void MainWindow::on_pushButton_2_clicked()
{
    int num = rand()%100; //0 y 100
    ui->listWidget->addItem(QString::number(num));
}

void MainWindow::on_pushButton_3_clicked()
{
    QString dir = QApplication::applicationDirPath();

    QString filtro = "Todos los archivos (*.*);;Archivos de texto (*.txt)";

    QString nombreAbrir = QFileDialog::getSaveFileName(this,
                          "Abrir archivo",
                          dir,
                          filtro);

    if (nombreAbrir == NULL ) return;
    archivo.setFileName(nombreAbrir);
    if (archivo.open(QIODevice::WriteOnly)){
        QDataStream data(&archivo);
        for (int i = 0 ; i< ui->listWidget->count(); i++){
            QString linea = ui->listWidget->item(i)->text();
            //txt << linea<< endl;
            int valor = linea.toFloat();
            data << valor;
        }

        /*Persona p;
        strcpy(p.nombre, "juan");
        strcpy(p.dir, "calle");
        p.dni = 24;
        data.writeRawData((char*) &p, sizeof(p));
        data << QString("algo");*/

        archivo.close();
    } else{

    }
}
