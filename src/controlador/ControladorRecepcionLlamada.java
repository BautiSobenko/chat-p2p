package controlador;

import Vista.IVistaRecepcionLlamada;
import Vista.VistaRecepcionLlamada;
import conexion.HiloEscucha;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class ControladorRecepcionLlamada implements ActionListener {

    private static ControladorRecepcionLlamada controladorRecepcionLlamada = null;

    private IVistaRecepcionLlamada vista;
    private static Socket socket;
    private boolean conexionAceptada;
    private ControladorInicio controladorInicio;

    private ControladorRecepcionLlamada() {
        this.vista = new VistaRecepcionLlamada();
        this.vista.setActionListener(this);
        this.conexionAceptada = false;
    }

    public static ControladorRecepcionLlamada get(boolean mostrar){
        if( controladorRecepcionLlamada == null ) {
            controladorRecepcionLlamada = new ControladorRecepcionLlamada();
        }


        if( mostrar ){
            controladorRecepcionLlamada.vista.setLabelPuerto(String.valueOf(socket.getPort()));
            controladorRecepcionLlamada.vista.mostrar();
        }

        return controladorRecepcionLlamada;
    }

    public void setSocket( Socket socket ){
        ControladorRecepcionLlamada.socket = socket;
    }

    public HiloEscucha creaHiloEscucha(){
        return new HiloEscucha(socket, ControladorSesionLlamada.get(false));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();

        switch(comando){

            case("Aceptar"):
                this.conexionAceptada = true;
                new HiloEscucha(socket, ControladorSesionLlamada.get(false)).start();
                controladorInicio.esconderVista();
                ControladorSesionLlamada.get(true);

                this.vista.esconder();
                ControladorInicio.get(false);
            break;

            case("Rechazar"):
                conexionAceptada = false;
                this.vista.esconder();
            break;
        }
    }

    public boolean isConexionAceptada() {
        return conexionAceptada;
    }
    
    public void setControladorInicio(ControladorInicio controlador) {
    	this.controladorInicio=controlador;
    }
}
