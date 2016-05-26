package Visao.Administracao.PaineisInternos.CreateEdit.Tentativa;

import Controle.AdministracaoMainController;
import Visao.Administracao.Componentes.SalvarCancelar.SalvarCancelarController;
import Visao.ControllerHierarchy.RegionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Gustavo Freitas on 17/10/2015.
 */
public class TentativaController extends RegionController {

    private Runnable afterSave = null;
    private Runnable afterCancel = null;

    //@FXML
    //private ComboBox<Tentativa.TENTATIVA_TYPE> tipos;

    @FXML
    private AnchorPane pane;

    @FXML
    private SalvarCancelarController salvarCancelarController;

    @FXML
    public void swapPane(ActionEvent event){
        //AdministracaoMainController.getInstance().getTentativaManager().swapPane(this);
        /*
        try {

            this.childrenController = RegionLoader.getInstance().load(this, FxmlReference.getReference("novo_tentativa_" + tipos.getSelectionModel().getSelectedItem()));
            this.pane.getChildren().clear();
            this.pane.getChildren().add(this.childrenController.getRegion());
            Tentativa.TENTATIVA_TYPE tipo = tipos.getSelectionModel().getSelectedItem();
            switch (tipo){
                case COMPARACAO_PALAVRA_FIGURAS: {
                    ApontarFiguraFormController form = ((ApontarFiguraFormController) this.getChildrenController());
                    Platform.runLater(() -> {
                        form.setPalavras(PalavraDAO.getInstance().obterTodos());
                        Collection<Imagem> allImgs = ImagemDAO.getInstance().obterTodos();
                        for (Imagem img : allImgs) {
                            ImagemDAO.getInstance().finalizarCarregamento(img, FileSaver.getInstance().getTmpFolderLocation());
                            ToggleImageButton button = new ToggleImageButton(img.getLocal(), "", 100, 100);
                            form.addImage(button);
                        }
                    });
                    break;
                }

                case COMPARACAO_PALAVRAS_FIGURA: {
                    ApontarPalavraFormController form = ((ApontarPalavraFormController) this.getChildrenController());
                    Platform.runLater(() -> {
                        form.setPalavras(PalavraDAO.getInstance().obterTodos());
                        Collection<Imagem> allImgs = ImagemDAO.getInstance().obterTodos();
                        for (Imagem img : allImgs) {
                            ImagemDAO.getInstance().finalizarCarregamento(img, FileSaver.getInstance().getTmpFolderLocation());
                            ToggleImageButton button = new ToggleImageButton(img.getLocal(), "", 100, 100);
                            form.addImage(button);
                        }
                    });
                    break;
                }
                case COPIA: {
                    CopiaController form = ((CopiaController) this.getChildrenController());
                    Platform.runLater(() -> {
                        form.setPalavras(PalavraDAO.getInstance().obterTodos());
                    });
                    break;
                }
                case DITADO: {
                    DitadoController form = ((DitadoController) this.getChildrenController());
                    Platform.runLater(() -> {
                        form.setPalavras(PalavraDAO.getInstance().obterTodos());
                    });
                    break;
                }
                case LEITURA: {
                    LeituraController form = ((LeituraController) this.getChildrenController());
                    Platform.runLater(() -> {
                        form.setPalavras(PalavraDAO.getInstance().obterTodos());
                    });
                    break;
                }
                case NOMEAR_IMAGEM: {
                    NomearImagemController form = ((NomearImagemController) this.getChildrenController());
                    Platform.runLater(() -> {
                        Collection<Imagem> allImgs = ImagemDAO.getInstance().obterTodos();
                        for (Imagem img : allImgs) {
                            ImagemDAO.getInstance().finalizarCarregamento(img, FileSaver.getInstance().getTmpFolderLocation());
                            ToggleImageButton button = new ToggleImageButton(img.getLocal(), "", 100, 100);
                            form.addImage(button);
                        }
                    });
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        */

    }

    private void limparFormulario() {
        //this.formularioController.limparDados();
    }

    public void salvar(){
        AdministracaoMainController.getInstance().getTentativaManager().createUpdate(this);
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
        //Define o que os bot�es ir�o fazer
        this.salvarCancelarController.setOnAction(this::salvar, this::cancelar);
        /*
        ArrayList<Tentativa.TENTATIVA_TYPE> tipos = new ArrayList<>();
        tipos.add(Tentativa.TENTATIVA_TYPE.COMPARACAO_PALAVRA_FIGURAS);
        tipos.add(Tentativa.TENTATIVA_TYPE.COMPARACAO_PALAVRAS_FIGURA);
        tipos.add(Tentativa.TENTATIVA_TYPE.COPIA);
        tipos.add(Tentativa.TENTATIVA_TYPE.DITADO);
        tipos.add(Tentativa.TENTATIVA_TYPE.LEITURA);
        tipos.add(Tentativa.TENTATIVA_TYPE.NOMEAR_IMAGEM);

        this.tipos.getItems().clear();
        this.tipos.getItems().addAll(tipos);
        */
    }

    //public Tentativa.TENTATIVA_TYPE getTipoSelecionado() {
    //    return (this.tipos.getSelectionModel().getSelectedItem());
    //}
}
