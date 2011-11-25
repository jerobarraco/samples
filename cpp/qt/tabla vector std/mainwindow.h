#ifndef MAINWINDOW_H
#define MAINWINDOW_H
#include "sensores.h"
#include <ctime>

#include <QMainWindow>

namespace Ui {
    class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

private slots:
    void on_pbGenerar_clicked();

    void on_pbMostrar_clicked();

    void on_pbProm_clicked();

    void on_pushButton_clicked();

    void on_pushButton_2_clicked();

private:
    Ui::MainWindow *ui;
    Sensores S;
};

#endif // MAINWINDOW_H
