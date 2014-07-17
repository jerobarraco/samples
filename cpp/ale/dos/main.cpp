#include <iostream>

using namespace std;


int main(){
	for( int i = 0; i<10; i++){
		if (i == 9) break;
		if (i == 5) continue;
		cout << i;
	}

	bool flag=true;
	int i = 0;
	while(flag){
		i++;
		if (i==10) flag = false;
		if (i==9) break;//rompe al llegar a 9
		if(i==5) continue;//vuelve al principio si es 5 sin ejecutar lo siguiente
		cout << i;

	}

	do{
	}	while(flag);

	//esto ya no se usa pero por las dudas aca esta

	i = 0;
	iniwhile:
		i++;
		cout <<i;
	if(i<10) goto iniwhile;
	return 0;
}

/*
 * ejemplo de uso
 * lo mejor es el try catch
int stringaEntero(){
	int val;

	if (.....)
		.....
		else goto error;

	if(.....)


	exito:
		return val;

	error:
		return -1;
}

*/
