package system.user;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Organizador extends Utilizador {

    public Organizador() {
        super();
    }

    public Organizador(String nome, String username, String email, String password) {
        super(nome, username, email, password);
    }

    public Organizador(Utilizador u) {
        super(u);
    }
    
    public boolean equals(Object obj){
    return super.equals(obj);
    }

}
