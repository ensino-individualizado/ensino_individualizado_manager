package Visao.Administracao.PaineisInternos.CreateEdit.Tentativa.Forms;

import Modelo.RecursoDidatico.Palavra;
import Modelo.RecursoDidatico.Silaba;
import Visao.ControllerHierarchy.RegionController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created by Gustavo Freitas on 18/10/2015.
 */
public class DitadoController extends RegionController {
    @FXML
    private ListView<Palavra> palavras;

    @FXML
    private TextField silaba;

    @FXML
    private ListView<Silaba> silabas;

    @FXML
    public void adicionarSilaba(){
        if(this.silaba.getText().length() > 0){
            this.silabas.getItems().add(new Silaba(this.silaba.getText()));
            this.silaba.clear();
        }
    }

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
