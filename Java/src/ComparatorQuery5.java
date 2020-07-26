import java.io.Serializable;
import java.util.Comparator;

/**
 * Compara 2 pares que contêm quantidades vendidas de um produto e uma instância do mesmo produto.
 */
public class ComparatorQuery5 implements Comparator<ParProdQuantidade>, Serializable {
    /**
     * Compara quantidade do produto vendidas dos dois pares e, caso sejam iguais, comparam os códigos do produtos dos pares.
     * @param p1 Par 1
     * @param p2 Par 2
     * @return Retorna uma comparação entre as quantidades dos produtos (decrescente) vendidas dos dois pares e, caso sejam iguais, comparam alfabeticamente os códigos dos produtos do par.
     */
    public int compare(ParProdQuantidade p1, ParProdQuantidade p2) {
        if(p1.getQuantidade() > p2.getQuantidade()) return -1;
        else if (p1.getQuantidade() < p2 .getQuantidade()) return 1;
        else return p1.getProduto().getCodigo().compareTo(p2.getProduto().getCodigo());
    }

}
