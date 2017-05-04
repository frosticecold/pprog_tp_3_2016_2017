package system.user;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Fae extends Utilizador {

    public Fae() {
        super();
    }

    public Fae(String nome, String username, String email, char[] password) {
        super(nome, username, email, password);
    }

    public Fae(Utilizador u) {
        super(u.getNome(), u.getUsername(), u.getEmail(), u.getPassword());

    }

}
