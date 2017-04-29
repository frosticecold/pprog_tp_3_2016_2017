package system.algoritmo;

import java.util.List;
import system.user.Fae;
import system.user.Utilizador;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public interface Algoritmo {

    public List<Fae> atribui(List<Utilizador> lu);
}
