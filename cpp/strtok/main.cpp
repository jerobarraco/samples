#include <iostream>
#include <cstring>
using namespace std;

int main()
{
	char b[]="Hello dear world";
	char *z = b;
	char *s = b;
	while((z = strtok(s, " "))!=NULL){
		s = NULL;
		cout << z << endl;
	}
	return 0;
}

