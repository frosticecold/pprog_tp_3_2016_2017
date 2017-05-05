package system.listas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import system.candidatura.Candidatura;
import system.evento.Evento;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class ListaCandidatura implements Iterable<Candidatura> {

    List<Candidatura> lista = new ArrayList<>();

    public ListaCandidatura() {

    }

    public boolean addCandidatura(Candidatura c) {
        return lista.add(c);
    }

    public Candidatura novaCandidatura(Evento e) {
        return new Candidatura();
    }

    @Override
    public Iterator<Candidatura> iterator() {
        return lista.iterator();
    }

    public boolean validaCandidatura(Candidatura c) {
        if (!lista.contains(c)) {
            return addCandidatura(c);
        }
        return false;
    }
    
    public int size(){
        return lista.size();
    }

}
