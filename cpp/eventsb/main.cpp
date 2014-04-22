#include <vector>
#include <map>
#include <string>
#include <iostream>
#include "event.h"
using namespace std;

static EventManager EM;//you can have as many events managers as you want, even one per thread
static std::string evname = "PlayerDied";

void PlayerDied(void* self, void* sender, void* p){//static func
    cout << "self " << (long)self << " sender " << sender << " p " << (char*)p << endl;
}

class SomeClass{
    Event *ev;

    int i;
public:
    SomeClass(int i){
        if((ev = EM.get(evname))){
            ev->subscribe(this, SomeClass::onPlayerDied);//can be used with classes :)
        }
        this->i = i;
    }
    static void onPlayerDied(void* self, void* sender, void* param){
        cout <<"PLayer just died!! i am " << ((SomeClass*)self)->i << " param is " << (char*)param << endl;
    }
    ~SomeClass(){
        if(ev){
            this->ev->unsubscribe(this);
        }
    }
};

int main(){
    SomeClass a(1), b(2), c(3);

  char *str = "holiwi";
  EM.get(evname)->subscribe(NULL, PlayerDied);//can use with fully static methods
  EM.get(evname)->subscribe((void*)1, PlayerDied);
  cout <<" probando"<< endl;
  //"dios ayudanos plz.jpg"
  EM.get(evname)->fire((void*)1,str);
}


//http://www.cocos2d-x.org/wiki/EventDispatcher_Mechanism#Custom-Event
