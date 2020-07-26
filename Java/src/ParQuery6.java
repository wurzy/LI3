import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * Par com produto, o número de vezes que foi comprado e por quantos clientes no ano.
 */
public class ParQuery6 implements Serializable {
    private IProduto produto;
    private int compras;
    private Set<ICliente> clientes;

    /**
     * Construtor por paramâmetros do Par.
     * @param prod produto.
     * @param quantidade quantidade comprado.
     */
    public ParQuery6(IProduto prod, int quantidade){
        this.produto = prod.clone();
        this.compras = quantidade;
        this.clientes = new TreeSet<>(new ComparatorCatClientes());
    }

    /**
     * Construtor por cópia do par.
     * @param par par.
     */
    public ParQuery6(ParQuery6 par) {
       this.produto = par.getProduto();
       this.compras = par.getCompras();
       this.clientes = par.getClientes();
    }

    /**
     * Atualiza a quantidade atual de compras e adiciona um cliente.
     * @param cliente cliente.
     * @param quantidade quantidade a adicionar.
     */
    public void atualiza(ICliente cliente, int quantidade) {
        this.compras+=quantidade;
        this.clientes.add(cliente.clone());
    }

    /**
     * Retorna um produto.
     * @return um produto.
     */
    public IProduto getProduto(){
        return this.produto.clone();
    }

    /**
     * Retorna o número de compras.
     * @return o número de compras.
     */
    public int getCompras(){
        return this.compras;
    }

    /**
     * Retorna o conjunto de clientes que compraram o produto.
     * @return o conjunto de clientes que compraram o produto.
     */
    public Set<ICliente> getClientes(){
        Set<ICliente> tree = new TreeSet<>(new ComparatorCatClientes());
        for(ICliente cl : this.clientes){
            tree.add(cl.clone());
        }
        return tree;
    }

    /**
     * Atualiza as compras do produto com um incremento de quantidade.
     * @param quantidade quantidade a incrementar.
     */
    public void atualizaCompras(int quantidade){
        this.compras+=quantidade;
    }

    /**
     * Retorna um clone do Par.
     * @return um clone do Par.
     */
    public ParQuery6 clone(){
        return new ParQuery6(this);
    }
}
