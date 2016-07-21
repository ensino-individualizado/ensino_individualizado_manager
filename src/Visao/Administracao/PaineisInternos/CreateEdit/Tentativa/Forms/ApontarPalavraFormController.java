package Visao.Administracao.PaineisInternos.CreateEdit.Tentativa.Forms;

import Modelo.RecursoDidatico.Palavra;
import Visao.Administracao.Componentes.ToggleImageButton;
import com.gustavo.utils.javafx.ControllerHierarchy.RegionController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created by Gustavo Freitas on 18/10/2015.
 */
public class ApontarPalavraFormController extends RegionController {

    private int qtdSelected = 0;
    private int selectionLimit = 1;

    private ArrayList<ToggleImageButton> selected = new ArrayList<>();

    @FXML
    private ListView<Palavra> palavras;

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
                imagens.getChildren().forEach((i)->{
                    if(!((ToggleImageButton) i).isSelected()){
                        i.setDisable(true);
                    }
                });
            }

        }
    }

    public Palavra getPalavra() {
        return palavras.getSelectionModel().getSelectedItem();
    }

    public void setPalavras(Collection<Palavra> palavra) {
        this.palavras.getItems().clear();
        this.palavras.getItems().addAll(palavra);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
