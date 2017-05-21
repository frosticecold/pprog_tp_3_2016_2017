package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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
import model.CentroEventos;
import model.candidatura.Atribuicao;
import model.candidatura.Decisao;
import model.evento.Evento;
import ui.misc.CustomCellRenderer;
import utils.Constantes;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class DecidirCandidatura extends JDialog implements ActionListener {

    //===================JPanels
    /**
     * Painel Principal da janela
     */
    private JPanel principal = new JPanel(new BorderLayout());

    //===================Buttons
    /**
     * Botão para guardar
     */
    private JButton guardar;
    /**
     * Botão para sair
     */
    private JButton sair;
    /**
     * Botão seguinte
     */
    private JButton seguinte;

    /**
     * Botão anterior
     */
    private JButton anterior;
    //===================JCheckbox
    /**
     * Checkbox aprovado
     */
    private JCheckBox aprovado;
    /**
     * Checkbox reprovado
     */
    private JCheckBox reprovado;

    //===================JTextArea
    /**
     * JTextfield para o texto justificativo
     */
    private JTextArea txtJustificativo;
    /**
     * JTextfield para a descrição da candidatura
     */
    private JTextArea txtDescricao;

    //===================JCombobox e DefaultComboBoxModel
    /**
     * Lista modelo de eventos
     */
    private DefaultComboBoxModel<Evento> listaModeloEventos;
    /**
     * JCombobox de eventos
     */
    private JComboBox<Evento> eventoComboBox;
    //===================JLabel
    private JLabel lbEmpresa;

    //===================Variáveis de instância utilizadas para decisões
    /**
     * Referência ao centro de eventos
     */
    private CentroEventos centroEventos;
    /**
     * Username do Fae
     */
    private String username;

    /**
     * Atribuição que é selecionada
     */
    private Atribuicao atrib;
    /**
     * Lista de atribuições para deslocar para trás/frente
     */
    private List<Atribuicao> listaAtrib;

    /**
     * Variável que guarda a decisão do utilizador
     */
    private int decisao_utilizador = Decisao.SEM_DECISAO;

    /**
     * Indice da lista de Atribuições
     */
    private int indice = 0;

    //===================Variáveis de classe
    /**
     * Distância dos componentes no painel Info
     */
    private static final int GAP_FLOWLAYOUT_WIDTH = 100, GAP_FLOWLAYOUT_HEIGHT = 0;

    /**
     * Construtor da JanelaDecidirCandidatura que recebe como parâmetro uma
     * referência da jframe pai, o centro de eventos e um username
     *
     * @param frame Referência JFrame
     * @param ce Referência CentroEventos
     * @param username Username do fae
     */
    public DecidirCandidatura(JFrame frame, CentroEventos ce, String username) {
        super(frame, Constantes.TITULO_BORDA_DECIDIR_CANDIDATURA, true);

        centroEventos = ce;
        this.username = username;

        initComponentes();

        setMinimumSize(Constantes.TAMANHO_DECIDIR_CANDIDATURA_MINIMO);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponentes() {
        initJLabel();
        initCheckboxes();
        initComboBox();
        initTxtArea();
        initBotoes();
        JPanel p1 = initPainelTopo();
        JPanel p2 = initPainelTxtArea();
        JPanel p3 = initPainelSul();
        adicionarPaineis(p1, p2, p3);

        copiarListaEventosParaListaComboBox();
        if (listaModeloEventos.getSize() > 0) {
            listaAtrib = ((Evento) eventoComboBox.getSelectedItem()).getListaAtribuicao().obterAtribuicoesAssociadaAoFae(username);
        }
    }

    private void initJLabel() {
        lbEmpresa = new JLabel(Constantes.TXT_LBL_EMPRESA);
    }

    private void initCheckboxes() {
        aprovado = new JCheckBox(Constantes.TXT_APROVADO);
        reprovado = new JCheckBox(Constantes.TXT_REPROVADO);
        //Atalhos
        aprovado.setMnemonic(KeyEvent.VK_A);
        reprovado.setMnemonic(KeyEvent.VK_R);

        //ActionListeners
        aprovado.addActionListener(this);
        reprovado.addActionListener(this);

    }

    private void initComboBox() {
        listaModeloEventos = new DefaultComboBoxModel<>();
        eventoComboBox = new JComboBox(listaModeloEventos);
        eventoComboBox.addActionListener(this);
        eventoComboBox.setRenderer(new CustomCellRenderer());
    }

    private void initTxtArea() {
        txtJustificativo = new JTextArea();
        txtDescricao = new JTextArea();

    }

    private void initBotoes() {
        //Criar botões
        guardar = new JButton(Constantes.TXT_GUARDAR);
        sair = new JButton(Constantes.TXT_SAIR);
        seguinte = new JButton();
        anterior = new JButton();

        //Definir icon ,action listeners e memónica
        definirIcon(anterior, Constantes.ICON_ANTERIOR);
        anterior.addActionListener(this);

        definirIcon(seguinte, Constantes.ICON_SEGUINTE);
        seguinte.addActionListener(this);

        sair.addActionListener(this);
        sair.setMnemonic(KeyEvent.VK_S);

        guardar.addActionListener(this);
        guardar.setMnemonic(KeyEvent.VK_G);
    }

    private JPanel initPainelTopo() {
        final int NUM_COL = 1, NUM_LINHAS = 2;
        JPanel painelTopo = new JPanel(new GridLayout(NUM_LINHAS, NUM_COL, Constantes.EMPTY_BORDER_GAP_DEZ, Constantes.EMPTY_BORDER_GAP_DEZ));
        painelTopo.add(initPainelInfo());
        painelTopo.add(initPainelAprovacao());
        painelTopo.setBorder(new TitledBorder(new EtchedBorder(), Constantes.TITULO_BORDA_DECIDIR_CANDIDATURA));
        return painelTopo;
    }

    private JPanel initPainelTxtArea() {
        final int NUM_COL = 1, NUM_LINHAS = 2;
        JPanel painelTextArea = new JPanel(new GridLayout(NUM_LINHAS, NUM_COL, Constantes.GAP_CINCO, Constantes.GAP_CINCO)),
                painelTextAreaNorte = new JPanel(new BorderLayout()),
                painelTextAreaSul = new JPanel(new BorderLayout());
        txtDescricao.setEditable(false);
        txtDescricao.setOpaque(false);
        painelTextAreaNorte.setBorder(new TitledBorder(new EtchedBorder(), "Descrição da Empresa"));
        painelTextAreaNorte.add(txtDescricao);

        painelTextAreaSul.setBorder(new TitledBorder(new EtchedBorder(), "Texto Justificativo"));
        painelTextAreaSul.add(txtJustificativo);

        painelTextArea.add(painelTextAreaNorte);
        painelTextArea.add(painelTextAreaSul);

        return painelTextArea;
    }

    private JPanel initPainelInfo() {
        JPanel painelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelInfo.add(lbEmpresa);
        return painelInfo;

    }

    private JPanel initPainelAprovacao() {
        JPanel painelAprovacao = new JPanel(new FlowLayout(FlowLayout.CENTER, GAP_FLOWLAYOUT_WIDTH, GAP_FLOWLAYOUT_HEIGHT));
        //Add components
        painelAprovacao.add(aprovado);
        painelAprovacao.add(reprovado);

        //Border
        //painelCheckbox.setBorder(new TitledBorder(new EtchedBorder(), TITULO_BORDA_DECIDIR_CANDIDATURA));
        return painelAprovacao;
    }

    private JPanel initPainelSul() {
        final int nrLinhas = 1, nrCol = 3;
        JPanel painelSul = new JPanel(new GridLayout(nrLinhas, nrCol)),
                painelSulEsq = new JPanel(new FlowLayout()),
                painelSulCentro = new JPanel(new FlowLayout()),
                painelSulDir = new JPanel(new FlowLayout());
        initPainelSulEsq(painelSulEsq);
        initPainelSulCentro(painelSulCentro);
        initPainelSulDir(painelSulDir);

        painelSul.add(painelSulEsq);
        painelSul.add(painelSulCentro);
        painelSul.add(painelSulDir);
        return painelSul;
    }

    private void initPainelSulEsq(JPanel painelSulEsq) {
        painelSulEsq.add(eventoComboBox);
    }

    private void initPainelSulCentro(JPanel painelSulCentro) {
        painelSulCentro.add(anterior);
        painelSulCentro.add(seguinte);
    }

    private void initPainelSulDir(JPanel painelSulDir) {
        painelSulDir.add(sair);
        painelSulDir.add(guardar);
    }

    private void adicionarPaineis(JPanel painelTopo, JPanel pTxtArea, JPanel pSul) {
        principal.add(painelTopo, BorderLayout.NORTH);
        principal.add(pTxtArea, BorderLayout.CENTER);
        principal.add(pSul, BorderLayout.SOUTH);
        principal.setBorder(new EmptyBorder(Constantes.EMPTY_BORDER_GAP_DEZ, Constantes.EMPTY_BORDER_GAP_DEZ, Constantes.EMPTY_BORDER_GAP_DEZ, Constantes.EMPTY_BORDER_GAP_DEZ));
        add(principal);
    }

    private void copiarListaEventosParaListaComboBox() {
        listaModeloEventos.removeAllElements();
        for (Evento e : centroEventos.getRegistoEventos()) {
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
        txtJustificativo.setText(Constantes.TXT_VAZIO);
        txtDescricao.setText(Constantes.TXT_VAZIO);
        decisao_utilizador = Decisao.SEM_DECISAO;
        aprovado.setSelected(false);
        reprovado.setSelected(false);

    }

    private void obterInformacaoJaGuardada() {
        Evento ev = (Evento) eventoComboBox.getSelectedItem();
        listaAtrib = ev.getListaAtribuicao().obterAtribuicoesAssociadaAoFae(username);
        if (!listaAtrib.isEmpty()) {
            atrib = listaAtrib.get(Constantes.INDICE_ZERO);
            if (atrib != null) {
                carregarInfoAtribuicao();
            }
        }
    }

    private void carregarInfoAtribuicao() {
        decisao_utilizador = atrib.getDecisao().getDecisao();
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
                    Constantes.mensagemErro(Constantes.ERRO_GUARDAR);
                }
            } else {
                Constantes.mensagemErro(Constantes.ERRO_SELECIONAR);
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
