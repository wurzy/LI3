import java.io.Serializable;
import java.util.Comparator;

/**
 * Compara 2 pares que contêm um produto, clientes que o compraram e o número de vezes que foi comprado.
 */
public class ComparatorQuery9 implements Comparator<ParQuery9>, Serializable {
    /**
     * Compara número de clientes que compraram o produto nos 2 pares.
     * @param s1 Par 1
     * @param s2 Par 2
     * @return Retorna uma comparação entre o número de clientes que compraram o produto nos dois pares (decrescente) e, caso sejam iguais, compara alfabeticamente os produtos.
     */
    public int compare(ParQuery9 s1,
                       ParQuery9 s2){
        if(s1.getPar().getValue().getKey() > s2.getPar().getValue().getKey())
            return -1;
        if(s1.getPar().getValue().getKey() < s2.getPar().getValue().getKey())
            return 1;
        return s1.getPar().getKey().getCodigo().compareTo(s2.getPar().getKey().getCodigo());
    }
}