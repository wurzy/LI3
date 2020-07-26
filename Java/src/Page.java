import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe auxiliar do navegador, contém a informação de uma página.
 */
public class Page implements Serializable {

    private int lin, col;
    private List<String> valores;

    /**
     * Construtor por defeito.
     */
    public Page(){
        this.lin = 0;
        this.col = 0;
        this.valores = new ArrayList<>();
    }

    /**
     * Construtor parametrizado.
     */
    public Page(int lin, int col, List<String> valores){
        this.lin = lin;
        this.col = col;
        this.valores = new ArrayList<>(valores);
    }

    /**
     * Construtor por cópia.
     */
    public Page(Page page){
        this.lin = page.getLin();
        this.col = page.getCol();
        this.setValores(page.getValores());
    }

    /**
     * Função que devolve o número de linhas.
     */
    public int getLin() {
        return this.lin;
    }

    /**
     * Função que define o número de linhas.
     */
    public void setLin(int lin) {
        this.lin = lin;
    }

    /**
     * Função que devolve o número de colunas.
     */
    public int getCol() {
        return this.col;
    }

    /**
     * Função que define o número de colunas.
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Função que devolve a informação para ser mostrada.
     */
    public List<String> getValores() {
        return new ArrayList<>(valores);
    }

    /**
     * Função que define a informação para ser mostrada.
     */
    public void setValores(List<String> valores) {
        this.valores = new ArrayList<>(valores);
    }

    /**
     * Função que imprime uma página.
     */
    public void printPage(int pag, int totalPag, int totalElems){
        if (this.col == 10){
            System.out.println("\t\t\t  Pagina ["+ pag +"] de [" + totalPag +"]\n\n");
        }
        else if (this.col == 15)
            System.out.println("\t\t\t\t\t\t Pagina ["+ pag +"] de [" + totalPag +"]\n\n");
        else if (this.col == 20)
            System.out.println("\t\t\t\t\t\t\t\t  Pagina ["+ pag +"] de [" + totalPag +"]\n\n");
        else {
            System.out.println("\n\t Pagina ["+ pag +"] de [" + totalPag +"]\n\n");
        }
        int  c = 1;
        for (String val : this.valores){
            if(c==this.col){
                c = 1;
                System.out.println(val);
            }
            else{
                c++;
                System.out.print(val + "\t");
            }
        }
        System.out.println("\nTotal de elementos: " + totalElems + '\n');
        System.out.println("[N]ext | [P]revious | [Q]uit ");
    }
}