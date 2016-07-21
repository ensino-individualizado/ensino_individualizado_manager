package Visao.Administracao.PaineisInternos.Visualizar.Alunos;

import Ferramentas.DateParser;
import Modelo.Aluno;
import Modelo.Avaliacao;
import com.gustavo.utils.javafx.ControllerHierarchy.RegionController;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Created by Gustavo Freitas on 11/04/2016.
 */
public class AlunoInfoController extends RegionController {

    Aluno referencia = null;
//TODO: Terminar essa merda
    //-------------------------- Aba "Dados Pessoais" -----------------------------------
    @FXML
    private TextField nome;
    @FXML
    private DatePicker nascimento;

    //-------------------------- Aba "Avaliações Realizadas" -----------------------------------
    @FXML
    private TableView<Avaliacao> avaliacoes;
    @FXML
    private TableColumn coluna_id;
    @FXML
    private TableColumn coluna_descricao;
    @FXML
    private TableColumn coluna_erros;
    @FXML
    private TableColumn coluna_acertos;
    @FXML
    private TableColumn coluna_total;

    //-------------------------- Métodos -----------------------------------
    public void setReferencia(Aluno aluno){
        this.referencia = aluno;
        this.nome.setText(aluno.getNome());
        this.nascimento.setValue(DateParser.toLocalDate(aluno.getDataNascimento()));
    }

    public String getNome(){
        return (this.nome.getText());
    }

    public LocalDate getNascimento(){
        return (this.nascimento.getValue());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
