package Vista;

import java.awt.event.ActionListener;

public interface IVista {
    void setActionListener(ActionListener controlador);
    void ejecutar();
    String getIP();
    int getPuerto();
    String getMensaje();
    void lanzarVentanaEmergente(String mensaje);
    void borrarMensaje();
    void agregarLineaChat(String mensaje);
    void limpiarCampo();
}
