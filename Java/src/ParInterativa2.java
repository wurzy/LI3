import java.io.Serializable;
import java.util.AbstractMap.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Par com o número total de vendas realizadas e o número total de clientes distintos para cada uma das filiais.
 */
public class ParInterativa2 implements Serializable {
    private List<SimpleEntry<Integer,Integer>> lista; // filial -> pares (clientes,vendas)
    private List<Set<ICliente>> clienteSet;

    /**
     * Construtor parametrizado do Par.
     * @param filiais número máximo de filiais.
     */
    public ParInterativa2(int filiais){
        this.lista = new ArrayList<>(filiais);
        for(int i = 0; i < filiais; i++) {
            this.lista.add(new SimpleEntry<>(0,0));
        }
        this.clienteSet = new ArrayList<>(filiais);
        for(int i = 0; i < filiais; i++) {
            this.clienteSet.add(new TreeSet<>(new ComparatorCatClientes()));
        }
    }

    /**
     * Adiciona uma venda ao par.
     * @param cl cliente em questão.
     * @param filial filial em questão.
     */
    public void addVenda(ICliente cl, int filial) {
        SimpleEntry<Integer,Integer> entry = this.lista.get(filial-1);
        if(this.clienteSet.get(filial-1).contains(cl)){
            this.lista.set(filial-1,new SimpleEntry<>(entry.getKey(),entry.getValue()+1));
        }
        else {
            this.lista.set(filial-1,new SimpleEntry<>(entry.getKey()+1,entry.getValue()+1));
        }
        this.clienteSet.get(filial-1).add(cl.clone());
    }

    /**
     * Retorna o par para uma filial.
     * @param filial filial em questão.
     * @return o par para uma filial.
     */
    public SimpleEntry<Integer,Integer> getPar(int filial) {
        return this.lista.get(filial-1);
    }

    /**
     * Retorna o número de clientes.
     * @return o número de clientes.
     */
    public int getNumClientes(){
        Set<ICliente> novo = new TreeSet<>(new ComparatorCatClientes());
        for(Set<ICliente> set: this.clienteSet) {
            for(ICliente cl : set) {
                novo.add(cl.clone());
            }
        }
        return novo.size();
    }

    /**
     * Retorna o número de vendas.
     * @return o número de vendas.
     */
    public int getNumVendas(){
        int x = 0;
        for(SimpleEntry<Integer,Integer> entry : this.lista) {
            x+= entry.getValue();
        }
        return x;
    }
}
