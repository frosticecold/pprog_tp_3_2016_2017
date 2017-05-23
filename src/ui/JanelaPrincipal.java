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
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import model.CentroEventos;
import ui.misc.MyJFileChooser;
import utils.Constantes;
import utils.LerBaseDados;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class JanelaPrincipal extends JFrame implements ActionListener {

    /**
     * JMenubar da aplicação
     */
    private JMenuBar menubar;
    /**
     * JMenu menu para interagir com a aplicação
     */
    private JMenu menu;

    /**
     * JMenu para interagir com métodos carregar e guardar ficheiro
     */
    private JMenu ficheiro;

    /**
     * JMenu para interagir com os casos de uso;
     */
    private JMenu iniciar;
    /**
     * JMenu com informações acerca da aplicação
     */
    private JMenu acerca_menu;

    /**
     * JMenu de debug
     */
    private JMenu debug_menu;
    /**
     * Opção de Login
     */
    private JMenuItem login;
    /**
     * Opção de Sair
     */
    private JMenuItem sair;
    /**
     * Opção Acerca
     */
    private JMenuItem acerca;
    /**
     * Opção para Recarregar Base de Dados
     */
    private JMenuItem reload_db;

    /**
     * Opção para o caso de uso Atribuir Candidatura
     */
    private JMenuItem atribuir_cand;
    /**
     * Opção para o caso de uso Decidir Candidatura;
     */
    private JMenuItem decidir_cand;
    /**
     * Opção para o caso de uso Submeter Candidatura
     */
    private JMenuItem submeter_cand;

    /**
     * Opção para carregar um ficheiro binário
     */
    private JMenuItem carregar_ficheiro;

    /**
     * Opção para gravar um ficheiro binário
     */
    private JMenuItem gravar_ficheiro;

    /**
     * Opção para limpar o centro de eventos
     */
    private JMenuItem debug_limpar;
    //Vars instância
    /**
     * Referência ao centro de eventos
     */
    private CentroEventos ce;

    /**
     * Username da pessoa logada
     */
    private String username;

    /**
     * Construtor da Janela Principal, que recebe como parâmetro o centro de
     * eventos
     *
     * @param ce Centro de Eventos
     */
    public JanelaPrincipal(CentroEventos ce) {
        super(Constantes.TITULO_JANELA_PRINCIPAL);

        this.ce = ce;
        initComponentes();

        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(Constantes.TAMANHO_MINIMO_JANELA_PRINCIPAL);
        setLocationRelativeTo(null);

        setVisible(true);

    }

    /**
     * Cria e inicializa todos componentes da janela
     */
    public void initComponentes() {
        initMenuBar();
    }

    /**
     * Cria e inicializa a menubar Adiciona todos os menus à menubar
     */
    public void initMenuBar() {
        menubar = new JMenuBar();
        setJMenuBar(menubar);
        initMenu();
        initMenuAcerca();
        initMenuDebug();
        adicionarMenu();

    }

    /**
     * Adiciona ao menu o submenu Menu
     */
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

    }

    /**
     * Inicializar o Menu Principal
     */
    public void initMenu() {
        menu = new JMenu(Constantes.JP_MENU_PRINCIPAL);
        sair = new JMenuItem(Constantes.JP_MENU_ITEM_SAIR);
        login = new JMenuItem(Constantes.JP_MENU_ITEM_LOGIN);
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

    /**
     * Inicializa o submenu Iniciar Adiciona os casos de uso como menu items
     * Define memónica Adiciona ActionListeners Adiciona teclas de atalho
     */
    public void initSubmenuIniciar() {
        iniciar = new JMenu(Constantes.JP_MENU_INICIAR);
        iniciar.setMnemonic(KeyEvent.VK_I);

        atribuir_cand = new JMenuItem(Constantes.JP_MENU_ITEM_ATRIB_CAND);
        decidir_cand = new JMenuItem(Constantes.JP_MENU_ITEM_DECID_CAND);
        submeter_cand = new JMenuItem(Constantes.JP_MENU_ITEM_SUB_CAND);

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

    /**
     * Inicializa o submenu Ficheiro Adiciona os casos de uso como menu items
     * Define memónica Adiciona ActionListeners Adiciona teclas de atalho
     */
    public void initSubmenuFicheiro() {
        ficheiro = new JMenu(Constantes.JP_MENU_FICHEIRO);
        carregar_ficheiro = new JMenuItem(Constantes.JP_MENU_ITEM_CARREGAR_FICHEIRO);
        gravar_ficheiro = new JMenuItem(Constantes.JP_MENU_ITEM_GRAVAR_FICHEIRO);

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

    /**
     * Cria e incializa o menu acerca, adiciona o menu item acerca, define o
     * icon e adiciona actionlistener
     */
    public void initMenuAcerca() {
        acerca_menu = new JMenu(Constantes.JP_MENU_PONTO_INTERROGACAO);
        acerca = new JMenuItem(Constantes.ACERCA);
        definirIcon(acerca, Constantes.ICON_ABOUT);
        acerca.addActionListener(this);
        acerca_menu.add(acerca);
    }

    /**
     * Cria e inicializa o menu debug
     */
    public void initMenuDebug() {
        debug_menu = new JMenu(Constantes.JP_MENU_DEBUG);
        reload_db = new JMenuItem(Constantes.JP_MENU_ITEM_RELOAD_DB);
        debug_limpar = new JMenuItem(Constantes.JP_MENU_ITEM_DEBUG_LIMPAR);

        debug_menu.add(reload_db);
        debug_menu.add(debug_limpar);

        //Mnemonic
        debug_menu.setMnemonic(KeyEvent.VK_D);
        //ActionListeners
        reload_db.addActionListener(this);
        debug_limpar.addActionListener(this);

    }

    /**
     * Onde é feita as decisões de todos os botões/menu
     *
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case Constantes.ACERCA:
                JOptionPane.showMessageDialog(this, Constantes.ACERCA_MENSAGEM, Constantes.ACERCA, JOptionPane.QUESTION_MESSAGE);
                break;
            case Constantes.JP_MENU_ITEM_SAIR:
                System.exit(0);
                break;
            case Constantes.JP_MENU_ITEM_RELOAD_DB:
                LerBaseDados lbd = new LerBaseDados(ce);
                lbd.lerBaseDados();
                break;
            case Constantes.JP_MENU_ITEM_CARREGAR_FICHEIRO: {
                MyJFileChooser jfc = new MyJFileChooser();
                int returnval = jfc.showOpenDialog(this);
                if (returnval == JFileChooser.APPROVE_OPTION) {
                    ObjectInputStream in = null;
                    try {
                        File ficheiro = jfc.getSelectedFile();
                        in = new ObjectInputStream(
                                new FileInputStream(ficheiro));

                        ce = (CentroEventos) in.readObject();
                        //Caso adicione um finally ele pede mais um try catch
                        in.close();
                    } catch (FileNotFoundException ex) {
                        Constantes.mensagemErro(Constantes.ERRO_FICHEIRO_NAO_ENCONTRADO);
                    } catch (IOException ex) {
                        Constantes.mensagemErro(Constantes.ERRO_FICHEIRO_IO_EXCEPTION);
                    } catch (ClassNotFoundException ex) {
                        Constantes.mensagemErro(Constantes.ERRO_CLASSE_NAO_ENCONTRADA);
                    }
                }

            }

            break;
            case Constantes.JP_MENU_ITEM_GRAVAR_FICHEIRO:
                try {
                    MyJFileChooser jfc = new MyJFileChooser();
                    int returnval = jfc.showSaveDialog(this);
                    if (returnval == JFileChooser.APPROVE_OPTION) {
                        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(jfc.getSelectedFile()));
                        out.writeObject(ce);
                        out.close();
                    }
                } catch (FileNotFoundException ex) {
                    Constantes.mensagemErro(Constantes.ERRO_FICHEIRO_NAO_ENCONTRADO);
                } catch (IOException ex) {
                    Constantes.mensagemErro(Constantes.ERRO_FICHEIRO_IO_EXCEPTION);
                }
                break;
            case Constantes.JP_MENU_ITEM_LOGIN:
                novaJanelaLogin(JanelaLogin.APRESENTAR_TODOS_UTILIZADORES);
                break;
            case Constantes.JP_MENU_ITEM_DEBUG_LIMPAR:
                ce = new CentroEventos();
                break;

            //                      Casos de Uso
            // Caso de uso atribuir candidatura
            case Constantes.JP_MENU_ITEM_ATRIB_CAND:
                if (ce.getRegistoUtilizadores().tamanho() > 0) {
                    if (ce.getRegistoEventos().tamanho() > 0) {
                        if (username != null) {
                            if (ce.getRegistoEventos().verificarSeOrganizadorTemEventos(username)) {
                                setVisible(false);
                                AtribuirCandidatura ac = new AtribuirCandidatura(this, ce, username);
                                setVisible(true);
                            }
                        } else {
                            Constantes.mensagemErro(Constantes.ERRO_UTILIZADOR_INVALIDO);
                        }

                    } else {
                        Constantes.mensagemErro(Constantes.ERRO_N_EXISTE_EVENTOS);
                    }
                } else {
                    Constantes.mensagemErro(Constantes.ERRO_SEM_UTILIZADORES);
                }
                break;

            // Caso de uso decidir candidatura
            case Constantes.JP_MENU_ITEM_DECID_CAND:
                if (ce.getRegistoUtilizadores().tamanho() > 0) {
                    if (username != null) {
                        if (ce.getRegistoEventos()
                                .verificarSeFaeTemAtribuicoes(username)) {
                            setVisible(false);
                            DecidirCandidatura dc = new DecidirCandidatura(this, ce, username);
                            setVisible(true);
                        } else {
                            Constantes.mensagemErro(Constantes.ERRO_NAO_TEM_ATRIBUICOES);
                        }
                    } else {
                        Constantes.mensagemErro(Constantes.ERRO_UTILIZADOR_INVALIDO);
                    }
                } else {
                    Constantes.mensagemErro(Constantes.ERRO_SEM_UTILIZADORES);
                }
                break;
            case Constantes.JP_MENU_ITEM_SUB_CAND:
                // Caso de uso submeter candidatura
                if (ce.getRegistoUtilizadores().tamanho() > 0) {
                    if (username != null) {
                        if (ce.getRegistoUtilizadores().verificarSeUserRepresentanteEmpresa(username)) {
                            setVisible(false);
                            SubmeterCandidatura sc = new SubmeterCandidatura(this, ce, ce.getRegistoUtilizadores().obterRepresentanteEmpresaPorUsername(username));
                            setVisible(true);
                        } else {
                            Constantes.mensagemErro(Constantes.ERRO_USER_N_E_REP_EMP);
                        }
                    } else {
                        Constantes.mensagemErro(Constantes.ERRO_UTILIZADOR_INVALIDO);
                    }

                } else {
                    Constantes.mensagemErro(Constantes.ERRO_SEM_UTILIZADORES);
                }
                break;
        }
    }

    /**
     * Define um icon aos jmenuitems
     *
     * @param cmp JMenuItem
     * @param nomeficheiro Nome do icon
     */
    public void definirIcon(JMenuItem cmp, String nomeficheiro) {
        cmp.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(Constantes.ICON_FOLDER + nomeficheiro)));
    }

    /**
     * Cria a nova janela de login e vai buscar o username;
     *
     * @param tipo_user Filtro para o tipo de utilizador
     */
    private void novaJanelaLogin(int tipo_user) {
        JanelaLogin jl = new JanelaLogin(this, ce, tipo_user);
        if (jl.getUsername() != null) {
            username = jl.getUsername();
        }
    }
}
