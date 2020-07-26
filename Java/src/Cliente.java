import java.io.Serializable;

/**
 * Classe que implementa a informação acerca de um cliente.
 */
public class Cliente implements ICliente, Serializable {
    private String codigo;

    /**
     * Construtor vazio dum cliente.
     */
    public Cliente(){
        this.codigo = "N/A";
    }

    /**
     * Construtor por parâmetro de um cliente.
     * Recebe como parâmetro um código de cliente.
     */
    public Cliente(String cod) {
        this.codigo = cod;
    }

    /**
     * Construtor por cópia de um cliente.
     * @param c Cliente que será usado como cópia.
     */
    public Cliente(Cliente c){
        this.codigo = c.getCodigo();
    }

    /**
     * Retorna o código de cliente.
     * @return Código de cliente, em String.
     */
    public String getCodigo() {
        return this.codigo;
    }

    /**
     * Altera o código da instância de cliente que invoca o método.
     * @param codigo Novo código de cliente.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Cria uma cópia da instância de Cliente que invoca o método.
     * @return Cópia do Cliente.
     */
    public Cliente clone(){
        return new Cliente(this);
    }

    /**
     * Compara dois objetos, em concreto, deverá ser usado para comparar dois Clientes.
     * @param o Objeto para comparar ao Cliente.
     * @return false, se não forem iguais, true se forem iguais.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return this.codigo.equals(cliente.getCodigo());
    }

    /**
     * Retorna o Cliente em formato de String.
     * @return Cliente em formato de String.
     */
    public String toString() {
        return this.codigo;
    }

    /**
     * Método de hashing para estruturas que o exigem.
     * @return Inteiro que representa o resultado do hashing.
     */
    public int hashCode(){
        return codigo.hashCode();
    }

}
