package system.evento;

import java.util.Objects;
import system.listas.ListaWorkshop;
import utils.Data;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Congresso extends Evento {

    private ListaWorkshop lwk = new ListaWorkshop();

    public Congresso() {
        super();
    }

    public Congresso(String titulo, String descricao, Data dataInicio, Data dataFim) {
        super(titulo, descricao, dataInicio, dataFim);
    }

    public ListaWorkshop getListaWorkshop() {
        return lwk;
    }

    @Override
    public boolean equals(Object obj) {

        if (!super.equals(obj)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Congresso other = (Congresso) obj;
        if (!Objects.equals(this.lwk, other.lwk)) {
            return false;
        }

        return true;
    }

}
