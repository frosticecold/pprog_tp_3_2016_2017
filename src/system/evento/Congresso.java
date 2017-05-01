package system.evento;

import java.util.Objects;
import system.listas.ListaWorkshop;
import utils.Data;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Congresso extends Evento {

    private ListaWorkshop listaWorkshop = new ListaWorkshop();

    public Congresso() {
        super();
    }

    public Congresso(String titulo, String descricao,String nomeLocal, Data dataInicio, Data dataFim) {
        super(titulo, descricao,nomeLocal, dataInicio, dataFim);
    }

    public ListaWorkshop getListaWorkshop() {
        return listaWorkshop;
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
        if (!Objects.equals(this.listaWorkshop, other.listaWorkshop)) {
            return false;
        }

        return true;
    }

}
