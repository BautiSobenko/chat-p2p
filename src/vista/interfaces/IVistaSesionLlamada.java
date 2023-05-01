package vista.interfaces;

import java.awt.event.ActionListener;

public interface IVistaSesionLlamada {

    public void setActionListener(ActionListener controlador);
    public void mostrar();
    public void esconder();
    String getMensaje();
    void agregarLineaChat(String mensaje);
    void limpiarCampo();
	public void lanzarVentanaEmergente(String string);
    public void error(String string);
    void eliminarHistorial();
}
