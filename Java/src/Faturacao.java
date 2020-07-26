import java.util.*;

/**
 * Implementação da interface IFaturacao, que tem o relacionamento entre produtos e as suas vendas.
 */
public class Faturacao implements IFaturacao{
    private List<Map<IProduto, InfoProdFat>> produtosinfoN; //mes -> info

    /**
     * Construtor parametrizado duma faturação.
     * @param meses Número de meses que tem o ano.
     */
    public Faturacao(int meses) {
        this.produtosinfoN = new ArrayList<>();
        for (int i = 0; i < meses; i++) {
            this.produtosinfoN.add(new HashMap<>());
        }
    }

    /**
     * Construtor por cópia de uma faturação.
     * @param p Faturação que é usada como cópia.
     */
    public Faturacao(Faturacao p) {
        this.produtosinfoN = p.getProdutosInfoN();
    }

    /**
     * Retorna a variável de instância que contém uma associação de produto para a informação de venda, para cada mês.
     * @return Uma lista com o tamanho do número de meses, com associação de cada produto para cada venda realizada com ele.
     */
    public List<Map<IProduto,InfoProdFat>> getProdutosInfoN(){
        List<Map<IProduto,InfoProdFat>> list  = new ArrayList<>();

        for(Map<IProduto,InfoProdFat> x : this.produtosinfoN) {
            Map<IProduto,InfoProdFat> novo = new HashMap<>();

            for(Map.Entry<IProduto,InfoProdFat> s: x.entrySet()) {
                novo.put(s.getKey().clone(),s.getValue().clone());
            }

            list.add(novo);
        }
        return list;
    }

    /**
     * Método que cria uma nova Faturação idêntica à que que invoca este método.
     * @return Uma nova faturação.
     */
    public Faturacao clone(){
        return new Faturacao(this);
    }

    /**
     * Compara uma Faturação e um objeto.
     * @param o Objeto para ser comparado.
     * @return false, se forem diferentes, true se forem iguais.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faturacao that = (Faturacao) o;
        return (this.produtosinfoN.equals(that.getProdutosInfoN()) &&
                this.produtosinfoN.equals(that.getProdutosInfoN()));
    }

    /**
     * Adiciona à faturação dados de venda recebidos por parâmetro.
     * @param filial filial que foi realizada a compra.
     * @param modo modo que foi realizada a compra.
     * @param prod prod que foi comprado.
     * @param preco preco a que o produto foi comprado.
     * @param quantidade quantidade do produto que foi comprado.
     * @param mes mês em que foi realizada a compra
     */
    public void addToFaturacao(int filial, String modo, IProduto prod, double preco, int quantidade, int mes) {
        if(this.produtosinfoN.get(mes-1).containsKey(prod)) {
            this.produtosinfoN.get(mes-1).get(prod).addCompra(filial,preco,quantidade);
        }
        else {
            this.produtosinfoN.get(mes-1).put(prod.clone(),new InfoProdFat());
            this.produtosinfoN.get(mes-1).get(prod).addCompra(filial,preco,quantidade);
        }
    }

    /**
     * Devolve o total faturado num mês por um dado produto
     * @param mes mês
     * @param prod produto
     * @return Total faturado num mês por um produto.
     */
    public double getTotalFaturado(int mes, IProduto prod) {
        double d = 0;
        Map<IProduto, InfoProdFat> n = this.produtosinfoN.get(mes-1);
        if(n.containsKey(prod)){
            d+=n.get(prod).getFaturado();
        }
        return d;
    }

    /**
     * Devolve o total faturado dado um mês e uma filial.
     * @param mes mês.
     * @param filial filial.
     * @return Total faturado num mês e numa filial.
     */
    public double getTotalFaturado(int mes, int filial) {
        Map<IProduto, InfoProdFat> n = this.produtosinfoN.get(mes-1);
        double d = 0;
        for(InfoProdFat x : n.values()) {
            d+=x.getFaturado(filial);
        }
        return d;
    }

    /**
     * Devolve o total faturado por um produto num mês e numa filial
     * @param mes mês.
     * @param filial filial.
     * @param prod produto.
     * @return Total faturado pelo produto num mês e numa filial.
     */
    public double getTotalFaturado(int mes, int filial, IProduto prod) {
        InfoProdFat ninfo = this.produtosinfoN.get(mes-1).get(prod);
        double d = 0;
        if(ninfo!=null) {
            d+=ninfo.getFaturado(filial);
        }

        return d;
    }

    /**
     * Devolve o total faturado por todos os produtos.
     * @return Total faturado por todos os produtos.
     */
    public double getTotalFaturado() {
        double d = 0;

        for(Map<IProduto,InfoProdFat> x: this.produtosinfoN) {
            d+= x.values().stream()
                    .mapToDouble(InfoProdFat::getFaturado)
                    .sum();
        }
        return d;
    }

    /**
     * Devolve o número de compras num dado mês.
     * @param mes mês.
     * @return Número total de compras realizadas nesse mês.
     */
    public int getNumCompras(int mes) {
        int i = 0;
        Map<IProduto, InfoProdFat> n = this.produtosinfoN.get(mes-1);
        for(InfoProdFat info : n.values()) {
            i+=info.getNumCompras();
        }

        return i;
    }

}
