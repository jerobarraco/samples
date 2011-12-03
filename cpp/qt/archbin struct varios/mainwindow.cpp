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
    QString filtro = "BIN (*.bin);;Todos los archivos (*.*)";

    QString nombreAbrir = QFileDialog::getOpenFileName(this,
                          "Abrir archivo", "",
                          filtro);

    if (nombreAbrir == NULL ) return;

    archivo.setFileName(nombreAbrir);

    ui->tableWidget->setRowCount(0);
    ui->tableWidget->setColumnCount(0);
    QStringList header;
    header << "Numero" << "Nombre" << "Dni"<< "Direccion";
    //coloco los headers (texto de titulo de columna)
    ui->tableWidget->setColumnCount(header.count());//header.size es valido tamb
    ui->tableWidget->setHorizontalHeaderLabels(header);

    if (! archivo.open(QIODevice::ReadOnly)){
        return;
    }
    int cant = archivo.size()/sizeof(Persona);
    Persona *ps = new Persona[cant];
    archivo.read((char*)ps, archivo.size());
    ui->tableWidget->setRowCount(cant);
    for (int i = 0; i< cant; i++){
        ui->tableWidget->setItem(i, 0, new QTableWidgetItem(QString::number(ps[i].numero)));
        ui->tableWidget->setItem(i, 1, new QTableWidgetItem(ps[i].nombre));
        ui->tableWidget->setItem(i, 2, new QTableWidgetItem(QString::number(ps[i].dni)));
        ui->tableWidget->setItem(i, 3, new QTableWidgetItem(ps[i].direccion));
    }
    archivo.close();
    delete [] ps;
    ps =NULL;

}
