/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.Administracao;

import Controle.AdministracaoMainController;
import Visao.Administracao.Componentes.MenuLateral.MenuLateralController;
import com.gustavo.utils.javafx.AcceptMessage;
import com.gustavo.utils.javafx.ControllerHierarchy.RegionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Gustavo Freitas
 */
public class AdministracaoController extends RegionController implements AcceptMessage<String> {

    @FXML
    private AnchorPane pane;
    
    @FXML
    private MenuLateralController menuLateralController;
    
    @FXML
    private void telaCheia(ActionEvent event){
        windowController.getStage().setFullScreen(!windowController.getStage().isFullScreen());
    }
    
    /** 
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuLateralController.setFatherController(this);
        menuLateralController.setWindowController(this.windowController);
    }

    @Override
    public void sendMessage(String message) {
        AdministracaoMainController.getInstance().loadPane(this, message);
    }

    @Override
    public void receiveMessage(String message) {
        throw new UnsupportedOperationException("Operação de envio não suportada.");
    }

    public AnchorPane getPane() {
        return pane;
    }
}
