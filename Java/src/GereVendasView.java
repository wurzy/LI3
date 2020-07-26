import static java.lang.System.*;

import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.*;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * View do programa.
 */
public class GereVendasView implements IGereVendasView {
    private Menu menuPrincipal;
    private Menu menuEstatistica;
    private Menu menuEstatisticaSub;
    private Menu menuQueries;
    private Menu menuLeitura;
    private Menu atual;

    /**
     * Construtor vazio da View.
     */
    public GereVendasView(){
        String[] principal = {
            "Ler ficheiro de vendas;",
            "Guardar estado atual;",
            "Carregar estado gravado;",
            "Consultas estatísticas;",
            "Consultas interativas.",
        };
        String[] queries = {
            "Códigos dos produtos nunca comprados e o respectivo total, por ordem alfabética;",
            "Número total de vendas realizadas num mês e o número total de clientes distintos que as fizeram;",
            "Para cada mês, quantas compras, quantos produtos distintos comprou e quanto gastou no total um cliente;",
            "Quantas vezes foi comprado um produto em cada mês, por quantos clientes diferentes e o total facturado;",
            "Códigos do(s) produto(s) que um cliente mais comprou e quantidade;",
            "Conjunto dos X produtos mais vendidos, com os clientes respetivos;",
            "Três maiores compradores, em termos de dinheiro facturado, de cada filial;",
            "Códigos dos X clientes que compraram mais produtos diferentes;",
            "Conjunto dos X clientes que mais compraram um produto e, para cada um, qual o valor gasto;",
            "Facturação total com um dado produto."
        };
        String[] estatistica = {
            "Dados gerais do ficheiro de vendas;",
            "Dados gerais das estruturas."
        };

        String[] subEstatistica = {
            "Número total de compras por mês;",
            "Faturação total por mês;",
            "Número de clientes distintos que compraram em cada mês."
        };

        String[] lerNovo = {
                "Vendas_1M.txt;",
                "Vendas_3M.txt;",
                "Vendas_5M.txt;"
        };

        this.menuPrincipal = new Menu(principal);
        this.menuEstatistica = new Menu(estatistica);
        this.menuQueries = new Menu(queries);
        this.menuLeitura = new Menu(lerNovo);
        this.menuEstatisticaSub = new Menu(subEstatistica);
    }

    /**
     * Executa menu principal.
     */
    public void run(){
        this.atual = this.menuPrincipal;
        this.atual.executa();
    }
    /**
     * Executa menu queries estatísticas.
     */
    public void runEstatistica() {
        this.atual = this.menuEstatistica;
        this.atual.executa();
    }

    /**
     * Executa menu de queries estatísticas sobre estruturas de dados.
     */
    public void runSubEstatistica(){
        this.atual = this.menuEstatisticaSub;
        this.atual.executa();
    }

    /**
     * Executa menu de queries interativas.
     */
    public void runQueries(){
        this.atual = this.menuQueries;
        this.atual.executa();
    }

    /**
     * Executa menu de leitura de ficheiros.
     */
    public void runNovo(){
        this.atual = this.menuLeitura;
        this.atual.executa();
    }

    /**
     * Método que espera pelo input dum "Enter" pelo utilizador.
     */
    public void waitEnter(){
        out.println("Clique [ENTER] para voltar.");
        try{
            in.read();
        }catch(Exception e){e.printStackTrace();}
    }

    /**
     * Imprime no ecrã o tempo de execução da query.
     * @param tempo Tempo.
     */
    public void printTempo(double tempo) {
        out.printf("Tempo: %f segundos.\n", tempo);
    }

    /**
     * Imprime um erro no ecrã.
     * @param s Erro.
     */
    public void printError(String s) {
        out.println(s);
        waitEnter();
    }

    /**
     * Método que espera input de um código de cliente.
     */
    public void askCliente(){
        out.print("Insira o código do cliente: ");
    }

    /**
     * Método que espera input de um número de clientes.
     */
    public void askNumClientes(){
        out.print("Insira o número de clientes: ");
    }

    /**
     * Método que espera input de um código de produto.
     */
    public void askProduto(){
        out.print("Insira o código do produto: ");
    }

    /**
     * Método que espera input de um numero de produtos.
     */
    public void askNumProds(){
        out.print("Insira o número de produtos: ");
    }

    /**
     * Métoda que espera input dum mês.
     */
    public void askMes(){
        out.print("Insira o mês [1..12]: ");
    }

    /**
     * Método que espera input de um código de um número de linhas.
     */
    public void askLinhas(){
        out.print("Número de linhas [ 10 | 15 | 20 ]: ");
    }

    /**
     * Método que espera input de um código de um número de colunas.
     */
    public void askColunas(){
        out.print("Número de colunas [ 10 | 15 | 20 ]: ");
    }

    /**
     * Método que indica que o programa vai saír de execução.
     */
    public void exitProgram(){
        out.println("A sair do programa...");
    }

    /**
     * Imprime uma string no ecrã.
     * @param s String.
     */
    public void print(String s) {out.print(s);}

    /**
     * Indica o fim do menú de estatística.
     */
    public void estatisticaEnd(){
        this.atual = menuPrincipal;
    }

    /**
     * Indica o fim do menu de estatística sobre as estruturas de dados.
     */
    public void subEstatisticaEnd(){
        this.atual = menuEstatistica;
    }

    /**
     * Indica o fim do menu de queries interativas.
     */
    public void queriesEnd(){
        this.atual = menuPrincipal;
    }

    /**
     * Indica o fim do menu de leitura de ficheiros.
     */
    public void leituraEnd(){this.atual = menuPrincipal;}

    /**
     * Retorna a opção escolhida pelo utilizador no menu.
     * @return Opção do menu.
     */
    public int getOpcao(){
        return this.atual.getOp();
    }

    /**
     * Imprime os resultados da query de estatística sobre o ficheiro de vendas.
     * @param query Classe que contém os resultados.
     */
    public void query1EstatisticaPrint(QueryEstatisticaAux query) {
        out.println("[Vendas]");
        out.println("Último ficheiro de vendas lido: "+ query.getUltimoFile());
        out.println("Válidas: " + query.getVendasValidas() + "/" + query.getVendasLidas());
        out.println("Inválidas: " + query.getVendasInvalidas() + "/" + query.getVendasLidas());
        out.println("Total de compras a 0: "+ query.getVendasZero());
        out.println("Total faturado: " + query.getFaturado() + " €");
        out.println();
        out.println("[Clientes]");
        out.println("Válidos: " + query.getClientesValidos() + "/" + query.getClientesLidos() );
        out.println("Compraram: " + query.getClientesCompraram() + "/" + query.getClientesLidos());
        out.println("Não compraram: " + query.getClientesNaoCompraram() + "/" + query.getClientesLidos());
        out.println();
        out.println("[Produtos]");
        out.println("Válidos: " + query.getProdutosValidos() + "/" + query.getProdutosLidos());
        out.println("Comprados: " + query.getProdutosComprados() + "/" + query.getProdutosLidos());
        out.println("Não comprados: " + query.getProdutosNaoComprados() + "/" + query.getProdutosLidos());
        waitEnter();
    }

    /**
     * Imprime os resultados da query de estatística sobre o nº de compras por mês.
     * @param mes mês.
     * @param quantidade mês.
     */
    public void query2EstatisticaPrint(int mes, int quantidade) {
        if(mes==1) out.println("Foram realizadas as seguintes compras por mês:");
        out.println("Mês [" +mes + "]" +": " + quantidade );
        if(mes == 12) waitEnter();
    }

    /**
     * Imprime a faturação total por mês e por filial.
     * @param filialmes Lista com pares de filial e valor faturado.
     * @param mes mês.
     * @param total total faturado.
     */
    public void query3EstatisticaPrint(List<SimpleEntry<Integer,Double>> filialmes, int mes,double total) {
        out.println("Mês [" + mes + "]:");
        for(SimpleEntry<Integer,Double> x : filialmes) {
            out.println("\tFilial [" + x.getKey() + "]: " + x.getValue() + " €");
        }

        out.println("\tTotal: " + total);
        if(mes==12) waitEnter();
    }

    /**
     * Imprime o número total de clientes distintos que compraram em cada mês.
     * @param clientesmes Lista com pares de filial e clientes que compraram na filial.
     * @param mes mês.
     */
    public void query4EstatisticaPrint(List<SimpleEntry<Integer,Integer>> clientesmes,int mes){
        out.println("Mês [" + mes + "]:");
        for(SimpleEntry<Integer,Integer> x : clientesmes) {
            out.println("\tFilial [" + x.getKey() + "]: " + x.getValue() + " clientes");
        }
        if(mes==12) waitEnter();
    }

    /**
     * Imprime códigos dos produtos nunca comprados e o respectivo total, por ordem alfabética.
     * @param prods conjunto de produtos.
     * @param lin linhas do navegador.
     * @param col colunas do navegador.
     */
    public void query1InterativaPrint(Set<IProduto> prods,int lin, int col){
        if(prods.size()==0) {
            out.println("Não há nenhum elemento.");
            return;
        }
        List<String> lista = prods.stream()
                .map(IProduto::getCodigo)
                .collect(Collectors.toList());
        Navegador nav = new Navegador(lista,lin,col);
        nav.start();
    }

    /**
     * Imprime o número total de vendas realizadas num mês e o número total de clientes distintos que as fizeram
     * @param numfiliais número de filiais.
     * @param par Par com o número total global de vendas realizadas e número total de clientes distintos por filial.
     */
    public void query2InterativaPrint(int numfiliais, ParInterativa2 par) {
        SimpleEntry<Integer,Integer> p;
        for(int i = 1; i <= numfiliais; i++) {
            p = par.getPar(i);
            out.println("\nFilial[" + i + "]:\nClientes: " + p.getKey() + "\n" + "Vendas: " +  p.getValue());
        }
        out.println("\nTotal de clientes distintos: " + par.getNumClientes());
        out.println("Total de vendas distintas: " + par.getNumVendas() + "\n");
    }

    /**
     * Para cada mês, quantas compras, quantos produtos distintos comprou e quanto gastou no total um cliente
     * @param par Par que contém um cliente e as compras que fez, quantos produtos comprou e o total faturado
     * @param mes mês.
     * @param d tempo.
     */
    public void query3InterativaPrint(ParInterativa3 par, int mes, double d) {
        out.println("Mês [" + mes + "]: ");

        out.println("\tCompradas efetuadas: " + par.getComprados());
        out.println("\tProdutos diferentes comprados: " + par.getProdutos());
        out.println("\tDinheiro gasto: " + par.gasto() + " €");

        if(mes==12){
            printTempo(d);
            waitEnter();
        }
    }

    /**
     * Quantas vezes foi comprado um produto em cada mês, por quantos clientes diferentes e o total facturado
     * @param mes mês.
     * @param par Par que tem o número de clientes, o número total de vezes comprado e o faturado do produto.
     * @param d tempo.
     */
    public void query4InterativaPrint(int mes, ParInterativa4 par, double d){
        out.println("Mês [" + mes + "]: ");
        out.printf("\tNúmero de vezes que foi comprado: %d\n", par.getComprados());
        out.printf("\tNúmero de clientes distintos: %d\n",par.getClientes());
        out.printf("\tTotal faturado: %f\n",par.getFaturados());
        if(mes==12) {
            printTempo(d);
            waitEnter();
        }
    }

    /**
     * Códigos do(s) produto(s) que um cliente mais comprou e quantidade.
     * @param set Lista pares de produtos com a quantidade comprada de cada um.
     */
    public void query5InterativaPrint(List<ParProdQuantidade> set) {
        if(set.size()==0) {
            out.println("Não há nenhum elemento.");
            return;
        }
        List<String> lista = new ArrayList<>();
        for(ParProdQuantidade par : set){
            lista.add(par.getProduto().getCodigo() + ": " + par.getQuantidade() + "\t");
        }
        Navegador novo = new Navegador(lista,10,10);
        novo.start();
    }

    /**
     * Conjunto dos X produtos mais vendidos, com os clientes respetivos.
     * @param set Lista com pares de produto, quantidade comprada e nº de clientes que compraram o produto.
     * @param limit limite máximo de produtos.
     */
    public void query6InterativaPrint(List<ParQuery6> set,int limit){
        if(set.size()==0) {
            out.println("Não há nenhum elemento.");
            return;
        }
        List<String> lista = new ArrayList<>();
        for(int i = 0 ; i < limit; i++) {
            lista.add(set.get(i).getProduto() + ": Quantidade = " + set.get(i).getCompras() +  " Nº Clientes = " + set.get(i).getClientes().size()+ "\t");
        }
        Navegador novo = new Navegador(lista,10,4);
        novo.start();
    }

    /**
     * Três maiores compradores, em termos de dinheiro facturado, de cada filial.
     * @param list Lista com pares de clientes e dinheiro faturado numa filial.
     * @param filial filial.
     * @param numfils número total de filiais.
     * @param d tempo.
     */
    public void query7InterativaPrint(List<ParQuery7> list, int filial, int numfils, double d) {
        out.println("Filial " + (filial+1) + ':');

        for(int i = 0; i < 3 ; i++) {
            out.println('\t' + list.get(i).getCodigo() + ": " + list.get(i).getValue() + " €\n");
        }

        if(filial+1==numfils) {
            printTempo(d);
            waitEnter();
        }
    }

    /**
     * Códigos dos X clientes que compraram mais produtos diferentes.
     * @param aux Lista com pares de clientes e quantidade de produtos diferentes que compraram.
     */
    public void query8InterativaPrint(List<ParQuery8> aux) {
        if(aux.size()==0) {
            out.println("Não há nenhum elemento.");
            return;
        }
        List<String> lista = new ArrayList<>();
        for (ParQuery8 entry : aux){
            lista.add(entry.getCodigo() + ": " + entry.getValue() + "\t");
        }
        Navegador novo = new Navegador(lista,10,10);
        novo.start();
    }

    /**
     * Conjunto dos X clientes que mais compraram um produto e, para cada um, qual o valor gasto.
     * @param pares Lista com pares de clientes que compraram o produto e o valor gasto nele.
     */
    public void query9InterativaPrint(List<ParQuery9> pares) {
        if(pares.size()==0) {
            out.println("Não há nenhum elemento.");
            return;
        }
        List<String> lista = new ArrayList<>();
        for(ParQuery9 par  : pares){
            lista.add(par.getPar().getKey() + ": Comprado = " + par.getPar().getValue().getKey() + " Total: " + par.getPar().getValue().getValue() + "\t");
        }
        Navegador novo = new Navegador(lista,10,1);
        novo.start();
    }

    /**
     * Facturação total com produtos por mês e filial.
     * @param total total de faturado.
     * @param filial filial atual.
     * @param filmax número máximo de filiais.
     * @param mes mês.
     * @param d tempo.
     */
    public void query10InterativaPrint(double total, int filial,int filmax, int mes, double d) {
        if(filial==1) out.printf("Mês [%d]:\n", mes);
        out.printf("\tFilial [%d]: %f\n", filial,total);
        if(mes==12 && filial==filmax){
            printTempo(d);
            waitEnter();
        }
    }

}
