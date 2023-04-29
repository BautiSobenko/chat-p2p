package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class VistaInicio extends JFrame implements IVistaInicio {

	private JPanel contentPane;
	private JTextField txtPuerto;
	private JButton btnConfiguracion;
	private JButton btnConectar;
	private JTextField txtIP;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaInicio frame = new VistaInicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaInicio() {
		setTitle("Inicio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IP: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(66, 43, 26, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Puerto:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(51, 108, 61, 14);
		contentPane.add(lblNewLabel_1);
		
		txtPuerto = new JTextField();
		txtPuerto.setColumns(10);
		txtPuerto.setBounds(609, 43, 265, 48);
		contentPane.add(txtPuerto);
		
		txtPuerto = new JTextField();
		txtPuerto.setToolTipText("");
		txtPuerto.setHorizontalAlignment(SwingConstants.CENTER);
		txtPuerto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtPuerto.setColumns(10);
		txtPuerto.setBounds(147, 96, 227, 40);
		contentPane.add(txtPuerto);
		
		btnConectar = new JButton("Conectar");
		btnConectar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnConectar.setBounds(293, 194, 124, 48);
		contentPane.add(btnConectar);
		
		btnConfiguracion = new JButton("Configuracion");
		btnConfiguracion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnConfiguracion.setBounds(51, 194, 124, 48);
		contentPane.add(btnConfiguracion);
		
		txtIP = new JTextField();
		txtIP.setText("localhost");
		txtIP.setHorizontalAlignment(SwingConstants.CENTER);
		txtIP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtIP.setColumns(10);
		txtIP.setBounds(147, 29, 227, 40);
		contentPane.add(txtIP);

		this.esconder();
	}

	@Override
	public void setActionListener(ActionListener controlador) {
		this.btnConfiguracion.addActionListener(controlador);
		this.btnConectar.addActionListener(controlador);

	}

	@Override
	public String getIP() {
		return this.txtIP.getText();
	}

	@Override
	public int getPuerto() {
		return Integer.parseInt(this.txtPuerto.getText());
	}


	@Override
	public void lanzarVentanaEmergente(String mensaje) {

	}

	@Override
	public void limpiarCampo() {
		this.txtIP.setText("");
		this.txtPuerto.setText("");
	}

	@Override
	public void mostrar() {
		this.setVisible(true);
	}

	@Override
	public void esconder() {
		this.setVisible(false);
	}
}
