package system.user;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Utilizador {

    private String nome, username, email, password;

    private String NOME_OMISSAO = "Sem nome", USERNAME_OMISSAO = "Sem username",
            EMAIL_OMISSAO = "Sem email";

    public Utilizador() {
        nome = NOME_OMISSAO;
        username = USERNAME_OMISSAO;
        email = EMAIL_OMISSAO;
        password = "123";
    }

    public Utilizador(String nome, String username, String email, String password) {
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.password = password;
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
    public String getPassword() {
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
    public void setPassword(String password) {
        this.password = password;
    }

}
