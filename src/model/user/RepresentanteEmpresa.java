package model.user;

import java.io.Serializable;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class RepresentanteEmpresa extends Utilizador implements Serializable {

    private String nomeEmpresa;

    private static String NOME_EMPRESA_OMISSAO = "Sem nome";

    public RepresentanteEmpresa() {
        super();
        nomeEmpresa = NOME_EMPRESA_OMISSAO;
    }

    public RepresentanteEmpresa(String nome, String username, String email, char[] password, String nomeEmpresa) {
        super(nome, username, email, password);
        this.nomeEmpresa = nomeEmpresa;
    }

    public RepresentanteEmpresa(Utilizador u) {
        super(u);
        nomeEmpresa = NOME_EMPRESA_OMISSAO;
    }

    public RepresentanteEmpresa(Utilizador u, String nomeEmpresa) {
        super(u);
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

}
