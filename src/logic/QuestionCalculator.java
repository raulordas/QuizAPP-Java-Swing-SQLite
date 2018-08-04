package logic;

import model.Jugador;

public class QuestionCalculator {
	private Jugador jugador;
	private int aciertos;
	private long tiempo;
	
	public QuestionCalculator(Jugador jugador, int aciertos, long tiempo) {
		this.jugador = jugador;
		this.aciertos = aciertos;
		this.tiempo = tiempo;
	}
	
	public Jugador calcularResultados() {
		
		if (aciertos > jugador.getAciertos()) {
			return new Jugador(jugador.getId_j(), jugador.getNombre(), jugador.getDni(), jugador.getPassword() ,new java.sql.Date(System.currentTimeMillis()), aciertos, tiempo); 
		
		} else if (aciertos == jugador.getAciertos() && tiempo < jugador.getTiempo_Record()) {
			return new Jugador(jugador.getId_j(), jugador.getNombre(), jugador.getDni(), jugador.getPassword(),new java.sql.Date(System.currentTimeMillis()), aciertos, tiempo); 
		
		} else {
			return null;
		}
	}
}
