#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QTextStream>
#include <QFileDialog>
#include "ctime"

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

    float suma = 0;


    //se abre el archivo y se le pasa las opciones con las que se quiere abrir
    if (archivo.open(QIODevice::Text | QIODevice::ReadOnly)){

        QTextStream txt(&archivo);

        ui->listWidget->clear();

        while (!txt.atEnd()){
            QString linea = txt.readLine();

            ui->listWidget->addItem(linea);
            suma += linea.toFloat();
        }
        archivo.close();

        ui->label->setText(QString::number(suma, 'f', 12));
    } else{

    }
}

void MainWindow::on_pushButton_2_clicked()
{
    float num = rand()%100; //0 y 100
    ui->listWidget->addItem(QString::number(num, 'f',2));
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
    if (archivo.open(QIODevice::Text | QIODevice::WriteOnly)){
        QTextStream txt(&archivo);
        for (int i = 0 ; i< ui->listWidget->count(); i++){
            QString linea = ui->listWidget->item(i)->text();
            //txt << linea<< endl;
            float valor = linea.toFloat();
            txt << valor << endl;
        }
        archivo.close();
    } else{

    }
}
