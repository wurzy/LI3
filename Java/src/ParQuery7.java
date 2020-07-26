import java.io.Serializable;

/**
 * Par com cliente e o total gasto por ele.
 */
public class ParQuery7 implements Serializable {
    private ICliente cliente;
    private double faturado;

    /**
     * Construtor por parâmetros do Par.
     * @param cliente cliente.
     * @param faturado total faturado.
     */
    public ParQuery7(ICliente cliente, double faturado) {
        this.cliente = cliente.clone();
        this.faturado = faturado;
    }

    /**
     * Retorna o código do cliente.
     * @return o código do cliente.
     */
    public String getCodigo(){
        return this.cliente.toString();
    }

    /**
     * Retorna o total faturado.
     * @return o total faturado.
     */
    public double getValue(){
        return this.faturado;
    }
}
