#ifndef AUDIA3_H
#define AUDIA3_H
#include "auto.h"

class Estereo{};

class AudiA3 : public Auto{//Audi es un CASO ESPECIAL de auto, por eso es una herencia
	Estereo sony;
public:
	AudiA3();
	void turbo();
};

#endif // AUDIA3_H
