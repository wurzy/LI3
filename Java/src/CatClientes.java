import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
/**
 * Classe que implementa a organização de clientes num catálogo.
 */
public class CatClientes implements ICatClientes, Serializable {
    private Set<ICliente> ctc;
    private int validos;
    private int total;

    /**
     * Construtor vazio do catálogo.
     */
    public CatClientes (){
        this.ctc = new TreeSet<>(new ComparatorCatClientes());
        this.validos = 0;
        this.total = 0;
    }
    /**
     * Construtor parametrizado do catálogo.
     * Aceita como parâmetro as variáveis de instância.
     */
    public CatClientes(Set<ICliente> ctc, int validos, int total) {
        this.setCTC(ctc);
        this.validos = validos;
        this.total = total;
    }

    /**
     * Construtor por cópia do catálogo de clientes.
     * @param ctc Outra instância de Catálogo de Clientes.
     */
    public CatClientes(CatClientes ctc){
        this.setCTC(ctc.getCTC());
        this.validos = ctc.getValidos();
        this.total = ctc.getTotal();
    }

    /**
     * Retorna o catálogo de clientes em forma de conjunto de clientes.
     * @return Um conjunto de clientes.
     */
    public Set<ICliente> getCTC(){
        Set<ICliente> ret = new TreeSet<>(new ComparatorCatClientes());
        for (ICliente s : this.ctc){
            ret.add(s.clone());
        }
        return ret;
    }

    /**
     * Coloca a variável interna do catálogo através dum conjunto por parâmetro.
     * @param ctc Conjunto usado como base.
     */
    public void setCTC(Set<ICliente> ctc) {
        this.ctc = new TreeSet<>(new ComparatorCatClientes());
        for (ICliente s : ctc){
            this.ctc.add(s.clone());
        }
    }

    /**
     * Função que retorna o número total de clientes no catálogo de clientes.
     * @return O número total de clientes que estão no catálogo de clientes.
     */
    public int getValidos() {
        return this.validos;
    }

    /**
     * Coloca o número de válidos o inteiro dado por parâmetro.
     * @param validos Inteiro para colocar como número total de clientes válidos.
     */
    public void setValidos(int validos) {
        this.validos = validos;
    }

    /**
     * Retorna o total de clientes lidos no ficheiro de Clientes.
     * @return Total de clientes lidos do ficheiro.
     */
    public int getTotal() {
        return this.total;
    }

    /**
     * Coloca a variável local de total de clientes a dada por argumento.
     * @param total Total de clientes lidos do ficheiro.
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * Retorna um novo catálogo idêntico ao objeto que invoca este método.
     * @return Um novo catálogo igual ao objeto que invoca este método.
     */
    public CatClientes clone(){
        return new CatClientes(this);
    }

    /**
     * Utiliza-se para comparar dois objetos, em concreto, deverá ser usado para comparar dois catálogos de clientes.
     * @param o Objeto para comparar
     * @return
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatClientes that = (CatClientes) o;
        return validos == that.validos &&
                total == that.total &&
                this.ctc.equals(that.ctc);
    }

    /**
     * Adiciona um cliente ao catálogo de clientes.
     * @param cl Cliente para adicionar.
     */
    public void add(ICliente cl) {
        this.ctc.add(cl.clone());
    }

    /**
     * Verifica se existe um cliente no catálogo de clientes.
     * @param cl Cliente que será verificada a existência no catálogo de clientes.
     * @return false, se não existe, true se existe.
     */
    public boolean existeCliente(ICliente cl) {
        return this.ctc.contains(cl);
    }

}
