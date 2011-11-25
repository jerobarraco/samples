#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QStringList>
#include <QFileDialog>
#include <QInputDialog>
#include <vector>

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    QStringList l ;
    l<< "hola"<< "como" <<"stas" <<"ponele";
    ui->listWidget->addItems(l);
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_pushButton_clicked()
{
    ui->listWidget->addItems(QFileDialog::getOpenFileNames(this, "Elije", "", "Any (*.*)"));
}

void MainWindow::on_pushButton_2_clicked()
{
    ui->listWidget->sortItems(Qt::DescendingOrder);
}

void MainWindow::on_spinBox_valueChanged(int arg1)
{

    ui->tableWidget->setRowCount(ui->spinBox->value());

}

void MainWindow::on_spinBox_2_valueChanged(int arg1)
{
    ui->tableWidget->setColumnCount(arg1);
}

void MainWindow::on_tableWidget_cellDoubleClicked(int row, int column)
{

    //cacheamos el tablewidgetitem anterior
    QTableWidgetItem *qtwi = ui->tableWidget->item(row, column);
    if (qtwi == NULL){
        qtwi = new QTableWidgetItem("");
    }
    QString prevtext = qtwi->text();;//previous text
    bool ok;

    QString text = QInputDialog::getText(this, tr("Aplicacion"),
                    tr("Ingrese un valor:"),
                    QLineEdit::Normal,
                    prevtext,
                    &ok);

    if (ok && !text.isEmpty())
         ui->tableWidget->setItem(row, column, qtwi);
}
