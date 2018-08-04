package view;

import controller.ControllerQuiz;
import model.Administrador;
import javax.swing.border.LineBorder;
import config.AppColorStyles;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModalLoginAdmin extends JDialog implements IGuiQuizApp {
	private JLabel lblUsuario;
	private JLabel lblPassword;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JTextArea txtUsuario;
	private JTextArea txtPassword;
	
	public ModalLoginAdmin() {
		inicializar();
	}

	@Override
	public void inicializar() {
		
		//Determina el tipo modal del componente JDialog
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		//Titulo de la ventana
		setTitle("Login Usuario");
	
		//Icono de la barra de titulo
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModalLoginAdmin.class.getResource("/images/QuizIconSmall.png")));
		
		//Background del ContentPane
		getContentPane().setBackground(Color.WHITE);
		
		//Tamaño del JDialog
		setBounds(0, 0, 585, 400);
		
		//Layout del JDialog
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//Separator
		separator_2 = new JSeparator();
		separator_2.setSize(new Dimension(0, 40));
		separator_2.setRequestFocusEnabled(false);
		separator_2.setPreferredSize(new Dimension(500, 40));
		separator_2.setForeground(Color.WHITE);
		separator_2.setBorder(null);
		separator_2.setBackground(Color.WHITE);
		getContentPane().add(separator_2);
		
		//Label Usuario
		lblUsuario = new JLabel("USUARIO");
		lblUsuario.setFont(new Font("Arial", Font.PLAIN, 20));
		getContentPane().add(lblUsuario);
		
		//Text Usuario
		txtUsuario = new JTextArea();
		txtUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
		txtUsuario.setBorder(new LineBorder(new Color(223, 86, 86), 3));
		txtUsuario.setPreferredSize(new Dimension(500, 40));
		getContentPane().add(txtUsuario);
		
		//Separator
		separator_1 = new JSeparator();
		separator_1.setSize(new Dimension(0, 40));
		separator_1.setRequestFocusEnabled(false);
		separator_1.setPreferredSize(new Dimension(500, 40));
		separator_1.setForeground(Color.WHITE);
		separator_1.setBorder(null);
		separator_1.setBackground(Color.WHITE);
		getContentPane().add(separator_1);
		
		
		//Label Contrasenya
		lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 20));
		getContentPane().add(lblPassword);
		
		//Text Contrasenya
		txtPassword = new JTextArea();
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPassword.setPreferredSize(new Dimension(500, 40));
		txtPassword.setBorder(new LineBorder(new Color(223, 86, 86), 3));
		getContentPane().add(txtPassword);
		
		//Separator
		separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBackground(Color.WHITE);
		separator.setRequestFocusEnabled(false);
		separator.setBorder(null);
		separator.setPreferredSize(new Dimension(500, 40));
		separator.setSize(new Dimension(0, 40));
		getContentPane().add(separator);
		
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
	
	public Administrador getDatos() {
		return new Administrador(txtUsuario.getText(), txtPassword.getText());
	}
	
	public void recycle() {
		txtUsuario.setText("");
		txtPassword.setText("");
	}
}
