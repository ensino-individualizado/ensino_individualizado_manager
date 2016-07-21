package Controle.SubControllers;

import DAO.PalavraDAO;
import Ferramentas.FileSaver;
import Ferramentas.SeparadorSilabicoAdapter;
import Modelo.Construtores.PalavraBuilder;
import Modelo.RecursoDidatico.Palavra;
import Modelo.RecursoDidatico.Silaba;
import Visao.Administracao.FxmlReference;
import Visao.Administracao.PaineisInternos.CreateEdit.Palavra.PalavraController;
import Visao.Administracao.PaineisInternos.CreateEdit.Palavra.PalavraFormController;
import Visao.Administracao.PaineisInternos.Visualizar.Palavras.PalavraInfoController;
import Visao.Administracao.PaineisInternos.Visualizar.Palavras.PalavrasController;
import com.gustavo.utils.javafx.Dialog.JanelaDeMensagem;
import com.gustavo.utils.javafx.Dialog.SimpleDialog;
import com.gustavo.utils.javafx.Dialog.SimpleMediaPlayerDialog.SimpleMediaPlayerDialog;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by Gustavo Freitas on 12/10/2015.
 */
public class PalavraManager implements SubController{

    public boolean createUpdate(PalavraController form){

        boolean result = false;

        if(form.getFormularioController().getId() == -1){
            result = this.novaPalavra(form.getFormularioController());
        }
        else{
            result = this.atualizarPalavra(form.getFormularioController());
        }

        if(result){
            JanelaDeMensagem.mostrarSucesso("Sucesso ao salvar", "A palavra foi salva com sucesso",
                    "Dados:\n\tPalavra: " + form.getFormularioController().getPalavra());
            form.getFormularioController().limparDados();
        }
        else{
            JanelaDeMensagem.mostrarErro("Erro ao salvar =(", "Ocorreu um erro ao salvar a palavra...", null);
        }
        return (result);
    }

    public boolean novaPalavra(PalavraFormController form){

        Palavra nova;
        PalavraBuilder palavraBuilder = new PalavraBuilder();
        palavraBuilder.novaPalavra(form.getPalavra());
        palavraBuilder.adicionarAudio(form.getCaminhoAudio(), form.getTranscricaoAudio());
        if(form.getCaminhoVideo().length() != 0) {
            palavraBuilder.adicionarVideo(form.getCaminhoVideo(), form.getDescricaoVideo());
        }
        palavraBuilder.adicionarImagem(form.getCaminhoImagem());
        palavraBuilder.adicionarSilabas(form.getSilabas());

        nova = palavraBuilder.getInstance();

        return (PalavraDAO.getInstance().novo(nova));
    }

    public boolean atualizarPalavra(PalavraFormController form) {
        Palavra atualizada;
        PalavraBuilder palavraBuilder = new PalavraBuilder();
        palavraBuilder.novaPalavra(form.getPalavra());
        palavraBuilder.adicionarAudio(form.getCaminhoAudio(), form.getTranscricaoAudio());
        if(form.getCaminhoVideo().length() != 0) {
            palavraBuilder.adicionarVideo(form.getCaminhoVideo(), form.getDescricaoVideo());
        }
        palavraBuilder.adicionarImagem(form.getCaminhoImagem());
        palavraBuilder.adicionarSilabas(form.getSilabas());

        atualizada = palavraBuilder.getInstance();

        return (PalavraDAO.getInstance().atualizar(atualizada));
    }

    public void separarSilabas(PalavraFormController form){
        if(form.getPalavra().length() > 0) {
            Collection<Silaba> silabas = SeparadorSilabicoAdapter.getInstance().separar(form.getPalavra());
            form.getSilabas().clear();
            form.getSilabas().addAll(silabas);
        }
    }

    public Collection<Palavra> obterTodasPalavras() {
        return PalavraDAO.getInstance().obterTodos();
    }

    public void showInfoPalavra(PalavrasController controller) {
        Palavra palavra = controller.getTable().getSelectionModel().getSelectedItem();

        if(palavra != null){
            try {
                SimpleDialog dialog = new SimpleDialog(controller.getWindowController(), "Palavra", null,
                        FxmlReference.getReference("visualizar_palavras_info"));

                PalavraInfoController info = (PalavraInfoController) dialog.getController();

                info.setReferencia(palavra);

                info.setPalavra(palavra.getPalavra());
                info.setTranscricaoAudio(palavra.getAudio().getTranscricao());
                if(palavra.getVideo() != null){
                    info.setDescricaoVideo(palavra.getVideo().getDescricao());
                }
                info.setSilabas(palavra.getSilabas());

                PalavraDAO.getInstance().carregarImagem(palavra, FileSaver.getInstance().getTmpFolderLocation());
                info.setImagem(palavra.getImagem().getLocal());

                dialog.showAndWait();
            }
            catch (IOException IOE) {
                JanelaDeMensagem.mostrarExcessao("Erro ao carregar funcionalidade", "Ocorreu um erro ao iniciar a função solicitada.", IOE);
            }
        }
        else{
            JanelaDeMensagem.mostrarSucesso("Selecione uma palavra", "É necessário selecionar a palavra que deseja mais informações.", null);
        }
    }

    public void playVideo(PalavraInfoController controller){

        Palavra tmp = controller.getReferencia();

        if(PalavraDAO.getInstance().carregarVideo(tmp, FileSaver.getInstance().getTmpFolderLocation())) {

            try {
                SimpleMediaPlayerDialog player = new SimpleMediaPlayerDialog(controller.getWindowController(), tmp.getVideo().getNomeOriginal(), tmp.getVideo().getLocal());
                player.showWaitAndPlay();
            } catch (IOException e) {
                JanelaDeMensagem.mostrarExcessao("Erro.", "Erro ao abrir video.", e);
            }
        }
    }

    public void playAudio(PalavraInfoController controller){

        Palavra tmp = controller.getReferencia();

        if(PalavraDAO.getInstance().carregarAudio(tmp, FileSaver.getInstance().getTmpFolderLocation())) {

            try {
                SimpleMediaPlayerDialog player = new SimpleMediaPlayerDialog(controller.getWindowController(), tmp.getAudio().getNomeOriginal(), tmp.getAudio().getLocal());
                player.showWaitAndPlay();
            } catch (IOException e) {
                JanelaDeMensagem.mostrarExcessao("Erro.", "Erro ao abrir video.", e);
            }
        }
    }
}
