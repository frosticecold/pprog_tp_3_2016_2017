package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
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

    private Evento ev;

    //Users
    //private static final String GESTOR = "gestor";
    private static final String ORGANIZADOR = "organizador";
    private static final String UTILIZADOR = "utilizador";
    private static final String REPRESENTANTE = "representante";
    private static final String FAE = "fae";

    //Eventos
    private static final String CONGRESSO = "congresso";
    private static final String EXPOSICAO = "exposicao";

    //Candidatura
    private static final String CANDIDATURA = "candidatura";

    private static final int CAMPO_UTILZ_NOME = 1,
            CAMPO_UTILZ_USERNAME = 2,
            CAMPO_UTILZ_EMAIL = 3,
            CAMPO_UTILZ_PASSWORD = 4,
            CAMPO_FAE_EXPERIENCIA_PROFISSIONAL = 5;
    private static final int CAMPO_REPEMP_NOME_EMPRESA = 5;

    private static final int CAMPO_EVENTO_NOME = 1,
            CAMPO_EVENTO_DESCRICAO = 2,
            CAMPO_EVENTO_LOCAL = 3,
            CAMPO_DATA_INICIO_ANO = 4,
            CAMPO_DATA_INICIO_MES = 5,
            CAMPO_DATA_INICIO_DIA = 6,
            CAMPO_DATA_FIM_ANO = 7,
            CAMPO_DATA_FIM_MES = 8,
            CAMPO_DATA_FIM_DIA = 9;

    private static final int CAMPO_CAND_NOME = 1,
            CAMPO_CAND_REP_USERNAME = 2;

    private static final String SPLIT_SYMBOL = ",";
    private static final char SYMBOL_IGNORAR = '#';
    private static final String FILE_NAME = "dados.txt";

    public LerBaseDados(CentroEventos ce) {
        final int primeiroCaractere = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(FILE_NAME));
            String linha = br.readLine();
            while (linha != null) {
                if (linha.length() > 0 && linha.charAt(primeiroCaractere) != SYMBOL_IGNORAR) {
                    decidirString(ce, linha);
                }
                linha = br.readLine();
            }

        } catch (IOException ex) {
            Logger.getLogger(LerBaseDados.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(LerBaseDados.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void decidirString(CentroEventos ce, String decidir) {
        final String[] array = decidir.split(SPLIT_SYMBOL);
        switch (array[0].toLowerCase()) {
            case ORGANIZADOR:
                Organizador o = lerOrganizador(array);
                adicionarUser(o, ce);
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

    private Organizador lerOrganizador(String[] array) {
        final String user = array[CAMPO_UTILZ_NOME];
        final String username = array[CAMPO_UTILZ_USERNAME];
        final String email = array[CAMPO_UTILZ_EMAIL];
        final char[] password = array[CAMPO_UTILZ_PASSWORD].toCharArray();

        return new Organizador(user, username, email, password);
    }

    private Utilizador lerUtilizador(String[] array) {
        final String user = array[CAMPO_UTILZ_NOME];
        final String username = array[CAMPO_UTILZ_USERNAME];
        final String email = array[CAMPO_UTILZ_EMAIL];
        final char[] password = array[CAMPO_UTILZ_PASSWORD].toCharArray();

        return new Utilizador(user, username, email, password);
    }

    private RepresentanteEmpresa lerRepEmpresa(String[] array) {
        final String user = array[CAMPO_UTILZ_NOME];
        final String username = array[CAMPO_UTILZ_USERNAME];
        final String email = array[CAMPO_UTILZ_EMAIL];
        final char[] password = array[CAMPO_UTILZ_PASSWORD].toCharArray();
        final String nomeEmp = array[CAMPO_REPEMP_NOME_EMPRESA];
        return new RepresentanteEmpresa(user, username, email, password, nomeEmp);
    }

    private Fae lerFae(String[] array) {
        final String user = array[CAMPO_UTILZ_NOME];
        final String username = array[CAMPO_UTILZ_USERNAME];
        final String email = array[CAMPO_UTILZ_EMAIL];
        final char[] password = array[CAMPO_UTILZ_PASSWORD].toCharArray();
        final int experiencia_profissional = Integer.parseInt(array[CAMPO_FAE_EXPERIENCIA_PROFISSIONAL]);
        return new Fae(user, username, email, password, experiencia_profissional);
    }

    private Congresso lerCongresso(String[] array) {
        final String nome = array[CAMPO_EVENTO_NOME];
        final String descricao = array[CAMPO_EVENTO_DESCRICAO];
        final String local = array[CAMPO_EVENTO_LOCAL];
        final Data dataInicio = new Data(CAMPO_DATA_INICIO_ANO, CAMPO_DATA_INICIO_MES, CAMPO_DATA_INICIO_DIA);
        final Data dataFim = new Data(CAMPO_DATA_FIM_ANO, CAMPO_DATA_FIM_MES, CAMPO_DATA_FIM_DIA);
        return new Congresso(nome, descricao, local, dataInicio, dataFim);

    }

    private Exposicao lerExposicao(String[] array) {
        final String nome = array[CAMPO_EVENTO_NOME];
        final String descricao = array[CAMPO_EVENTO_DESCRICAO];
        final String local = array[CAMPO_EVENTO_LOCAL];
        final Data dataInicio = new Data(CAMPO_DATA_INICIO_ANO, CAMPO_DATA_INICIO_MES, CAMPO_DATA_INICIO_DIA);
        final Data dataFim = new Data(CAMPO_DATA_FIM_ANO, CAMPO_DATA_FIM_MES, CAMPO_DATA_FIM_DIA);
        return new Exposicao(nome, descricao, local, dataInicio, dataFim);

    }

    private Candidatura lerCandidatura(String[] array, CentroEventos ce) {
        final String descricao = array[CAMPO_CAND_NOME];
        final RepresentanteEmpresa re = (RepresentanteEmpresa) ce.getRegistoUtilizadores()
                .obterUtilizadorPorUsername(array[CAMPO_CAND_REP_USERNAME]);
        if (re != null) {
            return new Candidatura(descricao, re);
        }
        return null;
    }

    private void adicionarUser(Utilizador u, CentroEventos ce) {
        if (u != null) {
            ce.getRegistoUtilizadores().adicionarUtilizador(u);
        }

    }

    private void adicionarEvento(Evento ev, CentroEventos ce) {
        if (ev != null) {
            ce.getRegistoEventos().adicionarEvento(ev);
        }
    }

    private void adicionarCandidatura(Evento ev, Candidatura cd) {
        if (ev != null) {
            ev.getListaCandidatura().addCandidatura(cd);
        }
    }
}
