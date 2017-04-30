package system.listas;

import java.util.ArrayList;
import java.util.List;
import system.evento.Workshop;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class ListaWorkshop {

    List<Workshop> lwp;

    public ListaWorkshop() {
        lwp = new ArrayList<>();
    }

    public boolean addWorkshop(Workshop wk) {
        if (!lwp.contains(wk)) {
            return lwp.add(wk);
        }
        return false;
    }

}
