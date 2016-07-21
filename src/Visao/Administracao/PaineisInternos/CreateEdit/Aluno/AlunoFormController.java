/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.Administracao.PaineisInternos.CreateEdit.Aluno;

import Ferramentas.DateParser;
import Modelo.Turma;
import com.gustavo.utils.javafx.ControllerHierarchy.RegionController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Gustavo Freitas
 */
public class AlunoFormController extends RegionController {

    Integer id = -1;

    @FXML
    private TextField nome;
    
    @FXML
    private DatePicker data_nascimento;
    
    @FXML
    private ComboBox<Turma> turmas;

    public void limparDados() {
        this.nome.clear();
        this.data_nascimento.setValue(null);
        this.turmas.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Impede a seleção de uma data "maior" que a atual
        final Callback<DatePicker, DateCell> dayCellFactory = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                           
                            if (item.isAfter(LocalDate.now())){ 
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                            }   
                    }
                };
            }
        };
        this.data_nascimento.setDayCellFactory(dayCellFactory);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome.getText();
    }

    public void setNome(String nome) {
        this.nome.setText(nome);
    }

    public Date getDataNascimento() {
        if(this.data_nascimento.getValue() != null) {
            return DateParser.toDate(this.data_nascimento.getValue());
        }
        else{
            return (null);
        }
    }

    public void setDataNascimento(Date data) {
        this.data_nascimento.setValue(DateParser.toLocalDate(data));
    }

    public Turma getTurma() {
        return this.turmas.getValue();
    }

    public void setTurmas(Collection<Turma> turmas) {
        this.turmas.getItems().clear();
        this.turmas.getItems().addAll(turmas);
    }

    public ComboBox<Turma> getTurmas() {
        return turmas;
    }
}
