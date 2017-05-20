/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author salva
 */
public class AlgoritmoExperienciaProfi extends AlgoritmoAtribuicao {

    private static final String NOME_ALGORITMO_OMISSAO = "Algoritmo Experiencia Profissional";
    private static final String MSG_INPUT = "Qual a experiência mínima?";
    private static final String TITULO_INPUT = "Experiência Mínima";

    public AlgoritmoExperienciaProfi() {
        super(NOME_ALGORITMO_OMISSAO);
    }

    @Override
    public List<Atribuicao> atribui(Evento e) {
        ArrayList<Atribuicao> lista = new ArrayList<>();
        if (e.getListaCandidatura().tamanho() > 0 && e.getListaFae().tamanho() > 0) {
            String input = JOptionPane.showInputDialog(null, MSG_INPUT, TITULO_INPUT, JOptionPane.QUESTION_MESSAGE);
            if (input != null) {
                int qtdPessoas = Integer.parseInt(input);

                if (qtdPessoas > e.getListaFae().tamanho()) {
                    throw new NumberFormatException();
                }

                for (Candidatura cd : e.getListaCandidatura()) {
                    for (Fae f : e.getListaFae()) {
                        Atribuicao a = new Atribuicao(f, cd);
                        lista.add(a);
                    }
                }
            } else {
                Constantes.mensagemErro(Constantes.ERRO_ALG_SEMCAND_OU_FAE);
            }
        }
        return lista;
    }

    @Override
    public String toString() {
        return super.getNomeAlgoritmo();
    }

}
