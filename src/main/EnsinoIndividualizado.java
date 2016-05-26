/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Visao.Administracao.AdministracaoPreLoader;
import com.sun.javafx.application.LauncherImpl;

/**
 *
 * @author Gustavo Freitas
 */
public class EnsinoIndividualizado {

    public static void main(String[] args){
        //Start the application
        LauncherImpl.launchApplication(AdministracaoWindow.class, AdministracaoPreLoader.class, args);

    }

}
