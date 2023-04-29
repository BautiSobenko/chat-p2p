package conexion;

import controlador.ControladorSesionLlamada;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;


public class ConexionEnvio{

	private String Ip;
	private int puerto;

	private Socket socket;
	private PrintWriter out;

	
	public ConexionEnvio (String ip, int puerto) throws SocketException {
		this.Ip = ip;
		this.puerto = puerto;

		try {

			this.socket = new Socket(this.Ip,this.puerto);
			out = new PrintWriter(socket.getOutputStream(),true);

		} catch (SocketException e) {
			throw new SocketException();
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
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
