package system.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
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
public class AtribuirCandidatura extends JFrame implements ActionListener {
    
    JPanel principal = new JPanel(new BorderLayout()), 
            pnorte = new JPanel(new FlowLayout()), 
            pcentro = new JPanel(new GridLayout(1, 2)), 
            psul = new JPanel(new FlowLayout());
    JPanel psulesq = new JPanel(new FlowLayout()), 
            psuldir = new JPanel(new FlowLayout());
    JButton guardar = new JButton("Guardar"), 
            sair = new JButton("Sair"), atribui = new JButton("Atribui");
    
    JLabel labelEvento = new JLabel("Evento");
    
    DefaultListModel<Fae> lmUtilizador = new DefaultListModel<>();
    DefaultListModel<Fae> lmAtrib = new DefaultListModel<>();
    DefaultComboBoxModel<Evento> lmEvento = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<String> lmAlg = new DefaultComboBoxModel<>();
    JList jlistUtilizadores = new JList<>(lmUtilizador);
    JList jlAtribuidos = new JList<>(lmAtrib);
    JScrollPane spFonte = new JScrollPane(), spFim = new JScrollPane();
    
    JComboBox<String> algoritmoComboBox = new JComboBox<>();
    JComboBox<Evento> eventoComboBox = new JComboBox<>();
    
    private static String MENSAGEM_EVENTO = "Seleciona o Evento";
    private static String MENSAGEM_ALGORITMO = "Seleciona o Algoritmo de Atribuição";
    //OtherOptions
    private CentroEventos ce;
    
    private static int LIST_WIDTH = 250;
    private static int LIST_HEIGHT = 250;
    private static Dimension LIST_DIMENSION = new Dimension(250, 250);
    private static Dimension COMBOBOX_DIMENSION = new Dimension(150, 30);
    private static Dimension TAMANHO_JANELA_MINIMO = new Dimension(650, 350);
    private List<Fae> ListaFaeAtribuida = new ArrayList<>();
    private List<Atribuicao> ListaAtribuicoes = new ArrayList<>();
    
    public AtribuirCandidatura(CentroEventos ce) {
        super("Atribuir Candidatura");
        this.ce = ce;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
        setMinimumSize(TAMANHO_JANELA_MINIMO);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //List<Fae> listaFaeFonte, List<Fae> listaAtribuir
    public void initComponents() {
        //END TEST STUFF
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
        //        pcentro.add(jlistUtilizadores);
        pnorte.add(labelEvento);
        
        
        
        pnorte.setBorder(new EmptyBorder(10, 0, 0, 0));
        pcentro.add(spFonte);
        //pcentro.add(new JSeparator());
        
        pcentro.add(spFim);

        psulesq.add(eventoComboBox);
        psulesq.add(algoritmoComboBox);
        psulesq.add(atribui);
        
        psuldir.add(sair);
        psuldir.add(guardar);
        
        psul.add(psulesq);
        psul.add(new JSeparator());
        psul.add(psuldir);
        //principal.add(pnorte, BorderLayout.NORTH);
        principal.add(pcentro, BorderLayout.CENTER);
        principal.add(psul, BorderLayout.SOUTH);
        add(principal);
    }
    
    public void initLists() {
        copiarListaUtilizadores(ce.getRegistoEventos().get(0).getListaFae());
//        jlistUtilizadores.setListData(a);
//        jlAtribuidos.setListData(b);
        jlistUtilizadores.setLayoutOrientation(JList.VERTICAL);
        jlAtribuidos.setLayoutOrientation(JList.VERTICAL);
        spFonte.setPreferredSize(LIST_DIMENSION);
        spFim.setPreferredSize(LIST_DIMENSION);
//        spFonte.setMinimumSize(LIST_DIMENSION);
//        spFim.setMinimumSize(LIST_DIMENSION);
        spFonte.setViewportView(jlistUtilizadores);
        spFim.setViewportView(jlAtribuidos);
        
    }
    
    public void initButtons() {
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
