package model.evento;

import java.io.Serializable;
import model.listas.ListaAtribuicao;
import model.listas.ListaCandidatura;
import model.listas.ListaFae;
import model.listas.ListaOrganizador;
import utils.Data;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public abstract class Evento implements Comparable<Evento>, Serializable {

    /**
     * String com o título do evento
     */
    private String titulo;
    /**
     * String com a descrição do evento
     */
    private String descricao;
    /**
     * Data Inicio
     */
    private Data dataInicio;
    /**
     * Data Fim
     */
    private Data dataFim;
    /**
     * Local de realização do evento
     */
    private String local;

    /**
     * Lista de Organizadores de um Evento
     */
    private ListaOrganizador listaOrganizador = new ListaOrganizador();
    /**
     * Lista de Candidaturas de um Evento
     */
    private ListaCandidatura listaCandidatura = new ListaCandidatura();
    /**
     * Lista de Fae de um Evento
     */
    private ListaFae listaFae = new ListaFae();

    /**
     * Lista de Atribuição de um Evento
     */
    private ListaAtribuicao listaAtribuicao = new ListaAtribuicao();
    /**
     * Valor por omissão do Titulo de um Evento
     */
    private final static String TITULO_OMISSAO = "Sem Titulo";
    /**
     * Valor por omissão da Descrição de um Evento
     */
    private final static String DESCRICAO_OMISSAO = "Sem descrição";

    /**
     * Valor por omissão de um Local
     *
     */
    private static final String LOCAL_OMISSAO = "Sem local";

    /**
     * Construtor vazio de um evento
     */
    public Evento() {
        titulo = TITULO_OMISSAO;
        descricao = DESCRICAO_OMISSAO;
        local = LOCAL_OMISSAO;
        dataInicio = new Data();
        dataFim = new Data();
    }

    /**
     * Construtor completo de um Evento, que recebe como parâmetro um título,
     * uma descrição, o nome do Local, uma data de inicio e uma data final
     *
     * @param titulo - Titulo de um Evento
     * @param descricao - Descrição de um Evento
     * @param nomeLocal - Nome do local de um Evento
     * @param dataInicio - Data inicio de um Evento
     * @param dataFim - Data fim de um Evento
     */
    public Evento(String titulo, String descricao, String nomeLocal, Data dataInicio, Data dataFim) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.local = nomeLocal;
        this.dataInicio = new Data(dataInicio);
        this.dataFim = new Data(dataFim);
    }

    /**
     *
     * @return Devolve Título do Evento
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @return Devolve a Descrição do Evento
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @return Devolve a Data de Inicio de um Evento
     */
    public Data getDataInicio() {
        return dataInicio;
    }

    /**
     * @return Devolve a Data de Fim de um Evento
     */
    public Data getDataFim() {
        return dataFim;
    }

    /**
     * @return Devolve a lista de Organizador de um Evento
     */
    public ListaOrganizador getListaOrganizador() {
        return listaOrganizador;
    }

    /**
     * @return Devolve a lista de Candidatura de um Evento
     */
    public ListaCandidatura getListaCandidatura() {
        return listaCandidatura;
    }

    /**
     *
     * @return Devolve a lista de Fae de um Evento
     */
    public ListaFae getListaFae() {
        return listaFae;
    }

    /**
     * @return Devolve a lista de Atribuições de um Evento
     */
    public ListaAtribuicao getListaAtribuicao() {
        return listaAtribuicao;
    }

    /**
     *
     * @return Devolve o nome do local
     */
    public String getLocal() {
        return local;
    }

    /**
     * Modifica o titulo de um objeto tipo Evento
     *
     * @param titulo Titulo a modificar de um Evento
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Modifica a descrição de um objeto tipo Evento
     *
     * @param descricao Descrição a modificar de um Evento
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Modifica a data de inicio de um objeto tipo Evento
     *
     * @param dataInicio Data
     */
    public void setDataInicio(Data dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * Modifica a data de fim de um objeto tipo Evento
     *
     * @param dataFim the dataFim to set
     */
    public void setDataFim(Data dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * Modifica o lodal de um objeto tipo Evento
     *
     * @param local
     */
    public void setLocal(String local) {
        this.local = local;
    }

    /**
     * Compara se dois objetos são iguais
     *
     * @param obj Objeto a comparar com
     * @return Verdadeiro ou Falso
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evento other = (Evento) obj;
        if (!this.titulo.equals(other.titulo)) {
            return false;
        }
        if (!this.descricao.equals(other.descricao)) {
            return false;
        }
        if (!this.dataInicio.equals(other.dataInicio)) {
            return false;
        }
        if (!this.dataFim.equals(other.dataFim)) {
            return false;
        }
        if (!this.listaOrganizador.equals(other.listaOrganizador)) {
            return false;
        }
        if (!this.listaCandidatura.equals(other.listaCandidatura)) {
            return false;
        }
        return true;
    }

    /**
     * Retorna a descrição de um objeto
     *
     * @return descrição de um objeto
     */
    @Override
    public String toString() {
        return String.format("Título:%s Descrição:%s Local:%s Data Inicio:%s Data Fim:%s", titulo, descricao, local, dataInicio, dataFim);
    }

    /**
     * Método para compare te que retorna a ordem de um evento conforme o título
     *
     * @param o Outro evento a comparar
     * @return um inteiro com a comparação lexical de dois titulos de dois
     * eventos
     */
    @Override
    public int compareTo(Evento o) {
        return this.getTitulo().compareTo(o.getTitulo());
    }

}
