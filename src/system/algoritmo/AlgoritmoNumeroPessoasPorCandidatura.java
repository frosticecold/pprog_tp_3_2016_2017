package system.algoritmo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import system.candidatura.Atribuicao;
import system.candidatura.Candidatura;
import system.evento.Evento;
import system.user.Fae;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class AlgoritmoNumeroPessoasPorCandidatura implements AlgoritmoAtribuicao {

    private static String MSG_INPUT = "Quantas pessoas por candidatura?";
    private static String MSG_ERRO = "Erro, input inválido.";
    private static String TITULO_INPUT = "Algoritmo Quantas Pessoas";
    private static String TITULO_ERRO = "Erro";

    @Override
    public List<Atribuicao> atribui(Evento e) {
        List<Atribuicao> listaAtrib = new ArrayList<>();
        try {
            String input = JOptionPane.showInputDialog(null, MSG_INPUT, TITULO_INPUT, JOptionPane.QUESTION_MESSAGE);
            if (input != null) {
                int qtdPessoas = Integer.parseInt(input);

                if (qtdPessoas > e.getListaFae().size()) {
                    throw new NumberFormatException();
                }
                int indiceFae = 0;
                Candidatura c;
                Fae f;
                for (int i = 0; i < e.getListaCandidatura().size(); i++) {
                    for (int j = 0; j < qtdPessoas; j++) {
                        Atribuicao a = new Atribuicao();
                        c = e.getListaCandidatura().get(i);
                        do {
                            f = obterFaeListaAleatorio(e);
                        } while (verificarSeFaeJaFoiAtribuido(listaAtrib, c, f));
                        a.setDados(f, c);
                        listaAtrib.add(a);

                    }
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, MSG_ERRO, TITULO_ERRO, JOptionPane.ERROR_MESSAGE);
        }
        //Collections.sort(listaAtrib);
        return listaAtrib;
    }

    public boolean verificarSeFaeJaFoiAtribuido(List<Atribuicao> lista, Candidatura cd, Fae f) {
        for (Atribuicao atribuicao : lista) {
            if (atribuicao.getCandidatura().equals(cd)) {
                if (atribuicao.getFae().equals(f)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Fae obterFaeListaAleatorio(Evento e) {
        int numRandom = (int) (Math.random() * e.getListaFae().size());
        Fae f = e.getListaFae().get(numRandom);
        return f;
    }
    /*
                int indiceCandidaturas = 0;
            int indiceFae = 0;
            boolean terminar = false;
            do {
                Atribuicao a = new Atribuicao();
                Candidatura c = e.getListaCandidatura().get(indiceCandidaturas);
                Fae f = e.getListaFae().get(indiceFae);
                a.setFae(f);
                a.setCandidatura(c);
                listaAtrib.add(a);

                indiceFae++;
                if (indiceFae % qtdPessoas == 0) {
                    indiceCandidaturas++;
                }
                if (indiceFae == e.getListaFae().size()) {
                    indiceFae = 0;
                }

                if (indiceCandidaturas >= e.getListaCandidatura().size()) {
                    terminar = true;
                }
            } while (terminar == false);
     */
}
