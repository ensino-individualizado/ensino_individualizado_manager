package Controle.SubControllers;

import DAO.TurmaDAO;
import Modelo.Turma;
import Visao.Administracao.FxmlReference;
import Visao.Administracao.PaineisInternos.CreateEdit.Turma.TurmaController;
import Visao.Administracao.PaineisInternos.CreateEdit.Turma.TurmaFormController;
import Visao.Administracao.PaineisInternos.Visualizar.Turma.TurmasController;
import Visao.Dialog.SimpleDialog;
import Visao.Mensagens.JanelaDeMensagem;
import javafx.application.Platform;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by Gustavo Freitas on 12/10/2015.
 */
public class TurmaManager implements SubController{

    public void updateTurma(TurmasController controller){
        Turma tmp = controller.getTabela().getSelectionModel().getSelectedItem();

        if(tmp == null){
            JanelaDeMensagem.mostrarSucesso("Selecione um aluno para editar", "É necessário selecionar o aluno que deseja editar.", null);
        }
        else{
            try{
                final SimpleDialog dialog = new SimpleDialog(controller.getWindowController(), "Editar aluno", "Atualize os dados do aluno com os campos abaixo.",
                        FxmlReference.getReference("novo_turma"));

                TurmaFormController form = ((TurmaController) dialog.getController()).getFormularioController();

                form.setId(tmp.getId());
                form.setNome(tmp.getNome());
                form.setDescricao(tmp.getDescricao());

                ((TurmaController) dialog.getController()).setAfterCancel(() -> {
                    dialog.getDialogPane().getScene().getWindow().hide();
                    dialog.close();
                });

                ((TurmaController) dialog.getController()).setAfterSave(() -> {
                    Platform.runLater(() -> {
                        dialog.getDialogPane().getScene().getWindow().hide();
                        dialog.close();
                    });
                });

                dialog.showAndWait();
            }
            catch (IOException IOE) {
                JanelaDeMensagem.mostrarExcessao("Erro ao carregar funcionalidade", "Ocorreu um erro ao iniciar a função solicitada.", IOE);
            }
        }
    }

    public boolean createUpdate(TurmaController form){

        boolean result = false;

        if(form.getFormularioController().getId() == -1){
            result = this.novaTurma(form.getFormularioController());
        }
        else{
            result = this.atualizarTurma(form.getFormularioController());
        }

        if(result){
            JanelaDeMensagem.mostrarSucesso("Sucesso ao salvar", "A turma foi salva com sucesso",
                    "Dados:\n\tNome: " + form.getFormularioController().getNome() +
                    "\n\tDescrição: " + form.getFormularioController().getDescricao());
            form.getFormularioController().limparDados();
        }
        return (result);
    }

    private boolean novaTurma(TurmaFormController form){

        Turma nova = null;

        if(form.getNome().length() == 0 || form.getDescricao().length() == 0){
            JanelaDeMensagem.mostrarErro("Erro", "Os campos devem ser preenchidos corretamente.", null);
            return (false);
        }
        else{
            nova = new Turma(form.getNome(), form.getDescricao());
            return (TurmaDAO.getInstance().novo(nova));
        }
    }


    private boolean atualizarTurma(TurmaFormController form){

        Turma updated = null;

        if(form.getNome().length() == 0 || form.getDescricao().length() == 0){
            JanelaDeMensagem.mostrarErro("Erro", "Os campos devem ser preenchidos corretamente.", null);
            return (false);
        }
        else{
            updated = new Turma(form.getId(), form.getNome(), form.getDescricao());
            return(TurmaDAO.getInstance().atualizar(updated));
        }
    }

    public Collection<Turma> obterTodasAsTurmas(){
        return (TurmaDAO.getInstance().obterTodos());
    }

    public boolean apagarTurma(TurmasController controller) {
        Turma tmp = controller.getTabela().getSelectionModel().getSelectedItem();

        if(tmp == null){
            JanelaDeMensagem.mostrarSucesso("Selecione uma turma para excluir.", "É necessário selecionar a turma que deseja excluir.", null);
        }
        else{
            if(TurmaDAO.getInstance().apagar(tmp.getId())){
                JanelaDeMensagem.mostrarSucesso("Turma apagada", "A turma foi removida com sucesso!", null);
                controller.getTabela().getItems().clear();
                controller.getTabela().getItems().addAll(this.obterTodasAsTurmas());
                return (true);
            }
        }
        return (false);
    }

}
