package main;
import controlador.ControladorConf;
import Vista.IVistaConf;
import Vista.VistaConf;

public class app extends Thread{

	public void run() {
		        IVistaConf ventana =  new VistaConf();
		        ControladorConf controlador = new ControladorConf(ventana);
		    }
}
