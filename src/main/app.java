package main;

import controlador.ControladorConfiguracion;
import controlador.ControladorInicio;

public class app extends Thread{

	public void run() {
		ControladorConfiguracion controlador = ControladorConfiguracion.get(true);
	}
}
