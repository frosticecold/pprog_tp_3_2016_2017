package ui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.listas.RegistoUtilizador;
import model.user.Utilizador;
import utils.Constantes;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class JanelaLogin extends JDialog implements ActionListener {

    private final JPanel pCentro = new JPanel(new BorderLayout(GAP, GAP)),
            pLabel = new JPanel(new GridLayout(N_LINHAS_A, N_COL_A)),
            pInput = new JPanel(new GridLayout(N_LINHAS_A, N_COL_A)),
            pSul = new JPanel(new BorderLayout());
    private final JButton login = new JButton("Login");
    private final JLabel lb_username = new JLabel("Username"), lb_password = new JLabel("Password");
    private final JTextField txtField = new JTextField(TAMANHO_TXTFIELD);
    private final JPasswordField passwordfield = new JPasswordField(TAMANHO_TXTFIELD);

    //Vars de Instancia
    private final RegistoUtilizador registoUtilizador;
    private String username;
    //Vars de Classe
    private static final String TITULO_ERROR_MESSAGE = "Erro";
    private static final String ERROR_MESSAGE = "Utilizador inválido";
    private static final boolean MODAL = true, ISRESIZEABLE = false;
    private static final int N_LINHAS_A = 2, N_COL_A = 1;
    private static final int TAMANHO_TXTFIELD = 15;
    private static final int GAP = 5;

    public JanelaLogin(Frame owner, RegistoUtilizador ru) {
        super(owner, Constantes.TITULO_JANELA_LOGIN, MODAL);
        registoUtilizador = ru;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(ISRESIZEABLE);
        initComponentes();
        pack();
        setLocationRelativeTo(owner);
        setVisible(true);
    }

    private void initComponentes() {
        initBotoes();
        initPainelCentro();
        initPainelSul();
        adicionarPaineis();
    }

    private void initBotoes() {
        login.addActionListener(this);
    }

    private void initPainelLabel() {
        pLabel.add(lb_username);
        pLabel.add(lb_password);
    }

    private void initPainelInput() {
        pInput.add(txtField);
        pInput.add(passwordfield);
    }

    private void initPainelCentro() {
        initPainelLabel();
        initPainelInput();
        pCentro.add(pLabel, BorderLayout.WEST);
        pCentro.add(pInput, BorderLayout.EAST);
        pCentro.add(pSul, BorderLayout.SOUTH);
        pCentro.setBorder(new EmptyBorder(GAP, GAP, GAP, GAP));
    }

    private void initPainelSul() {
        //pSul.add(sair, BorderLayout.WEST);
        pSul.add(login, BorderLayout.EAST);
    }

    private void adicionarPaineis() {
        this.add(pCentro, BorderLayout.CENTER);

    }

    public boolean verificarCredenciais() {
        for (Utilizador utilizador : registoUtilizador) {
            if (txtField.getText().equals(utilizador.getUsername())) {
                if (Arrays.equals(passwordfield.getPassword(), utilizador.getPassword())) {
                    return true;
                }
            }
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
                username = txtField.getText();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, ERROR_MESSAGE, TITULO_ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
            }
        }

    }}
