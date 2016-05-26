/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.Administracao.PaineisInternos.CreateEdit.Turma;

import Visao.ControllerHierarchy.RegionController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Gustavo Freitas
 */
public class TurmaFormController extends RegionController {

    private Integer id = -1;

    @FXML
    private TextField nome;
    
    @FXML
    private TextArea descricao;

    public void limparDados() {
        this.nome.clear();
        this.descricao.clear();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome.getText();
    }

    public void setNome(String nome) {
        this.nome.setText(nome);
    }

    public String getDescricao() {
        return descricao.getText();
    }

    public void setDescricao(String descricao) {
        this.descricao.setText(descricao);
    }
}
