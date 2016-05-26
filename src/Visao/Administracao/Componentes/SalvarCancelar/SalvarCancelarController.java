/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.Administracao.Componentes.SalvarCancelar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Gustavo Freitas
 */
public class SalvarCancelarController implements Initializable {

    @FXML
    private Button save;

    @FXML
    private Button cancel;

    private Runnable onSave = null;
    private Runnable onCancel = null;
    
    @FXML
    private void save(ActionEvent evento){
        onSave.run();
    }
    
    @FXML
    private void cancel(ActionEvent evento){
        onCancel.run();
    }
    
    public void setOnAction(Runnable save, Runnable calcel){
        if(save != null) {
            this.onSave = save;
            this.save.setDisable(false);
        }
        if(cancel != null) {
            this.onCancel = calcel;
            this.cancel.setDisable(false);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.save.setDisable(true);
        this.cancel.setDisable(true);
    }    
    
}
