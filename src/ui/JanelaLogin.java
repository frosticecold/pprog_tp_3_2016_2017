package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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

    private JPanel pCentro;
    private JButton login;
    private JLabel lb_username;
    private JComboBox cmb_utilizador;
    private DefaultComboBoxModel<Utilizador> listaModeloUtilizadores;
    //Vars de Instancia
    private final CentroEventos centroEventos;
    private String username;
    private int tipo_utilizador;
    //Vars de Classe
    private static final String TITULO_ERROR_MESSAGE = "Erro";
    private static final String ERROR_MESSAGE = "Utilizador inválido";
    private static final boolean MODAL = true, ISRESIZEABLE = true;
    private static final int N_LINHAS_A = 1, N_COL_A = 1;
    private static final int TAMANHO_TXTFIELD = 15;
    public static final int APRESENTAR_TODOS_UTILIZADORES = 0;
    public static final int APRESENTAR_APENAS_UTILIZADORES = 1;
    public static final int APRESENTAR_REPRESENTANTES_EMPRESA = 2;

    public JanelaLogin(Frame owner, CentroEventos ce, int tipo_utilizador) {
        super(owner, Constantes.TITULO_JANELA_LOGIN, MODAL);
        centroEventos = ce;
        this.tipo_utilizador = tipo_utilizador;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(ISRESIZEABLE);
        initComponentes();
        pack();
        setLocationRelativeTo(owner);
        setVisible(true);
    }

    private void initComponentes() {
        initBotoes();
        initLabels();
        initJCombobox();
        initPainelPrincipal();
        adicionarPaineis();
        copiarUtilizadoresParaListaModeloUtilizadores();
    }

    private void initBotoes() {
        login = new JButton("Login");
        login.addActionListener(this);
    }

    private void initLabels() {
        lb_username = new JLabel("Username");
    }

    private void initJCombobox() {
        listaModeloUtilizadores = new DefaultComboBoxModel<Utilizador>();
        cmb_utilizador = new JComboBox(listaModeloUtilizadores);
        cmb_utilizador.setRenderer(new CustomCellRenderer());
    }

    private JPanel initPainelSelecionarUtilizador() {
        JPanel pCentroNorte = new JPanel(new FlowLayout());
        pCentroNorte.add(lb_username);
        pCentroNorte.add(cmb_utilizador);
        return pCentroNorte;
    }

    private JPanel initPainelCentroSul() {
        JPanel psul = new JPanel(new FlowLayout());
        psul.add(login);
        return psul;
    }

    private void initPainelPrincipal() {
        pCentro = new JPanel(new BorderLayout(Constantes.GAP_CINCO, Constantes.GAP_CINCO));
        pCentro.add(initPainelSelecionarUtilizador(), BorderLayout.CENTER);
        pCentro.add(initPainelCentroSul(), BorderLayout.SOUTH);
        pCentro.setBorder(new EmptyBorder(Constantes.GAP_CINCO, Constantes.GAP_CINCO, Constantes.GAP_CINCO, Constantes.GAP_CINCO));
    }

    private void adicionarPaineis() {
        this.add(pCentro, BorderLayout.CENTER);

    }

    public boolean verificarCredenciais() {
        for (Utilizador utilizador : centroEventos.getRegistoUtilizadores()) {
//            if (txtField.getText().equals(utilizador.getUsername())) {
//                if (Arrays.equals(passwordfield.getPassword(), utilizador.getPassword())) {
            return true;
//                }
//            }
        }
        return false;
    }

    public String getUsername() {

        return username;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            if (verificarCredenciais()) {
                username = ((Utilizador) cmb_utilizador.getSelectedItem()).getUsername();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, ERROR_MESSAGE, TITULO_ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
            }
        }

    }

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
