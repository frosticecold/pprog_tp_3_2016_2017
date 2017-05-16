package system.listas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import system.user.Organizador;
import system.user.Utilizador;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class ListaOrganizador implements Serializable{

    List<Organizador> lista;

    public ListaOrganizador() {
        lista = new ArrayList<>();
    }

    public boolean add(Utilizador u) {
        Organizador o = new Organizador(u);
        return lista.add(o);
    }

    public boolean remove(Organizador o) {
        return remove(o);
    }

    public boolean isOrganizadorEvento(String username) {
        for (Organizador o : lista) {
            if (o.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(Organizador o) {
        return lista.contains(o);
    }
    
}
