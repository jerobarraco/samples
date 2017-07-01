#include "stdlib.h"
#include <iostream>
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

long long fib(int i){
    // caso de corte
    long long res = 1;
    if (i < 1){
        res = 1;
    }else{
        // caso recursivo
        res = fib(i-1) + fib(i-2);
    }

    return res;
}

long long fibiter(int pos){
    long long res = 0;
    long long ant=1, ant2=0;

    for (int i = 0; i<pos+1; i++){
        res = ant + ant2;

        ant2 = ant;
        ant = res;
    }

    return res;
}


void quicksort(int v[], int pi, int pf){
    if(pi >= pf){
        return;
    }else{
        int pivot = pf;
        int pared = pi-1;
        for (int j = pi; j<pivot; j++){
            if (v[j]< v[pivot]){
                pared ++;
                int aux = v[j];
                v[j] = v[pared];
                v[pared] = aux;
            }
        }

        pared ++ ;
        int aux2 = v[pivot];
        v[pivot] = v[pared];
        v[pared] = aux2;

        quicksort(v, pi, pared-1);
        quicksort(v, pared+1, pf);
    }
    return;
}

using namespace std;
int main(int argc, char *argv[]){
	int vec[] = {11,12,3,4,6,14,15};
	int max = Maximo(vec, 7);
    cout << "el max es "<< max<< endl;

    quicksort(vec, 0, 6);
    for (int i = 0; i<7; i++){
        cout << vec[i]<<", ";
    }

    std::cout << "Fibonacci de 40 " << fibiter(40) << std::endl;
    std::cout << "Fibonacci de 45 " << fibiter(45) << std::endl;
    std::cout << "Fibonacci de 50 " << fibiter(50) << std::endl;

    std::cout << "Fibonacci de 40 " << fib(40) << std::endl;
    std::cout << "Fibonacci de 45 " << fib(45) << std::endl;
    std::cout << "Fibonacci de 50 " << fib(50) << std::endl;
}
