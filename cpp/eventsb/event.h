#ifndef EVENT_H
#define EVENT_H

#include <vector>
#include <map>
#include <string>
using namespace std;

/* I tried using an approach to be able to make callbacks into bound methods, but it sucked a lot
 * because i still need to store the "this" pointer at some structure (subscriber) and also to be able to
 * call a bounded method i need it to belong to a certain class (really c++¿) so basically i needed an extra
 * class just to be able to do (class->*cb)( ) which sucked like a black hole (seriously whats with that syntax!)
 * it also prevented me to create callbacks to static methods..
 * so i rather just do Board* diz = (Board*) sender; and fuck the world
 * also it went against the K.I.S.S. principle.
 *
 */
typedef void (*Callback)(void *self, void *sender, void *param);

struct Subscriber{
    Callback cb;
    void* self;
};

typedef std::vector<Subscriber* > SVec;

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
    void unsubscribe(void *self){
        SVec::iterator it;
        Subscriber *s;
        for (it = subs.begin(); it != subs.end(); it++){
            s = *it;
            if(self){
                if (s->self == self){
                    subs.erase(it);
                    break;
                };
            };
        }
    }
    void fire(void * sender, void* param) {
        Subscriber *s;
        SVec::iterator it;
        for (it = subs.begin(); it != subs.end(); it++){
            s = *it;
            s->cb(s->self, sender, param);
        }
    }
    void clear(){
        SVec::iterator it;
        Subscriber *s;
        for (it = subs.begin(); it != subs.end(); it++){
            s = *it;
            delete s;
        };
        subs.clear();
    }
    friend bool operator<(const Event& a, const Event& b){
        //for crappy map
         return !! (int)(&a<&b);//i dont really care on sorting, why should i?
    }
    Event(){
        name="";
    }
    ~Event() {
        clear();
    }
};

typedef std::map<std::string, Event*> EMap;

class EventManager{
    EMap events;
public:
    Event *subscribe(void* self, std::string name, Callback cb){
        Event *e = this->get(name);
        if (!e) return NULL;
        e->subscribe(self, cb);
        return e;
    }
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
#endif // EVENT_H
