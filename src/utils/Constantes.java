package utils;

import java.awt.Dimension;

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
    public static final String ACERCA = "Raúl Correia - 1090657@isep.ipp.pt\nSalvador Gouveia 1151238@isep.ipp.pt";
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
//Erro
    public static final String ERRO_TITULO = "Erro";
    public static final String ERRO_SELECIONAR = "Sem seleção";
    public static final String ERRO_GUARDAR = "Texto Inválido";
    public static final String ERRO_N_EXISTE_EVENTOS = "Erro, não existem Eventos.";
    //public static final String ERRO_EXCLAMACAO = "Erro!";

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

//Ints
    public static final int GAP_CINCO = 5;
    public static final int EMPTY_BORDER_GAP_DEZ = 10;

//Dimensões Janela
    public static final Dimension TAMANHO_DECIDIR_CANDIDATURA_MINIMO = new Dimension(650, 450);
    public static final Dimension TAMANHO_JANELA_PRINCIPAL_MINIMO = new Dimension(450, 300);

}
