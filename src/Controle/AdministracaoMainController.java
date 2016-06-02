/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Controle.SubControllers.*;
import Ferramentas.Configuracao;
import Ferramentas.FileSaver;
import Ferramentas.GerenciadorBD;
import Visao.Administracao.AdministracaoController;
import Visao.Administracao.FxmlReference;
import Visao.ControllerHierarchy.RegionController;
import Visao.Mensagens.JanelaDeMensagem;
import Visao.RegionLoader;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Platform;
import javafx.application.Preloader;
import main.AdministracaoWindow;

import static DAO.mysql.generatedclasses.tables.Audio.AUDIO;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Gustavo Freitas
 */
public class AdministracaoMainController {

    private AdministracaoWindow windowController = null;
    private static AdministracaoMainController administracaoMainController = new AdministracaoMainController();

    private AlunoManager alunoManager = null;
    private TurmaManager turmaManager = null;
    private PalavraManager palavraManager = null;
    private TentativaManager tentativaManager = null;
    private AvaliacaoManager avaliacaoManager = null;

    public boolean startApplication(AdministracaoWindow aplicacao){

        boolean error = false;

        this.windowController = aplicacao;

        LauncherImpl.notifyPreloader(aplicacao, new Preloader.ProgressNotification(0.0));

        //Carrega as informações do BD
        try {
            Configuracao.carregarDadosDoBanco();
        }
        catch (FileNotFoundException FNFE){
            FNFE.printStackTrace();
            JOptionPane.showMessageDialog(null, "Arquivo de configurações do banco de dados não encontrado. \n" + FNFE, "Erro", JOptionPane.ERROR_MESSAGE);
            Platform.exit();
        }
        catch(IOException IOE){
            IOE.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao abir o arquivo de configurações. \n" + IOE, "Erro", JOptionPane.ERROR_MESSAGE);
            Platform.exit();
        }

        LauncherImpl.notifyPreloader(aplicacao, new Preloader.ProgressNotification(0.30));

        //Tenta se conectar com o bd
        try {
            GerenciadorBD.conectar();
            if(GerenciadorBD.getContext().selectFrom(AUDIO).where(AUDIO.IDAUDIO.eq(1)).fetchOne() == null) {
                Thread write = new Thread(() -> {
                    FileSaver.getInstance().gravarEnunciadosBanco();
                });
                write.start();
            }
        }
        catch (SQLException SQLE) {
            //JanelaDeMensagem.mostrarExcessao("Erro ao se conectar com o BD", "Erro ao se conectar com o banco de dados.", SQLE);
            SQLE.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados. \n" + SQLE, "Erro", JOptionPane.ERROR_MESSAGE);
            Platform.exit();
        }
        catch (ClassNotFoundException CNF) {
            CNF.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar driver de conexão com o banco de dados. \n" + CNF, "Erro", JOptionPane.ERROR_MESSAGE);
            Platform.exit();
        }

        LauncherImpl.notifyPreloader(aplicacao, new Preloader.ProgressNotification(0.60));

        //Carrega as fontes do sistema...
        Configuracao.carregarFontes();
        //if(!Configuracao.carregarFontes()){
           // JanelaDeMensagem.mostrarErro("Erro ao carregar fontes", "Ocorreu um erro ao carregar as fontes utilizadas nos textos da aplica��o.",
            //                             "A aplica��o ser� iniciada normalmente, mas poder� apresentar problemas na disposi��o dos componentes.");
        //}

        LauncherImpl.notifyPreloader(aplicacao, new Preloader.ProgressNotification(1.0));

        return (error);
    }

    public boolean loadPane(AdministracaoController controller, String id){
        try{
            RegionController regionController = RegionLoader.getInstance().load(controller, FxmlReference.getReference(id));

            controller.getPane().getChildren().clear();
            controller.getPane().getChildren().add(regionController.getRegion());
            controller.setChildrenController(regionController);

            regionController.setFatherController(controller);
            regionController.setWindowController(this.windowController);
        }
        catch (IOException | NullPointerException e){
            JanelaDeMensagem.mostrarExcessao("Erro ao carregar painel",
                    "Ocorreu um erro ao carregar o painel requisitado.", e);
            return (false);
        }

        return (true);
    }

    public AlunoManager getAlunoManager() {
        if(this.alunoManager == null){
            this.alunoManager = new AlunoManager();
        }
        return alunoManager;
    }

    public PalavraManager getPalavraManager() {
        if(this.palavraManager == null){
            this.palavraManager = new PalavraManager();
        }
        return palavraManager;
    }

    public TurmaManager getTurmaManager() {
        if(this.turmaManager == null){
            this.turmaManager = new TurmaManager();
        }
        return turmaManager;
    }

    public TentativaManager getTentativaManager() {
        if(this.tentativaManager == null){
            this.tentativaManager = new TentativaManager();
        }
        return tentativaManager;
    }

    public AvaliacaoManager getAvaliacaoManager() {
        if(this.avaliacaoManager == null){
            this.avaliacaoManager = new AvaliacaoManager();
        }
        return avaliacaoManager;
    }

    public static AdministracaoMainController getInstance() {
        return (administracaoMainController);
    }

    public AdministracaoWindow getWindowController() {
        return windowController;
    }
}
