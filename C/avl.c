#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "avl.h"
#include "bought.h"
#include "boolean.h"

struct info{
  int *comprado;
  char* key;
  double preco;
  int mes;
};

struct avl{
	struct info data;
	struct avl *left,*right;
	int ht;   //height
};

int existValue(AVLTree,char*);
AVLTree insert(AVLTree,char*);
void inorder(AVLTree);
static int height(AVLTree);
static int BF(AVLTree);
static AVLTree rotateright(AVLTree);
static AVLTree rotateleft(AVLTree);
static AVLTree RR(AVLTree);
static AVLTree LL(AVLTree);
static AVLTree LR(AVLTree);
static AVLTree RL(AVLTree);


void avlNaoComprados(AVLTree t, int* counter){
  if(t != NULL){
    avlNaoComprados(t->left, counter);
    int i,control;
    for(i=0, control=1; i<3; i++)
      if (t->data.comprado[i] != 0){
        control = 0;
      }
    if (control == 1){
      (*counter)++;
    }
    avlNaoComprados(t->right, counter);
  }
}

AVLTree getAVLE (AVLTree T){
  return T->left;
}

AVLTree getAVLD (AVLTree T){
  return T->right;
}

void avlComprasAllFil(AVLTree t, Bought* b, char letter){
  if (t != NULL){
    avlComprasAllFil(t->left,b,letter);
    int i,control;
    control = 0;
    for(i=0; i<3; i++)
      if (t->data.comprado[i] == 0){
        control = 1;
        break;
      }
    if (control == 0){
      char *s, str[10];
      sprintf(str,"%c%s",letter,t->data.key);
      s = strdup(str);
      insertInBoughtArr(b,s);
      free(s);
    }
    avlComprasAllFil(t->right,b,letter);
  }
}

double getPrecoA (AVLTree T){
  return T->data.preco;
}

char* getKeyA (AVLTree T){
  return T->data.key;
}

void avlProdNaoCompradosAllFil(AVLTree t, Bought* b, char fst, char snd){
  if(t != NULL){
    avlProdNaoCompradosAllFil(t->left, b, fst, snd);
    int i,control;
    for(i=0, control=1; i<3; i++)
      if(t->data.comprado[i] != 0)
        control = 0;
    if(control == 1){
      char *s, str[10];
      sprintf(str,"%c%c%s", fst, snd, t->data.key);
      s = strdup(str);
      insertInBoughtArr(b,s);
      free(s);
    }
    avlProdNaoCompradosAllFil(t->right, b, fst, snd);
  }
}

void avlProdNaoComprados(AVLTree t, ArrayComp* arr, char fst, char snd){
  if(t != NULL){
    avlProdNaoComprados(t->left, arr, fst, snd);
    int i;
    for(i=0; i<3; i++){
      if(t->data.comprado[i] == 0){
        char *s, str[10];
        sprintf(str, "%c%c%s", fst, snd, t->data.key);
        s = strdup(str);
        insereArrComp(arr,i,s);
        free(s);
      }
    }
    avlProdNaoComprados(t->right, arr, fst, snd);
  }
}

void avlClieSemCompras(AVLTree t, ArrayComp* arr, char letter){
  if(t != NULL){
    avlClieSemCompras(t->left, arr, letter);
    int i;
    for(i=0; i<3; i++){
      if(t->data.comprado[i] == 0){
        char *s, str[10];
        sprintf(str, "%c%s", letter, t->data.key);
        s = strdup(str);
        insereArrComp(arr,i,s);
        free(s);
      }
    }
    avlClieSemCompras(t->right, arr, letter);
  }
}

void printBought(AVLTree T){

  if (T){
    int i;
    for(i=0; i<3; i++)
      if (T->data.comprado[i] != 0)
        printf("%s -> comprado = %d\n",T->data.key,T->data.comprado[i]);
    printBought(T->left);
    printBought(T->right);
  }
}

AVLTree alteraValorArray(AVLTree T, char* x, int filial){

  AVLTree *tree = &T;

  while((*tree) != NULL){
    //printf("%s == %s\n", (*tree)->data.key, x);
    if (strcmp((*tree)->data.key,x) == 0){
      (*tree)->data.comprado[filial-1]++;
      break;
    }
    else
      if (strcmp(x,(*tree)->data.key) > 0) tree = &(*tree)->right;
      else tree = &(*tree)->left;
  }

  return T;
}

void avlToArray(AVLTree T, char fst, char snd, char** array, int *pos){
  if(T->left)
    avlToArray(T->left,fst,snd,array,pos);
  char str[8];
  sprintf(str,"%c%c%s",fst,snd,T->data.key);
  array[(*pos)++] = strdup(str);
  if(T->right)
    avlToArray(T->right,fst,snd,array,pos);
}

Boolean existValue(AVLTree T, char* snd){

  AVLTree tree = T;

  while (tree != NULL){
    if (strcmp(tree->data.key, snd) == 0)
      return true;
    else
      if (strcmp(snd,tree->data.key) > 0) tree = tree -> right;
      else tree = tree -> left;
  }
  return false;
}

static int height(AVLTree T){

	int lh,rh;

	if(T == NULL)
		return 0;

	if(T->left == NULL)
		lh = 0;
	else
		lh = 1 + T->left->ht;

	if(T->right == NULL)
		rh = 0;
	else
		rh = 1 + T->right->ht;

	if(lh > rh)
		return lh;

	return rh;
}


static int BF(AVLTree T){
	int lh,rh;
	if(T == NULL)
		return 0;

	if(T->left == NULL)
		lh = 0;
	else
		lh = 1 + T->left->ht;

	if(T->right == NULL)
		rh = 0;
	else
		rh = 1 + T->right->ht;

	return (lh-rh);
}

static AVLTree rotateright(AVLTree x){

	AVLTree y;

	y = x->left;
	x->left = y->right;
	y->right = x;

	x->ht = height(x);
	y->ht = height(y);

	return(y);
}

static AVLTree rotateleft(AVLTree x){

	AVLTree y;

	y = x->right;
	x->right = y->left;
	y->left = x;

	x->ht = height(x);
	y->ht = height(y);

	return(y);
}

static AVLTree RR(AVLTree T){
	T = rotateleft(T);
	return(T);
}

static AVLTree LL(AVLTree T){
	T = rotateright(T);
	return(T);
}

static AVLTree LR(AVLTree T){
	T->left = rotateleft(T->left);
	T = rotateright(T);

	return(T);
}

static AVLTree RL(AVLTree T){
	T->right = rotateright(T->right);
	T = rotateleft(T);
	return(T);
}

AVLTree insertByFilial(AVLTree T, char *x, double preco, int mes){

  int i;

  if(T == NULL){
		T = (AVLTree)malloc(sizeof(struct avl));
		T->data.key = strdup(x);
		T->data.comprado = (int*)malloc(sizeof(int)*3);
		T->data.preco = preco;
    T->data.mes = mes;
		for (i=0; i<3; i++)
		  T->data.comprado[i] = 0;
		T->left  = NULL;
		T->right = NULL;
	}
	else{
		if (strcmp(x,T->data.key) >= 0){ //positivo == x > t->data.key
			T->right = insertByFilial(T->right, x, preco, mes);
			if(BF(T) == -2){
				if(strcmp(x,T->right->data.key) >= 0)
					T = RR(T);
				else
					T = RL(T);
      }
		}
		else
			if(strcmp(x,T->data.key) < 0){ //negativo == x > t->data.key
				T->left = insertByFilial(T->left, x, preco, mes);
				if(BF(T) == 2){
					if(strcmp(x,T->left->data.key) < 0)
						T = LL(T);
					else
						T = LR(T);
        }
			}
  }

  T->ht = height(T);
  return(T);
}


AVLTree insert(AVLTree T, char *x){

  int i;

  if(T == NULL){
		T = (AVLTree)malloc(sizeof(struct avl));
		T->data.key = strdup(x);
		T->data.comprado = (int*)malloc(sizeof(int)*3);
		for (i=0; i<3; i++)
		  T->data.comprado[i] = 0;
		T->left  = NULL;
		T->right = NULL;
	}
	else{
		if (strcmp(x,T->data.key) > 0){ //positivo == x > t->data.key
			T->right = insert(T->right, x);
			if(BF(T) == -2){
				if(strcmp(x,T->right->data.key) > 0)
					T = RR(T);
				else
					T = RL(T);
      }
		}
		else
			if(strcmp(x,T->data.key) < 0){ //negativo == x > t->data.key
				T->left = insert(T->left, x);
				if(BF(T) == 2){
					if(strcmp(x,T->left->data.key) < 0)
						T = LL(T);
					else
						T = LR(T);
        }
			}
  }

  T->ht = height(T);
  return(T);
}

void inorder(AVLTree T){
	if(T!=NULL){
		inorder(T->left);
		printf("%s(Bf=%d)",T->data.key,BF(T));
		inorder(T->right);
	}
}

int contaClientes(AVLTree T,int mes){
  int r = 0;
  if(T!=NULL){
    if (mes == T->data.mes)r++;
    r += contaClientes(T->left,mes);
		r += contaClientes(T->right,mes);
	}
  return r;
}
