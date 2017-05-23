package ui.misc;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;

public class MyJFileChooser extends JFileChooser {

    public MyJFileChooser() {
        super(diretorioAtual());
        definirFiltroExtensaoBin();
    }

    private void definirFiltroExtensaoBin() {
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                return f.getName().toLowerCase().endsWith(".bin");
            }

            @Override
            public String getDescription() {
                return "*.bin";
            }

        };

        setFileFilter(filter);
    }

    public static void personalizarEmPortugues() {

        // T�tulos das Caixas de Di�logo
        UIManager.put("FileChooser.openDialogTitleText", "Importar Centro de Eventos");
        UIManager.put("FileChooser.saveDialogTitleText", "Exportar Centro de Eventos");

        // Bot�o "Importar"
        UIManager.put("FileChooser.openButtonText", "Importar");
        UIManager.put("FileChooser.openButtonToolTipText", "Importar Centro de Eventos");

        // Bot�o "Exportar"
        UIManager.put("FileChooser.saveButtonText", "Exportar");
        UIManager.put("FileChooser.saveButtonToolTipText", "Exportar Centro de Eventos");

        // Bot�o "Cancelar"
        UIManager.put("FileChooser.cancelButtonText", "Cancelar");
        UIManager.put("FileChooser.cancelButtonToolTipText", "Cancelar");

        // Legenda "Procurar em:"
        UIManager.put("FileChooser.lookInLabelText", "Procurar em:");

        // Legenda "Guardar em:"
        UIManager.put("FileChooser.saveInLabelText", "Guardar em:");

        // Legenda "Tipo de ficheiros:"
        UIManager.put("FileChooser.filesOfTypeLabelText", "Ficheiros do tipo:");

        // Legenda "Nome do ficheiro:"
        UIManager.put("FileChooser.fileNameLabelText", "Nome do ficheiro:");

        // Filtro "Todos os Ficheiros"
        UIManager.put("FileChooser.acceptAllFileFilterText", "Todos os Ficheiros");

        // Bot�o "Um n�vel acima"
        UIManager.put("FileChooser.upFolderToolTipText", "Um nível acima");

        // Bot�o "Nova Pasta"
        UIManager.put("FileChooser.newFolderToolTipText", "Criar nova pasta");

        // Bot�o "Vista Lista"
        UIManager.put("FileChooser.listViewButtonToolTipText", "Lista");

        // Bot�o "Vista Detalhada"
        UIManager.put("FileChooser.detailsViewButtonToolTipText", "Detalhes");

    }

    /**
     * Retorna o diretorio atual
     *
     * @return Diretorio Atual
     */
    private static File diretorioAtual() {
        return new File(System.getProperty("user.dir"));
    }

}
