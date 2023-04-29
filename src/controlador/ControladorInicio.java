package controlador;

import Vista.IVistaInicio;
import Vista.VistaInicio;
import conexion.ConexionEnvio;
import conexion.ConexionReceptor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.net.SocketException;

public class ControladorInicio implements ActionListener {

    private static ControladorInicio controladorInicio = null;

    private IVistaInicio vista;
    private ConexionReceptor conexionReceptor;
    private ConexionEnvio conexionEnvio;

    int envio=0;
    Socket socketCliente=null;
    String ultMensaje;

    private ControladorInicio() {
        this.vista = new VistaInicio();
        this.vista.setActionListener(this);
    }

    public static ControladorInicio get(boolean mostrar) {
        if( controladorInicio == null) {
            controladorInicio = new ControladorInicio();
        }
        if( mostrar )
            controladorInicio.vista.mostrar();

        return controladorInicio;
    }

    public void setConexionReceptor(ConexionReceptor conexion){
        this.conexionReceptor = conexion;
        this.conexionReceptor.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();

        switch (comando) {
            case "Configuracion":
                ControladorConfiguracion.get(true);
                this.vista.esconder();
            break;

            case "Conectar":
                String IP = vista.getIP();
                System.out.println(IP);
                int puerto = vista.getPuerto();
                this.vista.limpiarCampo();
                try {
                    this.conexionEnvio = new ConexionEnvio(IP, puerto);
                    ControladorSesionLlamada.get(true);
                } catch (SocketException ex) {
                    //TODO: Ventana emergente
                    System.out.println("No se puede comunicar");
                }
                break;
        }

    }
}
