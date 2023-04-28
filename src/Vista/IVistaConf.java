package Vista;

import java.awt.event.ActionListener;

public interface IVistaConf {

	public void setActionListener(ActionListener controlador);
	public String getIP();
	public int getPuerto();
	public void cierra();
}
