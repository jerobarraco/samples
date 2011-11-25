/* 4) Se desea almacenar la información de 250000 sensores de presión distribuidos en una
pileta de 500 por 500 mts de tal forma que queda determinada una grilla virtual en la cual
cada sensor cubre 1 metro cuadrado de superficie.  Realice una clase que sea capaz de guardar
y leer todos los datos a través métodos de forma tal que coincida con dicha grilla virtual.
 También la clase debe calcular el promedio de todos los valores almacenados de los sensores.
Cree una aplicación que al hacer clicck en el botón “Mostrar”  muestre en  una Grilla
(TableWidget) los datos que ya han sido almacenados previamente por otra aplicación, muestre
en una etiqueta llamada lbValor el valor almacenado en al sensor cuya ubicación en la grilla
virtual se determina a través de los cuadros de edición   leFil y leCol y en otra etiqueta
llamada lbProm el promedio de todos los sensores.Finalmente para un calculo rápido del
promedio la aplicación  deberá tener la opción de recalcular el promedio de los sensores que
se ubican únicamente en las diagonales de la pileta, mostrándolos en una etiqueta lbProm2 al
hacer clic en el botón “Promedio”. */

#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QMessageBox>
MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    srand(time(0));
    this->setWindowTitle("Calculo de Puntos");
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_pbGenerar_clicked()
{
    S.Generar();
}

void MainWindow::on_pbMostrar_clicked()
{
    ui->lbProm->setText(S.Mostrar(ui->tableWidget));
}

void MainWindow::on_pbProm_clicked()
{
    ui->lbProm2->setText(S.promd());

}

void MainWindow::on_pushButton_clicked()
{
    QApplication::exit();
}

void MainWindow::on_pushButton_2_clicked()
{
    if (ui->tableWidget->currentItem() != NULL){
        QMessageBox msg;
        msg.setWindowTitle("Programa de Puntos");
        QString texto;

        texto = "Valor Seleccionado : " + ui->tableWidget->currentItem()->text() + "\n";
        texto += QString("Seleccionado la fila ") +QString::number( ui->tableWidget->currentRow()+1) + "\n";
        texto += "Seleccionado la columna "+QString::number( ui->tableWidget->currentColumn() +1 );
        msg.setText(texto);
        msg.addButton("Ok", QMessageBox::AcceptRole);
        msg.exec();
    }
    ui->lbValor->setText(S.valor(ui->leFil->text().toInt(), ui->leCol->text().toInt()));
}
