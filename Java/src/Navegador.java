import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Navegador de Strings.
 */
public class Navegador implements Serializable {

    private int pageNumber, pageTotal, elemTotal;
    private List<Page> lp;

    /**
     * Construtor parametrizado.
     * @param ls Lista de Strings para serem mostradas ao utilizador.
     * @param lin Número de linhas que o navegador irá conter por página.
     * @param col Número de colunas que o navegador irá conter por página.
     */
    public Navegador(List<String> ls, int lin, int col){

        this.pageNumber = 1;
        int counter = 0;
        List<Page> listP = new ArrayList<>();
        List<String> l  = new ArrayList<>();
        Page p;
        while(counter<ls.size()) {
            for(int i = 0; i < lin; i++) {
                for(int j = 0; j < col; j++) {
                    if(counter < ls.size()) {
                        l.add(ls.get(counter));
                    }
                    counter++;
                }
            }
            p = new Page(lin,col,l);
            l = new ArrayList<>();
            listP.add(p);
        }

        this.lp = listP;
        this.pageTotal = lp.size();
        this.elemTotal = ls.size();
    }

    /**
     * Função que inicia o navegador.
     */
    public void start(){
        String acao;
        do {
            this.lp.get(this.pageNumber-1).printPage(this.pageNumber,this.pageTotal,this.elemTotal);
            Scanner sc = new Scanner(System.in);
            acao = sc.nextLine();
            switch (acao){
                case "P":
                case "p":
                    if(this.pageNumber>1)
                        this.pageNumber--;
                    break;
                case "N":
                case "n":
                    if(this.pageNumber<this.pageTotal)
                        this.pageNumber++;
                    break;
                default:
                    break;
            }
        }while(!acao.equals("Q") && !acao.equals("q"));
        System.out.println('\n');
    }
}