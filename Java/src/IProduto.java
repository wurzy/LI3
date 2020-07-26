import java.io.Serializable;

public interface IProduto extends Serializable {
    /**
     * Função que retorna o código de produto.
     */
    String getCodigo();
    /**
     * Função que define o código de produto.
     * @param codigo Código que o produto irá ter.
     */
    void setCodigo(String codigo);
    /**
     * Função que dá clone a um Produto.
     */
    Produto clone();
    /**
     * Função que verifica se dois produtos são iguais.
     */
    boolean equals(Object o);
    /**
     * Função que transforma um Produto em String.
     */
    String toString();
    /**
     * Função define o hashcode de um produto.
     */
    int hashCode();
}
