package system.algoritmo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import system.CentroEventos;
import system.evento.Evento;
import system.user.Fae;
import system.user.Utilizador;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class OrdemAlfabetica extends Algoritmo {

    private int qtdPessoas = 7;

    @Override
    public List<Fae> atribui(CentroEventos ce, String tituloEvento) {

        List<Fae> listaFae = new ArrayList<>();
        try {
            qtdPessoas = Integer.parseInt(JOptionPane.showInputDialog("Quantas pessoas a atribuir?"));
            if (qtdPessoas > ce.getRegistoUtilizadores().size()) {
                throw new NumberFormatException();
            }
            Evento ev = procurarEvento(ce, tituloEvento);
            if (ev != null) {
                ce.getRegistoUtilizadores().sort();
                for (int i = 0; i < qtdPessoas; i++) {
                    Utilizador u = ce.getRegistoUtilizadores().get(i);
                    listaFae.add(new Fae(u));
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Erro, quantidade inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return listaFae;
    }

//    @Override
//    public List<Fae> atribui(CentroEventos ce, String tituloEvento, List<Fae> listaFae) {
//        Evento ev = procurarEvento(ce, tituloEvento);
//        if (ev != null) {
//            ce.getRegistoUtilizadores().sort();
//            int index = 0;
//            for (int i = listaFae.size(); i < ce.getRegistoUtilizadores().size(); i++) {
//                if (index < qtdPessoas) {
//                    listaFae.add(new Fae(ce.getRegistoUtilizadores().get(i)));
//                    index++;
//                } else {
//                    break;
//                }
//            }
//        }
//        return listaFae;
//    }
}
