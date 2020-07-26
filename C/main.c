#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include "CatClientes.h"
#include "CatProd.h"
#include "Lista.h"
#include "Produto.h"
#include "avl.h"
#include "AVLFact.h"
#include "Faturacao.h"
#include "main.h"
#include "print.h"
#include "bought.h"
#include "Vendas.h"
#include "queries.h"
#include "Filial.h"
#include "avlfil.h"

struct svg{
  CatProdutos svg_ctp;
  CatClientes svg_ctc;
  Faturacao svg_fat;
  Filial svg_fil;
};

SVG initSVG(){
  SVG s;
  s = (SVG)malloc(sizeof(struct svg));
  s->svg_ctc = initCatClientes();
  s->svg_ctp = initCatProdutos();
  initFaturacao(s->svg_fat);
  s->svg_fil = initFilial();
  return s;
}

SVG readVendas(SVG sv, int* lidosVendas, int* validosVendas){

  int size = VENDAS_SIZE;
  FILE *fp;
  char s[100];
  VendaBasica venda;
  venda = NULL;
  fp = fopen("Vendas_1M.txt","r");
  if (fp == NULL)
    perror("O ficheiro não existe");
  char* str;
  while (fgets(s,size,fp)){
    str = strdup (strtok (s,"\n\r"));
    venda = mkVendaBasica(str);
    (*lidosVendas)++;
    if(valida(sv->svg_ctp,sv->svg_ctc,venda)) {
      (*validosVendas)++;
      sv->svg_fil = addToFilial(venda,sv->svg_fil);
      addToFaturacao(venda,sv->svg_fat);
    }
  }
  freeVenda(venda);
  return sv;
}

CatProdutos readProd (CatProdutos ctp){

  int size = FILE_SIZE;
  FILE *fp;
  char s[size];
  char* str;
  Produto p;
  fp = fopen("Produtos.txt","r");
  if (fp == NULL)
    perror("Ficheiro de produtos não se encontra na pasta atual.");

  while (fgets(s,size,fp)){
    str = strdup (strtok (s,"\n\r"));
    p = createProd(str);

    if (validaProduto(p)){
      ctp = addProduct(ctp,p);
      ctp = increaseValidProd(ctp);
    }
    ctp = increaseReadProd(ctp);
  }
  fclose(fp);

  return ctp;
}

CatClientes readClients (CatClientes ctc){

  int size = FILE_SIZE;

  FILE *fp;
  char s[size];
  char* str;
  Cliente c;
  fp = fopen("Clientes.txt","r");
  if (fp == NULL)
    perror("Ficheiro de clientes não se encontra na pasta atual.");

  while (fgets(s,size,fp)){
    str = strdup (strtok (s,"\n\r"));
    c = createClient(str);

    if (validaCliente(c)){
      ctc = addCliente(ctc,c);
      ctc = increaseValidClie(ctc);
    }
    ctc = increaseReadCli(ctc);
  }

  fclose(fp);
  return ctc;
}


void queries(SVG s){
  int control,lidosVendas,validosVendas;
  lidosVendas = 0;
  validosVendas = 0;
  ArrayComp arr = NULL;
  control = 0;
  char buff[2048], letter;
  while((buff[0] != 'Q' && buff[0] != 'q') || control == 0){
    menu();
    fgets(buff, 2048 , stdin);
    if (buff[0] == '1')
      control++;
    if (control){
      switch(buff[0]){
        case '1':
          if (control > 1){
            printf("Os ficheiros já foram lidos!\n");
            sleep(2);
          }
          else{
            s->svg_ctp = readProd(s->svg_ctp);
            s->svg_ctc = readClients(s->svg_ctc);
            s = readVendas(s,&lidosVendas,&validosVendas);
            query1(s->svg_ctp,s->svg_ctc,lidosVendas,validosVendas);
          }
          break;
        case '2':
          system("clear");
          printf("Insira a letra que pretende procurar: ");
          scanf("%c",&letter);
          query2(s->svg_ctp,letter);
          break;
        case '3':
          break;
        case '4':
          //arr = initArrayComp();
          //arr = produtosNaoComprados(arr,s->svg_ctp);
          query4(s->svg_ctp);
          break;
        case '5':
          query5(s->svg_ctc);
          break;
        case '6':
          query6(s->svg_ctp, s->svg_ctc);
          break;
        case '7':
          query7(s->svg_fil);
          break;
        case '8':
          query8(s->svg_fat);
          break;
        case '9':
          query9(s->svg_fil);
          break;
      }
    }
    else{
      printf("Necessita de fazer a leitura dos ficheiros primeiro!\n");
      sleep(2);
    }
  }
}

int main(){
  SVG s;
  s = initSVG();
  queries(s);
  return 0;
}
