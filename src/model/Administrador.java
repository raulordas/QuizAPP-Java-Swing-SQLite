package model;

public class Administrador {
	private int id_admin;
	private String usuario;
	private String password;
	
	public Administrador(int id_admin, String usuario, String password) {
		this.id_admin = id_admin;
		this.usuario = usuario;
		this.password = password;
	}
	
	public Administrador(String usuario, String password) {
		this.usuario = usuario;
		this.password = password;
	}

	public int getId_admin() {
		return id_admin;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getPassword() {
		return password;
	}
}
