/********************************************************************************
** Form generated from reading UI file 'mainwindow.ui'
**
** Created: Fri 25. Nov 12:51:04 2011
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
#include <QtGui/QHBoxLayout>
#include <QtGui/QHeaderView>
#include <QtGui/QLabel>
#include <QtGui/QLineEdit>
#include <QtGui/QMainWindow>
#include <QtGui/QMenuBar>
#include <QtGui/QPushButton>
#include <QtGui/QSpacerItem>
#include <QtGui/QStatusBar>
#include <QtGui/QTableWidget>
#include <QtGui/QToolBar>
#include <QtGui/QVBoxLayout>
#include <QtGui/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainWindow
{
public:
    QWidget *centralWidget;
    QHBoxLayout *horizontalLayout_7;
    QVBoxLayout *verticalLayout_3;
    QTableWidget *tableWidget;
    QHBoxLayout *horizontalLayout_6;
    QSpacerItem *horizontalSpacer;
    QVBoxLayout *verticalLayout_2;
    QHBoxLayout *horizontalLayout_3;
    QLabel *label;
    QLineEdit *leFil;
    QHBoxLayout *horizontalLayout_4;
    QLabel *label_2;
    QLineEdit *leCol;
    QHBoxLayout *horizontalLayout_5;
    QPushButton *pushButton_2;
    QLabel *label_4;
    QLabel *lbValor;
    QSpacerItem *horizontalSpacer_2;
    QVBoxLayout *verticalLayout;
    QSpacerItem *verticalSpacer_9;
    QPushButton *pbGenerar;
    QSpacerItem *verticalSpacer;
    QPushButton *pbMostrar;
    QSpacerItem *verticalSpacer_2;
    QPushButton *pbProm;
    QSpacerItem *verticalSpacer_3;
    QHBoxLayout *horizontalLayout;
    QLabel *label_3;
    QLabel *lbProm;
    QSpacerItem *verticalSpacer_4;
    QHBoxLayout *horizontalLayout_2;
    QLabel *label_6;
    QLabel *lbProm2;
    QSpacerItem *verticalSpacer_7;
    QPushButton *pushButton;
    QSpacerItem *verticalSpacer_8;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName(QString::fromUtf8("MainWindow"));
        MainWindow->resize(483, 443);
        centralWidget = new QWidget(MainWindow);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        horizontalLayout_7 = new QHBoxLayout(centralWidget);
        horizontalLayout_7->setSpacing(6);
        horizontalLayout_7->setContentsMargins(11, 11, 11, 11);
        horizontalLayout_7->setObjectName(QString::fromUtf8("horizontalLayout_7"));
        verticalLayout_3 = new QVBoxLayout();
        verticalLayout_3->setSpacing(6);
        verticalLayout_3->setObjectName(QString::fromUtf8("verticalLayout_3"));
        tableWidget = new QTableWidget(centralWidget);
        tableWidget->setObjectName(QString::fromUtf8("tableWidget"));
        QSizePolicy sizePolicy(QSizePolicy::Expanding, QSizePolicy::Expanding);
        sizePolicy.setHorizontalStretch(0);
        sizePolicy.setVerticalStretch(0);
        sizePolicy.setHeightForWidth(tableWidget->sizePolicy().hasHeightForWidth());
        tableWidget->setSizePolicy(sizePolicy);
        tableWidget->setMaximumSize(QSize(16777215, 16777215));

        verticalLayout_3->addWidget(tableWidget);

        horizontalLayout_6 = new QHBoxLayout();
        horizontalLayout_6->setSpacing(6);
        horizontalLayout_6->setObjectName(QString::fromUtf8("horizontalLayout_6"));
        horizontalSpacer = new QSpacerItem(40, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout_6->addItem(horizontalSpacer);

        verticalLayout_2 = new QVBoxLayout();
        verticalLayout_2->setSpacing(6);
        verticalLayout_2->setObjectName(QString::fromUtf8("verticalLayout_2"));
        horizontalLayout_3 = new QHBoxLayout();
        horizontalLayout_3->setSpacing(6);
        horizontalLayout_3->setObjectName(QString::fromUtf8("horizontalLayout_3"));
        label = new QLabel(centralWidget);
        label->setObjectName(QString::fromUtf8("label"));

        horizontalLayout_3->addWidget(label);

        leFil = new QLineEdit(centralWidget);
        leFil->setObjectName(QString::fromUtf8("leFil"));

        horizontalLayout_3->addWidget(leFil);


        verticalLayout_2->addLayout(horizontalLayout_3);

        horizontalLayout_4 = new QHBoxLayout();
        horizontalLayout_4->setSpacing(6);
        horizontalLayout_4->setObjectName(QString::fromUtf8("horizontalLayout_4"));
        label_2 = new QLabel(centralWidget);
        label_2->setObjectName(QString::fromUtf8("label_2"));

        horizontalLayout_4->addWidget(label_2);

        leCol = new QLineEdit(centralWidget);
        leCol->setObjectName(QString::fromUtf8("leCol"));

        horizontalLayout_4->addWidget(leCol);


        verticalLayout_2->addLayout(horizontalLayout_4);

        horizontalLayout_5 = new QHBoxLayout();
        horizontalLayout_5->setSpacing(6);
        horizontalLayout_5->setObjectName(QString::fromUtf8("horizontalLayout_5"));
        pushButton_2 = new QPushButton(centralWidget);
        pushButton_2->setObjectName(QString::fromUtf8("pushButton_2"));

        horizontalLayout_5->addWidget(pushButton_2);

        label_4 = new QLabel(centralWidget);
        label_4->setObjectName(QString::fromUtf8("label_4"));

        horizontalLayout_5->addWidget(label_4);

        lbValor = new QLabel(centralWidget);
        lbValor->setObjectName(QString::fromUtf8("lbValor"));

        horizontalLayout_5->addWidget(lbValor);


        verticalLayout_2->addLayout(horizontalLayout_5);


        horizontalLayout_6->addLayout(verticalLayout_2);

        horizontalSpacer_2 = new QSpacerItem(40, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout_6->addItem(horizontalSpacer_2);


        verticalLayout_3->addLayout(horizontalLayout_6);


        horizontalLayout_7->addLayout(verticalLayout_3);

        verticalLayout = new QVBoxLayout();
        verticalLayout->setSpacing(6);
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        verticalSpacer_9 = new QSpacerItem(20, 18, QSizePolicy::Minimum, QSizePolicy::Expanding);

        verticalLayout->addItem(verticalSpacer_9);

        pbGenerar = new QPushButton(centralWidget);
        pbGenerar->setObjectName(QString::fromUtf8("pbGenerar"));

        verticalLayout->addWidget(pbGenerar);

        verticalSpacer = new QSpacerItem(20, 13, QSizePolicy::Minimum, QSizePolicy::Expanding);

        verticalLayout->addItem(verticalSpacer);

        pbMostrar = new QPushButton(centralWidget);
        pbMostrar->setObjectName(QString::fromUtf8("pbMostrar"));

        verticalLayout->addWidget(pbMostrar);

        verticalSpacer_2 = new QSpacerItem(20, 13, QSizePolicy::Minimum, QSizePolicy::Expanding);

        verticalLayout->addItem(verticalSpacer_2);

        pbProm = new QPushButton(centralWidget);
        pbProm->setObjectName(QString::fromUtf8("pbProm"));

        verticalLayout->addWidget(pbProm);

        verticalSpacer_3 = new QSpacerItem(20, 17, QSizePolicy::Minimum, QSizePolicy::Expanding);

        verticalLayout->addItem(verticalSpacer_3);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setSpacing(6);
        horizontalLayout->setObjectName(QString::fromUtf8("horizontalLayout"));
        label_3 = new QLabel(centralWidget);
        label_3->setObjectName(QString::fromUtf8("label_3"));

        horizontalLayout->addWidget(label_3);

        lbProm = new QLabel(centralWidget);
        lbProm->setObjectName(QString::fromUtf8("lbProm"));

        horizontalLayout->addWidget(lbProm);


        verticalLayout->addLayout(horizontalLayout);

        verticalSpacer_4 = new QSpacerItem(20, 13, QSizePolicy::Minimum, QSizePolicy::Expanding);

        verticalLayout->addItem(verticalSpacer_4);

        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setSpacing(6);
        horizontalLayout_2->setObjectName(QString::fromUtf8("horizontalLayout_2"));
        label_6 = new QLabel(centralWidget);
        label_6->setObjectName(QString::fromUtf8("label_6"));

        horizontalLayout_2->addWidget(label_6);

        lbProm2 = new QLabel(centralWidget);
        lbProm2->setObjectName(QString::fromUtf8("lbProm2"));

        horizontalLayout_2->addWidget(lbProm2);


        verticalLayout->addLayout(horizontalLayout_2);

        verticalSpacer_7 = new QSpacerItem(20, 18, QSizePolicy::Minimum, QSizePolicy::Expanding);

        verticalLayout->addItem(verticalSpacer_7);

        pushButton = new QPushButton(centralWidget);
        pushButton->setObjectName(QString::fromUtf8("pushButton"));

        verticalLayout->addWidget(pushButton);

        verticalSpacer_8 = new QSpacerItem(20, 18, QSizePolicy::Minimum, QSizePolicy::Expanding);

        verticalLayout->addItem(verticalSpacer_8);


        horizontalLayout_7->addLayout(verticalLayout);

        MainWindow->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(MainWindow);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 483, 22));
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
        label->setText(QApplication::translate("MainWindow", "FIla: ", 0, QApplication::UnicodeUTF8));
        label_2->setText(QApplication::translate("MainWindow", "Columna: ", 0, QApplication::UnicodeUTF8));
        pushButton_2->setText(QApplication::translate("MainWindow", "Buscar", 0, QApplication::UnicodeUTF8));
        label_4->setText(QApplication::translate("MainWindow", "Valor a Buscar", 0, QApplication::UnicodeUTF8));
        lbValor->setText(QString());
        pbGenerar->setText(QApplication::translate("MainWindow", "Generar", 0, QApplication::UnicodeUTF8));
        pbMostrar->setText(QApplication::translate("MainWindow", "Mostrar", 0, QApplication::UnicodeUTF8));
        pbProm->setText(QApplication::translate("MainWindow", "Promedio", 0, QApplication::UnicodeUTF8));
        label_3->setText(QApplication::translate("MainWindow", "Promedio: ", 0, QApplication::UnicodeUTF8));
        lbProm->setText(QApplication::translate("MainWindow", "-", 0, QApplication::UnicodeUTF8));
        label_6->setText(QApplication::translate("MainWindow", "Promedio 2: ", 0, QApplication::UnicodeUTF8));
        lbProm2->setText(QApplication::translate("MainWindow", "-", 0, QApplication::UnicodeUTF8));
        pushButton->setText(QApplication::translate("MainWindow", "Salir", 0, QApplication::UnicodeUTF8));
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H
