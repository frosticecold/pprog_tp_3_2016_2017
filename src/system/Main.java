/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import javax.swing.JOptionPane;
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
        JanelaPrincipal p = new JanelaPrincipal(ce);
    }

}