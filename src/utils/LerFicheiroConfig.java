package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.listas.ListaTipoEvento;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class LerFicheiroConfig {

    private static String CAMINHO_FICHEIRO_CONFIG_OMISSAO = "config.ini";
    private static String CLASS_PATH_OMISSAO = "classpath=";

    public String lerFicheiroConfig(final String START_CONFIG, final String END_CONFIG, String CLASSPATH, InterfaceListaLerConfig rl) {
        BufferedReader br;
        try {
            //File file = new File(CAMINHO_FICHEIRO_CONFIG);
            //br = new BufferedReader(new FileReader(file));
            br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(CAMINHO_FICHEIRO_CONFIG_OMISSAO)));

            boolean terminar = false;
            while (terminar == false) {
                String linha = br.readLine();
                if (linha == null) {
                    terminar = true;
                } else if (linha.equals(START_CONFIG)) {
                    while (terminar == false) {
                        linha = br.readLine();
                        if (linha.contains(CLASS_PATH_OMISSAO)) {
                            String[] linhasplit = linha.split("=");
                            CLASSPATH = linhasplit[1];
                        } else if (!linha.equals(END_CONFIG)) {
                            rl.add(linha);

                        } else {
                            terminar = true;
                        }
                    }
                }
            }
            br.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListaTipoEvento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListaTipoEvento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CLASSPATH;
    }

}
