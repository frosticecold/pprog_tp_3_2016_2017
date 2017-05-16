package system;

import java.io.Serializable;
import system.listas.RegistoAlgoritmosAtribuicao;
import system.listas.RegistoEvento;
import system.listas.RegistoUtilizador;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class CentroEventos implements Serializable{

    private RegistoEvento registoEventos;
    private RegistoUtilizador registoUtilizadores;
    private RegistoAlgoritmosAtribuicao registoAlgoritmosAtribuicao;

    public CentroEventos() {
        registoEventos = new RegistoEvento();
        registoUtilizadores = new RegistoUtilizador();
        registoAlgoritmosAtribuicao = new RegistoAlgoritmosAtribuicao();
    }

    public RegistoEvento getRegistoEventos() {
        return registoEventos;
    }

    public RegistoUtilizador getRegistoUtilizadores() {
        return registoUtilizadores;
    }

    public RegistoAlgoritmosAtribuicao getRegistoAlgoritmosAtribuicao() {
        return registoAlgoritmosAtribuicao;
    }

    public void setRegistoEventos(RegistoEvento registoEventos) {
        this.registoEventos = registoEventos;
    }

    public void setRegistoUtilizadores(RegistoUtilizador registoUtilizadores) {
        this.registoUtilizadores = registoUtilizadores;
    }

    public void setRegistoAlgoritmosAtribuicao(RegistoAlgoritmosAtribuicao registoAlgoritmosAtribuicao) {
        this.registoAlgoritmosAtribuicao = registoAlgoritmosAtribuicao;
    }

}
