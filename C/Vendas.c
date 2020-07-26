#include <stdlib.h>
#include <stdio.h>
#include "string.h"
#include "Vendas.h"
#include "Produto.h"
#include "Cliente.h"
#include "CatProd.h"
#include "CatClientes.h"

struct venda {
    char* codProd;
    char* codCli;
    double precoUnit;
    int quantidade;
    char* tipo;
    int mes;
    int filial;
};

int getVendaQuant(VendaBasica v){
  return v->quantidade;
}

double getVendaPreco(VendaBasica v){
  return v->precoUnit;
}

int getVendaMes(VendaBasica v){
  return v->mes;
}

char* getVendaTipo(VendaBasica v){
  char* str;
  str = strdup(v->tipo);
  return str;
}

int getVendaFilial(VendaBasica v){
  return v->filial;
}

char* getVendaProd(VendaBasica v){
  char* str;
  str = strdup(v->codProd);
  return str;
}

char* getVendaCliente(VendaBasica v){
  char* str;
  str = strdup(v->codCli);
  return str;
}

int verificaProduto(CatProdutos ctp, char* produto){
  //printf("%s\n",produto);
  Produto p;
  p = createProd(produto);
  return existeProduto(ctp, p);
}

int verificaCliente(CatClientes ctc, char* cliente){
  Cliente c;
  c = createClient(cliente);
  return existeCliente(ctc,c);
}

int validaPreco(double preco){
  return (preco >= 0.0 && preco <= 999.99);
}

int validaQuantidade(int quant){
  return (quant >= 1 && quant <= 200);
}

int validaTipo(char* tipo){
  char* n = "N";
  char* p = "P";
  return (!(strcmp(tipo,n)) || !(strcmp(tipo,p)));
}

int validaMes(int mes){
  return (mes >= 1 && mes <= 12);
}

int validaFilial(int filial){
  return (filial >= 1 && filial <= 3);
}

int valida(CatProdutos ctp, CatClientes ctc, VendaBasica venda){
  int r;
  r = (verificaProduto(ctp, venda->codProd)
      && verificaCliente(ctc, venda->codCli)
      && validaPreco(venda->precoUnit)
      && validaQuantidade(venda->quantidade)
      && validaTipo(venda->tipo)
      && validaMes(venda->mes)
      && validaFilial(venda->filial));
  if (r) {
    produtoComprado(&ctp, venda->codProd, venda->filial);
    clienteCompra(&ctc,venda->codCli,venda->filial);
  }
  return r;
}

VendaBasica mkVendaBasica(char* linhaVenda){
    char* campos[CAMPOSVENDA];
    VendaBasica venda;
    venda = (VendaBasica) malloc(sizeof(struct venda));
    int index = 0;
    char* token = strtok(linhaVenda, " ");
    while (!(token == NULL)) {
        campos[index] = strdup(token);
//        printf("%s\n", token);
        token = strtok(NULL, " ");
        index++;
    }
    venda->codProd    = strdup(campos[0]);
    venda->codCli     = strdup(campos[4]);
    venda->precoUnit  = atof(campos[1]);
    venda->quantidade = atoi(campos[2]);
    venda->tipo       = campos[3];
    venda->mes        = atoi(campos[5]);
    venda->filial     = atoi(campos[6]);
    //printf("Prod = %s\nCli = %s\nPreco = %f\nquant = %d\ntipo = %s\nmes = %d\nfilial = %d\n",venda.codProd,venda.codCli,venda.precoUnit,venda.quantidade,venda.tipo,venda.mes,venda.filial);
    return venda;
}

VendaBasica initVenda(){

  VendaBasica venda;
  venda = (VendaBasica)malloc(sizeof(struct venda));
  venda->codProd = (char*)malloc(sizeof(char));
  venda->codCli = (char*)malloc(sizeof(char));
  venda->tipo = (char*)malloc(sizeof(char));
  return venda;
}

void freeVenda(VendaBasica v) {
  free(v->codProd);
  free(v->codCli);
  free(v->tipo);
  free(v);
}
