package system.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import system.CentroEventos;
import system.Teste;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class JanelaPrincipal extends JFrame implements ActionListener {
    
    private JMenuBar menubar;
    private JMenu menu, ficheiro, func, help;
    private JMenuItem sair, about, debug;
    private JMenuItem atribuir_cand, decidir_cand, submeter_cand;
    
    private CentroEventos ce;
    private static String ICON_FOLDER = "icons/";
    
    public JanelaPrincipal(CentroEventos ce) {
        super("Aplicação PPROG TP3");
        this.ce = ce;
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(450, 300));
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void initComponents() {
        menubar = new JMenuBar();
        
        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_M);
        
        func = new JMenu("Iniciar");
        func.setMnemonic(KeyEvent.VK_I);
        
        debug = new JMenuItem("Carregador valores Debug");
        debug.addActionListener(this);
        atribuir_cand = new JMenuItem("Atribuir Candidatura");
        atribuir_cand.addActionListener(this);
        decidir_cand = new JMenuItem("Decidir Candidatura");
        submeter_cand = new JMenuItem("Submeter Candidatura");
        
        func.add(atribuir_cand);
        func.add(decidir_cand);
        func.add(submeter_cand);
        
        ficheiro = new JMenu("Ficheiro");
        ficheiro.setMnemonic(KeyEvent.VK_F);
        
        sair = new JMenuItem("Sair");
        sair.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(ICON_FOLDER + "exit.png")));
        sair.addActionListener(this);
        
        help = new JMenu("?");
        about = new JMenuItem("Acerca");
        about.addActionListener(this);
        help.add(debug);
        help.add(about);
        
        menu.add(func);
        menu.addSeparator();
        menu.add(sair);
        menubar.add(menu);
        //menubar.add(Box.createHorizontalGlue());
        menubar.add(help);
        setJMenuBar(menubar);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == about) {
            JOptionPane.showMessageDialog(null, "Raúl Correia - 1090657@isep.ipp.pt\nSalvador Gouveia 1151238@isep.ipp.pt");
        }
        if (e.getSource() == sair) {
            System.exit(0);
        }
        if (e.getSource() == atribuir_cand) {
            AtribuirCandidatura ac = new AtribuirCandidatura(ce);
        }
        if (e.getSource() == debug) {
            Teste t = new Teste();
            t.init();
            ce.setRegistoEventos(t.getRe());
            ce.setRegistoUtilizadores(t.getRu());
        }
    }
    
}
