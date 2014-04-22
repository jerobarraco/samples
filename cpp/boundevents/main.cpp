#include <iostream>
#include "event.h"
#include <string>
/* I tried using an approach to be able to make callbacks into bound methods, but it sucked a lot
 * because i still need to store the "this" pointer at some structure (subscriber) and also to be able to
 * call a bounded method i need it to belong to a certain class (really c++¿) so basically i needed an extra
 * class just to be able to do (class->*cb)( ) which sucked like a black hole (seriously whats with that syntax!)
 * it also prevented me to create callbacks to static methods..
 * so i rather just do Board* diz = (Board*) sender; and fuck the world
 * also it went against the K.I.S.S. principle.
 */

using namespace std;
class IListen: public Listener{
public:
    void callme(void*sender, void* param){
        cout << "i gat this "    << (long)sender << " param " << (long) param << endl;
        cout << "i am at "<< (long) this;
    }
    IListen(){
        this->connect("caca", (Callback)&IListen::callme);
    }
};
int main()
{
    IListen ll;
    Event *e= EM.get("caca");
    e->fire(NULL, (void*) 100);
    cout <<  "ll is at " << (long ) &ll << endl;
    cout << "Hello World!" << endl;

    return 0;
}
