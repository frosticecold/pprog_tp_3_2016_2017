package system;

import system.algoritmo.Algoritmo;
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

    RegistoUtilizador ru = new RegistoUtilizador();
    RegistoEvento re = new RegistoEvento();

    public void init() {

        Utilizador f1 = new Utilizador("Ana Maria", "ana.maria", "anamaria@abc.pt", "123");
        Utilizador f2 = new Utilizador("Bernardo Noé", "b.noe", "bnoe@abc.pt", "123");
        Utilizador f3 = new Utilizador("Carlos Costa", "ccosta", "ccosta@abc.pt", "123");
        Utilizador f4 = new Utilizador("Domingos Neves", "domingos", "domingos@abc.pt", "123");
        Utilizador f5 = new Utilizador("Fábia Lopes", "flopes", "flopes@abc.pt", "123");
        Utilizador f6 = new Utilizador("Hércules", "herc", "herc@abc.pt", "123");
        Utilizador f7 = new Utilizador("Joana Silva", "jsilva", "jsilva@abc.pt", "123");
        Utilizador f8 = new Utilizador("Jorge Mendes", "jorgemendes", "jmendes@abc.pt", "123");
        Utilizador f9 = new Utilizador("André Nicolau", "anicolau", "anicolau@abc.pt", "123");
        Utilizador f10 = new Utilizador("Catia Ariana", "catiaa", "catiaa@abc.pt", "123");
        Utilizador f11 = new Utilizador("Miguel Lopes", "miguellopes", "miguellopes@abc.pt", "123");
        Utilizador f12 = new Utilizador("Luis Sousa", "luiss", "luiss@abc.pt", "123");
        Utilizador f13 = new Utilizador("Tiago Silva", "tiagosilva", "tiagosilva@abc.pt", "123");
        Utilizador f14 = new Utilizador("Andreia Antunes", "andreiaa", "andreiaa@abc.pt", "123");
        Utilizador f15 = new Utilizador("Carlos Mendes", "cmendes", "cmendes@abc.pt", "123");

        ru.add(f1);
        ru.add(f2);
        ru.add(f3);
        ru.add(f4);
        ru.add(f5);
        ru.add(f6);
        ru.add(f7);
        ru.add(f8);
        ru.add(f9);
        ru.add(f10);
        ru.add(f11);
        ru.add(f12);
        ru.add(f13);
        ru.add(f14);
        ru.add(f15);

        Congresso c1 = new Congresso("Congresso 1", "O congresso 1", new Data(2004, 8, 11), new Data(2008, 9, 24));
        c1.getListaOrganizador().add(f1);
        c1.getListaOrganizador().add(f2);
        Congresso c2 = new Congresso("Congresso 2", "O congresso 2", new Data(2006, 12, 25), new Data(2007, 1, 10));
        c2.getListaOrganizador().add(f1);
        c2.getListaOrganizador().add(f3);
        Congresso c3 = new Congresso("Congresso 3", "O congresso 3", new Data(2007, 2, 22), new Data(2007, 4, 25));
        c3.getListaOrganizador().add(f2);
        c3.getListaOrganizador().add(f3);
        Congresso c4 = new Congresso("Congresso 4", "O congresso 4", new Data(2008, 1, 1), new Data(2009, 1, 1));
        c4.getListaOrganizador().add(f4);
        c4.getListaOrganizador().add(f5);
        Congresso c5 = new Congresso("Congresso 5", "O congresso 5", new Data(2009, 5, 5), new Data(2009, 6, 01));
        c5.getListaOrganizador().add(f1);
        c5.getListaOrganizador().add(f5);

        Exposicao e1 = new Exposicao("Exposicao 1", "A exposicao 1", new Data(2004, 8, 11), new Data(2008, 9, 24));
        e1.getListaOrganizador().add(f1);
        e1.getListaOrganizador().add(f4);
        Exposicao e2 = new Exposicao("Exposicao 2", "A exposicao 2", new Data(2006, 12, 25), new Data(2007, 1, 10));
        e2.getListaOrganizador().add(f2);
        e2.getListaOrganizador().add(f5);
        Exposicao e3 = new Exposicao("Exposicao 3", "A exposicao 3", new Data(2007, 2, 22), new Data(2007, 4, 25));
        e3.getListaOrganizador().add(f3);
        e3.getListaOrganizador().add(f6);
        Exposicao e4 = new Exposicao("Exposicao 4", "A exposicao 4", new Data(2009, 5, 5), new Data(2009, 6, 01));
        e4.getListaOrganizador().add(f6);
        e4.getListaOrganizador().add(f7);

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
