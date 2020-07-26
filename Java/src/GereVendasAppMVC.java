import static java.lang.System.exit;
import static java.lang.System.out;

/**
 * Aplicação principal do programa.
 */
public class GereVendasAppMVC {
    public static void main (String[] args){

        IGereVendasModel model = new GereVendasModel();
        model.createData();
        if(model.isNull()) {
            out.println("Não foi possível criar os dados.\nSaíndo do programa...");
            exit(-1);
        }
        IGereVendasView view = new GereVendasView();
        IGereVendasController control = new GereVendasController();
        control.setView(view);
        control.setModel(model);
        control.startController();
        exit(0);
    }
}
