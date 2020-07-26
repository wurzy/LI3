#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Lista.h"

struct lista{
  int size;
  int pagina;
  int totalPag;
  int columns;
  int rows;
  int maxSize;
  char** array;
};

void freeList(Lista list){
  free(list->array);
  free(list);
}

Lista constructorList(Lista list, int col, int lin, int arr_size){
  int totalPags;
  list->columns = col;
  list->rows = lin;
  list->maxSize = lin * col;
  list->pagina = 1;
  list->size = arr_size;
  totalPags = list->size / list->maxSize;
  if (list->size % list->maxSize) totalPags++;
  list->totalPag= totalPags;
  list->array = (char **)malloc(arr_size * sizeof(char *));

  return list;
}

Lista setListArray(Lista list, char** listArray){
  int i;
  for(i=0; i<list->size; i++)
    list->array[i] = strdup(listArray[i]);
  return list;
}

Lista memallocArray(Lista list, int size){
  list->array = (char **)malloc(size * sizeof(char *));
  return list;
}

Lista setListMaxSize(Lista list){
  list->maxSize = list->columns * list->rows;
  return list;
}

Lista setListRows(Lista list, int rows){
  list->rows = rows;
  return list;
}

Lista setListColumns(Lista list, int col){
  list->columns = col;
  return list;
}

int getListSize(Lista list){
  return list->size;
}

Lista setListSize(Lista list, int newSize){

  list->size = newSize;
  return list;
}

Lista decreaseListPag(Lista list){

  if(list->pagina == 1)
    return list;
  else
    list->pagina--;
  return list;
}

Lista increaseListPag(Lista list){

  if(list->pagina == list->totalPag)
    return list;
  else
    list->pagina++;
  return list;
}

int getListPag(Lista list){
  return list->pagina;
}

Lista setListPag(Lista list, int newPag){
  list->pagina = newPag;
  return list;
}

int getListTotal(Lista list){
  return list->totalPag;
}

Lista setListTotal(Lista list){

  int totalPags;
  totalPags = list->size / list->maxSize;
  if (list->size % list->maxSize) totalPags++;

  list->totalPag= totalPags;

  return list;
}

char** getListArray(Lista list){
  char** newArray= (char**)malloc(sizeof(char*) * list->size);
  int i;
  for(i=0; i<list->size; i++)
    newArray[i] = strdup(list->array[i]);
  return newArray;
}

Lista setList(Lista list, int size, char** array){
  list->size = size;
  list->pagina = 1;
  list = setListTotal(list); //Retorna o numero total de paginas
  int i;
  for (i=0; i<list->size; i++){
    list->array[i] = strdup(array[i]);
  }
  return list;
}

void print_list(Lista list){

  int pag, initPos, fst,i,j;
  char opcao;
  do{
    system("clear");
    initPos = (list->pagina -1) * list->maxSize;
    if (list->columns == 10)
      printf("\t\t\t  Pagina [%d] de [%d]\n\n", list->pagina, list->totalPag);
    if (list->columns == 15)
      printf("\t\t\t\t\t\t  Pagina [%d] de [%d]\n\n", list->pagina, list->totalPag);
    if (list->columns == 20)
      printf("\t\t\t\t\t\t\t\t      Pagina [%d] de [%d]\n\n", list->pagina, list->totalPag);

    for(j=0; j<list->rows && initPos < list->size; j++){
      for(i=0; i<list->columns && initPos < list->size; i++){
        printf("%s\t", list->array[initPos++]);
      }
      printf("\n");
    }
    printf("\nTotal de elementos: %d\n", list->size);
    printf("\n[N]EXT | [P]REVIOUS | [Q]UIT ");
    scanf("%s",&opcao);
    switch(opcao){
      case 'P':
      case 'p':
        if (list->pagina > 1)
          list->pagina--;
        break;
      case 'N':
      case 'n':
        if (list->pagina  != list->totalPag)
        list->pagina++;
        break;
      default:
        break;
    }
  } while (opcao != 'q' && opcao != 'Q');
  printf("\n");
}

Lista initLista(){
  Lista list = (Lista)malloc(sizeof(struct lista));
  list->size = list->pagina = list->totalPag = list->maxSize = 0;
  list->columns = list->rows = 0;
  return list;
}
