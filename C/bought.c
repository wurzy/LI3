#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include "bought.h"
#include "Lista.h"

struct arrComp{
  Bought* arrayComprados;
};

struct bought{
  int size;
  int used;
  char** array;
};

void freeBought(Bought b){
  free(b->array);
  free(b);
}

Lista boughtToList(Bought* b, int lin, int col){
  Lista list;
  int arr_size;
  arr_size = (*b)->used;

  list = initLista();
  list = constructorList(list,col,lin,arr_size);
  list = setListArray(list,(*b)->array);

  return list;
}

int getBoughtUsed(Bought b){
  return b->used;
}

char** getBoughtArray(Bought b){
  int i;
  char** array;
  array = (char**)malloc(sizeof(char*) * b->used);
  for(i=0; i<b->used;i++)
    array[i] = strdup(b->array[i]);
  return array;
}

void insertInBoughtArr(Bought* l, char* x){
  if ((*l)->used >= (0.99 * (*l)->size)){
    (*l)->size += INIT_SIZE;
    (*l)->array = (char**)realloc((*l)->array,sizeof(char*) * (*l)->size);
  }
  (*l)->array[(*l)->used++] = strdup(x);
}

Bought initBought(){
  Bought b;
  b = (Bought)malloc(sizeof(struct bought));
  b->size = INIT_SIZE;
  b->used = 0;
  b->array = (char**)malloc(sizeof(char*)*INIT_SIZE);
  return b;
}

void insereArrComp(ArrayComp* y, int fil, char* x){

  ArrayComp arr = *y;

  if(arr->arrayComprados[fil]->used >= (0.99*arr->arrayComprados[fil]->size)){
    arr->arrayComprados[fil]->size += INIT_SIZE;
    arr->arrayComprados[fil]->array = (char**)realloc(arr->arrayComprados[fil]->array,sizeof(char*) * arr->arrayComprados[fil]->size);
  }
  arr->arrayComprados[fil]->array[arr->arrayComprados[fil]->used++] = strdup(x);
}

ArrayComp initArrayComp(){
  ArrayComp arr;
  arr = (ArrayComp)malloc(sizeof(struct arrComp));
  arr->arrayComprados = (Bought*)malloc(sizeof(Bought) * 3);
  int i;
  for(i=0; i<3; i++){
    arr->arrayComprados[i] = (Bought)malloc(sizeof(struct bought));
    arr->arrayComprados[i]->size = INIT_SIZE;
    arr->arrayComprados[i]->used = 0;
    arr->arrayComprados[i]->array = (char**)malloc(sizeof(char*) * INIT_SIZE);
  }
  return arr;
}

void printArrayComp(ArrayComp arr){
  int i;
  for(i=0; i<3; i++){
    int j;
    for(j=0; j<arr->arrayComprados[i]->used; j++)
      printf("%s\n", arr->arrayComprados[i]->array[j]);
  }
}

void freeArrayComp(ArrayComp arr){
  int i;
  for(i=0; i<3; i++)
    free(arr->arrayComprados[i]->array);
  free(arr->arrayComprados);
  free(arr);
}

Lista naoComprados(ArrayComp arr, int lin, int col, int fil){
  Lista list;
  int arr_size;

  arr_size = arr->arrayComprados[fil-1]->used;

  list = initLista();
  list = constructorList(list,col,lin,arr_size);
  list = setListArray(list,arr->arrayComprados[fil-1]->array);

  return list;
}

Lista naoCompradosGlobal(Bought b, int lin, int col){
  Lista list;
  int pag, arr_size,i;
  pag = 1;
  arr_size = getBoughtUsed(b);
  char **array;
  array = (char**)malloc(sizeof(char*) * arr_size);
  for(i=0; i<arr_size; i++)
    array[i] = strdup(b->array[i]);

  list = initLista();
  list = setListColumns(list,col);
  list = setListRows(list,lin);
  list = setListMaxSize(list);
  list = memallocArray(list,arr_size);
  list = setListSize(list,arr_size);
  list = setListPag(list,pag);
  list = setListTotal(list);
  list = setListArray(list, array);

  return list;
}
