package system.algoritmo;

import java.util.ArrayList;
import java.util.List;
import system.CentroEventos;
import system.evento.Evento;
import system.user.Fae;
import system.user.Utilizador;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Algteste extends Algoritmo {

    @Override
    public List<Fae> atribui(CentroEventos ce, String tituloEvento) {
        List<Fae> lfae = new ArrayList<>();
        Evento e = procurarEvento(ce, tituloEvento);
        if (e != null) {
            for (Utilizador u : ce.getRegistoUtilizadores()) {
                lfae.add(new Fae(u));
            }

        }

        return lfae;
    }

//    @Override
//    public List<Fae> atribui(CentroEventos ce, String tituloEvento, List<Fae> listaFae) {
//        Evento e = procurarEvento(ce, tituloEvento);
//        if(e != null){
//        
//        
//        }
//    return listaFae;}
}
