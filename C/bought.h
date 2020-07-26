#ifndef BOUGHT_H
#define BOUGHT_H

#include "Lista.h"

#define INIT_SIZE 100

typedef struct bought* Bought;
typedef struct arrComp* ArrayComp;

ArrayComp initArrayComp();
Bought initBought();
Lista boughtToList(Bought* b, int lin, int col);
Lista naoComprados(ArrayComp arr, int lin, int col, int fil);
Lista naoCompradosGlobal(Bought b, int lin, int col);
char** getBoughtArray(Bought b);
int getBoughtUsed(Bought b);
void freeArrayComp(ArrayComp arr);
void freeBought(Bought b);
void insereArrComp(ArrayComp* arr, int fil, char* x);
void insertInBoughtArr(Bought* l, char* x);
void printArrayComp(ArrayComp arr);
void printB (Bought l);

#endif
