import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.AbstractMap.*;

import static java.lang.Integer.parseInt;

import static java.lang.System.out;

/**
 * Modelo do programa.
 */
public class GereVendasModel implements Serializable,IGereVendasModel {
    private int    NUM_FIL;
    private int    NUM_MESES;
    private int    TOTAL_READ;
    private int    TOTAL_VALID;
    private String FILE_FOLDER;
    private String FILE_CLIE;
    private String FILE_PROD;
    private String FILE_VEND;
    private double TEMPO_PRODS;
    private double TEMPO_CLIE;
    private double TEMPO_VENDAS;
    private static final String FILE_GLOB = "./files/configs.txt";

    private List<IFilial> filial;
    private IFaturacao faturacao;
    private ICatClientes ctc;
    private ICatProd ctp;

    /**
     * Constutor por defeito do model.
     */
    public GereVendasModel(){
        this.NUM_FIL = 0;
        this.NUM_MESES = 0;
        this.FILE_FOLDER = "N/A";
        this.FILE_CLIE = "N/A";
        this.FILE_PROD = "N/A";
        this.FILE_VEND = "N/A";
        this.ctp = null;
        this.ctc = null;
        this.filial = null;
        this.faturacao = null;
    }
    /**
     * Função que lê do ficheiro para uma List de Strings.
     * @param fichtxt ficheiro onde se encontra o conteúdo para ser lido.
     */
    public static List<String> readLinesWithBR(String fichtxt){
        List<String> linhas = new ArrayList<>();
        BufferedReader inFile = null;
        String linha = null;
        try{
            inFile = new BufferedReader(new FileReader(fichtxt));
            while((linha = inFile.readLine()) != null)
                linhas.add(linha);
        } catch (IOException e) {out.println(e);}
        return linhas;
    }
    /**
     * Função que transforma uma linha de texto numa venda.
     * @param linha linha com a informação necessária para criar uma venda.
     */
    public Venda linhaToVenda(String linha){
        String[] campos=null;
        String codProd, codCli, tipo;
        int mes=0,quant=0,filial=0;
        double preco=0.0;

        campos=linha.split(" ");
        codProd=campos[0];
        if(!this.ctp.existeProduto(new Produto(codProd))) {
            return null;
        }
        try{
            preco=Double.parseDouble(campos[1]);
        }
        catch(InputMismatchException | NumberFormatException exc){
            return null;
        }
        try{
            quant=Integer.parseInt(campos[2]);
        }
        catch(InputMismatchException | NumberFormatException exc){
            return null;
        }
        tipo=campos[3];
        if(!(tipo.equals("N") || tipo.equals("P"))) {
            return null;
        }
        codCli=campos[4];
        if(!this.ctc.existeCliente(new Cliente(codCli))){
            return null;
        }
        try{
            mes=Integer.parseInt(campos[5]);
        }
        catch(InputMismatchException | NumberFormatException exc){
            return null;
        }
        try{
            filial=Integer.parseInt(campos[6]);
        }
        catch(InputMismatchException | NumberFormatException exc ){
            return null;
        }
        return new Venda(codProd, codCli ,preco, quant, tipo,mes,filial);
    }

    /**
     * Função que verifica se o alguma das variáveis de instância do model são null.
     */
    public boolean isNull(){
        return (this.ctc==null||this.filial==null||this.faturacao==null||this.ctp==null);
    }
    /**
     * Função que cria todas as constantes do programa.
     * @param filename Ficheiro de texto onde se encontram as constantes.
     */
    public void createGlobal(String filename) {
        List<String> vars = readLinesWithBR(filename);
        String[] helper;
        if(vars==null) return;
        for(String s:vars) {
            helper = s.split("=");
            switch(helper[0]){
                case "NUM_FILIAIS":
                    this.NUM_FIL = parseInt(helper[1]);
                    break;
                case "NUM_MESES":
                    this.NUM_MESES = parseInt(helper[1]);
                    break;
                case "FILE_FOLDER":
                    this.FILE_FOLDER= helper[1];
                    break;
                case "CLIENTES_FILE":
                    this.FILE_CLIE = this.FILE_FOLDER + helper[1];
                    break;
                case "PRODUTOS_FILE":
                    this.FILE_PROD = this.FILE_FOLDER + helper[1];
                    break;
                case "VENDAS_FILE":
                    this.FILE_VEND = this.FILE_FOLDER+ helper[1];
                    break;
            }
        }
    }

    /**
     * Função que cria o catálogo de produtos.
     * @param filename Ficheiro de texto onde se encontram os produtos a ser lidos.
     */
    public void createProds (String filename){
        Crono.start();
        List<String> produtos = readLinesWithBR(filename);
        if(produtos==null) return;
        int validos = 0,total = 0;
        this.ctp = new CatProd();
        for(String s:produtos) {
            if(s.matches("^[A-Z]{2}\\d{4}") && Character.getNumericValue(s.charAt(2))>0) {
                IProduto produto = new Produto(s);
                this.ctp.add(produto);
                validos++;
            }
            total++;
        }
        this.ctp.setTotal(total);
        this.ctp.setValidos(validos);
        this.TEMPO_PRODS = Crono.stop();
        out.println(TEMPO_PRODS);
    }
    /**
     * Função que cria o catálogo de clientes.
     * @param filename Ficheiro de texto onde se encontram os clientes a ser lidos.
     */
    public void createClientes(String filename) {
        Crono.start();
        List<String> clientes = readLinesWithBR(filename);
        if(clientes==null) return;
        int validos = 0,total = 0;
        this.ctc = new CatClientes();
        for(String s:clientes) {
            if(s.matches("^[A-Z]\\d{4}") && Integer.parseInt(s.substring(1))>=1000 && Integer.parseInt(s.substring(1))<=5000) {
                ICliente cl = new Cliente(s);
                this.ctc.add(cl);
                validos++;
            }
            total++;
        }
        this.ctc.setTotal(total);
        this.ctc.setValidos(validos);
        this.TEMPO_CLIE = Crono.stop();
        out.println(TEMPO_CLIE);
    }
    /**
     * Função que cria a filial e a faturação.
     * @param filename Ficheiro de texto onde se encontram as vendas.
     */
    public void createStructs(String filename) {
        Crono.start();
        List<String> vendas = readLinesWithBR(filename);
        if(vendas==null) return;
        int validos = 0, total = 0;

        this.faturacao = new Faturacao(this.NUM_MESES);
        this.filial = new ArrayList<>(this.NUM_FIL);
        for(int i = 0; i<this.NUM_FIL; i++){
            this.filial.add(new Filial());
        }
        for(String s: vendas) {
            Venda venda = linhaToVenda(s);
            if(venda!=null){
                this.faturacao.addToFaturacao(venda.getFilial(),venda.getTipo(),new Produto(venda.getCodProd()),venda.getPrecoUnit(),venda.getQuantidade(),venda.getMes());
                // this.filial.addToFilial(this.NUM_MESES,this.NUM_FIL,venda.getTipo(),new Produto(venda.getCodProd()),new Cliente(venda.getCodClie()),venda.getPrecoUnit(),venda.getQuantidade(),venda.getMes());
                this.filial.get(venda.getFilial()-1).addToFilial(venda);
                validos++;
            }
            total++;
        }
        this.FILE_VEND = filename;
        this.TOTAL_VALID = validos;
        this.TOTAL_READ = total;
        this.TEMPO_VENDAS = Crono.stop();
    }
    /**
     * Função que gera todas as variáveis de instância do model.
     */
    public void createData(){
        this.createGlobal(FILE_GLOB);
        this.createProds(this.FILE_PROD);
        this.createClientes(this.FILE_CLIE);
        this.createStructs(this.FILE_VEND);
    }
    /**
     * Função que retorna o número de meses.
     */
    public int getMeses(){
        return this.NUM_MESES;
    }
    /**
     * Função que retorna o número de filias.
     */
    public int getFiliais(){
        return this.NUM_FIL;
    }
    /**
     * Função que verifica se existe produto.
     */
    public boolean existeProduto(IProduto p){
        return this.ctp.existeProduto(p);
    }
    /**
     * Função que verifica se existe cliente.
     */
    public boolean existeCliente(ICliente c) {
        return this.ctc.existeCliente(c);
    }
    /**
     * Função que retorna a faturação de uma dado mês de um produto.
     * @param mes O mês no qual quer ver a faturação.
     * @param prod Produto em questão, do qual queremos obter a faturação.
     */
    public double getFaturacao(int mes, String prod) {
        return this.faturacao.getTotalFaturado(mes,new Produto(prod));
    }
    /**
     * Função que retorna o total faturado.
     */
    public double getFaturacao(){
        return this.faturacao.getTotalFaturado();
    }
    /**
     * Função que retorna o total de compras que um cliente fez numa filial num dado mês.
     * @param mes O mês no qual quer ver a faturação.
     * @param fil Filial na qual quer ver as compras.
     * @param cl Cliente em questão.
     */
    public int getCompras(int mes,int fil, ICliente cl) {
        return this.filial.get(fil-1).getCompras(cl,mes);
    }
    /**
     * Função que retorna o total de compras que um cliente num dado mês.
     * @param mes O mês no qual quer ver a faturação.
     * @param cl Cliente em questão.
     */
    public int getCompras(int mes, ICliente cl){
        int res = 0;
        for(IFilial f: this.filial){
            res+=f.getCompras(cl,mes);
        }
        return res;
    }
    /**
     * Função que retorna o total faturado num determinado mês numa filial.
     * @param mes O mês no qual quer ver a faturação.
     * @param filial Filial em questão
     */
    public double getFaturacao(int mes, int filial) {
        return this.faturacao.getTotalFaturado(mes,filial);
    }
    /**
     * Função que retorna o último ficheiro lido.
     */
    public String getLastFileRead(){
        return this.FILE_VEND;
    }
    /**
     * Função que retorna o número de vendas válidas.
     */
    public int getVendasValidas(){
        return this.TOTAL_VALID;
    }
    /**
     * Função que retorna o número de vendas lidas.
     */
    public int getVendasLidos(){
        return this.TOTAL_READ;
    }
    /**
     * Função que retorna o número de vendas inválidas.
     */
    public int getVendasInvalidas(){
        return this.TOTAL_READ-this.TOTAL_VALID;
    }
    /**
     * Função que retorna o número de clientes.
     */
    public int getClientes(){
        return this.ctc.getTotal();
    }
    /**
     * Função que retorna o número de clientes inválidos.
     */
    public int getClientesInvalidos(){
        return this.ctc.getTotal()-this.ctc.getValidos();
    }
    /**
     * Função que retorna o número de clientes que realizaram compras.
     */
    public int getClientesCompraram(){
        Set<ICliente> novo = new TreeSet<>(new ComparatorCatClientes());
        for(IFilial f : this.filial){
            for(ICliente p : f.getClientes()) {
                novo.add(p.clone());
            }
        }
        return novo.size();
    }
    /**
     * Função que retorna o número de clientes que realizaram compras num certo mês numa filial em questão.
     * @param mes Mês no qual queremos verificar se os clientes realizaram compras.
     * @param filial Filial em questão.
     */
    public int getClientesCompraram(int mes, int filial) {
        Set<ICliente> novo = new TreeSet<>(new ComparatorCatClientes());
        IFilial f = this.filial.get(filial-1).clone();
        for(ICliente p : f.getClientes(mes)) {
            novo.add(p.clone());
        }
        return novo.size();
    }
    /**
     * Função que retorna o número vendas que estão a zero.
     */
    public int getVendasZero(){
        return this.filial.stream()
                .mapToInt(IFilial::getVendasZero)
                .sum();
    }
    /**
     * Função que retorna o número de produtos comprados.
     */
    public int getProdutosComprados(){
        Set<IProduto> novo = new TreeSet<>(new ComparatorCatProd());
        for(IFilial f : this.filial){
            for(IProduto p : f.getProdutos()) {
                novo.add(p.clone());
            }
        }
        return novo.size();
    }
    /**
     * Função que retorna os clientes que realizaram compras.
     */
    public Set<ICliente> getSetClientesComprados(){
        Set<ICliente> novo = new TreeSet<>(new ComparatorCatClientes());
        for(IFilial f : this.filial){
            for(ICliente p : f.getClientes()) {
                novo.add(p.clone());
            }
        }
        return novo;
    }
    /**
     * Função que retorna os produtos que foram comprados.
     */
    public Set<IProduto> getSetProdutosComprados(){
        Set<IProduto> novo = new TreeSet<>(new ComparatorCatProd());
        for(IFilial f : this.filial){
            for(IProduto p : f.getProdutos()) {
                novo.add(p.clone());
            }
        }
        return novo;
    }
    /**
     * Função que retorna os produtos que nunca foram comprados.
     */
    public Set<IProduto> getSetProdsNuncaComprados(){
        return this.ctp.nuncaComprados(this.getSetProdutosComprados());
    }

    /**
     * Função que retorna o número total de produtos.
     */
    public int getProdutosTotal(){
        return this.ctp.getTotal();
    }
    /**
     * Função que retorna o número total de produtos válidos.
     */
    public int getProdutosValidos(){
        return this.ctp.getValidos();
    }

    /**
     * Retorna número de compras num mês.
     * @param mes mês
     * @return Número de compras num mês.
     */
    public int getNumCompras(int mes) {
        return this.faturacao.getNumCompras(mes);
    }

    /**
     * Função que retorna o número total de clientes distintos e o número total de vendas para cada filial.
     * @param mes Mês no qual queremos ver a quantidade de vendas.
     */
    public ParInterativa2 getNumVendas(int mes){
        ParInterativa2 nova = new ParInterativa2(this.NUM_FIL);
        for(int i = 1; i <= this.NUM_FIL; i++){
            this.filial.get(i-1).atualizaPar(mes,this.NUM_FIL,i,nova);
        }
        return nova;
    }
    /**
     * Função que retorna o número de compras, os produtos comprados, e o total gasto.
     * @param cl Cliente em questão
     */
    public ParInterativa3 getComprasMes(ICliente cl) {
        ParInterativa3 par = new ParInterativa3(cl);
        if(this.ctc.existeCliente(cl)) {
            for(IFilial f : this.filial){
                for(ClientesFilial fl : f.getVendas(cl)) {
                    par.addVenda(fl);
                }
            }
            return par;
        }
        return null;
    }
    /**
     * Função que retorna mês a mês, quantas vezes foi comprado um produto, por quantos clientes diferentes e o total facturado.
     * @param p Produto do qual queremos saber as informações.
     * @param mes Mês em questão.
     */
    public ParInterativa4 getProdutoComprasMes(IProduto p, int mes) {
        ParInterativa4 par = new ParInterativa4();
        if(this.ctp.existeProduto(p)){
            for(IFilial f : this.filial) {
                f.atualizaPar(par,p,mes);
            }
            return par;
        }
        return null;
    }
    /**
     * Função que retorna uma lista que contém pares produto quantidade.
     * @param cliente Cliente em questão.
     */
    public List<ParProdQuantidade> getProdutosQuantidade(ICliente cliente) {
        List<ClientesFilial> l;
        Map<String, ParProdQuantidade> mapa = new HashMap<>();
        for(IFilial f: this.filial) {
            l = f.getVendas(cliente);
            for(ClientesFilial fi : l) {
                if(mapa.containsKey(fi.getCodProd())) {
                    mapa.get(fi.getCodProd()).addQuantidade(fi.getQuantidade());
                }
                else {
                    mapa.put(fi.getCodProd(),new ParProdQuantidade(new Produto(fi.getCodProd()),fi.getQuantidade()));
                }
            }
        }
        return mapa.values().stream()
                .sorted(new ComparatorQuery5())
                .map(ParProdQuantidade::clone).collect(Collectors.toList());
    }
    /**
     * Função que retorna os X produtos mais comprados assim como os clientes que os compraram.
     * @param limit Número de produtos que quer.
     */
    public List<ParQuery6> getProdutosMaisComprados(int limit){
        Map<IProduto, ParQuery6> ret = new HashMap<>();
        for(IFilial f: this.filial) {
            Map<IProduto,ParQuery6> comprados = f.mapComprados();
            for(ParQuery6 par : comprados.values()) {
                if(ret.containsKey(par.getProduto())){
                    ret.get(par.getProduto()).atualizaCompras(par.getCompras());
                }
                else {
                    ret.put(par.getProduto(),par.clone());
                }
            }
        }
        return ret.values().stream()
                .sorted(new ComparatorQuery6())
                .map(ParQuery6::clone)
                .limit(limit)
                .collect(Collectors.toList());
    }
    /**
     * Função que determina, para cada filial, a lista dos três maiores compradores em termos de dinheiro facturado
     * @param filial Filial em questão.
     */
    public List<ParQuery7> getClientesMaisCompradores(int filial){
        Map<ICliente, List<ClientesFilial>> fil = this.filial.get(filial).getFilial();
        double gastos;
        List<ParQuery7> list = new ArrayList<>();
        for(Map.Entry<ICliente, List<ClientesFilial>> entry : fil.entrySet()){
            gastos = entry.getValue().stream().mapToDouble(x->x.getValorCompra()).sum();
            list.add(new ParQuery7(entry.getKey(), gastos));
        }
        list.sort(new ComparatorClientesMaisCompradores());

        return list;

    }
    /**
     * Função que retorna determina os códigos dos X clientes que mais produtos diferentes.
     * @param max Corresponde ao máximo(X) de clientes.
     */
    public List<ParQuery8> getMaisProdsDif(int max){
        Map<ICliente, List<IProduto>> aux = new HashMap<>();
        List<ParQuery8> cli = new ArrayList<>();
        for(IFilial filial : this.filial){
            for(Map.Entry<ICliente,List<ClientesFilial>> client : filial.getFilial().entrySet()){
                List<IProduto> aux1 = new ArrayList<>();
                for (ClientesFilial f : client.getValue()){
                    if(!aux1.contains(f.getProdutoBought())){
                        aux1.add(f.getProdutoBought());
                    }
                }
                if(aux.containsKey(client.getKey())){
                    aux1.stream().filter(x->!aux.get(client.getKey()).contains(x)).forEach(x->aux.get(client.getKey()).add(x));
                }
                else aux.put(client.getKey(), aux1);
            }
        }
        aux.entrySet().stream().forEach(x->cli.add(new ParQuery8(x.getKey(),x.getValue().size())));
        return cli.stream().sorted(new ComparatorMaisProds()).limit(max).collect(Collectors.toList());
    }

    /**
     * Função que recebendo um produto determina o conjunto dos X clientes que mais o compraram e, para cada um, qual o valor gasto.
     * @param prod Produto em questão.
     * @param max Corresponde ao máximo(X) de clientes.
     */
    public List<ParQuery9> getClientePorProduto (IProduto prod, int max){
        Map<ICliente, SimpleEntry<Integer,Double>> map = new HashMap<>();
        for(IFilial i : this.filial){
            for(Map.Entry<ICliente,List<ClientesFilial>> entry : i.getFilial().entrySet()){
                List<ClientesFilial> aux = entry.getValue().stream().filter(x->x.getProdutoBought().equals(prod)).collect(Collectors.toList());
                int quant = aux.stream().mapToInt(ClientesFilial::getQuantidade).sum();
                double gasto = aux.stream().mapToDouble(ClientesFilial::getValorCompra).sum();
                SimpleEntry<Integer,Double> se;
                if(map.containsKey(entry.getKey())){
                    se = new SimpleEntry<>(map.get(entry.getKey()).getKey()+quant , map.get(entry.getKey()).getValue()+gasto);
                }
                else se = new SimpleEntry<>(quant,gasto);
                map.put(entry.getKey(), se);
            }
        }

        List<ParQuery9> paraRet = new ArrayList<>();
        for(Map.Entry<ICliente, SimpleEntry<Integer,Double>> entry : map.entrySet()){
            SimpleEntry<Integer,Double> nseAux = new SimpleEntry<>(entry.getValue().getKey(),entry.getValue().getValue());
            ParQuery9 nse = new ParQuery9(entry.getKey(),nseAux);
            paraRet.add(nse);
        }
        paraRet.sort(new ComparatorQuery9());
        return paraRet.stream().limit(max).collect(Collectors.toList());
    }
    /**
     * Função que determina o total faturado por um produto numa filial num determinado mês.
     * @param p Produto em questão.
     * @param mes Mês em que queremos o total faturado.
     * @param filial Filial em questão
     */
    public double getFaturado(IProduto p, int mes, int filial) {
        return this.faturacao.getTotalFaturado(mes,filial,p);
    }
    /**
     * Função que devolve todos os ficheiros de vendas que existem.
     */
    public List<String> ficheirosPossiveis(){
        List<String> ret = new ArrayList<>();
        ret.add(this.FILE_FOLDER + "Vendas_1M.txt");
        ret.add(this.FILE_FOLDER + "Vendas_3M.txt");
        ret.add(this.FILE_FOLDER + "Vendas_5M.txt");
        return ret;
    }
    /**
     * Função que devolve informações para as queries estatísticas.
     */
    public QueryEstatisticaAux getDadosGerais(){
        String file = getLastFileRead();
        int vlidas = getVendasLidos();
        int vvalidas = getVendasValidas();
        int vzero = getVendasZero();
        int clientes = getClientes();
        int clientesC = getClientesCompraram();
        int clientesI = getClientesInvalidos();
        int produtosT = getProdutosTotal();
        int produtosV = getProdutosValidos();
        int produtosC = getProdutosComprados();
        double faturacaoTotal = getFaturacao();
        return new QueryEstatisticaAux(file,vlidas,vvalidas,vzero,clientes,clientesI,clientesC,produtosT,produtosV,produtosC,faturacaoTotal);
    }
    /**
     * Função que devolve o tempo total de carregamento dos ficheiros para o programa.
     */
    public double getTempoTotal(){
        return this.TEMPO_CLIE + this.TEMPO_VENDAS + this.TEMPO_PRODS;
    }
    /**
     * Função que devolve o tempo de carregamento das vendas.
     */
    public double getTempoVendas(){
        return this.TEMPO_VENDAS;
    }
}
