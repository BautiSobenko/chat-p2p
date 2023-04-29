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
            while (true) {
                mensaje = in.readLine();
                if (controladorllamada.noTengoConexionEnvio()) {
                	controladorllamada.creaConexionEnvio(Integer.parseInt(mensaje));
                }
                else
                	if(mensaje.equals("ACEPTO LLAMADA")) {
                		controladorllamada.get(true);
                		controladorInicio.esconderVista();
                	}
                	else
                		if(mensaje.equals("DESCONECTAR"))
                			controladorllamada.desconectar();
                		else
                			controladorllamada.muestraMensaje(socket.getPort()+": "+ mensaje);
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
	
}
