package Visao.Administracao.PaineisInternos.CreateEdit.Avaliacao;

import Modelo.RecursoDidatico.Palavra;
import com.gustavo.utils.javafx.ControllerHierarchy.RegionController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created by Gustavo Freitas on 18/10/2015.
 */
public class AvaliacaoFormController extends RegionController {

    int id = -1;

    @FXML
    private AvaliacaoFormController formularioController;

    @FXML
    private ListView<Palavra> dissilabas;

    @FXML
    private ListView<Palavra> trissilabas;

    @FXML
    private ListView<Palavra> dificuldades;

    @FXML
    private TextArea descricao;

    public void limparDados(){

    }

    public Collection<Palavra> getDissilabas() {
        return dissilabas.getSelectionModel().getSelectedItems();
    }

    public Collection<Palavra> getTrissilabas() {
        return trissilabas.getSelectionModel().getSelectedItems();
    }

    public Collection<Palavra> getDificuldades() {
        return dificuldades.getSelectionModel().getSelectedItems();
    }

    public String getDescricao() {
        return descricao.getText();
    }

    public void insertPalavra(Collection<Palavra> dissilabas, Collection<Palavra> trissilabas, Collection<Palavra> dificuldades){
        this.dissilabas.getItems().addAll(dissilabas);
        this.trissilabas.getItems().addAll(trissilabas);
        this.dificuldades.getItems().addAll(dificuldades);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.dissilabas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.trissilabas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.dificuldades.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public int getId() {
        return id;
    }
}