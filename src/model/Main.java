/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.user.Utilizador;
import ui.JanelaPrincipal;
import utils.LerBaseDados;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        CentroEventos ce = new CentroEventos();

//        Teste t = new Teste();
//        t.init(ce);
        LerBaseDados lbd = new LerBaseDados(ce);
        for (Utilizador u : ce.getRegistoUtilizadores()) {
            System.out.println(u);
        }
        JanelaPrincipal p = new JanelaPrincipal(ce);
    }

}
