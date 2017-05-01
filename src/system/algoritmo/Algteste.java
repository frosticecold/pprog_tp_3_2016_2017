package system.algoritmo;

import java.util.ArrayList;
import java.util.List;
import system.candidatura.Atribuicao;
import system.evento.Evento;
import system.user.Fae;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Algteste implements AlgoritmoAtribuicao {

    public List<Atribuicao> atribui(Evento e) {
        List<Atribuicao> listaAtrib = new ArrayList<>();
        int qtdPessoas = e.getListaFae().size();
        for (int i = 0; i < qtdPessoas; i++) {
            Atribuicao a = e.getListaAtribuicao().novaAtribuicao();
            Fae f = e.getListaFae().get(i);
            if (f != null) {
                a.setFae(f);
                listaAtrib.add(a);
            }
        }

        return listaAtrib;
    }
}
//    @Override
//    public List<Fae> atribui(CentroEventos ce, String tituloEvento, List<Fae> listaFae) {
//        Evento e = procurarEvento(ce, tituloEvento);
//        if(e != null){
//        
//        
//        }
//    return listaFae;}

