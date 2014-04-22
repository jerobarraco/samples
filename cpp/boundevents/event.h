#ifndef EVENT_H
#define EVENT_H
/*
 * Event.h
 *
 *  Created on: Apr 14, 2014
 *      Author: nande
 */

#include <vector>
#include <map>
#include <string>
using namespace std;

class Listener; //foward declr

typedef void (Listener::*Callback)(void *sender, void *param);

struct Subscriber{
    Listener *self;
    Callback cb;
};

typedef std::vector<Subscriber*> SVec;

class Event{
public:
    SVec subs;//criptions
    std::string name;
    Event(){ name=""; }
    ~Event(){ clear(); }
    Event(std::string name){ this->name = name; }
    //for map
    Event(const Event &other){ *this=other; }
    Event& operator=(const Event &other){//for crappy map
        clear();
        for (unsigned int i = 0 ; i<other.subs.size(); i++){
            Subscriber *s = other.subs[i];
            connect(s->self, s->cb);
        };
        name = other.name;
        return *this;
    }
    void connect(Listener* self, Callback cb) {
       Subscriber *s = new Subscriber;
       s->cb = cb;
       s->self = self;
       subs.push_back(s);
    }
    void fire(void * sender, void* param) {
        Subscriber *s;
        SVec::iterator it;
        for (it = subs.begin(); it != subs.end(); it++){
            s = *it;
            Listener *lis = s->self;
            Callback cb = s->cb;
            //C++ if once i had some faith in you, today you've finished making your own coffin.. you suck monkey ass
            (lis->*cb)(sender, param);
        }
    }
    void clear(){
        SVec::iterator it;
        Subscriber *s;
        for (it = subs.begin(); it != subs.end(); it++){
            s = *it;
            if(s)
                delete s;
        };
        subs.clear();
    }
    friend bool operator<(const Event& a, const Event& b){
        //for crappy map
         return !! (int)(&a<&b);//i dont really care on sorting, why should i?
    }
};

typedef std::map<std::string, Event*> EMap;

class EventManager{
    EMap events;
public:
    Event *get(std::string name){
        Event * ev = NULL;
        //ev = events[name];//using this we get a segmentation fault :)
        try{
            EMap::iterator pair = events.find(name);
            if(pair != events.end()){
                ev = pair->second;
            }
            //at doesnt exists and [] fails like shit
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


extern EventManager EM;
/*
EventManager EM;
void PlayerDied(void* self, void* sender, void* p){
    cout << "self " << (long)self << " sender " << sender << " p " << (char*)p << endl;
};
int main(){
  Event* ev = EM.get("PlayerDied");
  int a = 60;
  char *c = "c";
  ev->subscribe(NULL, PlayerDied);
  cout <<" probando"<< endl;
  //"dios ayudanos plz.jpg"
  ev->fire((void*)1, c);
}
*/

//ejemplo de evento sin manager extern Event playerDied;

extern const char* EV_GAMEOVER;

class Listener{
public:
    Listener(){}
    Event* connect(string name, Callback cb){
        Event* e= EM.get(name);
        if(!e) return NULL;
        e->connect(this, cb);
        return e;
    }
    Event* getEvent(string name){
        return EM.get(name);
    }
};
#endif // EVENT_H
