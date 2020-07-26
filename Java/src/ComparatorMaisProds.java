import java.io.Serializable;
import java.util.Comparator;

/**
 * Compara 2 pares que contêm a quantidade de produtos diferentes que compraram e uma instância do Cliente.
 */
public class ComparatorMaisProds implements Comparator<ParQuery8>, Serializable {
    /**
     * Compara quantidades de produtos comparadas de dois pares e, caso sejam iguais, comparam os códigos do clientes dos pares.
     * @param s1 Par 1
     * @param s2 Par 2
     * @return Compara a quantidade de produtos diferentes dos pares (decrescente), se forem iguais, compara alfabeticamente os clientes.
     */
    public int compare (ParQuery8 s1 , ParQuery8 s2){
        if (s1.getValue() > s2.getValue())
            return -1;
        else if (s2.getValue() > s1.getValue())
            return 1;
        return s1.getCodigo().compareTo(s2.getCodigo());
    }
}