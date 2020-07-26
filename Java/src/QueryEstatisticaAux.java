import java.io.Serializable;

/**
 * Classe que contém vários dados acerca dos últimos ficheiros lidos.
 */
public class QueryEstatisticaAux implements Serializable {
    String ultimoFile;
    private int vendasLidas;
    private int vendasValidas;
    private int vendasZero;
    private int clientesLidos;
    private int clientesInvalidos;
    private int clientesCompraram;
    private int produtosLidos;
    private int produtosValidos;
    private int produtosComprados;
    private double faturado;

    /**
     * Construtor parametrizado de QueryEstatisticaAux
     * @param ultimoFile último ficheiro lido
     * @param vendasLidas
     * @param vendasValidas
     * @param vendasZero
     * @param clientesLidos
     * @param clientesInvalidos
     * @param clientesCompraram
     * @param produtosLidos
     * @param produtosValidos
     * @param produtosComprados
     * @param faturado
     */
    public QueryEstatisticaAux(String ultimoFile, int vendasLidas, int vendasValidas, int vendasZero, int clientesLidos, int clientesInvalidos, int clientesCompraram, int produtosLidos, int produtosValidos, int produtosComprados, double faturado) {
        this.ultimoFile = ultimoFile;
        this.vendasLidas = vendasLidas;
        this.vendasValidas = vendasValidas;
        this.vendasZero = vendasZero;
        this.clientesLidos = clientesLidos;
        this.clientesInvalidos = clientesInvalidos;
        this.clientesCompraram = clientesCompraram;
        this.produtosLidos = produtosLidos;
        this.produtosValidos = produtosValidos;
        this.produtosComprados = produtosComprados;
        this.faturado = faturado;
    }

    /**
     * Retorna o último ficheiro lido.
     * @return
     */
    public String getUltimoFile() {
        return ultimoFile;
    }

    /**
     * Retorna o número de vendas lindas.
     * @return número de vendas lidas.
     */
    public int getVendasLidas(){
        return this.vendasLidas;
    }

    /**
     * Retorna o número de vendas válidas.
     * @return número de vendas válidas.
     */
    public int getVendasValidas(){
        return this.vendasValidas;
    }

    /**
     * Retorna o número de vendas a zero.
     * @return número de vendas a zero.
     */
    public int getVendasZero(){
        return this.vendasZero;
    }

    /**
     * Retorna número de vendas inválidas.
     * @return número de vendas inválidas.
     */
    public int getVendasInvalidas(){
        return this.vendasLidas-this.vendasValidas;
    }

    /**
     * Retorna o número de clientes lidos.
     * @return número de clientes lidos.
     */
    public int getClientesLidos(){
        return this.clientesLidos;
    }

    /**
     * Retorna o número de clientes válidos.
     * @return o número de clientes válidos.
     */
    public int getClientesValidos() {
        return this.clientesLidos - this.clientesInvalidos;
    }

    /**
     * Retorna o número de clientes inválidos.
     * @return o número de clientes inválidos.
     */
    public int getClientesInvalidos(){
        return this.clientesInvalidos;
    }

    /**
     * Retorna o número de clientes que efetuaram compras.
     * @return o número de clientes que efetuaram compras.
     */
    public int getClientesCompraram() {
        return this.clientesCompraram;
    }

    /**
     * Retorna o número de clientes que não compraram nada.
     * @return número de clientes que não compraram nada.
     */
    public int getClientesNaoCompraram(){
        return this.clientesLidos-this.clientesCompraram;
    }

    /**
     * Retorna o número de produtos lidos.
     * @return número de produtos lidos.
     */
    public int getProdutosLidos() {
        return this.produtosLidos;
    }

    /**
     * Retorna o número de produtos válidos.
     * @return o número de produtos válidos.
     */
    public int getProdutosValidos() {
        return this.produtosValidos;
    }

    /**
     * Retorna o número de produtos comprados.
     * @return o número de produtos comprados.
     */
    public int getProdutosComprados() {
        return this.produtosComprados;
    }

    /**
     * Retorna o número de produtos não comprados.
     * @return o número de produtos não comprados.
     */
    public int getProdutosNaoComprados(){
        return this.produtosLidos-this.produtosComprados;
    }

    /**
     * Retorna o total faturado.
     * @return o total faturado.
     */
    public double getFaturado() {
        return this.faturado;
    }
}
