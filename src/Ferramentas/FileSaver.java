/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ferramentas;

import DAO.AudioDAO;
import Modelo.Bloco;
import Modelo.RecursoDidatico.Midia.Audio;

import java.io.*;

/**
 *
 * @author Gustavo Freitas
 */
public class FileSaver {

    private static String lastDirectoryOpen = null;

    public static final int BUFFER_TAMANHO_PADRAO = 2048;
    
    private static FileSaver saver = new FileSaver();
    
    public static FileSaver getInstance(){
        return (FileSaver.saver);
    }

    public void save(InputStream entrada, String destino, int tamanhoBuffer) throws FileNotFoundException, IOException{

        BufferedInputStream is = new BufferedInputStream(entrada);
        FileOutputStream fos = new FileOutputStream(new File(destino));

        byte[] buffer = new byte[tamanhoBuffer];
        int r = 0;
        while((r = is.read(buffer))!=-1) {
            fos.write(buffer, 0, r);
        }
        fos.flush();
        fos.close();
        is.close();
    }

    public String getTmpFolderLocation(){
        if(!(new File(System.getProperty("user.dir") + "\\tmp\\").exists())){
            new File(System.getProperty("user.dir") + "\\tmp\\").mkdir();
        }
        return (System.getProperty("user.dir") + "\\tmp\\");
    }

    public void deleteTmpFolder(){
        if(new File(System.getProperty("user.dir") + "\\tmp").exists()){
            if(new File(System.getProperty("user.dir") + "\\tmp").delete()){
                System.out.println("Dados temporários apagados com sucesso.");
            }
            else{
                System.out.println("Os dados temporários não foram apagados.");
            }
        }
        else{
            System.out.println("Não existem dados temporários para apagar.");
        }
    }

    public String getEnunciado(Bloco.TYPE tipo){

        String local = System.getProperty("user.dir") + "\\enunciados_tipo\\";

        switch (tipo){
            case APONTAR_PALAVRA:
            case APONTAR_FIGURA:
                local = local.concat("aponte.mp4");
                break;
            case COMPARACAO_PALAVRA_FIGURAS:
                local = local.concat("aponte_a_figura.mp4");
                break;
            case COMPARACAO_PALAVRAS_FIGURA:
                local = local.concat("aponte_a_palavra.mp4");
                break;
            case COPIA:
                local = local.concat("escreva_igual.mp4");
                break;
            case DITADO:
                local = local.concat("escreva.mp4");
                break;
            case LEITURA:
                local = local.concat("que_palavra_e_essa.mp4");
                break;
            case NOMEAR_IMAGEM:
                local = local.concat("que_figura_e_essa.mp4");
                break;
        }

        return (local);
    }

    public void gravarEnunciadosBanco(){

        Audio novo = new Audio(1, "Aponte");
        novo.setLocal(getEnunciado(Bloco.TYPE.APONTAR_PALAVRA));
        AudioDAO.getInstance().novo(novo);

        novo = new Audio(2, "Aponte");
        novo.setLocal(getEnunciado(Bloco.TYPE.APONTAR_FIGURA));
        AudioDAO.getInstance().novo(novo);

        novo = new Audio(3, "Aponte a palavra");
        novo.setLocal(getEnunciado(Bloco.TYPE.COMPARACAO_PALAVRAS_FIGURA));
        AudioDAO.getInstance().novo(novo);

        novo = new Audio(4, "Aponte a figura");
        novo.setLocal(getEnunciado(Bloco.TYPE.COMPARACAO_PALAVRA_FIGURAS));
        AudioDAO.getInstance().novo(novo);

        novo = new Audio(5, "Escreva igual");
        novo.setLocal(getEnunciado(Bloco.TYPE.COPIA));
        AudioDAO.getInstance().novo(novo);

        novo = new Audio(6, "Escreva");
        novo.setLocal(getEnunciado(Bloco.TYPE.DITADO));
        AudioDAO.getInstance().novo(novo);

        novo = new Audio(7, "Que palavra é essa?");
        novo.setLocal(getEnunciado(Bloco.TYPE.LEITURA));
        AudioDAO.getInstance().novo(novo);

        novo = new Audio(8, "Que figura é essa?");
        novo.setLocal(getEnunciado(Bloco.TYPE.NOMEAR_IMAGEM));
        AudioDAO.getInstance().novo(novo);
    }

    public static String getLastDirectoryOpen() {
        if(lastDirectoryOpen == null){
            lastDirectoryOpen = System.getProperty("user.dir");
        }
        return lastDirectoryOpen;
    }

    public static void setLastDirectoryOpen(String lastDirectoryOpen) {
        FileSaver.lastDirectoryOpen = lastDirectoryOpen;
    }
}
