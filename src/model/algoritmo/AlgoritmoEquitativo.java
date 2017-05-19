/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.algoritmo;

import java.util.ArrayList;
import java.util.List;
import model.candidatura.Atribuicao;
import model.evento.Evento;

/**
 *
 * @author salva
 */
public class AlgoritmoEquitativo extends AlgoritmoAtribuicao {

    /**
     * Nome do algoritmo por omissão
     */
    private static final String NOME_ALGORITMO_OMISSAO = "Algoritmo Equitativo";

    public AlgoritmoEquitativo() {
        super(NOME_ALGORITMO_OMISSAO);
    }

    /**
     * Atribui a um evento faes de forma igual
     *
     * @param e Evento a atribuir
     * @return Lista de Atribuições
     */
    @Override
    public List<Atribuicao> atribui(Evento e) {
        List<Atribuicao> lista = new ArrayList<>();
        for (int i = 0; i < e.getListaFae().tamanho(); i++) {
            for (int j = 0; j < e.getListaCandidatura().tamanho(); j++) {
                Atribuicao a = new Atribuicao();
                a.setFae(e.getListaFae().obterFae(i));
                a.setCandidatura(e.getListaCandidatura().obterCandidatura(j));
                lista.add(a);
            }

        }

        return lista;
    }

    /**
     * Retorna o nome do algoritmo
     *
     * @return Nome Algoritmo
     */
    @Override
    public String toString() {
        return getNomeAlgoritmo();
    }

}
