package system.candidatura;

import java.io.Serializable;
import system.user.Fae;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Atribuicao implements Comparable<Atribuicao>, Serializable {

    private Fae fae;
    private Candidatura cd;
    private Decisao decisao;

    public Atribuicao() {
        fae = new Fae();
        cd = new Candidatura();
        decisao = new Decisao();
    }

    public Atribuicao(Fae fae, Candidatura cd) {
        this.fae = fae;
        this.cd = cd;
        this.decisao = new Decisao();
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

    public Candidatura getCandidatura() {
        return cd;
    }

    public void setCandidatura(Candidatura cd) {
        this.cd = cd;
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

    public void setDados(Fae f, Candidatura cd) {
        this.fae = f;
        this.cd = cd;
    }

    public String toString() {
        return fae.getNome() + " " + decisao.getAprovacao();
    }

    @Override
    public int compareTo(Atribuicao o) {
        return this.getFae().getUsername().compareTo(o.getFae().getUsername());
    }

}
