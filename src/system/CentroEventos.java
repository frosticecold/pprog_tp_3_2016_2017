package system;

import java.io.Serializable;
import system.listas.RegistoAlgoritmosAtribuicao;
import system.listas.RegistoEvento;
import system.listas.RegistoUtilizador;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class CentroEventos implements Serializable {

    /**
     * Registo de Eventos
     */
    private RegistoEvento registoEventos;
    /**
     * Registo de Utilizadores
     */
    private RegistoUtilizador registoUtilizadores;
    /**
     * Registo de Algoritmos de Atribuição
     */
    private RegistoAlgoritmosAtribuicao registoAlgoritmosAtribuicao;

    /**
     * Construtor vazio de um objeto do tipo Centro de Eventos
     */
    public CentroEventos() {
        registoEventos = new RegistoEvento();
        registoUtilizadores = new RegistoUtilizador();
        registoAlgoritmosAtribuicao = new RegistoAlgoritmosAtribuicao();
    }

    /**
     * Devolve o registo de eventos
     *
     * @return registo de eventos
     */
    public RegistoEvento getRegistoEventos() {
        return registoEventos;
    }

    /**
     * Devolve o registo de utilizadores
     *
     * @return registo de utilizadores
     */
    public RegistoUtilizador getRegistoUtilizadores() {
        return registoUtilizadores;
    }

    /**
     * Devolve o registo de algoritmos de atribuição
     *
     * @return registo de algoritmos de atribuição
     */
    public RegistoAlgoritmosAtribuicao getRegistoAlgoritmosAtribuicao() {
        return registoAlgoritmosAtribuicao;
    }

    /**
     * Modifica o registo de eventos
     *
     * @param registoEventos Registo de eventos a modificar
     */
    public void setRegistoEventos(RegistoEvento registoEventos) {
        this.registoEventos = registoEventos;
    }

    /**
     * Modifica o registo de utilizadores
     *
     * @param registoUtilizadores Registo de eventos a modificar
     */
    public void setRegistoUtilizadores(RegistoUtilizador registoUtilizadores) {
        this.registoUtilizadores = registoUtilizadores;
    }

    /**
     * Modifica o registo de Algoritmos de Atribuição
     *
     * @param registoAlgoritmosAtribuicao Registo de algoritmos de atribuição a
     * modificar
     */
    public void setRegistoAlgoritmosAtribuicao(RegistoAlgoritmosAtribuicao registoAlgoritmosAtribuicao) {
        this.registoAlgoritmosAtribuicao = registoAlgoritmosAtribuicao;
    }

}
