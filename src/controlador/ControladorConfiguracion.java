package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import vista.interfaces.IVistaConfiguracion;
import conexion.ConexionReceptor;
import configuracion.Configuracion;
import vista.vistas.VistaConfiguracionPuerto;


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

			ControladorInicio controladorInicio = ControladorInicio.get(false);
			Configuracion.escribirPuertoArchivo(vista.getPuerto());
			if (Configuracion.puertoValido()) {
				conexionReceptor = new ConexionReceptor(IP, vista.getPuerto());
				controladorInicio.setConexionReceptor(conexionReceptor);
				controladorInicio.setMiPuerto(vista.getPuerto());
			}	
				controladorInicio.verificarBoton();
				ControladorInicio.get(true);
				this.vista.esconder();
		} catch (IOException exception) {
			vista.lanzarVentanaEmergente("El puerto ingresado ya esta en uso");
		}catch (Exception exception){
			vista.lanzarVentanaEmergente("Error al ingresar Puerto");

		}
	}

}
