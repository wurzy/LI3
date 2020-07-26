#ifndef FAT_H
#define FAT_H
#include "boolean.h"
#include "Vendas.h"

typedef struct avlFact* AVLFact;
typedef struct mes* Faturacao[12];


void addToFaturacao (VendaBasica vb, Faturacao faturacao);
void initFaturacao(Faturacao faturacao);
void printFaturacao(Faturacao fat);
int getTotVendas(Faturacao p, int i);
double totalFatMesTipo(Faturacao p, int i, char *c);

#endif
