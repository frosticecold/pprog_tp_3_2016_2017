package system.candidatura;

import java.io.Serializable;
import system.user.RepresentanteEmpresa;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Candidatura implements Serializable {

    /**
     * Descrição de uma Candidatura
     */
    private String descricao;
    /**
     * Representante associado a uma candidatura
     */
    private RepresentanteEmpresa representanteEmpresa;

    /**
     * Descrição por omissão;
     */
    private static final String DESCRICAO_POR_OMISSAO = "Sem Descricao";

    /**
     * Construtor vazio de um objeto do tipo Candidatura
     */
    public Candidatura() {
        descricao = DESCRICAO_POR_OMISSAO;
        this.representanteEmpresa = new RepresentanteEmpresa();
    }

    /**
     * Construtor de um objeto do tipo Candidatura que recebe como parâmetro uma
     * descrição e uma representante da empresa
     *
     * @param descricao Descrição da candidatura
     * @param re Representante da Empresa
     */
    public Candidatura(String descricao, RepresentanteEmpresa re) {
        this.descricao = descricao;
        this.representanteEmpresa = re;
    }

    /**
     * Construtor cópia de uma candidatura
     *
     * @param c Objeto do tipo candidatura a copiar
     */
    public Candidatura(Candidatura c) {
        this.descricao = c.descricao;
        this.representanteEmpresa = c.representanteEmpresa;
    }

    /**
     * Devolve a descrição de uma candidatura
     *
     * @return Descrição de uma candidatura
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Devolve o representante de empresa associado à candidatura
     *
     * @return Represetante de empresa
     */
    public RepresentanteEmpresa getRepresentanteEmpresa() {
        return representanteEmpresa;
    }

    /**
     * Modifica a descrição de uma candidatura
     *
     * @param descricao a guardar
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Modifica o representante de empresa de uma candidatura
     *
     * @param representanteEmpresa Representante de Empresa a guardar
     */
    public void setRepresentanteEmpresa(RepresentanteEmpresa representanteEmpresa) {
        this.representanteEmpresa = representanteEmpresa;
    }

    /**
     * Modifica o representante de empresa e a descrição de uma candidatura
     *
     * @param descricao Descrição a guardar
     * @param re Representante de empresa a guardar
     */
    public void setDados(String descricao, RepresentanteEmpresa re) {
        this.descricao = descricao;
        this.representanteEmpresa = re;
    }

    /**
     * Devolve a descrição do objeto Candidatura
     *
     * @return descricao da candidatura
     */
    @Override
    public String toString() {
        return String.format(descricao + " " + representanteEmpresa);
    }

    /**
     * Comparara se dois objetos são iguais
     *
     * @param obj Objeto a comparar
     * @return Retorna verdadeiro ou falso
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Candidatura other = (Candidatura) obj;
        return this.descricao.equals(other.descricao) && this.representanteEmpresa.equals(other.representanteEmpresa);
    }

}
