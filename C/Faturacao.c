#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Faturacao.h"
#include "boolean.h"
#include "AVLFact.h"
#include <math.h>
//#include "Vendas.h"

struct mes{
  int totvendas;
  //double totfaturado;
  AVLFact n;
  AVLFact p;
};

void printFaturacao(Faturacao fat){
  int i;
  for(i=0; i<12; i++)
    inorderFat(fat[i]->n);
}

void initFaturacao(Faturacao faturacao) {
  for(int i = 0; i <12; i++) {
    faturacao[i] = malloc(sizeof(struct mes));
    faturacao[i]->n = NULL;
    faturacao[i]->p = NULL;
    faturacao[i]->totvendas = 0;
    //faturacao[i]->totfaturado = 0;

  }
}

void addToFaturacao (VendaBasica vb, Faturacao faturacao) {
  int qtd, mes;
  char *tip,*prd,typ;
  double prc;
  qtd = getVendaQuant(vb);
  prc = getVendaPreco(vb);
  mes = getVendaMes(vb) -1;
  tip = getVendaTipo(vb);
  typ = tip[0];
  prd = getVendaProd(vb);
  //printf("GOT: %s\n", prd);
  if (typ == 'N'){
    faturacao[mes]->n = insertFat(faturacao[mes]->n,prd,qtd,prc);
  }
  else {
    faturacao[mes]->p = insertFat(faturacao[mes]->p,prd,qtd,prc);
  }
  faturacao[mes]->totvendas += qtd;
  //faturacao[mes]->totfaturado += prc*qtd;
}

double totalFatMesTipo (Faturacao p, int mes, char *tipo) {
  char c;
  double total;
  total = 0;
  c = tipo[0];
  if (c=='N') {
    total+=fatInType(p[mes]->n);
  }
  else {
    total+=fatInType(p[mes]->p);
  }
  return total;
}

int getTotVendas(Faturacao p, int i) {
  return p[i]->totvendas;
}
