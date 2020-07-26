#ifndef AVLFIL_H
#define AVLFIL_H

#include "avl.h"
#include "boolean.h"

typedef struct avlfil* AVLFil;

char* getKeyF (AVLFil F);
AVLFil getAVLFilE (AVLFil F);
AVLFil getAVLFilD (AVLFil F);
AVLTree getProdutosF (AVLFil F);
AVLFil setProdutosF(AVLFil F, AVLTree T);
AVLTree getProdutosFD (AVLFil F);
AVLTree getProdutosFE (AVLFil F);
AVLFil insertF(AVLFil F, char*, char*, double p, int m);
Boolean existValueF(AVLFil F, char* snd);
void inorderF(AVLFil F);

#endif
