package model;

public class RespuestaIncorrecta {
	private int id_r;
	private String respuestaIncorrecta;
	
	public RespuestaIncorrecta(int id_r, String respuestaIncorrecta) {
		this.id_r = id_r;
		this.respuestaIncorrecta = respuestaIncorrecta;
	}
	
	public RespuestaIncorrecta(String respuestaIncorrecta) {
		this.respuestaIncorrecta = respuestaIncorrecta;
	}

	public int getId_r() {
		return id_r;
	}

	public String getRespuestaIncorrecta() {
		return respuestaIncorrecta;
	}
	
	
}
