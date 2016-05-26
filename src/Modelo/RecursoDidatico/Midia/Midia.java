/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.RecursoDidatico.Midia;

import Modelo.RecursoDidatico.RecursoDidatico;

/**
 *
 * @author Gustavo Freitas
 */
public abstract class Midia implements RecursoDidatico{
    
    protected int id = -1;
    protected String local;
    protected String nomeOriginal;
    
    public Midia(){
        
    }
    
    public Midia(int id){
        this.id = id;
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
