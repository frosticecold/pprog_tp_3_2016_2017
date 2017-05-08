/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import system.evento.Evento;
import system.listas.RegistoEvento;
import system.listas.ListaTipoEvento;
import system.user.RepresentanteEmpresa;

/**
 *
 * @author salva
 */
public class SubmeterCandidatura extends JDialog implements ActionListener {

    JPanel pnorte = new JPanel(new BorderLayout()), pcentro = new JPanel(new BorderLayout()), psul = new JPanel(new BorderLayout()),
            psuldir = new JPanel(new FlowLayout()),
            painel = new JPanel(new BorderLayout(10, 10));
    JButton submeter = new JButton("Submeter"), sair = new JButton("Sair");
    DefaultComboBoxModel<String> cbmTipoEvento = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<Evento> cbmEvento = new DefaultComboBoxModel<>();
    JComboBox<String> tipoEventocmbox = new JComboBox<>();
    JComboBox<Evento> eventoCombox = new JComboBox<>();
    JTextArea txtArea = new JTextArea();
    //Vars de instância

    RepresentanteEmpresa representante;
    RegistoEvento registoEvento;
    ListaTipoEvento listaTipoEvento = new ListaTipoEvento();
    //Vars de classe
    private static boolean MODAL = true;
    private static String TITULO_JANELA = "Submeter Candidatura";
    private static Dimension TAMANHO_JANELA = new Dimension(327, 266);

    public SubmeterCandidatura(Frame owner, RegistoEvento regEvent, RepresentanteEmpresa repEmpresa) {
        super(owner, TITULO_JANELA, MODAL);
        initComponentes();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(TAMANHO_JANELA);
        setLocationRelativeTo(owner);
        setVisible(true);

        //Teste
        representante = repEmpresa;
        registoEvento = regEvent;
    }

    private void initComponentes() {
        initBotoes();
        initPainelNorte();
        initPainelCentro();
        initPainelSul();
        adicionarComponentes();
        copiarTiposEventoParaCombobox();
    }

    public void initBotoes() {

        sair.setMnemonic(KeyEvent.VK_S);
        submeter.setMnemonic(KeyEvent.VK_B);

        submeter.addActionListener(this);
        sair.addActionListener(this);
    }

    public void initPainelNorte() {
        pnorte.add(tipoEventocmbox, BorderLayout.WEST);
        pnorte.add(eventoCombox, BorderLayout.EAST);

    }

    public void initPainelCentro() {
        pcentro.setBorder(new EtchedBorder());
        pcentro.add(txtArea);

    }

    public void initPainelSul() {
        initPainelSulDir();
        psul.add(psuldir, BorderLayout.EAST);

    }

    public void initPainelSulDir() {
        psuldir.add(sair);
        psuldir.add(submeter);
    }

    public void adicionarComponentes() {
        painel.setBorder(new EmptyBorder(10, 10, 10, 10));
        painel.add(pnorte, BorderLayout.NORTH);
        painel.add(pcentro, BorderLayout.CENTER);
        painel.add(psul, BorderLayout.SOUTH);
        add(painel);
    }

    /**
     * Este método limpa a default model lista associada ao TipoEventoCombobox e adiciona
     * os tipos de Evento
     */
    public void copiarTiposEventoParaCombobox() {
        cbmTipoEvento.removeAllElements();
        for (String s : listaTipoEvento) {
            cbmTipoEvento.addElement(s);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submeter) {
            System.out.println(String.format("Width: %d Height: %d", this.getWidth(), this.getHeight()));
        }
        if(e.getSource() == sair){
        dispose();
        }
    }

}
