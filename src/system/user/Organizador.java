package system.user;

import java.io.Serializable;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Organizador extends Utilizador implements Serializable{

    public Organizador() {
        super();
    }

    public Organizador(String nome, String username, String email, char[] password) {
        super(nome, username, email, password);
    }

    public Organizador(Utilizador u) {
        super(u);
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
