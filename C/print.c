#include <stdio.h>
#include <stdlib.h>
#include "print.h"
#include "bought.h"

//void print_bought(Bought* l){
//  int i,j,size;
//  for(i=0; i<3; i++)
//    printBoughtArray(l[i]);
//}

void linhasColunas(int* lin, int* col){

  system("clear");
  printf("Caso não escolha um dos valores facultados será escolhido o 20.\n");
  printf("Quantas linhas quer que a tabela tenha?  10 | 15 | 20 : ");
  scanf("%d",lin);
  switch(*lin){
    case 10:
      (*lin) = 10;
      break;
    case 15:
      (*lin) = 15;
      break;
    default:
      (*lin) = 20;
  }
  printf("Quantas colunas quer que a tabela tenha? 10 | 15 | 20 : ");
  scanf("%d",col);
  switch(*col){
    case 10:
      (*col) = 10;
      break;
    case 15:
      (*col) = 15;
      break;
    default:
      (*col) = 20;
  }
}

void menu() {

  system("clear");
	printf("QUAL A OPÇÃO PRETENDIDA?\n\n");

	printf("[1] Leitura de ficheiros.\n");// Main --> check
	printf("[2] Apresentar a lista de produtos começados por uma letra.\n");// Catprod--> check
	printf("[3] Apresentar o total faturado com um dado produto num dado mês.\n");// totais separados em N em P -> Faturaçao
	printf("[4] Apresentar os códigos de produtos que não foram comprados.\n"); // dividir pelas filiais depois -> CatProd (?)--> check
	printf("[5] Apresentar a lista ordenada de clientes que realizaram compras em todas as filiais.\n");// Filial--> check
	printf("[6] Apresentar número de clientes que não realizaram compras bem como o número de produtos que ninguém comprou.\n"); // ctc e ctp, rufados --> check
	printf("[7] Dado um código de um cliente, apresentar a tabela, organizada por filial, da quantidade de produtos comprados.\n");// filial
	printf("[8] Dado um intervalo de meses determinar o total de vendas registadas nesse intervalo assim como o total faturado.\n");// fat
	printf("[9] Dado um código de produto e uma filial apresentar os códigos dos clientes que o compraram.\n");// distinguir N e P -> filial
	printf("[A] Dado um código de um cliente e um mês, apresentar a lista de produtos que mais comprou, por ordem decrescente.\n"); //fat
	printf("[B] Apresentar uma lista dos N produtos mais vendidos em todo o ano.\n");// fat
	printf("[C] Dado um código de cliente determinar quais os códigos dos 3 produtos em que mais gastou dinheiro.\n");// filial meia rufada
	printf("[Q] Quit\n");
}
