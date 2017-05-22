package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.CentroEventos;
import model.user.RepresentanteEmpresa;
import model.user.Utilizador;
import ui.misc.CustomCellRenderer;
import utils.Constantes;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class JanelaLogin extends JDialog implements ActionListener {

    /**
     * Painel Principal
     */
    private JPanel painelPrincipal;
    /**
     * Botão de Login
     */
    private JButton login;
    /**
     * JLabel com texto username
     */
    private JLabel lb_username;
    /**
     * Combobox de utilizadores
     */
    private JComboBox cmb_utilizador;
    /**
     * Lista modelo associada à cmb_utilizador
     */
    private DefaultComboBoxModel<Utilizador> listaModeloUtilizadores;
    //Vars de Instancia
    /**
     * Referência ao centro de eventos
     */
    private final CentroEventos centroEventos;
    /**
     * Username onde se guarda o username da pessoa que se faz login
     */
    private String username;

    /**
     * Variável para filtrar os utilizadores por tipo
     */
    private int tipo_utilizador;

    //Vars de Classe
    /**
     *
     */
    private static final boolean ISRESIZEABLE = false;
    public static final int APRESENTAR_TODOS_UTILIZADORES = 0;
    public static final int APRESENTAR_APENAS_UTILIZADORES = 1;
    public static final int APRESENTAR_REPRESENTANTES_EMPRESA = 2;

    /**
     * Construtor da Janela Login, recebe como parâmetro a jframe pai, o centro
     * de eventos e o tipo de utilizador para fazer login
     *
     * @param owner JFrame Pai
     * @param ce Centro de Eventos
     * @param tipo_utilizador Tipo de Utilizador
     */
    public JanelaLogin(JFrame owner, CentroEventos ce, int tipo_utilizador) {
        super(owner, Constantes.TITULO_JANELA_LOGIN, DEFAULT_MODALITY_TYPE);

        centroEventos = ce;
        this.tipo_utilizador = tipo_utilizador;
        initComponentes();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(ISRESIZEABLE);
        pack();
        setLocationRelativeTo(owner);
        setVisible(true);
    }
    
    /**
     * Getter para o utilizador selecionado para login
     * @return 
     */
    public String getUsername() {
        return username;
    }

    /**
     * Método que inicializa todos os componentes da janela
     */
    private void initComponentes() {
        initBotoes();
        initLabels();
        initJCombobox();
        initPainelPrincipal();
        adicionarPaineis();
        copiarUtilizadoresParaListaModeloUtilizadores();
    }

    /**
     * Inicializa o botão de login
     */
    private void initBotoes() {
        login = new JButton("Login");
        login.addActionListener(this);
    }

    /**
     * Inicializa a label de username
     */
    private void initLabels() {
        lb_username = new JLabel("Username");
    }

    /**
     * Inicializa a combobox de utilizadores
     *
     * Aplica um filtro para apenas mostrar o nome do utilizador
     */
    private void initJCombobox() {
        listaModeloUtilizadores = new DefaultComboBoxModel<>();
        cmb_utilizador = new JComboBox(listaModeloUtilizadores);
        cmb_utilizador.setRenderer(new CustomCellRenderer());
    }

    /**
     * Inicializa o painel onde contem a jlabel username e a combobox para
     * selecionar utilizador
     *
     * @return
     */
    private JPanel initPainelSelecionarUtilizador() {
        JPanel pCentroNorte = new JPanel(new FlowLayout());
        pCentroNorte.add(lb_username);
        pCentroNorte.add(cmb_utilizador);
        return pCentroNorte;
    }

    /**
     * Inicializa o painel onde tem o botão de login
     *
     * @return
     */
    private JPanel initPainelCentroSul() {
        JPanel psul = new JPanel(new FlowLayout());
        psul.add(login);
        return psul;
    }

    /**
     * Método que manda inicializar os paineís SelecionarUtilizador e
     * PainelCentroSul e adiciona ao painel principal
     */
    private void initPainelPrincipal() {
        painelPrincipal = new JPanel(new BorderLayout(Constantes.GAP_CINCO, Constantes.GAP_CINCO));
        painelPrincipal.add(initPainelSelecionarUtilizador(), BorderLayout.CENTER);
        painelPrincipal.add(initPainelCentroSul(), BorderLayout.SOUTH);
        painelPrincipal.setBorder(new EmptyBorder(Constantes.GAP_CINCO, Constantes.GAP_CINCO, Constantes.GAP_CINCO, Constantes.GAP_CINCO));
    }

    /**
     * Adiciona o painelPrincipal à jframe
     */
    private void adicionarPaineis() {
        this.add(painelPrincipal, BorderLayout.CENTER);

    }
    /**
     * ActionListeners
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            username = ((Utilizador) cmb_utilizador.getSelectedItem()).getUsername();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, Constantes.ERRO_UTILIZADOR_INVALIDO, Constantes.ERRO_TITULO, JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Copia os utilizadores conforme o critério de filtro para a lista modelo de utilizadores
     */
    private void copiarUtilizadoresParaListaModeloUtilizadores() {
        for (Utilizador u : centroEventos.getRegistoUtilizadores()) {
            switch (tipo_utilizador) {
                case APRESENTAR_TODOS_UTILIZADORES:
                    listaModeloUtilizadores.addElement(u);
                    break;
                case APRESENTAR_APENAS_UTILIZADORES:
                    if (!(u instanceof RepresentanteEmpresa)) {
                        listaModeloUtilizadores.addElement(u);
                    }
                    break;
                case APRESENTAR_REPRESENTANTES_EMPRESA:
                    if (u instanceof RepresentanteEmpresa) {
                        listaModeloUtilizadores.addElement(u);
                    }
            }
        }
    }
}
