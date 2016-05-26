/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.Administracao.PaineisInternos.Visualizar.Turma;

import Controle.AdministracaoMainController;
import Modelo.Turma;
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
 * FXML Controller class
 *
 * @author Gustavo Freitas
 */
public class TurmasController extends RegionController implements RegionWithToollbarEmbeded {

    @FXML
    private ToolBar toolBarController;
    
    @FXML
    private TableView<Turma> tabela;
    
    @FXML
    private TableColumn coluna_id;
    
    @FXML
    private TableColumn coluna_nome;
    
    @FXML
    private TableColumn coluna_descricao;

    private void atualizarTabela(){
        this.tabela.getItems().clear();
        this.tabela.setItems(FXCollections.observableArrayList(AdministracaoMainController.getInstance().getTurmaManager().obterTodasAsTurmas()));
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        coluna_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        coluna_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        coluna_descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

        this.toolBarController.setFatherController(this);
        this.toolBarController.setWindowController(this.getWindowController());
        this.toolBarController.setSupported(false, true, true, false);

        this.atualizarTabela();
    }

    public TableView<Turma> getTabela() {
        return tabela;
    }

    @Override
    public void filter(String filter) {

    }

    @Override
    public void delete() {
        AdministracaoMainController.getInstance().getTurmaManager().apagarTurma(this);
        this.getTabela().getItems().clear();
        this.getTabela().getItems().addAll(AdministracaoMainController.getInstance().getTurmaManager().obterTodasAsTurmas());
    }

    @Override
    public void edit() {
        AdministracaoMainController.getInstance().getTurmaManager().updateTurma(this);
        this.getTabela().getItems().clear();
        this.getTabela().getItems().addAll(AdministracaoMainController.getInstance().getTurmaManager().obterTodasAsTurmas());
    }

    @Override
    public void info() {

    }
}
