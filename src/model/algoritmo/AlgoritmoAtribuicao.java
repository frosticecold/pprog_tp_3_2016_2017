package model.algoritmo;

import java.util.List;
import model.candidatura.Atribuicao;
import model.evento.Evento;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public abstract class AlgoritmoAtribuicao {

    private String nomeAlgoritmo;

    public AlgoritmoAtribuicao(String nomeAlgoritmo) {
        this.nomeAlgoritmo = nomeAlgoritmo;
    }

    public String getNomeAlgoritmo() {
        return nomeAlgoritmo;
    }

    /**
     * Interface para atribuir um evento que devolve uma lista de atribuições
     *
     * @param e Evento a atribuir
     * @return Devolve uma lista com as atribuições
     */
    public abstract List<Atribuicao> atribui(Evento e);

    @Override
    public abstract String toString();
}
