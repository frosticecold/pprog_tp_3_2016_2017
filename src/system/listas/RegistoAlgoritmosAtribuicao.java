package system.listas;

import utils.InterfaceListaLerConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import utils.lerFicheiroConfig;
import system.algoritmo.AlgoritmoAtribuicao;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class RegistoAlgoritmosAtribuicao implements Iterable<String>, InterfaceListaLerConfig<String> {

    private List<String> la = new ArrayList<>();
    public static String CLASSPATH = "";
    private static String START_CONFIG = "[algoritmo]";
    private static String END_CONFIG = "[/algoritmo]";

    public RegistoAlgoritmosAtribuicao() {
        lerFicheiroConfig lf = new lerFicheiroConfig();
        CLASSPATH = lf.lerFicheiroConfig(START_CONFIG, END_CONFIG,CLASSPATH,this);
    }

    public boolean add(String a) {
        return la.add(a);
    }

    @Override
    public Iterator<String> iterator() {
        return la.iterator();
    }

}
