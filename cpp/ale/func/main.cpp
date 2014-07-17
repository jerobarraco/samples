#include <iostream>

using namespace std;

int suma(int a, int b=10){
	return a+b;
}

int suma(float a, float b){
	return (int)a+b;
}

//Class vector:
//	x=0
//	y=0
//	z=0
//	def __add__(self, otro):
//		res = vector()
//		res.x = self.x+otro.x
//		res.y = self.y+otro.y
//		res.z = self.z+otro.z
//		return res
//	def __mult__

//v1 = vector()
//v2 = vector

//v1+v2 ----> v1.__add__(v2)
//v3 = v1+v2
//v1*3 ----> v1.__mult__(3)

int main(){
	int x,y ;
	cin >> x >> y;
	cout << suma(x, y) << endl;
	float z;
	cin >> z;
	cout << suma(z, z);
	//el valor dentro del switch es tipo basico
	//todos los casos tienen que ser constantes
	const int v= 5;
	switch (x){
		case 9: case 10:
			break;
		case 20:
			break;
		case v:
			cout << "es v"<<endl;
		default:
			break;
	}
	return 0;

	//operador triuno
//	boolean
//		==
//		!=   es similar pero no igual a !( x == b)
//		>=
//		<=
//		! unario


	bool igual;
	igual = (x==y);
	if(igual);

//	if(x>0)
//	igual = x>0 ? x==y: false;
//	if(x>0){
//		igual = x==y;
//	}else{
//		igual = false;
//	}

	//valor absoluto
	int a, abs ;
	cin >> a;
	if(a>=0){
		abs = a;
	}else{
		abs = -a;
	}
	//retorna		condicion	?	valorverd	:	valorf;
	abs				=		a>=0		?	a					:	-a;
//la condicion es un valor booleano, recordar porque hay miles de formas de obtener un valor boolean
//valorverd y valorf son expresiones
	abs = a>=0 ? a : -a;
}

