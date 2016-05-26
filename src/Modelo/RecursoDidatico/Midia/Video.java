/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.RecursoDidatico.Midia;

/**
 *
 * @author Gustavo Freitas
 */
public class Video extends Midia{
    
    private String descricao;

    public Video(){
        
    }
    
    public Video(int id, String descricao){
        super(id);
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
