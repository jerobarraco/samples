#include <vector>
#include <map>
#include <string>
#include <iostream>
using namespace std;
typedef void (*Callback)(void *self, void *sender, void *param);
//typedef fnc callback;
//typedef function<void(int param)> callback;

struct Subscriber{
    Callback c;
    void* self;
};

class Event {

public:
    std::vector<Subscriber* > subs;//criptions
    Event(const Event &other){
        *this=other;
    }
    Event& operator=( const Event &other){
        clear();
        for (unsigned int i = 0 ; i<other.subs.size(); i++){
            Subscriber *s = other.subs[i];
            subscribe(s->self, s->c);
        };
        return *this;
    }
    void subscribe(void* self, Callback cb) {
        Subscriber *s = new Subscriber;
        s->c = cb;
        s->self = self;
        subs.push_back(s);
    }

    void fire(void * sender, void* param) {
        for (int i = 0 ; i<subs.size(); i++){
            Subscriber *s = subs[i];
            //Subscriber *s = *it;
            Callback cb = s->c;
            cb(s->self, sender, param);
        }
    }

    void clear(){
        for (int i = 0 ; i<subs.size(); i++){
            Subscriber *s = subs[i];
            delete s;
        };
    }
    friend bool operator<(const Event& a, const Event& b)
    {
         return !! (int)(&a<&b);
     };
    Event() { };~Event() {
        clear();
    }
        /*std::vector<Subscriber*>::iterator it;
        std::vector<Subscriber*>* v = &other.subs;

        for (it = v->subs.begin(); it!=v->subs.end(); it++){

        }

    };
    /* bool operator()(Event const& lhs, Event const& rhs) const
    {
         return true;
    }

*/
};


class EventManager{
    std::map<std::string, Event> events;
public:
    Event *get(std::string name){
        Event * ev =NULL;
        try{
            ev = &events[name];
        }catch(...){
            Event e;
            events.insert(std::pair<std::string, Event> (name, e));
        }
        return ev;
    };
    void clear(){
        /*Event * e;
        std::map<std::string, Event>::iterator it;

        for (it=events.begin(); it!=events.end(); ++it){
            Event ev = it->second;
            delete ev;
        }*/
        events.clear();
    }
    ~EventManager(){
        clear();
    }
};

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
