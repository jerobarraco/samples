#include "avion.h"

avion::avion (int cant_ruedas){
    ruedas = new rueda[cant_ruedas];
}

avion::~avion(){
    delete[] ruedas;
}
