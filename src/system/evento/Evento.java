package system.evento;

import system.listas.ListaCandidatura;
import system.listas.ListaFae;
import system.listas.ListaOrganizador;
import utils.Data;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public abstract class Evento implements Comparable<Evento> {

    private String titulo, descricao;
    private Data dataInicio, dataFim;
    //periodo atribuiçao ??

    private ListaOrganizador listaOrganizador = new ListaOrganizador();

    private ListaCandidatura listaCandidatura = new ListaCandidatura();
    private ListaFae listaFae = new ListaFae();

    private String TITULO_OMISSAO = "Sem Titulo",
            DESCRICAO_OMISSAO = "Sem descrição";

    public Evento() {
        titulo = TITULO_OMISSAO;
        descricao = descricao;
        dataInicio = new Data();
        dataFim = new Data();
    }

    public Evento(String titulo, String descricao, Data dataInicio, Data dataFim) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = new Data(dataInicio);
        this.dataFim = new Data(dataFim);
        listaOrganizador = new ListaOrganizador();
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @return the dataInicio
     */
    public Data getDataInicio() {
        return dataInicio;
    }

    /**
     * @return the dataFim
     */
    public Data getDataFim() {
        return dataFim;
    }

    /**
     * @return the listaOrganizador
     */
    public ListaOrganizador getListaOrganizador() {
        return listaOrganizador;
    }

    /**
     * @return the listaCandidatura
     */
    public ListaCandidatura getListaCandidatura() {
        return listaCandidatura;
    }

    public ListaFae getListaFae() {
        return listaFae;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @param dataInicio the dataInicio to set
     */
    public void setDataInicio(Data dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @param dataFim the dataFim to set
     */
    public void setDataFim(Data dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @param listaOrganizador the listaOrganizador to set
     */
    public void setListaOrganizador(ListaOrganizador listaOrganizador) {
        this.listaOrganizador = listaOrganizador;
    }

    /**
     * @param listaCandidatura the listaCandidatura to set
     */
    public void setListaCandidatura(ListaCandidatura listaCandidatura) {
        this.listaCandidatura = listaCandidatura;
    }

    public void setListaFae(ListaFae listaFae) {
        this.listaFae = listaFae;
    }

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
        if (!(this.titulo.equals(other.titulo))) {
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

    @Override
    public String toString() {
        return titulo;
    }

    @Override
    public int compareTo(Evento o) {
        return this.getTitulo().compareTo(o.getTitulo());
    }
    

}
