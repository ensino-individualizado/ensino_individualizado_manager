package Visao.Administracao.PaineisInternos.Visualizar.Palavras;

import Controle.AdministracaoMainController;
import Modelo.RecursoDidatico.Palavra;
import Visao.Administracao.Componentes.ToolBar.RegionWithToollbarEmbeded;
import Visao.Administracao.Componentes.ToolBar.ToolBar;
import com.gustavo.utils.javafx.ControllerHierarchy.RegionController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Gustavo Freitas on 15/10/2015.
 */
public class PalavrasController extends RegionController implements RegionWithToollbarEmbeded{

    @FXML
    private ToolBar toolBarController;

    @FXML
    private TableView<Palavra> table;

    @FXML
    private TableColumn coluna_id;

    @FXML
    private TableColumn coluna_palavra;

    public void uptateTable(){
        this.table.getItems().clear();
        this.table.setItems(FXCollections.observableArrayList(AdministracaoMainController.getInstance().getPalavraManager().obterTodasPalavras()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        coluna_id.setCellValueFactory(new PropertyValueFactory("id"));
        coluna_palavra.setCellValueFactory(new PropertyValueFactory("palavra"));

        this.toolBarController.setFatherController(this);
        this.toolBarController.setWindowController(this.getWindowController());
        this.toolBarController.setSupported(false, false, false, true);
        this.uptateTable();
    }

    @Override
    public void filter(String filter) {

    }

    @Override
    public void delete() {

    }

    @Override
    public void edit() {

    }

    @Override
    public void info() {
        AdministracaoMainController.getInstance().getPalavraManager().showInfoPalavra(this);
    }

    public TableView<Palavra> getTable() {
        return table;
    }
}
