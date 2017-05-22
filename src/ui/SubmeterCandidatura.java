package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
import utils.Constantes;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class SubmeterCandidatura extends JDialog implements ActionListener {

    private JPanel painel;
    private JButton submeter;
    private JButton sair;
    private DefaultComboBoxModel<String> dcbmTipoEvento;
    private DefaultComboBoxModel<Evento> dcbmEvento;
    private JComboBox<String> tipoEventocmbox;
    private JComboBox<Evento> eventoCombox;
    private JTextArea txtArea;

    //Vars de instância
    private ListaTipoEvento listaTipoEvento;
    private RepresentanteEmpresa representante;
    private CentroEventos centroEventos;

    public SubmeterCandidatura(JFrame owner, CentroEventos ce, RepresentanteEmpresa repEmpresa) {
        super(owner, Constantes.TITULO_JANELA_DECID_CAND, DEFAULT_MODALITY_TYPE);

        listaTipoEvento = new ListaTipoEvento();
        representante = repEmpresa;
        centroEventos = ce;

        initComponentes();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(Constantes.TAMANHO_MINIMO_JANELA_DECIDIR_CAND);
        setLocationRelativeTo(owner);
        setVisible(true);

    }

    /**
     * Cria e inicializa todos os componentes da janela
     */
    private void initComponentes() {
        initCombobox();
        initBotoes();
        initTxtArea();
        JPanel pnorte = initPainelNorte();
        JPanel pcentro = initPainelCentro();
        JPanel psul = initPainelSul();
        criarPainelPrincipalAdicionarComponentes(pnorte, pcentro, psul);
        copiarTiposEventoParaCombobox();
        copiarEventoPorTipoParaCombobox();
    }

    /**
     * Cria as listas modelos e combobox tipoEvento e Evento
     */
    private void initCombobox() {
        //Inicializa lista modelo e combobox tipo evento
        dcbmTipoEvento = new DefaultComboBoxModel<>();
        tipoEventocmbox = new JComboBox<>(dcbmTipoEvento);

        //Inicializa lista modelo e combobox eventos
        dcbmEvento = new DefaultComboBoxModel<>();
        eventoCombox = new JComboBox<>(dcbmEvento);

        //Adiciona tooltiptext
        tipoEventocmbox.setToolTipText(Constantes.TOOLTIP_DC_TIPO_EVENTO);
        eventoCombox.setToolTipText(Constantes.TOOLTIP_DC_EVENTO);

        //Adiciona actionlistener
        tipoEventocmbox.addActionListener(this);
        eventoCombox.addActionListener(this);

        //Serve para fazer mostrar apenas o texto do evento em vez do toString() completo do objeto
        eventoCombox.setRenderer(new CustomCellRenderer());

        //Set Action Command
        tipoEventocmbox.setActionCommand(Constantes.CMBBOX_TIPOEVENTO);
        eventoCombox.setActionCommand(Constantes.CMBBOX_EVENTO);
    }

    /**
     * Cria os botões submeter e sair
     */
    private void initBotoes() {

        submeter = new JButton(Constantes.BTN_SUBMETER);
        sair = new JButton(Constantes.BTN_SAIR);

        sair.setToolTipText(Constantes.TOOLTIP_DC_SAIR);
        submeter.setToolTipText(Constantes.TOOLTIP_DC_SUBMETER);

        sair.setMnemonic(KeyEvent.VK_S);
        submeter.setMnemonic(KeyEvent.VK_B);

        submeter.addActionListener(this);
        sair.addActionListener(this);

        //SetActionCommands
        submeter.setActionCommand(Constantes.BTN_SUBMETER);
        sair.setActionCommand(Constantes.BTN_SAIR);
    }

    /**
     * Cria e inicializa a JTextArea
     */
    private void initTxtArea() {
        txtArea = new JTextArea();
        txtArea.setToolTipText(Constantes.TOOLTIP_DC_TXT_AREA);
    }

    /**
     * Cria e inicializa o painel norte Com combobox tipo de evento e evento
     * combobox
     *
     * @return JPanel painel norte
     */
    private JPanel initPainelNorte() {
        JPanel pnorte = new JPanel(new BorderLayout());
        pnorte.add(tipoEventocmbox, BorderLayout.WEST);
        pnorte.add(eventoCombox, BorderLayout.EAST);
        return pnorte;

    }

    /**
     * Cria e inicializa o painel centro com uma txtarea e um border
     *
     * @return JPanel painel centro
     */
    private JPanel initPainelCentro() {
        JPanel pcentro = new JPanel(new BorderLayout());
        pcentro.setBorder(new EtchedBorder());
        pcentro.add(txtArea);
        return pcentro;

    }

    /**
     * Cria e inicializa um psul
     *
     * @return
     */
    private JPanel initPainelSul() {
        JPanel psul = new JPanel(new BorderLayout());
        JPanel psuldir = initPainelSulDir();
        psul.add(psuldir, BorderLayout.EAST);
        return psul;
    }

    /**
     * Cria e inicializa o painel sul direito com os botões sair e submeter
     *
     * @return
     */
    private JPanel initPainelSulDir() {
        JPanel psuldir = new JPanel(new FlowLayout());
        psuldir.add(sair);
        psuldir.add(submeter);
        return psuldir;
    }

    /**
     * Cria o painel principal e adiciona o painel norte, centro e sul ao
     * painel. Por fim adiciona à JFrame
     *
     * @param pnorte JPanel painel norte
     * @param pcentro JPanel painel centro
     * @param psul JPanel painel sul
     */
    private void criarPainelPrincipalAdicionarComponentes(JPanel pnorte, JPanel pcentro, JPanel psul) {
        painel = new JPanel(new BorderLayout(Constantes.GAP_DEZ, Constantes.GAP_DEZ));
        painel.setBorder(new EmptyBorder(Constantes.GAP_DEZ, Constantes.GAP_DEZ, Constantes.GAP_DEZ, Constantes.GAP_DEZ));
        painel.add(pnorte, BorderLayout.NORTH);
        painel.add(pcentro, BorderLayout.CENTER);
        painel.add(psul, BorderLayout.SOUTH);
        add(painel);
    }

    /**
     * Método que limpa a default model lista associada ao TipoEventoCombobox e
     * adiciona os tipos de Evento
     */
    private void copiarTiposEventoParaCombobox() {
        dcbmTipoEvento.removeAllElements();
        for (String s : listaTipoEvento) {
            dcbmTipoEvento.addElement(s);
        }

    }

    /**
     * Método que limpa a default model lista associada ao EventoCombobox e
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

    /**
     * Método que limpa o texto da txtarea
     */
    private void limparTxtArea() {
        txtArea.setText(Constantes.TXT_VAZIO);
    }

    /**
     * Método onde decide as ações dos botões
     *
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case Constantes.CMBBOX_TIPOEVENTO:
                copiarEventoPorTipoParaCombobox();
                limparTxtArea();
                break;
            case Constantes.CMBBOX_EVENTO:
                limparTxtArea();
                break;
            case Constantes.BTN_SUBMETER:
                if (!txtArea.getText().isEmpty()) {
                    Evento ev = (Evento) dcbmEvento.getSelectedItem();
                    if (ev != null) {
                        Candidatura c = new Candidatura();
                        c.setDados(txtArea.getText(), representante);
                        ev.getListaCandidatura().addCandidatura(c);
                        Constantes.mensagemConfirmar(Constantes.MENSAGEM_CAND_CRIADO_SUCESSO);
                    }
                } else {
                    Constantes.mensagemErro(Constantes.ERRO_TXT_INVALIDO);
                }
                break;
            case Constantes.BTN_SAIR:
                dispose();
                break;
        }
    }

}
