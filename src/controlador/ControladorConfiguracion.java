package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Vista.*;
import conexion.ConexionReceptor;
import configuracion.Configuracion;


public class ControladorConfiguracion implements ActionListener{

	private static ControladorConfiguracion controladorConfiguracion = null;

	private IVistaConfiguracion vista;
    ConexionReceptor conexionReceptor;

	private ControladorConfiguracion() {
		this.vista = new VistaConfiguracionPuerto();
		this.vista.setActionListener(this);
	}

    public static ControladorConfiguracion get(boolean mostrar){
        if( controladorConfiguracion == null ) {
			controladorConfiguracion = new ControladorConfiguracion();
		}

		if( mostrar )
			controladorConfiguracion.vista.mostrar();

		return controladorConfiguracion;
    }

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			String IP = "localhost";

	        ControladorInicio controladorInicio = ControladorInicio.get(true);
			Configuracion.escribirPuertoArchivo(vista.getPuerto());
			if (Configuracion.puertoValido()) {
				conexionReceptor = new ConexionReceptor(IP, vista.getPuerto());
				controladorInicio.setConexionReceptor(conexionReceptor);
				controladorInicio.setMiPuerto(vista.getPuerto());
			}	
				controladorInicio.verificarBoton();
				this.vista.esconder();
		} catch (IOException exception) {
			//TODO: Validar Puerto
		}catch (Exception exception){
			vista.lanzarVentanaEmergente("Error al ingresar Puerto");

		}
	}

}
