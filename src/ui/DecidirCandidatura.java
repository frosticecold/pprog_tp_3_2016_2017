package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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
import system.candidatura.Candidatura;
import system.candidatura.Decisao;
import system.evento.Evento;
import system.listas.RegistoEvento;
import utils.Constantes;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class DecidirCandidatura extends JDialog implements ActionListener {

    //Checkboxes
    private JCheckBox aprovado = new JCheckBox(Constantes.TXT_APROVADO), reprovado = new JCheckBox(Constantes.TXT_REPROVADO);
    //TextArea
    private JTextArea txtJustificativo = new JTextArea(), txtDescricao = new JTextArea();
    //ComboBox Eventos
    private DefaultComboBoxModel<Evento> listaModeloEventos = new DefaultComboBoxModel<>();
    private JComboBox<Evento> eventoComboBox = new JComboBox(listaModeloEventos);

    private DefaultComboBoxModel<Candidatura> listaModeloCandidaturas = new DefaultComboBoxModel<Candidatura>();
    private JComboBox<Candidatura> cmboxCandidatura = new JComboBox<Candidatura>(listaModeloCandidaturas);
    //Botoes
    private JButton guardar = new JButton(Constantes.TXT_GUARDAR),
            sair = new JButton(Constantes.TXT_SAIR);
    private JButton seguinte = new JButton();
    private JButton anterior = new JButton();

    private JLabel lbEmpresa = new JLabel(Constantes.TXT_LBL_EMPRESA);
    private JPanel principal = new JPanel(new BorderLayout()),
            painelTopo = new JPanel(new GridLayout(NUM_LINHAS, NUM_COL, Constantes.EMPTY_BORDER_GAP_DEZ, Constantes.EMPTY_BORDER_GAP_DEZ)),
            painelCheckbox = new JPanel(new FlowLayout(FlowLayout.CENTER, GAP_FLOWLAYOUT_WIDTH, GAP_FLOWLAYOUT_HEIGHT)),
            painelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT)),
            painelTextArea = new JPanel(new GridLayout(NUM_LINHAS, NUM_COL, Constantes.GAP_CINCO, Constantes.GAP_CINCO)),
            painelTextAreaNorte = new JPanel(new BorderLayout()),
            painelTextAreaSul = new JPanel(new BorderLayout()),
            painelSul = new JPanel(new GridLayout(1, 3)),
            painelSulEsq = new JPanel(new FlowLayout()),
            painelSulCentro = new JPanel(new FlowLayout()),
            painelSulDir = new JPanel(new FlowLayout());

    //vars instância
    private RegistoEvento registoEvento;
    private String username;

    private Atribuicao atrib;
    private List<Atribuicao> listaAtrib;

    private int decisao_utilizador = Decisao.SEM_DECISAO;
    private int indice = 0;

    //vars classe    
    private static final int GAP_FLOWLAYOUT_WIDTH = 100, GAP_FLOWLAYOUT_HEIGHT = 0;
    private static final int NUM_COL = 1, NUM_LINHAS = 2;
    private static final int PRIMEIRO_ELEMENTO = 0;

    public DecidirCandidatura(JFrame frame, RegistoEvento re, String username) {
        super(frame, Constantes.TITULO_JANELA, true);

        registoEvento = re;
        this.username = username;

        initComponentes();

        setMinimumSize(Constantes.TAMANHO_DECIDIR_CANDIDATURA_MINIMO);
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

        copiarListaEventosParaListaComboBox();
        listaAtrib = ((Evento) eventoComboBox.getSelectedItem()).getListaAtribuicao().obterAtribuicoesAssociadaAoFae(username);
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

        definirIcon(anterior, Constantes.ICON_ANTERIOR);
        anterior.addActionListener(this);

        definirIcon(seguinte, Constantes.ICON_SEGUINTE);
        seguinte.addActionListener(this);

        sair.addActionListener(this);
        sair.setMnemonic(KeyEvent.VK_S);

        guardar.addActionListener(this);
        guardar.setMnemonic(KeyEvent.VK_G);
    }

    private void initPainelTopo() {

        initPainelInfo();
        initPainelAprovacao();
        painelTopo.add(painelInfo);
        painelTopo.add(painelCheckbox);
        painelTopo.setBorder(new TitledBorder(new EtchedBorder(), Constantes.TITULO_JANELA));
    }

    private void initPainelTxtArea() {
        txtDescricao.setEditable(false);
        txtDescricao.setOpaque(false);
        painelTextAreaNorte.setBorder(new TitledBorder(new EtchedBorder(),"Descrição da Empresa"));
        painelTextAreaNorte.add(txtDescricao);

        painelTextAreaSul.setBorder(new TitledBorder(new EtchedBorder(),"Texto Justificativo"));
        painelTextAreaSul.add(txtJustificativo);

        painelTextArea.add(painelTextAreaNorte);
        painelTextArea.add(painelTextAreaSul);

        //painelTextArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
    }

    private void initPainelInfo() {
        //painelInfo.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        painelInfo.add(lbEmpresa);

    }

    private void initPainelAprovacao() {
        //Add components
        painelCheckbox.add(aprovado);
        painelCheckbox.add(reprovado);

        //Border
        //painelCheckbox.setBorder(new TitledBorder(new EtchedBorder(), TITULO_JANELA));
    }

    private void initPainelSul() {
        initPainelSulEsq();
        initPainelSulCentro();
        initPainelSulDir();
        painelSul.add(painelSulEsq);
        painelSul.add(painelSulCentro);
        painelSul.add(painelSulDir);
    }

    private void initPainelSulEsq() {
        painelSulEsq.add(eventoComboBox);
    }

    private void initPainelSulDir() {
        painelSulDir.add(sair);
        painelSulDir.add(guardar);
    }

    private void initPainelSulCentro() {
        painelSulCentro.add(anterior);
        painelSulCentro.add(seguinte);
    }

    private void adicionarPaineis() {
        principal.add(painelTopo, BorderLayout.NORTH);
        principal.add(painelTextArea, BorderLayout.CENTER);
        principal.add(painelSul, BorderLayout.SOUTH);
        principal.setBorder(new EmptyBorder(Constantes.EMPTY_BORDER_GAP_DEZ, Constantes.EMPTY_BORDER_GAP_DEZ, Constantes.EMPTY_BORDER_GAP_DEZ, Constantes.EMPTY_BORDER_GAP_DEZ));
        add(principal);
    }

    private void copiarListaEventosParaListaComboBox() {
        listaModeloEventos.removeAllElements();
        for (Evento e : registoEvento) {
            if (e.getListaFae().isFaeEvento(username) && e.getListaAtribuicao().verificarSeFaeTemAtribuicoes(username)) {
                listaModeloEventos.addElement(e);
            }
        }
    }

    private boolean validarTexto() {
        if (txtJustificativo.getText().length() > 5) {
            return true;
        }

        return false;
    }

    private void limparComponentes() {
        decisao_utilizador = Decisao.SEM_DECISAO;
        aprovado.setSelected(false);
        reprovado.setSelected(false);
        txtJustificativo.setText(Constantes.TXT_VAZIO);
        txtDescricao.setText(Constantes.TXT_VAZIO);
    }

    private void obterInformacaoJaGuardada() {
        Evento ev = (Evento) eventoComboBox.getSelectedItem();
        listaAtrib = ev.getListaAtribuicao().obterAtribuicoesAssociadaAoFae(username);
        if (!listaAtrib.isEmpty()) {
            atrib = listaAtrib.get(PRIMEIRO_ELEMENTO);
            if (atrib != null) {
                carregarInfoAtribuicao();
            }
        }
    }

    private void carregarInfoAtribuicao() {
        decisao_utilizador = atrib.getDecisao().getAprovacao();
        switch (decisao_utilizador) {
            case Decisao.APROVADO:
                aprovado.setSelected(true);
                reprovado.setSelected(false);
                break;
            case Decisao.NAO_APROVADO:
                aprovado.setSelected(false);
                reprovado.setSelected(true);
                break;
            default:
                aprovado.setSelected(false);
                reprovado.setSelected(false);
                break;
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
                } else {
                    JOptionPane.showMessageDialog(this, Constantes.ERRO_GUARDAR, Constantes.ERRO_TITULO, JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, Constantes.ERRO_SELECIONAR, Constantes.ERRO_TITULO, JOptionPane.ERROR_MESSAGE);
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
            limparComponentes();
            obterInformacaoJaGuardada();

        }
        if (e.getSource() == anterior) {
            if (indice > 0) {
                indice--;
                atrib = listaAtrib.get(indice);
                limparComponentes();
                carregarInfoAtribuicao();
            }
        }
        if (e.getSource() == seguinte) {
            if (indice < listaAtrib.size() - 1) {
                indice++;
                atrib = listaAtrib.get(indice);
                limparComponentes();
                carregarInfoAtribuicao();
            }
        }
    }

    public void definirIcon(JButton cmp, String nomeficheiro) {
        cmp.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(Constantes.ICON_FOLDER + nomeficheiro)));
    }
}
