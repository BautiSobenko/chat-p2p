package Vista;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;

public class VistaSesion extends JFrame implements IVista{

	private JPanel contentPane;
	private JTextField txtMensaje;
	private JTextField txtIp;
	private JTextField txtPuerto;
	private JButton btnEnviar;
	private JButton btnDesconectarse;
	private JTextArea txtArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaSesion frame = new VistaSesion();
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
	public VistaSesion() {
		setTitle("Sesion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 913, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtMensaje = new JTextField();
		txtMensaje.setBounds(36, 378, 836, 63);
		contentPane.add(txtMensaje);
		txtMensaje.setColumns(10);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEnviar.setBounds(36, 452, 263, 31);
		contentPane.add(btnEnviar);
		
		btnDesconectarse = new JButton("Desconectarse");
		btnDesconectarse.setBounds(615, 452, 251, 38);
		contentPane.add(btnDesconectarse);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setForeground(new Color(255, 255, 255));
		panel.setBounds(36, 126, 836, 241);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtArea = new JTextArea();
		txtArea.setBounds(0, 0, 836, 241);
		panel.add(txtArea);
		
		txtIp = new JTextField();
		txtIp.setBounds(86, 37, 281, 55);
		contentPane.add(txtIp);
		txtIp.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("IP: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(58, 55, 49, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Puerto:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(469, 57, 61, 14);
		contentPane.add(lblNewLabel_1);
		
		txtPuerto = new JTextField();
		txtPuerto.setBounds(601, 37, 265, 48);
		contentPane.add(txtPuerto);
		txtPuerto.setColumns(10);
		
		txtIp.setText("localhost");
		this.setVisible(true);
	}
	
    public void setActionListener(ActionListener controlador) {
        this.btnEnviar.addActionListener(controlador);
        this.btnDesconectarse.addActionListener(controlador);
    }

	@Override
	public void ejecutar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getIP() {
		return this.txtIp.getText();
	}

	@Override
	public int getPuerto() {
		return Integer.parseInt(this.txtPuerto.getText());
	}

	@Override
	public String getMensaje() {
		return this.txtMensaje.getText();
	}

	@Override
	public void lanzarVentanaEmergente(String mensaje) {
		// TODO Auto-generated method stub
		JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, mensaje);
	}

	@Override
	public void borrarMensaje() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregarLineaChat(String mensaje) {
		// TODO Auto-generated method stub
		this.txtArea.append(mensaje + "\n");
	}

	@Override
	public void limpiarCampo() {
		// TODO Auto-generated method stub
		txtMensaje.setText("");
	}
}
