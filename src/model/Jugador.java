package model;

import java.sql.Date;

public class Jugador {
	private int id_j;
	private String nombre;
	private String dni;
	private String password;
	private Date fecha;
	private int aciertos;
	private long tiempo_Record;
	
	public Jugador(int id_j, String nombre, String dni, String password, Date fecha, int aciertos, long tiempo_Record) {
		this.id_j = id_j;
		this.nombre = nombre;
		this.dni = dni;
		this.password = password;
		this.fecha = fecha;
		this.aciertos = aciertos;
		this.tiempo_Record = tiempo_Record;
	}
	
	public Jugador(String nombre, String dni, String password) {
		this.nombre = nombre;
		this.dni = dni;
		this.password = password;
	}
	
	public Jugador(String dni, String password) {
		this.dni = dni;
		this.password = password;
	}

	public int getId_j() {
		return id_j;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDni() {
		return dni;
	}

	public String getPassword() {
		return password;
	}

	public Date getFecha() {
		return fecha;
	}

	public int getAciertos() {
		return aciertos;
	}

	public long getTiempo_Record() {
		return tiempo_Record;
	}
}