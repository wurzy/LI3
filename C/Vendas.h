#ifndef VENDAS_H
#define VENDAS_H

#include "Produto.h"
#include "Cliente.h"
#include "CatProd.h"
#include "CatClientes.h"
#define CAMPOSVENDA 7
#define VENDAS_SIZE 100

typedef struct venda* VendaBasica;

VendaBasica mkVendaBasica(char* linhaVenda);
VendaBasica initVenda();
char* getVendaCliente(VendaBasica v);
char* getVendaProd(VendaBasica v);
char* getVendaTipo(VendaBasica v);
double getVendaPreco(VendaBasica v);
int getVendaFilial(VendaBasica v);
int getVendaMes(VendaBasica v);
int getVendaQuant(VendaBasica v);
int valida(CatProdutos ctp, CatClientes ctc, VendaBasica venda);
int validaPreco(double preco);
int validaQuantidade(int quant);
int validaTipo(char* tipo);
int verificaCliente(CatClientes ctc, char* cliente);
int verificaProduto(CatProdutos ctp, char* produto);
void leVendas();
void validaVendas();
void freeVenda(VendaBasica v);
#endif
