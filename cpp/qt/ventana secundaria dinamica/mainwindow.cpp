#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    this->midialogo  = new Dialog(this);
}

MainWindow::~MainWindow()
{
    delete ui;

}

void MainWindow::on_pushButton_clicked()
{
   bool uno= ui->checkBox->isChecked();
   ui->checkBox_2->setChecked(uno);
   this->midialogo->show();
}

void MainWindow::on_buttonBox_accepted()
{
    this->midialogo->close();
}

void MainWindow::on_commandLinkButton_clicked()
{
    this->midialogo->setModal(true);
    midialogo->show();

}
