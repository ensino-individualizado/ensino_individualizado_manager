package Visao.Administracao.PaineisInternos.CreateEdit.Tentativa.Forms;

import Modelo.RecursoDidatico.Palavra;
import com.gustavo.utils.javafx.ControllerHierarchy.RegionController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created by Gustavo Freitas on 18/10/2015.
 */
public class CopiaController extends RegionController {

    @FXML
    private ListView<Palavra> palavras;

    public Palavra getPalavra() {
        return palavras.getSelectionModel().getSelectedItem();
    }

    public void setPalavras(Collection<Palavra> palavra) {
        this.palavras.getItems().clear();
        this.palavras.getItems().addAll(palavra);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
