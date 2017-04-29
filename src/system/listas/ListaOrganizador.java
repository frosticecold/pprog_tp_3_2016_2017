package system.listas;

import java.util.ArrayList;
import java.util.List;
import system.user.Organizador;
import system.user.Utilizador;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class ListaOrganizador {
    
    List<Organizador> lo;
    
    public ListaOrganizador() {
        lo = new ArrayList<>();
    }
    
    public boolean add(Utilizador u) {
        Organizador o = (Organizador) u;
        return add(o);
    }
    
    public boolean remove(Organizador o) {
        return remove(o);
    }
    
    public boolean isOrganizadorEvento(String username) {
        for (Organizador o : lo) {
            if (o.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
