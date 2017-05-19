/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.algoritmo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.candidatura.Atribuicao;
import model.evento.Evento;
import model.user.Fae;

/**
 *
 * @author salva
 */
public class AlgoritmoExperienciaProfi extends AlgoritmoAtribuicao {

    private static final String NOME_ALGORITMO_OMISSAO = "AlgoritmoExperienciaProfinal";

    public AlgoritmoExperienciaProfi() {
        super(NOME_ALGORITMO_OMISSAO);
    }

    @Override
    public List<Atribuicao> atribui(Evento e) {
        ArrayList<Fae> listalocal = new ArrayList<>();
        for (Fae f : e.getListaFae()) {
            listalocal.add(f);
        }

        Collections.sort(listalocal, new Comparator<Fae>() {

            @Override
            public int compare(Fae o1, Fae o2) {
                return o2.getExperiencia_profissional() - o1.getExperiencia_profissional();
            }
        });
        for (Fae f : listalocal) {
            System.out.println(f.getExperiencia_profissional());
        }
        return null;
    }

    @Override
    public String toString() {
        return super.getNomeAlgoritmo();
    }

}
