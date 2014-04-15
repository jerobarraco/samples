#include <vector>
#include <map>
#include <string>
#include <iostream>
using namespace std;
typedef void (*Callback)(void *self, void *sender, void *param);
//typedef fnc callback;
//typedef function<void(int param)> callback;

struct Subscriber{
    Callback cb;
    void* self;
};

class Event {
public:
    std::vector<Subscriber* > subs;//criptions
    std::string name;
    Event(std::string name){
        this->name = name;
    }
    Event(const Event &other){//for map
        *this=other;
    }
    Event& operator=(const Event &other){//for crappy map
        clear();
        for (unsigned int i = 0 ; i<other.subs.size(); i++){
            Subscriber *s = other.subs[i];
            subscribe(s->self, s->cb);
        };
        name = other.name;
        return *this;
    }
    void subscribe(void* self, Callback cb) {
        Subscriber *s = new Subscriber;
        s->cb = cb;
        s->self = self;
        subs.push_back(s);
    }

    void fire(void * sender, void* param) {
        Subscriber *s;
        std::vector<Subscriber* >::iterator it;
        for (it = subs.begin(); it != subs.end(); it++){
            //for (int i = 0 ; i<subs.size(); i++){
            //Subscriber *s = subs[i];
            s = *it;
            s->cb(s->self, sender, param);
            /*Callback cb = s->cb;
            cb(s->self, sender, param);*/
        }
    }
    void clear(){
        std::vector<Subscriber* >::iterator it;
        Subscriber *s;
        for (it = subs.begin(); it != subs.end(); it++){
            //for (int i = 0 ; i<subs.size(); i++){
            s = *it;
            delete s;
        };
        subs.clear();
    }
    friend bool operator<(const Event& a, const Event& b)//for crappy map
    {
         return !! (int)(&a<&b);//i dont really care on sorting, why should i?
     }
    Event(){
        name="";
    }
    ~Event() {
        clear();
    }

};


class EventManager{
    std::map<std::string, Event*> events;
public:
    Event *get(std::string name){
        Event * ev = NULL;
        try{
            ev = events.at(name);
        }catch(...){
         //like i care
        }
        if(!ev){//handles error
            ev = new Event(name);
            events.insert(std::pair<std::string, Event*> (name, ev));
        }
        return ev;
    }
    void clear(){
        Event * e;
        std::map<std::string, Event*>::iterator it;
        for (it=events.begin(); it!=events.end(); ++it){
            e = it->second;
            delete e;
        }
        events.clear();
    }
    ~EventManager(){
        clear();
    }
};

static EventManager EM;
void PlayerDied(void* self, void* sender, void* p){
    cout << "self " << (long)self << " sender " << sender << " p " << (char*)p << endl;
}

int main(){
    static std::string evname = "PlayerDied";
  Event* ev = EM.get(evname);
  int a = 60;
  char *c = "c";
  EM.get(evname)->subscribe(NULL, PlayerDied);
  EM.get(evname)->subscribe((void*)1, PlayerDied);
  EM.get(evname)->subscribe((void*)2, PlayerDied);
  EM.get(evname)->subscribe((void*)3, PlayerDied);
  cout <<" probando"<< endl;
  //"dios ayudanos plz.jpg"
  EM.get(evname)->fire((void*)1, c);
}


//http://www.cocos2d-x.org/wiki/EventDispatcher_Mechanism#Custom-Event
