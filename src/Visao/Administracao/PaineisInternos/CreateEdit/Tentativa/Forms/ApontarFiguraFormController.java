package Visao.Administracao.PaineisInternos.CreateEdit.Tentativa.Forms;

import Modelo.RecursoDidatico.Palavra;
import Visao.Administracao.Componentes.ToggleImageButton;
import Visao.ControllerHierarchy.RegionController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created by Gustavo Freitas on 17/10/2015.
 */
public class ApontarFiguraFormController extends RegionController {

    private int qtdSelected = 0;
    private int selectionLimit = 2;

    private ArrayList<ToggleImageButton> selected = new ArrayList<>();

    @FXML
    private ListView<Palavra> palavra;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private FlowPane imagens;

    public void imageSelected(ToggleImageButton button){
        if(this.selected.contains(button)){
            this.selected.remove(button);
            this.qtdSelected--;
            imagens.getChildren().forEach((i)->{
                i.setDisable(false);
            });
        }
        else{
            if(this.selectionLimit == this.qtdSelected){
                button.setSelected(false);
            }
            else{
                this.selected.add(button);
                this.qtdSelected++;
            }

            if(this.selectionLimit == this.qtdSelected){
                imagens.getChildren().forEach((i)->{
                    if(!((ToggleImageButton) i).isSelected()){
                        i.setDisable(true);
                    }
                });
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public Palavra getPalavra() {
        return palavra.getSelectionModel().getSelectedItem();
    }

    public void setPalavras(Collection<Palavra> palavra) {
        this.palavra.getItems().clear();
        this.palavra.getItems().addAll(palavra);
    }

    public Collection<ToggleImageButton> getSelected(){
        return (this.selected);
    }

    public void addImage(ToggleImageButton image) {
        this.imagens.getChildren().add(image);
        image.setOnAction(event -> {
            imageSelected(image);
        });
        this.imagens.setHgap(20);
        this.imagens.setVgap(20);
    }
}
