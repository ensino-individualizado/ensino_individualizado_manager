/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.Administracao.Componentes.ToolBar;

import Visao.ControllerHierarchy.RegionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Gustavo Freitas
 */
public class ToolBar extends RegionController{

    @FXML
    private TextField filtro;

    @FXML
    private Button filtrar;

    @FXML
    private Button apagar;

    @FXML
    private Button editar;

    @FXML
    private Button info;

    @FXML
    public void applyFilter(ActionEvent evento){
        ((RegionWithToollbarEmbeded)this.getFatherController()).filter(this.filtro.getText());
    }
    
    @FXML
    public void delete(ActionEvent evento){
        ((RegionWithToollbarEmbeded)this.getFatherController()).delete();
    }
    
    @FXML
    public void edit(ActionEvent evento){
        ((RegionWithToollbarEmbeded)this.getFatherController()).edit();
    }

    @FXML
    public void info(ActionEvent evento) {
        ((RegionWithToollbarEmbeded)this.getFatherController()).info();
    }

    public void filterSupport(boolean support){
        this.filtrar.setDisable(!support);
        this.filtro.setDisable(!support);
    }

    public void deleteSupport(boolean support){
        this.apagar.setDisable(!support);
    }

    public void editSupport(boolean support){
        this.editar.setDisable(!support);
    }

    public void infoSupport(boolean support){
        this.info.setDisable(!support);
    }

    public void setSupported(boolean filter, boolean delete, boolean edit, boolean info){
        this.filterSupport(filter);
        this.deleteSupport(delete);
        this.editSupport(edit);
        this.infoSupport(info);
    }

    public String getFiltro(){
        return (this.filtro.getText());
    } 
    
    public void setFiltro(String filtro){
        this.filtro.setText(filtro);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.setSupported(false, false, false, false);
    }


}
