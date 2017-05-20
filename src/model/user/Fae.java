package model.user;

import java.io.Serializable;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Fae extends Utilizador implements Serializable {

    private int experiencia_profissional;

    private static final int EXPERIENCIA_PROFISSIONAL_POR_OMISSAO = 0;

    public Fae() {
        super();
        experiencia_profissional = EXPERIENCIA_PROFISSIONAL_POR_OMISSAO;
    }

    public Fae(String nome, String username, String email, char[] password) {
        super(nome, username, email, password);
        experiencia_profissional = EXPERIENCIA_PROFISSIONAL_POR_OMISSAO;
    }

    public Fae(String nome, String username, String email, char[] password, int experiencia_profissional) {
        super(nome, username, email, password);
        this.experiencia_profissional = experiencia_profissional;
    }

    public Fae(Utilizador u) {
        super(u.getNome(), u.getUsername(), u.getEmail(), u.getPassword());
        experiencia_profissional = EXPERIENCIA_PROFISSIONAL_POR_OMISSAO;
    }

    public int getExperiencia_profissional() {
        return experiencia_profissional;
    }

    public void setExperiencia_profissional(int experiencia_profissional) {
        this.experiencia_profissional = experiencia_profissional;
    }

    @Override
    public String toString() {
        return super.toString() + "Exp. Profissional: " + experiencia_profissional;
    }

}
