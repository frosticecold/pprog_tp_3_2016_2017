package model.algoritmo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.candidatura.Atribuicao;
import model.candidatura.Candidatura;
import model.evento.Evento;
import model.user.Fae;
import utils.Constantes;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class AlgoritmoNumeroPessoasPorCandidatura extends AlgoritmoAtribuicao {

    private static final String MSG_INPUT = "Quantas pessoas por candidatura?";
    private static final String TITULO_INPUT = "Algoritmo Quantas Pessoas";
    private static final String NOME_ALGORITMO_OMISSAO = "Numero Pessoas por Candidatura";

    /**
     * Construtor vazio com o nome do algoritmo por omissão
     */
    public AlgoritmoNumeroPessoasPorCandidatura() {
        super(NOME_ALGORITMO_OMISSAO);
    }

    @Override
    /**
     * Método que atribui um evento
     * <ul>
     * <li> Pergunta quantas pessoas quer atribuir</li>
     * <li> Verifica se o input está dentro do número de faes</li>
     * <li> Atribui o número de pessoas definidas pelo utilizador</li>
     * <li> Retorna uma lista de atribuições </li>
     */
    public List<Atribuicao> atribui(Evento e) {
        List<Atribuicao> listaAtrib = new ArrayList<>();
        try {
            String input = JOptionPane.showInputDialog(null, MSG_INPUT, TITULO_INPUT, JOptionPane.QUESTION_MESSAGE);
            if (input != null) {
                int qtdPessoas = Integer.parseInt(input);

                if (qtdPessoas > e.getListaFae().tamanho()) {
                    throw new NumberFormatException();
                }

                int indiceFae = 0;
                Candidatura c;
                Fae f;

                for (int i = 0; i < e.getListaCandidatura().tamanho(); i++) {
                    for (int j = 0; j < qtdPessoas; j++) {

                        Atribuicao a = new Atribuicao();
                        c = e.getListaCandidatura().obterCandidatura(i);

                        do {
                            f = obterFaeListaAleatorio(e);
                        } while (verificarSeFaeJaFoiAtribuido(listaAtrib, c, f));

                        a.setDados(f, c);
                        listaAtrib.add(a);

                    }
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, Constantes.ERRO_INPUT_INVALIDO, Constantes.ERRO_TITULO, JOptionPane.ERROR_MESSAGE);
        }
        //Collections.sort(listaAtrib);
        return listaAtrib;
    }

    /**
     * Método que verifica que se numa lista se um fae já foi atribuido Com o
     * intuito de atribuir um fae que não seja repetido
     *
     * @param lista Lista de Atribuições
     * @param cd Candidatura a comparar se já foi atribuido
     * @param f Fae a comparar se já foi atribuido
     * @return Verdadeiro ou Falso
     */
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

    /**
     * Método que retorna um fae aleatório de uma lista
     *
     * @param e Evento que tem Faes
     * @return Fae
     */
    public Fae obterFaeListaAleatorio(Evento e) {
        int numRandom = (int) (Math.random() * e.getListaFae().tamanho());
        return e.getListaFae().obterFae(numRandom);
    }

    /**
     * Devolve o nome do Algoritmo
     *
     * @return Nome algoritmo
     */
    @Override
    public String toString() {
        return getNomeAlgoritmo();
    }

}
