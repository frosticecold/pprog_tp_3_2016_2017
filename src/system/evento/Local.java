package system.evento;

import java.io.Serializable;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Local implements Serializable{

    private String nomeDoLocal;
    private String NOME_LOCAL_OMISSAO = "Sem nome";

    public Local(String nomeDoLocal) {
        this.nomeDoLocal = nomeDoLocal;
    }

    public Local() {
        nomeDoLocal = NOME_LOCAL_OMISSAO;
    }

    public String getNomeDoLocal() {
        return nomeDoLocal;
    }

    public void setNomeDoLocal(String nomeDoLocal) {
        this.nomeDoLocal = nomeDoLocal;
    }

}
