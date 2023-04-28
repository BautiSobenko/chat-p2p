package conexion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import controlador.ControladorSesion;

public class HiloEscucha extends Thread{
	
	private Socket socket;
	private ControladorSesion controlador;
	
	public HiloEscucha(Socket socket,ControladorSesion controlador) {
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
                /* Hacemos eco, sólo por depurar */
              //  out.println("Recibido mensaje: " + mensaje);
                
                controlador.muestraMensaje(socket.getPort()+": "+mensaje);
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
