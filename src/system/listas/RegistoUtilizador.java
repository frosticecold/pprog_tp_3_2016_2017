package system.listas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import system.user.Utilizador;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class RegistoUtilizador implements Iterable<Utilizador>, Serializable {

    List<Utilizador> lista = new ArrayList<>();

    public RegistoUtilizador() {
    }

    public boolean add(Utilizador u) {
        return lista.add(u);
    }

    public boolean remove(Utilizador u) {
        return lista.remove(u);
    }

    @Override
    public Iterator<Utilizador> iterator() {
        return lista.iterator();
    }

    public void sort() {
        Collections.sort(lista);
    }

    public Utilizador get(int i) {
        return lista.get(i);
    }

    public int size() {
        return lista.size();
    }

}
