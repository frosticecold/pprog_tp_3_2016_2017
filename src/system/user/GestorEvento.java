package system.user;

import java.io.Serializable;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class GestorEvento extends Utilizador implements Serializable{

    public GestorEvento() {
        super();
    }

    public GestorEvento(String nome, String username, String email, char[] password) {
        super(nome, username, email, password);
    }

    public GestorEvento(Utilizador u) {
        super(u);
    }
}
