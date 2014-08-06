#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>

namespace Ui {
class MainWindow;
}

struct nodo{
    int idProc;
    int prio;
    nodo *sig;
};

void agregar(nodo* &pini, int idProc, int prio);
class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    nodo *base;
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

private slots:
    void on_pushButton_clicked();

    void on_pushButton_2_clicked();

private:
    Ui::MainWindow *ui;
};

#endif // MAINWINDOW_H
