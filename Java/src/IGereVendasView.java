import java.io.Serializable;
import java.util.AbstractMap.*;
import java.util.List;
import java.util.Set;

public interface IGereVendasView extends Serializable {
    /**
     * Retorna a opção escolhida pelo utilizador no menu.
     * @return Opção do menu.
     */
    int getOpcao();
    /**
     * Executa menu principal.
     */
    void run();
    /**
     * Executa menu de leitura de ficheiros.
     */
    void runNovo();
    /**
     * Executa menu queries estatísticas.
     */
    void runEstatistica();
    /**
     * Executa menu de queries estatísticas sobre estruturas de dados.
     */
    void runSubEstatistica();
    /**
     * Executa menu de queries interativas.
     */
    void runQueries();
    /**
     * Indica o fim do menú de estatística.
     */
    void estatisticaEnd();
    /**
     * Indica o fim do menu de estatística sobre as estruturas de dados.
     */
    void subEstatisticaEnd();
    /**
     * Indica o fim do menu de queries interativas.
     */
    void queriesEnd();
    /**
     * Indica o fim do menu de leitura de ficheiros.
     */
    void leituraEnd();
    /**
     * Imprime um erro no ecrã.
     * @param s Erro.
     */
    void printError(String s);
    /**
     * Método que espera input de um código de produto.
     */
    void askProduto();
    /**
     * Método que espera input de um número de clientes.
     */
    void askNumClientes();
    /**
     * Método que espera input de um código de cliente.
     */
    void askCliente();
    /**
     * Método que espera input de um numero de produtos.
     */
    void askNumProds();
    /**
     * Métoda que espera input dum mês.
     */
    void askMes();
    /**
     * Método que espera input de um código de um número de linhas.
     */
    void askLinhas();
    /**
     * Método que espera input de um código de um número de colunas.
     */
    void askColunas();
    /**
     * Método que indica que o programa vai saír de execução.
     */
    void exitProgram();
    /**
     * Imprime no ecrã o tempo de execução da query.
     * @param tempo Tempo.
     */
    void printTempo(double tempo);
    /**
     * Imprime uma string no ecrã.
     * @param s String.
     */
    void print(String s);
    /**
     * Método que espera pelo input dum "Enter" pelo utilizador.
     */
    void waitEnter();
    /**
     * Imprime os resultados da query de estatística sobre o ficheiro de vendas.
     * @param query Classe que contém os resultados.
     */
    void query1EstatisticaPrint(QueryEstatisticaAux query);
    /**
     * Imprime os resultados da query de estatística sobre o nº de compras por mês.
     * @param mes mês.
     * @param quantidade mês.
     */
    void query2EstatisticaPrint(int mes, int quantidade);
    /**
     * Imprime a faturação total por mês e por filial.
     * @param filialmes Lista com pares de filial e valor faturado.
     * @param mes mês.
     * @param total total faturado.
     */
    void query3EstatisticaPrint(List<SimpleEntry<Integer, Double>> filialmes, int mes, double total);
    /**
     * Imprime o número total de clientes distintos que compraram em cada mês.
     * @param clientesmes Lista com pares de filial e clientes que compraram na filial.
     * @param mes mês.
     */
    void query4EstatisticaPrint(List<SimpleEntry<Integer, Integer>> clientesmes, int mes);
    /**
     * Imprime códigos dos produtos nunca comprados e o respectivo total, por ordem alfabética.
     * @param prods conjunto de produtos.
     * @param lin linhas do navegador.
     * @param col colunas do navegador.
     */
    void query1InterativaPrint(Set<IProduto> prods, int lin, int col);
    /**
     * Imprime o número total de vendas realizadas num mês e o número total de clientes distintos que as fizeram
     * @param numfiliais número de filiais.
     * @param par Par com o número total global de vendas realizadas e número total de clientes distintos por filial.
     */
    void query2InterativaPrint(int numfiliais, ParInterativa2 par);
    /**
     * Para cada mês, quantas compras, quantos produtos distintos comprou e quanto gastou no total um cliente
     * @param par Par que contém um cliente e as compras que fez, quantos produtos comprou e o total faturado
     * @param mes mês.
     * @param d tempo.
     */
    void query3InterativaPrint(ParInterativa3 par, int mes, double d);
    /**
     * Quantas vezes foi comprado um produto em cada mês, por quantos clientes diferentes e o total facturado
     * @param mes mês.
     * @param par Par que tem o número de clientes, o número total de vezes comprado e o faturado do produto.
     * @param d tempo.
     */
    void query4InterativaPrint(int mes, ParInterativa4 par, double d);
    /**
     * Códigos do(s) produto(s) que um cliente mais comprou e quantidade.
     * @param set Lista pares de produtos com a quantidade comprada de cada um.
     */
    void query5InterativaPrint(List<ParProdQuantidade> set);
    /**
     * Conjunto dos X produtos mais vendidos, com os clientes respetivos.
     * @param set Lista com pares de produto, quantidade comprada e nº de clientes que compraram o produto.
     * @param limit limite máximo de produtos.
     */
    void query6InterativaPrint(List<ParQuery6> set,int limit);
    /**
     * Três maiores compradores, em termos de dinheiro facturado, de cada filial.
     * @param list Lista com pares de clientes e dinheiro faturado numa filial.
     * @param filial filial.
     * @param numfils número total de filiais.
     * @param d tempo.
     */
    void query7InterativaPrint(List<ParQuery7> list, int filial, int numfils, double d);
    /**
     * Códigos dos X clientes que compraram mais produtos diferentes.
     * @param aux Lista com pares de clientes e quantidade de produtos diferentes que compraram.
     */
    void query8InterativaPrint(List<ParQuery8> aux);
    /**
     * Conjunto dos X clientes que mais compraram um produto e, para cada um, qual o valor gasto.
     * @param pares Lista com pares de clientes que compraram o produto e o valor gasto nele.
     */
    void query9InterativaPrint(List<ParQuery9> pares);
    /**
     * Facturação total com produtos por mês e filial.
     * @param total total de faturado.
     * @param filial filial atual.
     * @param filmax número máximo de filiais.
     * @param mes mês.
     * @param d tempo.
     */
    void query10InterativaPrint(double total, int filial, int filmax, int mes, double d);
}