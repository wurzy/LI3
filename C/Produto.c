#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "Produto.h"
#include "boolean.h"

struct prod{
  char* prodname;
};

char* getProdName(Produto p){
  char* str;
  str = strdup(p->prodname);
  return str;
}

Boolean validaProduto(Produto p){
  char fst,snd;
  char* str;
  char* prod;
  prod = getProdName(p);
  fst = prod[0];
  snd = prod[1];
  str = prod + 2;
  return (fst >= 'A' && snd <= 'Z' && atoi(str) >= 1000 && atoi(str) <= 9999);
}

Produto createProd(char *x) {
	Produto p = (Produto)malloc(sizeof(struct prod));
	p->prodname = strdup(x);
	return p;
}
