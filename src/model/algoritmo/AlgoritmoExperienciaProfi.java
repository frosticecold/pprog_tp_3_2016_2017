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
 * @author 1151238@isep.ipp.pt
 */
public class AlgoritmoExperienciaProfi extends AlgoritmoAtribuicao {

    private static final String NOME_ALGORITMO_OMISSAO = "Algoritmo Experiencia Profissional";
    private static final String MSG_INPUT = "Qual a experiência mínima?";
    private static final String TITULO_INPUT = "Experiência Mínima";
    private int min_exp, max_exp;

    public AlgoritmoExperienciaProfi() {
        super(NOME_ALGORITMO_OMISSAO);
    }

    @Override
    public List<Atribuicao> atribui(Evento e) {
        ArrayList<Atribuicao> lista = new ArrayList<>();
        try {
            if (e.getListaCandidatura().tamanho() > 0 && e.getListaFae().tamanho() > 0) {
                obterMaxMin(e);

                String input = JOptionPane.showInputDialog(null, MSG_INPUT, TITULO_INPUT, JOptionPane.QUESTION_MESSAGE);
                if (input != null) {
                    int expMin = Integer.parseInt(input);

                    if (expMin > e.getListaFae().tamanho()) {
                        throw new NumberFormatException();
                    }
                    for (Candidatura cd : e.getListaCandidatura()) {
                        for (Fae f : e.getListaFae()) {
                            if (f.getExperiencia_profissional() >= expMin) {
                                Atribuicao a = new Atribuicao(f, cd);
                                lista.add(a);
                            }
                        }
                    }
                } else {
                    Constantes.mensagemErro(Constantes.ERRO_ALG_SEMCAND_OU_FAE);
                }
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, Constantes.ERRO_INPUT_INVALIDO, Constantes.ERRO_TITULO, JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /*
     *    
     *    
     */
    private void obterMaxMin(Evento e) {
        for (int i = 0; i < e.getListaFae().tamanho(); i++) {
            Fae f = e.getListaFae().obterFae(i);
            if (i == 0) {
                min_exp = max_exp = f.getExperiencia_profissional();
            }
            if (f.getExperiencia_profissional() > max_exp) {
                max_exp = f.getExperiencia_profissional();
            }
            if (f.getExperiencia_profissional() < min_exp) {
                min_exp = f.getExperiencia_profissional();
            }
        }
    }

//    private void lerInput(Evento e) {
//        obterMaxMin(e);
//        String input = JOptionPane.showInputDialog(null, MSG_INPUT, TITULO_INPUT, JOptionPane.QUESTION_MESSAGE);
//
//        if (input != null) {
//            int expMin = Integer.parseInt(input);
//            while(expMin<min_exp && expMin>max_exp){
//                System.out.println(MSG_INPUT);
//            }
//
//        }
//    }
}
