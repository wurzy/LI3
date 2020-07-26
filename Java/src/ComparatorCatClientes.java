import java.io.Serializable;
import java.util.Comparator;

/**
 * Comparador para dois clientes que compara os códigos de ambos, pela sua ordem alfabética.
 */
public class ComparatorCatClientes implements Comparator<ICliente>, Serializable {
    /**
     * Compara dois clientes através dos códigos de cliente.
     * @param s1 Cliente 1
     * @param s2 Cliente 2
     * @return Resultado da comparação alfabética dos 2 clientes.
     */
    public int compare(ICliente s1, ICliente s2){
        return s1.getCodigo().compareTo(s2.getCodigo());
    }
}
