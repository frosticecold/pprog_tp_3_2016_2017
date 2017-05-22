package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.CentroEventos;
import model.evento.Evento;
import model.listas.ListaFae;
import model.listas.RegistoAlgoritmosAtribuicao;
import model.user.Fae;
import model.algoritmo.AlgoritmoAtribuicao;
import model.candidatura.Atribuicao;
import model.candidatura.Candidatura;
import model.listas.ListaCandidatura;
import model.listas.ListaAtribuicao;
import model.listas.RegistoEvento;
import ui.misc.CustomCellRenderer;
import utils.Constantes;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class AtribuirCandidatura extends JDialog implements ActionListener, ListSelectionListener {

    //===================JPanels
    /**
     * Painel Principal da janela
     */
    private JPanel principal;

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
     * Botão para atribuir
     */
    private JButton atribui;

    //===================DefaultListModels
    /**
     * Lista modelo com faes disponíveis
     */
    private DefaultListModel<Fae> listaModeloFaeDisponiveis;
    /**
     * Lista modelo com faes atribuidos
     */
    private DefaultListModel<Fae> listaModeloFaeAtribuidos;
    /**
     * Lista modelo com as candidaturas
     */
    private DefaultListModel<Candidatura> listaModeloCandidatura;

    //===================DefaultComboboxModel
    /**
     * Lista modelo com eventos
     */
    private DefaultComboBoxModel<Evento> listaModeloEvento;
    /**
     * Lista modelo com algoritmos
     */
    private DefaultComboBoxModel<AlgoritmoAtribuicao> listaModeloAlgoritmo;

    //===================JLists
    /**
     * Jlist de faes disponiveis
     */
    private JList jlistFaeDisponiveis;
    /**
     * Jlist de candidaturas
     */
    private JList jlistCandidaturas;
    /**
     * Jlist de faes atribuidos
     */
    private JList jlistFaeAtribuidos;

    //===================JScrollPane
    /**
     * Scrollpane associado à jlist de faes disponiveis
     */
    private JScrollPane spFaeDisponiveis;

    /**
     * Scrollpane associado à jlist de candidaturas
     */
    private JScrollPane spCandidaturas;
    /**
     * Scrollpane associado à jlist de fae atribuidos
     */
    private JScrollPane spFaeAtribuidos;

    //===================JCombobox
    /**
     * JCombobox de algoritmos de atribuição
     */
    private JComboBox<AlgoritmoAtribuicao> algoritmoComboBox;
    /**
     * JCombobox de eventos disponíveis
     */
    private JComboBox<Evento> eventoComboBox;

    //Vars de Instancia
    /**
     * Referência ao centro de eventos
     */
    private final CentroEventos ce;

    /**
     * Username do organizador
     */
    private String username;

    /**
     * Lista de atribuições
     */
    private List<Atribuicao> listaAtribuicoes;

    //Vars Estáticos
    /**
     * Número de linhas e colunas do painel central
     */
    private static final int NR_LINHAS = 1, NR_COLUNAS = 3;

    /**
     * Construtor da jdialog atribuir candidaturas que recebe como parâmetro a
     * referência a uma jframe e um centro de eventos
     *
     * @param frame referência à jframe
     * @param ce referência ao centro de eventos
     */
    public AtribuirCandidatura(JFrame frame, CentroEventos ce, String username) {
        super(frame, Constantes.TITULO_JANELA_ATRIBUIR, true);

        this.ce = ce;
        this.username = username;
        initComponents();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(Constantes.TAMANHO_MINIMO_JANELA_ATRIBUIR_CAND);
        setLocationRelativeTo(null);

        pack();
        setVisible(true);
    }

    /**
     * Inicializa todos os componentes por ordem de utilização
     *
     * - Listas - Combobox - Botões - Paineis - Carregar - Valores de Arranque
     */
    public final void initComponents() {
        initLists();
        initComboBox();
        initButtons();
        initPanels();
        verificarValoresArranque();
    }

    /**
     * Inicializa todos os tipos de listas utilizadas
     */
    public void initLists() {
        //Iniciar ArrayLists
        listaAtribuicoes = new ArrayList<>();

        //Criar lista modelos
        listaModeloFaeDisponiveis = new DefaultListModel<>();
        listaModeloFaeAtribuidos = new DefaultListModel<>();
        listaModeloCandidatura = new DefaultListModel<>();
        listaModeloEvento = new DefaultComboBoxModel<>();
        listaModeloAlgoritmo = new DefaultComboBoxModel<>();

        //Criar Scrollpanes
        spFaeDisponiveis = new JScrollPane();
        spCandidaturas = new JScrollPane();
        spFaeAtribuidos = new JScrollPane();

        //Criar jlists
        jlistFaeDisponiveis = new JList<>(listaModeloFaeDisponiveis);
        jlistCandidaturas = new JList<>(listaModeloCandidatura);
        jlistFaeAtribuidos = new JList<>(listaModeloFaeAtribuidos);

        //Custom Renderers
        jlistFaeDisponiveis.setCellRenderer(new CustomCellRenderer());
        jlistCandidaturas.setCellRenderer(new CustomCellRenderer());
        jlistFaeAtribuidos.setCellRenderer(new CustomCellRenderer());

        jlistFaeDisponiveis.setLayoutOrientation(JList.VERTICAL);
        jlistCandidaturas.setLayoutOrientation(JList.VERTICAL);
        jlistFaeAtribuidos.setLayoutOrientation(JList.VERTICAL);

        spFaeDisponiveis.setViewportView(jlistFaeDisponiveis);
        spCandidaturas.setViewportView(jlistCandidaturas);
        spFaeAtribuidos.setViewportView(jlistFaeAtribuidos);

        //Listeners
        jlistCandidaturas.addListSelectionListener(this);

        jlistFaeDisponiveis.setToolTipText(Constantes.TOOLTIP_ATRIB_CAND_JLIST_FAEDISPONIVEIS);
        jlistCandidaturas.setToolTipText(Constantes.TOOLTIP_ATRIB_CAND_JLIST_LISTA_CANDIDATURAS);
        jlistFaeAtribuidos.setToolTipText(Constantes.TOOLTIP_ATRIB_CAND_JLIST_FAE_ATRIB_POR_CAND);

    }

    /**
     * Inicializa/Cria todos os painéis a ser utilizados pela aplicação
     */
    public void initPanels() {
        principal = new JPanel(new BorderLayout());
        JPanel pcentro = new JPanel(new GridLayout(NR_LINHAS, NR_COLUNAS, Constantes.GAP_DEZ, Constantes.GAP_DEZ));
        JPanel psul = new JPanel(new BorderLayout());
        JPanel psulesq = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel psuldir = new JPanel(new FlowLayout());
        initPainelCentro(pcentro);
        initPainelSul(psul, psulesq, psuldir);
        initPainelPrincipal(pcentro, psul);
    }

    /**
     * Adiciona os paineis centro e sul ao painel principal
     *
     * @param pcentro painel centro
     * @param psul painel sul
     */
    public void initPainelPrincipal(JPanel pcentro, JPanel psul) {
        principal.setBorder(new EmptyBorder(Constantes.GAP_DEZ, Constantes.GAP_DEZ, Constantes.GAP_ZERO, Constantes.GAP_DEZ));
        principal.add(pcentro, BorderLayout.CENTER);
        principal.add(psul, BorderLayout.SOUTH);
        add(principal);
    }

    /**
     * Adiciona os scrollpanes ao painel central
     *
     * @param pcentro
     */
    public void initPainelCentro(JPanel pcentro) {
        pcentro.add(spFaeDisponiveis);
        pcentro.add(spCandidaturas);
        pcentro.add(spFaeAtribuidos);

    }

    /**
     * <ul>
     * <li>Adiciona combobox e botões ao painel sul esquerdo</li>
     * <li>Adiciona botões sair e guardar ao painel sul direito</li>
     * <li>Adiciona ambos paineis anteriores ao painel sul</li>
     * </ul>
     *
     * @param psul Painel sul
     * @param psulesq Painel sul esquerdo
     * @param psuldir Painel sul direito
     */
    public void initPainelSul(JPanel psul, JPanel psulesq, JPanel psuldir) {

        //Adiciona componentes ao painel sul esquerdo
        psulesq.add(eventoComboBox);
        psulesq.add(algoritmoComboBox);
        psulesq.add(atribui);

        //Adiciona componentes ao painel sul direito
        psuldir.add(sair);
        psuldir.add(guardar);

        //Adiciona os paineis anteriores ao painel sul
        psul.add(psulesq, BorderLayout.WEST);
        psul.add(psuldir, BorderLayout.EAST);
    }

    /**
     * <ul>
     * <li>Cria/inicializa os botões da aplicação</li>
     * <li>Adiciona tamanho preferido</li>
     * <li>Adiciona memónicas</li>
     * <li>Adiciona tooltips</li>
     * <li>Adiciona actionlisteners</li>
     * </ul>
     */
    public void initButtons() {

        //Criar os botões
        guardar = new JButton(Constantes.BTN_GUARDAR);
        sair = new JButton(Constantes.BTN_SAIR);
        atribui = new JButton(Constantes.BTN_ATRIBUI);

        //Definir tamanho predefinido
        atribui.setPreferredSize(Constantes.ATRIB_CAND_BTN_DIMENSION);
        sair.setPreferredSize(Constantes.ATRIB_CAND_BTN_DIMENSION);
        guardar.setPreferredSize(Constantes.ATRIB_CAND_BTN_DIMENSION);

        //Definir as memónicas
        atribui.setMnemonic(KeyEvent.VK_A);
        sair.setMnemonic(KeyEvent.VK_S);
        guardar.setMnemonic(KeyEvent.VK_G);

        //Adicionar Tooltips
        atribui.setToolTipText(Constantes.TOOLTIP_ATRIB_CAND_ATRIBUIR);
        sair.setToolTipText(Constantes.TOOLTIP_ATRIB_CAND_SAIR);
        guardar.setToolTipText(Constantes.TOOLTIP_ATRIB_CAND_GUARDAR);

        //Adicionar os actionlisteners
        atribui.addActionListener(this);
        sair.addActionListener(this);
        guardar.addActionListener(this);
    }

    /**
     * <ul>
     * <li>Inicializa/cria as combobox</li>
     * <li>Define listasmodelo</li>
     * <li>Define tamanho das combobox</li>
     * <li>Adiciona actionlisteners</li>
     * <li>Copia os dados necessários ao seu funcionamento</li>
     * </ul>
     */
    public void initComboBox() {
        //Criar combobox
        algoritmoComboBox = new JComboBox<>();
        eventoComboBox = new JComboBox<>();

        //Associar listas modelos às devidas combobox
        eventoComboBox.setModel(listaModeloEvento);
        algoritmoComboBox.setModel(listaModeloAlgoritmo);

        //Definir tamanho das combobox
        eventoComboBox.setPreferredSize(Constantes.ATRIB_CAND_COMBOBOX_DIMENSION);
        algoritmoComboBox.setPreferredSize(Constantes.ATRIB_CAND_COMBOBOX_DIMENSION);

        //Definir action listeners
        eventoComboBox.addActionListener(this);

        //Definir Tooltip
        eventoComboBox.setToolTipText(Constantes.MENSAGEM_EVENTO);
        algoritmoComboBox.setToolTipText(Constantes.MENSAGEM_ALGORITMO);

        //Definir Custom Renderer
        eventoComboBox.setRenderer(new CustomCellRenderer());

        //Preencher listas
        copiarListaEventosParaListaModeloEventos(ce.getRegistoEventos());
        copiarListaAlgoritmosParaComboBox(ce.getRegistoAlgoritmosAtribuicao());

    }

    /**
     * Verifica se ao arrancar a aplicação já existem dados anteriores, se
     * existerem carrega a informação para os devidos componentes
     */
    public void verificarValoresArranque() {
        if (ce.getRegistoEventos().tamanho() != 0) {
            Evento ev = (Evento) eventoComboBox.getSelectedItem();
            if (ev != null && ev.getListaFae().tamanho() != 0) {
                copiarListaFaeDisponiveis(ev.getListaFae());
                copiarListaCandidaturasParalmCandidatura(ev.getListaCandidatura());
            }
        }

    }

    /**
     * Copia as candidaturas de uma lista de candidatura para a lista modelo de
     * candidaturas
     *
     * @param lc Lista do tipo ListaCandidatura com candidaturas
     */
    private void copiarListaCandidaturasParalmCandidatura(ListaCandidatura lc) {
        listaModeloCandidatura.clear();
        for (Candidatura c : lc) {
            listaModeloCandidatura.addElement(c);

        }

    }

    /**
     * Copia os faes de uma lista de fae para a lista modelo de faes
     *
     * @param listaFae Lista do tipo ListaFae com faes
     */
    private void copiarListaFaeDisponiveis(ListaFae listaFae) {
        listaModeloFaeDisponiveis.clear();
        for (Fae f : listaFae) {
            listaModeloFaeDisponiveis.addElement(f);
        }
    }

    /**
     * Encontra os faes com a associação a uma mesma candidatura e copiaos para
     * a lista modelo de fae atribuidos
     *
     * @param la Lista de Atribuições
     * @param c Candidatura a procurar
     */
    private void copiarListaFaePorCandidatura(List<Atribuicao> la, Candidatura c) {
        listaModeloFaeAtribuidos.clear();
        for (Atribuicao a : la) {
            if (a.getCandidatura().equals(c)) {
                listaModeloFaeAtribuidos.addElement(a.getFae());
            }
        }
    }

    /**
     * Copia todas as atribuições de uma ListaAtribuição que contenha aquela
     * candidatura
     *
     * @param listaAtrib ListraAtribuição com as aitribuições
     * @param cd Candidatura a procurar
     */
    private void copiarListaFaePorCandidaturaJaGuardada(ListaAtribuicao listaAtrib, Candidatura cd) {
        listaModeloFaeAtribuidos.clear();
        for (Atribuicao a : listaAtrib) {
            if (a.getCandidatura().equals(cd)) {
                listaModeloFaeAtribuidos.addElement(a.getFae());
            }
        }
    }

    /**
     * Copia todos os algoritmos de atribuição para a combobox
     *
     * @param ra RegistoAlgoritmosAtribuicao Onde estão guardados os algoritmos
     */
    private void copiarListaAlgoritmosParaComboBox(RegistoAlgoritmosAtribuicao ra) {
        listaModeloAlgoritmo.removeAllElements();
        for (String s : ra) {
            try {
                String nomeClasse = RegistoAlgoritmosAtribuicao.CLASSPATH + s;
                AlgoritmoAtribuicao a = (AlgoritmoAtribuicao) Class.forName(nomeClasse).newInstance();
                listaModeloAlgoritmo.addElement(a);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                JOptionPane.showMessageDialog(this, Constantes.ERRO_INSTANCIAR_ALGORITMO, Constantes.ERRO_TITULO, JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    /**
     * Copia todos os eventos da lista RegistoEvento para a lista modelo
     *
     * @param registoEventos RegistoEventos onde estão guardado os eventos
     */
    private void copiarListaEventosParaListaModeloEventos(RegistoEvento registoEventos) {
        listaModeloEvento.removeAllElements();
        for (Evento e : ce.getRegistoEventos().getListEventoPorUtilizadorPreDefinido(username, RegistoEvento.UTILIZADOR_ORGANIZADOR)) {
            listaModeloEvento.addElement(e);
        }
    }

    /**
     * Limpa todas as listas da janela AtribuirCandidatura
     */
    private void limparListas() {
        listaModeloFaeDisponiveis.clear();
        listaModeloCandidatura.clear();
        listaModeloFaeAtribuidos.clear();
        listaAtribuicoes.clear();
    }

    /**
     * Método onde é feito as decisões dos botões
     *
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //Se sair
        if (e.getSource() == sair) {
            dispose();
        }

        //Se carregar no botão atribuir
        if (e.getSource() == atribui) {
            Evento ev = (Evento) eventoComboBox.getSelectedItem();
            if (ev.getListaCandidatura().tamanho() == 0) {
                JOptionPane.showMessageDialog(this, Constantes.MENSAGEM_ERRO_SEM_CANDIDATURAS,
                        Constantes.ERRO_TITULO, JOptionPane.ERROR_MESSAGE);
            } else {
                listaAtribuicoes = ((AlgoritmoAtribuicao) algoritmoComboBox.getSelectedItem()).atribui(ev);
                if (jlistCandidaturas.getSelectedIndex() != -1) {
                    int num = jlistCandidaturas.getSelectedIndex();
                    jlistCandidaturas.clearSelection();
                    jlistCandidaturas.setSelectedIndex(num);
                } else {
                    jlistCandidaturas.setSelectedIndex(Constantes.INDICE_ZERO);
                }

            }
        }

        //Se carregar no botão guardar
        if (e.getSource() == guardar) {
            if (!listaAtribuicoes.isEmpty()) {
                Evento ev = (Evento) eventoComboBox.getSelectedItem();
                ev.getListaAtribuicao().limparLista();
                ev.getListaAtribuicao().guardarAtribuicoes(listaAtribuicoes);
            }
        }

        //Se mexermos na combobox
        if (e.getSource() == eventoComboBox) {
            limparListas();
            Evento ev = (Evento) eventoComboBox.getSelectedItem();
            copiarListaFaeDisponiveis(ev.getListaFae());
            copiarListaCandidaturasParalmCandidatura(ev.getListaCandidatura());
            copiarListaFaeDisponiveis(ev.getListaFae());
        }
    }

    //Método para verificar se foi carregado na jlist do meio
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == jlistCandidaturas) {
            Evento ev = (Evento) eventoComboBox.getSelectedItem();
            Candidatura c = (Candidatura) jlistCandidaturas.getSelectedValue();
            if (ev.getListaAtribuicao().tamanho() > 0 && listaAtribuicoes.isEmpty()) {
                copiarListaFaePorCandidaturaJaGuardada(ev.getListaAtribuicao(), c);
            } else {
                copiarListaFaePorCandidatura(listaAtribuicoes, c);
            }
        }
    }

}
