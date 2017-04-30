package system.listas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import system.user.Fae;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class ListaFae implements Iterable<Fae> {

    List<Fae> lista= new ArrayList<>();;

    public ListaFae() {
    }

    public boolean add(Fae f) {
        if (!lista.contains(f)) {
            return lista.add(f);
        }
        return false;
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
    
    public int size(){
        return lista.size();
    }

}
