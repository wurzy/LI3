import java.io.Serializable;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;
/**
 * Classe que gera os menus do programa.
 */
public class Menu implements Serializable {
    private List<String> opcoes;
    private int op;

    /**
     * Construtor parametrizado.
     * @param opcoes Opções disponíveis para mostrar no menu.
     */
    public Menu(String[] opcoes){
        this.opcoes = Arrays.asList(opcoes);
        this.op = 0;
    }

    /**
     * Função que põe o menu a correr.
     */
    public void executa() {
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);
    }

    /**
     * Função que mostra o menu.
     */
    public void showMenu(){
        out.println("\n Selecionar opção:\n");
        for (int i=0; i<this.opcoes.size(); i++){
            out.print(i+1);
            out.print(" - ");
            out.println(this.opcoes.get(i));
        }
        out.println("0 - Sair");
    }

    /**
     * Função espera pela opção do utilizador, após isso lê e devolve a mesma.
     */
    public int lerOpcao(){
        int op;
        Scanner sc = new Scanner(System.in);
        out.print("Opção: ");
        try {
            op = sc.nextInt();
        }
        catch (InputMismatchException e){
            op = -1;
        }

        if (op < 0 || op > this.opcoes.size()){
            out.println("Opção inválida.\nPor favor escolha opção entre "+ 0 + " a " + this.opcoes.size() + ".");
            op = -1;
        }
        return op;
    }

    /**
     * Função retorna a opção do utilizador.
     */
    public int getOp(){
        return this.op;
    }
    /**
     * Função define a opção do menu.
     */
    public void setOp(int op){ this.op = op; }
}