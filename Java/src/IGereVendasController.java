import java.io.Serializable;

public interface IGereVendasController extends Serializable {
    /**
     * Altera o modelo do controlador para o do argumento.
     * @param model Modelo.
     */
    void setModel(IGereVendasModel model);
    /**
     * Altera a view do controlador para a do argumento.
     * @param view View.
     */
    void setView(IGereVendasView view);
    /**
     * Executa o início do programa efetivo.
     */
    void startController();
}
