import java.io.Serializable;
import java.security.ProtectionDomain;
import java.util.*;
/**
 * Classe que gera um produto.
 */
public class Produto implements IProduto, Serializable {
    private String codigo;

    /**
     * Construtor por default.
     */
    public Produto(){
        this.codigo = "N/A";
    }

    /**
     * Construtor por parametrizado.
     * @param codigo Código do produto.
     */
    public Produto(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Construtor por cópia.
     * @param p Produto a ser copiado.
     */
    public Produto(Produto p){
        this.codigo = p.getCodigo();
    }

    /**
     * Função que retorna o código de produto.
     */
    public String getCodigo() {
        return this.codigo;
    }

    /**
     * Função que define o código de produto.
     * @param codigo Código que o produto irá ter.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Função que dá clone a um Produto.
     */
    public Produto clone(){
        return new Produto(this);
    }

    /**
     * Função que verifica se dois produtos são iguais.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return this.codigo.equals(produto.getCodigo());
    }

    /**
     * Função que transforma um Produto em String.
     */
    public String toString() {
        return this.codigo;
    }

    /**
     * Função define o hashcode de um produto.
     */
    public int hashCode(){
        return this.codigo.hashCode();
    }

}
