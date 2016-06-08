#include <iostream>
#include <vector>
#include <list>
#include <stdlib.h>
#include <algorithm>

int main(int argc, char *argv[]){
	//vector de 200 elementos
	std::vector<int> enta(20);
	std::vector<int>::iterator i = enta.begin();
	srand(time(0));
	for (i = enta.begin(); i != enta.end(); i++){
		*i = rand()%900;
	}

	for (i = enta.begin(); i != enta.end(); i++){
		std::cout << *i<< std::endl;
	}
	std::cout << std::endl;

	/*sort(enta.begin()+5, enta.begin()+15);
	std::reverse(enta.begin()+5, enta.begin()+15);*/

	//ordenar inverso del 5 al 15
	std::sort(
		enta.rend() -15, enta.rend() -5
	);
	for (i = enta.begin(); i != enta.end(); i++){
		std::cout << *i<< std::endl;
	}

	return 0;
}
