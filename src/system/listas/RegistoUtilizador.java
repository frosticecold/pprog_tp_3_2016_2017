package system.listas;

import java.util.ArrayList;
import java.util.List;
import system.evento.Evento;
import system.user.Utilizador;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class RegistoUtilizador {

    List<Utilizador> lu;

    public RegistoUtilizador() {
        lu = new ArrayList<>();
    }

    public boolean add(Utilizador u) {
        return lu.add(u);
    }

    public boolean remove(Utilizador u) {
        return lu.remove(u);
    }

}
