package old;

import system.gui.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import system.listas.RegistoUtilizador;
import system.user.Fae;
import system.user.Utilizador;
import system.algoritmo.AlgoritmoAtribuicao;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class AtribuirCandidatura_malfeito extends JFrame implements ActionListener {

    JPanel principal = new JPanel(new BorderLayout()), pnorte = new JPanel(new FlowLayout()), pcentro = new JPanel(new FlowLayout()), psul = new JPanel(new FlowLayout());
    JPanel psulesq = new JPanel(new FlowLayout()), psuldir = new JPanel(new FlowLayout());
    JButton guardar = new JButton("Guardar"), sair = new JButton("Sair"), atribui = new JButton("Atribui");

    JLabel labelEvento = new JLabel("Evento");

    DefaultListModel<Utilizador> lmUtilizador = new DefaultListModel<>();
    DefaultListModel<Fae> lmAtrib = new DefaultListModel<>();
    DefaultComboBoxModel<Evento> lmEvento = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<String> lmAlg = new DefaultComboBoxModel<>();
    JList jlistUtilizadores = new JList<>(lmUtilizador);
    JList jlAtribuidos = new JList<>(lmAtrib);
    JScrollPane spFonte = new JScrollPane(), spFim = new JScrollPane();

    JComboBox<String> algoritmoComboBox = new JComboBox<>();
    JComboBox<Evento> eventoComboBox = new JComboBox<>();

    //OtherOptions
    private CentroEventos ce;

    private static Dimension LIST_DIMENSION = new Dimension(150, 200);
    private static Dimension COMBOBOX_DIMENSION = new Dimension(150, 30);
    private static Dimension TAMANHO_JANELA_MINIMO = new Dimension(450, 350);
    private List<Fae> ListaAtribuida = new ArrayList<>();

    public AtribuirCandidatura_malfeito(CentroEventos ce) {
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
                copiarListaFaeParalmAtrib(ev.getListaFae());
            }
        }

    }

    public void initPanels() {
        //        pcentro.add(jlistUtilizadores);
        pnorte.add(labelEvento);
        pnorte.add(eventoComboBox);
        pnorte.setBorder(new EmptyBorder(10, 0, 0, 0));
        pcentro.add(spFonte);
        //pcentro.setBorder(new EmptyBorder(20, 20, 20, 20));
        pcentro.add(new JSeparator());
        pcentro.add(spFim);
//        pcentro.add(jlAtribuidos);

        psulesq.add(algoritmoComboBox);
        psulesq.add(atribui);

        psuldir.add(sair);
        psuldir.add(guardar);

        psul.add(psulesq);
        psul.add(new JSeparator());
        psul.add(psuldir);
        principal.add(pnorte, BorderLayout.NORTH);
        principal.add(pcentro, BorderLayout.CENTER);
        principal.add(psul, BorderLayout.SOUTH);
        add(principal);
    }

    public void initLists() {
        copiarListaUtilizadores(ce.getRegistoUtilizadores());
//        jlistUtilizadores.setListData(a);
//        jlAtribuidos.setListData(b);
        jlistUtilizadores.setLayoutOrientation(JList.VERTICAL);
        jlAtribuidos.setLayoutOrientation(JList.VERTICAL);
        spFonte.setPreferredSize(LIST_DIMENSION);
        spFim.setPreferredSize(LIST_DIMENSION);
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

        algoritmoComboBox.setPreferredSize(COMBOBOX_DIMENSION);

        copiarListaAlgoritmos(ce.getRegistoAlgoritmosAtribuicao());
        algoritmoComboBox.setModel(lmAlg);

        eventoComboBox.addActionListener(this);

    }

    private void copiarListaUtilizadores(RegistoUtilizador ru) {
        for (Utilizador u : ru) {
            lmUtilizador.addElement(u);
        }
    }

    private void copiarListaAlgoritmos(RegistoAlgoritmosAtribuicao ra) {
        for (String s : ra) {
            lmAlg.addElement(s);
        }

    }

    private void copiarListaFaeParalmAtrib(List<Fae> listaFaeAtrib) {
        lmAtrib.clear();
        for (Fae fae : listaFaeAtrib) {
            lmAtrib.addElement(fae);
        }
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
                //ListaAtribuida = a.atribui(ce, ev.getTitulo());
                copiarListaFaeParalmAtrib(ListaAtribuida);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                JOptionPane.showMessageDialog(this, "A instanciação do algoritmo correu mal", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == guardar) {
            if (!ListaAtribuida.isEmpty()) {
                Evento ev = (Evento) eventoComboBox.getSelectedItem();
                ev.getListaFae().copiarListaFae(ListaAtribuida);
            }
        }
        if (e.getSource() == eventoComboBox) {
            lmAtrib.removeAllElements();
            Evento ev = (Evento) eventoComboBox.getSelectedItem();
            copiarListaFaeParalmAtrib(ev.getListaFae());
        }
    }

}
