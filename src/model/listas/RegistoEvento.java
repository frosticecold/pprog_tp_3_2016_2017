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

    /**
     * Lista de Eventos
     */
    private List<Evento> lista;

    /**
     * Construtor vazio de um RegistoEvento
     */
    public RegistoEvento() {
        lista = new ArrayList<>();
    }

    /**
     * Adiciona um evento à lista, caso não seja duplicado
     *
     * @param e Evento a guardar na lista
     * @return Verdadeiro ou Falso
     */
    public boolean adicionarEvento(Evento e) {
        if (!lista.contains(e)) {
            return lista.add(e);
        }
        return false;
    }

    /**
     * Método que procura e retorna um evento por o título de um Evento
     *
     * @param tituloEvento Título de um Evento
     * @return Evento ou null
     */
    public Evento procurarEvento(String tituloEvento) {
        for (Evento e : lista) {
            if (tituloEvento.equals(e.getTitulo())) {
                return e;
            }
        }
        return null;
    }

    /**
     * Retorna um Evento guardado no indice passado por parâmetro
     *
     * @param i Indice do Evento a obter
     * @return Evento
     */
    public Evento obterEvento(int i) {
        return lista.get(i);
    }

    /**
     * Faz sort a uma lista pela ordem dos eventos
     */
    public void sort() {
        Collections.sort(lista);
    }

    /**
     * Retorna um inteiro com o tamanho da lista
     *
     * @return inteiro com o tamanho da lista
     */
    public int tamanho() {
        return lista.size();
    }

    /**
     * Retorna uma lista de Eventos associados a um organizador
     *
     * @param o Organizador ao qual se procura se Eventos lhe estão associados
     * @return ListaEventos associados ao organizador
     */
    public List<Evento> getListaEventoPorOrganizador(Organizador o) {
        List<Evento> le = new ArrayList<>();
        for (Evento ev : lista) {
            if (ev.getListaOrganizador().isOrganizadorEvento(o.getUsername())) {
                le.add(ev);
            }
        }

        return le;
    }

    /**
     * Retorna uma lista de Eventos associados a um fae
     *
     * @param f Fae ao qual se procura se Eventos lhe estão associados
     * @return ListaEventos associados ao fae
     */
    public List<Evento> getListaEventoPorFae(Fae f) {
        List<Evento> le = new ArrayList<>();

        for (Evento ev : lista) {
            if (ev.getListaFae().isFaeEvento(f.getUsername())) {
                le.add(ev);
            }
        }

        return le;
    }

    /**
     * Retorna uma lista de Eventos associados a um username
     *
     * @param username Username ao qual se procura se Eventos lhe estão
     * associados
     * @return ListaEventos associados ao username
     */
    public List<Evento> getListaEventoPorFae(String username) {
        List<Evento> le = new ArrayList<>();

        for (Evento ev : lista) {
            if (ev.getListaFae().isFaeEvento(username)) {
                le.add(ev);
            }
        }

        return le;
    }

    /**
     * Retorna uma lista de Eventos associados a um Organizador por username
     *
     * @param username Username ao qual se procura se Eventos lhe estão
     * associados
     * @return ListaEventos associados ao username
     */
    public List<Evento> getListaEventoPorOrganizador(String username) {
        List<Evento> le = new ArrayList<>();
        for (Evento e : lista) {
            if (e.getListaOrganizador().isOrganizadorEvento(username)) {
                le.add(e);
            }
        }

        return le;
    }

//    public List<Evento> getListaEventoAtivoPorOrganizador(String username) {
//        Calendar cal = Calendar.getInstance();
//        Data dataHoje = new Data(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
//        List<Evento> listaEventosAtivos = new ArrayList<>();
//
//        for (Evento e : lista) {
//            if (e.getListaOrganizador().isOrganizadorEvento(username)) {
//                if (dataHoje.diferenca(e.getDataInicio()) >= 0 && dataHoje.diferenca(e.getDataFim()) <= 0) {
//                    listaEventosAtivos.add(e);
//                }
//            }
//        }
//        return listaEventosAtivos;
//    }
    /**
     * Método que recebe um username e verefica se tem atribuições associadas
     *
     * @param username
     * @return
     */
    public boolean verificarSeFaeTemAtribuicoes(String username) {
        for (Evento ev : lista) {
            if (ev.getListaAtribuicao().verificarSeFaeTemAtribuicoes(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método iterator para que a classe RegistoEvento possa ser percorrida por
     * um for enchanced loop
     *
     * @return iterator
     */
    @Override
    public Iterator<Evento> iterator() {
        return lista.iterator();
    }
}
