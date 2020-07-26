import java.io.Serializable;

public interface IFaturacao extends Serializable {
    /**
     * Adiciona à faturação dados de venda recebidos por parâmetro.
     * @param filial filial que foi realizada a compra.
     * @param modo modo que foi realizada a compra.
     * @param prod prod que foi comprado.
     * @param preco preco a que o produto foi comprado.
     * @param quantidade quantidade do produto que foi comprado.
     * @param mes mês em que foi realizada a compra
     */
    void addToFaturacao(int filial, String modo, IProduto prod, double preco, int quantidade, int mes);
    /**
     * Devolve o total faturado num mês por um dado produto
     * @param mes mês
     * @param prod produto
     * @return Total faturado num mês por um produto.
     */
    double getTotalFaturado(int mes, IProduto prod);
    /**
     * Devolve o total faturado por todos os produtos.
     * @return Total faturado por todos os produtos.
     */
    double getTotalFaturado();
    /**
     * Devolve o número de compras num dado mês.
     * @param mes mês.
     * @return Número total de compras realizadas nesse mês.
     */
    int getNumCompras(int mes);
    /**
     * Devolve o total faturado dado um mês e uma filial.
     * @param mes mês.
     * @param filial filial.
     * @return Total faturado num mês e numa filial.
     */
    double getTotalFaturado(int mes, int filial);
    /**
     * Devolve o total faturado por um produto num mês e numa filial
     * @param mes mês.
     * @param filial filial.
     * @param prod produto.
     * @return Total faturado pelo produto num mês e numa filial.
     */
    double getTotalFaturado(int mes, int filial, IProduto prod);
}
