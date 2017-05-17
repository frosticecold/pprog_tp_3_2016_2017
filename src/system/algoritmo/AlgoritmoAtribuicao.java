package system.algoritmo;

import java.util.List;
import system.candidatura.Atribuicao;
import system.evento.Evento;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public interface AlgoritmoAtribuicao {

    /**
     * Interface para atribuir um evento que devolve uma lista de atribuições
     *
     * @param e Evento a atribuir
     * @return Devolve uma lista com as atribuições
     */
    public List<Atribuicao> atribui(Evento e);
}
