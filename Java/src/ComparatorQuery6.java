import java.io.Serializable;
import java.util.Comparator;

/**
 * Compara 2 pares que contêm um produto, clientes que o compraram e o número de vezes que foi comprado.
 */
public class ComparatorQuery6 implements Comparator<ParQuery6>, Serializable {
    /**
     * Compara quantidade do produto vendidas dos dois pares e, caso sejam iguais, comparam os códigos do produtos dos pares.
     * @param p1 Par 1
     * @param p2 Par 2
     * @return Retorna uma comparação entre as quantidades dos produtos (decrescente) vendidas dos dois pares e, caso sejam iguais, comparam alfabeticamente os códigos dos produtos do par.
     */
    public int compare(ParQuery6 p1, ParQuery6 p2) {
        if (p1.getCompras() > p2.getCompras()) return -1;
        else if (p1.getCompras() < p2.getCompras()) return 1;
        else return (p1.getProduto().getCodigo().compareTo(p2.getProduto().getCodigo()));
    }
}
