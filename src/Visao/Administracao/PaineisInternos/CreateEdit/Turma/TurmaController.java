/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.Administracao.PaineisInternos.CreateEdit.Turma;

import Controle.AdministracaoMainController;
import Visao.Administracao.Componentes.SalvarCancelar.SalvarCancelarController;
import Visao.ControllerHierarchy.RegionController;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Gustavo Freitas
 */
public class TurmaController extends RegionController {

    private Runnable afterSave = null;
    private Runnable afterCancel = null;

    @FXML
    private TurmaFormController formularioController;

    @FXML
    private SalvarCancelarController salvarCancelarController;
    
    private void limparFormulario(){
        this.formularioController.limparDados();
    }

    public void salvar(){
        AdministracaoMainController.getInstance().getTurmaManager().createUpdate(this);
        if(this.afterSave != null){
            this.afterSave.run();
        }
    }

    public void cancelar(){
        this.limparFormulario();
        if(this.afterCancel != null){
            this.afterCancel.run();
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override 
    public void initialize(URL url, ResourceBundle rb) {
        //Define o que os botões irão fazer
        this.salvarCancelarController.setOnAction(this::salvar, this::cancelar);
    }

    public void setAfterSave(Runnable run){
        this.afterSave = run;
    }

    public void setAfterCancel(Runnable run){
        this.afterCancel = run;
    }

    public TurmaFormController getFormularioController() {
        return formularioController;
    }
}
