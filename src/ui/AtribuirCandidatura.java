package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
     * 
     */
    private List<Atribuicao> ListaAtribuicoes;

    //Vars Estáticos
    private static final int NR_LINHAS = 1, NR_COLUNAS = 3;

    public AtribuirCandidatura(JFrame frame, CentroEventos ce) {
        super(frame, Constantes.TITULO_JANELA_ATRIBUIR, true);

        this.ce = ce;

        initComponents();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(Constantes.TAMANHO_JANELA_MINIMO_ATRIBUIR_CAND);
        setLocationRelativeTo(null);

        pack();
        setVisible(true);
    }

    public final void initComponents() {
        initLists();
        initComboBox();
        initButtons();
        initPanels();
        verificarListArranque();
    }

    public void initLists() {
        //Iniciar ArrayLists
        ListaAtribuicoes = new ArrayList<>();

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

        jlistFaeDisponiveis.setToolTipText(Constantes.ATRIB_CAND_TOOLTIP_JLIST_FAEDISPONIVEIS);
        jlistCandidaturas.setToolTipText(Constantes.ATRIB_CAND_TOOLTIP_JLIST_LISTA_CANDIDATURAS);
        jlistFaeAtribuidos.setToolTipText(Constantes.ATRIB_CAND_TOOLTIP_JLIST_FAE_ATRIB_POR_CAND);

        copiarListaFaeDisponiveis(ce.getRegistoEventos().get(Constantes.INDICE_ZERO).getListaFae());
    }

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

    public void initPainelPrincipal(JPanel pcentro, JPanel psul) {
        principal.setBorder(new EmptyBorder(Constantes.GAP_DEZ, Constantes.GAP_DEZ, Constantes.GAP_ZERO, Constantes.GAP_DEZ));
        principal.add(pcentro, BorderLayout.CENTER);
        principal.add(psul, BorderLayout.SOUTH);
        add(principal);
    }

    public void initPainelCentro(JPanel pcentro) {
        pcentro.add(spFaeDisponiveis);
        pcentro.add(spCandidaturas);
        pcentro.add(spFaeAtribuidos);

    }

    public void initPainelSul(JPanel psul, JPanel psulesq, JPanel psuldir) {

        psulesq.add(eventoComboBox);
        psulesq.add(algoritmoComboBox);
        psulesq.add(atribui);

        psuldir.add(sair);
        psuldir.add(guardar);

        psul.add(psulesq, BorderLayout.WEST);
        psul.add(psuldir, BorderLayout.EAST);
    }

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
        atribui.setToolTipText(Constantes.ATRIB_CAND_TOOLTIP_ATRIBUIR);
        sair.setToolTipText(Constantes.ATRIB_CAND_TOOLTIP_SAIR);
        guardar.setToolTipText(Constantes.ATRIB_CAND_TOOLTIP_GUARDAR);

        //Adicionar os actionlisteners
        atribui.addActionListener(this);
        sair.addActionListener(this);
        guardar.addActionListener(this);
    }

    public void initComboBox() {
        //Criar combobox
        algoritmoComboBox = new JComboBox<>();
        eventoComboBox = new JComboBox<>();

        //Preencher listas
        copiarListaEventosParaListaModeloEventos(ce.getRegistoEventos());
        copiarListaAlgoritmosParaComboBox(ce.getRegistoAlgoritmosAtribuicao());

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

    }

    public void verificarListArranque() {
        if (ce.getRegistoEventos().size() != 0) {
            Evento ev = (Evento) eventoComboBox.getSelectedItem();
            if (ev != null && ev.getListaFae().size() != 0) {
                copiarListaFaeDisponiveis(ev.getListaFae());
                copiarListaCandidaturasParalmCandidatura(ev.getListaCandidatura());
            }
        }

    }

    private void copiarListaCandidaturasParalmCandidatura(ListaCandidatura lc) {
        listaModeloCandidatura.clear();
        for (Candidatura c : lc) {
            listaModeloCandidatura.addElement(c);

        }

    }

    private void copiarListaFaeDisponiveis(ListaFae listaFae) {
        listaModeloFaeDisponiveis.clear();
        for (Fae f : listaFae) {
            listaModeloFaeDisponiveis.addElement(f);
        }
    }

    private void copiarListaFaePorCandidatura(List<Atribuicao> la, Candidatura c) {
        listaModeloFaeAtribuidos.clear();
        for (Atribuicao a : la) {
            if (a.getCandidatura().equals(c)) {
                listaModeloFaeAtribuidos.addElement(a.getFae());
            }
        }
    }

    private void copiarListaFaePorCandidaturaJaGuardada(ListaAtribuicao la, Candidatura c) {
        listaModeloFaeAtribuidos.clear();
        for (Atribuicao a : la) {
            if (a.getCandidatura().equals(c)) {
                listaModeloFaeAtribuidos.addElement(a.getFae());
            }
        }
    }

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

    private void copiarListaEventosParaListaModeloEventos(RegistoEvento registoEventos) {
        listaModeloEvento.removeAllElements();
        for (Evento e : ce.getRegistoEventos()) {
            listaModeloEvento.addElement(e);
        }
    }

    private void limparListas() {
        listaModeloFaeDisponiveis.clear();
        listaModeloCandidatura.clear();
        listaModeloFaeAtribuidos.clear();
        ListaAtribuicoes.clear();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sair) {
            dispose();
        }
        if (e.getSource() == atribui) {
            Evento ev = (Evento) eventoComboBox.getSelectedItem();
            if (ev.getListaCandidatura().size() == 0) {
                JOptionPane.showMessageDialog(this, Constantes.MENSAGEM_ERRO_SEM_CANDIDATURAS, Constantes.ERRO_TITULO, JOptionPane.ERROR_MESSAGE);
            } else {

                ListaAtribuicoes = ((AlgoritmoAtribuicao) algoritmoComboBox.getSelectedItem()).atribui(ev);
                if (jlistCandidaturas.getSelectedIndex() != -1) {
                    int num = jlistCandidaturas.getSelectedIndex();
                    jlistCandidaturas.clearSelection();
                    jlistCandidaturas.setSelectedIndex(num);
                } else {
                    jlistCandidaturas.setSelectedIndex(Constantes.INDICE_ZERO);
                }

            }
        }
        if (e.getSource() == guardar) {
            if (!ListaAtribuicoes.isEmpty()) {
                Evento ev = (Evento) eventoComboBox.getSelectedItem();
                ev.getListaAtribuicao().clear();
                ev.getListaAtribuicao().guardarAtribuicoes(ListaAtribuicoes);
            }
        }

        if (e.getSource() == eventoComboBox) {
            limparListas();
            Evento ev = (Evento) eventoComboBox.getSelectedItem();
            copiarListaFaeDisponiveis(ev.getListaFae());
            copiarListaCandidaturasParalmCandidatura(ev.getListaCandidatura());
            copiarListaFaeDisponiveis(ev.getListaFae());
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == jlistCandidaturas) {
            Evento ev = (Evento) eventoComboBox.getSelectedItem();
            Candidatura c = (Candidatura) jlistCandidaturas.getSelectedValue();
            if (ev.getListaAtribuicao().size() > 0 && ListaAtribuicoes.isEmpty()) {
                copiarListaFaePorCandidaturaJaGuardada(ev.getListaAtribuicao(), c);
            } else {
                copiarListaFaePorCandidatura(ListaAtribuicoes, c);
            }
        }
    }

}
