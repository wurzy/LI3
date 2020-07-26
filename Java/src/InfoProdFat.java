import java.io.Serializable;
import java.util.*;
import java.util.AbstractMap.*;

public class InfoProdFat implements Serializable {

    private Map<Integer,List<SimpleEntry<Integer,Double>>> compras; //filial -> lista de pares (quantidade,preco)

    /**
     * Construtor por defeito da classe.
     */
    public InfoProdFat(){
        this.compras = new HashMap<>();
    }

    /**
     * Construtor parametrizado.
     */
    public InfoProdFat(InfoProdFat p) {
        this.compras = p.getCompras();
    }

    /**
     * Função que retorna a variável de instância "compras".
     */
    public Map<Integer,List<SimpleEntry<Integer,Double>>> getCompras() {
        Map<Integer,List<SimpleEntry<Integer,Double>>> mapa = new HashMap<>();
        for(Map.Entry<Integer,List<SimpleEntry<Integer,Double>>> k : this.compras.entrySet()) {
            List<SimpleEntry<Integer,Double>> list = new ArrayList<>();
            for(SimpleEntry<Integer,Double> x: k.getValue()) {
                SimpleEntry<Integer,Double> nova = new SimpleEntry<>(x.getKey(),x.getValue());
                list.add(nova);
            }
            mapa.put(k.getKey(),list);
        }

        return mapa;
    }
    /**
     * Função que devolve uma nova InfoProdFat(clonada).
     */
    public InfoProdFat clone(){
        return new InfoProdFat(this);
    }

    /**
     * Função que testa se duas InfoProdFat são iguais.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoProdFat infoProdFat = (InfoProdFat) o;
        return (this.compras.equals(infoProdFat.getCompras()));
    }

    /**
     * Função que adiciona uma compra.
     * @param filial Filial em que a compra foi efetuada.
     * @param preco Preco que o produto comprado costou.
     * @param quantidade Quantidade de compra do produto em questão.
     */
    public void addCompra(int filial, double preco, int quantidade) {
        if(this.compras.containsKey(filial)){
            this.compras.get(filial).add(new SimpleEntry<>(quantidade,preco));
        }
        else {
            List<SimpleEntry<Integer,Double>> list = new ArrayList<>();
            list.add(new SimpleEntry<>(quantidade,preco));
            this.compras.put(filial,list);
        }
    }

    /**
     * Função que devolve o total faturado numa certa filial.
     * @param filial Filial em questão.
     */
    public double getFaturado(int filial){
        double res = 0;
        if(this.compras.containsKey(filial)) {
            for(SimpleEntry<Integer,Double> x: this.compras.get(filial)) {
                res += x.getKey()*x.getValue();
            }
        }
        return res;
    }

    /**
     * Função que devolve o total faturado.
     */
    public double getFaturado() {
        double res = 0;
        for(List<SimpleEntry<Integer,Double>> x : this.compras.values()) {
            for(SimpleEntry<Integer,Double> s: x) {
                res += s.getKey()*s.getValue();
            }
        }
        return res;
    }

    /**
     * Função que devolve o total de compras efetuadas.
     */
    public int getNumCompras(){
        int i =0;
        for(List<SimpleEntry<Integer,Double>> lista : this.compras.values()) {
            i += lista.size();
        }
        return i;
    }

}
