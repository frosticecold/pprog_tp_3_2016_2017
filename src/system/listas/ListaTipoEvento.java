/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.listas;

import java.io.Serializable;
import utils.InterfaceListaLerConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import utils.lerFicheiroConfig;

/**
 *
 * @author Raúl Correia
 */
public class ListaTipoEvento implements InterfaceListaLerConfig<String>, Iterable<String>,Serializable {

    List<String> lista;
    public static String CLASSPATH;
    private static String START_CONFIG = "[tipoevento]";
    private static String END_CONFIG = "[/tipoevento]";

    public ListaTipoEvento() {
        lista = new ArrayList<>();
        lerFicheiroConfig lf = new lerFicheiroConfig();
        CLASSPATH = lf.lerFicheiroConfig(START_CONFIG, END_CONFIG, CLASSPATH, this);
    }
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
