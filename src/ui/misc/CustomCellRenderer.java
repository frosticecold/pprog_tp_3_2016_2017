package ui.misc;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import model.candidatura.Candidatura;
import model.evento.Evento;
import model.user.Utilizador;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public class CustomCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Evento) {
            Evento ev = (Evento) value;
            setText(ev.getTitulo()); // where getValue is some method you implement that gets the text you want to render for the component
        } else if (value instanceof Candidatura) {
            Candidatura cd = (Candidatura) value;
            setText(cd.getDescricao());
        } else if (value instanceof Utilizador) {
            Utilizador u = (Utilizador) value;
            setText(u.getNome());
        }
        return c;
    }
}
