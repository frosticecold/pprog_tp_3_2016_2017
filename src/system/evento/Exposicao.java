package system.evento;

import utils.Data;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Exposicao extends Evento {

    public Exposicao() {
        super();
    }

    public Exposicao(String titulo, String descricao,String nomeLocal, Data dataInicio, Data dataFim) {
        super(titulo, descricao, nomeLocal, dataInicio, dataFim);
    }

}
