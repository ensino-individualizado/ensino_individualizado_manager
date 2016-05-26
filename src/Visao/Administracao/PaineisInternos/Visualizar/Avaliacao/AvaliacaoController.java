package Visao.Administracao.PaineisInternos.Visualizar.Avaliacao;

import Controle.AdministracaoMainController;
import Modelo.Avaliacao;
import Visao.Administracao.Componentes.ToolBar.RegionWithToollbarEmbeded;
import Visao.Administracao.Componentes.ToolBar.ToolBar;
import Visao.ControllerHierarchy.RegionController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Gustavo Freitas on 18/10/2015.
 */
public class AvaliacaoController extends RegionController implements RegionWithToollbarEmbeded{

    @FXML
    private ToolBar toolBarController;

    @FXML
    private TableView<Avaliacao> tabela;

    @FXML
    private TableColumn coluna_id;

    @FXML
    private TableColumn coluna_descricao;

    private void atualizarTabela(){
        this.tabela.getItems().clear();
        this.tabela.setItems(FXCollections.observableArrayList(AdministracaoMainController.getInstance().getAvaliacaoManager().obterTodas()));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        coluna_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        coluna_descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

        this.toolBarController.setFatherController(this);
        this.toolBarController.setWindowController(this.getWindowController());
        this.toolBarController.setSupported(false, false, false, false);

        this.atualizarTabela();
    }

    public TableView<Avaliacao> getTabela() {
        return tabela;
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
    }
}