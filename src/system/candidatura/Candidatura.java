package system.candidatura;

import system.user.RepresentanteEmpresa;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Candidatura {

    private String descricao;

    private static final String DESCRICAO_POR_OMISSAO = "sem descricao";
    private RepresentanteEmpresa re;

    public Candidatura(String descricao, RepresentanteEmpresa re) {
        this.descricao = descricao;
        this.re = re;
    }

    public Candidatura() {
        descricao = DESCRICAO_POR_OMISSAO;
        this.re = new RepresentanteEmpresa();
    }

    public Candidatura(Candidatura c) {
        this.descricao = c.descricao;
        this.re = c.re;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDados(String descricao, RepresentanteEmpresa re) {
        this.descricao = descricao;
        this.re = re;
    }

    /**
     *
     * @return descricao da candidatura
     */
    @Override
    public String toString() {
        return String.format(descricao);
    }

    public boolean valida() {
        boolean s = false;
        boolean rep = false;
        if (descricao != null && descricao.length() > 15) {
            s = true;
        }
        if (re != null) {
            rep = true;
        }
        return s && rep;
    }
}
