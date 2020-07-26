CFLAGS=-Wall -D_GNU_SOURCE -pedantic -Wextra -g -O2
SRC=avl.c Produto.c Cliente.c CatClientes.c CatProd.c main.c Lista.c print.c bought.c AVLFact.c Faturacao.c queries.c Vendas.c avlfil.c Filial.c
PROJNAME=SGV

project: $(SRC)
	gcc $(CFLAGS) -o $(PROJNAME) $(SRC)

run: project
	./$(PROJNAME)

zip:
	zip -r $(PROJNAME).zip ./ -x *.txt

clean:
	rm -rf *.o *.zip
