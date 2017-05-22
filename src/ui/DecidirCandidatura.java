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

        setMinimumSize(Constantes.TAMANHO_MINIMO_DECIDIR_CANDIDATURA);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Inicializa todos os componentes utilizados na janela Copia os eventos
     * associados para a combobox Por fim vai buscar a lista de atribuições
     * associadas a um fae
     */
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

        copiarEventosParaListaComboBox();
        if (listaModeloEventos.getSize() > 0) {
            listaAtrib = ((Evento) eventoComboBox.getSelectedItem()).getListaAtribuicao().obterAtribuicoesAssociadaAoFae(username);
        }
    }

    /**
     * Inicializa a JLabel com o nome da empresa
     */
    private void initJLabel() {
        lbEmpresa = new JLabel(Constantes.TXT_LBL_EMPRESA);
    }

    /**
     * <ul>
     * <li>Inicializa as checkboxes aprovado e reprovado</li>
     * <li>Adiciona memónicas</li>
     * <li>Adiciona ActionListeners</li></ul>
     */
    private void initCheckboxes() {
        aprovado = new JCheckBox(Constantes.TXT_APROVADO);
        reprovado = new JCheckBox(Constantes.TXT_REPROVADO);
        //Atalhos
        aprovado.setMnemonic(KeyEvent.VK_A);
        reprovado.setMnemonic(KeyEvent.VK_R);

        //ActionCommands
        aprovado.setActionCommand(Constantes.CHKBX_APROVADO);
        reprovado.setActionCommand(Constantes.CHKBX_REPROVADO);
        //ActionListeners
        aprovado.addActionListener(this);
        reprovado.addActionListener(this);

    }

    /**
     * <ul>
     * <li>Inicializa a combox de evento</li>
     * <li>Inicializa a lista modelo de eventos</li>
     * <li>Adiciona ActionListeners</li>
     * </ul>
     */
    private void initComboBox() {
        listaModeloEventos = new DefaultComboBoxModel<>();
        eventoComboBox = new JComboBox(listaModeloEventos);
        eventoComboBox.addActionListener(this);
        eventoComboBox.setRenderer(new CustomCellRenderer());
        eventoComboBox.setActionCommand(Constantes.CMBBOX_EVENTO);
    }

    /**
     *
     * Inicializa as textArea de Texto Justificativo e Texto Descrição
     */
    private void initTxtArea() {
        txtJustificativo = new JTextArea();
        txtDescricao = new JTextArea();

    }

    /**
     * <ul>
     * <li>Inicializa os botões</li>
     * <li>Adicione os respetivos icones a cada botão</li>
     * <li>Adiciona memónicas</li>
     * <li>Action Listeners</li>
     * <li>Botões:</li>
     * <li>Guardar</li>
     * <li>Sair</li>
     * <li>Seguinte</li>
     * <li>Anterior</li>
     * </ul>
     */
    private void initBotoes() {
        //Criar botões
        guardar = new JButton(Constantes.BTN_GUARDAR);
        sair = new JButton(Constantes.BTN_SAIR);
        seguinte = new JButton();
        seguinte.setActionCommand(Constantes.BTN_SEGUINTE);
        anterior = new JButton();
        anterior.setActionCommand(Constantes.BTN_ANTERIOR);

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

    /**
     * Inicializa o painel do topo com dois paineis, painel de informação e
     * painel de aprovação Põe uma borda no painel
     *
     * @return JPanel
     */
    private JPanel initPainelTopo() {
        final int NUM_COL = 1, NUM_LINHAS = 2;
        JPanel painelTopo = new JPanel(new GridLayout(NUM_LINHAS, NUM_COL, Constantes.EMPTY_BORDER_GAP_DEZ, Constantes.EMPTY_BORDER_GAP_DEZ));
        painelTopo.add(initPainelInfo());
        painelTopo.add(initPainelAprovacao());
        painelTopo.setBorder(new TitledBorder(new EtchedBorder(), Constantes.TITULO_BORDA_DECIDIR_CANDIDATURA));
        return painelTopo;
    }

    /**
     * Inicializa o painel das TxtAreas Adiciona ambas txtareas ao painel
     *
     * @return JPanel
     */
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

    /**
     * Inicializa o painel com a informação de uma candidatura
     *
     * @return JPanel
     */
    private JPanel initPainelInfo() {
        JPanel painelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelInfo.add(lbEmpresa);
        return painelInfo;

    }

    /**
     * <ul>
     * <li>Inicializa o painel aprovação</li>
     * <li>Tem duas checkboxes, aprovado e reprovado</li>
     *
     * @return
     */
    private JPanel initPainelAprovacao() {
        JPanel painelAprovacao = new JPanel(new FlowLayout(FlowLayout.CENTER, GAP_FLOWLAYOUT_WIDTH, GAP_FLOWLAYOUT_HEIGHT));
        //Add components
        painelAprovacao.add(aprovado);
        painelAprovacao.add(reprovado);

        return painelAprovacao;
    }

    /**
     * Inicializa o painel Sul Tem três paineis, psulesq,pscentro,psuldir
     *
     * @return
     */
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

    /**
     * Painel Sul Esquerdo com a combobox de eventos
     *
     * @param painelSulEsq JPanel a adicionar a combobox
     */
    private void initPainelSulEsq(JPanel painelSulEsq) {
        painelSulEsq.add(eventoComboBox);
    }

    /**
     * Painel Sul Centrado com botões anterior e seguinte
     *
     * @param painelSulCentro JPanel a adicionar os botões
     */
    private void initPainelSulCentro(JPanel painelSulCentro) {
        painelSulCentro.add(anterior);
        painelSulCentro.add(seguinte);
    }

    /**
     * Painel Sul Direita com botões sair e guardar
     *
     * @param painelSulDir JPanel a adicionar os botões
     */
    private void initPainelSulDir(JPanel painelSulDir) {
        painelSulDir.add(sair);
        painelSulDir.add(guardar);
    }

    /**
     * Adiciona os paineis PainelTopo PainelTxtArea e PainelSul ao painel
     * principal
     *
     * @param painelTopo JPanel Topo com informação da candidatura e decisão
     * @param pTxtArea JPanel com textareas
     * @param pSul JPanel Sul com combobox e botões
     */
    private void adicionarPaineis(JPanel painelTopo, JPanel pTxtArea, JPanel pSul) {
        principal.add(painelTopo, BorderLayout.NORTH);
        principal.add(pTxtArea, BorderLayout.CENTER);
        principal.add(pSul, BorderLayout.SOUTH);
        principal.setBorder(new EmptyBorder(Constantes.EMPTY_BORDER_GAP_DEZ, Constantes.EMPTY_BORDER_GAP_DEZ, Constantes.EMPTY_BORDER_GAP_DEZ, Constantes.EMPTY_BORDER_GAP_DEZ));
        add(principal);
    }

    /**
     * Limpa a lista modelo de eventos e copia todos os eventos com atribuições
     * atribuidas ao fae
     */
    private void copiarEventosParaListaComboBox() {
        listaModeloEventos.removeAllElements();
        for (Evento e : centroEventos.getRegistoEventos()) {
            if (e.getListaFae().isFaeEvento(username) && e.getListaAtribuicao().verificarSeFaeTemAtribuicoes(username)) {
                listaModeloEventos.addElement(e);
            }
        }
    }

    /**
     * Valida se o texto tem mais de 5 caracteres
     *
     * @return Verdadeiro ou Falso
     */
    private boolean validarTexto() {
        if (txtJustificativo.getText().length() > 5) {
            return true;
        }

        return false;
    }

    /**
     * Limpa a informação de todos os componentes da janela
     */
    private void limparComponentes() {
        txtJustificativo.setText(Constantes.TXT_VAZIO);
        txtDescricao.setText(Constantes.TXT_VAZIO);
        decisao_utilizador = Decisao.SEM_DECISAO;
        aprovado.setSelected(false);
        reprovado.setSelected(false);

    }

    /**
     * Verifica se existem atribuições, se existirem, carrega a informação de
     * uma atribuição para os respetivos componentes
     */
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

    /**
     * Carrega a informação para os respetivos componentes
     */
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

    /**
     * ActionListeners
     *
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case Constantes.BTN_SAIR:
                dispose();
                break;
            case Constantes.BTN_GUARDAR:
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

                break;
            case Constantes.CHKBX_APROVADO:
                if (reprovado.isSelected()) {
                    reprovado.setSelected(false);
                }
                decisao_utilizador = Decisao.APROVADO;
                break;
            case Constantes.CHKBX_REPROVADO:
                if (aprovado.isSelected()) {
                    aprovado.setSelected(false);
                }
                decisao_utilizador = Decisao.NAO_APROVADO;
                break;
            case Constantes.CMBBOX_EVENTO:
                limparComponentes();
                obterInformacaoJaGuardada();

                break;
            case Constantes.BTN_ANTERIOR:

                if (indice > 0) {
                    indice--;
                    atrib = listaAtrib.get(indice);
                    limparComponentes();
                    carregarInfoAtribuicao();
                }
                break;
            case Constantes.BTN_SEGUINTE:
                if (indice < listaAtrib.size() - 1) {
                    indice++;
                    atrib = listaAtrib.get(indice);
                    limparComponentes();
                    carregarInfoAtribuicao();
                }
                break;
        }
    }

    /**
     * Define um icon de um botão
     *
     * @param cmp JButton a mudar o ícone
     * @param nomeficheiro Nome do ficheiro do icon
     */
    public void definirIcon(JButton cmp, String nomeficheiro) {
        cmp.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(Constantes.ICON_FOLDER + nomeficheiro)));
    }
}
