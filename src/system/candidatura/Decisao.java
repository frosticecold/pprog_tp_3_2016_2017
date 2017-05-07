package system.candidatura;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Decisao {

    private int aprovacao;
    private String textoJustificativo;

    public final static int SEM_DECISAO = -1, NAO_APROVADO = 0, APROVADO = 1;
    public final static String SEM_TEXTO = "Sem texto";

    public Decisao() {
        aprovacao = SEM_DECISAO;
        textoJustificativo = SEM_TEXTO;
    }

    public Decisao(int decisao, String texto_justificativo) {
        this.aprovacao = decisao;
        this.textoJustificativo = texto_justificativo;
    }

    public int getAprovacao() {
        return aprovacao;
    }

    public String getTextoJustificativo() {
        return textoJustificativo;
    }

    public void setAprovacao(int aprovacao) {
        this.aprovacao = aprovacao;
    }

    public void setTextoJustificativo(String texto_justificativo) {
        this.textoJustificativo = texto_justificativo;
    }

    @Override
    public String toString() {
        return "Decisao{" + "decisao=" + aprovacao + ", textoJustificativo=" + textoJustificativo + '}';
    }
    
    

}
