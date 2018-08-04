package model;

import java.util.ArrayList;

public class Pregunta {
	private int id_p;
	private String pregunta;
	private String respuestaCorrecta;
	private ArrayList<RespuestaIncorrecta> listRespuestaIncorrecta;
	
	public Pregunta(int id_p, String pregunta, String respuesta, ArrayList<RespuestaIncorrecta> listRespuestaIncorrecta) {
		this.id_p = id_p;
		this.pregunta = pregunta;
		this.respuestaCorrecta = respuesta;
		this.listRespuestaIncorrecta = listRespuestaIncorrecta;
	}
	
	public Pregunta(String pregunta, String respuesta, ArrayList<RespuestaIncorrecta> listRespuestaIncorrecta) {
		this.pregunta = pregunta;
		this.respuestaCorrecta = respuesta;
		this.listRespuestaIncorrecta = listRespuestaIncorrecta;
	}
	
	public Pregunta(int id_p, String pregunta, String respuesta) {
		this.id_p = id_p;
		this.pregunta = pregunta;
		this.respuestaCorrecta = respuesta;
	}

	public int getId_p() {
		return id_p;
	}

	public String getPregunta() {
		return pregunta;
	}

	public String getRespuestaCorrecta() {
		return respuestaCorrecta;
	}

	public ArrayList<RespuestaIncorrecta> getListRespuestaIncorrecta() {
		return listRespuestaIncorrecta;
	}

	public void setListRespuestaIncorrecta(ArrayList<RespuestaIncorrecta> listRespuestaIncorrecta) {
		this.listRespuestaIncorrecta = listRespuestaIncorrecta;
	}
}


