package model.user;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Utilizador implements Comparable<Utilizador>, Serializable {

    private String nome, username, email;
    private char[] password;

    //Vars de Classe
    private static final String DEFAULT_PASSWORD = "123";

    private static final String NOME_OMISSAO = "Sem nome", USERNAME_OMISSAO = "Sem username",
            EMAIL_OMISSAO = "Sem email";

    public Utilizador() {
        nome = NOME_OMISSAO;
        username = USERNAME_OMISSAO;
        email = EMAIL_OMISSAO;
        password = DEFAULT_PASSWORD.toCharArray();
    }

    public Utilizador(String nome, String username, String email, char[] password) {
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Utilizador(Utilizador u) {
        this.nome = u.getNome();
        this.username = u.getUsername();
        this.email = u.getEmail();
        this.password = u.getPassword();
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the password
     */
    public char[] getPassword() {
        return password;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(char[] password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("Utilizador{ nome= %s username= %s email= %s", nome,username,email);
        //+ ", username=" + username + ", email=" + email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utilizador u = (Utilizador) obj;
        return this.nome.equals(u.nome) && this.email.equals(u.email) && this.username.equals(u.username);
    }

    @Override
    public int compareTo(Utilizador u) {
        return this.getNome().compareTo(u.getNome());
    }

}
