package main;

import controlador.ControladorConfiguracion;

public class app extends Thread{

	public void run() {
		ControladorConfiguracion controlador = ControladorConfiguracion.get(true);
	}
}
