package utils;

import java.awt.Dimension;
import javax.swing.JOptionPane;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public interface Constantes {

    //JanelaPrincipal
    public static final String JP_MENU_INICIAR = "Iniciar";
    public static final String JP_SUBMENU_ATRIB_CAND = "Atribuir Candidatura";
    public static final String JP_SUBMENU_DECID_CAND = "Decidir Candidatura";
    public static final String JP_SUBMENU_SUB_CAND = "Submeter Candidatura";
    public static final String TITULO_JANELA_PRINCIPAL = "Aplicação PPROG TP3";
    public static final String ACERCA_MENSAGEM = "Raúl Correia - 1090657@isep.ipp.pt\nSalvador Gouveia 1151238@isep.ipp.pt";
    public static final String TITULO_ACERCA = "Acerca";
    public static final String MENU_INICIAR = "Ficheiro";
    public static final String MENU_ITEM_CARREGAR_FICHEIRO = "Carregar ficheiro";
    public static final String MENU_ITEM_GRAVAR_FICHEIRO = "Gravar Ficheiro";
//Icons
    public static final String ICON_FOLDER = "icons/";
    public static final String ICON_ANTERIOR = "back.png";
    public static final String ICON_SEGUINTE = "foward.png";

//Titulos
    public static final String TITULO_BORDER = "Decisão";
    public static final String TITULO_JANELA = "Decidir Candidatura";
    public static final String TITULO_JANELA_LOGIN = "Janela Login";
    public static final String TITULO_JANELA_ATRIBUIR = "Atribuir Candidatura";
//Erro
    public static final String ERRO_TITULO = "Erro";
    public static final String ERRO_SELECIONAR = "Sem seleção";
    public static final String ERRO_GUARDAR = "Texto Inválido";
    public static final String ERRO_N_EXISTE_EVENTOS = "Erro, não existem Eventos.";
    public static final String ERRO_INSTANCIAR_ALGORITMO = "A instanciação do algoritmo correu mal.";
    public static final String ERRO_NAO_TEM_ATRIBUICOES = "O fae não tem atribuições associadas.";
    //public static final String ERRO_EXCLAMACAO = "Erro!";
//Mensagens
    public static final String MENSAGEM_EVENTO = "Seleciona o Evento";
    public static final String MENSAGEM_ALGORITMO = "Seleciona o Algoritmo de Atribuição";
    public static final String MENSAGEM_ERRO_SEM_CANDIDATURAS = "Erro, sem candidaturas...";
//Texto
    public static final String TXT_APROVADO = "Aprovado";
    public static final String TXT_LBL_EMPRESA = "Empresa: ";
    public static final String TXT_VAZIO = "";
    public static final String TXT_GUARDAR = "Guardar";
    public static final String TXT_REPROVADO = "Reprovado";
    public static final String TXT_SAIR = "Sair";
    public static final String ICON_SAVE = "save.gif";
    public static final String ICON_EXIT = "exit.png";
    public static final String ICON_DECIDIR_CANDIDATURA = "martelo.gif";
    public static final String ICON_LOAD_FILE = "plus.gif";
    public static final String ICON_SUBMETER_CANDIDATURA = "mala.gif";
    public static final String ICON_ATRIBUIR_CANDIDATURA = "raio.gif";
    public static final String ICON_ABOUT = "about.gif";
//Tooltips
    public static final String ATRIB_CAND_TOOLTIP_JLIST_FAEDISPONIVEIS = "Lista de Faes disponíveis";
    public static final String ATRIB_CAND_TOOLTIP_JLIST_LISTA_CANDIDATURAS = "Lista de Candidaturas disponíveis";
    public static final String ATRIB_CAND_TOOLTIP_JLIST_FAE_ATRIB_POR_CAND = "Lista de Faes Atribuidos à candidatura";
    public static final String ATRIB_CAND_TOOLTIP_ATRIBUIR = "Atribuir Faes";
    public static final String ATRIB_CAND_TOOLTIP_SAIR = "Sair da janela";
    public static final String ATRIB_CAND_TOOLTIP_GUARDAR = "Guardar atribuições";

    //Buttons
    public static final String BTN_GUARDAR = "Guardar";
    public static final String BTN_SAIR = "Sair";
    public static final String BTN_ATRIBUI = "Atribui";

//Ints
    public static final int GAP_ZERO = 0;
    public static final int GAP_CINCO = 5;
    public static final int GAP_DEZ = 10;
    public static final int EMPTY_BORDER_GAP_DEZ = 10;
    public static final int INDICE_ZERO = 0;
//Dimensões Janela
    public static final Dimension TAMANHO_DECIDIR_CANDIDATURA_MINIMO = new Dimension(650, 450);
    public static final Dimension TAMANHO_JANELA_MINIMO_PRINCIPAL = new Dimension(450, 300);
    public static final Dimension TAMANHO_JANELA_MINIMO_ATRIBUIR_CAND = new Dimension(650, 350);
    //Outras dimensões
    public static final Dimension ATRIB_CAND_BTN_DIMENSION = new Dimension(95, 30);
    public static final Dimension ATRIB_CAND_COMBOBOX_DIMENSION = new Dimension(150, 30);

    public static void mensagemErro(String msg) {
        JOptionPane.showMessageDialog(null, msg, Constantes.ERRO_TITULO, JOptionPane.ERROR_MESSAGE);
    }
}
