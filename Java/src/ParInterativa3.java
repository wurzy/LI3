import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Par com cliente e para cada mês quantas compras fez, quantos produtos distintos comprou e quanto gastou.
 */
public class ParInterativa3 implements Serializable {
    private ICliente cliente;
    private List<ClientesFilial> compras;
    private int comprados;
    private int produtos;
    private double gasto;

    /**
     * Construtor parametrizado de par.
     * @param cl Cliente.
     */
    public ParInterativa3(ICliente cl) {
        this.cliente = cl.clone();
        this.compras = new ArrayList<>();
        this.comprados = 0;
        this.produtos = 0;
        this.gasto = 0;
    }

    /**
     * Adiciona uma venda ao Par.
     * @param cl Venda relativa ao cliente.
     */
    public void addVenda(ClientesFilial cl) {
        this.compras.add(cl.clone());
    }

    /**
     * Genera o par consoante o mês por parâmetro.
     * @param mes mês em questão
     */
    public void generate(int mes) {
        Set<IProduto> clie = new TreeSet<>(new ComparatorCatProd());
        for(ClientesFilial cl : this.compras){
            if(cl.getMes()==mes){
                clie.add(new Produto(cl.getCodProd()));
                comprados++;
                this.gasto += cl.getPrecoUnit()*cl.getQuantidade();

            }
        }
        this.produtos = clie.size();
    }

    /**
     * Retorna o número de compras do cliente.
     * @return o número de compras do cliente
     */
    public int getComprados(){
        return this.comprados;
    }

    /**
     * Retorna o número de produtos comprados do cliente.
     * @return o número de produtos comprados do cliente
     */
    public int getProdutos(){
        return this.produtos;
    }

    /**
     * Retorna o total gasto pelo cliente.
     * @return o total gasto pelo cliente.
     */
    public double gasto(){
        return this.gasto;
    }

}
