/********************************************************************************
** Form generated from reading UI file 'mainwindow.ui'
**
** Created: Tue 29. Nov 11:56:39 2011
**      by: Qt User Interface Compiler version 4.7.4
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MAINWINDOW_H
#define UI_MAINWINDOW_H

#include <QtCore/QVariant>
#include <QtGui/QAction>
#include <QtGui/QApplication>
#include <QtGui/QButtonGroup>
#include <QtGui/QFormLayout>
#include <QtGui/QHBoxLayout>
#include <QtGui/QHeaderView>
#include <QtGui/QLabel>
#include <QtGui/QLineEdit>
#include <QtGui/QMainWindow>
#include <QtGui/QMenuBar>
#include <QtGui/QPushButton>
#include <QtGui/QSpinBox>
#include <QtGui/QStatusBar>
#include <QtGui/QToolBar>
#include <QtGui/QVBoxLayout>
#include <QtGui/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainWindow
{
public:
    QWidget *centralWidget;
    QHBoxLayout *horizontalLayout;
    QFormLayout *formLayout;
    QLabel *nOmbreYApellidoLabel;
    QLineEdit *nya;
    QLabel *emailLabel;
    QLineEdit *email;
    QLabel *aONacimientoLabel;
    QSpinBox *nac;
    QVBoxLayout *verticalLayout;
    QSpinBox *spinBox_2;
    QPushButton *pushButton;
    QPushButton *pushButton_2;
    QPushButton *pushButton_3;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName(QString::fromUtf8("MainWindow"));
        MainWindow->resize(400, 217);
        centralWidget = new QWidget(MainWindow);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        horizontalLayout = new QHBoxLayout(centralWidget);
        horizontalLayout->setSpacing(6);
        horizontalLayout->setContentsMargins(11, 11, 11, 11);
        horizontalLayout->setObjectName(QString::fromUtf8("horizontalLayout"));
        formLayout = new QFormLayout();
        formLayout->setSpacing(6);
        formLayout->setObjectName(QString::fromUtf8("formLayout"));
        formLayout->setFieldGrowthPolicy(QFormLayout::AllNonFixedFieldsGrow);
        nOmbreYApellidoLabel = new QLabel(centralWidget);
        nOmbreYApellidoLabel->setObjectName(QString::fromUtf8("nOmbreYApellidoLabel"));

        formLayout->setWidget(2, QFormLayout::LabelRole, nOmbreYApellidoLabel);

        nya = new QLineEdit(centralWidget);
        nya->setObjectName(QString::fromUtf8("nya"));

        formLayout->setWidget(2, QFormLayout::FieldRole, nya);

        emailLabel = new QLabel(centralWidget);
        emailLabel->setObjectName(QString::fromUtf8("emailLabel"));

        formLayout->setWidget(3, QFormLayout::LabelRole, emailLabel);

        email = new QLineEdit(centralWidget);
        email->setObjectName(QString::fromUtf8("email"));

        formLayout->setWidget(3, QFormLayout::FieldRole, email);

        aONacimientoLabel = new QLabel(centralWidget);
        aONacimientoLabel->setObjectName(QString::fromUtf8("aONacimientoLabel"));

        formLayout->setWidget(4, QFormLayout::LabelRole, aONacimientoLabel);

        nac = new QSpinBox(centralWidget);
        nac->setObjectName(QString::fromUtf8("nac"));

        formLayout->setWidget(4, QFormLayout::FieldRole, nac);


        horizontalLayout->addLayout(formLayout);

        verticalLayout = new QVBoxLayout();
        verticalLayout->setSpacing(6);
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        spinBox_2 = new QSpinBox(centralWidget);
        spinBox_2->setObjectName(QString::fromUtf8("spinBox_2"));

        verticalLayout->addWidget(spinBox_2);

        pushButton = new QPushButton(centralWidget);
        pushButton->setObjectName(QString::fromUtf8("pushButton"));
        pushButton->setEnabled(false);

        verticalLayout->addWidget(pushButton);

        pushButton_2 = new QPushButton(centralWidget);
        pushButton_2->setObjectName(QString::fromUtf8("pushButton_2"));
        pushButton_2->setEnabled(false);

        verticalLayout->addWidget(pushButton_2);

        pushButton_3 = new QPushButton(centralWidget);
        pushButton_3->setObjectName(QString::fromUtf8("pushButton_3"));

        verticalLayout->addWidget(pushButton_3);


        horizontalLayout->addLayout(verticalLayout);

        MainWindow->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(MainWindow);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 400, 22));
        MainWindow->setMenuBar(menuBar);
        mainToolBar = new QToolBar(MainWindow);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        MainWindow->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(MainWindow);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        MainWindow->setStatusBar(statusBar);

        retranslateUi(MainWindow);

        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QApplication::translate("MainWindow", "MainWindow", 0, QApplication::UnicodeUTF8));
        nOmbreYApellidoLabel->setText(QApplication::translate("MainWindow", "Nombre y apellido", 0, QApplication::UnicodeUTF8));
        emailLabel->setText(QApplication::translate("MainWindow", "Email", 0, QApplication::UnicodeUTF8));
        aONacimientoLabel->setText(QApplication::translate("MainWindow", "A\303\261o nacimiento", 0, QApplication::UnicodeUTF8));
        pushButton->setText(QApplication::translate("MainWindow", "Leer", 0, QApplication::UnicodeUTF8));
        pushButton_2->setText(QApplication::translate("MainWindow", "Escribir", 0, QApplication::UnicodeUTF8));
        pushButton_3->setText(QApplication::translate("MainWindow", "Abrir", 0, QApplication::UnicodeUTF8));
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H
