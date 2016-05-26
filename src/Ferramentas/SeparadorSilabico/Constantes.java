package Ferramentas.SeparadorSilabico;

/**
 * Created by Gustavo Freitas on 05/05/2016.
 */
public abstract interface Constantes
{
    public static final char[] CONSOANTES = { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'x', 'z', 'ç' };
    public static final char[] VOGAIS = { 'a', 'e', 'i', 'o', 'u' };
    public static final char[] CONJ_1 = { 'b', 'c', 'd', 'f', 'g', 'p', 't', 'v' };
    public static final char[] CONJ_2 = { 'c', 'l', 'n' };
    public static final char[] LATERAIS = { 'l', 'r', 'z' };
    public static final char[] H = { 'h' };
    public static final char[] CONJ_5 = { 'c', 'g', 'm', 'p' };
    public static final char[] CONJ_6 = { 'n' };
    public static final char[] ACENTOS_GA = { 'à', 'á', 'é', 'í', 'ó', 'ú' };
    public static final char[] CIRCUNFLEXO = { 'â', 'ê', 'î', 'ô' };
    public static final char[] SEMI_VOGAIS = { 'i', 'u' };
    public static final char[] TIL = { 'ã', 'õ' };
    public static final char[] NASAIS = { 'm', 'n' };
    public static final char[] CONJ_8 = { 'q', 'g' };
    public static final char[] HIFEN = { '-' };
    public static final String[] MONOSSILABOS_ATONOS = {
            "o", "a", "os", "as",
            "um", "uns",
            "me", "te", "se", "lhe", "nos", "lhes",
            "que",
            "com", "de", "por", "sem", "sob",
            "e", "mas", "nem", "ou" };
    public static final String PROLONGAMENTO = "~";
    public static final int G_SEMINIMA = 1;
    public static final int G_COLCHEIA = 2;
    public static final int G_SEMICOLCHEIA = 4;
    public static final int N0 = 0;
    public static final int N1 = 1;
    public static final int N2 = 2;
    public static final int N3 = 3;
    public static final int N4 = 4;
    public static final int N5 = 5;
    public static final int NIVEL_FRACO = 6;
    public static final int[] DOIS_DOIS = { 0, 4, 3, 4, 2, 4, 3, 4, 1, 4, 3, 4, 2, 4, 3, 4 };
    public static final int[] QUATRO_QUATRO = { 0, 4, 3, 4, 2, 4, 3, 4, 1, 4, 3, 4, 2, 4, 3, 4 };
    public static final int[] TRES_QUATRO = { 0, 3, 2, 3, 1, 3, 2, 3, 1, 3, 2, 3 };
    public static final int[] DOIS_QUATRO = { 0, 3, 2, 3, 1, 3, 2, 3 };
    public static final int[] TRES_OITO = { 0, 2, 1, 2, 1, 2 };
    public static final int[] SEIS_OITO = { 0, 3, 2, 3, 2, 3, 1, 3, 2, 3, 2, 3 };
    public static final int NENHUM = -1;
    public static final int AMBOS = 0;
    public static final int TEMPO = 1;
    public static final int NIVEIS = 2;
    public static final String FICHEIROS_DIR = "ficheiros/";
    public static final int TEMPO_FORTE = 0;
    public static final int PAUSA = 1;
    public static final int TEMPO_FORTE_PAUSA = 2;
    public static final int FIM = 3;
    public static final int TEMPO_FORTE_FIM = 4;
    public static final int TEMPO_FORTE_OUTRO_PAUSA = 5;
    public static final int ULTIMO_FORTE_PARTE = 6;
    public static final int NOTA_CURTA = 8;
}
