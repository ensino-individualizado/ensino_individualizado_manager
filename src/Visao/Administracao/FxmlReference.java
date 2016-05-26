/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.Administracao;

import java.util.HashMap;

/**
 *
 * @author Gustavo Freitas
 */
public class FxmlReference {
    
    private static final HashMap<String, String> locationMap = new HashMap<>();

    static{
        locationMap.put("novo_aluno", "/Visao/Administracao/PaineisInternos/CreateEdit/Aluno/Aluno.fxml");
        locationMap.put("novo_avaliacao", "/Visao/Administracao/PaineisInternos/CreateEdit/Avaliacao/Avaliacao.fxml");
        locationMap.put("novo_bloco", "/Visao/Administracao/PaineisInternos/CreateEdit/Bloco/Bloco.fxml");
        locationMap.put("novo_palavra", "/Visao/Administracao/PaineisInternos/CreateEdit/Palavra/Palavra.fxml");

        locationMap.put("novo_tentativa", "/Visao/Administracao/PaineisInternos/CreateEdit/Tentativa/Tentativa.fxml");
        /*
        locationMap.put("novo_tentativa_" + Tentativa.TENTATIVA_TYPE.COMPARACAO_PALAVRA_FIGURAS, "/Visao/Administracao/PaineisInternos/CreateEdit/Tentativa/Forms/ApontarFiguraForm.fxml");
        locationMap.put("novo_tentativa_" + Tentativa.TENTATIVA_TYPE.COMPARACAO_PALAVRAS_FIGURA, "/Visao/Administracao/PaineisInternos/CreateEdit/Tentativa/Forms/ApontarPalavraForm.fxml");
        locationMap.put("novo_tentativa_" + Tentativa.TENTATIVA_TYPE.COPIA, "/Visao/Administracao/PaineisInternos/CreateEdit/Tentativa/Forms/Copia.fxml");
        locationMap.put("novo_tentativa_" + Tentativa.TENTATIVA_TYPE.DITADO, "/Visao/Administracao/PaineisInternos/CreateEdit/Tentativa/Forms/Ditado.fxml");
        locationMap.put("novo_tentativa_" + Tentativa.TENTATIVA_TYPE.LEITURA, "/Visao/Administracao/PaineisInternos/CreateEdit/Tentativa/Forms/Leitura.fxml");
        locationMap.put("novo_tentativa_" + Tentativa.TENTATIVA_TYPE.NOMEAR_IMAGEM, "/Visao/Administracao/PaineisInternos/CreateEdit/Tentativa/Forms/NomearImagem.fxml");
        */
        locationMap.put("novo_treino", "/Visao/Administracao/PaineisInternos/CreateEdit/Treino/Treino.fxml");
        locationMap.put("novo_turma", "/Visao/Administracao/PaineisInternos/CreateEdit/Turma/Turma.fxml");

        locationMap.put("visualizar_alunos", "/Visao/Administracao/PaineisInternos/Visualizar/Alunos/Aluno.fxml");
        locationMap.put("visualizar_avaliacoes", "/Visao/Administracao/PaineisInternos/Visualizar/Avaliacao/Avaliacao.fxml");
        locationMap.put("visualizar_blocos", "/Visao/Administracao/PaineisInternos/Visualizar/Bloco/Bloco.fxml");
        locationMap.put("visualizar_palavras", "/Visao/Administracao/PaineisInternos/Visualizar/Palavras/Palavras.fxml");
        locationMap.put("visualizar_tentativas", "/Visao/Administracao/PaineisInternos/Visualizar/Tentativa/Tentativa.fxml");
        locationMap.put("visualizar_treinos", "/Visao/Administracao/PaineisInternos/Visualizar/Treino/Treino.fxml");
        locationMap.put("visualizar_turmas", "/Visao/Administracao/PaineisInternos/Visualizar/Turma/Turmas.fxml");

        locationMap.put("visualizar_palavras_info", "/Visao/Administracao/PaineisInternos/Visualizar/Palavras/PalavraInfo.fxml");
        locationMap.put("visualizar_alunos_info", "/Visao/Administracao/PaineisInternos/Visualizar/Alunos/AlunoInfo.fxml");
        locationMap.put("simple_media_player", "/Visao/Dialog/SimpleMediaPlayerDialog/SimpleMediaPlayer.fxml");
    }

    public static String getReference(String id){
        return (locationMap.get(id));
    }
}
