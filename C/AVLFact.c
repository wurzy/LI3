#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "boolean.h"
#include "AVLFact.h"

#define SIZEFT 100

struct infoFact{
  int *comprados;
  double *precos;
  int used,size;
  char* key; /*prod*/
};

struct avlFact{
	struct infoFact data;
	struct avlFact *left,*right;
	int ht;   //height
};

static int height(AVLFact T){

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


static int BF(AVLFact T){
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

static AVLFact rotateright(AVLFact x){

	AVLFact y;

	y = x->left;
	x->left = y->right;
	y->right = x;

	x->ht = height(x);
	y->ht = height(y);

	return(y);
}

static AVLFact rotateleft(AVLFact x){

	AVLFact y;

	y = x->right;
	x->right = y->left;
	y->left = x;

	x->ht = height(x);
	y->ht = height(y);

	return(y);
}

static AVLFact RR(AVLFact T){
	T = rotateleft(T);
	return(T);
}

static AVLFact LL(AVLFact T){
	T = rotateright(T);
	return(T);
}

static AVLFact LR(AVLFact T){
	T->left = rotateleft(T->left);
	T = rotateright(T);

	return(T);
}

static AVLFact RL(AVLFact T){
	T->right = rotateright(T->right);
	T = rotateleft(T);
	return(T);
}


static void manageOcup(AVLFact t, int vendas, double preco) {
  int x;
  if(t->data.used>(0.9*(t->data.size))){
    x = t->data.size+SIZEFT;
    t->data.comprados = (int*) realloc(t->data.comprados,x*sizeof(int));
    t->data.precos = (double*) realloc(t->data.precos,x*sizeof(double));
    t->data.size = x;
  }
  t->data.comprados[t->data.used] = vendas;
  t->data.precos[t->data.used] = preco;
  t->data.used++;
  //printf("USING %d\n", t->data.used);
}

AVLFact insertFat(AVLFact T, char *x,int vendas,double preco){
  if(T == NULL){
		T = (AVLFact)malloc(sizeof(struct avlFact));
		T->data.key = strdup(x);
    T->data.comprados = (int*) malloc(sizeof(int)*SIZEFT);
    T->data.precos = (double*) malloc(sizeof(double)*SIZEFT);
    T->data.used = 0;
    T->data.size = SIZEFT;
  	manageOcup(T,vendas,preco);
		T->left  = NULL;
		T->right = NULL;
	}
	else{
    if (strcmp(x,T->data.key)==0){
      manageOcup(T,vendas,preco);
    }
		 else if (strcmp(x,T->data.key) > 0){ //positivo == x > t->data.key
			T->right = insertFat(T->right, x,vendas,preco);
			if(BF(T) == -2){
				if(strcmp(x,T->right->data.key) > 0)
					T = RR(T);
				else
					T = RL(T);
      }
		}
		else
			if(strcmp(x,T->data.key) < 0){ //negativo == x > t->data.key
				T->left = insertFat(T->left, x,vendas,preco);
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

static void printFields(AVLFact t){
    for (int i = 0; i < t->data.used; i++) {
      printf(" COMP: %d PRECO: %f\n", t->data.comprados[i], t->data.precos[i]);
    }
}

void inorderFat(AVLFact T){
	if(T!=NULL){
		inorderFat(T->left);
		printf("%s(Bf=%d)",T->data.key,BF(T));
    printFields(T);
		inorderFat(T->right);
	}
}

double fatInType(AVLFact t) {
  double r = 0;
  if(t) {
    for(int i = 0; i<t->data.used; i++) {
      r+= t->data.precos[i] * t->data.comprados[i];

    }
    r += fatInType(t->left);
    r += fatInType(t->right);
  }
  return r;
}

int getAVLFactUsed(AVLFact t) {
  return (t->data.used);
}

int getAVLFactComprados(AVLFact t, int i) {
  return (t->data.comprados[i]);
}

char* getAVLFactKey(AVLFact t) {
  return (t->data.key);
}

AVLFact getAVLFactL(AVLFact t) {
  return t->left;
}

AVLFact getAVLFactR(AVLFact t) {
  return t->right;
}

/*
int main(){

  AVLFact t = NULL;

for(int i = 0; i<256; i++) {
 t =insertFat(t,"C1821",2,9.7+i);
}
for(int i = 0; i<104;i++) {
 t = insertFat(t,"A1721",4,10.4+i);
}
for(int i = 0; i<121;i++) {
  t =insertFat(t,"B1111",101,12+i);
}
  inorderFat(t);

  return 0;
}
*/
