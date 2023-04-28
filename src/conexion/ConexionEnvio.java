package conexion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import controlador.ControladorSesion;

public class ConexionEnvio extends Thread{
	private String Ip;
	private int puerto;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private ControladorSesion controlador;
	private String mensaje = null;
	
	
	public ConexionEnvio (String ip,int puerto,ControladorSesion controlador){
		this.Ip = ip;
		this.puerto = puerto;
		this.controlador = controlador;
		try {
			socket = new Socket(this.Ip,this.puerto);
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
	public void envia(String msg) {
          out.println(msg);
	}
	
	
	public void stopServer() throws IOException {
		socket.close();
		out.close();
		this.Ip = null;
		this.puerto = 0;
		this.socket = null;
	}

}
