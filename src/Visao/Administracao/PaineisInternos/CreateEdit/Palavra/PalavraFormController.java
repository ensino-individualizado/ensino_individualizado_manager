/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.Administracao.PaineisInternos.CreateEdit.Palavra;

import Controle.AdministracaoMainController;
import Modelo.RecursoDidatico.Silaba;
import Visao.ControllerHierarchy.RegionController;
import Visao.Mensagens.ObterArquivo;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Gustavo Freitas
 */
public class PalavraFormController extends RegionController {

    private int Id = -1;

    @FXML
    private TextField palavra;
    
    @FXML
    private TextField caminhoImagem;
    
    @FXML
    private TextField caminhoAudio;
    
    @FXML
    private TextArea transcricaoAudio;
    
    @FXML
    private TextField caminhoVideo;
    
    @FXML
    private TextArea descricaoVideo;
    
    @FXML
    private TextField silaba;
    
    @FXML
    private ListView<Silaba> silabas;

    @FXML
    private Button autoGenButton;

    @FXML
    public void inserirAudio(ActionEvent evento){
        this.caminhoAudio.setText(ObterArquivo.obterAudio(this.windowController.getStage()));
    }
    
    @FXML
    public void inserirImagem(ActionEvent evento){
        this.caminhoImagem.setText(ObterArquivo.obterImagem(this.windowController.getStage()));
    } 
    
    @FXML
    public void inserirVideo(ActionEvent evento){
        this.caminhoVideo.setText(ObterArquivo.obterVideo(this.windowController.getStage()));
    }
    
    @FXML
    public void adicionarSilaba(ActionEvent evento){
        if(this.silaba.getText().length() > 0){
            this.silabas.getItems().add(new Silaba(this.silaba.getText()));
            this.silaba.clear();
        }
    }

    @FXML
    public void removerSilaba(ActionEvent evento){
        silabas.getItems().remove(silabas.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void gerarSilabas(ActionEvent evento){
        AdministracaoMainController.getInstance().getPalavraManager().separarSilabas(this);
    }

    @FXML
    void palavraAtualizada(KeyEvent event) {
        if(this.palavra.getText().length() == 0){
            this.autoGenButton.setDisable(true);
        }
        else{
            this.autoGenButton.setDisable(false);
        }
    }

    public void limparDados() {
        this.palavra.clear();
        this.caminhoAudio.clear();
        this.transcricaoAudio.clear();
        this.caminhoVideo.clear();
        this.descricaoVideo.clear();
        this.silaba.clear();
        this.silabas.getItems().clear();
        this.caminhoImagem.clear();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.silabas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }    

    public String getPalavra() {
        return palavra.getText();
    }

    public String getCaminhoImagem() {
        return caminhoImagem.getText();
    }

    public String getCaminhoAudio() {
        return caminhoAudio.getText();
    }

    public String getTranscricaoAudio() {
        return transcricaoAudio.getText();
    }

    public String getCaminhoVideo() {
        return caminhoVideo.getText();
    }

    public String getDescricaoVideo() {
        return descricaoVideo.getText();
    }

    public ObservableList<Silaba> getSilabas() {
        return silabas.getItems();
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
