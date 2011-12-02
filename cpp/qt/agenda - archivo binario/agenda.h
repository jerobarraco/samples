#include <QString>
#include <QFile>
#include <QDataStream>

typedef struct{
    char nya[120];
    char email[250];
    unsigned int anac;
} registro;

#ifndef AGENDA_H
#define AGENDA_H

class Agenda
{
    private:
        QFile archivo;
    public:
        //primero nos ocupamos de construir y destruir el objeto
        Agenda(QString narchivo);
        ~Agenda();

        int cantEntrada();
        bool actEntrada(unsigned int nentrada, registro v);

        void agregarFinal(registro v);
        bool valorEntrada(unsigned int nentrada, registro &v);
};

#endif // AGENDA_H
