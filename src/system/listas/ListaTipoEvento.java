/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.listas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.lerFicheiroConfig;

/**
 *
 * @author Raúl Correia
 */
public class ListaTipoEvento implements InterfaceListaLerConfig<String>, Iterable<String> {

    List<String> lista;
    public static String CLASSPATH;
    private static String START_CONFIG = "[tipoevento]";
    private static String END_CONFIG = "[/tipoevento]";

    public ListaTipoEvento() {
        lista = new ArrayList<>();
        lerFicheiroConfig lf = new lerFicheiroConfig();
        CLASSPATH = lf.lerFicheiroConfig(START_CONFIG, END_CONFIG, CLASSPATH, this);
    }

//    public void lerFicheiroConfig() {
//        BufferedReader br;
//
//        try {
//            //File file = new File(CAMINHO_FICHEIRO_CONFIG);
//            //br = new BufferedReader(new FileReader(file));
//            br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(CAMINHO_FICHEIRO_CONFIG)));
//
//            boolean terminar = false;
//            while (terminar == false) {
//                String linha = br.readLine();
//                if (linha == null) {
//                    terminar = true;
//                } else if (linha.equals(START_CONFIG)) {
//                    while (terminar == false) {
//                        linha = br.readLine();
//                        if (!linha.equals(END_CONFIG)) {
//                            lista.add(linha);
//                        } else {
//                            terminar = true;
//                        }
//                    }
//                }
//            }
//            br.close();
//        } catch (IOException ex) {
//            System.out.println("Erro");
//        }
//
//    }

    public void escreverOutput() {
        for (String string : lista) {
            System.out.println(string);

        }
    }

    @Override
    public boolean add(String s) {
        return lista.add(s);
    }

    public Iterator<String> iterator() {
        return lista.iterator();
    }
}
