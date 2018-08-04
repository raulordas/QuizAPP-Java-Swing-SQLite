package view;

import controller.ControllerQuiz;
import logic.PasswordEncDec;
import model.Jugador;
import javax.swing.border.LineBorder;
import config.AppColorStyles;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrarJugador extends JDialog implements IGuiQuizApp {
	private JLabel lblNombre;
	private JLabel lblDni;
	private JSeparator separator;
	private JSeparator separator_1;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JTextArea txtNombre;
	private JTextArea txtDni;
	private JLabel lblPassword;
	private JTextArea txtPassword;
	private JSeparator separator_3;
	private JSeparator separator_2;
	
	public RegistrarJugador() {
		inicializar();
	}

	@Override
	public void inicializar() {
		
		//Determina el tipo modal del componente JDialog
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		//Titulo de la ventana
		setTitle("REGISTRAR JUGADOR");
	
		//Icono de la barra de titulo
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrarJugador.class.getResource("/images/QuizIconSmall.png")));
		
		//Background del ContentPane
		getContentPane().setBackground(Color.WHITE);
		
		//Tamaño del JDialog
		setBounds(0, 0, 585, 400);
		
		//Layout del JDialog
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//Separator
		separator_3 = new JSeparator();
		separator_3.setSize(new Dimension(0, 40));
		separator_3.setRequestFocusEnabled(false);
		separator_3.setPreferredSize(new Dimension(500, 10));
		separator_3.setForeground(Color.WHITE);
		separator_3.setBorder(null);
		separator_3.setBackground(Color.WHITE);
		getContentPane().add(separator_3);
		
		//Label Jugador
		lblNombre = new JLabel("NOMBRE DE USUARIO");
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 15));
		getContentPane().add(lblNombre);
		
		//Text Usuario
		txtNombre = new JTextArea();
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNombre.setBorder(new LineBorder(new Color(223, 86, 86), 3));
		txtNombre.setPreferredSize(new Dimension(500, 40));
		getContentPane().add(txtNombre);
		
		//Separator
		separator_1 = new JSeparator();
		separator_1.setSize(new Dimension(0, 40));
		separator_1.setRequestFocusEnabled(false);
		separator_1.setPreferredSize(new Dimension(500, 20));
		separator_1.setForeground(Color.WHITE);
		separator_1.setBorder(null);
		separator_1.setBackground(Color.WHITE);
		getContentPane().add(separator_1);
		
		
		//Label DNI
		lblDni = new JLabel("DOCUMENTO NACIONAL IDENTIDAD");
		lblDni.setFont(new Font("Arial", Font.PLAIN, 15));
		getContentPane().add(lblDni);
		
		//Text DNI
		txtDni = new JTextArea();
		txtDni.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDni.setPreferredSize(new Dimension(500, 40));
		txtDni.setBorder(new LineBorder(new Color(223, 86, 86), 3));
		getContentPane().add(txtDni);
		
		//Separator
		separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBackground(Color.WHITE);
		separator.setRequestFocusEnabled(false);
		separator.setBorder(null);
		separator.setPreferredSize(new Dimension(500, 20));
		separator.setSize(new Dimension(0, 40));
		getContentPane().add(separator);
		
		//Label Password
		lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 15));
		getContentPane().add(lblPassword);
		
		//TextArea Password
		txtPassword = new JTextArea();
		txtPassword.setPreferredSize(new Dimension(500, 40));
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPassword.setBorder(new LineBorder(new Color(223, 86, 86), 3));
		getContentPane().add(txtPassword);
		
		//Separator
		separator_2 = new JSeparator();
		separator_2.setSize(new Dimension(0, 40));
		separator_2.setRequestFocusEnabled(false);
		separator_2.setPreferredSize(new Dimension(500, 20));
		separator_2.setForeground(Color.WHITE);
		separator_2.setBorder(null);
		separator_2.setBackground(Color.WHITE);
		getContentPane().add(separator_2);
		
		//Button Aceptar
		btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("Arial", Font.PLAIN, 13));
		btnAceptar.setBackground(new Color(AppColorStyles.APP_MAIN_COLOR[0],AppColorStyles.APP_MAIN_COLOR[1], AppColorStyles.APP_MAIN_COLOR[2]));
		btnAceptar.setIconTextGap(40);
		btnAceptar.setPreferredSize(new Dimension(150, 50));
		getContentPane().add(btnAceptar);
		
		
		//Button Cancelar
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 13));
		btnCancelar.setBackground(new Color(AppColorStyles.APP_MAIN_COLOR[0],AppColorStyles.APP_MAIN_COLOR[1], AppColorStyles.APP_MAIN_COLOR[2]));
		btnCancelar.setPreferredSize(new Dimension(150, 50));
		getContentPane().add(btnCancelar);
		
		//Centra el JDialog en el viewport del cliente
		setLocationRelativeTo(null);
		
	}

	@Override
	public void setControlador(ControllerQuiz control) {
		btnAceptar.addActionListener(control);
		
	}
	
	public void hacerVisible() {
		setVisible(true);
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}
	
	public Jugador getDatos() {
		if (txtNombre.getText().isEmpty() || txtDni.getText().isEmpty() || txtPassword.getText().isEmpty()) {
			return null;
		} else {
			return new Jugador(txtNombre.getText(),txtDni.getText(), new PasswordEncDec().EncryptPassword(txtPassword.getText()));	
		}
	}
	
	public void recycle() {
		txtNombre.setText("");
		txtDni.setText("");
		txtPassword.setText("");
	}
}
