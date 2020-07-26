import java.io.Serializable;
import java.util.AbstractMap.*;

/**
 * Par com um cliente e o total de compras efetuadas dum produto e o total gasto nele.
 */
public class ParQuery9 implements Serializable {
    private SimpleEntry<ICliente,SimpleEntry<Integer,Double>> par;

    /**
     * Construtor parametrizado do Par.
     * @param cl cliente que comprou um dado produto.
     * @param outropar Par com o total comprado e o total gasto um dado produto.
     */
    public ParQuery9(ICliente cl,SimpleEntry<Integer,Double> outropar){
        this.par = new SimpleEntry<>(cl.clone(),outropar);
    }

    /**
     * Retorna o par em questão.
     * @return par cuja primeira componente é o cliente e a segunda um par de total comprado e total gasto.
     */
    public SimpleEntry<ICliente,SimpleEntry<Integer,Double>> getPar(){
        return new SimpleEntry<>(this.par.getKey().clone(),new SimpleEntry<>(this.par.getValue()));
    }
}
