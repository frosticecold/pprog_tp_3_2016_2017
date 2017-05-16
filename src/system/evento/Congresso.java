package system.evento;

import java.io.Serializable;
import java.util.Objects;
import utils.Data;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class Congresso extends Evento implements Serializable{



    public Congresso() {
        super();
    }

    public Congresso(String titulo, String descricao,String nomeLocal, Data dataInicio, Data dataFim) {
        super(titulo, descricao,nomeLocal, dataInicio, dataFim);
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
        return true;
    }

}
