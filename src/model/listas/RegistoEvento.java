package model.listas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import model.evento.Evento;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class RegistoEvento implements Iterable<Evento>, Serializable {

    /**
     * Constantes para filtrar utilizadores organizadores
     */
    public static final int UTILIZADOR_ORGANIZADOR = 0;
    /**
     * Constante para filtrar utilizadores faes
     */
    public static final int UTILIZADOR_FAE = 1;
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
     * Retorna uma lista de Eventos associados a um Tipo de Utilizador por
     * username
     *
     * @param username Username ao qual se procura se Eventos lhe estão
     * associados
     * @param TIPO_UTILIZADOR Tipo de utilizador a procurar
     * @return ListaEventos associados ao username
     */
    public List<Evento> getListEventoPorUtilizadorPreDefinido(String username, int TIPO_UTILIZADOR) {
        List<Evento> le = new ArrayList<>();
        for (Evento e : lista) {
            switch (TIPO_UTILIZADOR) {
                case UTILIZADOR_ORGANIZADOR:
                    if (e.getListaOrganizador().isOrganizadorEvento(username)) {
                        le.add(e);
                    }
                    break;
                case UTILIZADOR_FAE:
                    if (e.getListaFae().isFaeEvento(username)) {
                        le.add(e);
                        break;
                    }
            }
        }
        return le;
    }

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
     * Método que recebe um username e verefica se é organizador e tem eventos
     * associados
     *
     * @param username Username do organizador
     * @return
     */
    public boolean verificarSeOrganizadorTemEventos(String username) {
        for (Evento ev : lista) {
            if (ev.getListaOrganizador().isOrganizadorEvento(username)) {
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
