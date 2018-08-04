package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class PreguntaTableModel extends AbstractTableModel {
	private ArrayList<Pregunta> listPreguntas;
	private String[] fields = {"ID_P", "PREGUNTA"};
	
	public PreguntaTableModel(ArrayList<Pregunta> listPreguntas) {
		this.listPreguntas = listPreguntas;
	}
	
	@Override
	public int getColumnCount() {
		return fields.length;
	}

	@Override
	public int getRowCount() {
		return listPreguntas.size();
	}

	@Override
	public Object getValueAt(int fila, int col) {
		switch (col) {
		
		case 0: return listPreguntas.get(fila).getId_p();

		case 1: return listPreguntas.get(fila).getPregunta();
		
		default: return null;
		}
	}
	
	public Pregunta getPreguntaObject(int fila) {
		return listPreguntas.get(fila);
	}

	@Override
	public String getColumnName(int column) {
		return fields[column];
	}
}
