/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.algoritmo;

import java.util.ArrayList;
import java.util.List;
import system.candidatura.Atribuicao;
import system.candidatura.Decisao;
import system.evento.Evento;

/**
 *
 * @author salva
 */
public class AlgoritmoEquitativo implements AlgoritmoAtribuicao {

    @Override
    public List<Atribuicao> atribui(Evento e) {
        List<Atribuicao> lista = new ArrayList<>();
        int qntCandidaturas = e.getListaCandidatura().size();
        for (int i = 0; i < qntCandidaturas; i++) {
            for (int j = 0; j < e.getListaFae().size(); j++) {
                Atribuicao a = e.getListaAtribuicao().novaAtribuicao();
                a.setFae(e.getListaFae().get(j));
                a.setDecisao(new Decisao());
                lista.add(a);
            }
        }
        return lista;
    }

}
