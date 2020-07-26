#ifndef PRODUTO_H
#define PRODUTO_H

#include "Produto.h"
#include "boolean.h"

typedef struct prod* Produto;

char* getProdName(Produto p);
Boolean validaProduto(Produto p);
Produto createProd(char*);

#endif
