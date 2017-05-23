package model;

import java.util.List;
import model.candidatura.Atribuicao;
import model.candidatura.Candidatura;
import model.evento.Congresso;
import model.evento.Evento;
import model.evento.Exposicao;
import model.listas.RegistoEvento;
import model.user.Utilizador;
import model.listas.RegistoUtilizador;
import model.user.Fae;
import model.user.GestorEvento;
import model.user.RepresentanteEmpresa;
import utils.Data;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Teste {

    RegistoUtilizador ru;
    RegistoEvento re;
    private final static char[] DEFAULT_PASSWORD = "123".toCharArray();

    /**
     * Método que inicializa todos os dados de teste e Reinicializa o
     * RegistoUtilizador e Registo de Evento
     *
     * @param ce Referência ao centro de eventos
     */
    public void init(CentroEventos ce) {

        ru = new RegistoUtilizador();
        re = new RegistoEvento();

        //Gestores de Evento
        GestorEvento raul = new GestorEvento("Raul", "raul", "raul@raul.pt", DEFAULT_PASSWORD);
        GestorEvento salvador = new GestorEvento("Salvador", "salvador", "salvador@salvador.pt", DEFAULT_PASSWORD);
        ru.adicionarUtilizador(raul);
        ru.adicionarUtilizador(salvador);
        //Utilizadores
        Utilizador u1 = new Utilizador("Ana Maria", "ana.maria", "anamaria@abc.pt", DEFAULT_PASSWORD);
        Utilizador u2 = new Utilizador("Bernardo Noé", "b.noe", "bnoe@abc.pt", DEFAULT_PASSWORD);
        Utilizador u3 = new Utilizador("Carlos Costa", "ccosta", "ccosta@abc.pt", DEFAULT_PASSWORD);
        Utilizador u4 = new Utilizador("Domingos Neves", "domingos", "domingos@abc.pt", DEFAULT_PASSWORD);
        Utilizador u5 = new Utilizador("Fábia Lopes", "flopes", "flopes@abc.pt", DEFAULT_PASSWORD);
        Utilizador u6 = new Utilizador("Hércules", "herc", "herc@abc.pt", DEFAULT_PASSWORD);
        Utilizador u7 = new Utilizador("Joana Silva", "jsilva", "jsilva@abc.pt", DEFAULT_PASSWORD);
        Utilizador u8 = new Utilizador("Jorge Mendes", "jorgemendes", "jmendes@abc.pt", DEFAULT_PASSWORD);
        Utilizador u9 = new Utilizador("André Nicolau", "anicolau", "anicolau@abc.pt", DEFAULT_PASSWORD);
        Utilizador u10 = new Utilizador("Catia Ariana", "catiaa", "catiaa@abc.pt", DEFAULT_PASSWORD);
        Utilizador u11 = new Utilizador("Miguel Lopes", "miguellopes", "miguellopes@abc.pt", DEFAULT_PASSWORD);
        Utilizador u12 = new Utilizador("Luis Sousa", "luiss", "luiss@abc.pt", DEFAULT_PASSWORD);
        Utilizador u13 = new Utilizador("Tiago Silva", "tiagosilva", "tiagosilva@abc.pt", DEFAULT_PASSWORD);
        Utilizador u14 = new Utilizador("Andreia Antunes", "andreiaa", "andreiaa@abc.pt", DEFAULT_PASSWORD);
        Utilizador u15 = new Utilizador("Utilizador a", "usera", "usera@user.pt", DEFAULT_PASSWORD);
        Utilizador u16 = new Utilizador("Utilizador b", "userb", "userb@user.pt", DEFAULT_PASSWORD);
        Utilizador u17 = new Utilizador("Utilizador c", "userc", "userc@user.pt", DEFAULT_PASSWORD);
        Utilizador u18 = new Utilizador("Utilizador d", "userd", "userd@user.pt", DEFAULT_PASSWORD);
        Utilizador u19 = new Utilizador("Utilizador e", "usere", "usere@user.pt", DEFAULT_PASSWORD);
        Utilizador u20 = new Utilizador("Utilizador f", "userf", "userf@user.pt", DEFAULT_PASSWORD);
        Utilizador u21 = new Utilizador("Utilizador g", "userg", "userg@user.pt", DEFAULT_PASSWORD);
        Utilizador u22 = new Utilizador("Utilizador h", "userh", "userh@user.pt", DEFAULT_PASSWORD);
        Utilizador u23 = new Utilizador("Utilizador i", "useri", "useri@user.pt", DEFAULT_PASSWORD);
        Utilizador u24 = new Utilizador("Utilizador j", "userj", "userj@user.pt", DEFAULT_PASSWORD);
        Utilizador u25 = new Utilizador("Utilizador k", "userk", "userk@user.pt", DEFAULT_PASSWORD);
        //Utilizadores para Serem Representantes Empresa
        RepresentanteEmpresa rep1 = new RepresentanteEmpresa("Representante 1", "rep1", "rep1@rep1.pt", DEFAULT_PASSWORD, "Empresa A");
        RepresentanteEmpresa rep2 = new RepresentanteEmpresa("Representante 2", "rep2", "rep2@rep2.pt", DEFAULT_PASSWORD, "Empresa B");
        RepresentanteEmpresa rep3 = new RepresentanteEmpresa("Representante 3", "rep3", "rep3@rep3.pt", DEFAULT_PASSWORD, "Empresa C");
        RepresentanteEmpresa rep4 = new RepresentanteEmpresa("Representante 4", "rep4", "rep4@rep4.pt", DEFAULT_PASSWORD, "Empresa D");
        RepresentanteEmpresa rep5 = new RepresentanteEmpresa("Representante 5", "rep5", "rep5@rep5.pt", DEFAULT_PASSWORD, "Empresa E");
        RepresentanteEmpresa rep6 = new RepresentanteEmpresa("Representante 6", "rep6", "rep6@rep6.pt", DEFAULT_PASSWORD, "Empresa F");
        RepresentanteEmpresa rep7 = new RepresentanteEmpresa("Representante 7", "rep7", "rep7@rep7.pt", DEFAULT_PASSWORD, "Empresa G");
        RepresentanteEmpresa rep8 = new RepresentanteEmpresa("Representante 8", "rep8", "rep8@rep8.pt", DEFAULT_PASSWORD, "Empresa H");

        ru.adicionarUtilizador(rep1);
        ru.adicionarUtilizador(rep2);
        ru.adicionarUtilizador(rep3);
        ru.adicionarUtilizador(rep4);
        ru.adicionarUtilizador(rep5);
        ru.adicionarUtilizador(rep6);
        ru.adicionarUtilizador(rep7);
        ru.adicionarUtilizador(rep8);

        ru.adicionarUtilizador(u1);
        ru.adicionarUtilizador(u2);
        ru.adicionarUtilizador(u3);
        ru.adicionarUtilizador(u4);
        ru.adicionarUtilizador(u5);
        ru.adicionarUtilizador(u6);
        ru.adicionarUtilizador(u7);
        ru.adicionarUtilizador(u8);
        ru.adicionarUtilizador(u9);
        ru.adicionarUtilizador(u10);
        ru.adicionarUtilizador(u11);
        ru.adicionarUtilizador(u12);
        ru.adicionarUtilizador(u13);
        ru.adicionarUtilizador(u14);
        ru.adicionarUtilizador(u15);
        ru.adicionarUtilizador(u16);
        ru.adicionarUtilizador(u17);
        ru.adicionarUtilizador(u18);
        ru.adicionarUtilizador(u19);
        ru.adicionarUtilizador(u20);
        ru.adicionarUtilizador(u21);
        ru.adicionarUtilizador(u22);
        ru.adicionarUtilizador(u23);
        ru.adicionarUtilizador(u24);
        ru.adicionarUtilizador(u25);

        Candidatura cand1 = new Candidatura("Candidatura da Empresa X", rep1);
        Candidatura cand2 = new Candidatura("Candidatura da Empresa Y", rep2);
        Candidatura cand3 = new Candidatura("Candidatura da Empresa W", rep3);
        Candidatura cand4 = new Candidatura("Candidatura da Empresa Z", rep4);

        Congresso c1 = new Congresso("Congresso 1", "O congresso 1", "Local 1", new Data(2016, 8, 11), new Data(2018, 9, 24));
        c1.getListaOrganizador().adicionarOrganizador(u1);
        c1.getListaOrganizador().adicionarOrganizador(u2);
        c1.getListaFae().adicionarFae(u7);
        c1.getListaFae().adicionarFae(u8);
        c1.getListaFae().adicionarFae(u9);
        c1.getListaFae().adicionarFae(u10);
        c1.getListaFae().adicionarFae(u11);
        c1.getListaFae().adicionarFae(u12);
        c1.getListaFae().adicionarFae(u13);
        c1.getListaFae().adicionarFae(u18);
        c1.getListaOrganizador().adicionarOrganizador(u14);
        c1.getListaOrganizador().adicionarOrganizador(u15);

        c1.getListaCandidatura().addCandidatura(cand1);
        c1.getListaCandidatura().addCandidatura(cand2);

        Congresso c2 = new Congresso("Congresso 2", "O congresso 2", "Local 2", new Data(2015, 12, 25), new Data(2016, 1, 10));
        c2.getListaOrganizador().adicionarOrganizador(u1);
        c2.getListaOrganizador().adicionarOrganizador(u3);
        c2.getListaFae().adicionarFae(u2);
        c2.getListaFae().adicionarFae(u4);
        c2.getListaFae().adicionarFae(u5);
        c2.getListaFae().adicionarFae(u6);
        c2.getListaCandidatura().addCandidatura(cand3);
        c2.getListaCandidatura().addCandidatura(cand4);
        Congresso c3 = new Congresso("Congresso 3", "O congresso 3", "Local 3", new Data(2007, 2, 22), new Data(2007, 4, 25));
        c3.getListaOrganizador().adicionarOrganizador(u2);
        c3.getListaOrganizador().adicionarOrganizador(u3);
        c3.getListaFae().adicionarFae(u5);
        c3.getListaFae().adicionarFae(u6);
        c3.getListaFae().adicionarFae(u7);
        c3.getListaFae().adicionarFae(u8);
        Congresso c4 = new Congresso("Congresso 4", "O congresso 4", "Local 4", new Data(2016, 1, 1), new Data(2017, 6, 1));
        c4.getListaOrganizador().adicionarOrganizador(u4);
        c4.getListaOrganizador().adicionarOrganizador(u5);
        c4.getListaFae().adicionarFae(u2);
        c4.getListaFae().adicionarFae(u6);
        c4.getListaFae().adicionarFae(u7);
        c4.getListaFae().adicionarFae(u8);
        Congresso c5 = new Congresso("Congresso 5", "O congresso 5", "Local 5", new Data(2017, 1, 5), new Data(2017, 12, 01));
        c5.getListaOrganizador().adicionarOrganizador(u1);
        c5.getListaOrganizador().adicionarOrganizador(u5);
        c5.getListaFae().adicionarFae(u10);
        c5.getListaFae().adicionarFae(u11);
        c5.getListaFae().adicionarFae(u12);
        c5.getListaFae().adicionarFae(u13);
        Exposicao e1 = new Exposicao("Exposicao 1", "A exposicao 1", "Expo 1", new Data(2017, 2, 11), new Data(2017, 6, 24));
        e1.getListaOrganizador().adicionarOrganizador(u1);
        e1.getListaOrganizador().adicionarOrganizador(u4);
        e1.getListaFae().adicionarFae(u5);
        e1.getListaFae().adicionarFae(u6);
        e1.getListaFae().adicionarFae(u7);
        e1.getListaFae().adicionarFae(u8);
        Exposicao e2 = new Exposicao("Exposicao 2", "A exposicao 2", "Expo 2", new Data(2017, 1, 25), new Data(2018, 1, 10));
        e2.getListaOrganizador().adicionarOrganizador(u2);
        e2.getListaOrganizador().adicionarOrganizador(u5);
        e2.getListaFae().adicionarFae(u13);
        e2.getListaFae().adicionarFae(u6);
        e2.getListaFae().adicionarFae(u7);
        e2.getListaFae().adicionarFae(u8);
        Exposicao e3 = new Exposicao("Exposicao 3", "A exposicao 3", "Expo 3", new Data(2016, 2, 22), new Data(2017, 4, 25));
        e3.getListaOrganizador().adicionarOrganizador(u3);
        e3.getListaOrganizador().adicionarOrganizador(u6);
        e3.getListaFae().adicionarFae(u5);
        e3.getListaFae().adicionarFae(u9);
        e3.getListaFae().adicionarFae(u7);
        e3.getListaFae().adicionarFae(u8);
        e3.getListaCandidatura().addCandidatura(cand4);
        Atribuicao a = new Atribuicao(new Fae(u9), cand4);
        e3.getListaAtribuicao().adicionarAtribuicao(a);
        Exposicao e4 = new Exposicao("Exposicao 4", "A exposicao 4", "Expo 4", new Data(2015, 5, 5), new Data(2018, 6, 01));
        e4.getListaOrganizador().adicionarOrganizador(u6);
        e4.getListaOrganizador().adicionarOrganizador(u7);
        e4.getListaFae().adicionarFae(u5);
        e4.getListaFae().adicionarFae(u10);
        e4.getListaFae().adicionarFae(u11);
        e4.getListaFae().adicionarFae(u8);

        re.adicionarEvento(c1);
        re.adicionarEvento(c2);
        re.adicionarEvento(c3);
        re.adicionarEvento(c4);
        re.adicionarEvento(c5);
        re.adicionarEvento(e1);
        re.adicionarEvento(e2);
        re.adicionarEvento(e3);
        re.adicionarEvento(e4);

        //testeEventoPorOrganizador(u15.getUsername());
        ce.setRegistoEventos(re);
        ce.setRegistoUtilizadores(ru);

//        compararSeDoisUtilizadoresIguais(u1, u2);
//        compararSeDoisUtilizadoresIguais(u1, u1);
//        compararSeDoisUtilizadoresIguais(u15, u16);
    }

    /**
     * Escreve todos os utilizadores
     */
    public void escreverRegistoUtilizador() {
        for (Utilizador utilizador : ru) {
            System.out.println(utilizador);
        }
    }

    /**
     * Testa se um utilizador é organizador de eventos guardados
     *
     * @param username String username a procurar
     */
    public void testeEventoPorOrganizador(String username) {
        List<Evento> le = re.getListEventoPorUtilizadorPreDefinido(username, RegistoEvento.UTILIZADOR_ORGANIZADOR);
        for (Evento evento : le) {
            System.out.println("O utilizador " + username + " é organizador do evento: " + evento);
        }
    }

    /**
     * Testa se retorna o Fae anicolau
     *
     * @param ce CentroEventos
     * @return Fae "anicolau2 ou null
     */
    public static Fae retornarFaeTeste(CentroEventos ce) {
        String anic = "anicolau";
        for (Evento ev : ce.getRegistoEventos()) {
            if (ev.getListaFae().isFaeEvento(anic)) {
                return ev.getListaFae().procurarFaePorUsername(anic);
            }
        }
        return null;
    }

    /**
     * Compara se dois utilizadores são iguais e escreve para a consola
     *
     * @param u1 Utilizador 1
     * @param u2 Utilizador 2
     */
    private void compararSeDoisUtilizadoresIguais(Utilizador u1, Utilizador u2) {
        System.out.println(String.format("O Utilizador %s é igual ao %s ? \n %b", u1.getNome(), u2.getNome(), u1.equals(u2)));
    }
}
