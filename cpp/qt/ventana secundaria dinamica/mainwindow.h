#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include "dialog.h"

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
    void on_pushButton_clicked();

    void on_buttonBox_accepted();

    void on_commandLinkButton_clicked();

private:
    Ui::MainWindow *ui;
    Dialog *midialogo;
};

#endif // MAINWINDOW_H
