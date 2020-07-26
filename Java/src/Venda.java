import java.io.Serializable;
import java.util.InputMismatchException;

/**
 * Classe que gera uma venda.
 */
public class Venda implements IVenda, Serializable {
    private String codProd;
    private String codClie;
    private double precoUnit;
    private int quantidade;
    private String tipo;
    private int mes;
    private int filial;

    /**
     * Construtor por default.
     */
    public Venda(){
        this.codProd = "";
        this.codClie = "";
        this.precoUnit = 0.0;
        this.quantidade = 0;
        this.tipo = "";
        this.mes = 0;
        this.filial = 0;
    }

    /**
     * Construtor parametrizado.
     * @param codProd Código do produto que foi comprado.
     * @param codClie Código do cliente que realizou a compra.
     * @param precoUnit Preço do produto que foi comprado.
     * @param quantidade Quantidade comprada.
     * @param tipo Tipo da compra.
     * @param mes Mês em que a compra foi realizada.
     * @param filial Filial em que a compra foi realizada.
     */
    public Venda(String codProd, String codClie, double precoUnit, int quantidade, String tipo, int mes, int filial){
        this.codProd = codProd;
        this.codClie = codClie;
        this.precoUnit = precoUnit;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.mes = mes;
        this.filial = filial;
    }

    /**
     * Construtor por cópia.
     * @param v Venda que queremos copiar.
     */
    public Venda(Venda v){
        setCodProd(v.getCodProd());
        setCodClie(v.getCodClie());
        setPrecoUnit(v.getPrecoUnit());
        setQuantidade(v.getQuantidade());
        setTipo(v.getTipo());
        setMes(v.getMes());
        setFilial(v.getFilial());
    }

    /**
     * Retorna codigo do produto
     */
    public String getCodProd() {
        return this.codProd;
    }

    /**
     * Função que define o código de produto.
     * @param codProd Código do produto em questão.
     */
    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    /**
     * Função que devolve o código do cliente.
     */
    public String getCodClie() {
        return this.codClie;
    }

    /**
     * Função que define o código do cliente.
     * @param codClie Código do cliente em questão.
     */
    public void setCodClie(String codClie) {
        this.codClie = codClie;
    }

    /**
     * Função que devolve o o preço de uma venda.
     */
    public double getPrecoUnit() {
        return this.precoUnit;
    }

    /**
     * Função que define o preço unitário de uma venda.
     * @param precoUnit Preço unitário da venda.
     */
    public void setPrecoUnit(double precoUnit) {
        this.precoUnit = precoUnit;
    }

    /**
     * Função que define o preço unitário de uma venda.
     */
    public int getQuantidade() {
        return this.quantidade;
    }

    /**
     * Função que define a quantidade de uma venda.
     * @param quantidade Quantidade em questão.
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Função que devolve o tipo de uma compra.
     */
    public String getTipo() {
        return this.tipo;
    }

    /**
     * Função que define o tipo de uma venda.
     * @param tipo tipo da venda
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Função que devolve o mês em que a compra foi efetuada.
     */
    public int getMes() {
        return this.mes;
    }

    /**
     * Função que define o preço mês em que a compra foi efetuada.
     * @param mes Mês em questão.
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * Função que devolve a filial em que a compra foi efetuada.
     */
    public int getFilial() {
        return this.filial;
    }

    /**
     * Função que define a filial em que a compra foi efetuada.
     */
    public void setFilial(int filial) {
        this.filial = filial;
    }

    /**
     * Função que dá clone a uma venda.
     */
    public IVenda clone(){
        return new Venda(this);
    }


    /**
     * Função que verifica se duas vendas são iguais.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Venda v = (Venda) o;
        return (this.codProd.equals(v.codProd) &&
                this.codClie.equals(v.codClie) &&
                this.precoUnit == v.precoUnit &&
                this.quantidade == v.quantidade &&
                this.tipo.equals(v.tipo) &&
                this.mes == v.mes &&
                this.filial == v.filial);
    }

    /**
     * Função que transforma duas vendas em strings.
     */
    public String toString() {
        return ("Codigo de produto = " + this.codProd + "\n" +
                "Codigo de cliente = " + this.codClie + "\n" +
                "Preço unitario = " + this.precoUnit + "\n" +
                "Quantidade = " + this.quantidade + "\n" +
                "Tipo = " + this.tipo + "\n" +
                "Mes = " + this.mes + "\n" +
                "Filial = " + this.filial + "\n");
    }
}
