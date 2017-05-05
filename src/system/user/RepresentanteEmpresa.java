package system.user;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class RepresentanteEmpresa extends Utilizador {

    public RepresentanteEmpresa() {
        super();
    }

    public RepresentanteEmpresa(String nome, String username, String email, char[] password) {
        super(nome, username, email, password);
    }

    public RepresentanteEmpresa(Utilizador u) {
        super(u);
    }

}
