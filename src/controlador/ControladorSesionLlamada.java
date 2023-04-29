package controlador;

import Vista.IVistaSesionLlamada;
import Vista.VistaSesionLlamada;
import conexion.ConexionEnvio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorSesionLlamada implements ActionListener {

    private static ControladorSesionLlamada controladorSesionLlamada = null;

    private IVistaSesionLlamada vista;
    private ConexionEnvio conexionEnvio;
    private boolean primerEnvio;


    private ControladorSesionLlamada() {
        this.vista = new VistaSesionLlamada();
        this.vista.setActionListener(this);
        this.primerEnvio = false;
    }

    public static ControladorSesionLlamada get(boolean mostrar){
        if( controladorSesionLlamada == null ) {
            controladorSesionLlamada = new ControladorSesionLlamada();
        }

        if( mostrar )
            controladorSesionLlamada.vista.mostrar();

        return controladorSesionLlamada;
    }

    public void setConexionEnvio(ConexionEnvio conexionEnvio) {
        this.conexionEnvio = conexionEnvio;
    }

    public void muestraMensaje(String msg) {
        vista.agregarLineaChat(msg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();

        String ultMensaje;

        switch(comando){
            case("Enviar"):
                ultMensaje = vista.getMensaje();
                vista.limpiarCampo();
                this.conexionEnvio.envia(ultMensaje);
                this.muestraMensaje("Yo: " + ultMensaje);
            break;

            case("Desconectar"):
                /*

                try {
                    this.envio=0;
                    this.conexionEnvio.stopServer();
                    this.conexionReceptor.stopServer();
                    vista.lanzarVentanaEmergente("Conexion desconectada");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                 */
            break;
        }
    }



}
