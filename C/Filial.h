#ifndef FILIAL_H
#define FILIAL_H

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "avl.h"
#include "Vendas.h"
#include "boolean.h"
#include "CatProd.h"
#include "CatClientes.h"
#include "Produto.h"
#include "Cliente.h"
#include "avlfil.h"

typedef struct filial* Filial;

#define HFILIAL 3

Filial initFilial();
Filial addToFilial(VendaBasica vb, Filial F);
Boolean existClient(Filial f, char *key, int num);
AVLFil getClienteF(Filial F, int i);

#endif
