package system.candidatura;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Decisao {

    private boolean decisao;
    private String textoJustificativo;

    public static boolean APROVADO = true, NAO_APROVADO = false;
    public static String SEM_TEXTO = "Sem texto";

    public Decisao() {
        decisao = NAO_APROVADO;
        textoJustificativo = SEM_TEXTO;
    }

    public Decisao(boolean decisao, String texto_justificativo) {
        this.decisao = decisao;
        this.textoJustificativo = texto_justificativo;
    }

    public boolean isDecisao() {
        return decisao;
    }

    public String getTextoJustificativo() {
        return textoJustificativo;
    }

    public void setDecisao(boolean decisao) {
        this.decisao = decisao;
    }

    public void setTextoJustificativo(String texto_justificativo) {
        this.textoJustificativo = texto_justificativo;
    }
    

}
