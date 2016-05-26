package Ferramentas.SeparadorSilabico;

import Modelo.RecursoDidatico.Silaba;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Gustavo Freitas on 05/05/2016.
 */
public class SeparadorSilabicoAdapter {

    private SeparadorSilabico separador = SeparadorSilabico.getInstance();

    private static SeparadorSilabicoAdapter instance = new SeparadorSilabicoAdapter();

    public Collection<Silaba> separar(String palavra){
        Collection<Silaba> silabas = new ArrayList<Silaba>();

        for(String str : separador.separa(palavra)){
            silabas.add(new Silaba(str));
        }

        return (silabas);
    }

    public static SeparadorSilabicoAdapter getInstance() {
        return instance;
    }
}
