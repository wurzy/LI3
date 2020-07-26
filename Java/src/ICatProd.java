import java.io.Serializable;
import java.util.Set;

public interface ICatProd extends Serializable {
    /**
     * Devolve o conjunto de produtos válidos do catálogo de produtos.
     * @return Conjunto de Produtos válidos do catálogo de produtos.
     */
    Set<IProduto> getCTP();
    /**
     * Altera a conjunto de produtos do catálogo para um novo conjunto de produtos
     * @param ctp Conjunto de produtos novo.
     */
    void setCTP(Set<IProduto> ctp);
    /**
     * Retorna o número de produtos válidos.
     * @return Número de produtos válidos.
     */
    int getValidos();
    /**
     * Altera o número total de produtos válidos no catálogo de produtos.
     * @param validos Número de produtos válidos.
     */
    void setValidos(int validos);
    /**
     * Retorna o número total de produtos lidos do ficheiro de Produtos.
     * @return Número total de produtos lidos do ficheiro de Produtos.
     */
    int getTotal();
    /**
     * Altera o número total de produtos lidos para um novo total dado por argumento.
     * @param total Total novo de produtos lidos para o catálogo.
     */
    void setTotal(int total);
    /**
     * Retorna um catálogo de produtos idêntico à instância que invoca o método.
     * @return Um catálogo de produtos idêntico à instância que invoca o método.
     */
    ICatProd clone();
    /**
     * Compara dois objetos, deve ser usado, concretamente, para comparar 2 catálogos de produtos.
     * @param o Objeto para comparar.
     * @return false, se forem diferentes, true se forem iguais.
     */
    boolean equals(Object o);
    /**
     * Adiciona um produto ao catálogo de produtos.
     * @param p Produto a adicionar.
     */
    void add(IProduto p);
    /**
     * Verifica se existe um produto no catálogo.
     * @param p Produto para verificar a existência.
     * @return false, se não existir, true se existir.
     */
    boolean existeProduto(IProduto p);
    /**
     * Retorna o conjunto de produtos que nunca foram comprados, apartir dos que já foram comprados.
     * @param inicial Conjuntos de produtos já comprados.
     * @return Conjunto de produtos que não foram comprados
     */
    Set<IProduto> nuncaComprados(Set<IProduto> inicial);
}
