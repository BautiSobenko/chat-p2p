package conexion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import controlador.ControladorConfiguracion;
import controlador.ControladorRecepcionLlamada;
import controlador.ControladorSesion;
import controlador.ControladorSesionLlamada;

import java.net.ServerSocket;

public class ConexionReceptor extends Thread{

	private String ip;
	private int puerto;
	private ControladorSesionLlamada controlador;

    public ConexionReceptor(String IP, int puerto) throws IOException{
        this.puerto = puerto;
        this.ip = IP;
    }
    
	//Caso de modo escucha
	public void run(){
		try { 
			ServerSocket serverSocket = new ServerSocket(puerto);
			System.out.println("Esperando conexion en el puerto "+ puerto);
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("PASE");

				ControladorRecepcionLlamada	 controladorRecepcion = ControladorRecepcionLlamada.get(false);
				controladorRecepcion.setSocket(socket);
				ControladorRecepcionLlamada.get(true);
				if( controladorRecepcion.isConexionAceptada() ){
					controladorRecepcion.setSocket(socket);
					new Thread( controladorRecepcion.creaHiloEscucha() ).start();
				}
			}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage() + "\n");
			}
	}

	public void setControlador(ControladorSesionLlamada controlador) {
		this.controlador = controlador;
	}
	
	
	public void stopServer() {
		this.interrupt();
	}
	
}
