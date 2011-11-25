#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <ctime>
#include <QMessageBox>

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    srand(time(0));
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_pushButton_clicked()
{
    for (int i=0; i< ui->tableWidget->rowCount(); i++){
        for (int j=0; j<ui->tableWidget->columnCount(); j++){
            QString texto = QString::number(rand()%1000);
            QTableWidgetItem *item = new QTableWidgetItem(texto);
            ui->tableWidget->setItem(i, j, item);
            /*
            QTableWidgetItem *item = ui->tableWidget->item(i,j);
            QString texto = item->text();
            int valor = texto.toInt();*/
        }
    }

    for (int i = 0; i< ui->tableWidget_2->rowCount(); i++){
        //incognitas
        QString texto = "X"+QString::number(i+1);
        QTableWidgetItem *item = new QTableWidgetItem(texto);
        ui->tableWidget_2->setItem(i, 0, item);

        //independientes
        texto = QString::number(rand()%1000);
        item = new QTableWidgetItem(texto);
        ui->tableWidget_3->setItem(i, 0, item);
    }
}

void MainWindow::on_spinBox_valueChanged(int arg1)
{
    ui->tableWidget->setColumnCount( arg1 );
}

void MainWindow::on_spinBox_2_valueChanged(int arg1)
{
    ui->tableWidget->setRowCount( arg1 );
    ui->tableWidget_2->setRowCount( arg1 );
    ui->tableWidget_3->setRowCount( arg1 );
}

void MainWindow::on_pushButton_2_clicked()
{
    for (int i=0; i< ui->tableWidget->rowCount(); i++){
        for (int j=0; j<ui->tableWidget->columnCount(); j++){
            QTableWidgetItem *item = ui->tableWidget->item(i, j);
            if(item==NULL){
                QMessageBox msgBox;
                 msgBox.setText("ES nulo");
                 msgBox.exec();
                 return;
            }
        }
    }
}
