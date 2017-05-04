package system.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import system.evento.Evento;
import system.listas.RegistoEvento;
import system.user.Fae;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class DecidirCandidatura extends JDialog implements ActionListener {

    //Checkboxes
    private JCheckBox aprovado = new JCheckBox("Aprovado"), reprovado = new JCheckBox("Reprovado");
    //TextArea
    private JTextArea textArea = new JTextArea();
    //ComboBox Eventos
    private DefaultComboBoxModel<Evento> listaModeloEventos = new DefaultComboBoxModel<>();
    private JComboBox<Evento> eventoComboBox = new JComboBox(listaModeloEventos);
    //Botoes
    private JButton guardar = new JButton("Guardar"),
            sair = new JButton("Sair");

    private JLabel lbEmpresa = new JLabel(TXT_LBL_EMPRESA);
    private JPanel principal = new JPanel(new BorderLayout()),
            painelTopo = new JPanel(new GridLayout(NUM_LINHAS, NUM_COL, EMPTY_BORDER_GAP, EMPTY_BORDER_GAP)),
            painelCheckbox = new JPanel(new FlowLayout(FlowLayout.CENTER, GAP_FLOWLAYOUT_WIDTH, GAP_FLOWLAYOUT_HEIGHT)),
            painelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT)),
            painelCentro = new JPanel(new BorderLayout()),
            painelTextArea = new JPanel(new BorderLayout()),
            painelSul = new JPanel(new BorderLayout()),
            painelComboBox = new JPanel(new FlowLayout()),
            painelBotoes = new JPanel(new FlowLayout());

    //vars instância
    private RegistoEvento registoEvento;
    private Fae f_user;
    private boolean flag_aprovacao = false;

    //vars classe
    private static int GAP_FLOWLAYOUT_WIDTH = 100, GAP_FLOWLAYOUT_HEIGHT = 0;
    private static int NUM_COL = 1, NUM_LINHAS = 2;
    private static int EMPTY_BORDER_GAP = 10;
    private static Dimension TAMANHO_JANELA_MINIMO = new Dimension(450, 350);
    private static String TITULO_JANELA = "Decidir Candidatura", TITULO_BORDER = "Decisão";
    private static String ICON_FOLDER = "icons/";
    private static String ICON_APROVADO = "correct.gif", ICON_REPROVADO = "incorrect.gif";
    private static String TXT_LBL_EMPRESA = "Empresa: ", TXT_LBL_REPRESENTANTE = "Representante: ";
    private static String TXT_VAZIO = "";
    private static String TXT_EVENTO_SELECIONADO = "";

    public DecidirCandidatura(JFrame frame, RegistoEvento re, Fae f) {
        super(frame, TITULO_JANELA, true);
        registoEvento = re;
        f_user = f;
        initComponentes();
        setMinimumSize(TAMANHO_JANELA_MINIMO);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponentes() {
        initCheckboxes();
        initComboBox();
        initBotoes();
        initPainelTopo();
        initPainelCentro();
        initPainelSul();
        adicionarPaineis();

        //Teste
        copiarListaEventosParaListaComboBox();
        TXT_EVENTO_SELECIONADO = eventoComboBox.getSelectedItem().toString();
    }

    private void initCheckboxes() {
        //Atalhos
        aprovado.setMnemonic(KeyEvent.VK_A);
        reprovado.setMnemonic(KeyEvent.VK_R);

        //ActionListeners
        aprovado.addActionListener(this);
        reprovado.addActionListener(this);

    }

    private void initComboBox() {
        eventoComboBox.addActionListener(this);
    }

    private void initBotoes() {
        sair.addActionListener(this);
        sair.setMnemonic(KeyEvent.VK_S);

        guardar.addActionListener(this);
        guardar.setMnemonic(KeyEvent.VK_G);
    }

    private void initPainelTopo() {
        initPainelCheckbox();
        initPainelInfo();
        painelTopo.add(painelInfo);
        painelTopo.add(painelCheckbox);
        painelTopo.setBorder(new TitledBorder(new EtchedBorder(), TITULO_JANELA));
    }

    private void initPainelCentro() {
        painelTextArea.add(textArea);
        painelTextArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

    }

    private void initPainelSul() {
        initPainelComboBox();
        initPainelBotoes();
        painelSul.add(painelComboBox, BorderLayout.WEST);
        painelSul.add(painelBotoes, BorderLayout.EAST);
    }

    private void initPainelInfo() {
        //painelInfo.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        painelInfo.add(lbEmpresa);

    }

    private void initPainelCheckbox() {
        //Add components
        painelCheckbox.add(aprovado);
        painelCheckbox.add(reprovado);

        //Border
        //painelCheckbox.setBorder(new TitledBorder(new EtchedBorder(), TITULO_JANELA));
    }

    private void initPainelComboBox() {
        painelComboBox.add(eventoComboBox);
    }

    private void initPainelBotoes() {
        painelBotoes.add(sair);
        painelBotoes.add(guardar);
    }

    private void adicionarPaineis() {
        principal.add(painelTopo, BorderLayout.NORTH);
        principal.add(painelTextArea, BorderLayout.CENTER);
        principal.add(painelSul, BorderLayout.SOUTH);
        principal.setBorder(new EmptyBorder(EMPTY_BORDER_GAP, EMPTY_BORDER_GAP, EMPTY_BORDER_GAP, EMPTY_BORDER_GAP));
        add(principal);
    }

    private void copiarListaEventosParaListaComboBox() {
        listaModeloEventos.removeAllElements();
        for (Evento e : registoEvento) {
            if (e.getListaFae().isFaeEvento(f_user.getUsername())) {
                listaModeloEventos.addElement(e);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sair) {
            dispose();
        }
        if (e.getSource() == guardar) {

        }
        if (e.getSource() == aprovado) {
            if (reprovado.isSelected()) {
                reprovado.setSelected(false);
            }
            flag_aprovacao = true;
        }
        if (e.getSource() == reprovado) {
            if (aprovado.isSelected()) {
                aprovado.setSelected(false);
            }
            flag_aprovacao = false;
        }
        if (e.getSource() == eventoComboBox) {
            String temp = eventoComboBox.getSelectedItem().toString();
            if (!temp.equals(TXT_EVENTO_SELECIONADO)) {
                aprovado.setSelected(false);
                reprovado.setSelected(false);
                textArea.setText(TXT_VAZIO);
            }
        }
    }

}
