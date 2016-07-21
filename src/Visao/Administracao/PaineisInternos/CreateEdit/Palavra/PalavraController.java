/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.Administracao.PaineisInternos.CreateEdit.Palavra;

import Controle.AdministracaoMainController;
import Visao.Administracao.Componentes.SalvarCancelar.SalvarCancelarController;
import com.gustavo.utils.javafx.ControllerHierarchy.RegionController;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Gustavo Freitas
 */
public class PalavraController extends RegionController {

    @FXML
    private PalavraFormController formularioController;

    @FXML
    private SalvarCancelarController salvarCancelarController;
    
    //@Override
    public void salvar(){
        AdministracaoMainController.getInstance().getPalavraManager().createUpdate(this);
    }

   // @Override
    public void cancelar(){
        this.limparFormulario();
    }
    
    private void limparFormulario() {
        this.formularioController.limparDados();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Define o que os botões irão fazer
        this.salvarCancelarController.setOnAction(this::salvar, this::cancelar);
        this.formularioController.setFatherController(this);
        this.formularioController.setWindowController(AdministracaoMainController.getInstance().getWindowController());
    }

    public PalavraFormController getFormularioController() {
        return formularioController;
    }

    public SalvarCancelarController getSalvarCancelarController() {
        return salvarCancelarController;
    }
}
