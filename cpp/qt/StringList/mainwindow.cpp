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

    QString texto = ui->lineEdit->text();

    QStringList res = texto.split(" ");
    ui->tableWidget->setColumnCount(1);
    for (int i=0 ; i<res.size() ; i++)
        {
        int cFilas = ui->tableWidget->rowCount();

        ui->tableWidget->setRowCount(cFilas+1);
        ui->tableWidget->setItem(cFilas, 0, new QTableWidgetItem(res[i]));
        }
    }
