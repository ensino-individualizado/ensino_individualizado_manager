package Controle.SubControllers;

import Controle.AdministracaoMainController;
import DAO.AlunoDAO;
import Modelo.Aluno;
import Visao.Administracao.FxmlReference;
import Visao.Administracao.PaineisInternos.CreateEdit.Aluno.AlunoFormController;
import Visao.Administracao.PaineisInternos.Visualizar.Alunos.AlunoController;
import Visao.Administracao.PaineisInternos.Visualizar.Alunos.AlunoInfoController;
import Visao.Dialog.SimpleDialog;
import Visao.Mensagens.JanelaDeMensagem;
import javafx.application.Platform;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by Gustavo Freitas on 12/10/2015.
 */
public class AlunoManager implements SubController{

    public void showInfoAluno(AlunoController controller) {
        Aluno aluno = controller.getTabela().getSelectionModel().getSelectedItem();

        if(aluno != null){
            try {
                SimpleDialog dialog = new SimpleDialog(controller.getWindowController(), "Aluno", null,
                        FxmlReference.getReference("visualizar_alunos_info"));

                AlunoInfoController info = (AlunoInfoController) dialog.getController();
                info.setReferencia(aluno);
                dialog.showAndWait();
            }
            catch (Exception IOE) {
                JanelaDeMensagem.mostrarExcessao("Erro ao carregar funcionalidade", "Ocorreu um erro ao iniciar a função solicitada.", IOE);
            }
        }
        else{
            JanelaDeMensagem.mostrarSucesso("Selecione uma palavra", "É necessário selecionar a palavra que deseja mais informações.", null);
        }
    }

    public void updateAluno(AlunoController controller){

        Aluno tmp = controller.getTabela().getSelectionModel().getSelectedItem();

        if(tmp == null){
            JanelaDeMensagem.mostrarSucesso("Selecione um aluno para editar", "É necessário selecionar o aluno que deseja editar.", null);
        }
        else{

            try {
                SimpleDialog dialog = new SimpleDialog(controller.getWindowController(), "Editar aluno", "Atualize os dados do aluno com os campos abaixo.",
                                                FxmlReference.getReference("novo_aluno"));

                AlunoFormController form = ((Visao.Administracao.PaineisInternos.CreateEdit.Aluno.AlunoController) dialog.getController()).getFormularioController();

                form.setId(tmp.getId());
                form.setNome(tmp.getNome());
                form.setTurmas(AdministracaoMainController.getInstance().getTurmaManager().obterTodasAsTurmas());
                form.setDataNascimento(tmp.getDataNascimento());

                ((Visao.Administracao.PaineisInternos.CreateEdit.Aluno.AlunoController) dialog.getController()).setAfterCancel(() -> {
                    dialog.getDialogPane().getScene().getWindow().hide();
                    dialog.close();
                });

                ((Visao.Administracao.PaineisInternos.CreateEdit.Aluno.AlunoController) dialog.getController()).setAfterSave(() -> {
                    Platform.runLater(() -> {
                        dialog.getDialogPane().getScene().getWindow().hide();
                        dialog.close();
                    });
                });

                dialog.showAndWait();

                controller.getTabela().getItems().clear();
                controller.getTabela().getItems().addAll(this.obterTodosOsAlunos());
            }
            catch (IOException IOE) {
                JanelaDeMensagem.mostrarExcessao("Erro ao carregar funcionalidade", "Ocorreu um erro ao iniciar a função solicitada.", IOE);
            }
        }
    }

    public boolean createUpdate(Visao.Administracao.PaineisInternos.CreateEdit.Aluno.AlunoController form){

        boolean result = false;

        if(form.getFormularioController().getId() == -1){
            result = this.novoAluno(form.getFormularioController());
        }
        else{
            result = this.atualizarAluno(form.getFormularioController().getId(), form.getFormularioController());
        }

        if(result){
            JanelaDeMensagem.mostrarSucesso("Sucesso ao salvar", "O aluno foi salvo com sucesso",
                "Dados:\n\tNome: " + form.getFormularioController().getNome() +
                "\n\tData de Nascimento: " + form.getFormularioController().getDataNascimento());
//               + "\n\tTurma: " + form.getFormularioController().getTurma().getNome());
            form.getFormularioController().limparDados();
        }
        return (result);
    }

    private boolean novoAluno(AlunoFormController form) {
        if(validateForm(form)) {
            Aluno novo = new Aluno(form.getNome(), form.getDataNascimento(), form.getTurma());
            return (AlunoDAO.getInstance().novo(novo));
        }
        else{
            JanelaDeMensagem.mostrarErro("Preencha os campos corretamente", "Os dados não foram inseridos corretamente.", null);
            return (false);
        }
    }

    private boolean atualizarAluno(Integer id, AlunoFormController form) {
        if(validateForm(form)) {
            Aluno updated = new Aluno(form.getId(), form.getNome(), form.getDataNascimento(), form.getTurma());
            return (AlunoDAO.getInstance().atualizar(updated));
        }
        else{
            JanelaDeMensagem.mostrarErro("Preencha os campos corretamente", "Os dados não foram inseridos corretamente.", null);
            return (false);
        }
    }

    public Collection<Aluno> obterTodosOsAlunos() {
        return AlunoDAO.getInstance().obterTodos();
    }

    public boolean apagarAluno(AlunoController controller) {

        Aluno selected = controller.getTabela().getSelectionModel().getSelectedItem();

        if(selected == null){
            JanelaDeMensagem.mostrarSucesso("Selecione um aluno para excluir.", "É necessário selecionar o aluno que deseja apagar.", null);
        }
        else{
            if(AlunoDAO.getInstance().apagar(selected.getId())){
                JanelaDeMensagem.mostrarSucesso("Aluno apagado", "O aluno foi removido com sucesso!", null);
                controller.getTabela().getItems().clear();
                controller.getTabela().getItems().addAll(this.obterTodosOsAlunos());
                return (true);
            }
            else{
                JanelaDeMensagem.mostrarErro("Não é possível apagar", "O aluno não foi apagado.", "Verifique a existência de avaliações desse aluno e tente novamente.");
            }
        }
        return(false);
    }

    public boolean validateForm(AlunoFormController form){
        //if(form.getDataNascimento() != null && form.getNome().length() != 0 && form.getTurma() != null){
        if(form.getDataNascimento() != null && form.getNome().length() != 0 && form.getTurma() != null){
            return (true);
        }
        return (false);
    }
}
