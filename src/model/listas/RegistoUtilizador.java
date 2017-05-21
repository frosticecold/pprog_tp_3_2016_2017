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

    List<Utilizador> lista;

    public RegistoUtilizador() {
        lista = new ArrayList<>();
    }

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

    public void sort() {
        Collections.sort(lista);
    }

    public Utilizador obterUtilizador(int i) {
        return lista.get(i);
    }

    public Utilizador obterUtilizadorPorUsername(String username) {
        for (Utilizador u : lista) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    public RepresentanteEmpresa obterRepresentanteEmpresaPorUsername(String username) {
        return (RepresentanteEmpresa) obterUtilizadorPorUsername(username);
    }

    public int size() {
        return lista.size();
    }

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
