#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "Cliente.h"
#include "boolean.h"

struct clie{
  char* cliename;
};

char* getClientName(Cliente c){
  char* str;
  str = strdup(c->cliename);
  return str;
}

Boolean validaCliente(Cliente c){
  char fst;
  char* str;
  char* cliente;
  cliente = getClientName(c);
  fst = cliente[0];
  str = cliente + 1;
  return (fst >= 'A' && fst <= 'Z' && atoi(str) >= 1000 && atoi(str) <= 5000);
}


Cliente createClient(char *x) {
	Cliente c = (Cliente)malloc(sizeof(struct clie));
	c->cliename = strdup(x);
	return c;
}
