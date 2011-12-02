#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QFileDialog>

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    ag = NULL;
}

MainWindow::~MainWindow()
{
    delete ui;
    if (ag!=NULL)
        delete ag;
}

void MainWindow::on_pushButton_3_clicked()
{
    if (ag!= NULL)
        delete ag;

    QString narchivo = QFileDialog::getOpenFileName(this, "Elija el archivo", "", "Archivos de agenda(*.bin)");

    if (narchivo == NULL)
       return;

    ag = new Agenda(narchivo);

    this->ui->pushButton->setEnabled(true);
    this->ui->pushButton_2->setEnabled(true);
}

void MainWindow::on_pushButton_clicked()
{
    //leer un registro
    registro res;
    //leo la posicion desde el spinbox
    int nreg = ui->spinBox_2->value();
    if (!this->ag->valorEntrada(nreg, res)){
        //si no lo puedo leer, pongo a vacio los campos
        ui->nya->setText("");
        ui->email->setText("");
        ui->nac->setValue(0);
    }
    //sino, pongo los valores que lei de la agenda

    ui->nya->setText(QString(res.nya));
    ui->email->setText(QString (res.email));
    ui->nac->setValue(res.anac);
}

void MainWindow::on_pushButton_2_clicked()
{
    registro aux;

    //Copiamos lo que hay en un QString a una cadena de caracteres (char [])
    //si hacemos res.nya = ui->nya->text().toStdString().c_str() tendriamos problemas de punteros
    strcpy(aux.nya, ui->nya->text().toStdString().c_str());
    strcpy(aux.email, ui->email->text().toStdString().c_str());
    aux.anac = ui->nac->value();

    ag->actEntrada(ui->spinBox_2->value(), aux);

}
