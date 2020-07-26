#ifndef LISTA_H
#define LISTA_H

typedef struct lista* Lista;

Lista constructorList(Lista list, int col, int lin, int arr_size);
Lista decreaseListPag(Lista l);
Lista increaseListPag(Lista l);
Lista initLista();
Lista memallocArray(Lista l, int size);
Lista setList(Lista l, int size, char** arr);
Lista setListArray(Lista l, char** arr);
Lista setListColumns(Lista l,int cols);
Lista setListMaxSize(Lista l);
Lista setListPag(Lista l, int pag);
Lista setListRows(Lista l,int rows);
Lista setListSize(Lista l, int size);
Lista setListTotal(Lista l);
char** getListArray(Lista l);
int getListMaxSize(Lista l);
int getListPag(Lista l);
int getListSize(Lista l);
void freeList(Lista list);
void print_list(Lista l);

#endif
