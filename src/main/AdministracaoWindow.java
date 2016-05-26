/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Controle.AdministracaoMainController;
import DAO.PlayListDAO;
import Ferramentas.FileSaver;
import Ferramentas.GerenciadorBD;
import Modelo.RecursoDidatico.PlayList.PlayList;
import Visao.ControllerHierarchy.WindowController;
import Visao.Mensagens.JanelaDeMensagem;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Gustavo Freitas
 */
public class AdministracaoWindow extends WindowController{

    AdministracaoMainController administracaoMainController = null;

    @Override
    public void init() throws Exception {
        AdministracaoMainController.getInstance().startApplication(this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        super.start(primaryStage, "/Visao/Administracao/AdministracaoFXML.fxml", "Administração");

        getStage().getIcons().clear();
        getStage().getIcons().add(new Image("/resources/icons/mainIcon.png"));
        getStage().setWidth(1000);
        super.show();
    }

    @Override
    public void stop(){
        GerenciadorBD.fecharConexao();
        FileSaver.getInstance().deleteTmpFolder();
        System.out.println("Aplicação finalizada.");
    }
}
