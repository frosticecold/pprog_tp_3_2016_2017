package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
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
import ui.misc.EventoCellRenderer;
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
    private DefaultListModel<Fae> listaModeloFaeDisponiveis, listaModeloFaeAtribuidos;
    private DefaultListModel<Candidatura> listaModeloCandidatura;
    //===================DefaultComboboxModel
    private DefaultComboBoxModel<Evento> listaModeloEvento;
    private DefaultComboBoxModel<AlgoritmoAtribuicao> listaModeloAlgoritmo;
    //===================JLists
    private JList jlistFaeDisponiveis;
    private JList jlistaCandidaturas;
    private JList jlistFaeAtribuidos;
    //===================JScrollPane
    private JScrollPane spFaeDeEvento;
    private JScrollPane spCandDeEvento;
    private JScrollPane spFaeDeCandidatura;

    private JComboBox<AlgoritmoAtribuicao> algoritmoComboBox;
    private JComboBox<Evento> eventoComboBox;

    //Instancia
    private final CentroEventos ce;
    private final List<Fae> ListaFaeAtribuida;
    private List<Atribuicao> ListaAtribuicoes;

    //Estáticos
    private static final int PRIMEIRO_ITEM = 0;
    private static final int NR_LINHAS = 1, NR_COLUNAS = 3;
    private static final int LIST_GAP = 10;

    private static final Dimension TAMANHO_JANELA_MINIMO = new Dimension(650, 350);
    private static final String MENSAGEM_EVENTO = "Seleciona o Evento";
    private static final String MENSAGEM_ALGORITMO = "Seleciona o Algoritmo de Atribuição";
    private static final String MENSAGEM_ERRO_SEM_CANDIDATURAS = "Erro, sem candidaturas...";
    private static final String TITULO_ERRO = "Erro";
    private static final String TOOLTIP_JLIST_FAEDISPONIVEIS = "Lista de Faes disponíveis";
    private static final String TOOLTIP_JLIST_LISTA_CANDIDATURAS = "Lista de Candidaturas disponíveis";
    private static final String TOOLTIP_JLIST_FAE_ATRIB_POR_CAND = "Lista de Faes Atribuidos à candidatura";

    public AtribuirCandidatura(JFrame frame, CentroEventos ce) {
        super(frame, Constantes.TITULO_JANELA_ATRIBUIR, true);

        this.ce = ce;
        ListaFaeAtribuida = new ArrayList<>();
        ListaAtribuicoes = new ArrayList<>();

        initComponents();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(TAMANHO_JANELA_MINIMO);
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
        //Criar lista modelos
        listaModeloFaeDisponiveis = new DefaultListModel<>();
        listaModeloFaeAtribuidos = new DefaultListModel<>();
        listaModeloCandidatura = new DefaultListModel<>();
        listaModeloEvento = new DefaultComboBoxModel<>();
        listaModeloAlgoritmo = new DefaultComboBoxModel<>();
        jlistaCandidaturas = new JList<>(listaModeloCandidatura);
        jlistFaeAtribuidos = new JList<>(listaModeloFaeAtribuidos);

        //Criar Scrollpanes
        spFaeDeEvento = new JScrollPane();
        spCandDeEvento = new JScrollPane();
        spFaeDeCandidatura = new JScrollPane();

        //Criar jlists
        jlistFaeDisponiveis = new JList<>(listaModeloFaeDisponiveis);

        copiarListaFaeDisponiveis(ce.getRegistoEventos().get(0).getListaFae());

        jlistaCandidaturas.setCellRenderer(new CandidaturaCellRenderer());

        jlistFaeDisponiveis.setLayoutOrientation(JList.VERTICAL);
        jlistaCandidaturas.setLayoutOrientation(JList.VERTICAL);
        jlistFaeAtribuidos.setLayoutOrientation(JList.VERTICAL);

        spFaeDeEvento.setViewportView(jlistFaeDisponiveis);
        spCandDeEvento.setViewportView(jlistaCandidaturas);
        spFaeDeCandidatura.setViewportView(jlistFaeAtribuidos);

        //Listeners
        jlistaCandidaturas.addListSelectionListener(this);

        jlistFaeDisponiveis.setToolTipText(TOOLTIP_JLIST_FAEDISPONIVEIS);
        jlistaCandidaturas.setToolTipText(TOOLTIP_JLIST_LISTA_CANDIDATURAS);
        jlistFaeAtribuidos.setToolTipText(TOOLTIP_JLIST_FAE_ATRIB_POR_CAND);
    }

    public void initPanels() {
        principal = new JPanel(new BorderLayout());
        JPanel pcentro = new JPanel(new GridLayout(NR_LINHAS, NR_COLUNAS, LIST_GAP, LIST_GAP));
        JPanel psul = new JPanel(new BorderLayout());
        JPanel psulesq = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel psuldir = new JPanel(new FlowLayout());
        initPainelCentro(pcentro);
        initPainelSul(psul, psulesq, psuldir);
        initPainelPrincipal(pcentro, psul);
    }

    public void initPainelPrincipal(JPanel pcentro, JPanel psul) {
        final int GAP_DEZ = 10;
        final int GAP_ZERO = 0;
        principal.setBorder(new EmptyBorder(GAP_DEZ, GAP_DEZ, GAP_ZERO, GAP_DEZ));
        principal.add(pcentro, BorderLayout.CENTER);
        principal.add(psul, BorderLayout.SOUTH);
        add(principal);
    }

    public void initPainelCentro(JPanel pcentro) {
        pcentro.add(spFaeDeEvento);
        pcentro.add(spCandDeEvento);
        pcentro.add(spFaeDeCandidatura);

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

        //Adicionar os actionlisteners
        atribui.addActionListener(this);
        sair.addActionListener(this);
        guardar.addActionListener(this);
    }

    public void initComboBox() {
        //Criar combobox
        algoritmoComboBox = new JComboBox<>();
        eventoComboBox = new JComboBox<>();

        for (Evento e : ce.getRegistoEventos()) {
            listaModeloEvento.addElement(e);
        }
        eventoComboBox.setModel(listaModeloEvento);
        eventoComboBox.setPreferredSize(Constantes.ATRIB_CAND_COMBOBOX_DIMENSION);
        algoritmoComboBox.setPreferredSize(Constantes.ATRIB_CAND_COMBOBOX_DIMENSION);

        copiarListaAlgoritmosParaComboBox(ce.getRegistoAlgoritmosAtribuicao());
        algoritmoComboBox.setModel(listaModeloAlgoritmo);

        eventoComboBox.addActionListener(this);
        eventoComboBox.setToolTipText(MENSAGEM_EVENTO);
        eventoComboBox.setRenderer(new EventoCellRenderer());
        algoritmoComboBox.setToolTipText(MENSAGEM_ALGORITMO);

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

    private void limparListas() {
        listaModeloFaeDisponiveis.clear();
        listaModeloCandidatura.clear();
        listaModeloFaeAtribuidos.clear();
        ListaAtribuicoes.clear();
        ListaFaeAtribuida.clear();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sair) {
            dispose();
        }
        if (e.getSource() == atribui) {
            Evento ev = (Evento) eventoComboBox.getSelectedItem();
            if (ev.getListaCandidatura().size() == 0) {
                JOptionPane.showMessageDialog(this, MENSAGEM_ERRO_SEM_CANDIDATURAS, TITULO_ERRO, JOptionPane.ERROR_MESSAGE);
            } else {

                ListaAtribuicoes = ((AlgoritmoAtribuicao) algoritmoComboBox.getSelectedItem()).atribui(ev);
                if (jlistaCandidaturas.getSelectedIndex() != -1) {
                    int num = jlistaCandidaturas.getSelectedIndex();
                    jlistaCandidaturas.clearSelection();
                    jlistaCandidaturas.setSelectedIndex(num);
                } else {
                    jlistaCandidaturas.setSelectedIndex(PRIMEIRO_ITEM);
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
        if (e.getSource() == jlistaCandidaturas) {
            Evento ev = (Evento) eventoComboBox.getSelectedItem();
            Candidatura c = (Candidatura) jlistaCandidaturas.getSelectedValue();
            if (ev.getListaAtribuicao().size() > 0 && ListaAtribuicoes.isEmpty()) {
                copiarListaFaePorCandidaturaJaGuardada(ev.getListaAtribuicao(), c);
            } else {
                copiarListaFaePorCandidatura(ListaAtribuicoes, c);
            }
        }
    }
}

class CandidaturaCellRenderer extends DefaultListCellRenderer {

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        Candidatura cd = (Candidatura) value;
        setText(cd.getDescricao()); // where getValue is some method you implement that gets the text you want to render for the component
        return c;
    }
}
