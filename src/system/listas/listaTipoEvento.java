/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.listas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raúl Correia
 */
public class listaTipoEvento {

    List<String> listaTipoEvento;

    String CAMINHO_FICHEIRO_CONFIG = "config.ini";
    String START_CONFIG = "[tipoevento]";
    String END_CONFIG = "[/tipoevento]";

    public listaTipoEvento() {
        listaTipoEvento = new ArrayList<>();
    }

    public void lerFicheiroConfig() {
        BufferedReader br;

        try {
            //File file = new File(CAMINHO_FICHEIRO_CONFIG);
            //br = new BufferedReader(new FileReader(file));
            br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(CAMINHO_FICHEIRO_CONFIG)));

            boolean terminar = false;
            while (terminar == false) {
                String linha = br.readLine();
                if (linha == null) {
                    terminar = true;
                } else if (linha.equals(START_CONFIG)) {
                    while (terminar == false) {
                        linha = br.readLine();
                        if (!linha.equals(END_CONFIG)) {
                            listaTipoEvento.add(linha);
                        } else {
                            terminar = true;
                        }
                    }
                }
            }
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(listaTipoEvento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(listaTipoEvento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void escreverOutput() {
        for (String string : listaTipoEvento) {
            System.out.println(string);

        }
    }
}
