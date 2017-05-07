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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import system.candidatura.Atribuicao;
import system.candidatura.Decisao;
import system.evento.Evento;
import system.listas.RegistoEvento;
import system.user.Fae;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class DecidirCandidatura extends JDialog implements ActionListener {

    //Checkboxes
    private JCheckBox aprovado = new JCheckBox(TXT_APROVADO), reprovado = new JCheckBox(TXT_REPROVADO);
    //TextArea
    private JTextArea txtJustificativo = new JTextArea(), txtDescricao = new JTextArea();
    //ComboBox Eventos
    private DefaultComboBoxModel<Evento> listaModeloEventos = new DefaultComboBoxModel<>();
    private JComboBox<Evento> eventoComboBox = new JComboBox(listaModeloEventos);
    //Botoes
    private JButton guardar = new JButton(TXT_GUARDAR),
            sair = new JButton(TXT_SAIR);

    private JLabel lbEmpresa = new JLabel(TXT_LBL_EMPRESA);
    private JPanel principal = new JPanel(new BorderLayout()),
            painelTopo = new JPanel(new GridLayout(NUM_LINHAS, NUM_COL, EMPTY_BORDER_GAP, EMPTY_BORDER_GAP)),
            painelCheckbox = new JPanel(new FlowLayout(FlowLayout.CENTER, GAP_FLOWLAYOUT_WIDTH, GAP_FLOWLAYOUT_HEIGHT)),
            painelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT)),
            painelTextArea = new JPanel(new GridLayout(2, 1, 5, 5)),
            painelTextAreaNorte = new JPanel(new BorderLayout()),
            painelTextAreaSul = new JPanel(new BorderLayout()),
            painelSul = new JPanel(new BorderLayout()),
            painelComboBox = new JPanel(new FlowLayout()),
            painelBotoes = new JPanel(new FlowLayout());

    //vars instância
    private RegistoEvento registoEvento;
    private Fae f_user;
    private int decisao_utilizador = Decisao.SEM_DECISAO;
    private Atribuicao atrib;

    //vars classe    
    private static String TXT_EVENTO_SELECIONADO = "";
    private static final int GAP_FLOWLAYOUT_WIDTH = 100, GAP_FLOWLAYOUT_HEIGHT = 0;
    private static final int NUM_COL = 1, NUM_LINHAS = 2;
    private static final int EMPTY_BORDER_GAP = 10;
    private static final Dimension TAMANHO_JANELA_MINIMO = new Dimension(450, 350);
    private static final String TITULO_JANELA = "Decidir Candidatura", TITULO_BORDER = "Decisão";
    private static final String ICON_FOLDER = "icons/";
    private static final String ICON_APROVADO = "correct.gif", ICON_REPROVADO = "incorrect.gif";
    private static final String TXT_LBL_EMPRESA = "Empresa: ", TXT_LBL_REPRESENTANTE = "Representante: ";
    private static final String TXT_VAZIO = "";
    private static final String TXT_APROVADO = "Aprovado", TXT_REPROVADO = "Reprovado";
    private static final String TXT_GUARDAR = "Guardar", TXT_SAIR = "Sair";
    private static final String ERRO_GUARDAR = "Texto Inválido", ERRO_TITULO = "Erro";

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
        initPainelTxtArea();
        initPainelSul();
        adicionarPaineis();

        //Teste
        copiarListaEventosParaListaComboBox();
        TXT_EVENTO_SELECIONADO = eventoComboBox.getSelectedItem().toString();
        atrib = ((Evento) eventoComboBox.getSelectedItem()).getListaAtribuicao().obterAtribuicaoAssociadaAoFae(f_user);
        //txt.setText(atrib.getCandidatura().getDescricao());
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

        initPainelInfo();
        initPainelCheckbox();
        painelTopo.add(painelInfo);
        painelTopo.add(painelCheckbox);
        painelTopo.setBorder(new TitledBorder(new EtchedBorder(), TITULO_JANELA));
    }

    private void initPainelTxtArea() {
        txtDescricao.setEditable(false);
        txtDescricao.setOpaque(false);
        painelTextAreaNorte.setBorder(new TitledBorder("Descrição da Empresa"));
        painelTextAreaNorte.add(txtDescricao);

        painelTextAreaSul.setBorder(new TitledBorder("Texto Justificativo"));
        painelTextAreaSul.add(txtJustificativo);

        painelTextArea.add(painelTextAreaNorte);
        painelTextArea.add(painelTextAreaSul);

        //painelTextArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
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

    private boolean validarTexto() {
        if (txtJustificativo.getText().length() > 5 && !txtJustificativo.getText().equals(Decisao.SEM_TEXTO)) {
            return true;
        }

        return false;
    }

    private void limparComponentes() {
        decisao_utilizador = Decisao.SEM_DECISAO;
        aprovado.setSelected(false);
        reprovado.setSelected(false);
        txtJustificativo.setText(TXT_VAZIO);
        txtDescricao.setText(TXT_VAZIO);
    }

    private void obterInformacaoJaGuardada() {
        Evento ev = (Evento) eventoComboBox.getSelectedItem();
        Atribuicao atrib = ev.getListaAtribuicao().obterAtribuicaoAssociadaAoFae(f_user);
        if (atrib.getDecisao().getAprovacao() == Decisao.APROVADO) {
            aprovado.setSelected(true);
            reprovado.setSelected(false);
        } else if (atrib.getDecisao().getAprovacao() == Decisao.NAO_APROVADO) {
            aprovado.setSelected(false);
            reprovado.setSelected(true);
        } else {
            aprovado.setSelected(false);
            reprovado.setSelected(false);
        }
        lbEmpresa.setText(atrib.getCandidatura().getRepresentanteEmpresa().getNomeEmpresa());
        txtDescricao.setText(atrib.getCandidatura().getDescricao());
        txtJustificativo.setText(atrib.getDecisao().getTextoJustificativo());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sair) {
            dispose();
        }
        if (e.getSource() == guardar) {
            if (aprovado.isSelected() || reprovado.isSelected()) {
                Evento ev = (Evento) eventoComboBox.getSelectedItem();
                if (validarTexto()) {
                    atrib.setDecisao(new Decisao(decisao_utilizador, txtJustificativo.getText()));
                    System.out.println("Decisao: " + atrib.getDecisao());
                } else {
                    JOptionPane.showMessageDialog(this, ERRO_GUARDAR, ERRO_TITULO, JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (e.getSource() == aprovado) {
            if (reprovado.isSelected()) {
                reprovado.setSelected(false);
            }
            decisao_utilizador = Decisao.APROVADO;
        }
        if (e.getSource() == reprovado) {
            if (aprovado.isSelected()) {
                aprovado.setSelected(false);
            }
            decisao_utilizador = Decisao.NAO_APROVADO;
        }
        if (e.getSource() == eventoComboBox) {
            //String temp = eventoComboBox.getSelectedItem().toString();
            //if (!temp.equals(TXT_EVENTO_SELECIONADO)) {
            limparComponentes();
            obterInformacaoJaGuardada();
            atrib = ((Evento) eventoComboBox.getSelectedItem()).getListaAtribuicao().obterAtribuicaoAssociadaAoFae(f_user);
            decisao_utilizador = atrib.getDecisao().getAprovacao();
            //}
        }
    }

}
