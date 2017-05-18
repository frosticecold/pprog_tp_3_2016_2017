package model.algoritmo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import model.candidatura.Atribuicao;
import model.candidatura.Candidatura;
import model.evento.Evento;
import model.user.Fae;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class AlgoritmoNumeroPessoasPorCandidatura extends AlgoritmoAtribuicao {

    private static final String MSG_INPUT = "Quantas pessoas por candidatura?";
    private static final String MSG_ERRO = "Erro, input inválido.";
    private static final String TITULO_INPUT = "Algoritmo Quantas Pessoas";
    private static final String TITULO_ERRO = "Erro";

    private static final String NOME_ALGORITMO_OMISSAO = "Numero Pessoas por Candidatura";

    public AlgoritmoNumeroPessoasPorCandidatura() {
        super(NOME_ALGORITMO_OMISSAO);
    }

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

    @Override
    public String toString() {
        return getNomeAlgoritmo();
    }

}
