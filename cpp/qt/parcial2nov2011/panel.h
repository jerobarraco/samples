#include <vector>

#ifndef PANEL_H
#define PANEL_H
using namespace std;
class Panel
{
    vector< vector<bool> > matriz;

public:
    Panel(int m, int n);

    void encender(int i, int j);
    void apagar(int i, int j);
    bool encendido(int i, int j);
};

#endif // PANEL_H
