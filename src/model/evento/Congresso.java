package model.evento;

import java.io.Serializable;
import utils.Data;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Congresso extends Evento implements Serializable {

    /**
     * Construtor vazio de um objeto tipo Congresso
     */
    public Congresso() {
        super();
    }

    /**
     * Construtor completo de um objeto do tipo Congresso, que recebe como
     * parâmetro um titulo, uma descriçao, nome do local, uma data de inicio e
     * uma data de fim
     *
     * @param titulo Titulo de um Congresso
     * @param descricao Descrição de um Congresso
     * @param nomeLocal Nome do local de um Congresso
     * @param dataInicio Data de Inicio de um Congresso
     * @param dataFim Data de Fim de um Congresso
     */
    public Congresso(String titulo, String descricao, String nomeLocal, Data dataInicio, Data dataFim) {
        super(titulo, descricao, nomeLocal, dataInicio, dataFim);
    }

    /**
     * Compara se dois Congressos são iguais
     *
     * @param obj Segundo objeto a comparar
     * @return Verdadeiro ou falso
     */
    @Override
    public boolean equals(Object obj) {

        if (!super.equals(obj)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return true;
    }

    /**
     * Devolve a descrição de um Congresso
     *
     * @return
     */
    @Override
    public String toString() {
        return super.toString();
    }

}
