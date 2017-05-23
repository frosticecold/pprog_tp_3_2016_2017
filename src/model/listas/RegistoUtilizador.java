package model.listas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import model.user.RepresentanteEmpresa;
import model.user.Utilizador;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class RegistoUtilizador implements Iterable<Utilizador>, Serializable {

    /**
     * Lista de Utilizadores
     */
    List<Utilizador> lista;

    /**
     * Construtor de um RegistoUtilizador
     */
    public RegistoUtilizador() {
        lista = new ArrayList<>();
    }

    /**
     * Adiciona um utilizador ao RegistoUtilizador
     *
     * @param u Utilizador a adicionar
     * @return Verdadeiro ou Falso se foi adicionado
     */
    public boolean adicionarUtilizador(Utilizador u) {
        if (!lista.contains(u)) {
            return lista.add(u);
        }
        return false;
    }

    /**
     * Método iterator para que a classe RegistoUtilizador possa ser percorrida
     * por um for enchanced loop
     *
     * @return iterator
     */
    @Override
    public Iterator<Utilizador> iterator() {
        return lista.iterator();
    }

    /**
     * Método para ordenar a lista pela ordem definida nos utilizadores
     */
    public void sort() {
        Collections.sort(lista);
    }

    /**
     * Get a um utilizador
     *
     * @param i Indice a fazer get ao utilizador
     * @return Utilizador
     */
    public Utilizador obterUtilizador(int i) {
        return lista.get(i);
    }

    /**
     * Método que recebe como parâmetro o username e se encontrar um utilizador
     * com o mesmo username, retorna-o
     *
     * @param username String username a procurar
     * @return Utilizador se encontrar ou null se não encontrar
     */
    public Utilizador obterUtilizadorPorUsername(String username) {
        for (Utilizador u : lista) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Método que recebe como parâmetro o username e se encontrar um
     * representante de empresa com o mesmo username, retorna-o
     *
     * @param username String username a procurar
     * @return Representante de Empresa ou null
     */
    public RepresentanteEmpresa obterRepresentanteEmpresaPorUsername(String username) {
        RepresentanteEmpresa re = null;
        Utilizador u;
        u = obterUtilizadorPorUsername(username);
        if (u != null) {
            re = (RepresentanteEmpresa) u;
        }
        return re;
    }

    /**
     * Retorna o tamanho da lista
     *
     * @return tamanho da lista
     */
    public int tamanho() {
        return lista.size();
    }

    /**
     * Verifica se um utilizador é representante de empresa
     *
     * @param username Username a verificar
     * @return Verdadeiro ou Falso se é representante ou não
     */
    public boolean verificarSeUserRepresentanteEmpresa(String username) {
        for (Utilizador u : lista) {
            if (u instanceof RepresentanteEmpresa) {
                if (u.getUsername().equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }

}
