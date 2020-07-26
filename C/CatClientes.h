#ifndef CAT_CLIENTES
#define CAT_CLIENTES

#include "avl.h"
#include "boolean.h"
#include "Cliente.h"

#define HCLIENTES 26
#define FILE_SIZE 10

typedef struct catClientes* CatClientes;

ArrayComp clientesSemCompras(ArrayComp arr, CatClientes ctc);
Boolean existeCliente(CatClientes ctc, Cliente c);
Bought comprasTodasFiliais(CatClientes ctc);
CatClientes addCliente(CatClientes ctc, Cliente c);
CatClientes increaseReadCli(CatClientes ctc);
CatClientes increaseValidClie(CatClientes ctc);
CatClientes initCatClientes();
CatClientes leClientes ();
int getReadClient(CatClientes ctc);
int getValidClient(CatClientes ctc);
int quantosClieNaoCompraram(CatClientes ctc);
int totalClientes(CatClientes ctc);
int totalClientesLetra(CatClientes ctc, char letter);
void clienteCompra(CatClientes *x, char* clie, int filial);
void printComprados(CatClientes ctc);

#endif
