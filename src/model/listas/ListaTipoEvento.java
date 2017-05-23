/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.listas;

import java.io.Serializable;
import utils.InterfaceListaLerConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import utils.LerFicheiroConfig;

/**
 *
 * @author Raúl Correia
 */
public class ListaTipoEvento implements InterfaceListaLerConfig<String>, Iterable<String>, Serializable {

    /**
     * Lista com os tipos de evento
     */
    private List<String> lista;

    /**
     * Caminho da classe model.listas.ListaTipoEvento
     */
    public static String CLASSPATH;

    /**
     * Tag onde se inicia ler o tipo de evento
     */
    private static final String START_CONFIG = "[tipoevento]";
    /**
     * Tag onde acabar ler o tipo de evento
     */
    private static final String END_CONFIG = "[/tipoevento]";

    /**
     * Construtor de uma ListaTipoEvento
     */
    public ListaTipoEvento() {
        lista = new ArrayList<>();
        LerFicheiroConfig lf = new LerFicheiroConfig();
        CLASSPATH = lf.lerFicheiroConfig(START_CONFIG, END_CONFIG, this);
    }

    /**
     * Método para adicionar uma string à lista
     *
     * @param s String a adicionar
     * @return Verdadeiro ou falso se foi adicionado
     */
    @Override
    public boolean add(String s) {
        return lista.add(s);
    }

    /**
     * Iterador para percorrer a lista
     *
     * @return Iterator
     */
    public Iterator<String> iterator() {
        return lista.iterator();
    }
}
