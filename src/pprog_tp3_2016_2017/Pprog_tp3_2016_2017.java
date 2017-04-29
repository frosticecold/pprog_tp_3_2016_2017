/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pprog_tp3_2016_2017;

import system.listas.listaTipoEvento;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Pprog_tp3_2016_2017 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        listaTipoEvento lte = new listaTipoEvento();
        lte.lerFicheiroConfig();
        lte.escreverOutput();
    }
    
}
