package model.candidatura;

import java.io.Serializable;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Decisao implements Serializable {

    /**
     * Inteiro que guarda um dos três possíveis valores, SEM_DECISAO,
     * NAO_APROVADO, APROVADO
     */
    private int decisao;
    /**
     * String com a justificação da decisão
     */
    private String textoJustificativo;

    /**
     * Constante com o valor da decisão por omissão
     */
    public final static int SEM_DECISAO = -1;
    /**
     * Constante com o valor da decisão não aprovado
     */
    public final static int NAO_APROVADO = 0;
    /**
     * Constante com o valor da decisão aprovado
     */
    public final static int APROVADO = 1;
    /**
     * Constante do texto justificativo por omissão
     */
    public final static String SEM_TEXTO = "";

    /**
     * Construtor vazio de uma decisão, sem decisão e sem texto por omissão
     */
    public Decisao() {
        decisao = SEM_DECISAO;
        textoJustificativo = SEM_TEXTO;
    }

    /**
     * Construtor que recebe como parâmetro uma decisão e um texto justificativo
     * @param decisao
     * @param texto_justificativo 
     */
    public Decisao(int decisao, String texto_justificativo) {
        this.decisao = decisao;
        this.textoJustificativo = texto_justificativo;
    }

    /**
     * Devolve o valor da aprovação
     * @return valor da aprovação
     */
    public int getDecisao() {
        return decisao;
    }

    /**
     * Devolve a string com a justificação da decisão
     * @return string com justificação da decisão
     */
    public String getTextoJustificativo() {
        return textoJustificativo;
    }

    /**
     * Modifica o valor da decisão guardada para o valor da decisão passado por parâmetro
     * @param decisao decisão a guardar
     */
    public void setDecisao(int decisao) {
        this.decisao = decisao;
    }

    /**
     * Modifica o valor do texto justificativo para o valor do texto justificação passado por parâmetro
     * @param texto_justificativo texto a guardar
     */
    public void setTextoJustificativo(String texto_justificativo) {
        this.textoJustificativo = texto_justificativo;
    }

    /**
     * Devolve a descrição do objeto Decisão
     * @return String com a descrição do objeto
     */
    @Override
    public String toString() {
        return "Decisao{" + "decisao=" + decisao + ", textoJustificativo=" + textoJustificativo + '}';
    }

}
