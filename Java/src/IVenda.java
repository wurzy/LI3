import java.io.Serializable;

public interface IVenda extends Serializable {
    /**
     * Retorna codigo do produto
     */
    String getCodProd();
    /**
     * Função que devolve o código do cliente.
     */
    String getCodClie();
    /**
     * Função que devolve o o preço de uma venda.
     */
    double getPrecoUnit();
    /**
     * Função que define o preço unitário de uma venda.
     */
    int getQuantidade();
    /**
     * Função que devolve o tipo de uma compra.
     */
    String getTipo();
    /**
     * Função que devolve o mês em que a compra foi efetuada.
     */
    int getMes();
    /**
     * Função que devolve a filial em que a compra foi efetuada.
     */
    int getFilial();
    /**
     * Função que dá clone a uma venda.
     */
    IVenda clone();
    /**
     * Função que verifica se duas vendas são iguais.
     */
    boolean equals(Object o);
    /**
     * Função que transforma duas vendas em strings.
     */
    String toString();
}
