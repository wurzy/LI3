import java.io.Serializable;

/**
 * Par com produto e quantidade comprada do produto.
 */
public class ParProdQuantidade implements Serializable {
    private int quantidade;
    private IProduto produto;

    /**
     * Construtor parametrizado do Par.
     * @param p produto.
     * @param quantidade quantidade.
     */
    public ParProdQuantidade(IProduto p, int quantidade){
        this.produto = p.clone();
        this.quantidade = quantidade;
    }

    /**
     * Adiciona uma quantidade à quantidade atual.
     * @param quantidade quantidade a adicionar.
     */
    public void addQuantidade(int quantidade) {
        this.quantidade+=quantidade;
    }

    /**
     * Retorna a quantidade comprada do produto.
     * @return a quantidade comprada do produto.
     */
    public int getQuantidade(){
        return this.quantidade;
    }

    /**
     * Retorna o produto.
     * @return o produto.
     */
    public IProduto getProduto(){
        return this.produto.clone();
    }

    /**
     * Retorna um clone do Par.
     * @return clone do par.
     */
    public ParProdQuantidade clone(){
        return new ParProdQuantidade(this.produto.clone(),this.quantidade);
    }
}
