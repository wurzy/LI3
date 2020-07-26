#ifndef AVL_H
#define AVL_H

#include "bought.h"

typedef struct avl* AVLTree;

AVLTree alteraValorArray(AVLTree T, char* x, int filial);
AVLTree getAVLD (AVLTree T);
AVLTree getAVLE (AVLTree T);
AVLTree insert(AVLTree,char*);
AVLTree insertByFilial(AVLTree T, char *x, double preco, int mes);
char* getKeyA (AVLTree T);
double getPrecoA (AVLTree T);
int contaClientes(AVLTree T,int mes);
int existValue(AVLTree,char*);
void avlClieSemCompras(AVLTree t, ArrayComp* arr, char letter);
void avlComprasAllFil(AVLTree t, Bought* b, char letter);
void avlNaoComprados(AVLTree t, int* counter);
void avlProdNaoComprados(AVLTree t, ArrayComp* arr, char fst, char snd);
void avlProdNaoCompradosAllFil(AVLTree t, Bought* b, char fst, char snd);
void avlToArray(AVLTree,char,char,char**,int*);
void inorder(AVLTree);
void printBought(AVLTree);

#endif
