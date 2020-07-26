import java.io.Serializable;
import java.util.*;

/**
 * Classe que organiza vários produtos num só catálogo.
 */
public class CatProd implements ICatProd, Serializable {
    private Set<IProduto> ctp;
    private int validos;
    private int total;

    /**
     * Construtor vazio de um catálogo de produtos.
     */
    public CatProd (){
        this.ctp = new TreeSet<>(new ComparatorCatProd());
        this.validos = 0;
        this.total = 0;
    }

    /**
     * Construtor parametrizado de um catálogo de produtos.
     * Aceita como parâmetros as variáveis de instância.
     */
    public CatProd(Set<IProduto> ctp, int validos, int total) {
        this.setCTP(ctp);
        this.validos = validos;
        this.total = total;
    }

    /**
     * Construtor por cópia de um catálogo de produtos.
     * @param ctp Catálogo de produtos para ser copiado.
     */
    public CatProd(CatProd ctp){
        this.setCTP(ctp.getCTP());
        this.validos = ctp.getValidos();
        this.total = ctp.getTotal();
    }

    /**
     * Devolve o conjunto de produtos válidos do catálogo de produtos.
     * @return Conjunto de Produtos válidos do catálogo de produtos.
     */
    public Set<IProduto> getCTP(){
        Set<IProduto> ret = new TreeSet<>(new ComparatorCatProd());
        for (IProduto s : this.ctp){
            ret.add(s.clone());
        }
        return ret;
    }

    /**
     * Altera a conjunto de produtos do catálogo para um novo conjunto de produtos
     * @param ctp Conjunto de produtos novo.
     */
    public void setCTP(Set<IProduto> ctp) {
        this.ctp = new TreeSet<>(new ComparatorCatProd());
        for (IProduto s : ctp){
            this.ctp.add(s.clone());
        }
    }

    /**
     * Retorna o número de produtos válidos.
     * @return Número de produtos válidos.
     */
    public int getValidos() {
        return this.validos;
    }

    /**
     * Altera o número total de produtos válidos no catálogo de produtos.
     * @param validos Número de produtos válidos.
     */
    public void setValidos(int validos) {
        this.validos = validos;
    }

    /**
     * Retorna o número total de produtos lidos do ficheiro de Produtos.
     * @return Número total de produtos lidos do ficheiro de Produtos.
     */
    public int getTotal() {
        return this.total;
    }

    /**
     * Altera o número total de produtos lidos para um novo total dado por argumento.
     * @param total Total novo de produtos lidos para o catálogo.
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * Retorna um catálogo de produtos idêntico à instância que invoca o método.
     * @return Um catálogo de produtos idêntico à instância que invoca o método.
     */
    public ICatProd clone(){
        return new CatProd(this);
    }

    /**
     * Compara dois objetos, deve ser usado, concretamente, para comparar 2 catálogos de produtos.
     * @param o Objeto para comparar.
     * @return false, se forem diferentes, true se forem iguais.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatProd catProd = (CatProd) o;
        return validos == catProd.validos &&
                total == catProd.total &&
                this.ctp.equals(catProd.ctp);
    }

    /**
     * Adiciona um produto ao catálogo de produtos.
     * @param p Produto a adicionar.
     */
    public void add(IProduto p) {
        this.ctp.add(p.clone());
    }

    /**
     * Verifica se existe um produto no catálogo.
     * @param p Produto para verificar a existência.
     * @return false, se não existir, true se existir.
     */
    public boolean existeProduto(IProduto p) {
        return this.ctp.contains(p);
    }

    /**
     * Retorna o conjunto de produtos que nunca foram comprados, apartir dos que já foram comprados.
     * @param inicial Conjuntos de produtos já comprados.
     * @return Conjunto de produtos que não foram comprados
     */
    public Set<IProduto> nuncaComprados(Set<IProduto> inicial){
        Set<IProduto> ret = new TreeSet<>(new ComparatorCatProd());
        for(IProduto c: this.ctp) {
            if(!inicial.contains(c)){
                ret.add(c.clone());
            }
        }
        return ret;
    }
}
