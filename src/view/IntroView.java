package view;

import javax.swing.JPanel;
import controller.ControllerQuiz;
import model.Administrador;
import model.Jugador;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class IntroView extends JPanel implements IGuiQuizApp {
	static final String USUARIO_NO_IDENTIFICADO = "El usuario no está identificado";
	static final int[] APP_MAIN_COLOR = {223, 86, 86};
	private JLabel lblUsuario;
	
	public IntroView() {

		inicializar();
	}
	
	@Override
	public void inicializar() {
		setBorder(null);
		setBackground(Color.WHITE);
		setBounds(0, 0, 784, 521);
		setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(IntroView.class.getResource("/images/QuizIconBig.png")));
		lblLogo.setBounds(262, 11, 246, 292);
		add(lblLogo);
		
		JLabel lblBienvenida = new JLabel("<html><body style=text-align:'center'>Bievenido a Quiz.<br> El juego consiste en responder 10 preguntas lo m\u00E1s r\u00E1pido posible. Para jugar necesitas registrarte como usuario. El administrador podr\u00E1 adem\u00E1s agregar preguntas al juego.</body></html>");
		lblBienvenida.setFont(new Font("Arial", Font.PLAIN, 14));
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenida.setBounds(83, 314, 618, 77);
		add(lblBienvenida);
		
		lblUsuario = new JLabel("");
		lblUsuario.setFont(new Font("Arial", Font.PLAIN, 10));
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setOpaque(true);
		lblUsuario.setBackground(new Color(APP_MAIN_COLOR[0], APP_MAIN_COLOR[1] ,APP_MAIN_COLOR[2]));
		lblUsuario.setBounds(10, 482, 536, 28);
		add(lblUsuario);

	}

	@Override
	public void setControlador(ControllerQuiz control) {
		// TODO Auto-generated method stub

	}

	public void setUsuario(Administrador admin, Jugador jugador) {
		
		if (admin == null && jugador == null) {
			lblUsuario.setText(USUARIO_NO_IDENTIFICADO);
			
		} else if (admin != null && jugador == null) {
			lblUsuario.setText("Loggeado como admin: " + admin.getUsuario());
		
		} else if (admin == null && jugador != null) {
			lblUsuario.setText("Loggeado como jugador: " + jugador.getNombre());
		
		} else {
			lblUsuario.setText("Loggeado como admin: " + admin.getUsuario() + " y loggeado como jugador: " + jugador.getNombre());
		}
	}
}
