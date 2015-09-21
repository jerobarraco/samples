#include "stdlib.h"
int Maximo(int v[], int n){
	if(n==1){
		return v[0];
	}else{
		int max_aux = Maximo(v, n-1);
		if (v[n-1]>max_aux){
			return v[n-1];
		}else{
			return max_aux;
		}
	}
}

int main(int argc, char *argv[]){
	int vec[] = {11,12,3,4,6,14,15};
	int max = Maximo(vec, 7);
	//cout << "el max es "<< max;
}
