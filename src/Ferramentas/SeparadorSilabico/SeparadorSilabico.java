package Ferramentas.SeparadorSilabico;

/**
 * Created by Gustavo Freitas on 05/05/2016.
 */
import java.util.ArrayList;

public class SeparadorSilabico
        implements Constantes
{
    public static SeparadorSilabico instance = new SeparadorSilabico();

    public static final char[] ACENTOS = new char[ACENTOS_GA.length + CIRCUNFLEXO.length + TIL.length];
    public static final char[] VOGAIS_ACENTOS = new char[VOGAIS.length + ACENTOS.length];
    public static final char[] SEMI_VOGAIS_ACENTOS = new char[SEMI_VOGAIS.length + ACENTOS.length];

    static
    {
        iniciaArraysCompostos();
    }

    public static ArrayList<String> separa(String palavra)
    {
        palavra = palavra.toLowerCase();
        if ((palavra.equals("ao")) || (palavra.equals("aos")))
        {
            ArrayList<String> separada = new ArrayList();
            separada.add(palavra);
            return separada;
        }
        ArrayList<Integer> posicoes = constroiPosicoes1(palavra);

        posicoes = constroiPosicoes2(palavra, posicoes);

        return preencheSilabas(posicoes, palavra);
    }

    public static String getSilabasString(String palavra)
    {
        return getSilabasString(separa(palavra));
    }

    public static String getSilabasString(ArrayList<String> silabas)
    {
        String ret = "";
        for (int i = 0; i < silabas.size(); i++)
        {
            ret = ret + (String)silabas.get(i);
            if (i < silabas.size() - 1) {
                ret = ret + "-";
            }
        }
        return ret;
    }

    private static void iniciaArraysCompostos()
    {
        System.arraycopy(ACENTOS_GA, 0, ACENTOS, 0, ACENTOS_GA.length);
        System.arraycopy(CIRCUNFLEXO, 0, ACENTOS, ACENTOS_GA.length, CIRCUNFLEXO.length);
        System.arraycopy(TIL, 0, ACENTOS, ACENTOS_GA.length + CIRCUNFLEXO.length, TIL.length);

        System.arraycopy(VOGAIS, 0, VOGAIS_ACENTOS, 0, VOGAIS.length);
        System.arraycopy(ACENTOS, 0, VOGAIS_ACENTOS, VOGAIS.length, ACENTOS.length);

        System.arraycopy(SEMI_VOGAIS, 0, SEMI_VOGAIS_ACENTOS, 0, SEMI_VOGAIS.length);
        System.arraycopy(ACENTOS, 0, SEMI_VOGAIS_ACENTOS, SEMI_VOGAIS.length, ACENTOS.length);
    }

    private static ArrayList<Integer> constroiPosicoes1(String palavra)
    {
        ArrayList<Integer> posicoes = new ArrayList();
        for (int i = 1; i < palavra.length(); i++) {
            if ((pertence(palavra.charAt(i), VOGAIS_ACENTOS)) && (!pertence(palavra.charAt(i - 1), VOGAIS_ACENTOS)) && (i > 1)) {
                if (((pertence(palavra.charAt(i - 1), H)) && (pertence(palavra.charAt(i - 2), CONJ_2))) || (
                        (pertence(palavra.charAt(i - 1), LATERAIS)) && (pertence(palavra.charAt(i - 2), CONJ_1)))) {
                    posicoes.add(Integer.valueOf(i - 2));
                } else {
                    posicoes.add(Integer.valueOf(i - 1));
                }
            }
        }
        if ((posicoes.size() > 0) && (((Integer)posicoes.get(0)).intValue() == 1) && (!pertence(palavra.charAt(0), VOGAIS_ACENTOS))) {
            posicoes.set(0, Integer.valueOf(0));
        }
        if ((posicoes.size() == 0) || (((Integer)posicoes.get(0)).intValue() != 0)) {
            posicoes.add(0, Integer.valueOf(0));
        }
        return posicoes;
    }

    private static ArrayList<Integer> constroiPosicoes2(String palavra, ArrayList<Integer> posicoes)
    {
        for (int i = 1; i < palavra.length(); i++) {
            if ((pertence(palavra.charAt(i), VOGAIS_ACENTOS)) && (pertence(palavra.charAt(i - 1), VOGAIS_ACENTOS))) {
                if (((i <= 1) || (palavra.charAt(i - 1) != 'u') || (!pertence(palavra.charAt(i - 2), CONJ_8))) &&
                        (!pertence(palavra.charAt(i - 1), TIL))) {
                    if ((!pertence(palavra.charAt(i), SEMI_VOGAIS)) ||

                            ((ultimaSilaba(palavra, i, posicoes)) && (pertence2(LATERAIS, palavra, i + 1))) || (

                            (pertence2(NASAIS, palavra, i + 1)) && (!pertence2(VOGAIS_ACENTOS, palavra, i + 2)))) {
                        for (int j = 0; j < posicoes.size(); j++)
                        {
                            if (((Integer)posicoes.get(j)).intValue() > i)
                            {
                                posicoes.add(j, Integer.valueOf(i));
                                break;
                            }
                            if (j == posicoes.size() - 1)
                            {
                                posicoes.add(Integer.valueOf(i));
                                break;
                            }
                        }
                    }
                }
            }
        }
        return posicoes;
    }

    public static boolean pertence(char l, char[] conjunto)
    {
        for (int i = 0; i < conjunto.length; i++) {
            if (l == conjunto[i]) {
                return true;
            }
        }
        return false;
    }

    private static boolean pertence2(char[] conjunto, String palavra, int index)
    {
        if ((index >= palavra.length()) || (index < 0)) {
            return false;
        }
        return pertence(palavra.charAt(index), conjunto);
    }

    private static boolean ultimaSilaba(String palavra, int index, ArrayList<Integer> posicoes)
    {
        return index >= ((Integer)posicoes.get(posicoes.size() - 1)).intValue();
    }

    private static ArrayList<String> preencheSilabas(ArrayList<Integer> posicoes, String palavra)
    {
        ArrayList<String> ret = new ArrayList();
        if (posicoes.size() > 0)
        {
            int i = 0;
            while (i < posicoes.size() - 1)
            {
                ret.add(palavra.substring(((Integer)posicoes.get(i)).intValue(), ((Integer)posicoes.get(i + 1)).intValue()));
                i++;
            }
            ret.add(palavra.substring(((Integer)posicoes.get(i)).intValue(), palavra.length()));
        }
        return ret;
    }

    public static int getTonicaInt(String palavra)
    {
        ArrayList<String> silabas = separa(palavra);
        return getTonicaInt(palavra, silabas);
    }

    public static int getTonicaInt(String palavra, ArrayList<String> silabas)
    {
        if ((estaEm(MONOSSILABOS_ATONOS, palavra)) || (palavra.length() == 0)) {
            return -1;
        }
        for (int i = silabas.size() - 1; i >= 0; i--)
        {
            String silaba = (String)silabas.get(i);
            for (int j = 0; j < silaba.length(); j++)
            {
                if (((pertence(silaba.charAt(j), ACENTOS_GA)) || (pertence(silaba.charAt(j), CIRCUNFLEXO))) &&
                        (i > silabas.size() - 4)) {
                    return i;
                }
                if ((pertence(silaba.charAt(j), TIL)) &&
                        (i > silabas.size() - 3)) {
                    return i;
                }
            }
        }
        char ultimo = palavra.charAt(palavra.length() - 1);
        if ((silabas.size() == 1) || (pertence(ultimo, LATERAIS))) {
            return silabas.size() - 1;
        }
        String ultima = (String)silabas.get(silabas.size() - 1);
        for (int i = 0; i < ultima.length(); i++) {
            if ((pertence(ultima.charAt(i), SEMI_VOGAIS)) && (
                    (ultima.charAt(i) != 'u') || (!pertence2(CONJ_8, ultima, i - 1)))) {
                return silabas.size() - 1;
            }
        }
        return silabas.size() - 2;
    }

    public static String getTonicaString(String palavra)
    {
        ArrayList<String> silabas = separa(palavra);
        return getTonicaString(palavra, silabas);
    }

    public static String getTonicaString(String palavra, ArrayList<String> silabas)
    {
        int i = getTonicaInt(palavra, silabas);
        return i >= 0 ? (String)silabas.get(i) : "-";
    }

    public static boolean estaEm(String[] lista, String palavra)
    {
        for (int i = 0; i < lista.length; i++) {
            if (palavra.equals(lista[i])) {
                return true;
            }
        }
        return false;
    }

    public static SeparadorSilabico getInstance(){
        return (SeparadorSilabico.instance);
    }
}
