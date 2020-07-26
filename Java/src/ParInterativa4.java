import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * Par com produto para um mês, quantas vezes foi comprado, por quantos clientes diferentes e total faturado.
 */
public class ParInterativa4 implements Serializable {
    private int comprados;
    private double faturados;
    private Set<ICliente> clies;

    /**
     * Construtor vazio do Par.
     */
    public ParInterativa4(){
        this.clies = new TreeSet<>(new ComparatorCatClientes());
        this.faturados = 0;
        this.comprados = 0;
    }

    /**
     * Adiciona um cliente e uma faturação para o par.
     * @param cl cliente.
     * @param d faturação parcial.
     */
    public void addCliente(ICliente cl, double d) {
        if(!this.clies.contains(cl)){
            this.clies.add(cl.clone());
        }
        this.comprados++;
        this.faturados+=d;
    }

    /**
     * Retorna o número de compras do produto.
     * @return o número de compras do produto.
     */
    public int getComprados(){
        return this.comprados;
    }

    /**
     * Retorna a faturação total do produto.
     * @return a faturação total do produto.
     */
    public double getFaturados(){
        return this.faturados;
    }

    /**
     * Retorna o número de clientes que compraram o produto.
     * @return o número de clientes que compraram o produto.
     */
    public int getClientes(){
        return this.clies.size();
    }
}
