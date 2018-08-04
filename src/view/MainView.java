package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import config.AppColorStyles;
import java.awt.*;
import controller.ControllerQuiz;
import model.Jugador;

public class MainView extends JFrame implements IGuiQuizApp {
	private JMenuItem menuInicio;
	private JMenuItem menuJugar;
	private JMenuItem menuHallOfFame;
	private JMenuItem menuAcercaDe;
	private JSeparator separator;
	private JSeparator separator_2;
	private JSeparator separator_3;
	private JSeparator separator_4;
	private JMenu menuPreguntas;
	private JMenuItem menuAgregarPregunta;
	private JMenuItem menuGestionarPreguntas;
	private JMenu menuLogin;
	private JMenuItem menuLoginJugador;
	private JMenuItem menuRegistrarJugador;
	private JSeparator separator_1;
	private JMenuBar menuBar;
	private JSeparator separator_5;
	private JScrollPane scrollPane;
	private JMenuItem menuLogOut;
	private JMenuItem menuLoginAdministrador;
	
	public MainView() {
		inicializar();
	}
	
	@Override
	public void inicializar() {
		
		//Adapta el estilo de ventanas al proporcionado por el cliente
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
		
		//Background pane
		setBackground(new Color(255, 255, 255));
		
		//Impide el ajuste de tamaño del JFrame
		setResizable(false);

		//Tamaño del JFrame
		setBounds(0, 0, 800, 600);
		
		//Color del background JFrame
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		//ScrollPane viewport de los JPane
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(0, 0, 784, 521);
		getContentPane().add(scrollPane);
		
		//Centra el JFrame en el viewport
		setLocationRelativeTo(null);
		
		//Define el tipo de salida de aplicación por defecto
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//Cambia el color del outline de los componentes PopupMenu
		UIManager.put("PopupMenu.border", new LineBorder(Color.WHITE));
		
		//Componentes de la barra de menu
		menuBarComponents();
		
		//Estado inicial de la barra de menu
		setEstadoInicialMenuBar();
	}

	private void menuBarComponents() {
		//JMenuBar Barra del Menu de Inicio
		menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBorder(null);
		menuBar.setBackground(Color.WHITE);
		menuBar.setPreferredSize(new Dimension(0, 40));
		menuBar.setMinimumSize(new Dimension(0, 20));
		menuBar.setMaximumSize(new Dimension(0, 20));
		setJMenuBar(menuBar);
		
		//JMenuItem Inicio
		menuInicio = new JMenuItem("     INICIO");
		menuInicio.setPreferredSize(new Dimension(140, 22));
		menuInicio.setFont(new Font("Arial", Font.BOLD, 14));
		menuInicio.setForeground(Color.WHITE);
		menuInicio.setBackground(new Color(AppColorStyles.APP_MAIN_COLOR[0], AppColorStyles.APP_MAIN_COLOR[1] ,AppColorStyles.APP_MAIN_COLOR[2]));
		menuBar.add(menuInicio);
		
		//Separator
		separator = new JSeparator();
		separator.setPreferredSize(new Dimension(20, 2));
		menuBar.add(separator);
		
		//JMenuItem JUGAR
		menuJugar = new JMenuItem("     JUGAR");
		menuJugar.setFont(new Font("Arial", Font.BOLD, 14));
		menuJugar.setPreferredSize(new Dimension(140, 22));
		menuJugar.setForeground(Color.WHITE);
		menuJugar.setBackground(new Color(AppColorStyles.APP_MAIN_COLOR[0], AppColorStyles.APP_MAIN_COLOR[1] ,AppColorStyles.APP_MAIN_COLOR[2]));
		menuBar.add(menuJugar);
		
		//Separator
		separator_2 = new JSeparator();
		separator_2.setPreferredSize(new Dimension(20, 2));
		menuBar.add(separator_2);
		
		//JMenuItem HALL OF FAME
		menuHallOfFame = new JMenuItem(" HALL OF FAME");
		menuHallOfFame.setPreferredSize(new Dimension(170, 22));
		menuHallOfFame.setFont(new Font("Arial", Font.BOLD, 14));
		menuHallOfFame.setHorizontalTextPosition(SwingConstants.CENTER);
		menuHallOfFame.setHorizontalAlignment(SwingConstants.CENTER);
		menuHallOfFame.setForeground(Color.WHITE);
		menuHallOfFame.setBackground(new Color(AppColorStyles.APP_MAIN_COLOR[0], AppColorStyles.APP_MAIN_COLOR[1] ,AppColorStyles.APP_MAIN_COLOR[2]));
		menuBar.add(menuHallOfFame);
		
		//Separator
		separator_3 = new JSeparator();
		separator_3.setRequestFocusEnabled(false);
		separator_3.setForeground(new Color(255, 255, 255));
		separator_3.setBorder(new LineBorder(new Color(255, 255, 255), 3));
		separator_3.setPreferredSize(new Dimension(20, 2));
		separator_3.setBackground(Color.WHITE);
		menuBar.add(separator_3);
		
		//JMenuItem ACERCA DE
		menuAcercaDe = new JMenuItem(" ACERCA DE");
		menuAcercaDe.setBorderPainted(true);
		menuAcercaDe.setBorder(new LineBorder(new Color(255, 255, 255)));
		menuAcercaDe.setFont(new Font("Arial", Font.BOLD, 14));
		menuAcercaDe.setPreferredSize(new Dimension(140, 22));
		menuAcercaDe.setForeground(Color.WHITE);
		menuAcercaDe.setBackground(new Color(AppColorStyles.APP_MAIN_COLOR[0], AppColorStyles.APP_MAIN_COLOR[1] ,AppColorStyles.APP_MAIN_COLOR[2]));
		menuBar.add(menuAcercaDe);
		
		//Separator
		separator_4 = new JSeparator();
		separator_4.setPreferredSize(new Dimension(50, 30));
		separator_4.setBackground(Color.WHITE);
		menuBar.add(separator_4);
		
		//JMenuItem PREGUNTAS
		menuPreguntas = new JMenu("PREGUNTAS");
		menuPreguntas.setFocusable(false);
		menuPreguntas.setBorder(null);
		menuPreguntas.setPreferredSize(new Dimension(140, 22));
		menuPreguntas.setFont(new Font("Arial", Font.BOLD, 10));
		menuPreguntas.setForeground(new Color(AppColorStyles.APP_MAIN_COLOR[0], AppColorStyles.APP_MAIN_COLOR[1] ,AppColorStyles.APP_MAIN_COLOR[2]));
		menuBar.add(menuPreguntas);
		
		//JMenuItem AGREGAR PREGUNTA
		menuAgregarPregunta = new JMenuItem("AGREGAR PREGUNTAS");
		menuAgregarPregunta.setBorder(null);
		menuAgregarPregunta.setBorderPainted(true);
		menuAgregarPregunta.setPreferredSize(new Dimension(163, 35));
		menuAgregarPregunta.setForeground(Color.WHITE);
		menuAgregarPregunta.setBackground(new Color(AppColorStyles.APP_MAIN_COLOR[0], AppColorStyles.APP_MAIN_COLOR[1] ,AppColorStyles.APP_MAIN_COLOR[2]));
		menuPreguntas.add(menuAgregarPregunta);
		
		//Separator
		separator_5 = new JSeparator();
		separator_5.setPreferredSize(new Dimension(0, 5));
		menuPreguntas.add(separator_5);
		
		//JMenuItem GESTIONAR PREGUNTAS
		menuGestionarPreguntas = new JMenuItem("GESTIONAR PREGUNTAS");
		menuGestionarPreguntas.setPreferredSize(new Dimension(175, 35));
		menuGestionarPreguntas.setForeground(Color.WHITE);
		menuGestionarPreguntas.setBackground(new Color(AppColorStyles.APP_MAIN_COLOR[0], AppColorStyles.APP_MAIN_COLOR[1] ,AppColorStyles.APP_MAIN_COLOR[2]));
		menuPreguntas.add(menuGestionarPreguntas);
		
		//JMenuItem LOGIN
		menuLogin = new JMenu("LOGIN");
		menuLogin.setFocusable(false);
		menuLogin.setPreferredSize(new Dimension(100, 22));
		menuLogin.setFont(new Font("Arial", Font.BOLD, 10));
		menuLogin.setForeground(new Color(AppColorStyles.APP_MAIN_COLOR[0], AppColorStyles.APP_MAIN_COLOR[1] ,AppColorStyles.APP_MAIN_COLOR[2]));
		menuBar.add(menuLogin);
		
		//JMenuItem HACER LOGIN
		menuLoginJugador = new JMenuItem("LOGIN JUGADOR");
		menuLoginJugador.setPreferredSize(new Dimension(79, 35));
		menuLoginJugador.setForeground(Color.WHITE);
		menuLoginJugador.setBackground(new Color(AppColorStyles.APP_MAIN_COLOR[0], AppColorStyles.APP_MAIN_COLOR[1] ,AppColorStyles.APP_MAIN_COLOR[2]));
		menuLogin.add(menuLoginJugador);
		
		//JMenuItem LOGIN ADMINISTRADOR
		menuLoginAdministrador = new JMenuItem("LOGIN ADMINISTRADOR");
		menuLoginAdministrador.setPreferredSize(new Dimension(79, 35));
		menuLoginAdministrador.setForeground(Color.WHITE);
		menuLoginAdministrador.setBackground(new Color(AppColorStyles.APP_MAIN_COLOR[0], AppColorStyles.APP_MAIN_COLOR[1] ,AppColorStyles.APP_MAIN_COLOR[2]));
		menuLogin.add(menuLoginAdministrador);
		
		//JMenuItem REGISTRAR
		menuRegistrarJugador = new JMenuItem("REGISTRAR JUGADOR");
		menuRegistrarJugador.setPreferredSize(new Dimension(157, 35));
		menuRegistrarJugador.setForeground(Color.WHITE);
		menuRegistrarJugador.setBackground(new Color(AppColorStyles.APP_MAIN_COLOR[0], AppColorStyles.APP_MAIN_COLOR[1] ,AppColorStyles.APP_MAIN_COLOR[2]));
		menuLogin.add(menuRegistrarJugador);
		
		//JMenuItem LOG OUT
		menuLogOut = new JMenuItem("LOG OUT");
		menuLogOut.setPreferredSize(new Dimension(79, 35));
		menuLogOut.setForeground(Color.WHITE);
		menuLogOut.setBackground(new Color(AppColorStyles.APP_MAIN_COLOR[0], AppColorStyles.APP_MAIN_COLOR[1] ,AppColorStyles.APP_MAIN_COLOR[2]));
		menuLogin.add(menuLogOut);
		
		//Separator
		separator_1 = new JSeparator();
		separator_1.setPreferredSize(new Dimension(150, 2));
		menuBar.add(separator_1);
	}
	
	public void hacerVisible() {
		setVisible(true);
	}
	
	@Override
	public void setControlador(ControllerQuiz control) {
		
		//Menu Inicio
		menuInicio.addActionListener(control);
		
		//Menu Login
		menuLogOut.addActionListener(control);
		menuLoginAdministrador.addActionListener(control);
		menuRegistrarJugador.addActionListener(control);
		menuLoginJugador.addActionListener(control);
		
		//Menu Preguntas
		menuAgregarPregunta.addActionListener(control);
		menuGestionarPreguntas.addActionListener(control);
		
		//Menu Hall Of fame
		menuHallOfFame.addActionListener(control);
		
		//Menu Jugar
		menuJugar.addActionListener(control);
		
		//Menu AcercaDe
		menuAcercaDe.addActionListener(control);
		
	}
	
	public void setPanel(JPanel panel) {
		scrollPane.setViewportView(panel);
	}
	
	private void setEstadoInicialMenuBar() {
		menuJugar.setEnabled(false);
		menuPreguntas.setEnabled(false);
	}
	
	public JMenuItem getMenuInicio() {
		return menuInicio;
	}
	
	//Método que habilita o deshabilita menus si el usuario es administrador
	public void esAdmin(int resultado) {
		if (resultado == 0) {
			menuPreguntas.setEnabled(false);
		} else {
			menuPreguntas.setEnabled(true);
			
		}
	}
	
	//Metodo que habilita o deshabilita menus si el jugador ha hecho login
	public void esJugador(Jugador jugador) {
		
		if (jugador == null) {
			menuJugar.setEnabled(false);
		} else {
			menuJugar.setEnabled(true);
		}
	}
	
	//Método que devuelve el estado de los menus al estado inicial
	public void logOut() {
		setEstadoInicialMenuBar();
	}

	public JMenuItem getMenuLogOut() {
		return menuLogOut;
	}

	public JMenuItem getMenuLoginAdminsitrador() {
		return menuLoginAdministrador;
	}

	public JMenuItem getMenuAgregarPregunta() {
		return menuAgregarPregunta;
	}

	public JMenuItem getMenuGestionarPreguntas() {
		return menuGestionarPreguntas;
	}

	public JMenuItem getMenuHallOfFame() {
		return menuHallOfFame;
	}

	public JMenuItem getMenuRegistrarJugador() {
		return menuRegistrarJugador;
	}

	public JMenuItem getMenuJugar() {
		return menuJugar;
	}

	public JMenuItem getMenuLoginJugador() {
		return menuLoginJugador;
	}

	public JMenuItem getMenuAcercaDe() {
		return menuAcercaDe;
	}
}
