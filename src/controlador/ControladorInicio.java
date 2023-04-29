package controlador;

import Vista.IVistaInicio;
import Vista.VistaInicio;
import conexion.ConexionEnvio;
import conexion.ConexionReceptor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class ControladorInicio implements ActionListener {

    private static ControladorInicio controladorInicio = null;

    private IVistaInicio vista;
    private ConexionReceptor conexionReceptor;
    private ConexionEnvio conexionEnvio;
    private int miPuerto;
    
    int envio=0;
    Socket socketCliente=null;
    String ultMensaje;

    private ControladorSesionLlamada controladorSesionLlamada;
    
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
        this.conexionReceptor.setControladorInicio(this);
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
            	envio=1;
                String IP = vista.getIP();
                int puerto = vista.getPuerto();
                this.vista.limpiarCampo();
                try {
                    this.conexionEnvio = new ConexionEnvio(IP, puerto);
                    controladorSesionLlamada = ControladorSesionLlamada.get(false);
                    controladorSesionLlamada.setConexionEnvio(this.conexionEnvio);
                    conexionEnvio.envia( Integer.toString(miPuerto) );
                    controladorSesionLlamada.setConexionReceptor(conexionReceptor);
                    vista.lanzarVentanaEmergente("Esperando a ser atendido...");

                    if( conexionEnvio.getSocket() == null || conexionEnvio.getSocket().isClosed()){
                        this.conexionEnvio.stopServer();
                        controladorSesionLlamada.setConexionEnvio(null);
                        break;
                    }
                } catch (SocketException ex) {
                    vista.error("Error en la conexion");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
        }

    }
    
    public void setMiPuerto(int puerto) {
    	this.miPuerto = puerto;
    }
    
    public boolean inicieConversacion() {
    	return envio == 1;
    }
    
    public void esconderVista() {
    	this.vista.esconder();
    }
    
}
