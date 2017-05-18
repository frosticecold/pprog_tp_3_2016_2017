package model.listas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.candidatura.Atribuicao;
import model.candidatura.Candidatura;
import model.evento.Evento;
import model.user.Fae;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class ListaCandidatura implements Iterable<Candidatura>,Serializable {

    List<Candidatura> lista = new ArrayList<>();

    public ListaCandidatura() {

    }

    public boolean addCandidatura(Candidatura c) {
        return lista.add(c);
    }

    public Candidatura novaCandidatura() {
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

    public int size() {
        return lista.size();
    }

    public Candidatura get(int i) {
        return lista.get(i);
    }

}
