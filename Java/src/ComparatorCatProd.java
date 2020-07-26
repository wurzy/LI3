import java.io.Serializable;
import java.util.Comparator;
/**
 * Comparador para dois produtos que compara os códigos de ambos, pela sua ordem alfabética.
 */
public class ComparatorCatProd implements Comparator<IProduto>, Serializable {
    /**
     * Compara dois Produtos através dos códigos de produtos.
     * @param s1 Produtos 1
     * @param s2 Produtos 2
     * @return Resultado da comparação alfabética dos 2 produtos.
     */
    public int compare(IProduto s1, IProduto s2){
        return s1.getCodigo().compareTo(s2.getCodigo());
    }
}
