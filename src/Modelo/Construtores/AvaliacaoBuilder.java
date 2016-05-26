package Modelo.Construtores;

import Modelo.*;
import Modelo.RecursoDidatico.Midia.Audio;
import Modelo.RecursoDidatico.Palavra;
import Modelo.RecursoDidatico.PlayList.PlayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Gustavo Freitas on 01/11/2015.
 */
public class AvaliacaoBuilder {

    private Avaliacao avaliacao = new Avaliacao();

    public void addBloco(Bloco novo){
        this.avaliacao.addBloco(novo);
    }

    public void addBloco(Bloco.TYPE tipo, LinkedList<Tentativa> tentativas){
        avaliacao.addBloco(new Bloco(tipo, tentativas));
    }

    public void addBloco(Bloco.TYPE tipo, ArrayList<Palavra> palavras){

        Bloco novo = null;

        switch (tipo){
            case APONTAR_PALAVRA:
            case APONTAR_FIGURA:
            case COMPARACAO_PALAVRA_FIGURAS:
            case COMPARACAO_PALAVRAS_FIGURA:
                novo = this.novoBlocoComparacao(tipo, palavras);
                break;
            default:
                novo = this.novoBlocoOutro(tipo, palavras);
        }

        this.avaliacao.addBloco(novo);
    }

    public Bloco novoBlocoComparacao(Bloco.TYPE tipo, ArrayList<Palavra> palavras){

        Bloco novo = new Bloco(tipo);

        Random rand = new Random();

        for(Palavra resposta : palavras){

            Palavra outra1, outra2;

            //Sorteia as duas outras alternativas
            do{
                outra1 = palavras.get(rand.nextInt(palavras.size()));
                outra2 = palavras.get(rand.nextInt(palavras.size()));
            }while (outra1 == outra2 || outra1 == resposta || outra2 == resposta);

            //Monta o bloco
            Collection<Alternativa> alternativas = new ArrayList<>();
            alternativas.add(new Alternativa(false, outra1));
            alternativas.add(new Alternativa(false, outra2));
            alternativas.add(new Alternativa(true, resposta));

            Audio inicio = new Audio();
            inicio.setId(tipo.getId());

            PlayList enunciado = null;
            System.out.println("Palavra: " + resposta.getPalavra() + "\nTipo Bloco: " + tipo.getDescricao());
            System.out.println("Inicio: " + inicio);
            //Apontar palavra e apontar figura necessitam de um tratamento especial
            if(tipo.equals(Bloco.TYPE.APONTAR_PALAVRA) || tipo.equals(Bloco.TYPE.APONTAR_FIGURA)){
                System.out.println("Restante: " + resposta.getAudio() + "\n");
                enunciado = new PlayList(PlayList.TYPE.AUDIO, inicio, resposta.getAudio());
            }
            else {
                enunciado = new PlayList(PlayList.TYPE.AUDIO, inicio);
            }

            novo.addTentativa(new Tentativa(alternativas, new Enunciado("", enunciado)));
        }

        return (novo);
    }

    public Bloco novoBlocoOutro(Bloco.TYPE tipo, Collection<Palavra> palavras){
        Bloco novo = new Bloco(tipo);

        for(Palavra palavra : palavras){
            Alternativa alternativa = new Alternativa(true, palavra);

            Audio inicio = new Audio();
            inicio.setId(tipo.getId());

            Enunciado enunciado;

            if(tipo == Bloco.TYPE.DITADO) {
                enunciado = new Enunciado("", new PlayList(PlayList.TYPE.AUDIO, inicio, palavra.getAudio()));
            }
            else{
                enunciado = new Enunciado("", new PlayList(PlayList.TYPE.AUDIO, inicio));
            }

            novo.addTentativa(new Tentativa(alternativa, enunciado));
        }
        return (novo);
    }

    public void setDescricao(String descricao){
        this.avaliacao.setDescricao(descricao);
    }

    public void setId(int id){
        this.avaliacao.setId(id);
    }

    public Avaliacao getInstance(){
        Avaliacao tmp = this.avaliacao;
        this.avaliacao = new Avaliacao();
        return (tmp);
    }

}
