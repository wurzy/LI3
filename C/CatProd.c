#include <stdio.h>
#include <stdlib.h>
#include "CatProd.h"
#include "Lista.h"
#include "Produto.h"
#include "avl.h"
#include "boolean.h"
#include "bought.h"
#include "string.h"

typedef CatProdutos HashProdutos[HPROD];

typedef struct hash{
  int size;
  AVLTree tree;
} *Hash;

struct catProdutos{
  int lidosProdutos;
  int validadosProd;
  Hash hashp[HPROD];
};

Boolean existeProduto(CatProdutos,Produto);
CatProdutos addProduct(CatProdutos,Produto);
CatProdutos initCatProdutos();
CatProdutos leProdutos();
int getReadProd(CatProdutos);
int getValidProd(CatProdutos);
int totalProdutos(CatProdutos);
int totalProdutosLetra(CatProdutos,char);
static int hashProdutos(char*);

void printar(CatProdutos ctp, char* x){
  int pos;
  pos = hashProdutos(x);
  printBought(ctp->hashp[pos]->tree);
}

int quantosProdNaoComprados(CatProdutos ctp){
  int i, counter;
  counter = 0;
  for(i=0; i<HPROD; i++)
    avlNaoComprados(ctp->hashp[i]->tree, &counter);
  return counter;
}

Bought prodNuncaComprados(Bought b, CatProdutos ctp){
  int i;
  char fst, snd;
  fst = snd = 'A';
  for(i=0; i<HPROD; i++){
    avlProdNaoCompradosAllFil(ctp->hashp[i]->tree, &b, fst, snd);
    if (snd % 'Z' == 0){
      fst++;
      snd = 'A';
    }
    else snd++;
  }
  return b;
}

ArrayComp produtosNaoComprados(ArrayComp arr, CatProdutos ctp){
  int i;
  char fst, snd;
  fst = 'A';
  snd = 'A';
  for(i=0; i<HPROD; i++){
    avlProdNaoComprados(ctp->hashp[i]->tree, &arr, fst, snd);
    if (snd % 'Z' == 0){
      fst++;
      snd = 'A';
    }
    else snd++;

  }
  return arr;
}

void produtoComprado(CatProdutos *aux, char* prod, int filial){
  CatProdutos ctp = *aux;
  int pos;
  pos = hashProdutos(prod);
  char *str;
  str = strdup(prod +2);
  ctp->hashp[pos]->tree = alteraValorArray(ctp->hashp[pos]->tree, str, filial);
  free(str);
  return;
}

int getValidProd(CatProdutos ctp){
  return ctp->validadosProd;
}

CatProdutos increaseValidProd(CatProdutos ctp){
  ctp->validadosProd++;
  return ctp;
}

int getReadProd(CatProdutos ctp){
  return ctp->lidosProdutos;
}

CatProdutos increaseReadProd(CatProdutos ctp){
  ctp->lidosProdutos++;
  return ctp;
}

CatProdutos initCatProdutos(){

	CatProdutos ctp = (CatProdutos)malloc(sizeof(struct catProdutos));

  ctp->lidosProdutos = 0;
  ctp->validadosProd = 0;

	int i;
  for (i=0; i<HPROD; i++){
    ctp->hashp[i] = (Hash)malloc(sizeof(struct hash));
    ctp->hashp[i]->size = 0;
    ctp->hashp[i]->tree = NULL;
  }
  return ctp;
}

static int hashProdutos(char* x){
  char fst, snd;
  fst = x[0];
  snd = x[1];
  return (26 * (fst % 'A') + snd % 'A');
}

CatProdutos addProduct(CatProdutos ctp, Produto p){

  char *fst;
  char *snd;
  fst = strndup(getProdName(p), 2);
  snd = strdup(getProdName(p) + 2);

  int pos;
  pos = hashProdutos(fst);

  ctp->hashp[pos]->size++;
  ctp->hashp[pos]->tree = insert(ctp->hashp[pos]->tree, snd);

  return ctp;
}

Boolean existeProduto(CatProdutos ctp, Produto p){

	char *prod, *snd, *fst;
	prod = getProdName(p);
	fst = strndup(prod,2);
  snd = strdup(prod + 2);

  int pos = hashProdutos(fst);

  return existValue(ctp->hashp[pos]->tree, snd);
}

int totalProdutos(CatProdutos ctp) {

	int i, total;
	total = 0;

	for(i=0; i<HPROD; i++)
    total += ctp->hashp[i]->size;

	return total;
}

int totalProdutosLetra(CatProdutos ctp, char letter) {

	int pos,i,total;
	pos = letter % 'A';
	total = 0;

	for (i=pos; i<pos+26; i++)
    total += ctp->hashp[i]->size;

	return total;
}

Lista getProdsByLetter(CatProdutos ctp, char letter, int lin, int col){

  int pos, pag, arr_size, j, i,k;
  Lista list;
  char c;
  i = 0;
  pag = 1;
  list = initLista();

  pos = letter % 'A';
  arr_size = totalProdutosLetra(ctp,letter);
  list = constructorList(list,col,lin,arr_size);
  char **array = (char **)malloc(arr_size * sizeof(char *));
  c = 'A';
  for (j=pos,k=0; j<pos+26; j++,k++)
    avlToArray(ctp->hashp[j]->tree,letter,c+k,array,&i);
  list = setListArray(list, array);
  free(array);

  return list;
}
