package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class HallFameTableModel extends AbstractTableModel {
	private ArrayList<Jugador> listJugadores;
	private String[] fields = {"NOMBRE", "FECHA", "ACIERTOS", "TIEMPO RECORD"};
	
	public HallFameTableModel(ArrayList<Jugador> listJugadores) {
		this.listJugadores = listJugadores;
	}
	
	@Override
	public int getColumnCount() {
		return fields.length;
	}

	@Override
	public int getRowCount() {
		return listJugadores.size();
	}

	@Override
	public String getColumnName(int column) {
		return fields[column];
	}

	@Override
	public Object getValueAt(int fila, int col) {
		switch (col) {
		
		case 0: return listJugadores.get(fila).getNombre();

		case 1: return listJugadores.get(fila).getFecha();
		
		case 2: return listJugadores.get(fila).getAciertos();
		
		case 3: return parseTiempo(listJugadores.get(fila).getTiempo_Record());
		
		default: return null;
		}
	}
	
	private String parseTiempo(long tiempo) {
		int seconds = (int) tiempo / 1000 % 60;
		int minutes = (int) tiempo / (1000*60) % 60;
		int hours   = (int) tiempo / (1000*60*60) % 24;
		
		return hours + ":" + minutes + ":" + seconds;
	}
}
