/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.Administracao.PaineisInternos.CreateEdit.Aluno;

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
public class AlunoController extends RegionController {

    private Runnable afterSave = null;
    private Runnable afterCancel = null;

    @FXML
    private AlunoFormController formularioController;

    @FXML
    private SalvarCancelarController salvarCancelarController;
    
    private void limparFormulario() {
        this.formularioController.limparDados();
    }

    public void salvar(){
        boolean result = AdministracaoMainController.getInstance().getAlunoManager().createUpdate(this);
        if(this.afterSave != null && result){
            this.afterSave.run();
        }
    }

    public void cancelar(){
        this.limparFormulario();
        if(this.afterCancel != null){
            this.afterCancel.run();
        }
    }

    public void setAfterSave(Runnable run){
        this.afterSave = run;
    }

    public void setAfterCancel(Runnable run){
        this.afterCancel = run;
    }

    /**
     * Inicializa o controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Define o que os botões irão fazer
        this.salvarCancelarController.setOnAction(this::salvar, this::cancelar);
        this.formularioController.setTurmas(AdministracaoMainController.getInstance().getTurmaManager().obterTodasAsTurmas());
    }

    public AlunoFormController getFormularioController() {
        return formularioController;
    }
}
