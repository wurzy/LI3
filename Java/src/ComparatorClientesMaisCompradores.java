import java.io.Serializable;
import java.util.Comparator;

/**
 * Compara 2 pares que contêm o dinheiro gasto e uma instância do Cliente.
 */
public class ComparatorClientesMaisCompradores implements Comparator<ParQuery7>, Serializable {
    /**
     * Compara valores de faturação de dois pares e, caso sejam iguais, comparam os códigos do produtos dos pares.
     * @param c1 Par 1
     * @param c2 Par 2
     * @return Resultado da comparação do dinheiro faturado (decrescente), se for igual, compara alfabeticamente os clientes.
     */
    public int compare(ParQuery7 c1, ParQuery7 c2){
        if (c1.getValue() > c2.getValue())
            return -1;
        if (c2.getValue() > c1.getValue())
            return 1;
        else return c1.getCodigo().compareTo(c2.getCodigo());
    }
}