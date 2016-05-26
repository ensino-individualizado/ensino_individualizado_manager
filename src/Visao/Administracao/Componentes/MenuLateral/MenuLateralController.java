/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.Administracao.Componentes.MenuLateral;

import Visao.AcceptMessage;
import Visao.ControllerHierarchy.RegionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Gustavo Freitas
 */
public class MenuLateralController extends RegionController {

    private Button lastClicked = null;

    @FXML
    private void opcaoPainel(ActionEvent evento){
        if(!evento.getSource().equals(this.lastClicked)) {

            if(this.lastClicked != null) {
                this.lastClicked.setStyle(null);
            }

            this.lastClicked = ((Button) evento.getSource());
            this.lastClicked.setStyle("-fx-background-color: #43A047");

            ((AcceptMessage) getFatherController()).sendMessage(this.lastClicked.getId());
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
