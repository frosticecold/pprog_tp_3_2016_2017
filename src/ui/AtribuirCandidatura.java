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
import utils.Constantes;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class AtribuirCandidatura extends JDialog implements ActionListener, ListSelectionListener {

    private final JPanel principal = new JPanel(new BorderLayout()),
            pcentro = new JPanel(new GridLayout(NR_LINHAS, NR_COLUNAS, LIST_GAP, LIST_GAP)),
            psul = new JPanel(new BorderLayout()),
            psulesq = new JPanel(new FlowLayout(FlowLayout.LEFT)),
            psuldir = new JPanel(new FlowLayout());
    private final JButton guardar = new JButton("Guardar"),
            sair = new JButton("Sair"), atribui = new JButton("Atribui");

    private final DefaultListModel<Fae> lmFaeDisponiveis = new DefaultListModel<>();
    private final DefaultListModel<Candidatura> lmCandidatura = new DefaultListModel<>();
    private final DefaultListModel<Fae> lmFaeAtribuidos = new DefaultListModel<>();

    private final DefaultComboBoxModel<Evento> lmEvento = new DefaultComboBoxModel<>();
    private final DefaultComboBoxModel<String> lmAlg = new DefaultComboBoxModel<>();
    private final JList jlistFaeDisponiveis = new JList<>(lmFaeDisponiveis);
    private final JList jlistaCandidaturas = new JList<>(lmCandidatura);
    private final JList jlistFaeAtribuidos = new JList<>(lmFaeAtribuidos);
    private final JScrollPane spFaeDeEvento = new JScrollPane(), spCandDeEvento = new JScrollPane(), spFaeDeCandidatura = new JScrollPane();

    private final JComboBox<String> algoritmoComboBox = new JComboBox<>();
    private final JComboBox<Evento> eventoComboBox = new JComboBox<>();

    //Instancia
    private final CentroEventos ce;
    private final List<Fae> ListaFaeAtribuida = new ArrayList<>();
    private List<Atribuicao> ListaAtribuicoes = new ArrayList<>();

    //Estáticos
    private static final int PRIMEIRO_ITEM = 0;
    private static final int NR_LINHAS = 1, NR_COLUNAS = 3;
    private static final int LIST_GAP = 10;
    private static final Dimension BUTTON_DIMENSION = new Dimension(95, 30);
    private static final Dimension COMBOBOX_DIMENSION = new Dimension(150, 30);
    private static final Dimension TAMANHO_JANELA_MINIMO = new Dimension(650, 350);
    private static final String MENSAGEM_EVENTO = "Seleciona o Evento";
    private static final String MENSAGEM_ALGORITMO = "Seleciona o Algoritmo de Atribuição";
    private static final String MENSAGEM_ERRO_SEM_CANDIDATURAS = "Erro, sem candidaturas...";
    private static final String TITULO_ERRO = "Erro";
    private static final String TOOLTIP_JLIST_FAEDISPONIVEIS = "Lista de Faes disponíveis";
    private static final String TOOLTIP_JLIST_LISTA_CANDIDATURAS = "Lista de Candidaturas disponíveis";
    private static final String TOOLTIP_JLIST_FAE_ATRIB_POR_CAND = "Lista de Faes Atribuidos à candidatura";

    public AtribuirCandidatura(JFrame frame, CentroEventos ce) {
        //super("Atribuir Candidatura");
        super(frame, "Atribuir Candidatura", true);
        this.ce = ce;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
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

    public void verificarListArranque() {
        if (ce.getRegistoEventos().size() != 0) {
            Evento ev = (Evento) eventoComboBox.getSelectedItem();
            if (ev != null && ev.getListaFae().size() != 0) {
                copiarListaFaeDisponiveis(ev.getListaFae());
                copiarListaCandidaturasParalmCandidatura(ev.getListaCandidatura());
            }
        }

    }

    public void initPanels() {
        initPainelCentro();
        initPainelSul();
        initPainelPrincipal();
    }

    public void initPainelPrincipal() {
        final int GAP_DEZ = 10;
        final int GAP_ZERO = 0;
        principal.setBorder(new EmptyBorder(GAP_DEZ, GAP_DEZ, GAP_ZERO, GAP_DEZ));
        principal.add(pcentro, BorderLayout.CENTER);
        principal.add(psul, BorderLayout.SOUTH);
        add(principal);
    }

    public void initPainelCentro() {
        pcentro.add(spFaeDeEvento);
        pcentro.add(spCandDeEvento);
        pcentro.add(spFaeDeCandidatura);

    }

    public void initPainelSul() {

        psulesq.add(eventoComboBox);
        psulesq.add(algoritmoComboBox);
        psulesq.add(atribui);

        psuldir.add(sair);
        psuldir.add(guardar);

        psul.add(psulesq, BorderLayout.WEST);
        psul.add(psuldir, BorderLayout.EAST);
    }

    public void initLists() {
        copiarListaFaeDisponiveis(ce.getRegistoEventos().get(0).getListaFae());

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

    public void initButtons() {
        atribui.setPreferredSize(BUTTON_DIMENSION);
        sair.setPreferredSize(BUTTON_DIMENSION);
        guardar.setPreferredSize(BUTTON_DIMENSION);

        atribui.setMnemonic(KeyEvent.VK_A);
        sair.setMnemonic(KeyEvent.VK_S);
        guardar.setMnemonic(KeyEvent.VK_G);
        atribui.addActionListener(this);
        sair.addActionListener(this);
        guardar.addActionListener(this);
    }

    public void initComboBox() {
        for (Evento e : ce.getRegistoEventos()) {
            lmEvento.addElement(e);
        }
        eventoComboBox.setModel(lmEvento);
        eventoComboBox.setPreferredSize(COMBOBOX_DIMENSION);
        algoritmoComboBox.setPreferredSize(COMBOBOX_DIMENSION);

        copiarListaAlgoritmosParaComboBox(ce.getRegistoAlgoritmosAtribuicao());
        algoritmoComboBox.setModel(lmAlg);

        eventoComboBox.addActionListener(this);
        eventoComboBox.setToolTipText(MENSAGEM_EVENTO);
        algoritmoComboBox.setToolTipText(MENSAGEM_ALGORITMO);

    }

    private void copiarListaCandidaturasParalmCandidatura(ListaCandidatura lc) {
        lmCandidatura.clear();
        for (Candidatura c : lc) {
            lmCandidatura.addElement(c);

        }

    }

    private void copiarListaFaeDisponiveis(ListaFae listaFae) {
        lmFaeDisponiveis.clear();
        for (Fae f : listaFae) {
            lmFaeDisponiveis.addElement(f);
        }
    }

    private void copiarListaFaePorCandidatura(List<Atribuicao> la, Candidatura c) {
        lmFaeAtribuidos.clear();
        for (Atribuicao a : la) {
            if (a.getCandidatura().equals(c)) {
                lmFaeAtribuidos.addElement(a.getFae());
            }
        }
    }

    private void copiarListaFaePorCandidaturaJaGuardada(ListaAtribuicao la, Candidatura c) {
        lmFaeAtribuidos.clear();
        for (Atribuicao a : la) {
            if (a.getCandidatura().equals(c)) {
                lmFaeAtribuidos.addElement(a.getFae());
            }
        }
    }

    private void copiarListaAlgoritmosParaComboBox(RegistoAlgoritmosAtribuicao ra) {
        lmAlg.removeAllElements();
        for (String s : ra) {
            lmAlg.addElement(s);
        }

    }

    private void limparListas() {
        lmFaeDisponiveis.clear();
        lmCandidatura.clear();
        lmFaeAtribuidos.clear();
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
                try {
                    String nomeClasse = RegistoAlgoritmosAtribuicao.CLASSPATH + algoritmoComboBox.getSelectedItem();
                    AlgoritmoAtribuicao a = (AlgoritmoAtribuicao) Class.forName(nomeClasse).newInstance();
                    ListaAtribuicoes = a.atribui(ev);
                    if (jlistaCandidaturas.getSelectedIndex() != -1) {
                        int num = jlistaCandidaturas.getSelectedIndex();
                        jlistaCandidaturas.clearSelection();
                        jlistaCandidaturas.setSelectedIndex(num);
                    } else {
                        jlistaCandidaturas.setSelectedIndex(PRIMEIRO_ITEM);
                    }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    JOptionPane.showMessageDialog(this, "A instanciação do algoritmo correu mal", "Erro!", JOptionPane.ERROR_MESSAGE);
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
