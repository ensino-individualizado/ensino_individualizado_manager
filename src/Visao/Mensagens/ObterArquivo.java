/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.Mensagens;

import Ferramentas.FileSaver;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 *
 * @author Gustavo Freitas
 */
public class ObterArquivo {

    public static String obterArquivo(Stage palco){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(FileSaver.getLastDirectoryOpen()));
        File arquivo = fileChooser.showOpenDialog(palco);
        if(arquivo != null) {
            FileSaver.setLastDirectoryOpen(arquivo.getParent());
            return (arquivo.getAbsolutePath());
        }

        return (null);
    }
    
    public static String obterImagem(Stage palco){
        
        File arquivo;
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Todos os arquivos", "*.*"),
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        fileChooser.setInitialDirectory(new File(FileSaver.getLastDirectoryOpen()));
        arquivo = fileChooser.showOpenDialog(palco);
        if(arquivo != null) {
            FileSaver.setLastDirectoryOpen(arquivo.getParent());
            return (arquivo.getAbsolutePath());
        }

        return (null);
    }
    
    public static String obterVideo(Stage palco){
        
        File arquivo;
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Todos os arquivos", "*.*"),
            new FileChooser.ExtensionFilter("AVI", "*.avi"),
            new FileChooser.ExtensionFilter("FLV", "*.flv"),
            new FileChooser.ExtensionFilter("MP4", "*.mp4"),
            new FileChooser.ExtensionFilter("MPG", "*.mpg"),
            new FileChooser.ExtensionFilter("WMV", "*.wmv")
        );

        fileChooser.setInitialDirectory(new File(FileSaver.getLastDirectoryOpen()));
        arquivo = fileChooser.showOpenDialog(palco);
        if(arquivo != null) {
            FileSaver.setLastDirectoryOpen(arquivo.getParent());
            return (arquivo.getAbsolutePath());
        }

        return (null);
    }

    public static String obterAudio(Stage palco) {
        
        File arquivo;
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Todos os arquivos", "*.*"),
            new FileChooser.ExtensionFilter("MP3", "*.mp3"),
            new FileChooser.ExtensionFilter("WMA", "*.wma")
        );

        fileChooser.setInitialDirectory(new File(FileSaver.getLastDirectoryOpen()));
        arquivo = fileChooser.showOpenDialog(palco);
        if(arquivo != null) {
            FileSaver.setLastDirectoryOpen(arquivo.getParent());
            return (arquivo.getAbsolutePath());
        }

        return (null);
    }
}
