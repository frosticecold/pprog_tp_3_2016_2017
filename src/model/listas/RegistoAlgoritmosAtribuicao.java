package model.listas;

import java.io.Serializable;
import utils.InterfaceListaLerConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import utils.LerFicheiroConfig;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class RegistoAlgoritmosAtribuicao implements Iterable<String>, InterfaceListaLerConfig<String>, Serializable {

    /**
     * Lista de Registos de Algoritmos de Atribuição
     */
    private List<String> la = new ArrayList<>();
    /**
     * Caminho da classe model.listas.RegistoAlgoritmosAtribuicao
     */
    public static String CLASSPATH = "";
    /**
     * Tag onde se inicia ler os algoritmos
     */
    private static final String START_CONFIG = "[algoritmo]";
    /**
     * Tag onde acabar ler os algoritmos
     */
    private static final String END_CONFIG = "[/algoritmo]";

    /**
     * Construtor de um RegistoAlgoritmosAtribuicao
     */
    public RegistoAlgoritmosAtribuicao() {
        LerFicheiroConfig lf = new LerFicheiroConfig();
        CLASSPATH = lf.lerFicheiroConfig(START_CONFIG, END_CONFIG, this);
    }

    /**
     * Método para adicionar uma string à list
     * @param a String a adicionar
     * @return Verdadeiro ou Falso se foi adicionado à lista
     */
    @Override
    public boolean add(String a) {
        return la.add(a);
    }

    /**
     * Retorna o iterador da lista
     * @return Iterator
     */
    @Override
    public Iterator<String> iterator() {
        return la.iterator();
    }

}
