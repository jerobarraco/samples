#include <QtCore/QCoreApplication>
#include "iostream"
class miexcep {};

class exept2:public miexcep{};

using namespace std;
int main(int argc, char *argv[])
{
    try{
        int a = 0+1;
        throw exept2();
    }
    catch(miexcep &e){
        cout << "error";
    }
}
