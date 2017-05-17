package system.evento;

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
     * onstrutor completo de um objeto do tipo Exposição, que recebe como
     * parâmetro um titulo, uma descriçao, nome do local, uma data de inicio e
     * uma data de fim
     *
     * @param titulo Titulo de um Evento
     * @param descricao Descrição de um Evento
     * @param nomeLocal Nome do local de um Evento
     * @param dataInicio Data de Inicio de um Evento
     * @param dataFim Data de Fim de um Evento
     */
    public Exposicao(String titulo, String descricao, String nomeLocal, Data dataInicio, Data dataFim) {
        super(titulo, descricao, nomeLocal, dataInicio, dataFim);
    }

}
