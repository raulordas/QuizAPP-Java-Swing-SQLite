package controller;

import dao.*;
import view.*;
import javax.swing.*;
import model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import config.AppMessages;
import logic.QuestionCalculator;

public class ControllerQuiz implements ActionListener {
	private static final int NUM_PREGUNTAS = 10;
	private Administrador admin;
	private MainView mainView;
	private IntroView introView;
	private Jugador jugador;
	private DaoAdministrador daoAdmin;
	private ModalLoginAdmin modalLoginAdmin;
	private AgregarPreguntas addQuestionView;
	private GestionarPreguntas gestionarPreguntas;
	private ModificarPreguntas modificarPregunta;
	private RegistrarJugador registrarJugador;
	private ModalLoginJugador loginJugador;
	private DaoPreguntas daoPreguntas;
	private DaoJugador daoJugador;
	private JugarView jugarView;
	private HallOfFameView hallOfFame;
	
	
	public ControllerQuiz(MainView mainView, IntroView introView, 
			ModalLoginAdmin modalLoginAdmin, AgregarPreguntas addQuestionView, 
			GestionarPreguntas gestionarPreguntas, ModificarPreguntas modificarPreguntas,
			JugarView jugarView, RegistrarJugador registrarJugador,
			ModalLoginJugador loginJugador, HallOfFameView hallOfFame) {
		
		//Atributos Administrador y Jugador
		this.admin = null;
		this.jugador = null;
		
		//Vistas
		this.mainView = mainView;
		this.introView = introView;
		this.addQuestionView = addQuestionView;  
		this.gestionarPreguntas = gestionarPreguntas;
		this.registrarJugador = registrarJugador;
		this.modalLoginAdmin = modalLoginAdmin;
		this.loginJugador = loginJugador;
		this.modificarPregunta = modificarPreguntas;
		this.daoJugador = new DaoJugador();
		this.hallOfFame = hallOfFame;
		this.jugarView = jugarView;
		
		//Persistencias
		this.daoAdmin = new DaoAdministrador();
		daoPreguntas = new DaoPreguntas();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object action = event.getSource();
		
		//Comprueba si el evento procede de un componente JMenuItem
		if (action instanceof JMenuItem) {
				
				//Evento pulsar en el menu INICIO
				if (action.equals(mainView.getMenuInicio())) {
					mainView.setPanel(introView);
					introView.setUsuario(admin, jugador);
				}
				
				
				//Evento pulsar en el menu LOG OUT
				if (action.equals(mainView.getMenuLogOut())) {
					
					//Ventana de confirmación para realizar el log out
					int res = JOptionPane.showConfirmDialog(mainView, AppMessages.MSG_LOGOUT);
					
					//Si el cliente confirma el logout los atributos admin y jugador se convierten a null
					if (res == JOptionPane.YES_OPTION) {
						admin = null;
						jugador = null;
						mainView.esAdmin(0);
						mainView.esJugador(jugador);
						introView.setUsuario(admin, jugador);
						mainView.setPanel(introView);
					}
				}
				
				//Evento pulsar en el menu LOGIN ADMINISTRADOR
				if (action.equals(mainView.getMenuLoginAdminsitrador())) {
					modalLoginAdmin.hacerVisible();
				}
				
				//Evento pulsar en el menu LOGIN LOGIN JUGADOR
				if (action.equals(mainView.getMenuLoginJugador())) {
					loginJugador.hacerVisible();
				}
				
				//Evento pulsar en menu AGREGAR PREGUNTA
				if(action.equals(mainView.getMenuAgregarPregunta())) {
					mainView.setPanel(addQuestionView);
				}
				
				//Evento pulsar en el menu GESTIONAR PREGUNTAS
				if (action.equals(mainView.getMenuGestionarPreguntas())) {
					mainView.setPanel(gestionarPreguntas);
					gestionarPreguntas.cargarTabla(daoPreguntas.getAllPreguntas());
				}

				//Evento pulsar en el menú REGISTRAR JUGADOR
				if (action.equals(mainView.getMenuRegistrarJugador())) {
					registrarJugador.recycle();
					registrarJugador.hacerVisible();
				}
				
				//Evento pulsar en el menú JUGAR
				if (action.equals(mainView.getMenuJugar())) {
					
					if (daoPreguntas.getAllPreguntas().size() < 1) {
						JOptionPane.showMessageDialog(mainView, AppMessages.NOT_ENOUGH_INFO);
					} else {
						mainView.setPanel(jugarView);
						jugarView.enableDisable(false);
						jugarView.reset();
						jugarView.loadListPreguntas(daoPreguntas.getAllPreguntas());
					}
				}
				
				//Evento pulsar en ACERCA DE
				if (action.equals(mainView.getMenuAcercaDe())) {
					JOptionPane.showMessageDialog(mainView, AppMessages.DEVELOPER);
				}
				
				if(action.equals(mainView.getMenuHallOfFame())) {
					mainView.setPanel(hallOfFame);
					hallOfFame.cargarTabla(daoJugador.getAllPlayers());
				}
		}
		
		if (action instanceof JButton) {
			
			//Evento pulsar ACEPTAR en la ventana Modal LOGIN ADMINISTRADOR para comprobar si el usuario es correcto
			if(action.equals(modalLoginAdmin.getBtnAceptar())) {
				int res = daoAdmin.checkUser(modalLoginAdmin.getDatos());
				
				//Datos Incorrectos
				if (res == 0) {
					modalLoginAdmin.recycle();
					JOptionPane.showMessageDialog(modalLoginAdmin, AppMessages.WRONG_USER);
				
				//Datos Correctos
				} else {
					admin = modalLoginAdmin.getDatos();
					JOptionPane.showMessageDialog(modalLoginAdmin, AppMessages.VALID_USER);
					mainView.esAdmin(res);
					introView.setUsuario(admin, jugador);
					modalLoginAdmin.recycle();
					modalLoginAdmin.dispose();
				}
			}
			
			
			//Evento pulsar Aceptar para almacenar una nueva pregunta
			if (action.equals(addQuestionView.getBtnAgregarPregunta())) {
				
				//Actua INSERTANDO en la persistencia PREGUNTAS
				if (addQuestionView.getDatos() != null) {
					int res = daoPreguntas.insertPregunta(addQuestionView.getDatos());
					
					if (res == 1) {
						JOptionPane.showMessageDialog(addQuestionView, AppMessages.SUCCESS);
						addQuestionView.recycle();
					} else {
						JOptionPane.showMessageDialog(modificarPregunta, AppMessages.SQL_EXCEPTION);
					}
				}
			}
			
			//Evento pulsar en MODIFICAR una pregunta de la tabla
			if (action.equals(gestionarPreguntas.getBtnModificarPregunta())) {
				mainView.setPanel(modificarPregunta);
				modificarPregunta.cargarPregunta(gestionarPreguntas.getDatos());
			}
			
			//Evento Aceptar MODIFICAR un registro de pregunta
			if(action.equals(modificarPregunta.getBtnAgregarPregunta())) {
				
				if (modificarPregunta.getDatos() != null) {
					
					int res = daoPreguntas.updatePregunta(modificarPregunta.getDatos());
					
					//Error
					if (res == 0) {
						JOptionPane.showMessageDialog(modificarPregunta, AppMessages.SQL_EXCEPTION);
					
					//Actualización satisfactoria
					} else {
						JOptionPane.showMessageDialog(modalLoginAdmin, AppMessages.SUCCESS);
						mainView.setPanel(gestionarPreguntas);
						gestionarPreguntas.cargarTabla(daoPreguntas.getAllPreguntas());
					}
				}
			}
			
			//Evento ELIMINAR una pregunta
			if (action.equals(gestionarPreguntas.getBtnEliminar())) {
				
				int opcion = JOptionPane.showConfirmDialog(gestionarPreguntas, AppMessages.PREVENT, "Confirm", JOptionPane.YES_NO_OPTION);
				
				if (opcion == JOptionPane.YES_OPTION) {
					int res = daoPreguntas.deletePregunta(gestionarPreguntas.getDatos());
					
					if (res == 0) {
						JOptionPane.showMessageDialog(modificarPregunta, AppMessages.SQL_EXCEPTION);
					} else {
						JOptionPane.showMessageDialog(modalLoginAdmin, AppMessages.SUCCESS);
						gestionarPreguntas.cargarTabla(daoPreguntas.getAllPreguntas());
					}
				}
			}
			
			//Evento pulsar SIGUIENTE PREGUNTA durante el juego
			if (action.equals(jugarView.getBtnNext())) {
				
				//Condicion mientras dura el juego
				if (jugarView.getCont() < NUM_PREGUNTAS) {
					
					jugarView.clearRandomAnswers();
					jugarView.checkAciertos(jugarView.getLoadedPregunta());
					jugarView.setCont(jugarView.getCont() + 1);
					jugarView.resetModel();
					jugarView.cargarPregunta(jugarView.getListPreguntas().get(jugarView.randomQuestion(jugarView.getListPreguntas())));
				
				//Operaciones al finalizar el juego
				} else {
					Jugador revalidateJugador = daoJugador.revalidatePlayer(jugador);
					QuestionCalculator calcu = new QuestionCalculator(revalidateJugador, jugarView.getAciertos(), jugarView.finalizarTimer());
					JOptionPane.showMessageDialog(jugarView, "Has Obtenido " + jugarView.getAciertos() + " aciertos.");
					Jugador jugadorAux = calcu.calcularResultados();
					
					if (jugadorAux != null) {
						
						int res = daoJugador.updateJugador(jugadorAux);
						
						if (res == 0 ) {
							JOptionPane.showMessageDialog(jugarView, AppMessages.SQL_EXCEPTION);
						} else {
							JOptionPane.showMessageDialog(jugarView, AppMessages.NEW_RECORD);
						}
						
						
						mainView.setPanel(introView);
						jugarView.reset();
					
					} else {
						JOptionPane.showMessageDialog(jugarView, AppMessages.NOT_RECORD);
						mainView.setPanel(introView);
						jugarView.reset();
					}
				}
			}
			
			//Evento pulsar aceptar para REGISTRAR UN JUGADOR
			if (action.equals(registrarJugador.getBtnAceptar())) {
				
				if (registrarJugador.getDatos() != null) {
					
					int res = daoJugador.insertPlayer(registrarJugador.getDatos());
					
					if (res == 1) {
						JOptionPane.showMessageDialog(registrarJugador, AppMessages.SUCCESS);
						registrarJugador.recycle();
						registrarJugador.dispose();
					
					} else {
						JOptionPane.showMessageDialog(registrarJugador, AppMessages.SQL_EXCEPTION);
					}
					
				} else {
					JOptionPane.showMessageDialog(registrarJugador, AppMessages.PREVENT);
				}	
			}
			
			//Evento pulsar ACEPTAR en la ventana Modal LOGIN JUGADOR para comprobar si el usuario es correcto
			if(action.equals(loginJugador.getBtnAceptar())) {
				Jugador jugadorAux =  daoJugador.checkPlayer(loginJugador.getDatos());
				
				//Datos Incorrectos
				if (jugadorAux == null) {
					loginJugador.recycle();
					JOptionPane.showMessageDialog(loginJugador, AppMessages.WRONG_USER);
				
				//Datos Correctos
				} else {
					jugador = jugadorAux;
					System.out.println(jugador.getNombre());
					JOptionPane.showMessageDialog(loginJugador, AppMessages.VALID_USER);
					mainView.esJugador(jugador);
					introView.setUsuario(admin, jugador);
					loginJugador.recycle();
					loginJugador.dispose();
				}
			}
		}
	}
}
