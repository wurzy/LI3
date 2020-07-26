#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "avlfil.h"
#include "avl.h"
#include "boolean.h"

struct info{
  char* key;
  AVLTree produtos;
};

struct avlfil{
  struct info data;
  struct avlfil *left, *right;
  int ht;
};

Boolean existValueF(AVLFil F, char* snd);
AVLFil insertF(AVLFil F, char* x, char* y, double p, int m);
void inorderF(AVLFil F);
static int heightF (AVLFil);
static int BFF(AVLFil);
static AVLFil rotaterightF (AVLFil);
static AVLFil rotateleftF (AVLFil);
static AVLFil RRF(AVLFil);
static AVLFil LLF(AVLFil);
static AVLFil RLF(AVLFil);
static AVLFil LRF(AVLFil);


char* getKeyF (AVLFil F){
  char *K = F->data.key;
  return K;
}

AVLFil getAVLFilE (AVLFil F){
  F = F->left;
  return F;
}

AVLFil getAVLFilD (AVLFil F){
  F = F->right;
  return F;
}

AVLTree getProdutosF (AVLFil F){
  AVLTree T = NULL;
  if (F!=NULL)
    T = F->data.produtos;
  return T;
}

AVLFil setProdutosF(AVLFil F, AVLTree T){
  F->data.produtos = T;
  return F;
}

AVLTree getProdutosFD (AVLFil F){
  AVLTree T = getAVLD(F->data.produtos);
  return T;
}
AVLTree getProdutosFE (AVLFil F){
  AVLTree T = getAVLE(F->data.produtos);
  return T;
}

Boolean existValueF(AVLFil F, char* snd){

  AVLFil tree = F;

  while (tree != NULL){
    if (strcmp(tree->data.key, snd) == 0){
      return true;
    }
    else{
      if (strcmp(snd, tree->data.key) > 0) tree = tree->right;
      else tree = tree -> left;
    }
  }
  return false;
}


static int heightF(AVLFil F){

  int lh, rh;

  if (F == NULL)
    return 0;

  if (F->left == NULL)
    lh = 0;
  else
    lh = 1 + F->left->ht;

  if (F->right == NULL)
    rh = 0;
  else
    rh = 1 + F->right->ht;

  if (lh > rh)
    return lh;

  return rh;
}

static int BFF(AVLFil F){

  int lh,rh;
	if(F == NULL)
		return 0;

	if(F->left == NULL)
		lh = 0;
	else
		lh = 1 + F->left->ht;

	if(F->right == NULL)
		rh = 0;
	else
		rh = 1 + F->right->ht;

	return (lh-rh);
}

static AVLFil rotaterightF(AVLFil x){

	AVLFil y;

	y = x->left;
	x->left = y->right;
	y->right = x;

	x->ht = heightF(x);
	y->ht = heightF(y);

	return(y);
}


static AVLFil rotateleftF(AVLFil x){

	AVLFil y;

	y = x->right;
	x->right = y->left;
	y->left = x;

	x->ht = heightF(x);
	y->ht = heightF(y);

	return(y);
}

static AVLFil RRF(AVLFil F){
	F = rotateleftF(F);
	return(F);
}

static AVLFil LLF(AVLFil F){
	F = rotaterightF(F);
	return(F);
}

static AVLFil LRF(AVLFil F){
	F->left = rotateleftF(F->left);
	F = rotaterightF(F);

	return(F);
}

static AVLFil RLF(AVLFil F){
	F->right = rotaterightF(F->right);
	F = rotateleftF(F);
	return(F);
}

AVLFil insertF(AVLFil F, char* x, char* y, double p, int m){

  if(F == NULL){
    F = (AVLFil)malloc(sizeof(struct avlfil));
    F->data.key = strdup(x);
    F->data.produtos = insertByFilial(NULL, y, p, m);
    F->left = NULL;
    F->right = NULL;
  }


  else{
    if (strcmp(x,F->data.key) == 0){
      F->data.produtos = insertByFilial (F->data.produtos,y,p, m);
    }
    if (strcmp(x,F->data.key) > 0){
      F->right = insertF(F->right,x,y,p,m);
      if (BFF(F) == -2){
        if (strcmp(x,F->right->data.key) > 0)
          F = RRF(F);
        else
          F = RLF(F);
      }
    }
    else{
      if (strcmp(x,F->data.key) < 0){
        F->left = insertF(F->left, x,y,p,m);
        if (BFF(F) == 2){
          if (strcmp(x,F->left->data.key) < 0)
            F = LLF(F);
          else
            F = LRF(F);
        }
      }
    }
  }

  F->ht = heightF(F);
  return F;

}

/*
void freeAVLFil(AVLFil F){

  if (F!=NULL){
    freeAVLFil(F->right);
    freeAVLFil(F->left);
  }

  free(F->data.key);
  freeAVLTree(F->data.produtos);
  free(F->data);
  free(F);


}
*/
void inorderF(AVLFil F){
  if(F!=NULL){
    inorderF(F->left);
    printf("%s\n", F->data.key);
    inorderF(F->right);
  }
}
