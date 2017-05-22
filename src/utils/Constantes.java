package utils;

import java.awt.Dimension;
import javax.swing.JOptionPane;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public interface Constantes {

    //JanelaPrincipal
    public static final String JP_MENU_PRINCIPAL = "Menu";
    public static final String JP_MENU_DEBUG = "Debug";

    public static final String JP_MENU_INICIAR = "Iniciar";
    public static final String JP_MENU_ITEM_LOGIN = "Mudar Login";
    public static final String JP_MENU_ITEM_SAIR = "Sair";
    public static final String JP_MENU_PONTO_INTERROGACAO = "?";
    public static final String JP_MENU_ITEM_ATRIB_CAND = "Atribuir Candidatura";
    public static final String JP_MENU_ITEM_DECID_CAND = "Decidir Candidatura";
    public static final String JP_MENU_ITEM_SUB_CAND = "Submeter Candidatura";
    public static final String JP_MENU_ITEM_RELOAD_DB = "Recarregar BaseDados";
    public static final String JP_MENU_ITEM_DEBUG_LIMPAR = "Limpar Centro Eventos";
    public static final String JP_MENU_FICHEIRO = "Ficheiro";
    public static final String JP_MENU_ITEM_CARREGAR_FICHEIRO = "Carregar ficheiro";
    public static final String JP_MENU_ITEM_GRAVAR_FICHEIRO = "Gravar Ficheiro";
    public static final String TITULO_JANELA_PRINCIPAL = "Aplicação PPROG TP3";
    public static final String ACERCA_MENSAGEM = "Raúl Correia - 1090657@isep.ipp.pt\nSalvador Gouveia 1151238@isep.ipp.pt";
    public static final String ACERCA = "Acerca";

//Icons
    public static final String ICON_FOLDER = "icons/";
    public static final String ICON_ANTERIOR = "back.png";
    public static final String ICON_SEGUINTE = "foward.png";
    public static final String ICON_SAVE = "save.gif";
    public static final String ICON_EXIT = "exit.png";
    public static final String ICON_DECIDIR_CANDIDATURA = "martelo.gif";
    public static final String ICON_LOAD_FILE = "plus.gif";
    public static final String ICON_SUBMETER_CANDIDATURA = "mala.gif";
    public static final String ICON_ATRIBUIR_CANDIDATURA = "raio.gif";
    public static final String ICON_ABOUT = "about.gif";
//Titulos
    public static final String TITULO_BORDER = "Decisão";
    public static final String TITULO_BORDA_DECIDIR_CANDIDATURA = "Decidir Candidatura";
    public static final String TITULO_JANELA_LOGIN = "Janela Login";
    public static final String TITULO_JANELA_ATRIBUIR = "Atribuir Candidatura";
    public final static String TITULO_JANELA_DECID_CAND = "Submeter Candidatura";
//Erro
    public static final String ERRO_TITULO = "Erro";
    public static final String ERRO_SELECIONAR = "Sem seleção";
    public static final String ERRO_GUARDAR = "Texto Inválido";
    public static final String ERRO_N_EXISTE_EVENTOS = "Erro, não existem Eventos.";
    public static final String ERRO_INSTANCIAR_ALGORITMO = "A instanciação do algoritmo correu mal.";
    public static final String ERRO_NAO_TEM_ATRIBUICOES = "O fae não tem atribuições associadas.";
    public static final String ERRO_INPUT_INVALIDO = "Erro, input inválido.";
    public static final String ERRO_ALG_SEMCAND_OU_FAE = "Erro sem dados para atribuir.";
    public static final String ERRO_USER_N_E_REP_EMP = "Erro, user não é representante de empresa.";
    public static final String ERRO_FICHEIRO_NAO_ENCONTRADO = "Erro, Ficheiro não encontrado";
    public static final String ERRO_FICHEIRO_IO_EXCEPTION = "Erro de I/O";
    public static final String ERRO_CLASSE_NAO_ENCONTRADA = "Erro Classe não encontrada";
    public static final String ERRO_UTILIZADOR_INVALIDO = "Erro, Utilizador inválido";
    public static final String ERRO_SEM_UTILIZADORES = "Erro, Sem utilizadores";
    public static final String ERRO_TXT_INVALIDO = "Erro, Texto inválido";

//Mensagens
    public static final String MENSAGEM_EVENTO = "Seleciona o Evento";
    public static final String MENSAGEM_ALGORITMO = "Seleciona o Algoritmo de Atribuição";
    public static final String MENSAGEM_ERRO_SEM_CANDIDATURAS = "Erro, sem candidaturas...";
    public static final String MENSAGEM_CAND_CRIADO_SUCESSO = "Foi criada candidatura com sucesso";
//Texto
    public static final String TXT_APROVADO = "Aprovado";
    public static final String TXT_LBL_EMPRESA = "Empresa: ";
    public static final String TXT_VAZIO = "";
    public static final String TXT_REPROVADO = "Reprovado";

//Tooltips
    public static final String TOOLTIP_ATRIB_CAND_JLIST_FAEDISPONIVEIS = "Lista de Faes disponíveis";
    public static final String TOOLTIP_ATRIB_CAND_JLIST_LISTA_CANDIDATURAS = "Lista de Candidaturas disponíveis";
    public static final String TOOLTIP_ATRIB_CAND_JLIST_FAE_ATRIB_POR_CAND = "Lista de Faes Atribuidos à candidatura";
    public static final String TOOLTIP_ATRIB_CAND_ATRIBUIR = "Atribuir Faes";
    public static final String TOOLTIP_ATRIB_CAND_SAIR = "Sair da janela";
    public static final String TOOLTIP_ATRIB_CAND_GUARDAR = "Guardar atribuições";
    public static final String TOOLTIP_DC_SUBMETER = "Guardar a submissão da candidatura";
    public static final String TOOLTIP_DC_SAIR = "Cancelar a submissão da candidatura";
    public static final String TOOLTIP_DC_TIPO_EVENTO = "Selecione o Tipo de Evento";
    public static final String TOOLTIP_DC_TXT_AREA = "Texto justificativo da candidatura";
    public static final String TOOLTIP_DC_EVENTO = "Selecione o Evento";

    //Buttons
    public static final String BTN_GUARDAR = "Guardar";
    public static final String BTN_SAIR = "Sair";
    public static final String BTN_ATRIBUI = "Atribui";
    public static final String BTN_SEGUINTE = "Seguinte";
    public static final String BTN_ANTERIOR = "Anterior";
    public static final String BTN_SUBMETER = "Submeter";
    //Checkbox
    public static final String CHKBX_APROVADO = "Aprovado";
    public static final String CHKBX_REPROVADO = "Reprovado";

    //Combobox
    public static final String CMBBOX_EVENTO = "CBX_Evento";
    public static final String CMBBOX_TIPOEVENTO = "CBX_TipoEvento";

//Ints
    public static final int GAP_ZERO = 0;
    public static final int GAP_CINCO = 5;
    public static final int GAP_DEZ = 10;
    public static final int EMPTY_BORDER_GAP_DEZ = 10;
    public static final int INDICE_ZERO = 0;
//Dimensões Janela
    public static final Dimension TAMANHO_MINIMO_DECIDIR_CANDIDATURA = new Dimension(650, 450);
    public static final Dimension TAMANHO_MINIMO_JANELA_PRINCIPAL = new Dimension(450, 300);
    public static final Dimension TAMANHO_MINIMO_JANELA_ATRIBUIR_CAND = new Dimension(650, 350);
    public static final Dimension TAMANHO_MINIMO_JANELA_DECIDIR_CAND = new Dimension(327, 266);
    //Outras dimensões
    public static final Dimension ATRIB_CAND_BTN_DIMENSION = new Dimension(95, 30);
    public static final Dimension ATRIB_CAND_COMBOBOX_DIMENSION = new Dimension(150, 30);

    public static void mensagemErro(String msg) {
        JOptionPane.showMessageDialog(null, msg, Constantes.ERRO_TITULO, JOptionPane.ERROR_MESSAGE);
    }

    public static void mensagemConfirmar(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}
