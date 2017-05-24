package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CentroEventos;
import model.candidatura.Candidatura;
import model.evento.Congresso;
import model.evento.Evento;
import model.evento.Exposicao;
import model.user.Fae;
import model.user.Organizador;
import model.user.RepresentanteEmpresa;
import model.user.Utilizador;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class LerBaseDados {

    /**
     * Referencia ao centro de eventos
     */
    private CentroEventos ce;
    /**
     * Evento lido no ficheiro de texto ao qual se vai adicionar dados
     */
    private Evento ev;

    //Users
    //private static final String GESTOR = "gestor";
    /**
     * Constante para identificar um organizador
     */
    private static final String ORGANIZADOR = "organizador";
    /**
     * Constante para identificar um utilizador
     */
    private static final String UTILIZADOR = "utilizador";
    /**
     * Constante para identificar um representante de empresa
     */
    private static final String REPRESENTANTE = "representante";
    /**
     * Constante para identificar um fae de empresa
     */
    private static final String FAE = "fae";

    //Eventos
    /**
     * Constante para identificar um congresso
     */
    private static final String CONGRESSO = "congresso";
    /**
     * Constante para identificar uma exposição
     */
    private static final String EXPOSICAO = "exposicao";

    //Candidatura
    /**
     * Constante para identificar uma caniddatura
     */
    private static final String CANDIDATURA = "candidatura";

    /**
     * Campos a identificar as posições de quais dados são guardados num
     * utilizador/fae
     */
    private static final int CAMPO_UTILZ_NOME = 1,
            CAMPO_UTILZ_USERNAME = 2,
            CAMPO_UTILZ_EMAIL = 3,
            CAMPO_UTILZ_PASSWORD = 4;
    /**
     * Campo a identificar a posição do dado extra de fae ou Representante
     * Empresa
     */
    private static final int CAMPO_REPEMP_NOME_EMPRESA = 5, CAMPO_FAE_EXPERIENCIA_PROFISSIONAL = 5;

    /**
     * Campos a identificar as posições dos dados de um evento
     */
    private static final int CAMPO_EVENTO_NOME = 1,
            CAMPO_EVENTO_DESCRICAO = 2,
            CAMPO_EVENTO_LOCAL = 3,
            CAMPO_DATA_INICIO_ANO = 4,
            CAMPO_DATA_INICIO_MES = 5,
            CAMPO_DATA_INICIO_DIA = 6,
            CAMPO_DATA_FIM_ANO = 7,
            CAMPO_DATA_FIM_MES = 8,
            CAMPO_DATA_FIM_DIA = 9;

    /**
     * Campos a identificar as posições dos dados de uma candidatura
     */
    private static final int CAMPO_CAND_NOME = 1,
            CAMPO_CAND_REP_USERNAME = 2;

    /**
     * Símbolo separador de string
     */
    private static final String SPLIT_SYMBOL = ",";

    /**
     * Símbolo para ignorar uma linha
     */
    private static final char SYMBOL_IGNORAR = '#';
    /**
     * Ficheiro onde está a base de dados
     */
    private static final String FILE_NAME = "dados.txt";

    /**
     * Construtor de LerBaseDados que recebe como parâmetro o centro de eventos
     *
     * @param ce
     */
    public LerBaseDados(CentroEventos ce) {
        this.ce = ce;
    }

    /**
     * Método que lê o ficheiro dados.txt
     */
    public void lerBaseDados() {
        final int primeiroCaractere = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(FILE_NAME));
            String linha = br.readLine();
            while (linha != null) {
                if (linha.length() > 0 && linha.charAt(primeiroCaractere) != SYMBOL_IGNORAR) {
                    decidirString(linha);
                }
                linha = br.readLine();
            }

        } catch (IOException ex) {
            Constantes.mensagemErro(Constantes.ERRO_FICHEIRO_IO_EXCEPTION);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    Constantes.mensagemErro(Constantes.ERRO_FICHEIRO_IO_EXCEPTION);
                }
            }
        }
    }

    /**
     * Método que recebe uma string e decide o que fazer com ela Se adiciona
     * utilizador,evento ou candidatura
     *
     * @param linha String a receber para decisão
     */
    public void decidirString(String linha) {
        final String[] array = linha.split(SPLIT_SYMBOL);
        switch (array[0].toLowerCase()) {
            case ORGANIZADOR:
                Organizador o = lerOrganizador(array);
                if (ev != null) {
                    adicionarOrganizador(o, ev);
                }
                break;
            case UTILIZADOR:
                Utilizador u = lerUtilizador(array);
                adicionarUser(u, ce);
                break;
            case REPRESENTANTE:
                RepresentanteEmpresa re = lerRepEmpresa(array);
                adicionarUser(re, ce);
                break;
            case FAE:
                Fae f = lerFae(array);
                if (ev != null) {
                    ev.getListaFae().adicionarFae(f);
                }
                break;
            case CONGRESSO:
                Congresso cg = lerCongresso(array);
                ev = cg;
                adicionarEvento(ev, ce);
                break;
            case EXPOSICAO:
                Exposicao exp = lerExposicao(array);
                ev = exp;
                adicionarEvento(ev, ce);
                break;
            case CANDIDATURA:
                Candidatura cd = lerCandidatura(array, ce);
                if (ev != null) {
                    adicionarCandidatura(ev, cd);
                }
                break;
            default:
                break;
        }
    }

    /**
     * Método para ler um Organizador
     *
     * @param array linha já separada em array
     * @return Organizador
     */
    private Organizador lerOrganizador(String[] array) {
        final String user = array[CAMPO_UTILZ_NOME];
        final String username = array[CAMPO_UTILZ_USERNAME];
        final String email = array[CAMPO_UTILZ_EMAIL];
        final char[] password = array[CAMPO_UTILZ_PASSWORD].toCharArray();

        return new Organizador(user, username, email, password);
    }

    /**
     * Método para ler um Utilizador
     *
     * @param array linha já separada em array
     * @return Utilizador
     */
    private Utilizador lerUtilizador(String[] array) {
        final String user = array[CAMPO_UTILZ_NOME];
        final String username = array[CAMPO_UTILZ_USERNAME];
        final String email = array[CAMPO_UTILZ_EMAIL];
        final char[] password = array[CAMPO_UTILZ_PASSWORD].toCharArray();

        return new Utilizador(user, username, email, password);
    }

    /**
     * Método para ler um RepresentanteEmpresa
     *
     * @param array linha já separada em array
     * @return RepresentanteEmpresa
     */
    private RepresentanteEmpresa lerRepEmpresa(String[] array) {
        final String user = array[CAMPO_UTILZ_NOME];
        final String username = array[CAMPO_UTILZ_USERNAME];
        final String email = array[CAMPO_UTILZ_EMAIL];
        final char[] password = array[CAMPO_UTILZ_PASSWORD].toCharArray();
        final String nomeEmp = array[CAMPO_REPEMP_NOME_EMPRESA];
        return new RepresentanteEmpresa(user, username, email, password, nomeEmp);
    }

    /**
     * Método para ler um Fae
     *
     * @param array linha já separada em array
     * @return Fae
     */
    private Fae lerFae(String[] array) {
        final String user = array[CAMPO_UTILZ_NOME];
        final String username = array[CAMPO_UTILZ_USERNAME];
        final String email = array[CAMPO_UTILZ_EMAIL];
        final char[] password = array[CAMPO_UTILZ_PASSWORD].toCharArray();
        final int experiencia_profissional = Integer.parseInt(array[CAMPO_FAE_EXPERIENCIA_PROFISSIONAL]);
        return new Fae(user, username, email, password, experiencia_profissional);
    }

    /**
     * Método para ler um Congresso
     *
     * @param array linha já separada em array
     * @return Congresso
     */
    private Congresso lerCongresso(String[] array) {
        final String nome = array[CAMPO_EVENTO_NOME];
        final String descricao = array[CAMPO_EVENTO_DESCRICAO];
        final String local = array[CAMPO_EVENTO_LOCAL];
        final Data dataInicio = new Data(CAMPO_DATA_INICIO_ANO, CAMPO_DATA_INICIO_MES, CAMPO_DATA_INICIO_DIA);
        final Data dataFim = new Data(CAMPO_DATA_FIM_ANO, CAMPO_DATA_FIM_MES, CAMPO_DATA_FIM_DIA);
        return new Congresso(nome, descricao, local, dataInicio, dataFim);

    }

    /**
     * Método para ler uma Exposicao
     *
     * @param array linha já separada em array
     * @return Exposicao
     */
    private Exposicao lerExposicao(String[] array) {
        final String nome = array[CAMPO_EVENTO_NOME];
        final String descricao = array[CAMPO_EVENTO_DESCRICAO];
        final String local = array[CAMPO_EVENTO_LOCAL];
        final Data dataInicio = new Data(CAMPO_DATA_INICIO_ANO, CAMPO_DATA_INICIO_MES, CAMPO_DATA_INICIO_DIA);
        final Data dataFim = new Data(CAMPO_DATA_FIM_ANO, CAMPO_DATA_FIM_MES, CAMPO_DATA_FIM_DIA);
        return new Exposicao(nome, descricao, local, dataInicio, dataFim);

    }

    /**
     * Método para ler uma Candidatura
     *
     * @param array linha já separada em array
     * @return Candidatura
     */
    private Candidatura lerCandidatura(String[] array, CentroEventos ce) {
        final String descricao = array[CAMPO_CAND_NOME];
        final RepresentanteEmpresa re = (RepresentanteEmpresa) ce.getRegistoUtilizadores()
                .obterUtilizadorPorUsername(array[CAMPO_CAND_REP_USERNAME]);
        if (re != null) {
            return new Candidatura(descricao, re);
        }
        return null;
    }

    /**
     * Adiciona um organizador a um evento
     *
     * @param o Organizador
     * @param ev Evento a adicionar
     */
    private void adicionarOrganizador(Organizador o, Evento ev) {
        if (o != null) {
            ev.getListaOrganizador().adicionarOrganizador(o);
        }

    }

    /**
     * Adiciona um utilizador ao centro de eventos
     *
     * @param u Utilizador
     * @param ce Centro de Eventos
     */
    private void adicionarUser(Utilizador u, CentroEventos ce) {
        if (u != null) {
            ce.getRegistoUtilizadores().adicionarUtilizador(u);
        }

    }

    /**
     * Adiciona um evento ao centro de eventos
     *
     * @param ev Evento
     * @param ce Centro de Eventos
     */
    private void adicionarEvento(Evento ev, CentroEventos ce) {
        if (ev != null) {
            ce.getRegistoEventos().adicionarEvento(ev);
        }
    }

    /**
     * Adiciona uma candidatura ao evento
     *
     * @param ev Evento
     * @param cd Candidatura
     */
    private void adicionarCandidatura(Evento ev, Candidatura cd) {
        if (ev != null) {
            ev.getListaCandidatura().addCandidatura(cd);
        }
    }
}
