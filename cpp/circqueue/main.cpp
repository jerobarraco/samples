#include <iostream>
//theory http://en.wikipedia.org/wiki/Circular_buffer
using namespace std;
typedef int mytype; //so you know you can make a queue out of anything
const unsigned int MAX=5;
unsigned int _qstart=0, _qend=0;
mytype queue[MAX];

void push(const mytype &v){
	if (((_qend+1)%MAX) == _qstart){
		//this might be hard to read, basically is: if the next falls in the same place as the begin, then its full,
		//it just warps on the end using %MAX
		//if you debug, you will notice that there is one item that will never be used,
		//thats a limitation, you can't determinate if its full or emtpy using the same condition "start==end"
		//there are 2 solutions, use different conditions (start==end -> empty, end+1==start -> full)
		//or use a flag, but i dont like flags.
		//though avoiding the % whichs is the reminder of a division probably will give you 1 or 2 cpu cicles
		//using "if" and flags cooooould help you, but there will be no noticeable improvement
		//not even 60 frames per second, (or try it :) )
		return; //queue is full
	}

	queue[_qend++] = v;
	//btw notice here that we are copyng the values BUT!
	//as this is a static array you could simply use the values in the array, passing references or whatever

	_qend %= MAX; //same as if (_qend == MAX){ _qend = 0;}
	//also notice we return nothing, you could return bool, meaning true if it succeded, or false if it failed
	//but in that case you need to modify pop to return the mytype val by reference
}

mytype &pop(){//bool pop(mytype &res)
	mytype res;
	if (_qstart == _qend ){
		return res;//empty
	}
	res = queue[_qstart++];
	//takes one, moves fordward and loops back if it reached the max
	_qstart %= MAX;
	return res;
}

int main(){
	mytype a;
	push(5);
	push(6);
	push(7);
	push(10);
	push(10);
	push(11);
	push(20);
	a = pop();
	push(30);
	a = pop();
	push(40);
	push(50);
	push(60);
	a = pop();
	a = pop();
	a = pop();
	a = pop();
	a = pop();
	a = pop();

	return 0;
}

