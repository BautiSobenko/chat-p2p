package controlador;

import vista.interfaces.IVistaSesionLlamada;
import vista.vistas.VistaSesionLlamada;
import conexion.ConexionEnvio;
import conexion.ConexionReceptor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketException;

public class ControladorSesionLlamada implements ActionListener {

    private static ControladorSesionLlamada controladorSesionLlamada = null;

    private IVistaSesionLlamada vista;
    private ConexionEnvio conexionEnvio=null;
    private ConexionReceptor conexionReceptor;


    private ControladorSesionLlamada() {
        this.vista = new VistaSesionLlamada();
        this.vista.setActionListener(this);
    }

    public static ControladorSesionLlamada get(boolean mostrar){
        if( controladorSesionLlamada == null ) {
            controladorSesionLlamada = new ControladorSesionLlamada();
        }

        if( mostrar )
            controladorSesionLlamada.vista.mostrar();

        return controladorSesionLlamada;
    }

    public void setConexionEnvio(ConexionEnvio conexionEnvio) {
        this.conexionEnvio = conexionEnvio;
    }
    
    public void setConexionReceptor(ConexionReceptor conexionReceptor) {
        this.conexionReceptor = conexionReceptor;
        this.conexionReceptor.setControladorLlamada(this);
    }

    public void muestraMensaje(String msg) {
        vista.agregarLineaChat(msg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();

        String ultMensaje;

        switch(comando){
            case("Enviar"):
                ultMensaje = vista.getMensaje();
                vista.limpiarCampo();
                if(ultMensaje!=null) {
                	this.conexionEnvio.envia(ultMensaje);
                	this.muestraMensaje("Yo: " + ultMensaje);
                }
            break;

            case("Desconectar"):
				this.conexionEnvio.envia("DESCONECTAR");
				this.desconectar();
            break;
        }
    }
    
    public void creaConexionEnvioAcepto(int puerto) {
    	try {
			this.conexionEnvio = new ConexionEnvio("localhost",puerto);
			this.conexionEnvio.envia("ACEPTO LLAMADA");
		} catch (SocketException e) {
			e.printStackTrace();
		}
    }
    	
    public boolean noTengoConexionEnvio() {
    	if(conexionEnvio == null)
    		return true;
    	else
    		return false;
    }

    public void desconectar() {
    	try {
    		vista.esconder();
	        ControladorInicio.get(true);
			this.conexionEnvio.stopServer();
			this.conexionEnvio = null;
			vista.eliminarHistorial();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void creaConexionEnvioRechazo(int puerto) {
    	try {
			this.conexionEnvio = new ConexionEnvio("localhost",puerto);
			this.conexionEnvio.envia("RECHAZO LLAMADA");
		} catch (SocketException e) {
			e.printStackTrace();
		}
    }
    
    public void llamadaRechazada() {
    	this.vista.error("La llamada a sido rechazada");
    }


}