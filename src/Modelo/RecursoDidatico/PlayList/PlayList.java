/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.RecursoDidatico.PlayList;

import Modelo.RecursoDidatico.Midia.Midia;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;

/**
 *
 * @author Gustavo Freitas
 */
public class PlayList{

    public static enum TYPE {
        AUDIO(1, "PlayList de audio."), VIDEO(2, "PlayList de video");

        int id;
        String descricao;

        TYPE(int id, String descricao) {
            this.id = id;
            this.descricao = descricao;
        }

        public int getId() {
            return id;
        }

        public String getDescricao() {
            return descricao;
        }
    };

    private int id = -1;

    private TYPE tipo;
    private MediaPlayer player = null;
    private ArrayList<Midia> midias = new ArrayList<>();

    public PlayList(TYPE tipo){
        this.tipo = tipo;
    }
    
    public PlayList(TYPE tipo, ArrayList<Midia> midias){
        this.tipo = tipo;
        this.midias.addAll(midias);
    }
    
    public PlayList(TYPE tipo, Midia ... midias){
        this.tipo = tipo;
        for(Midia midia : midias){
            this.midias.add(midia);
        }
    }
    
    public void play(){

    }
    
    public void pause(){

    }
    
    public void stop(){

    }
    
    public double getTempoCompleto(){
        return (-1);
    }
    
    public ArrayList<Midia> getMidias(){
        return (this.midias);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TYPE getTipo() {
        return tipo;
    }

    public void setTipo(TYPE tipo) {
        this.tipo = tipo;
    }
}
