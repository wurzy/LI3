import java.util.AbstractMap.*;
import java.util.*;
import java.io.*;

/**
 * Controlador do programa.
 */
public class GereVendasController implements IGereVendasController{

    private IGereVendasModel model;
    private IGereVendasView view;

    /**
     * Cria um controlador vazio.
     */
    public GereVendasController(){
        this.model = null;
        this.view = null;
    }

    /**
     * Altera o modelo do controlador para o do argumento.
     * @param model Modelo.
     */
    public void setModel(IGereVendasModel model){
        this.model = model;
    }

    /**
     * Altera a view do controlador para a do argumento.
     * @param view View.
     */
    public void setView (IGereVendasView view){
        this.view = view;
    }

    /**
     * Executa o início do programa efetivo.
     */
    public void startController(){
        int x;
        this.view.printTempo(this.model.getTempoTotal());
        do{
            this.view.run();
            x=this.view.getOpcao();
            switch(x) {
                case 1:
                    this.lerNovo();
                    break;
                case 2:
                    Crono.start();
                    this.gravaDados();
                    double y = Crono.stop();
                    System.out.println(y);
                    break;
                case 3:
                    Crono.start();
                    this.carregaDados();
                    double d = Crono.stop();
                    System.out.println(d);
                    break;
                case 4:
                    this.manageEstatistica();
                    break;
                case 5:
                    this.manageQueries();
                    break;

            }
        }while(this.view.getOpcao()!=0);
        this.view.exitProgram();
    }

    /**
     * Inicia os menus de todas as queries estatísticas.
     */
    public void manageEstatistica(){
        int x;
        do{
        this.view.runEstatistica();
        x = this.view.getOpcao();
            switch(x){
                case 1:
                    this.query1Estatistica();
                    break;
                case 2:
                    this.manageSubEstatistica();
                    break;
            }
        }while(this.view.getOpcao()!=0);
        this.view.estatisticaEnd();
    }

    /**
     * Queries estatísticas sobre estruturas de dados.
     */
    public void manageSubEstatistica(){
        int x;
        do {
            this.view.runSubEstatistica();
            x = this.view.getOpcao();
            switch(x){
                case 1:
                    this.query2Estatistica();
                    break;
                case 2:
                    this.query3Estatistica();
                    break;
                case 3:
                    this.query4Estatistica();
                    break;
            }
        }while(this.view.getOpcao()!=0);
        this.view.subEstatisticaEnd();
    }

    /**
     * Queries interativas sobre estruturas de dados.
     */
    public void manageQueries(){
        int x;
        do {
            this.view.runQueries();
            x = this.view.getOpcao();
            switch(x){
                case 1:
                    this.query1Interativa();
                    break;
                case 2:
                    this.query2Interativa();
                    break;
                case 3:
                    this.query3Interativa();
                    break;
                case 4:
                    this.query4Interativa();
                    break;
                case 5:
                    this.query5Interativa();
                    break;
                case 6:
                    this.query6Interativa();
                    break;
                case 7:
                    this.query7Interativa();
                    break;
                case 8:
                    this.query8Interativa();
                    break;
                case 9:
                    this.query9Interativa();
                    break;
                case 10:
                    this.query10Interativa();
                    break;
            }
        }while(this.view.getOpcao()!=0);
        this.view.queriesEnd();
    }

    /**
     * Query acerca do ficheiro de vendas lido.
     */
    public void query1Estatistica(){
        Crono.start();
        QueryEstatisticaAux ola = this.model.getDadosGerais();
        double d = Crono.stop();
        this.view.query1EstatisticaPrint(ola);
        System.out.println(d);
    }

    /**
     * Query acerca do número de compras por mês.
     */
    public void query2Estatistica(){
        double ds=0;
        int meses = model.getMeses();
        int d = 0;
        for(int i = 1; i <= meses; i++) {
            Crono.start();
            d = model.getNumCompras(i);
            ds+=Crono.stop();
            this.view.query2EstatisticaPrint(i,d);
        }
        System.out.println(ds);
    }

    /**
     * Query acercado do total de faturação num mês por filial.
     */
    public void query3Estatistica(){
        int meses = model.getMeses();
        int filiais  = model.getFiliais();
        List<SimpleEntry<Integer,Double>> sender;
        double d = 0;
        double fat, total = 0;
        for(int i = 1; i <= meses; i++) {
            sender = new ArrayList<>();
            for(int j = 1; j <= filiais; j++) {
                Crono.start();
                fat = this.model.getFaturacao(i,j);
                d+=Crono.stop();
                total+=fat;
                SimpleEntry<Integer,Double> x = new SimpleEntry<>(j,fat);
                sender.add(x);
            }
            this.view.query3EstatisticaPrint(sender,i,total);
            total = 0;
        }
        System.out.println(Crono.stop());
    }

    /**
     * Query acerca do número distinto de clientes que compraram por mês.
     */
    public void query4Estatistica(){
        int filiais = model.getFiliais();
        int meses = model.getMeses();
        List<SimpleEntry<Integer,Integer>> sender;
        int clientes;
        double d = 0;
        for(int i = 1; i <= meses; i++){
            sender = new ArrayList<>();
            for(int j = 1; j<=filiais; j++){
                Crono.start();
                clientes = this.model.getClientesCompraram(i,j);
                d+=Crono.stop();
                SimpleEntry<Integer,Integer> entrada = new SimpleEntry<>(j,clientes);
                sender.add(entrada);
            }
            this.view.query4EstatisticaPrint(sender,i);
        }
        System.out.println(d);
    }

    /**
     * Códigos dos produtos nunca comprados e o respectivo total, por ordem alfabética
     */
    public void query1Interativa(){
        Crono.start();
        Set<IProduto> ret = this.model.getSetProdsNuncaComprados();
        double d = Crono.stop();
        int lin,col;
        do {
            this.view.askLinhas();
            lin = Input.lerInt();
        }while(lin!=10 && lin!=15 && lin!=20);
        do {
            this.view.askColunas();
            col = Input.lerInt();
        }while(col!=10 && col!=15 && col!=20);
        this.view.query1InterativaPrint(ret,lin,col);
        this.view.printTempo(d);
        this.view.waitEnter();
    }

    /**
     * Número total de vendas realizadas num mês e o número total de clientes distintos que as fizeram
     */
    public void query2Interativa(){
        int mes;
        do{
            this.view.askMes();
            mes = Input.lerInt();
        }while(mes<0 || mes > 12);
        int fil = this.model.getFiliais();
        Crono.start();
        ParInterativa2 par = this.model.getNumVendas(mes);
        double d = Crono.stop();
        this.view.query2InterativaPrint(fil,par);
        this.view.printTempo(d);
        this.view.waitEnter();
    }

    /**
     * Para cada mês, quantas compras, quantos produtos distintos comprou e quanto gastou no total um cliente
     */
    public void query3Interativa(){
        this.view.askCliente();
        String s = Input.lerString();
        int meses = this.model.getMeses();
        ParInterativa3 par;
        ICliente cl = new Cliente(s);
        if(!this.model.existeCliente(cl)) {
            this.view.printError("Cliente inexistente.");
            return;
        }
        double d = 0;
        for(int i = 1; i <= meses; i++) {
            Crono.start();
            par =this.model.getComprasMes(cl);
            if(par == null) break;
            d+=Crono.stop();
            par.generate(i);
            this.view.query3InterativaPrint(par,i,d);
        }
    }

    /**
     * Quantas vezes foi comprado um produto em cada mês, por quantos clientes diferentes e o total facturado
     */
    public void query4Interativa(){
        this.view.askProduto();
        String s = Input.lerString();
        IProduto p = new Produto(s);
        if(!this.model.existeProduto(p)) {
            this.view.printError("Produto não existe.");
            return;
        }
        int meses = this.model.getMeses();
        ParInterativa4 par;
        double d = 0;
        for(int i = 1; i <= meses; i++) {
            Crono.start();
            par = this.model.getProdutoComprasMes(p,i);
            if(par == null) break;
            d+=Crono.stop();
            this.view.query4InterativaPrint(i,par,d);
        }
    }

    /**
     * Códigos do(s) produto(s) que um cliente mais comprou e quantidade.
     */
    public void query5Interativa(){
        this.view.askCliente();
        String s = Input.lerString();
        ICliente cl = new Cliente(s);
        if(!this.model.existeCliente(cl)) {
            this.view.printError("Cliente inexistente.");
            return;
        }
        Crono.start();
        List<ParProdQuantidade> set = this.model.getProdutosQuantidade(cl);
        double d = Crono.stop();
        this.view.query5InterativaPrint(set);
        this.view.printTempo(d);
        this.view.waitEnter();
    }

    /**
     * Conjunto dos X produtos mais vendidos, com os clientes respetivos.
     */
    public void query6Interativa(){
        this.view.askNumProds();
        int i = Input.lerInt();
        Crono.start();
        List<ParQuery6> pares = this.model.getProdutosMaisComprados(i);
        double d = Crono.stop();
        this.view.query6InterativaPrint(pares,i);
        this.view.printTempo(d);
        this.view.waitEnter();
    }

    /**
     * Três maiores compradores, em termos de dinheiro facturado, de cada filial.
     */
    public void query7Interativa(){
        int filiais = this.model.getFiliais();
        double d = 0;
        for(int i = 0; i<filiais ; i++){
            Crono.start();
            List<ParQuery7> list = this.model.getClientesMaisCompradores(i);
            d+=Crono.stop();
            this.view.query7InterativaPrint(list,i,filiais,d);
        }
    }

    /**
     * Códigos dos X clientes que compraram mais produtos diferentes.
     */
    public void query8Interativa(){
        this.view.askNumClientes();
        int numCli = Input.lerInt();
        Crono.start();
        List<ParQuery8> aux = this.model.getMaisProdsDif(numCli);
        double d = Crono.stop();
        this.view.query8InterativaPrint(aux);
        this.view.printTempo(d);
        this.view.waitEnter();
    }

    /**
     * Conjunto dos X clientes que mais compraram um produto e, para cada um, qual o valor gasto.
     */
    public void query9Interativa(){
        this.view.askProduto();
        String prodS = Input.lerString();
        IProduto prod = new Produto(prodS);
        if(!this.model.existeProduto(prod)) {
            this.view.printError("Produto inexistente.");
            return;
        }
        this.view.askNumClientes();
        int x = Input.lerInt();
        Crono.start();
        List<ParQuery9> list = this.model.getClientePorProduto(prod,x);
        double d = Crono.stop();
        this.view.query9InterativaPrint(list);
        this.view.printTempo(d);
        this.view.waitEnter();
    }

    /**
     * Facturação total com um dado produto.
     */
    public void query10Interativa(){
        //this.view.askProduto();
        String prodS;
        IProduto prod;
        int meses = this.model.getMeses();
        int filiais = this.model.getFiliais();
        double total;
        double d = 0;
        do{
            this.view.print("Insira um código de produto ou 0 para saír: ");
            prodS = Input.lerString();
            prod = new Produto(prodS);
            if(prodS.equals("0")) break;
            if(!this.model.existeProduto(prod)) {
                this.view.printError("Produto não existente.");
            }
            else {
                for(int i = 1; i <= meses; i++) {
                    for(int j = 1 ; j <= filiais; j++) {
                        Crono.start();
                        total = this.model.getFaturado(prod,i,j);
                        d+=Crono.stop();
                        this.view.query10InterativaPrint(total,j,filiais,i,d);
                    }
                }
            }
        }while(true);
    }

    /**
     * Guarda o modelo em um ficheiro binário.
     * @throws IOException
     */
    private void guardaModel() throws IOException{
        FileOutputStream fos = new FileOutputStream("gestVendas.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.model);
        oos.flush();
        oos.close();
    }

    /**
     * Lê o ficheiro de vendas dum ficheiro binário
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void carregaModel() throws IOException,ClassNotFoundException {
        FileInputStream fis = new FileInputStream("gestVendas.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        this.model = (GereVendasModel) ois.readObject();
        ois.close();
    }

    /**
     * Tenta ler dados do ficheiro binário, se não conseguir avisa.
     */
    private void carregaDados(){
        try{
            carregaModel();
        }
        catch(IOException | ClassNotFoundException e) {
            this.view.printError("Não é possível carregar dados, tente guardar primeiro.");
        }
    }

    /**
     * Grava dados em ficheiro binário, se não conseguir avisa.
     */
    private void gravaDados(){
        try{
            guardaModel();
        }
        catch(IOException e) {
            this.view.printError(e.getMessage());
        }
    }

    /**
     * Lê novo ficheiro de vendas.
     */
    private void lerNovo(){
        List<String> filesPossiveis = this.model.ficheirosPossiveis();
        int x;
        do{
            this.view.runNovo();
            x=this.view.getOpcao();
            switch(x) {
                case 1:
                    this.model.createStructs(filesPossiveis.get(0));
                    break;
                case 2:
                    this.model.createStructs(filesPossiveis.get(1));
                    break;
                case 3:
                    this.model.createStructs(filesPossiveis.get(2));
                    break;
            }
            this.view.printTempo(this.model.getTempoVendas());
        }while(this.view.getOpcao()!=0);
        this.view.leituraEnd();
    }
}
