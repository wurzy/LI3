#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Faturacao.h"
#include "boolean.h"
#include "AVLFact.h"
#include "avl.h"
#include "boolean.h"
#include "CatClientes.h"
#include "CatProd.h"
#include <unistd.h>
#include "Lista.h"
#include "print.h"
#include "Filial.h"
#include "avlfil.h"

void query1(CatProdutos ctp, CatClientes ctc, int lv, int vv){
  int prod_lidos, prod_validos;
  int clie_lidos, clie_validos;
  prod_lidos = getReadProd(ctp);
  prod_validos = getValidProd(ctp);
  clie_lidos = getReadClient(ctc);
  clie_validos = getValidClient(ctc);

  system("clear");
  puts("-->Ficheiro: Produtos.txt");
  printf("%d produtos lidos.\n",prod_lidos);
  printf("%d produtos válidos.\n\n",prod_validos);
  puts("-->Ficheiro: Clientes.txt");
  printf("%d clientes lidos.\n",clie_lidos);
  printf("%d clientes válidos.\n\n",clie_validos);
  puts("-->Ficheiro: Vendas_1M.txt");
  printf("%d vendas lidas.\n",lv);
  printf("%d vendas válidas.\n\n",vv);
  char op;
  printf("Pressione qualquer tecla para voltar para o menu. ");
  scanf("%s",&op);
}


void query2(CatProdutos ctp, char letter){
  Lista list;
  int col,lin;
  linhasColunas(&lin,&col);
  list = getProdsByLetter(ctp,letter,lin,col);
  print_list(list);
}

void query4(CatProdutos ctp){
  Lista list;
  ArrayComp arr;
  Bought b;
  list = initLista();
  int fil,lin,col;
  system("clear");
  printf("Pretende obter informação de que filial?\n");
  puts("[1] Filial 1");
  puts("[2] Filial 2");
  puts("[3] Filial 3");
  puts("[4] Global");
  scanf("%d",&fil);
  linhasColunas(&lin,&col);
  switch(fil){
    case 1:
    case 2:
    case 3:
      arr = initArrayComp();
      arr = produtosNaoComprados(arr,ctp);
      list = naoComprados(arr,lin,col,fil);
      freeArrayComp(arr);
      break;
    case 4:
      b = initBought();
      b = prodNuncaComprados(b,ctp);
      list = naoCompradosGlobal(b,lin,col);
      freeBought(b);
      break;
    default:
      puts("Opção inválida");
      return ;
      break;
  }
  print_list(list);
  freeList(list);
}

void query5(CatClientes ctc){
  int lin,col,arr_size;
  char** newArr;
  Bought b;
  Lista list;

  list = initLista();
  b = initBought();

  linhasColunas(&lin,&col);
  b = comprasTodasFiliais(ctc);
  arr_size = getBoughtUsed(b);

  list = constructorList(list,col,lin,arr_size);
  newArr = getBoughtArray(b);
  list = setListArray(list,newArr);
  print_list(list);

  freeList(list);
}

void query6(CatProdutos ctp, CatClientes ctc){
  system("clear");
  int prod, clie;
  prod = quantosProdNaoComprados(ctp);
  clie = quantosClieNaoCompraram(ctc);
  printf("Não realizaram compras %d clientes\n", clie);
  printf("Não foram comprados %d produtos\n", prod);
  puts("A voltar para o menu em 5 segundos!");
  sleep(5);
}

void query8 (Faturacao p) {
  double tot, totaux;
  int vendas, vendasaux, mes1, mes2;
  char xd;
  tot = 0;
  totaux = 0;
  vendas = 0;
  vendasaux = 0;
  system("clear");
  printf("Insira o primeiro mês: ");
  scanf("%d",&mes1);
  printf("Insira o segundo mês: ");
  scanf("%d",&mes2);
  int i;
  printf("Verificando intervalo...\n");
  if(mes1>mes2 || mes1<=0 || mes1>12 || mes2<=0 || mes2>12) {
    printf("Erro. Insira um intervalo correto.\n");
    return;
  }
  printf("OK.\nRESULTADOS:\n");
  // 0-11 -> 1-12
  for(i = mes1-1; i<mes2 ; i++) {
    totaux += totalFatMesTipo(p,i,"N");
    totaux += totalFatMesTipo(p,i,"P");
    vendasaux += getTotVendas(p,i);
    printf("Mês [%d]:\n\tTotal Faturação: %.2f €\n", i+1, totaux);
    tot+=totaux;
    vendas+=vendasaux;
    totaux=0;
    printf("\tTotal Vendido:   %d Unidades\n\n", vendasaux);
    vendasaux=0;
  }
  printf("NO TOTAL:\n\tFaturado:        %.2f €\n\tVendido:         %d Unidades\n", tot, vendas);
  printf("Clique uma tecla qualquer para saír\n");
  scanf("%s",&xd);
}



void query7 (Filial  F){
  system("clear");
  char cli[6], xd;
  printf("Indique o Cliente: ");
  scanf("%s",cli);
  int x = 0;
  //printf("%s\n",cli);
  AVLFil aux;
  for (int mes = 1; mes<13 ; mes++){
    x=0;
    printf("Mês [%d]:\n", mes);
  for(int i = 0; i<HFILIAL; i++){
    aux = getClienteF(F,i);
    while(aux!=NULL){
      if(strcmp(getKeyF(aux), cli) == 0){
        x += contaClientes(getProdutosF(aux),mes);
        printf("\tFilial [%d] foram comprados: %d\n", i+1, contaClientes(getProdutosF(aux),mes));
        break;
      }

      else if (strcmp(getKeyF(aux), cli) > 0)
        aux = getAVLFilE(aux);

      else
        aux = getAVLFilD(aux);
    }
    if(i==2) {
      printf("\tForam comprados no TOTAL:   %d\n",x);
    }
  }
  printf("\n");
}
  printf("Clique uma tecla qualquer para saír\n");
  scanf("%s",&xd);
}


static void inorderQ9 (AVLFil a , char* p, int fil){

  if (a!=NULL){
    inorderQ9(getAVLFilE(a), p, fil);

    if(existValue(getProdutosF(a),p))
      printf("%s\n", getKeyF(a));

    inorderQ9(getAVLFilD(a), p, fil);
  }

}

void query9 (Filial F){
  system("clear");
  char p[7],xd;
  int fil;
  printf("Introduzir um produto: ");
  scanf("%s",p);
  printf("Introduzir uma filial: ");
  scanf("%d",&fil);
  inorderQ9(getClienteF(F,fil-1), p ,fil-1);
  printf("Clique uma tecla qualquer para saír\n");
  scanf("%s",&xd);
}

static void inorderQ12(AVLTree T, double *prim, double *seg, double *ter, char** fst, char** snd, char** thr){

  if (T!=NULL){
    inorderQ12(getAVLE (T), prim, seg, ter, fst, snd, thr);

    if (getPrecoA(T)>*prim){
      *ter = *seg;
      *thr = *snd;
      *seg = *prim;
      *snd = *fst;
      *prim = getPrecoA(T);
      *fst = getKeyA(T);
    }
    else if (getPrecoA(T)>*seg){
      *ter = *seg;
      *thr = *snd;
      *seg = getPrecoA(T);
      *snd = getKeyA(T);
    }
    else if (getPrecoA(T)<*ter){
      *ter = getPrecoA(T);
      *thr = getKeyA(T);
    }

    inorderQ12(getAVLD(T), prim, seg, ter, fst, snd, thr);
  }
}

void query12 (Filial F, char *cli){

  AVLFil aux;

  char *fst="", *snd="", *thr="";
  double prim=0, seg=0, ter=0;

  for (int i = 0; i<HFILIAL; i++){
    aux = getClienteF(F,i);

    while(aux!=NULL){
      if(strcmp(getKeyF(aux), cli) == 0){
        inorderQ12(getProdutosF(aux), &prim, &seg, &ter, &fst, &snd, &thr);
        break;
      }
      else if (strcmp(getKeyF(aux), cli) > 0)
        aux = getAVLFilE(aux);

      else
        aux = getAVLFilD(aux);

    }
  }

  printf("Código de produto(Preço)\n%s(%lf)\n%s(%lf)\n%s(%lf)\n",
        fst, prim, snd, seg, thr, ter);
}
