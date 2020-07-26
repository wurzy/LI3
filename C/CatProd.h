#ifndef CAT_PROD
#define CAT_PROD

#include "avl.h"
#include "boolean.h"
#include "Produto.h"
#include "Lista.h"

#define HPROD     676
#define FILE_SIZE 10

typedef struct catProdutos* CatProdutos;

ArrayComp produtosNaoComprados(ArrayComp arr, CatProdutos ctp);
Boolean existeProduto(CatProdutos ctp, Produto p);
Bought prodNuncaComprados(Bought b, CatProdutos ctp);
CatProdutos addProduct(CatProdutos ctp, Produto p);
CatProdutos increaseReadProd(CatProdutos ctp);
CatProdutos increaseValidProd(CatProdutos ctp);
CatProdutos initCatProdutos();
CatProdutos leProdutos();
Lista getProdsByLetter(CatProdutos ctp, char letter, int lin, int col);
int getReadProd(CatProdutos ctp);
int getValidProd(CatProdutos ctp);
int quantosProdNaoComprados(CatProdutos ctp);
int totalProdutos(CatProdutos ctp);
int totalProdutosLetra(CatProdutos ctp, char letter);
void printar(CatProdutos ctp, char* x);
void produtoComprado(CatProdutos *ctp, char* prod, int filial);

#endif
