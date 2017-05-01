package system.candidatura;

import system.user.Fae;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Atribuicao {

    private Fae fae;
    private Decisao decisao;

    public Atribuicao() {
        fae = new Fae();
        decisao = new Decisao();
    }

    public Atribuicao(Fae fae, Decisao decisao) {
        this.fae = fae;
        this.decisao = decisao;
    }

    /**
     * @return the fae
     */
    public Fae getFae() {
        return fae;
    }

    /**
     * @return the decisao
     */
    public Decisao getDecisao() {
        return decisao;
    }

    /**
     * @param fae the fae to set
     */
    public void setFae(Fae fae) {
        this.fae = fae;
    }

    /**
     * @param decisao the decisao to set
     */
    public void setDecisao(Decisao decisao) {
        this.decisao = decisao;
    }

    public String toString() {
        return fae.getNome() + " " + decisao.isDecisao();
    }
}
