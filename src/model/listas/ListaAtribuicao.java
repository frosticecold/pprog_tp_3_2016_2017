package model.listas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.candidatura.Atribuicao;
import model.user.Fae;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class ListaAtribuicao implements Iterable<Atribuicao>, Serializable {

    /**
     * Lista que guarda as atribuições
     */
    private List<Atribuicao> lista;

    /**
     * Construtor vazio de uma ListaAtribuição
     */
    public ListaAtribuicao() {
        lista = new ArrayList<>();
    }

    /**
     * Método que adiciona uma atribuição à lista, caso este não seja duplicado
     *
     * @param a Atribuição a adicionar
     * @return Verdadeiro ou Falso
     */
    public boolean adicionarAtribuicao(Atribuicao a) {
        if (!lista.contains(a)) {
            return lista.add(a);
        }
        return false;
    }

    /**
     * Método que recebe uma lista como parâmetro e copia para a lista da classe
     *
     * @param listaAtrib Lista de Atribuições a copiar
     */
    public void guardarAtribuicoes(List<Atribuicao> listaAtrib) {

        for (Atribuicao atribuicao : listaAtrib) {
            adicionarAtribuicao(atribuicao);
        }

    }

    /**
     * Método que percorre todas as atribuições e retorna uma lista com os faes
     * atribuidos
     *
     * @return Lista de Fae Atribuidos
     */
    public List<Fae> obterTodosFaesAtribuidos() {
        List<Fae> listaFae = new ArrayList<>();
        for (Atribuicao a : lista) {
            listaFae.add(a.getFae());
        }
        return listaFae;
    }

    /**
     * Método que procura as atribuições definidas ao fae, procurando por
     * username
     *
     * @param username String de username de um utilizador/fae
     * @return uma listra de Atribuições
     */
    public List<Atribuicao> obterAtribuicoesAssociadaAoFae(String username) {
        List<Atribuicao> listaAtrib = new ArrayList<>();
        for (Atribuicao a : lista) {
            if (a.getFae().getUsername().equals(username)) {
                listaAtrib.add(a);
            }
        }
        return listaAtrib;
    }

    /**
     * Verifica se um dado fae identificado pelo username tem atribuições
     *
     * @param username username de um utilizador/fae
     * @return Verdadeiro ou Falso
     */
    public boolean verificarSeFaeTemAtribuicoes(String username) {
        for (Atribuicao a : lista) {
            if (a.getFae().getUsername().equals(username)) {
                return true;
            }
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
     * Método que limpa a lista de Atribuições
     */
    public void limparLista() {
        lista.clear();
    }

    /**
     * Método iterator para que a classe ListaAtribuição possa ser percorrida
     * por um for enchanced loop
     *
     * @return iterator
     */
    @Override
    public Iterator<Atribuicao> iterator() {
        return lista.iterator();
    }

}
