package system.candidatura;

import system.user.RepresentanteEmpresa;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Candidatura {

    private String descricao;
    private RepresentanteEmpresa representanteEmpresa;

    private static final String DESCRICAO_POR_OMISSAO = "Sem Descricao";

    public Candidatura(String descricao, RepresentanteEmpresa re) {
        this.descricao = descricao;
        this.representanteEmpresa = re;
    }

    public Candidatura() {
        descricao = DESCRICAO_POR_OMISSAO;
        this.representanteEmpresa = new RepresentanteEmpresa();
    }

    public Candidatura(Candidatura c) {
        this.descricao = c.descricao;
        this.representanteEmpresa = c.representanteEmpresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public RepresentanteEmpresa getRepresentanteEmpresa() {
        return representanteEmpresa;
    }

    public void setRepresentanteEmpresa(RepresentanteEmpresa representanteEmpresa) {
        this.representanteEmpresa = representanteEmpresa;
    }

    public void setDados(String descricao, RepresentanteEmpresa re) {
        this.descricao = descricao;
        this.representanteEmpresa = re;
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
        if (representanteEmpresa != null) {
            rep = true;
        }
        return s && rep;
    }
}
