import java.io.Serializable;
/**
 * Classe que implementa a informação acerca de uma venda para um cliente, para a estrutura de Filial.
 */
public class ClientesFilial implements Serializable {
    private String codProd;
    private double precoUnit;
    private int quantidade;
    private String tipo;
    private int mes;

    /**
     * Construtor por parâmetro da informação de venda do cliente.
     * @param codProd Código do produto comprado.
     * @param precoUnit Preço do produto comprado por unidade.
     * @param quantidade Quantidade do produto comprado.
     * @param tipo Modo em que o produto foi comprado.
     * @param mes Mês em que o produto foi comprado.
     */
    public ClientesFilial(String codProd, double precoUnit, int quantidade, String tipo, int mes){
        this.codProd = codProd;
        this.precoUnit = precoUnit;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.mes = mes;
    }

    /**
     * Faz a cópia da instância de informações do cliente.
     * Recebe, por argumento, outra ClientesFilial para copiar.
     * @param v A outra ClientesFilial que será usada como cópia.
     */
    public ClientesFilial(ClientesFilial v){
        this.codProd = (v.getCodProd());
        this.precoUnit = (v.getPrecoUnit());
        this.quantidade = (v.getQuantidade());
        this.tipo = (v.getTipo());
        this.mes = (v.getMes());
    }

    /**
     * Devolve o código do produto comprado.
     * @return Código do produto comprado.
     */
    public String getCodProd() {
        return this.codProd;
    }

    /**
     * Devolve o preço por unidade do produto comprado.
     * @return Preço por unidade do produto comprado.
     */
    public double getPrecoUnit() {
        return this.precoUnit;
    }

    /**
     * Devolve a quantidade do produto comprado pelo cliente,
     * @return
     */
    public int getQuantidade() {
        return this.quantidade;
    }

    /**
     * Devolve o tipo de compra que foi realizada.
     * @return Tipo de compra realizada (N ou P).
     */
    public String getTipo() {
        return this.tipo;
    }

    /**
     * Devolve o mês em que a compra foi realizada.
     * @return Mês em que foi realizada a compra.
     */
    public int getMes() {
        return this.mes;
    }

    /**
     * Retorna uma cópia da instância de ClientesFilial atual.
     * @return Cópia de informação de venda de um Cliente.
     */
    public ClientesFilial clone(){
        return new ClientesFilial(this);
    }

    /**
     * Compara a instância atual de ClientesFilial com outro objeto qualquer, em concreto deve ser usado para comparar dois objetos do mesmo tipo.
     * @param o Objeto para comparar.
     * @return false, se forem diferentes, true, se forem iguais.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        ClientesFilial v = (ClientesFilial) o;
        return (this.codProd.equals(v.getCodProd()) &&
                this.precoUnit == v.getPrecoUnit() &&
                this.quantidade == v.getQuantidade() &&
                this.tipo.equals(v.getCodProd()) &&
                this.mes == v.getMes());
    }

    /**
     * Retorna o valor total da compra, ou seja, Preço * Quantidade.
     * @return Valor total da compra.
     */
    public double getValorCompra(){
        return this.precoUnit*this.quantidade;
    }

    /**
     * Retorna o produto que foi comprado.
     * @return O produto que foi comprado.
     */
    public IProduto getProdutoBought(){
        return new Produto(this.getCodProd());
    }
}
