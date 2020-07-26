#ifndef CLIENTE_H
#define CLIENTE_H

#include "boolean.h"

typedef struct clie* Cliente;

char* getClientName(Cliente);
Boolean validaCliente (Cliente);
Cliente createClient(char*);

#endif
