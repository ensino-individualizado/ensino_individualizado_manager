package Controle.SubControllers;

import DAO.AvaliacaoDAO;
import Modelo.Avaliacao;
import Modelo.Bloco;
import Modelo.Construtores.AvaliacaoBuilder;
import Modelo.RecursoDidatico.Palavra;
import Visao.Administracao.PaineisInternos.CreateEdit.Avaliacao.AvaliacaoController;
import Visao.Administracao.PaineisInternos.CreateEdit.Avaliacao.AvaliacaoFormController;
import Visao.Mensagens.JanelaDeMensagem;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jooq.exception.DataAccessException;
import sun.misc.JavaNetAccess;

import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Gustavo Freitas on 01/11/2015.
 */
public class AvaliacaoManager implements SubController {

    public void createUpdate(AvaliacaoController form){
        if(form.getFormularioController().getId() == -1){
            this.novaAvaliacao(form.getFormularioController());
        }
        else{
            //result = this.atualizarAvaliacao(form.getFormularioController());
        }
    }

    private void novaAvaliacao(AvaliacaoFormController form) throws InvalidParameterException{

        Alert temp = null;

        AvaliacaoBuilder builder = new AvaliacaoBuilder();

        ArrayList<Palavra> todasPalavras = new ArrayList<Palavra>();

        if(form.getDissilabas().size() < 3 && form.getDificuldades().size() < 3 && form.getTrissilabas().size() < 3){
            throw new InvalidParameterException("Selecione ao menos três palavras para cada um dos tipos.");
        }

        temp = JanelaDeMensagem.showLoading();

        todasPalavras.addAll(form.getDissilabas());
        todasPalavras.addAll(form.getTrissilabas());
        todasPalavras.addAll(form.getDificuldades());

        builder.addBloco(Bloco.TYPE.NOMEAR_IMAGEM,              todasPalavras);
        builder.addBloco(Bloco.TYPE.APONTAR_PALAVRA,            todasPalavras);
        builder.addBloco(Bloco.TYPE.APONTAR_FIGURA,             todasPalavras);
        builder.addBloco(Bloco.TYPE.COMPARACAO_PALAVRA_FIGURAS, todasPalavras);
        builder.addBloco(Bloco.TYPE.COMPARACAO_PALAVRAS_FIGURA, todasPalavras);
        builder.addBloco(Bloco.TYPE.COPIA,                      todasPalavras);
        builder.addBloco(Bloco.TYPE.DITADO,                     todasPalavras);
        builder.addBloco(Bloco.TYPE.LEITURA,                    todasPalavras);

        builder.setDescricao(form.getDescricao());

        Avaliacao nova = builder.getInstance();

        try {
            if(!AvaliacaoDAO.getInstance().novo(nova)){
                throw new DataAccessException("Erro ao salvar os dados.");
            }
        } catch (SQLException | ClassNotFoundException | DataAccessException e) {
            temp.close();
            e.printStackTrace();
            JanelaDeMensagem.mostrarExcessao("Erro ao salvar avaliacao", "Erro fatal: " + e.getMessage(), e);
        }
        finally {
            if(temp != null)temp.close();
        }
    }

    private boolean atualizarAvaliacao(AvaliacaoFormController form){
        return (false);
    }

    public Collection<Avaliacao> obterTodas(){
        return (AvaliacaoDAO.getInstance().obterTodos());
    }

}
