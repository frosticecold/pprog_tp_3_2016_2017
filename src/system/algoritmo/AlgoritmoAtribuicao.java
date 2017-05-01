package system.algoritmo;

import java.util.List;
import system.candidatura.Atribuicao;
import system.evento.Evento;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public interface AlgoritmoAtribuicao {

    public List<Atribuicao> atribui(Evento e);

//    public abstract List<Fae> atribui(CentroEventos ce, String tituloEvento, List<Fae> listaFae);

}
