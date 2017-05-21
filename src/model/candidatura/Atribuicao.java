package model.candidatura;

import java.io.Serializable;
import model.user.Fae;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Atribuicao implements Comparable<Atribuicao>, Serializable {

    /**
     * Fae a quem foi atribuido uma candidatura/decisão
     */
    private Fae fae;
    /**
     * Candidatura associada a um fae
     */
    private Candidatura cd;
    /**
     * Decisão efetuada por um fae
     */
    private Decisao decisao;

    /**
     * Construtor vazio de um objeto do tipo Atribuiçao;
     */
    public Atribuicao() {
        fae = new Fae();
        cd = new Candidatura();
        decisao = new Decisao();
    }

    /**
     * Construtor de uma atribuição que recebe como parametro um fae uma
     * candidatura;
     *
     * @param fae Fae a quem vai ser atribuida uma candidatura
     * @param cd Candidatura ao qual vai ser atribuida fae
     */
    public Atribuicao(Fae fae, Candidatura cd) {
        this.fae = fae;
        this.cd = cd;
        this.decisao = new Decisao();
    }

    /**
     * Retorna o Fae associado à atribuição
     *
     * @return Fae
     */
    public Fae getFae() {
        return fae;
    }

    /**
     * Retorna a decisão associada à atribuição
     *
     * @return decisao
     */
    public Decisao getDecisao() {
        return decisao;
    }

    /**
     * Retorna a candidatura associada à atribuição
     *
     * @return
     */
    public Candidatura getCandidatura() {
        return cd;
    }

    /**
     * Modifica a candidatura
     *
     * @param cd candidatura a guardar
     */
    public void setCandidatura(Candidatura cd) {
        this.cd = cd;
    }

    /**
     * Modifica o fae atribuido
     *
     * @param fae a guardar
     */
    public void setFae(Fae fae) {
        this.fae = fae;
    }

    /**
     * Modifica a associação da atribuição
     *
     * @param decisao a guardar
     */
    public void setDecisao(Decisao decisao) {
        this.decisao = decisao;
    }

    /**
     * Modifica o fae e a candidatura num só método
     *
     * @param f Fae a guardar
     * @param cd Candidatura a guardar
     */
    public void setDados(Fae f, Candidatura cd) {
        this.fae = f;
        this.cd = cd;
    }

    /**
     * Devolve a descrição da Atribuição
     *
     * @return O nome do fae e a decisão do fae
     */
    @Override
    public String toString() {
        return fae.getNome() + " " + decisao.getDecisao();
    }

    /**
     * Compara duas atribuições, impondo a ordem por username de fae
     *
     * @param o Atribuição a comparar
     * @return um inteiro com a comparação lexical de dois usarnames de faes
     */
    @Override
    public int compareTo(Atribuicao o) {
        return this.getFae().getUsername().compareTo(o.getFae().getUsername());
    }

}
