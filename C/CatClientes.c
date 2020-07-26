#include <stdio.h>
#include <stdlib.h>
#include "CatClientes.h"
#include "string.h"
#include "boolean.h"
#include "Cliente.h"
#include "bought.h"
#include "avl.h"

typedef CatClientes HashClientes[HCLIENTES];

typedef struct hash{
  int size;
  AVLTree tree;
} *Hash;

struct catClientes{
  int lidosClientes;
  int validadosClient;
  Hash hashc[HCLIENTES];
};

Boolean existeCliente(CatClientes, Cliente);
CatClientes addCliente(CatClientes, Cliente);
CatClientes initCatClientes();
CatClientes leClientes ();
int getReadClient(CatClientes);
int getValidClient(CatClientes);
int totalClientes(CatClientes);
int totalClientesLetra(CatClientes, char);
static int hashClientes(char*);

int quantosClieNaoCompraram(CatClientes ctc){
  int counter,i;
  counter = 0;
  for(i=0; i<HCLIENTES; i++)
    avlNaoComprados(ctc->hashc[i]->tree, &counter);
  return counter;
}

Bought comprasTodasFiliais(CatClientes ctc){
  Bought b;
  b = initBought();
  char letter = 'A';
  int i;
  for(i=0; i<HCLIENTES; i++, letter++)
    avlComprasAllFil(ctc->hashc[i]->tree,&b,letter);
  return b;
}

void printComprados(CatClientes ctc){
  printBought(ctc->hashc[25]->tree);
}

ArrayComp clientesSemCompras(ArrayComp arr, CatClientes ctc){
  int i;
  char x;
  for(i=0, x= 'A'; i<HCLIENTES; i++, x++){
    avlClieSemCompras(ctc->hashc[i]->tree, &arr, x);
  }
  return arr;
}

void clienteCompra(CatClientes *x, char* clie, int filial){
  CatClientes ctc = *x;
  int pos;
  pos = hashClientes(clie);
  char *str;
  str = strdup(clie +1);
  ctc->hashc[pos]->tree = alteraValorArray(ctc->hashc[pos]->tree, str, filial);
  free(str);
}

int getValidClient(CatClientes ctc){
  return ctc->validadosClient;
}

CatClientes increaseValidClie(CatClientes ctc){
  ctc->validadosClient++;
  return ctc;
}

int getReadClient(CatClientes ctc){
  return ctc->lidosClientes;
}

CatClientes increaseReadCli(CatClientes ctc){
  ctc->lidosClientes++;
  return ctc;
}

CatClientes initCatClientes(){

	CatClientes ctc = (CatClientes)malloc(sizeof(struct catClientes));

  ctc->lidosClientes = 0;
  ctc->validadosClient = 0;

  for (int i =0; i<HCLIENTES;i++){
    ctc->hashc[i] = (Hash)malloc(sizeof(struct hash));
    ctc->hashc[i]->size = 0;
    ctc->hashc[i]->tree = NULL;
  }
  return ctc;
}

static int hashClientes(char* x){
  char fst;
  fst = x[0];
  return (fst % 'A');
}

CatClientes addCliente(CatClientes ctc, Cliente c){

  char *fst;
  char *snd;
  fst = strndup(getClientName(c), 1);
  snd = strdup(getClientName(c) + 1);

  int pos;
  pos = hashClientes(fst);

  ctc->hashc[pos]->size++;
  ctc->hashc[pos]->tree = insert(ctc->hashc[pos]->tree, snd);

  return ctc;
}

Boolean existeCliente(CatClientes ctc, Cliente c){

	char *client, *fst, *snd;
  client = getClientName(c);
  fst = strndup(client,1);
  snd = strdup(client +1);

  int pos;
  pos = hashClientes(fst);

  return existValue(ctc->hashc[pos]->tree, snd);
}

int totalClientes(CatClientes ctc) {

	int i, total;
	total = 0;

	for(i=0; i<HCLIENTES; i++)
    total += ctc->hashc[i]->size;

	return total;
}

int totalClientesLetra(CatClientes ctc, char letter) {

	int pos;
	pos = letter % 'A';
	return (ctc->hashc[pos]->size);
}
