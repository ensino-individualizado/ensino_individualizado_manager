/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.Dialog;

import Visao.ControllerHierarchy.RegionController;
import Visao.ControllerHierarchy.WindowController;
import Visao.RegionLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;

import java.io.IOException;

/**
 *
 * @author Gustavo Freitas
 */
public class SimpleDialog extends Dialog{

    private RegionController controller;

    public SimpleDialog(WindowController parent, String title, String header, String src) throws IOException {

        this.setTitle(title);
        this.setHeaderText(header);

        this.controller = RegionLoader.getInstance().load(parent, src);
        this.getDialogPane().setStyle("-fx-padding: 0");

        this.getDialogPane().setContent(controller.getRegion());
        this.getDialogPane().setPadding(Insets.EMPTY);
        this.getDialogPane().getContent().setStyle("-fx-background-color: #FFFFFF");


        this.getDialogPane().getScene().getWindow().setOnCloseRequest(event -> {
            close();
        });
    }

    public RegionController getController() {
        return controller;
    }
}
