package model.listas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.candidatura.Candidatura;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class ListaCandidatura implements Iterable<Candidatura>, Serializable {

    /**
     * Lista de candidaturas
     */
    private List<Candidatura> lista;

    /**
     * Construtor vazio de uma ListaCandidatura
     */
    public ListaCandidatura() {
        lista = new ArrayList<>();
    }

    /**
     * Método que adiciona uma candidatura à lista de candidaturas, caso não seja duplicado
     *
     * @param c Candidatura a adicionar à lista
     * @return Verdadeiro ou Falso
     */
    public boolean addCandidatura(Candidatura c) {
        if (!lista.contains(c)) {
            return lista.add(c);
        }
        return false;
    }

    /**
     * Retorna um inteiro com o tamanho da lista
     *
     * @return inteiro com tamanho da lista
     */
    public int tamanho() {
        return lista.size();
    }

    /**
     * Retorna uma Candidatura guardada no indice passado por parâmetro
     *
     * @param i Indice da Candidatura a obter
     * @return Candidatura
     */
    public Candidatura obterCandidatura(int i) {
        return lista.get(i);
    }

    /**
     * Método iterator para que a classe ListaCandidatura possa ser percorrida
     * por um for enchanced loop
     *
     * @return iterator
     */
    @Override
    public Iterator<Candidatura> iterator() {
        return lista.iterator();
    }
}
