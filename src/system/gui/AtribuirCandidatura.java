package system.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
import system.CentroEventos;
import system.evento.Evento;
import system.listas.ListaFae;
import system.listas.RegistoAlgoritmosAtribuicao;
import system.user.Fae;
import system.algoritmo.AlgoritmoAtribuicao;
import system.candidatura.Atribuicao;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class AtribuirCandidatura extends JDialog implements ActionListener {

    private JPanel principal = new JPanel(new BorderLayout()),
            pcentro = new JPanel(new GridLayout(NR_LINHAS, NR_COLUNAS, LIST_GAP, LIST_GAP)),
            psul = new JPanel(new BorderLayout()),
            psulesq = new JPanel(new FlowLayout(FlowLayout.LEFT)),
            psuldir = new JPanel(new FlowLayout());
    private JButton guardar = new JButton("Guardar"),
            sair = new JButton("Sair"), atribui = new JButton("Atribui");

    private DefaultListModel<Fae> lmUtilizador = new DefaultListModel<>();
    private DefaultListModel<Fae> lmAtrib = new DefaultListModel<>();
    private DefaultComboBoxModel<Evento> lmEvento = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<String> lmAlg = new DefaultComboBoxModel<>();
    private JList jlistUtilizadores = new JList<>(lmUtilizador);
    private JList jlAtribuidos = new JList<>(lmAtrib);
    private JScrollPane spFonte = new JScrollPane(), spFim = new JScrollPane();

    private JComboBox<String> algoritmoComboBox = new JComboBox<>();
    private JComboBox<Evento> eventoComboBox = new JComboBox<>();

    //Instancia
    private CentroEventos ce;
    private List<Fae> ListaFaeAtribuida = new ArrayList<>();
    private List<Atribuicao> ListaAtribuicoes = new ArrayList<>();

    //Estáticos
    private static int NR_LINHAS = 1, NR_COLUNAS = 2;
    private static int LIST_GAP = 10;
    private static Dimension BUTTON_DIMENSION = new Dimension(95, 30);
    private static Dimension COMBOBOX_DIMENSION = new Dimension(150, 30);
    private static Dimension TAMANHO_JANELA_MINIMO = new Dimension(650, 350);
    private static String MENSAGEM_EVENTO = "Seleciona o Evento";
    private static String MENSAGEM_ALGORITMO = "Seleciona o Algoritmo de Atribuição";

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

    public void initComponents() {
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
                copiarListaFae_Atribuidos_Para_Combobox(ev.getListaAtribuicao().obterTodosFaesAtribuidos());
            }
        }

    }

    public void initPanels() {
        initPainelCentro();
        initPainelSul();
        initPainelPrincipal();
    }

    public void initPainelPrincipal() {
        principal.add(pcentro, BorderLayout.CENTER);
        principal.add(psul, BorderLayout.SOUTH);
        add(principal);
    }

    public void initPainelCentro() {
        pcentro.add(spFonte);
        pcentro.add(spFim);

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
        copiarListaUtilizadores(ce.getRegistoEventos().get(0).getListaFae());

        jlistUtilizadores.setLayoutOrientation(JList.VERTICAL);
        jlAtribuidos.setLayoutOrientation(JList.VERTICAL);

        spFonte.setViewportView(jlistUtilizadores);
        spFim.setViewportView(jlAtribuidos);

    }

    public void initButtons() {
        atribui.setPreferredSize(BUTTON_DIMENSION);
        atribui.addActionListener(this);
        sair.setPreferredSize(BUTTON_DIMENSION);
        sair.addActionListener(this);
        guardar.setPreferredSize(BUTTON_DIMENSION);
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

    private void copiarListaFaeParalmAtrib(ListaFae listaFaeAtrib) {
        lmAtrib.clear();
        for (Fae fae : listaFaeAtrib) {
            lmAtrib.addElement(fae);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sair) {
            dispose();
        }
        if (e.getSource() == atribui) {
            try {
                Evento ev = (Evento) eventoComboBox.getSelectedItem();
                String nomeClasse = RegistoAlgoritmosAtribuicao.CLASSPATH + algoritmoComboBox.getSelectedItem();
                AlgoritmoAtribuicao a = (AlgoritmoAtribuicao) Class.forName(nomeClasse).newInstance();
                ListaAtribuicoes = a.atribui(ev);
                copiarListaFaeParalmAtrib(ListaAtribuicoes);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                JOptionPane.showMessageDialog(this, "A instanciação do algoritmo correu mal", "Erro!", JOptionPane.ERROR_MESSAGE);
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
            lmAtrib.removeAllElements();
            ListaAtribuicoes.clear();
            ListaFaeAtribuida.clear();
            Evento ev = (Evento) eventoComboBox.getSelectedItem();
            copiarListaFae_Atribuidos_Para_Combobox(ev.getListaAtribuicao().obterTodosFaesAtribuidos());
        }
    }

    private void copiarListaUtilizadores(ListaFae listaFae) {
        for (Fae f : listaFae) {
            lmUtilizador.addElement(f);
        }
    }

    private void copiarListaAlgoritmosParaComboBox(RegistoAlgoritmosAtribuicao ra) {
        for (String s : ra) {
            lmAlg.addElement(s);
        }

    }

    private void copiarListaFaeParalmAtrib(List<Atribuicao> listaFaeAtrib) {
        lmAtrib.clear();
        for (Atribuicao a : listaFaeAtrib) {
            lmAtrib.addElement(a.getFae());
        }
    }

    private void copiarListaFae_Atribuidos_Para_Combobox(List<Fae> listaFae) {
        lmAtrib.clear();
        for (Fae f : listaFae) {
            lmAtrib.addElement(f);
        }
    }
}
