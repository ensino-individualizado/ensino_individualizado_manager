/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

/**
 *
 * @author Gustavo Freitas
 */
public interface AcceptMessage<Type> {
    
    public void receiveMessage(Type message);
    public void sendMessage(Type message);
}
