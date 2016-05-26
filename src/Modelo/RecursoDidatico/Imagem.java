/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.RecursoDidatico;

/**
 *
 * @author Gustavo Freitas
 */
public class Imagem implements RecursoDidatico{
    
    private int id = -1;
    private String local;
    private String nomeOriginal;
    
    public Imagem(){
    }
    
    public Imagem(int id, String nomeOriginal){
        this.id = id;
        this.nomeOriginal = nomeOriginal;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getNomeOriginal() {
        return nomeOriginal;
    }

    public void setNomeOriginal(String nomeOriginal) {
        this.nomeOriginal = nomeOriginal;
    }
}
