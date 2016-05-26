package Visao.Administracao.PaineisInternos.Visualizar.Palavras;

import Controle.AdministracaoMainController;
import Modelo.RecursoDidatico.Palavra;
import Modelo.RecursoDidatico.Silaba;
import Visao.ControllerHierarchy.RegionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created by Gustavo Freitas on 16/10/2015.
 */
public class PalavraInfoController extends RegionController{

    private Palavra referencia;

    @FXML
    private TextField palavra;

    @FXML
    private TextArea transcricaoAudio;

    @FXML
    private TextArea descricaoVideo;

    @FXML
    private ImageView imagem;

    @FXML
    private ListView<Silaba> silabas;

    @FXML
    private Button playVideoButton;

    @FXML
    private void playAudio(ActionEvent event){
        AdministracaoMainController.getInstance().getPalavraManager().playAudio(this);
    }

    @FXML
    private void playVideo(ActionEvent event){
        AdministracaoMainController.getInstance().getPalavraManager().playVideo(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.descricaoVideo.setText("Vídeo não disponível.");
        this.descricaoVideo.setDisable(true);
        this.playVideoButton.setDisable(true);
    }

    public Palavra getReferencia() {
        return referencia;
    }

    public void setReferencia(Palavra referencia) {
        this.referencia = referencia;
    }

    public String getPalavra() {
        return palavra.getText();
    }

    public void setPalavra(String palavra) {
        this.palavra.setText(palavra);
    }

    public String getTranscricaoAudio() {
        return transcricaoAudio.getText();
    }

    public void setTranscricaoAudio(String transcricaoAudio) {
        this.transcricaoAudio.setText(transcricaoAudio);
    }

    public String getDescricaoVideo() {
        return descricaoVideo.getText();
    }

    public void setDescricaoVideo(String descricaoVideo) {
        this.descricaoVideo.setText(descricaoVideo);
        this.descricaoVideo.setDisable(false);
        this.playVideoButton.setDisable(false);
    }

    public ImageView getImagem() {
        return imagem;
    }

    public void setImagem(String src) {
        this.imagem.setImage(new Image("file:////" + src));
    }

    public Collection<Silaba> getSilabas() {
        return silabas.getItems();
    }

    public void setSilabas(Collection<Silaba> silabas) {
        this.silabas.getItems().clear();
        this.silabas.getItems().addAll(silabas);
    }
}
