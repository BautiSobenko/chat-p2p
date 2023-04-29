package conexion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import controlador.ControladorSesion;
import controlador.ControladorSesionLlamada;

public class HiloEscucha extends Thread{
	
	private Socket socket;
	private ControladorSesionLlamada controlador;
	
	public HiloEscucha(Socket socket, ControladorSesionLlamada controlador) {
		this.socket = socket;
		this.controlador = controlador;
	}

	public void run() {

        try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            /* Mensajes recibidos */
            String mensaje;
            
            while (true) {
                mensaje = in.readLine();
                System.out.println("Mensaje  " + mensaje);
                
                controlador.muestraMensaje(socket.getPort()+": "+ mensaje);
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        /* Cerramos el socket */
        try {
            socket.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
