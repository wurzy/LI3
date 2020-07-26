import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.*;
public class MainTestes implements Serializable {

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

    public static List<String> WithoutParsingAL(String fichtxt){
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

    public static List<String> WithoutParsingALInit (String fichtxt){
        List<String> linhas = new ArrayList<>(1000000);
        BufferedReader inFile = null;
        String linha = null;
        try{
            inFile = new BufferedReader(new FileReader(fichtxt));
            while((linha = inFile.readLine()) != null)
              linhas.add(linha);
        } catch (IOException e) {out.println(e);}

        return linhas;
    }


    public static Set<String> WithoutParsingHS (String fichtxt){
        Set<String> linhas = new HashSet<>();
        BufferedReader inFile = null;
        String linha = null;
        try{
            inFile = new BufferedReader(new FileReader(fichtxt));
            while((linha = inFile.readLine()) != null)
              linhas.add(linha);
        } catch (IOException e) {out.println(e);}

        return linhas;
    }

    public static Set<String> WithoutParsingHSInit (String fichtxt){
        Set<String> linhas = new HashSet<>(1000000);
        BufferedReader inFile = null;
        String linha = null;
        try{
            inFile = new BufferedReader(new FileReader(fichtxt));
            while((linha = inFile.readLine()) != null)
                linhas.add(linha);
        } catch (IOException e) {out.println(e);}

        return linhas;
    }

    private static List<IVenda> WithParsingAL (String fichtxt){
        List<IVenda> linhas = new ArrayList<> (3000000);
        BufferedReader inFile = null;
        String linha = null;
        try{
            inFile = new BufferedReader (new FileReader (fichtxt));
            while ((linha=inFile.readLine()) != null)
              linhas.add(linhaToVenda(linha));
        }
        catch(IOException e) {out.println(e);}

        return linhas;
    }

    private static List<IVenda> WithParsingALInit (String fichtxt){
        List<IVenda> linhas = new ArrayList<> (1000000);
        BufferedReader inFile = null;
        String linha = null;
        try{
            inFile = new BufferedReader (new FileReader (fichtxt));
            while ((linha=inFile.readLine()) != null)
              linhas.add(linhaToVenda(linha));
        }
        catch(IOException e) {out.println(e);}

        return linhas;
    }

    private static Set<IVenda> WithParsingHS (String fichtxt){
        Set<IVenda> linhas = new HashSet<> ();
        BufferedReader inFile = null;
        String linha = null;
        try{
            inFile = new BufferedReader (new FileReader (fichtxt));
            while ((linha=inFile.readLine()) != null)
              linhas.add(linhaToVenda(linha));
        }
        catch(IOException e) {out.println(e);}

        return linhas;
    }

    private static Set<IVenda> WithParsingHSInit (String fichtxt){
        Set<IVenda> linhas = new HashSet<> (5000000);
        BufferedReader inFile = null;
        String linha = null;
        try{
            inFile = new BufferedReader (new FileReader (fichtxt));
            while ((linha=inFile.readLine()) != null)
              linhas.add(linhaToVenda(linha));
        }
        catch(IOException e) {out.println(e);}

        return linhas;
    }

    public static IVenda linhaToVenda(String linha){
        String codProd, codCli, tipo;
        int mes = 0; int quant = 0; int filial = 0;
        double preco = 0;
        String[] campos = null;

        campos = linha.split(" ");
        codProd = campos[0];
        tipo = campos[3];
        codCli = campos[4];

        try{
            preco = Double.parseDouble(campos[1]);
        }
        catch (InputMismatchException exc){ return null; }
        catch (NumberFormatException exc) { return null; }

        try{
            quant = Integer.parseInt(campos[2]);
        }
        catch (InputMismatchException exc){ return null; }
        catch (NumberFormatException exc) { return null; }

        try{
            mes = Integer.parseInt(campos[5]);
        }
        catch (InputMismatchException exc){ return null; }
        catch (NumberFormatException exc) { return null; }

        if (mes < 1 || mes > 12) return null;

        try{
            filial = Integer.parseInt(campos[6]);
        }
        catch (InputMismatchException exc){ return null; }
        catch (NumberFormatException exc) { return null; }

        if (filial < 1 || filial > 3) return null;
        return new Venda (codProd, codCli, preco, quant, tipo, mes, filial);

    }

    public static Venda validaVenda(String linha, ICatProd ctp, ICatClientes ctc){
        String[] campos=null;
        String codProd, codCli, tipo;
        int mes=0,quant=0,filial=0;
        double preco=0.0;

        campos=linha.split(" ");
        codProd=campos[0];
        if(!ctp.existeProduto(new Produto(codProd))) {
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
        if(!ctc.existeCliente(new Cliente(codCli))){
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

    public static List<String> readWithFiles(String fichtxt){
      List<String> linhas = new ArrayList<>();
      try{
          linhas = Files.readAllLines(Paths.get(fichtxt), StandardCharsets.UTF_8);
      } catch (IOException exc) {out.println(exc);}

      return linhas;
    }

//
    private static List<String> rAFWOParsing(String fichtxt){
        List<String> linhas = new ArrayList<>();
        try{
            linhas = Files.readAllLines(Paths.get(fichtxt));
        } catch (IOException exc) {out.println(exc);}
        return linhas;
    }

    private static List<IVenda> rAFParsing(String fichtxt){
        List<String> linhas = new ArrayList<>();
        try{
            linhas = Files.readAllLines(Paths.get(fichtxt));
        } catch (IOException exc) {out.println(exc);}
        return linhas.stream().map(s-> linhaToVenda(s)).collect(Collectors.toList());
    }


    private static List<IVenda> rAFValidacao(String fichtxt,ICatProd prod, ICatClientes cli){
        List<String> linhas = new ArrayList<>();
        try{
            linhas = Files.readAllLines(Paths.get(fichtxt));
        } catch (IOException exc) {out.println(exc);}
        return linhas.stream().map(s-> validaVenda(s,prod,cli)).collect(Collectors.toList());
    }

    //

    public static ICatProd createProds (String filename, ICatProd ctp){
        Crono.start();
        List<String> produtos = readLinesWithBR(filename);
        if(produtos==null) return null;
        int validos = 0,total = 0;
        ctp = new CatProd();
        for(String s:produtos) {
            if(s.matches("^[A-Z]{2}\\d{4}") && Character.getNumericValue(s.charAt(2))>0) {
                IProduto produto = new Produto(s);
                ctp.add(produto);
                validos++;
            }
            total++;
        }
        ctp.setTotal(total);
        ctp.setValidos(validos);
        return ctp;
    }

    public static ICatClientes createClientes(String filename, ICatClientes ctc) {
        Crono.start();
        List<String> clientes = readLinesWithBR(filename);
        if(clientes==null) return null;
        int validos = 0,total = 0;
        ctc = new CatClientes();
        for(String s:clientes) {
            if(s.matches("^[A-Z]\\d{4}") && Integer.parseInt(s.substring(1))>=1000 && Integer.parseInt(s.substring(1))<=5000) {
                ICliente cl = new Cliente(s);
                ctc.add(cl);
                validos++;
            }
            total++;
        }
        ctc.setTotal(total);
        ctc.setValidos(validos);
        return ctc;
    }

    private static Set<IVenda> validacaoAL (String fichtxt, ICatClientes ctc, ICatProd ctp){
        Set<IVenda> linhas = new HashSet<>();
        BufferedReader inFile = null;
        String linha = null;
        IVenda v = null;
        try{
            inFile = new BufferedReader (new FileReader (fichtxt));
            while ((linha=inFile.readLine()) != null) {
                v = validaVenda(linha,ctp,ctc);
                if(v!=null) {
                    linhas.add(validaVenda(linha,ctp,ctc));
                }
            }
        }
        catch(IOException e) {out.println(e);}

        return linhas;
    }

    /*public static void main(String[] args){

        out.println("Qual o ficheiro?");
        String input = Input.lerString();

        ICatProd ctp = new CatProd();
        ICatClientes ctc = new CatClientes();

        ctc =  createClientes("./files/Clientes.txt",ctc);
        ctp = createProds("./files/Produtos.txt",ctp);

        out.println("\nCom buffered Reader:");
        out.println("\nA ler 1M sem parsing e sem inicialização.\n");

        out.println("\nA ler para um ArrayList");
        Crono.start();
        List<String> alWoP = WithoutParsingAL(input);
        double alWoPT = Crono.stop();
        out.println("TEMPO: " + alWoPT);
        out.println("\nA ler para um HashSet");
        Crono.start();
        Set<String> hsWoP = WithoutParsingHS(input);
        double hsWoPT = Crono.stop();
        out.println("TEMPO: " + hsWoPT);


        out.println("\nA ler 1M sem parsing e com inicialização.");

        out.println("\nA ler para um ArrayList");
        Crono.start();
        List<String> alWoPI = WithoutParsingALInit(input);
        double alWoPIT = Crono.stop();
        out.println("TEMPO: " + alWoPIT);


        out.println("\nA ler para um HashSet");
        Crono.start();
        Set<String> hsWoPI = WithoutParsingHSInit(input);
        double hsWoPIT = Crono.stop();
        out.println("TEMPO: " + hsWoPIT);



        out.println("\nA ler 1M com parsing e sem inicialização.");

        out.println("\nA ler para um ArrayList");
        Crono.start();
        List<IVenda> alWP = WithParsingAL(input);
        double alWPT = Crono.stop();
        out.println("TEMPO: " + alWPT);

        out.println("\nA ler para um HashSet");
        Crono.start();
        Set<IVenda> hsWP = WithParsingHS(input);
        double hsWPT = Crono.stop();
        out.println("TEMPO: " + hsWPT);


        out.println("\nA ler 1M sem parsing e com inicialização.");

        out.println("\nA ler para um ArrayList");
        Crono.start();
        List<IVenda> alWPI = WithParsingALInit(input);
        double alWPIT = Crono.stop();
        out.println("TEMPO: " + alWPIT);


        out.println("\nA ler para um HashSet");
        Crono.start();
        Set<IVenda> hsWPI = WithParsingHSInit(input);
        double hsWPIT = Crono.stop();
        out.println("TEMPO: " + hsWPIT);

        out.println("\nA ler 1M com parsing e com verificação.\n");
        Crono.start();
        //List<IVenda> vALL = validacaoAL(input,ctc,ctp);
        double vAL = Crono.stop();
        out.println("TEMPO: " + vAL);

        out.println("\n---------------------------------------------------------------------------------------\n");
        out.println("\nCom ReadAllFiles:\n");
        Crono.start();
        List<String> rAF = readWithFiles(input);
        double rAFD = Crono.stop();
        out.println("TEMPO: " + rAFD);

        out.println("\nA ler readAllFiles sem parsing:");
        Crono.start();
        List<String> rAFWPar = rAFWOParsing(input);
        double rAFWpar = Crono.stop();
        out.println("TEMPO: " + rAFWpar);

        out.println("\nA ler readAllFiles com parsing:");
        Crono.start();
        //List<IVenda> rAFPar = rAFParsing(input);
        double rAFParD = Crono.stop();
        out.println("TEMPO: " + rAFParD);

        out.println("\nA ler readAllFiles com parsing e validação:");
        Crono.start();
        //List<IVenda> rAFVal = rAFValidacao(input,ctp,ctc);
        double rAFVald = Crono.stop();
        out.println("TEMPO: " + rAFVald);
        "./files/Vendas_1M.txt"

    }*/
    public static void main(String[] args) {
        ICatProd ctp = new CatProd();
        ICatClientes ctc = new CatClientes();

        ctc =  createClientes("./files/Clientes.txt",ctc);
        ctp = createProds("./files/Produtos.txt",ctp);

        out.println("\nA ler 1M com parsing e com verificação.\n");
        Crono.start();
        List<IVenda> vALL = rAFValidacao("./files/Vendas_5M.txt",ctp,ctc);
        double vAL = Crono.stop();
        out.println("TEMPO: " + vAL);
    }


}
