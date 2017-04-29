package system.listas;

import java.util.ArrayList;
import java.util.List;
import system.evento.Evento;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class RegistoEvento {

    List<Evento> listaEvento;

    public RegistoEvento() {
        listaEvento = new ArrayList<>();
    }

    public boolean add(Evento e) {
        return add(e);
    }

    public boolean remove(Evento e) {
        return remove(e);
    }

    public List<Evento> getListaEventoPorOrganizador(String username) {
        List<Evento> le = new ArrayList<>();
        for (Evento e : listaEvento) {
            if (e.getListaOrganizador().isOrganizadorEvento(username)) {
                le.add(e);
            }
        }

        return le;
    }
}
