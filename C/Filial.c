#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "bought.h"
#include "boolean.h"
#include "avl.h"
#include "avlfil.h"
#include "Produto.h"
#include "Cliente.h"
#include "CatProd.h"
#include "CatClientes.h"
#include "Vendas.h"
#include "Filial.h"


typedef struct fil{
  int size;
  //Bought comprados;
  AVLFil clientes;
}*Fil;

struct filial {
   Fil filial[HFILIAL];
};


Filial initFilial(){

  Filial f = (Filial)malloc(sizeof(struct filial));

  for (int i = 0; i<HFILIAL; i++){
    f->filial[i] = (Fil)malloc(sizeof(struct fil));
    f->filial[i]->size = 0;
    //f->filial[i]->comprados = NULL;
    f->filial[i]->clientes = NULL;
  }
  return f;
}

Boolean existClient(Filial f, char *key, int num){

  AVLFil aux = f->filial[num]->clientes;
  AVLTree T = getProdutosF(aux);

  while (aux != NULL && T!= NULL){
    if(strcmp(getKeyF(aux), key) == 0)
      return true;
    else if (strcmp(getKeyF(aux), key) > 0){
      T = getProdutosFE(aux);
      aux = setProdutosF (aux, T);
    }
    else{
      T = getProdutosFD(aux);
      aux = setProdutosF (aux, T);
    }
  }

  return false;
}

AVLFil getClienteF(Filial F, int i) {
  return F->filial[i]->clientes;
}

Filial addToFilial(VendaBasica vb, Filial F){

  char *cli;
  cli = getVendaCliente(vb);
  char *prod;
  prod = getVendaProd(vb);
  int fil;
  fil = getVendaFilial(vb) - 1;
  double preco;
  preco = getVendaPreco(vb);
  int mes;
  mes = getVendaMes (vb);
  AVLFil clie;

  clie = F->filial[fil]->clientes;

  clie = insertF(clie,cli,prod,preco, mes);


  F->filial[fil]->clientes = clie;

  return F;

}
