package dao;

public class DaoTableContracts {
	
	public static class TableAdministrador {
		public final static String TABLE_ADMINISTRADOR = "ADMINISTRADORES";
		public final static String ID_ADMIN =  "ID_ADMIN";
		public final static String USUARIO = "USUARIO";
		public final static String PASSWORD =  "PASSWORD";
	}
	
	public static class TablePreguntas {
		public final static String TABLE_PREGUNTAS = "PREGUNTAS";
		public final static String ID_PREGUNTA =  "ID_P";
		public final static String ID_PREGUNTA_ABSOLUTE = TABLE_PREGUNTAS + "." + ID_PREGUNTA;
		public final static String PREGUNTA = "PREGUNTA";
		public final static String RESPUESTA_CORRECTA =  "RESPUESTA";
		
	}
	
	public static class TableJugadores {
		public final static String TABLE_JUGADORES = "JUGADORES";
		public final static String ID_JUGADOR =  "ID_J";
		public final static String ID_JUGADOR_ABSOLUTE = TABLE_JUGADORES + "." + ID_JUGADOR;
		public final static String NOMBRE = "NOMBRE";
		public final static String DNI =  "DNI";
		public final static String PASSWORD =  "PASSWORD";
		public final static String FECHA =  "FECHA";
		public final static String ACIERTOS =  "ACIERTOS";
		public final static String TIEMPO_RECORD =  "TIEMPO_RECORD";
	}
	
	public static class TableRespuestasIncorrectas {
		public final static String TABLE_RESPUESTAS_INCORRECTAS = "RESPUESTASINCORRECTAS";
		public final static String ID_RESPUESTA_INCORRECTA =  "ID_RI";
		public final static String RESPUESTA_INCORRECTA = "RESPUESTAINCORRECTA";
		public final static String ID_PREGUNTA_FK =  "PREGUNTA";
		public final static String ID_PREGUNTA_FK_ABSOLUTE = TABLE_RESPUESTAS_INCORRECTAS + "." + ID_PREGUNTA_FK;
	}
}
