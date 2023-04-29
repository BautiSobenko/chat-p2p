package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import conexion.ConexionEnvio;
import conexion.ConexionReceptor;
import Vista.IVista;
import Vista.VistaSesion;

//! NO VA

public class ControladorSesion implements ActionListener{

    private IVista vista;
    ConexionReceptor conexionReceptor;
    ConexionEnvio conexionEnvio;
    int envio=0;
    Socket socketCliente=null;
    String ultMensaje;
    
    public ControladorSesion (IVista vista,ConexionReceptor conexionReceptor){
        this.vista = vista;
        vista.setActionListener(this);
        this.conexionReceptor = conexionReceptor;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Enviar":
				this.ultMensaje = vista.getMensaje();
				vista.limpiarCampo();
            	if (envio==0) {	
            		//this.conexionEnvio = new ConexionEnvio(vista.getIP(),vista.getPuerto(),this);
            		envio=1;
            		}
            	this.conexionEnvio.envia(ultMensaje);
            	this.muestraMensaje("Yo: " + ultMensaje);
				//vista.lanzarVentanaEmergente("Conexion establecida");
                break;
            case "Desconectar":
				try {
					this.envio=0;
					this.conexionEnvio.stopServer();
					this.conexionReceptor.stopServer();
					vista.lanzarVentanaEmergente("Conexion desconectada");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                break;

        }
    }
    
    
    public void muestraMensaje(String msg) {
    	vista.agregarLineaChat(msg);
    }
    
    public String getMsg() {
    	return this.ultMensaje;
    }
    

}
