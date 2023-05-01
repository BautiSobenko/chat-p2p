package vista.interfaces;

import java.awt.event.ActionListener;

public interface IVistaRecepcionLlamada {

    public void setActionListener(ActionListener controlador);
    public void mostrar();
    public void esconder();
    public void setLabelPuerto(String puerto);

}
