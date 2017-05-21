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

    private List<String> la = new ArrayList<>();
    public static String CLASSPATH = "";
    private static final String START_CONFIG = "[algoritmo]";
    private static final String END_CONFIG = "[/algoritmo]";

    public RegistoAlgoritmosAtribuicao() {
        LerFicheiroConfig lf = new LerFicheiroConfig();
        CLASSPATH = lf.lerFicheiroConfig(START_CONFIG, END_CONFIG,CLASSPATH,this);
    }

    @Override
    public boolean add(String a) {
        return la.add(a);
    }

    @Override
    public Iterator<String> iterator() {
        return la.iterator();
    }

}
