package system.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import system.algoritmo.Algoritmo;
import system.user.Fae;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class AtribuirCandidatura extends JFrame implements ActionListener {

    JPanel principal = new JPanel(new BorderLayout()), pcentro = new JPanel(new FlowLayout()), psul = new JPanel(new FlowLayout());
    JPanel psulesq = new JPanel(new FlowLayout()), psuldir = new JPanel(new FlowLayout());
    JButton ok = new JButton("Ok"), cancel = new JButton("Cancelar"), atribui = new JButton("Atribui");
    JScrollPane spFonte = new JScrollPane(), spFim = new JScrollPane();
    DefaultListModel<Fae> lmf = new DefaultListModel<>(), lmt = new DefaultListModel<>();
    JList<String> jlf = new JList<>(), jlt = new JList<>();
    JComboBox<String> combobox = new JComboBox<>();
    String[] a = {"A", "B", "C", "D", "N", "F", "H", "K", "P", "A", "W", "E", "R", "T", "Z", "C", "N", "M"};
    String[] b = {"Z", "X", "C", "V", "N", "F", "H", "K", "P", "A", "W", "E", "R", "T"};
    String[] c = {"@", "#", "$", "%", "&", "/"};

    //List<Fae> listaFaeFonte, List<Fae> listaAtribuir
    public AtribuirCandidatura() {
        super("Atribuir Candidatura");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
        setMinimumSize(new Dimension(450, 350));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //List<Fae> listaFaeFonte, List<Fae> listaAtribuir
    public void initComponents() {
        jlf.setListData(a);
        jlt.setListData(b);
        jlf.setLayoutOrientation(JList.VERTICAL);
        jlt.setLayoutOrientation(JList.VERTICAL);
        combobox.setPreferredSize(new Dimension(150, 30));
        for (String string : c) {
            combobox.addItem(string);
        }
//        pcentro.add(jlf);
        spFonte.setPreferredSize(new Dimension(150, 200));
        spFim.setPreferredSize(new Dimension(150, 200));
        spFonte.setViewportView(jlf);
        pcentro.add(spFonte);
        spFim.setViewportView(jlt);
        pcentro.setBorder(new EmptyBorder(20, 20, 20, 20));
        pcentro.add(new JSeparator());
        pcentro.add(spFim);
//        pcentro.add(jlt);

        psulesq.add(combobox);
        psulesq.add(atribui);

        psuldir.add(cancel);
        psuldir.add(ok);

        psul.add(psulesq);
        psul.add(new JSeparator());
        psul.add(psuldir);
        principal.add(pcentro, BorderLayout.CENTER);
        principal.add(psul, BorderLayout.SOUTH);
        add(principal);

        cancel.addActionListener(this);
    }

    public void copiarLista(List<Fae> listaFaeFonte) {
        for (Fae fae : listaFaeFonte) {
            lmf.addElement(fae);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            dispose();
        }
    }

}
