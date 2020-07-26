import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementação de filial, que contém associado a cada cliente informação sobre vendas que ele efetuou.
 */
public class Filial implements IFilial{
    private Map<ICliente,List<ClientesFilial>> filial; //cliente -> informaçoes de venda do cliente

    /**
     * Construtor vazio de filial.
     */
    public Filial(){
        this.filial = new HashMap<>();
    }

    /**
     * Construtor por cópia de filial
     * @param fil Filial usada como cópia.
     */
    public Filial(Filial fil){
        this.filial = fil.getFilial();
    }

    /**
     * Devolve a variável de instância da Filial.
     * @return Associação de cliente para as suas compras.
     */
    public Map<ICliente,List<ClientesFilial>> getFilial(){
        Map<ICliente,List<ClientesFilial>> novo = new HashMap<>();
        for(Map.Entry<ICliente,List<ClientesFilial>> m : this.filial.entrySet()){
            List<ClientesFilial> lista = new ArrayList<>();
            for(ClientesFilial v : m.getValue()){
                lista.add(v.clone());
            }
            novo.put(m.getKey().clone(),lista);
        }
        return novo;
    }

    /**
     * Coloca a variável local igual à dada por parâmetro.
     * @param fil Variável dada por parâmetro para atualizar a variável local.
     */
    public void setFilial(Map<ICliente,List<ClientesFilial>> fil){
        Map<ICliente,List<ClientesFilial>> novo = new HashMap<>();
        for(Map.Entry<ICliente,List<ClientesFilial>> m : fil.entrySet()){
            List<ClientesFilial> lista = new ArrayList<>();
            for(ClientesFilial v : m.getValue()){
                lista.add(v.clone());
            }
            novo.put(m.getKey().clone(),lista);
        }
        this.filial=novo;
    }

    /**
     * Devolve uma filial idêntica à instância atual.
     * @return Nova filial idêntica à instância atual.
     */
    public Filial clone(){
        return new Filial(this);
    }

    /**
     * Compara dois objetos, em que pelo menos um deles é instância de Filial.
     * @param o O objeto a ser comparado.
     * @return false se forem diferentes, true se forem iguais.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filial filial1 = (Filial) o;
        return this.filial.equals(filial1.getFilial());
    }

    /**
     * Adiciona à filial uma venda.
     * @param v Venda a adicionar.
     */
    public void addToFilial(IVenda v) {
        Cliente p = new Cliente(v.getCodClie());
        ClientesFilial f = new ClientesFilial(v.getCodProd(),v.getPrecoUnit(),v.getQuantidade(),v.getTipo(),v.getMes());
        if(this.filial.containsKey(p)){
            this.filial.get(p).add(f);
        }
        else {
            List<ClientesFilial> nova = new ArrayList<>();
            nova.add(f);
            this.filial.put(p,nova);
        }
    }

    /**
     * Devolve o número de compras de um cliente num mês.
     * @param cl Cliente.
     * @param mes Mês.
     * @return Número de compras de um cliente num mês.
     */
    public int getCompras(ICliente cl,int mes){
        int x = 0;

        if(this.filial.containsKey(cl)){
            for(ClientesFilial s: this.filial.get(cl)){
                if(s.getMes()==mes){
                    x+=1;
                }
            }
        }

        return x;
    }

    /**
     * Retorna os clientes que efetuaram compras na filial.
     * @return Conjunto de clientes que efetuaram compras na filial.
     */
    public Set<ICliente> getClientes(){
        return this.filial.keySet();
    }

    /**
     * Retorna os clientes que efetuaram compras na filial num mês.
     * @param mes mês.
     * @return Conjunto de clientes que efetuaram compras na filial num mês.
     */
    public Set<ICliente> getClientes(int mes) {
        Set<ICliente> ret = new TreeSet<>(new ComparatorCatClientes());
        for(Map.Entry<ICliente,List<ClientesFilial>> entrada : this.filial.entrySet()) {
            for(ClientesFilial cf : entrada.getValue()) {
                if (cf.getMes()==mes) {
                    ret.add(entrada.getKey().clone());
                    break;
                }
            }
        }
        return ret;
    }

    /**
     * Retorna o número de vendas de valor 0.
     * @return Número de vendas cujo valor é 0.
     */
    public int getVendasZero(){
        int r = 0;
        for(List<ClientesFilial> x : this.filial.values()){
            for(ClientesFilial s : x){
                if(s.getValorCompra()==0) r++;
            }
        }
        return r;
    }

    /**
     * Retorna os produtos comprados na filial.
     * @return Os produtos comprados na filial, agrupados.
     */
    public Set<IProduto> getProdutos(){
        Set<IProduto> tree = new HashSet<>();
        for(List<ClientesFilial> list : this.filial.values()) {
            for(ClientesFilial cl : list) {
                tree.add(cl.getProdutoBought());
            }
        }
        return tree;
    }

    /**
     * Atualiza um par com as vendas dum dado mês e duma filial.
     * @param mes mês.
     * @param filiais número máximo de filiais.
     * @param filial número da filial.
     * @param par Par.
     */
    public void atualizaPar(int mes, int filiais, int filial, ParInterativa2 par){
        for(Map.Entry<ICliente,List<ClientesFilial>> entry : this.filial.entrySet()) {
            for(ClientesFilial f : entry.getValue()) {
                if(f.getMes()==mes) {
                    par.addVenda(entry.getKey().clone(),filial);
                }
            }
        }
    }

    /**
     * Devovle uma lista de vendas efetuados dum cliente.
     * @param cl cliente.
     * @return Lista de vendas efetuadas por esse cliente.
     */
    public List<ClientesFilial> getVendas(ICliente cl){
        return this.filial.get(cl).stream().map(ClientesFilial::clone).collect(Collectors.toList());
    }

    /**
     * Atualiza um par com um cliente caso o produto seja comprado num mês.
     * @param par par .
     * @param p produto.
     * @param mes mês.
     */
    public void atualizaPar(ParInterativa4 par, IProduto p, int mes) {
        for(Map.Entry<ICliente,List<ClientesFilial>> lista : this.filial.entrySet()) {
            for(ClientesFilial cl : lista.getValue()) {
                if(cl.getCodProd().equals(p.getCodigo()) && cl.getMes()==mes){
                    par.addCliente(lista.getKey().clone(),cl.getPrecoUnit()*cl.getQuantidade());
                }
            }
        }
    }

    /**
     * Retorna uma associação entre produtos e um par que tem produto, vezes que foi comprado e clientes.
     * @return associação entre produtos e um par que tem produto, vezes que foi comprado e clientes.
     */
    public Map<IProduto,ParQuery6> mapComprados(){
        Map<IProduto,ParQuery6> ret = new HashMap<>();
        for(Map.Entry<ICliente,List<ClientesFilial>> mapa : this.filial.entrySet()){
            for(ClientesFilial cle : mapa.getValue()){
               IProduto prod = new Produto(cle.getCodProd());

               if(ret.containsKey(prod)){
                   ret.get(prod).atualiza(mapa.getKey(),cle.getQuantidade());
               }

               else {
                   ret.put(prod,new ParQuery6(prod,cle.getQuantidade()));
               }
            }
        }
        return ret;
    }
}
