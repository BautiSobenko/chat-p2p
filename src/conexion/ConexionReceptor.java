package conexion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import controlador.ControladorSesion;

import java.net.ServerSocket;

public class ConexionReceptor extends Thread{

	String ip;
	int puerto;
	ControladorSesion controlador;
	
    public ConexionReceptor(String IP, int puerto) throws IOException{
        this.puerto= puerto;
        this.ip= IP;
        
        //crearCliente();
        //crearServidor();
    }
    
	//Caso de modo escucha
	public void run(){
		try { 
			ServerSocket serverSocket = new ServerSocket(puerto);
			System.out.println("Esperando conexion en el puerto "+ puerto);
			while (true) {
				Socket socket = serverSocket.accept();
				new Thread(new HiloEscucha(socket,controlador)).start();
			}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage() + "\n");
			}
	}

	public void setControlador(ControladorSesion controlador) {
		this.controlador = controlador;
	}
	
	
	public void stopServer() {
		this.interrupt();
	}
	
}
