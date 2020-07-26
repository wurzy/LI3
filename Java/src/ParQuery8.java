import java.io.Serializable;

/**
 * Par com cliente e total de produtos diferentes comprados.
 */
public class ParQuery8 implements Serializable {
    private ICliente cliente;
    private int quantidade;

    /**
     * Construtor parametrizado do Par.
     * @param cl cliente.
     * @param quantidade quantidade comprada de produtos diferentes.
     */
    public ParQuery8(ICliente cl, int quantidade) {
        this.cliente = cl.clone();
        this.quantidade = quantidade;
    }

    /**
     * Retorna a quantidade comprada de produtos diferentes.
     * @return a quantidade comprada de produtos diferentes.
     */
    public int getValue(){
        return this.quantidade;
    }

    /**
     * Retorna o código de produto.
     * @return o código de produto.
     */
    public String getCodigo(){
        return this.cliente.toString();
    }
}
