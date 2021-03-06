#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QFileDialog>
#include <QFile>
#include <QStringList>
#include <QTextStream>

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_pushButton_clicked()
{
    //QString dir = QApplication::applicationDirPath();
    QString filtro = "CSV (*.csv);;Todos los archivos (*.*);;Archivos de texto (*.txt)";

    QString nombreAbrir = QFileDialog::getOpenFileName(this,
                          "Abrir archivo", "",
                          filtro);

    if (nombreAbrir == NULL ) return;
    archivo.setFileName(nombreAbrir);


    if (archivo.open(QIODevice::Text | QIODevice::ReadOnly)){
        QTextStream txt(&archivo);

        ui->tableWidget->setRowCount(0);
        ui->tableWidget->setColumnCount(0);

        QString linea;
        linea = txt.readLine(); //"Numero,Nombre,DNI,Direccion"
        QStringList header = linea.split(","); //[ "numero", "nombre", ...]
        //header.join(",");//el inverso de linea.split

        //coloco los headers (texto de titulo de columna)
        ui->tableWidget->setColumnCount(header.count());//header.size es valido tamb
        ui->tableWidget->setHorizontalHeaderLabels(header);

        while (!txt.atEnd()){
            linea = txt.readLine();

            //como usar un objeto que mantiene consistente su forma de escribir en un archivo
            //Persona p(linea);

            QStringList items = linea.split(",");
            //obtengo la cantidad de filas
            int row = ui->tableWidget->rowCount();
            //agrego una fila
            ui->tableWidget->setRowCount(row+1);

            //por cada elemento en el stringlist
            for (int i = 0; i<items.count(); i++){
                ui->tableWidget->setItem(row, i, new QTableWidgetItem(items.at(i)));
            }

        }
        archivo.close();
    } else{

    }
}
