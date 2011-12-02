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
    delete ui;º
}

void MainWindow::on_pushButton_clicked()
{
    QString filtro = "BIN (*.bin);;Todos los archivos (*.*)";

    QString nombreAbrir = QFileDialog::getOpenFileName(this,
                          "Abrir archivo", "",
                          filtro);

    if (nombreAbrir == NULL ) return;

    archivo.setFileName(nombreAbrir);


    if (archivo.open(QIODevice::ReadOnly)){

        ui->tableWidget->setRowCount(0);
        ui->tableWidget->setColumnCount(0);
        QStringList header;
        header << "Numero" << "Nombre" << "Dni"<< "Direccion";
        //coloco los headers (texto de titulo de columna)
        ui->tableWidget->setColumnCount(header.count());//header.size es valido tamb
        ui->tableWidget->setHorizontalHeaderLabels(header);

        while (!archivo.atEnd()){
            //como usar un objeto que mantiene consistente su forma de escribir en un archivo
            Persona p;
            archivo.read((char*) &p, sizeof(p) );

            int row = ui->tableWidget->rowCount();
            //agrego una fila
            ui->tableWidget->setRowCount(row+1);

            ui->tableWidget->setItem(row, 0, new QTableWidgetItem(QString::number(p.numero)));
            ui->tableWidget->setItem(row, 1, new QTableWidgetItem(p.nombre));
            ui->tableWidget->setItem(row, 2, new QTableWidgetItem(QString::number(p.dni)));
            ui->tableWidget->setItem(row, 3, new QTableWidgetItem(p.direccion));
        }
        archivo.close();
    } else{

    }
}
