#include <QtCore/QCoreApplication>

#include <string>
#include <iostream>

int main(int argc, char *argv[])
{
    std::string l;
    std::cin >> l;

    QString s = QString(l.c_str());
    std::cout << s.toStdString();
}
