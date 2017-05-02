package system.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import system.CentroEventos;
import system.Teste;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class JanelaPrincipal extends JFrame implements ActionListener {

    private JMenuBar menubar;
    private JMenu menu, ficheiro, iniciar, about_menu, debug_menu;
    private JMenuItem sair, about, debug_item;
    private JMenuItem atribuir_cand, decidir_cand, submeter_cand;
    private JMenuItem carregar_ficheiro, gravar_ficheiro;

    private CentroEventos ce;
    private static String ICON_FOLDER = "icons/";
    private static String ICON_EXIT = "exit.png", ICON_ABOUT = "about.gif",
            ICON_SAVE = "save.gif", ICON_PLUS = "plus.gif";

    public JanelaPrincipal(CentroEventos ce) {
        super("Aplicação PPROG TP3");
        this.ce = ce;
        initComponents();
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(450, 300));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void initComponents() {
        initMenuBar();
    }

    public void initMenuBar() {
        menubar = new JMenuBar();
        initMenu();
        initMenuAcerca();
        initMenuDebug();

        menu.add(iniciar);
        menu.add(ficheiro);
        menu.addSeparator();
        menu.add(sair);
        menubar.add(menu);
        //menubar.add(Box.createHorizontalGlue());
        menubar.add(debug_menu);
        menubar.add(about_menu);
        setJMenuBar(menubar);

    }

    public void initMenu() {
        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_M);

        initSubmenuIniciar();
        initSubmenuFicheiro();

        sair = new JMenuItem("Sair");
        definirIcon(sair, ICON_EXIT);
        sair.addActionListener(this);
    }

    public void definirIcon(JMenuItem cmp, String nomeficheiro) {
        cmp.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(ICON_FOLDER + nomeficheiro)));
    }

    public void initSubmenuIniciar() {
        iniciar = new JMenu("Iniciar");
        iniciar.setMnemonic(KeyEvent.VK_I);

        atribuir_cand = new JMenuItem("Atribuir Candidatura");
        decidir_cand = new JMenuItem("Decidir Candidatura");
        submeter_cand = new JMenuItem("Submeter Candidatura");

        atribuir_cand.setMnemonic(KeyEvent.VK_A);
        decidir_cand.setMnemonic(KeyEvent.VK_D);
        submeter_cand.setMnemonic(KeyEvent.VK_S);
        atribuir_cand.addActionListener(this);

        iniciar.add(atribuir_cand);
        iniciar.add(decidir_cand);
        iniciar.add(submeter_cand);

    }

    public void initSubmenuFicheiro() {
        ficheiro = new JMenu("Ficheiro");
        ficheiro.setMnemonic(KeyEvent.VK_F);
        carregar_ficheiro = new JMenuItem("Carregar ficheiro");
        definirIcon(carregar_ficheiro, ICON_PLUS);
        gravar_ficheiro = new JMenuItem("Gravar Ficheiro");
        definirIcon(gravar_ficheiro, ICON_SAVE);
        
        ficheiro.add(carregar_ficheiro);
        ficheiro.add(gravar_ficheiro);
    }

    public void initMenuAcerca() {
        about_menu = new JMenu("?");
        about = new JMenuItem("Acerca");
        definirIcon(about, ICON_ABOUT);
        about.addActionListener(this);
        about_menu.add(about);
    }

    public void initMenuDebug() {
        debug_menu = new JMenu("Debug");
        debug_item = new JMenuItem("Carregador valores Debug");
        debug_item.addActionListener(this);
        debug_menu.add(debug_item);
        debug_menu.add(debug_item);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == about) {
            JOptionPane.showMessageDialog(null, "Raúl Correia - 1090657@isep.ipp.pt\nSalvador Gouveia 1151238@isep.ipp.pt");
        }
        if (e.getSource() == sair) {
            System.exit(0);
        }
        if (e.getSource() == atribuir_cand) {
            if (ce.getRegistoEventos().size() > 0) {
                AtribuirCandidatura ac = new AtribuirCandidatura(ce);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Erro, não existem Eventos.", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == debug_item) {
            Teste t = new Teste();
            t.init(ce);
            ce.setRegistoEventos(t.getRe());
            ce.setRegistoUtilizadores(t.getRu());
        }
    }

}
