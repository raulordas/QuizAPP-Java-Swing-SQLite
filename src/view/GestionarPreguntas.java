package view;

import javax.swing.*;
import java.awt.*;
import controller.ControllerQuiz;
import model.Pregunta;
import model.PreguntaTableModel;
import config.AppColorStyles;
import java.util.ArrayList;

public class GestionarPreguntas extends JPanel implements IGuiQuizApp {
	private JButton btnEliminar;
	private JButton btnModificarPregunta;
	private JLabel lblPregunta;
	private JScrollPane scrollPane;
	private JTable tablePreguntas;
	private PreguntaTableModel model;
	
	public GestionarPreguntas() {
		setBackground(Color.WHITE);
		inicializar();
	}
	
	@Override
	public void inicializar() {
		
		//Tamaño del panel
		setBounds(0, 0, 784, 521);
		setLayout(null);
		
		//Label Pregunta
		lblPregunta = new JLabel("Seleccione una pregunta para realizar una operaci\u00F3n");
		lblPregunta.setHorizontalAlignment(SwingConstants.CENTER);
		lblPregunta.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPregunta.setBounds(59, 11, 698, 41);
		add(lblPregunta);
		
		//JButton Agregar Pregunta
		btnModificarPregunta = new JButton("MODIFICAR");
		btnModificarPregunta.setPreferredSize(new Dimension(150, 50));
		btnModificarPregunta.setIconTextGap(40);
		btnModificarPregunta.setFont(new Font("Arial", Font.PLAIN, 13));
		btnModificarPregunta.setBackground(new Color(AppColorStyles.APP_MAIN_COLOR[0], AppColorStyles.APP_MAIN_COLOR[1] ,AppColorStyles.APP_MAIN_COLOR[2]));
		btnModificarPregunta.setBounds(256, 449, 150, 50);
		add(btnModificarPregunta);
		
		//JButton Reset
		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setPreferredSize(new Dimension(150, 50));
		btnEliminar.setFont(new Font("Arial", Font.PLAIN, 13));
		btnEliminar.setBackground(new Color(AppColorStyles.APP_MAIN_COLOR[0], AppColorStyles.APP_MAIN_COLOR[1] ,AppColorStyles.APP_MAIN_COLOR[2]));
		btnEliminar.setBounds(411, 449, 150, 50);
		add(btnEliminar);
		
		//ScrollPane
		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(59, 63, 698, 367);
		add(scrollPane);
		
		//JTable
		tablePreguntas = new JTable();
		tablePreguntas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePreguntas.setFillsViewportHeight(true);
		scrollPane.setViewportView(tablePreguntas);
	}

	@Override
	public void setControlador(ControllerQuiz control) {
		btnModificarPregunta.addActionListener(control);
		btnEliminar.addActionListener(control);
	}
	
	public Pregunta getDatos() {
		return model.getPreguntaObject(tablePreguntas.getSelectedRow());
	}
	
	public void cargarTabla(ArrayList<Pregunta> listPreguntas) {
		model = new PreguntaTableModel(listPreguntas);
		tablePreguntas.setModel(model);
	}
	
	public JButton getBtnModificarPregunta() {
		return btnModificarPregunta;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}
}
