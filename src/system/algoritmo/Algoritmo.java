package system.algoritmo;

import java.util.List;
import system.CentroEventos;
import system.evento.Evento;
import system.user.Fae;
import system.user.Organizador;
import system.user.Utilizador;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public abstract class Algoritmo {

    public static Evento procurarEvento(CentroEventos ce, String tituloEvento) {
        for (Evento e : ce.getRegistoEventos()) {
            if (tituloEvento.equals(e.getTitulo())) {
                return e;
            }
        }
        return null;
    }

    public abstract List<Fae> atribui(CentroEventos ce, String tituloEvento);

//    public abstract List<Fae> atribui(CentroEventos ce, String tituloEvento, List<Fae> listaFae);

}
