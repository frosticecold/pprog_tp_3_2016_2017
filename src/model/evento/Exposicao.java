package model.evento;

import java.io.Serializable;
import utils.Data;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Exposicao extends Evento implements Serializable {

    /**
     * Construtor vazio de um objeto do tipo Exposição
     */
    public Exposicao() {
        super();
    }

    /**
     * Construtor completo de um objeto do tipo Exposição, que recebe como
     * parâmetro um titulo, uma descriçao, nome do local, uma data de inicio e
     * uma data de fim
     *
     * @param titulo Titulo de uma Exposicao
     * @param descricao Descrição de uma Exposicao
     * @param nomeLocal Nome do local de uma Exposicao
     * @param dataInicio Data de Inicio de uma Exposicao
     * @param dataFim Data de Fim de uma Exposicao
     */
    public Exposicao(String titulo, String descricao, String nomeLocal, Data dataInicio, Data dataFim) {
        super(titulo, descricao, nomeLocal, dataInicio, dataFim);
    }

    /**
     * Devolve a descrição de uma Exposição
     *
     * @return Descrição de uma Exposição
     */
    @Override
    public String toString() {
        return super.toString();
    }

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

}
