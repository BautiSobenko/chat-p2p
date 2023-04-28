package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Vista.IVista;
import Vista.IVistaConf;
import Vista.VistaSesion;
import conexion.ConexionReceptor;


public class ControladorConf implements ActionListener{
	
	private IVistaConf vista;
    ConexionReceptor conexionReceptor;
   
    public ControladorConf (IVistaConf vista){
        this.vista = vista;
        vista.setActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			conexionReceptor = new ConexionReceptor(vista.getIP(),vista.getPuerto());
	        IVista ventana = (IVista) new VistaSesion();
	        ControladorSesion controlador = new ControladorSesion(ventana,conexionReceptor);
	        conexionReceptor.setControlador(controlador);
	        conexionReceptor.start();
	        this.vista.cierra();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
