package system.listas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import system.candidatura.Atribuicao;
import system.user.Fae;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class ListaAtribuicao implements Iterable<Atribuicao>, Serializable {

    private List<Atribuicao> lista;

    public ListaAtribuicao() {
        lista = new ArrayList<>();
    }

    public Atribuicao novaAtribuicao() {

        return new Atribuicao();
    }

    public boolean add(Atribuicao a) {
        return lista.add(a);
    }

    public void guardarAtribuicoes(List<Atribuicao> listaAtrib) {
        for (Atribuicao atribuicao : listaAtrib) {
            add(atribuicao);
        }

    }

    @Override
    public Iterator<Atribuicao> iterator() {
        return lista.iterator();
    }

    public List<Fae> obterTodosFaesAtribuidos() {
        List<Fae> listaFae = new ArrayList<>();
        for (Atribuicao a : lista) {
            listaFae.add(a.getFae());
        }
        return listaFae;
    }

    public List<Atribuicao> obterAtribuicoesAssociadaAoFae(String username) {
        List<Atribuicao> listaAtrib = new ArrayList<>();
        for (Atribuicao a : lista) {
            if (a.getFae().getUsername().equals(username)) {
                listaAtrib.add(a);
            }
        }
        return listaAtrib;
    }

    public boolean verificarSeFaeTemAtribuicoes(String username) {
        for (Atribuicao a : lista) {
            if (a.getFae().getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public int size() {

        return lista.size();
    }

    public void clear() {
        lista.clear();
    }

}
