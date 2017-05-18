package model.listas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import model.evento.Evento;
import model.user.Fae;
import model.user.Organizador;
import utils.Data;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class RegistoEvento implements Iterable<Evento>, Serializable {

    List<Evento> lista = new ArrayList<>();

    public RegistoEvento() {

    }

    public boolean add(Evento e) {
        return lista.add(e);
    }

    public boolean remove(Evento e) {
        return lista.remove(e);
    }

    public Evento procurarEvento(String tituloEvento) {
        for (Evento e : lista) {
            if (tituloEvento.equals(e.getTitulo())) {
                return e;
            }
        }
        return null;
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

    public List<Evento> getListaEventoPorOrganizador(Organizador o) {
        List<Evento> le = new ArrayList<>();

        for (Evento ev : lista) {
            if (ev.getListaOrganizador().isOrganizadorEvento(o.getUsername())) {
                le.add(ev);
            }
        }

        return le;
    }

    public List<Evento> getListaEventoPorFae(Fae f) {
        List<Evento> le = new ArrayList<>();

        for (Evento ev : lista) {
            if (ev.getListaFae().isFaeEvento(f.getUsername())) {
                le.add(ev);
            }
        }

        return le;
    }

    public List<Evento> getListaEventoPorFae(String username) {
        List<Evento> le = new ArrayList<>();

        for (Evento ev : lista) {
            if (ev.getListaFae().isFaeEvento(username)) {
                le.add(ev);
            }
        }

        return le;
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

    public List<Evento> getListaEventoAtivoPorOrganizador(String username) {
        Calendar cal = Calendar.getInstance();
        Data dataHoje = new Data(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
        List<Evento> listaEventosAtivos = new ArrayList<>();

        for (Evento e : lista) {
            if (e.getListaOrganizador().isOrganizadorEvento(username)) {
                if (dataHoje.diferenca(e.getDataInicio()) >= 0 && dataHoje.diferenca(e.getDataFim()) <= 0) {
                    listaEventosAtivos.add(e);
                }
            }
        }
        return listaEventosAtivos;
    }

    public boolean verificarSeFaeTemAtribuicoes(String username) {
        for (Evento ev : lista) {
            if (ev.getListaAtribuicao().verificarSeFaeTemAtribuicoes(username)) {
                return true;
            }
        }
        return false;
    }
}
