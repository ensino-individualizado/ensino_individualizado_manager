package Visao.Administracao.PaineisInternos.CreateEdit.Avaliacao;

import Controle.AdministracaoMainController;
import DAO.PalavraDAO;
import Modelo.RecursoDidatico.Palavra;
import Visao.Administracao.Componentes.SalvarCancelar.SalvarCancelarController;
import com.gustavo.utils.javafx.ControllerHierarchy.RegionController;
import com.gustavo.utils.javafx.Dialog.JanelaDeMensagem;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Gustavo Freitas on 18/10/2015.
 */
public class AvaliacaoController extends RegionController {

    private Runnable afterSave;
    private Runnable afterCancel;

    @FXML
    private AvaliacaoFormController formularioController;

    @FXML
    private SalvarCancelarController salvarCancelarController;


    private void limparFormulario() {
        this.formularioController.limparDados();
    }

    public void salvar(){

        try {
            AdministracaoMainController.getInstance().getAvaliacaoManager().createUpdate(this);

            JanelaDeMensagem.mostrarSucesso("Sucesso ao salvar", "A avaliacao foi salva com sucesso",
                    "Dados:\n\t...");

            if(this.afterSave != null){
                afterSave.run();
            }

            this.getFormularioController().limparDados();
        }
        catch (Exception E){
            JanelaDeMensagem.mostrarExcessao("Erro", "Problema ao salvar os dados:", E);
        }
    }

    public void cancelar(){
        this.limparFormulario();
        if(this.afterCancel != null){
            this.afterCancel.run();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.salvarCancelarController.setOnAction(this::salvar, this::cancelar);

        this.formularioController.insertPalavra(PalavraDAO.getInstance().obterByCategoria(Palavra.CATEGORIA.DISSILABA_SIMPLES),
                PalavraDAO.getInstance().obterByCategoria(Palavra.CATEGORIA.TRISSILABA_SIMPLES),
                PalavraDAO.getInstance().obterByCategoria(Palavra.CATEGORIA.DIFICULDADE_DA_LINGUA));
    }

    public AvaliacaoFormController getFormularioController() {
        return formularioController;
    }
}