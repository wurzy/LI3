#ifndef AVL_FACT
#define AVL_FACT
#include "boolean.h"
typedef struct avlFact* AVLFact;

AVLFact insertFat(AVLFact T, char* x, int vendas, double preco);
void inorderFat(AVLFact T);
double fatInType(AVLFact T);
int getAVLFactUsed(AVLFact t); 
int getAVLFactComprados(AVLFact t, int i) ;
char* getAVLFactKey(AVLFact t); 
AVLFact getAVLFactL(AVLFact t); 
AVLFact getAVLFactR(AVLFact t);

#endif
