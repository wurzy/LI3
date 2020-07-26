import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IFilial extends Serializable {
    void addToFilial(IVenda v);
    int getCompras(ICliente cl,int mes);
    Set<ICliente> getClientes();
    Set<ICliente> getClientes(int mes);
    Map<ICliente,List<ClientesFilial>> getFilial();
    int getVendasZero();
    Set<IProduto> getProdutos();
    boolean equals(Object o);
    IFilial clone();
    void atualizaPar(int mes, int filiais, int filial, ParInterativa2 par);
    List<ClientesFilial> getVendas(ICliente cl);
    void atualizaPar(ParInterativa4 par, IProduto p, int mes);
    Map<IProduto,ParQuery6> mapComprados();
}
