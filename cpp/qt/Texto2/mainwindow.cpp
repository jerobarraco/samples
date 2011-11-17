#include "mainwindow.h"
#include "ui_mainwindow.h"

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
    analizador.setString(ui->lineEdit->text());
    analizador.setTabla(ui->tableWidget);
    analizador.procesar();
    /*QStringList res = analizador.procesar2();
    ui->tableWidget->rowCount(0);
    for (int i = 0; i< res.size(); i++)
        ui->tableWidget->rowCount(ui->tableWidget->rowCount()+1);*/


}
