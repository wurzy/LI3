import java.io.Serializable;
import java.util.AbstractMap.*;
import java.util.List;
import java.util.Set;

public interface IGereVendasModel extends Serializable {
    /**
     * Função que gera todas as variáveis de instância do model.
     */
    void createData();
    /**
     * Função que retorna o último ficheiro lido.
     */
    String getLastFileRead();
    /**
     * Função que cria a filial e a faturação.
     * @param filename Ficheiro de texto onde se encontram as vendas.
     */
    void createStructs(String filename);
    /**
     * Função que verifica se o alguma das variáveis de instância do model são null.
     */
    boolean isNull();
    /**
     * Função que verifica se existe cliente.
     */
    boolean existeCliente(ICliente c);
    /**
     * Função que verifica se existe produto.
     */
    boolean existeProduto(IProduto p);
    /**
     * Função que retorna o número de vendas lidas.
     */
    int getVendasLidos();
    /**
     * Função que retorna o número de vendas válidas.
     */
    int getVendasValidas();
    /**
     * Função que retorna o número de meses.
     */
    int getMeses();
    /**
     * Função que retorna o número de filias.
     */
    int getFiliais();
    /**
     * Função que retorna o número de clientes.
     */
    int getClientes();
    /**
     * Função que retorna o número de clientes inválidos.
     */
    int getClientesInvalidos();
    /**
     * Função que retorna o número de clientes que realizaram compras.
     */
    int getClientesCompraram();
    /**
     * Função que retorna o número de clientes que realizaram compras num certo mês numa filial em questão.
     * @param mes Mês no qual queremos verificar se os clientes realizaram compras.
     * @param filial Filial em questão.
     */
    int getClientesCompraram(int mes,int filial);
    /**
     * Função que retorna o número vendas que estão a zero.
     */
    int getVendasZero();
    /**
     * Função que retorna o número de produtos comprados.
     */
    int getProdutosComprados();
    /**
     * Função que retorna o número total de produtos válidos.
     */
    int getProdutosValidos();
    /**
     * Função que retorna o número total de produtos.
     */
    int getProdutosTotal();
    /**
     * Retorna número de compras num mês.
     * @param mes mês
     * @return Número de compras num mês.
     */
    int getNumCompras(int mes);
    /**
     * Função que devolve o tempo de carregamento das vendas.
     */
    double getTempoVendas();
    /**
     * Função que devolve o tempo total de carregamento dos ficheiros para o programa.
     */
    double getTempoTotal();
    /**
     * Função que retorna o total faturado.
     */
    double getFaturacao();
    /**
     * Função que retorna o total faturado num determinado mês numa filial.
     * @param mes O mês no qual quer ver a faturação.
     * @param filial Filial em questão
     */
    double getFaturacao(int mes, int filial);
    /**
     * Função que determina o total faturado por um produto numa filial num determinado mês.
     * @param p Produto em questão.
     * @param mes Mês em que queremos o total faturado.
     * @param filial Filial em questão
     */
    double getFaturado(IProduto p, int mes, int filial);
    /**
     * Função que devolve informações para as queries estatísticas.
     */
    QueryEstatisticaAux getDadosGerais();
    /**
     * Função que retorna os produtos que foram comprados.
     */
    Set<IProduto> getSetProdutosComprados();
    /**
     * Função que retorna os produtos que nunca foram comprados.
     */
    Set<IProduto> getSetProdsNuncaComprados();
    /**
     * Função que retorna os clientes que realizaram compras.
     */
    Set<ICliente> getSetClientesComprados();
    /**
     * Função que devolve todos os ficheiros de vendas que existem.
     */
    List<String> ficheirosPossiveis();
    /**
     * Função que retorna o número total de clientes distintos e o número total de vendas para cada filial.
     * @param mes Mês no qual queremos ver a quantidade de vendas.
     */
    ParInterativa2 getNumVendas(int mes);
    /**
     * Função que retorna o número de compras, os produtos comprados, e o total gasto.
     * @param cl Cliente em questão
     */
    ParInterativa3 getComprasMes(ICliente cl);
    /**
     * Função que retorna mês a mês, quantas vezes foi comprado um produto, por quantos clientes diferentes e o total facturado.
     * @param p Produto do qual queremos saber as informações.
     * @param mes Mês em questão.
     */
    ParInterativa4 getProdutoComprasMes(IProduto p, int mes);
    /**
     * Função que retorna uma lista que contém pares produto quantidade.
     * @param cliente Cliente em questão.
     */
    List<ParProdQuantidade> getProdutosQuantidade(ICliente cliente);
    /**
     * Função que retorna os X produtos mais comprados assim como os clientes que os compraram.
     * @param lim Número de produtos que quer.
     */
    List<ParQuery6> getProdutosMaisComprados(int lim);
    /**
     * Função que determina, para cada filial, a lista dos três maiores compradores em termos de dinheiro facturado
     * @param filial Filial em questão.
     */
    List<ParQuery7> getClientesMaisCompradores(int filial);
    /**
     * Função que retorna determina os códigos dos X clientes que mais produtos diferentes.
     * @param max Corresponde ao máximo(X) de clientes.
     */
    List<ParQuery8> getMaisProdsDif(int max);
    /**
     * Função que recebendo um produto determina o conjunto dos X clientes que mais o compraram e, para cada um, qual o valor gasto.
     * @param prod Produto em questão.
     * @param max Corresponde ao máximo(X) de clientes.
     */
    List<ParQuery9> getClientePorProduto (IProduto prod, int max);
}
