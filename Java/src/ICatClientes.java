import java.io.Serializable;
import java.util.Set;

public interface ICatClientes extends Serializable {
    /**
     * Retorna o catálogo de clientes em forma de conjunto de clientes.
     * @return Um conjunto de clientes.
     */
    Set<ICliente> getCTC();
    /**
     * Coloca a variável interna do catálogo através dum conjunto por parâmetro.
     * @param ctc Conjunto usado como base.
     */
    void setCTC(Set<ICliente> ctc);
    /**
     * Função que retorna o número total de clientes no catálogo de clientes.
     * @return O número total de clientes que estão no catálogo de clientes.
     */
    int getValidos();
    /**
     * Coloca o número de válidos o inteiro dado por parâmetro.
     * @param validos Inteiro para colocar como número total de clientes válidos.
     */
    void setValidos(int validos);
    /**
     * Retorna o total de clientes lidos no ficheiro de Clientes.
     * @return Total de clientes lidos do ficheiro.
     */
    int getTotal();
    /**
     * Coloca a variável local de total de clientes a dada por argumento.
     * @param total Total de clientes lidos do ficheiro.
     */
    void setTotal(int total);
    /**
     * Retorna um novo catálogo idêntico ao objeto que invoca este método.
     * @return Um novo catálogo igual ao objeto que invoca este método.
     */
    ICatClientes clone();
    /**
     * Utiliza-se para comparar dois objetos, em concreto, deverá ser usado para comparar dois catálogos de clientes.
     * @param o Objeto para comparar
     * @return
     */
    boolean equals(Object o);
    /**
     * Adiciona um cliente ao catálogo de clientes.
     * @param cl Cliente para adicionar.
     */
    void add(ICliente cl);
    /**
     * Verifica se existe um cliente no catálogo de clientes.
     * @param cliente Cliente que será verificada a existência no catálogo de clientes.
     * @return false, se não existe, true se existe.
     */
    boolean existeCliente(ICliente cliente);
}
