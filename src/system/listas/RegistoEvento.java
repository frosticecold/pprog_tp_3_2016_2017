package system.listas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import system.evento.Evento;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class RegistoEvento implements Iterable<Evento>, InterfaceListaLerConfig<Evento> {

    List<Evento> lista=new ArrayList<>();;

    public RegistoEvento() {

    }

    public boolean add(Evento e) {
        return lista.add(e);
    }

    public boolean remove(Evento e) {
        return lista.remove(e);
    }


    public List<Evento> getListaEventoPorOrganizador(String username) {
        List<Evento> le = new ArrayList<>();
        for (Evento e : lista) {
            if (e.getListaOrganizador().isOrganizadorEvento(username)) {
                le.add(e);
            }
        }

        return le;
    }

    @Override
    public Iterator<Evento> iterator() {
        return lista.iterator();
    }

   public void sort() {
        Collections.sort(lista);
    }

    public Evento get(int i) {
        return lista.get(i);
    }

    public int size() {
        return lista.size();
    }
}
