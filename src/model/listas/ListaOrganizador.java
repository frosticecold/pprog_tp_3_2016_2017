package model.listas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.user.Organizador;
import model.user.Utilizador;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class ListaOrganizador implements Serializable {

    /**
     * Lista de Organizadores
     */
    private List<Organizador> lista;

    /**
     * Construtor vazio de uma ListaOrganizador
     */
    public ListaOrganizador() {
        lista = new ArrayList<>();
    }

    /**
     * Método que adiciona um Utilizador como organizador à lista, caso não seja duplicado
     *
     * @param u Utilizador
     * @return Verdadeiro ou Falso
     */
    public boolean adicionarOrganizador(Utilizador u) {
        Organizador o = new Organizador(u);
        if (!lista.contains(o)) {
            return lista.add(o);
        }
        return false;
    }

    /**
     * Método que verifica se um dado username existe e é organizador de um
     * evento
     *
     * @param username String username a procurar
     * @return Verdadeiro ou Falso
     */
    public boolean isOrganizadorEvento(String username) {
        for (Organizador o : lista) {
            if (o.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
