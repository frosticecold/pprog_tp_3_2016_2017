package system;

import system.evento.Congresso;
import system.evento.Exposicao;
import system.listas.RegistoEvento;
import system.user.Utilizador;
import system.listas.RegistoUtilizador;
import utils.Data;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Teste {

    RegistoUtilizador ru;
    RegistoEvento re;

    public void init(CentroEventos ce) {
        ru = ce.getRegistoUtilizadores();
        re = ce.getRegistoEventos();
        Utilizador u1 = new Utilizador("Ana Maria", "ana.maria", "anamaria@abc.pt", "123");
        Utilizador u2 = new Utilizador("Bernardo Noé", "b.noe", "bnoe@abc.pt", "123");
        Utilizador u3 = new Utilizador("Carlos Costa", "ccosta", "ccosta@abc.pt", "123");
        Utilizador u4 = new Utilizador("Domingos Neves", "domingos", "domingos@abc.pt", "123");
        Utilizador u5 = new Utilizador("Fábia Lopes", "flopes", "flopes@abc.pt", "123");
        Utilizador u6 = new Utilizador("Hércules", "herc", "herc@abc.pt", "123");
        Utilizador u7 = new Utilizador("Joana Silva", "jsilva", "jsilva@abc.pt", "123");
        Utilizador u8 = new Utilizador("Jorge Mendes", "jorgemendes", "jmendes@abc.pt", "123");
        Utilizador u9 = new Utilizador("André Nicolau", "anicolau", "anicolau@abc.pt", "123");
        Utilizador u10 = new Utilizador("Catia Ariana", "catiaa", "catiaa@abc.pt", "123");
        Utilizador u11 = new Utilizador("Miguel Lopes", "miguellopes", "miguellopes@abc.pt", "123");
        Utilizador u12 = new Utilizador("Luis Sousa", "luiss", "luiss@abc.pt", "123");
        Utilizador u13 = new Utilizador("Tiago Silva", "tiagosilva", "tiagosilva@abc.pt", "123");
        Utilizador u14 = new Utilizador("Andreia Antunes", "andreiaa", "andreiaa@abc.pt", "123");
        Utilizador u15 = new Utilizador("Carlos Mendes", "cmendes", "cmendes@abc.pt", "123");

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

        Congresso c1 = new Congresso("Congresso 1", "O congresso 1", "Local 1", new Data(2016, 8, 11), new Data(2018, 9, 24));
        c1.getListaOrganizador().add(u1);
        c1.getListaOrganizador().add(u2);
        c1.getListaFae().adicionarFae(u9);
        c1.getListaFae().adicionarFae(u10);
        c1.getListaFae().adicionarFae(u11);
        c1.getListaFae().adicionarFae(u12);
        Congresso c2 = new Congresso("Congresso 2", "O congresso 2", "Local 2", new Data(2015, 12, 25), new Data(2016, 1, 10));
        c2.getListaOrganizador().add(u1);
        c2.getListaOrganizador().add(u3);
        c2.getListaFae().adicionarFae(u2);
        c2.getListaFae().adicionarFae(u4);
        c2.getListaFae().adicionarFae(u5);
        c2.getListaFae().adicionarFae(u6);
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

//        //Teste
//        System.out.println(Algoritmo.procurarUtilizadorIsOrganizadorEvento(e1, f1.getUsername()));
//        System.out.println(Algoritmo.procurarUtilizadorIsOrganizadorEvento(e1, f2.getUsername()));
//        System.out.println(Algoritmo.procurarUtilizadorIsOrganizadorEvento(e2, f1.getUsername()));
//        System.out.println(Algoritmo.procurarUtilizadorIsOrganizadorEvento(e2, f5.getUsername()));
//        System.out.println(Algoritmo.procurarUtilizadorIsOrganizadorEvento(e3, f3.getUsername()));
//        System.out.println(Algoritmo.procurarUtilizadorIsOrganizadorEvento(e3, f6.getUsername()));
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
}
