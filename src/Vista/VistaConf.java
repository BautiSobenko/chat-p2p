package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class VistaConf extends JFrame implements IVistaConf{

	private JPanel contentPane;
	private JTextField txtIP;
	private JTextField txtPuerto;
	private JButton btnContinuar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaConf frame = new VistaConf();
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
	public VistaConf() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 237);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtIP = new JTextField();
		txtIP.setBounds(313, 34, 140, 37);
		contentPane.add(txtIP);
		txtIP.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ingrese su Nro IP: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(22, 11, 172, 70);
		contentPane.add(lblNewLabel);
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnContinuar.setBounds(172, 163, 151, 29);
		contentPane.add(btnContinuar);
		
		JLabel lblNewLabel_1 = new JLabel("Ingrese su puerto: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(22, 82, 172, 70);
		contentPane.add(lblNewLabel_1);
		
		txtPuerto = new JTextField();
		txtPuerto.setColumns(10);
		txtPuerto.setBounds(313, 101, 140, 37);
		contentPane.add(txtPuerto);
		
		txtIP.setText("localhost");
		this.setVisible(true);
	}
	
    public void setActionListener(ActionListener controlador) {
        this.btnContinuar.addActionListener(controlador);
    }
    
	public String getIP() {
		return this.txtIP.getText();
	}

	public int getPuerto() {
		return Integer.parseInt(this.txtPuerto.getText());
	}

	@Override
	public void cierra() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}
