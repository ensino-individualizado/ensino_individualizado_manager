package Visao.Administracao;

import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Gustavo Freitas on 15/10/2015.
 */
public class AdministracaoPreLoader extends Preloader {

    Stage preloaderStage = null;
    Scene preloaderScene = null;

    Label label = null;
    ProgressIndicator indicator = null;

    @Override
    public void handleProgressNotification(ProgressNotification info) {
        this.indicator.setProgress(info.getProgress());
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification info) {
        if(info instanceof ProgressNotification)
        this.indicator.setProgress(((ProgressNotification) info).getProgress());
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        StateChangeNotification.Type type = info.getType();
        switch (type) {
            case BEFORE_LOAD:
                // Invocou o método start. Não preciso fazer coisa alguma.
                break;
            case BEFORE_INIT:
                // Invocou o método init. Tmabém não é necessário realizar ações.
                break;
            case BEFORE_START:
                // Terminou o carregamento
                preloaderStage.close();
                break;
        }
    }

    @Override
    /**
     * Realiza a inicialização básica da janela do preloader de AdministraçãoWindow
     */
    public void init() throws Exception {

        indicator = new ProgressIndicator();
        indicator.setStyle("-fx-font-size: 16px;");

        label = new Label("Carregando aplicação...\n");
        label.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px;");

        VBox vbox = new VBox();
        vbox.getChildren().clear();
        vbox.getChildren().addAll(label, indicator);

        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: #4CAF50; -fx-spacing: 30; -fx-padding: 30 30 30 30");

        Platform.runLater(() -> {
            preloaderScene = new Scene(vbox);
            preloaderScene.setRoot(vbox);
            //preloaderScene.getStylesheets().add(
            //        AdministracaoPreLoader.class.getResource(
            //                "preloader.css").toExternalForm());
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.preloaderStage = primaryStage;
        this.preloaderStage.setScene(this.preloaderScene);
        this.preloaderStage.initStyle(StageStyle.UNDECORATED);
        this.preloaderStage.show();
    }
}
