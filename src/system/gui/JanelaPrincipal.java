package system.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import system.CentroEventos;
import system.Teste;
import system.user.Fae;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class JanelaPrincipal extends JFrame implements ActionListener {

    private JMenuBar menubar;
    private JMenu menu, ficheiro, iniciar, acerca_menu, debug_menu;
    private JMenuItem login, sair, acerca, debug_item;
    private JMenuItem atribuir_cand, decidir_cand, submeter_cand;
    private JMenuItem carregar_ficheiro, gravar_ficheiro;

    //Vars instância
    private CentroEventos ce;
    private String username;
    //Vars classe
    private static String ICON_FOLDER = "icons/";
    private static String ICON_EXIT = "exit.png", ICON_ABOUT = "about.gif",
            ICON_SAVE = "save.gif", ICON_LOAD_FILE = "plus.gif",
            ICON_SUBMETER_CANDIDATURA = "mala.gif", ICON_DECIDIR_CANDIDATURA = "martelo.gif", ICON_ATRIBUIR_CANDIDATURA = "raio.gif";
    private static String TITULO_JANELA = "Aplicação PPROG TP3";

    public JanelaPrincipal(CentroEventos ce) {
        super(TITULO_JANELA);
        this.ce = ce;
        initComponentes();
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(450, 300));
        setLocationRelativeTo(null);

        JanelaLogin jlg = new JanelaLogin(this, ce.getRegistoUtilizadores());
        if (jlg.getUsername() == null) {
            System.exit(0);
        } else {
            setVisible(true);
        }
    }

    public void initComponentes() {
        initMenuBar();
    }

    public void initMenuBar() {
        menubar = new JMenuBar();
        initMenu();
        initMenuAcerca();
        initMenuDebug();
        adicionarMenu();

    }

    public void adicionarMenu() {
        menu.add(iniciar);
        menu.add(ficheiro);
        menu.addSeparator();
        menu.add(login);
        menu.addSeparator();
        menu.add(sair);
        menubar.add(menu);
        //menubar.add(Box.createHorizontalGlue());
        menubar.add(debug_menu);
        menubar.add(acerca_menu);
        setJMenuBar(menubar);

    }

    public void initMenu() {
        menu = new JMenu("Menu");
        sair = new JMenuItem("Sair");
        login = new JMenuItem("Login");
        login.setMnemonic(KeyEvent.VK_L);
        login.addActionListener(this);
        login.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, InputEvent.ALT_MASK));
        initSubmenuIniciar();
        initSubmenuFicheiro();

        //Icon 
        definirIcon(sair, ICON_EXIT);
        //Mnemonic
        menu.setMnemonic(KeyEvent.VK_M);
        //ActionListener
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

        //Definir Icons
        definirIcon(atribuir_cand, ICON_ATRIBUIR_CANDIDATURA);
        definirIcon(decidir_cand, ICON_DECIDIR_CANDIDATURA);
        definirIcon(submeter_cand, ICON_SUBMETER_CANDIDATURA);
        //Mnemonic
        atribuir_cand.setMnemonic(KeyEvent.VK_A);
        decidir_cand.setMnemonic(KeyEvent.VK_D);
        submeter_cand.setMnemonic(KeyEvent.VK_S);

        //ActionListener
        atribuir_cand.addActionListener(this);
        decidir_cand.addActionListener(this);
        submeter_cand.addActionListener(this);

        //Accelerator
        atribuir_cand.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.ALT_MASK));
        decidir_cand.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.ALT_MASK));
        submeter_cand.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.ALT_MASK));
        iniciar.add(atribuir_cand);
        iniciar.add(decidir_cand);
        iniciar.add(submeter_cand);

    }

    public void initSubmenuFicheiro() {
        ficheiro = new JMenu("Ficheiro");

        carregar_ficheiro = new JMenuItem("Carregar ficheiro");
        gravar_ficheiro = new JMenuItem("Gravar Ficheiro");

        //Icon 
        definirIcon(carregar_ficheiro, ICON_LOAD_FILE);
        definirIcon(gravar_ficheiro, ICON_SAVE);
        //ActionListeners
        carregar_ficheiro.addActionListener(this);
        //Mnemonic
        ficheiro.setMnemonic(KeyEvent.VK_F);
        carregar_ficheiro.setMnemonic(KeyEvent.VK_C);
        gravar_ficheiro.setMnemonic(KeyEvent.VK_G);
        //Add submenu
        ficheiro.add(carregar_ficheiro);
        ficheiro.add(gravar_ficheiro);
    }

    public void initMenuAcerca() {
        acerca_menu = new JMenu("?");
        acerca = new JMenuItem("Acerca");
        definirIcon(acerca, ICON_ABOUT);
        acerca.addActionListener(this);
        acerca_menu.add(acerca);
    }

    public void initMenuDebug() {
        debug_menu = new JMenu("Debug");
        debug_item = new JMenuItem("Carregador valores Debug");

        debug_menu.add(debug_item);
        debug_menu.add(debug_item);

        //Mnemonic
        debug_menu.setMnemonic(KeyEvent.VK_D);
        //ActionListeners
        debug_item.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == acerca) {
            JOptionPane.showMessageDialog(null, "Raúl Correia - 1090657@isep.ipp.pt\nSalvador Gouveia 1151238@isep.ipp.pt", "Acerca", JOptionPane.QUESTION_MESSAGE);
        }
        if (e.getSource() == sair) {
            System.exit(0);
        }
        if (e.getSource() == atribuir_cand) {
            if (ce.getRegistoEventos().size() > 0) {
                setVisible(false);
                AtribuirCandidatura ac = new AtribuirCandidatura(this, ce);
                setVisible(true);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Erro, não existem Eventos.", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == decidir_cand) {
            Teste t = new Teste();
            Fae f = t.retornarFaeTeste();
            setVisible(false);
            DecidirCandidatura dc = new DecidirCandidatura(this, ce.getRegistoEventos(), f);
            setVisible(true);

        }
        if (e.getSource() == debug_item) {
            Teste t = new Teste();
            t.init(ce);
        }
        if (e.getSource() == carregar_ficheiro) {
            JFileChooser jfc = new JFileChooser();
            jfc.showOpenDialog(this);
        }
        if (e.getSource() == login) {
            JanelaLogin jl = new JanelaLogin(this, ce.getRegistoUtilizadores());
            if (jl.getUsername() != null) {
                username = jl.getUsername();
            }
        }
    }

}
