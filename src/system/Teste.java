package system;

import java.util.List;
import system.candidatura.Atribuicao;
import system.candidatura.Candidatura;
import system.evento.Congresso;
import system.evento.Evento;
import system.evento.Exposicao;
import system.listas.RegistoEvento;
import system.user.Utilizador;
import system.listas.RegistoUtilizador;
import system.user.Fae;
import system.user.GestorEvento;
import system.user.RepresentanteEmpresa;
import utils.Data;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Teste {

    RegistoUtilizador ru;
    RegistoEvento re;
    private static char[] DP = "123".toCharArray();

    public void init(CentroEventos ce) {

        ru = new RegistoUtilizador();
        re = new RegistoEvento();

        //Gestores de Evento
        GestorEvento raul = new GestorEvento("Raul", "raul", "raul@raul.pt", DP);
        GestorEvento salvador = new GestorEvento("Salvador", "salvador", "salvador@salvador.pt", DP);
        ru.add(raul);
        ru.add(salvador);
        //Utilizadores
        Utilizador u1 = new Utilizador("Ana Maria", "ana.maria", "anamaria@abc.pt", DP);
        Utilizador u2 = new Utilizador("Bernardo Noé", "b.noe", "bnoe@abc.pt", DP);
        Utilizador u3 = new Utilizador("Carlos Costa", "ccosta", "ccosta@abc.pt", DP);
        Utilizador u4 = new Utilizador("Domingos Neves", "domingos", "domingos@abc.pt", DP);
        Utilizador u5 = new Utilizador("Fábia Lopes", "flopes", "flopes@abc.pt", DP);
        Utilizador u6 = new Utilizador("Hércules", "herc", "herc@abc.pt", DP);
        Utilizador u7 = new Utilizador("Joana Silva", "jsilva", "jsilva@abc.pt", DP);
        Utilizador u8 = new Utilizador("Jorge Mendes", "jorgemendes", "jmendes@abc.pt", DP);
        Utilizador u9 = new Utilizador("André Nicolau", "anicolau", "anicolau@abc.pt", DP);
        Utilizador u10 = new Utilizador("Catia Ariana", "catiaa", "catiaa@abc.pt", DP);
        Utilizador u11 = new Utilizador("Miguel Lopes", "miguellopes", "miguellopes@abc.pt", DP);
        Utilizador u12 = new Utilizador("Luis Sousa", "luiss", "luiss@abc.pt", DP);
        Utilizador u13 = new Utilizador("Tiago Silva", "tiagosilva", "tiagosilva@abc.pt", DP);
        Utilizador u14 = new Utilizador("Andreia Antunes", "andreiaa", "andreiaa@abc.pt", DP);
        Utilizador u15 = new Utilizador("Utilizador a", "usera", "usera@user.pt", DP);
        Utilizador u16 = new Utilizador("Utilizador b", "userb", "userb@user.pt", DP);
        Utilizador u17 = new Utilizador("Utilizador c", "userc", "userc@user.pt", DP);
        Utilizador u18 = new Utilizador("Utilizador d", "userd", "userd@user.pt", DP);
        Utilizador u19 = new Utilizador("Utilizador e", "usere", "usere@user.pt", DP);
        Utilizador u20 = new Utilizador("Utilizador f", "userf", "userf@user.pt", DP);
        Utilizador u21 = new Utilizador("Utilizador g", "userg", "userg@user.pt", DP);
        Utilizador u22 = new Utilizador("Utilizador h", "userh", "userh@user.pt", DP);
        Utilizador u23 = new Utilizador("Utilizador i", "useri", "useri@user.pt", DP);
        Utilizador u24 = new Utilizador("Utilizador j", "userj", "userj@user.pt", DP);
        Utilizador u25 = new Utilizador("Utilizador k", "userk", "userk@user.pt", DP);
        //Utilizadores para Serem Representantes Empresa
        RepresentanteEmpresa rep1 = new RepresentanteEmpresa("Representante 1", "rep1", "rep1@rep1.pt", "Empresa A", DP);
        RepresentanteEmpresa rep2 = new RepresentanteEmpresa("Representante 2", "rep2", "rep2@rep2.pt", "Empresa B", DP);
        RepresentanteEmpresa rep3 = new RepresentanteEmpresa("Representante 3", "rep3", "rep3@rep3.pt", "Empresa C", DP);
        RepresentanteEmpresa rep4 = new RepresentanteEmpresa("Representante 4", "rep4", "rep4@rep4.pt", "Empresa D", DP);
        RepresentanteEmpresa rep5 = new RepresentanteEmpresa("Representante 5", "rep5", "rep5@rep5.pt", "Empresa E", DP);
        RepresentanteEmpresa rep6 = new RepresentanteEmpresa("Representante 6", "rep6", "rep6@rep6.pt", "Empresa F", DP);
        RepresentanteEmpresa rep7 = new RepresentanteEmpresa("Representante 7", "rep7", "rep7@rep7.pt", "Empresa G", DP);
        RepresentanteEmpresa rep8 = new RepresentanteEmpresa("Representante 8", "rep8", "rep8@rep8.pt", "Empresa H", DP);

        ru.add(rep1);
        ru.add(rep2);
        ru.add(rep3);
        ru.add(rep4);
        ru.add(rep5);
        ru.add(rep6);
        ru.add(rep7);
        ru.add(rep8);

        ru.add(u1);
        ru.add(u2);
        ru.add(u3);
        ru.add(u4);
        ru.add(u5);
        ru.add(u6);
        ru.add(u7);
        ru.add(u8);
        ru.add(u9);
        ru.add(u10);
        ru.add(u11);
        ru.add(u12);
        ru.add(u13);
        ru.add(u14);
        ru.add(u15);
        ru.add(u16);
        ru.add(u17);
        ru.add(u18);
        ru.add(u19);
        ru.add(u20);
        ru.add(u21);
        ru.add(u22);
        ru.add(u23);
        ru.add(u24);
        ru.add(u25);
        
        
        Candidatura cand1 = new Candidatura("Candidatura da Empresa X", rep1);
        Candidatura cand2 = new Candidatura("Candidatura da Empresa Y", rep2);
        Candidatura cand3 = new Candidatura("Candidatura da Empresa W", rep3);
        Candidatura cand4 = new Candidatura("Candidatura da Empresa Z", rep4);

        Congresso c1 = new Congresso("Congresso 1", "O congresso 1", "Local 1", new Data(2016, 8, 11), new Data(2018, 9, 24));
        c1.getListaOrganizador().add(u1);
        c1.getListaOrganizador().add(u2);
        c1.getListaFae().adicionarFae(u7);
        c1.getListaFae().adicionarFae(u8);
        c1.getListaFae().adicionarFae(u9);
        c1.getListaFae().adicionarFae(u10);
        c1.getListaFae().adicionarFae(u11);
        c1.getListaFae().adicionarFae(u12);
        c1.getListaFae().adicionarFae(u13);
        c1.getListaFae().adicionarFae(u18);
        c1.getListaOrganizador().add(u14);
        c1.getListaOrganizador().add(u15);

        c1.getListaCandidatura().addCandidatura(cand1);
        c1.getListaCandidatura().addCandidatura(cand2);

        Congresso c2 = new Congresso("Congresso 2", "O congresso 2", "Local 2", new Data(2015, 12, 25), new Data(2016, 1, 10));
        c2.getListaOrganizador().add(u1);
        c2.getListaOrganizador().add(u3);
        c2.getListaFae().adicionarFae(u2);
        c2.getListaFae().adicionarFae(u4);
        c2.getListaFae().adicionarFae(u5);
        c2.getListaFae().adicionarFae(u6);
        c2.getListaCandidatura().addCandidatura(cand3);
        c2.getListaCandidatura().addCandidatura(cand4);
        Congresso c3 = new Congresso("Congresso 3", "O congresso 3", "Local 3", new Data(2007, 2, 22), new Data(2007, 4, 25));
        c3.getListaOrganizador().add(u2);
        c3.getListaOrganizador().add(u3);
        c3.getListaFae().adicionarFae(u5);
        c3.getListaFae().adicionarFae(u6);
        c3.getListaFae().adicionarFae(u7);
        c3.getListaFae().adicionarFae(u8);
        Congresso c4 = new Congresso("Congresso 4", "O congresso 4", "Local 4", new Data(2016, 1, 1), new Data(2017, 6, 1));
        c4.getListaOrganizador().add(u4);
        c4.getListaOrganizador().add(u5);
        c4.getListaFae().adicionarFae(u2);
        c4.getListaFae().adicionarFae(u6);
        c4.getListaFae().adicionarFae(u7);
        c4.getListaFae().adicionarFae(u8);
        Congresso c5 = new Congresso("Congresso 5", "O congresso 5", "Local 5", new Data(2017, 1, 5), new Data(2017, 12, 01));
        c5.getListaOrganizador().add(u1);
        c5.getListaOrganizador().add(u5);
        c5.getListaFae().adicionarFae(u10);
        c5.getListaFae().adicionarFae(u11);
        c5.getListaFae().adicionarFae(u12);
        c5.getListaFae().adicionarFae(u13);
        Exposicao e1 = new Exposicao("Exposicao 1", "A exposicao 1", "Expo 1", new Data(2017, 2, 11), new Data(2017, 6, 24));
        e1.getListaOrganizador().add(u1);
        e1.getListaOrganizador().add(u4);
        e1.getListaFae().adicionarFae(u5);
        e1.getListaFae().adicionarFae(u6);
        e1.getListaFae().adicionarFae(u7);
        e1.getListaFae().adicionarFae(u8);
        Exposicao e2 = new Exposicao("Exposicao 2", "A exposicao 2", "Expo 2", new Data(2017, 1, 25), new Data(2018, 1, 10));
        e2.getListaOrganizador().add(u2);
        e2.getListaOrganizador().add(u5);
        e2.getListaFae().adicionarFae(u13);
        e2.getListaFae().adicionarFae(u6);
        e2.getListaFae().adicionarFae(u7);
        e2.getListaFae().adicionarFae(u8);
        Exposicao e3 = new Exposicao("Exposicao 3", "A exposicao 3", "Expo 3", new Data(2016, 2, 22), new Data(2017, 4, 25));
        e3.getListaOrganizador().add(u3);
        e3.getListaOrganizador().add(u6);
        e3.getListaFae().adicionarFae(u5);
        e3.getListaFae().adicionarFae(u9);
        e3.getListaFae().adicionarFae(u7);
        e3.getListaFae().adicionarFae(u8);
        e3.getListaCandidatura().addCandidatura(cand4);
        Atribuicao a = new Atribuicao(new Fae(u9), cand4);
        e3.getListaAtribuicao().add(a);
        Exposicao e4 = new Exposicao("Exposicao 4", "A exposicao 4", "Expo 4", new Data(2015, 5, 5), new Data(2018, 6, 01));
        e4.getListaOrganizador().add(u6);
        e4.getListaOrganizador().add(u7);
        e4.getListaFae().adicionarFae(u5);
        e4.getListaFae().adicionarFae(u10);
        e4.getListaFae().adicionarFae(u11);
        e4.getListaFae().adicionarFae(u8);

        re.add(c1);
        re.add(c2);
        re.add(c3);
        re.add(c4);
        re.add(c5);
        re.add(e1);
        re.add(e2);
        re.add(e3);
        re.add(e4);

        //testeEventoPorOrganizador(u15.getUsername());
        ce.setRegistoEventos(re);
        ce.setRegistoUtilizadores(ru);

//        compararSeDoisUtilizadoresIguais(u1, u2);
//        compararSeDoisUtilizadoresIguais(u1, u1);
//        compararSeDoisUtilizadoresIguais(u15, u16);
    }

    public RegistoUtilizador getRu() {
        return ru;
    }

    public RegistoEvento getRe() {
        return re;
    }

    public void escreverRegistoUtilizador() {
        for (Utilizador utilizador : ru) {
            System.out.println(utilizador);
        }
    }

    public void testeEventoPorOrganizador(String username) {
        List<Evento> le = re.getListaEventoPorOrganizador(username);
        for (Evento evento : le) {
            System.out.println("O utilizador " + username + " é organizador do evento: " + evento);
        }
    }

    public static Fae retornarFaeTeste(CentroEventos ce) {
        String anic = "anicolau";
        for (Evento ev : ce.getRegistoEventos()) {
            if (ev.getListaFae().isFaeEvento(anic)) {
                return ev.getListaFae().procurarFaePorUsername(anic);
            }
        }
        return null;
    }

    public static RepresentanteEmpresa retornarRepEmpTeste(CentroEventos ce) {
        for (Utilizador utilizador : ce.getRegistoUtilizadores()) {
            if (utilizador instanceof RepresentanteEmpresa) {
                return (RepresentanteEmpresa) utilizador;
            }
        }
        return null;
    }

    private void compararSeDoisUtilizadoresIguais(Utilizador u1, Utilizador u2) {
        System.out.println(String.format("O Utilizador %s é igual ao %s ? \n %b", u1.getNome(), u2.getNome(), u1.equals(u2)));
    }
}
