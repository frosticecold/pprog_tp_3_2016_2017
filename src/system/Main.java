/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.util.List;
import system.algoritmo.Algteste;
import system.candidatura.Atribuicao;
import system.evento.Evento;
import system.gui.JanelaPrincipal;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        listaTipoEvento lte = new listaTipoEvento();
//        lte.lerFicheiroConfig();
//        lte.escreverOutput();
        CentroEventos ce = new CentroEventos();
//        
//        Teste t = new Teste();
//        t.init(ce);
        JanelaPrincipal p = new JanelaPrincipal(ce);
    }

}
