package app;

import java.awt.EventQueue;
import controller.ControllerQuiz;
import view.*;

public class QuizApp {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				//Declara e inicializa los componentes de la vista
				ModalLoginAdmin modalLoginAdmin = new ModalLoginAdmin();
				IntroView introPanel = new IntroView();
				MainView mainView = new MainView();
				AgregarPreguntas addQuestionView = new AgregarPreguntas();
				GestionarPreguntas gestionarPreguntas = new GestionarPreguntas();
				ModificarPreguntas modificarPreguntas = new ModificarPreguntas();
				JugarView jugarView = new JugarView();
				RegistrarJugador registrarJugador = new RegistrarJugador();
				ModalLoginJugador loginJugador = new ModalLoginJugador();
				HallOfFameView hallOfFame = new HallOfFameView();
				
				//Declara e inicializa el controlador de la aplicación
				ControllerQuiz control = new ControllerQuiz(mainView,introPanel, 
						modalLoginAdmin, addQuestionView, gestionarPreguntas, 
						modificarPreguntas, jugarView, registrarJugador, 
						loginJugador, hallOfFame);
				
				//Asigna el controlador a los componentes de la vista
				mainView.setControlador(control);
				modalLoginAdmin.setControlador(control);
				introPanel.setControlador(control);
				addQuestionView.setControlador(control);
				gestionarPreguntas.setControlador(control);
				modificarPreguntas.setControlador(control);
				jugarView.setControlador(control);
				registrarJugador.setControlador(control);
				loginJugador.setControlador(control);
				hallOfFame.setControlador(control);
				
				//Da salida al hilo principal visual de la aplicación
				mainView.setPanel(introPanel);
				introPanel.setUsuario(null, null);
				mainView.hacerVisible();
			}
		});
	}
}