package Controle.SubControllers;

import Visao.Administracao.PaineisInternos.CreateEdit.Tentativa.Forms.ApontarFiguraFormController;
import Visao.Administracao.PaineisInternos.CreateEdit.Tentativa.TentativaController;
import Visao.Mensagens.JanelaDeMensagem;

/**
 * Created by Gustavo Freitas on 17/10/2015.
 */
public class TentativaManager {

    public boolean createUpdate(TentativaController controller){
        /*
        Tentativa.TENTATIVA_TYPE tipo = controller.getTipoSelecionado();
        switch (tipo){
            case COMPARACAO_PALAVRA_FIGURAS:
                this.novoApontarFigura((ApontarFiguraFormController)controller.getChildrenController());
                break;
            case COMPARACAO_PALAVRAS_FIGURA:
                break;
            case COPIA:
                break;
            case DITADO:
                break;
            case LEITURA:
                break;
            case NOMEAR_IMAGEM:
                break;
        }*/
        return (false);
    }

    private boolean novoApontarFigura(ApontarFiguraFormController form){

        if(form.getPalavra() != null && form.getSelected().size() == 2){
            //ComparacaoFigurasPalavra nova = new ComparacaoFigurasPalavra();
            return (false);
        }
        else{
            JanelaDeMensagem.mostrarErro("Preencha os campos corretamente", "Os dados não foram inseridos corretamente.", null);
            return (false);
        }
    }
}
