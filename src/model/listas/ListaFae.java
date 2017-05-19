package model.listas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.user.Fae;
import model.user.Utilizador;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class ListaFae implements Iterable<Fae>, Serializable {

    /**
     * Lista de Faes
     */
    private List<Fae> lista;

    /**
     * Construtor vazio de uma ListaFae
     */
    public ListaFae() {
        lista = new ArrayList<>();
    }

    /**
     * Adiciona um Fae à Lista de Fae
     *
     * @param f Fae a guardar na lista
     * @return Verdadeiro ou Falso
     */
    private boolean adicionarFae(Fae f) {
        if (!lista.contains(f)) {
            return lista.add(f);
        }
        return false;
    }

    /**
     * Adiciona um Utilizador à Lista de FAe
     *
     * @param u Utilizador a guardar na lista
     * @return Verdadeira ou Falso
     */
    public boolean adicionarFae(Utilizador u) {
        Fae f = new Fae(u);
        if (!lista.contains(f)) {
            return lista.add(f);
        }
        return false;

    }

    /**
     * Método que copia uma lista recebida como parâmetro para a lista
     *
     * @param listaFaeCopiar Lista de Faes recebida como parâmetro
     */
    public void copiarListaFae(List<Fae> listaFaeCopiar) {
        for (Fae fae : listaFaeCopiar) {
            lista.add(fae);
        }
    }

    /**
     * Retorna um Fae guardada no indice passado por parâmetro
     *
     * @param i Indice de Fae a obter
     * @return Fae
     */
    public Fae obterFae(int i) {
        return lista.get(i);
    }

    /**
     * Método que verifica se um fae é fae de um evento
     *
     * @param f Fae a verificar
     * @return Verdadeiro ou Falso
     */
    public boolean isFaeEvento(Fae f) {
        for (Fae flist : lista) {
            if (flist.equals(f)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método que verifica se um username é fae de um evento
     *
     * @param username String username
     * @return Verdadeiro ou Falso
     */
    public boolean isFaeEvento(String username) {
        for (Fae f : lista) {
            if (f.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método que procura um Fae pelo username e retorna-o
     *
     * @param username String Username a procurar
     * @return Fae com o username igual ao username ou nulo
     */
    public Fae procurarFaePorUsername(String username) {
        for (Fae f : lista) {
            if (f.getUsername().equals(username)) {
                return f;
            }
        }

        return null;
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
     * Método iterator para que a classe ListaFae possa ser percorrida por um
     * for enchanced loop
     *
     * @return iterator
     */
    @Override
    public Iterator<Fae> iterator() {
        return lista.iterator();
    }
}
