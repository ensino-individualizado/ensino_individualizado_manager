/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.Administracao.PaineisInternos.Visualizar.Alunos;

import Controle.AdministracaoMainController;
import Modelo.Aluno;
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
 * FXML Controller class
 *
 * @author Gustavo Freitas
 */
public class AlunoController extends RegionController implements RegionWithToollbarEmbeded {
    
    @FXML
    private ToolBar toolBarController;
    
    @FXML
    private TableView<Aluno> tabela;
    
    @FXML
    private TableColumn coluna_id;
    
    @FXML
    private TableColumn coluna_nome;
    
    @FXML
    private TableColumn coluna_nascimento;

    private void atualizarTabela(){
        this.tabela.getItems().clear();
        this.tabela.setItems(FXCollections.observableArrayList(AdministracaoMainController.getInstance().getAlunoManager().obterTodosOsAlunos()));
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        coluna_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        coluna_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        coluna_nascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        //coluna_turma.setCellValueFactory(new PropertyValueFactory<>("turma"));

        this.toolBarController.setFatherController(this);
        this.toolBarController.setWindowController(this.getWindowController());
        this.toolBarController.setSupported(false, true, true, false);

        this.atualizarTabela();
    }

    public TableView<Aluno> getTabela() {
        return tabela;
    }

    @Override
    public void filter(String filter) {

    }

    @Override
    public void delete() {
        AdministracaoMainController.getInstance().getAlunoManager().apagarAluno(this);
    }

    @Override
    public void edit() {
        AdministracaoMainController.getInstance().getAlunoManager().updateAluno(this);
    }

    @Override
    public void info() {
        AdministracaoMainController.getInstance().getAlunoManager().showInfoAluno(this);
    }
}
