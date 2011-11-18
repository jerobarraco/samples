/********************************************************************************
** Form generated from reading UI file 'mainwindow.ui'
**
** Created: Fri 4. Nov 12:54:20 2011
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
#include <QtGui/QCheckBox>
#include <QtGui/QCommandLinkButton>
#include <QtGui/QDialogButtonBox>
#include <QtGui/QDockWidget>
#include <QtGui/QGroupBox>
#include <QtGui/QHeaderView>
#include <QtGui/QMainWindow>
#include <QtGui/QMenuBar>
#include <QtGui/QPushButton>
#include <QtGui/QRadioButton>
#include <QtGui/QStatusBar>
#include <QtGui/QToolBar>
#include <QtGui/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainWindow
{
public:
    QWidget *centralWidget;
    QPushButton *pushButton;
    QCheckBox *checkBox_3;
    QCommandLinkButton *commandLinkButton;
    QCommandLinkButton *commandLinkButton_2;
    QCommandLinkButton *commandLinkButton_3;
    QGroupBox *groupBox;
    QRadioButton *radioButton_4;
    QRadioButton *radioButton_5;
    QRadioButton *radioButton_6;
    QDialogButtonBox *buttonBox;
    QCheckBox *checkBox_2;
    QCheckBox *checkBox;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;
    QDockWidget *dockWidget;
    QWidget *dockWidgetContents;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName(QString::fromUtf8("MainWindow"));
        MainWindow->resize(668, 498);
        centralWidget = new QWidget(MainWindow);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        pushButton = new QPushButton(centralWidget);
        pushButton->setObjectName(QString::fromUtf8("pushButton"));
        pushButton->setGeometry(QRect(20, 90, 93, 28));
        pushButton->setCheckable(false);
        pushButton->setChecked(false);
        checkBox_3 = new QCheckBox(centralWidget);
        checkBox_3->setObjectName(QString::fromUtf8("checkBox_3"));
        checkBox_3->setGeometry(QRect(50, 160, 82, 21));
        commandLinkButton = new QCommandLinkButton(centralWidget);
        commandLinkButton->setObjectName(QString::fromUtf8("commandLinkButton"));
        commandLinkButton->setGeometry(QRect(200, 20, 186, 41));
        commandLinkButton_2 = new QCommandLinkButton(centralWidget);
        commandLinkButton_2->setObjectName(QString::fromUtf8("commandLinkButton_2"));
        commandLinkButton_2->setGeometry(QRect(200, 50, 186, 41));
        commandLinkButton_3 = new QCommandLinkButton(centralWidget);
        commandLinkButton_3->setObjectName(QString::fromUtf8("commandLinkButton_3"));
        commandLinkButton_3->setGeometry(QRect(190, 80, 186, 41));
        groupBox = new QGroupBox(centralWidget);
        groupBox->setObjectName(QString::fromUtf8("groupBox"));
        groupBox->setGeometry(QRect(10, 0, 171, 91));
        radioButton_4 = new QRadioButton(groupBox);
        radioButton_4->setObjectName(QString::fromUtf8("radioButton_4"));
        radioButton_4->setGeometry(QRect(50, 60, 95, 21));
        radioButton_5 = new QRadioButton(groupBox);
        radioButton_5->setObjectName(QString::fromUtf8("radioButton_5"));
        radioButton_5->setGeometry(QRect(50, 20, 95, 21));
        radioButton_6 = new QRadioButton(groupBox);
        radioButton_6->setObjectName(QString::fromUtf8("radioButton_6"));
        radioButton_6->setGeometry(QRect(50, 40, 95, 21));
        buttonBox = new QDialogButtonBox(centralWidget);
        buttonBox->setObjectName(QString::fromUtf8("buttonBox"));
        buttonBox->setGeometry(QRect(50, 190, 193, 28));
        buttonBox->setStandardButtons(QDialogButtonBox::Apply|QDialogButtonBox::Cancel|QDialogButtonBox::Ok);
        checkBox_2 = new QCheckBox(centralWidget);
        checkBox_2->setObjectName(QString::fromUtf8("checkBox_2"));
        checkBox_2->setGeometry(QRect(50, 140, 82, 21));
        checkBox = new QCheckBox(centralWidget);
        checkBox->setObjectName(QString::fromUtf8("checkBox"));
        checkBox->setGeometry(QRect(40, 120, 82, 21));
        MainWindow->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(MainWindow);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 668, 22));
        MainWindow->setMenuBar(menuBar);
        mainToolBar = new QToolBar(MainWindow);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        MainWindow->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(MainWindow);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        MainWindow->setStatusBar(statusBar);
        dockWidget = new QDockWidget(MainWindow);
        dockWidget->setObjectName(QString::fromUtf8("dockWidget"));
        dockWidgetContents = new QWidget();
        dockWidgetContents->setObjectName(QString::fromUtf8("dockWidgetContents"));
        dockWidget->setWidget(dockWidgetContents);
        MainWindow->addDockWidget(static_cast<Qt::DockWidgetArea>(1), dockWidget);

        mainToolBar->addSeparator();

        retranslateUi(MainWindow);

        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QApplication::translate("MainWindow", "MainWindow", 0, QApplication::UnicodeUTF8));
        pushButton->setText(QApplication::translate("MainWindow", "PushButton", 0, QApplication::UnicodeUTF8));
        checkBox_3->setText(QApplication::translate("MainWindow", "CheckBox", 0, QApplication::UnicodeUTF8));
        commandLinkButton->setText(QApplication::translate("MainWindow", "CommandLinkButton", 0, QApplication::UnicodeUTF8));
        commandLinkButton_2->setText(QApplication::translate("MainWindow", "CommandLinkButton", 0, QApplication::UnicodeUTF8));
        commandLinkButton_3->setText(QApplication::translate("MainWindow", "CommandLinkButton", 0, QApplication::UnicodeUTF8));
        groupBox->setTitle(QApplication::translate("MainWindow", "GroupBox", 0, QApplication::UnicodeUTF8));
        radioButton_4->setText(QApplication::translate("MainWindow", "RadioButton", 0, QApplication::UnicodeUTF8));
        radioButton_5->setText(QApplication::translate("MainWindow", "RadioButton", 0, QApplication::UnicodeUTF8));
        radioButton_6->setText(QApplication::translate("MainWindow", "RadioButton", 0, QApplication::UnicodeUTF8));
        checkBox_2->setText(QApplication::translate("MainWindow", "CheckBox", 0, QApplication::UnicodeUTF8));
        checkBox->setText(QApplication::translate("MainWindow", "CheckBox", 0, QApplication::UnicodeUTF8));
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H
