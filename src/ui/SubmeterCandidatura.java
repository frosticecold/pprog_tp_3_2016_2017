/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import model.CentroEventos;
import model.candidatura.Candidatura;
import model.evento.Evento;
import model.listas.ListaTipoEvento;
import model.user.RepresentanteEmpresa;
import ui.misc.CustomCellRenderer;

/**
 *
 * @author salva
 */
public class SubmeterCandidatura extends JDialog implements ActionListener {

    JPanel pnorte = new JPanel(new BorderLayout()), pcentro = new JPanel(new BorderLayout()), psul = new JPanel(new BorderLayout()),
            psuldir = new JPanel(new FlowLayout()),
            painel = new JPanel(new BorderLayout(GAP, GAP));

    JButton submeter = new JButton("Submeter"), sair = new JButton("Sair");
    DefaultComboBoxModel<String> dcbmTipoEvento = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<Evento> dcbmEvento = new DefaultComboBoxModel<>();
    JComboBox<String> tipoEventocmbox = new JComboBox<>(dcbmTipoEvento);
    JComboBox<Evento> eventoCombox = new JComboBox<>(dcbmEvento);
    JTextArea txtArea = new JTextArea();
    //Vars de instância
    private ListaTipoEvento listaTipoEvento;
    private RepresentanteEmpresa representante;
    private CentroEventos centroEventos;
    //Vars de classe
    private final static int GAP = 10;
    private final static boolean MODAL = true;
    private final static Dimension TAMANHO_JANELA = new Dimension(327, 266);
    private final static String SEM_TEXTO = "";
    private final static String TITULO_JANELA = "Submeter Candidatura";
    private final static String MENSAGEM_ERRO = "Erro, texto inválido";
    private final static String TITULO_ERRO = "Erro";
    private final static String TOOLTIP_EVENTO = "Selecione o Evento";
    private final static String TOOLTIP_TIPO_EVENTO = "Selecione o Tipo de Evento";
    private final static String TOOLTIP_TXT_AREA = "Texto justificativo da candidatura";
    private final static String TOOLTIP_SAIR = "Cancelar a submissão da candidatura";
    private final static String TOOLTIP_SUBMETER = "Guardar a submissão da candidatura";

    public SubmeterCandidatura(Frame owner, CentroEventos ce, RepresentanteEmpresa repEmpresa) {
        super(owner, TITULO_JANELA, MODAL);

        listaTipoEvento = new ListaTipoEvento();
        representante = repEmpresa;
        centroEventos = ce;

        initComponentes();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(TAMANHO_JANELA);
        setLocationRelativeTo(owner);
        setVisible(true);

        //Teste
    }

    private void initComponentes() {
        initCombobox();
        initBotoes();
        initTxtArea();
        initPainelNorte();
        initPainelCentro();
        initPainelSul();
        adicionarComponentes();
        copiarTiposEventoParaCombobox();
        copiarEventoPorTipoParaCombobox();
    }

    private void initCombobox() {
        tipoEventocmbox.setToolTipText(TOOLTIP_TIPO_EVENTO);
        eventoCombox.setToolTipText(TOOLTIP_EVENTO);

        tipoEventocmbox.addActionListener(this);
        eventoCombox.addActionListener(this);

        eventoCombox.setRenderer(new CustomCellRenderer());
    }

    private void initBotoes() {
        sair.setToolTipText(TOOLTIP_SAIR);
        submeter.setToolTipText(TOOLTIP_SUBMETER);

        sair.setMnemonic(KeyEvent.VK_S);
        submeter.setMnemonic(KeyEvent.VK_B);

        submeter.addActionListener(this);
        sair.addActionListener(this);
    }

    private void initTxtArea() {
        txtArea.setToolTipText(TOOLTIP_TXT_AREA);
    }

    private void initPainelNorte() {
        pnorte.add(tipoEventocmbox, BorderLayout.WEST);
        pnorte.add(eventoCombox, BorderLayout.EAST);

    }

    private void initPainelCentro() {
        pcentro.setBorder(new EtchedBorder());
        pcentro.add(txtArea);

    }

    private void initPainelSul() {
        initPainelSulDir();
        psul.add(psuldir, BorderLayout.EAST);

    }

    private void initPainelSulDir() {
        psuldir.add(sair);
        psuldir.add(submeter);
    }

    private void adicionarComponentes() {
        painel.setBorder(new EmptyBorder(GAP, GAP, GAP, GAP));
        painel.add(pnorte, BorderLayout.NORTH);
        painel.add(pcentro, BorderLayout.CENTER);
        painel.add(psul, BorderLayout.SOUTH);
        add(painel);
    }

    /**
     * Este método limpa a default model lista associada ao TipoEventoCombobox e
     * adiciona os tipos de Evento
     */
    private void copiarTiposEventoParaCombobox() {
        dcbmTipoEvento.removeAllElements();
        for (String s : listaTipoEvento) {
            dcbmTipoEvento.addElement(s);
        }

    }

    /**
     * Este método limpa a default model lista associada ao EventoCombobox e
     * adiciona os Eventos associados
     */
    private void copiarEventoPorTipoParaCombobox() {
        dcbmEvento.removeAllElements();
        for (Evento ev : centroEventos.getRegistoEventos()) {
            if (ev.getClass().getSimpleName().equalsIgnoreCase(dcbmTipoEvento.getSelectedItem().toString())) {
                dcbmEvento.addElement(ev);
            }

        }
    }

    private void limparTxtArea() {
        txtArea.setText(SEM_TEXTO);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tipoEventocmbox) {
            copiarEventoPorTipoParaCombobox();
            limparTxtArea();
        }
        if (e.getSource() == eventoCombox) {
            limparTxtArea();
        }
        if (e.getSource() == submeter) {
            if (!txtArea.getText().isEmpty()) {
                Evento ev = (Evento) dcbmEvento.getSelectedItem();
                if (ev != null) {
                    Candidatura c = new Candidatura();
                    c.setDados(txtArea.getText(), representante);
                    ev.getListaCandidatura().addCandidatura(c);
                    System.out.println("Evento: " + ev + "Candidatura: " + c);
                }
            } else {
                JOptionPane.showMessageDialog(this, MENSAGEM_ERRO, TITULO_ERRO, JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == sair) {
            dispose();
        }
    }

}
