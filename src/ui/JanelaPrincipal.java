package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import model.CentroEventos;
import model.Teste;
import model.user.Fae;
import model.user.RepresentanteEmpresa;
import utils.Constantes;

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
    private JMenuItem debug_limpar;
    //Vars instância
    private CentroEventos ce;
    private String username;
    //Vars classe

    public JanelaPrincipal(CentroEventos ce) {
        super(Constantes.TITULO_JANELA_PRINCIPAL);
        this.ce = ce;
        initComponentes();
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(Constantes.TAMANHO_JANELA_MINIMO_PRINCIPAL);
        setLocationRelativeTo(null);
//
//        JanelaLogin jlg = new JanelaLogin(this, ce.getRegistoUtilizadores());
//        if (jlg.getUsername() == null) {
//            System.exit(0);
//        } else {
        setVisible(true);
//        }
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
        menubar.add(debug_menu);
        menubar.add(acerca_menu);
        setJMenuBar(menubar);

    }

    public void initMenu() {
        menu = new JMenu("Menu");
        sair = new JMenuItem("Sair");
        login = new JMenuItem("Mudar Login");
        login.setMnemonic(KeyEvent.VK_L);
        login.addActionListener(this);
        login.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, InputEvent.ALT_MASK));
        initSubmenuIniciar();
        initSubmenuFicheiro();

        //Icon 
        definirIcon(sair, Constantes.ICON_EXIT);
        //Mnemonic
        menu.setMnemonic(KeyEvent.VK_M);
        //ActionListener
        sair.addActionListener(this);
    }

    public void initSubmenuIniciar() {
        iniciar = new JMenu(Constantes.JP_MENU_INICIAR);
        iniciar.setMnemonic(KeyEvent.VK_I);

        atribuir_cand = new JMenuItem(Constantes.JP_SUBMENU_ATRIB_CAND);
        decidir_cand = new JMenuItem(Constantes.JP_SUBMENU_DECID_CAND);
        submeter_cand = new JMenuItem(Constantes.JP_SUBMENU_SUB_CAND);

        //Definir Icons
        definirIcon(atribuir_cand, Constantes.ICON_ATRIBUIR_CANDIDATURA);
        definirIcon(decidir_cand, Constantes.ICON_DECIDIR_CANDIDATURA);
        definirIcon(submeter_cand, Constantes.ICON_SUBMETER_CANDIDATURA);
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
        ficheiro = new JMenu(Constantes.MENU_INICIAR);
        carregar_ficheiro = new JMenuItem(Constantes.MENU_ITEM_CARREGAR_FICHEIRO);
        gravar_ficheiro = new JMenuItem(Constantes.MENU_ITEM_GRAVAR_FICHEIRO);

        //Icon 
        definirIcon(carregar_ficheiro, Constantes.ICON_LOAD_FILE);
        definirIcon(gravar_ficheiro, Constantes.ICON_SAVE);
        //ActionListeners
        carregar_ficheiro.addActionListener(this);
        gravar_ficheiro.addActionListener(this);
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
        definirIcon(acerca, Constantes.ICON_ABOUT);
        acerca.addActionListener(this);
        acerca_menu.add(acerca);
    }

    public void initMenuDebug() {
        debug_menu = new JMenu("Debug");
        debug_item = new JMenuItem("Carregador valores Debug");
        debug_limpar = new JMenuItem("Limpar Centro Eventos");

        debug_menu.add(debug_item);
        debug_menu.add(debug_limpar);

        //Mnemonic
        debug_menu.setMnemonic(KeyEvent.VK_D);
        //ActionListeners
        debug_item.addActionListener(this);
        debug_limpar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == acerca) {
            JOptionPane.showMessageDialog(this, Constantes.ACERCA_MENSAGEM, Constantes.TITULO_ACERCA, JOptionPane.QUESTION_MESSAGE);
        }
        if (e.getSource() == sair) {
            System.exit(0);
        }
        if (e.getSource() == debug_item) {
            Teste t = new Teste();
            t.init(ce);
        }
        if (e.getSource() == carregar_ficheiro) {
            JFileChooser jfc = new JFileChooser(diretorioAtual());
            int returnval = jfc.showOpenDialog(this);
            if (returnval == JFileChooser.APPROVE_OPTION) {
                FileInputStream fInput = null;
                try {
                    File ficheiro = jfc.getSelectedFile();
                    fInput = new FileInputStream(ficheiro);
                    ObjectInputStream in = new ObjectInputStream(fInput);
                    ce = (CentroEventos) in.readObject();
                    in.close();

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        fInput.close();
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
        if (e.getSource() == gravar_ficheiro) {
            try {
                JFileChooser jfc = new JFileChooser(diretorioAtual());

                int returnval = jfc.showSaveDialog(this);
                if (returnval == JFileChooser.APPROVE_OPTION) {
                    FileOutputStream fileOut = new FileOutputStream(jfc.getSelectedFile());
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(ce);
                    out.close();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource() == login) {
            JanelaLogin jl = new JanelaLogin(this, ce.getRegistoUtilizadores());
            if (jl.getUsername() != null) {
                username = jl.getUsername();
            }
        }

        if (e.getSource() == debug_limpar) {
            ce = new CentroEventos();
            System.out.println("O centro de eventos foi reinicializado");
        }
        //                      Casos de Uso
        // Caso de uso atribuir candidatura
        if (e.getSource() == atribuir_cand) {
            if (ce.getRegistoEventos().size() > 0) {
                setVisible(false);
                AtribuirCandidatura ac = new AtribuirCandidatura(this, ce);
                setVisible(true);
            } else {
                Constantes.mensagemErro(Constantes.ERRO_N_EXISTE_EVENTOS);
            }
        }
        // Caso de uso decidir candidatura
        if (e.getSource() == decidir_cand) {
            if (ce.getRegistoUtilizadores().size() > 0) {
                //Fae f = Teste.retornarFaeTeste(ce);
                Fae f = new Fae("Utilizador c", "userc", "userc@user.pt", "123".toCharArray());

                if (ce.getRegistoEventos()
                        .verificarSeFaeTemAtribuicoes(f.getUsername())) {
                    setVisible(false);
                    new DecidirCandidatura(this, ce, f.getUsername());
                    setVisible(true);
                } else {
                    Constantes.mensagemErro(Constantes.ERRO_NAO_TEM_ATRIBUICOES);
                }
            }

        }
        // Caso de uso submeter candidatura
        if (e.getSource() == submeter_cand) {
            if (ce.getRegistoUtilizadores().size() > 0) {
                RepresentanteEmpresa reptest = Teste.retornarRepEmpTeste(ce);
                new SubmeterCandidatura(this, ce, reptest);
            }
        }
    }

    private static File diretorioAtual() {
        return new File(System.getProperty("user.dir"));
    }

    public void definirIcon(JMenuItem cmp, String nomeficheiro) {
        cmp.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(Constantes.ICON_FOLDER + nomeficheiro)));
    }
}
