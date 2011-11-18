#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QInputDialog>
MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    QStringList sl;
    sl.append("hola");
    sl.append("como");
    sl.append("estas");
    QString text = QInputDialog::getItem(this, "elija", "lista:",
            sl
    );
    ui->statusBar->showMessage(text);
}

MainWindow::~MainWindow()
{
    delete ui;
}
