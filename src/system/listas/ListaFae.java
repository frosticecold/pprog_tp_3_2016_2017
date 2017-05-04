package system.listas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import system.user.Fae;
import system.user.Organizador;
import system.user.Utilizador;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class ListaFae implements Iterable<Fae> {

    List<Fae> lista = new ArrayList<>();

    ;

    public ListaFae() {
    }

    public boolean add(Fae f) {
        if (!lista.contains(f)) {
            return lista.add(f);
        }
        return false;
    }

    public boolean adicionarFae(Utilizador u) {
        Fae f = new Fae(u);
        return lista.add(f);

    }

    @Override
    public Iterator<Fae> iterator() {
        return lista.iterator();
    }

    public void copiarListaFae(List<Fae> listaFaeCopiar) {
        for (Fae fae : listaFaeCopiar) {
            lista.add(fae);
        }
    }

    public int size() {
        return lista.size();
    }

    public Fae get(int i) {
        return lista.get(i);
    }

    public boolean isFaeEvento(String username) {
        for (Fae f : lista) {
            if (f.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

}
