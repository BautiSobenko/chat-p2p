package conexion;

import java.io.IOException;
import java.net.Socket;

import controlador.ControladorInicio;
import controlador.ControladorRecepcionLlamada;
import controlador.ControladorSesionLlamada;

import java.net.ServerSocket;

public class ConexionReceptor extends Thread{

	private String ip;
	private int puerto;
	private ControladorSesionLlamada controladorllamada;
	private ControladorInicio controladorInicio;
	private Thread hiloEscucha;
	private Socket socket;
	private ServerSocket serverSocket;
	private boolean stop;

    public ConexionReceptor(String IP, int puerto) throws IOException{
        this.puerto = puerto;
        this.ip = IP;
    }
    
	//Caso de modo escucha
	public void run(){
		try { 
			while ( true ) {

				this.socket = serverSocket.accept();

				if (!controladorInicio.inicieConversacion()) {
					ControladorRecepcionLlamada controladorRecepcion = ControladorRecepcionLlamada.get(false);
					controladorRecepcion.setSocket(socket);
					controladorRecepcion.setControladorInicio(controladorInicio);
					ControladorRecepcionLlamada.get(true);
				} else {
					this.hiloEscucha = new HiloEscucha(socket, ControladorSesionLlamada.get(false));
					this.hiloEscucha.start();
				}
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public void setControladorLlamada(ControladorSesionLlamada controlador) {
		this.controladorllamada = controlador;
	}
	
	
	public void stopServer() {

		//this.puerto = 0;
		this.hiloEscucha.interrupt();
		//this.interrupt();

	}
	
	public void setControladorInicio(ControladorInicio controlador) {
		this.controladorInicio = controlador;
	}
	
	public void iniciaServidor() throws IOException{
		serverSocket = new ServerSocket(puerto);
	}
}
