package system;

import system.listas.RegistoEvento;
import system.listas.RegistoUtilizador;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class CentroEventos {

    RegistoEvento regEv;
    RegistoUtilizador regUtlz;

    public CentroEventos() {
        regEv = new RegistoEvento();
        regUtlz = new RegistoUtilizador();
    }

}
