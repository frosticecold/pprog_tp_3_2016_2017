package system.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import system.CentroEventos;
import system.evento.Evento;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class DecidirCandidatura extends JFrame implements ActionListener {

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

    private JPanel principal = new JPanel(new BorderLayout()),
            painelCheckbox = new JPanel(new BorderLayout()),
            painelTextArea = new JPanel(new BorderLayout()),
            painelSul = new JPanel(new BorderLayout()),
            painelComboBox = new JPanel(new FlowLayout()),
            painelBotoes = new JPanel(new FlowLayout());

    //vars instância
    private CentroEventos ce;
    private boolean flag_aprovacao = false;

    //vars classe
    private static int EMPTY_BORDER_GAP = 10;
    private static Dimension TAMANHO_JANELA_MINIMO = new Dimension(450, 350);
    private static String TITULO_JANELA = "Decidir Candidatura", TITULO_BORDER = "Decisão";
    private static String ICON_FOLDER = "icons/";
    private static String ICON_APROVADO = "correct.gif", ICON_REPROVADO = "incorrect.gif";

    public DecidirCandidatura(CentroEventos ce) {
        super(TITULO_JANELA);
        this.ce = ce;
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
        //Add components
        painelCheckbox.add(reprovado, BorderLayout.WEST);
        painelCheckbox.add(aprovado, BorderLayout.EAST);

        //Border
        painelCheckbox.setBorder(new TitledBorder(new EtchedBorder(), TITULO_JANELA));
    }

    private void initPainelCentro() {
        painelTextArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        painelTextArea.add(textArea);
    }

    private void initPainelComboBox() {
        painelComboBox.add(eventoComboBox);
    }

    private void initPainelBotoes() {
        painelBotoes.add(sair);
        painelBotoes.add(guardar);
    }

    private void initPainelSul() {
        initPainelComboBox();
        initPainelBotoes();
        painelSul.add(painelComboBox, BorderLayout.WEST);
        painelSul.add(painelBotoes, BorderLayout.EAST);
    }

    private void adicionarPaineis() {
        principal.add(painelCheckbox, BorderLayout.NORTH);
        principal.add(painelTextArea, BorderLayout.CENTER);
        principal.add(painelSul, BorderLayout.SOUTH);
        principal.setBorder(new EmptyBorder(EMPTY_BORDER_GAP, EMPTY_BORDER_GAP, EMPTY_BORDER_GAP, EMPTY_BORDER_GAP));
        add(principal);
    }

    private void copiarListaEventosParaListaComboBox(){
        for (Evento e : ce.getRegistoEventos()) {
            listaModeloEventos.addElement(e);
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
    }

}
