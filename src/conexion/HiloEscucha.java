package conexion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import controlador.ControladorInicio;
import controlador.ControladorSesionLlamada;

public class HiloEscucha extends Thread{
	
	private Socket socket;
	private ControladorSesionLlamada controladorllamada;
	private ControladorInicio controladorInicio;
	private int acepteLlamada = 0;
	
	public HiloEscucha(Socket socket, ControladorSesionLlamada controlador) {
		this.socket = socket;
		this.controladorllamada = controlador;
		this.controladorInicio = ControladorInicio.get(false);
	}

	public void run() {

        try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            /* Mensajes recibidos */
            String mensaje;
            while (! this.isInterrupted()) {
                mensaje = in.readLine();
                if(mensaje!=null) {
	                if (controladorllamada.noTengoConexionEnvio()) {
	                	if (acepteLlamada == 1)
	                		controladorllamada.creaConexionEnvioAcepto(Integer.parseInt(mensaje));
	                	else
	                		controladorllamada.creaConexionEnvioRechazo(Integer.parseInt(mensaje));
	                }
	                else{
	                	if(mensaje.equals("ACEPTO LLAMADA")) {
	                		controladorllamada.get(true);
	                		controladorInicio.esconderVista();
	                	}
	                	else {
	                		if(mensaje.equals("RECHAZO LLAMADA")) {
	                			controladorllamada.llamadaRechazada();
	                			controladorllamada.desconectar();
	                			this.interrupt();
	                		}
		                	else
		                		if(mensaje.equals("DESCONECTAR")){
									controladorllamada.desconectar();
									this.interrupt();
								}
		                		else
		                			controladorllamada.muestraMensaje(socket.getPort()+": "+ mensaje);
	                		}
	                }   
				}
            }
        } catch(Exception e) {
         e.printStackTrace();
        }
    }
	
	public void aceptoLlamada() { this.acepteLlamada = 1;}
	public void rechazoLlamada() { this.acepteLlamada = 0;}
	
}
