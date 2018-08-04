package view;

import javax.swing.*;
import java.awt.*;
import controller.ControllerQuiz;
import model.Pregunta;
import model.RespuestaIncorrecta;
import javax.swing.border.LineBorder;
import config.AppColorStyles;
import config.AppMessages;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ModificarPreguntas extends JPanel implements IGuiQuizApp {
	private static final String EMPTY_STRING = "";
	private JScrollPane scrollPanePregunta;
	private JScrollPane scrollPaneRIncorrecta1;
	private JButton btnBorrar;
	private JButton btnAgregarPregunta;
	private JTextArea textRespuestaIncorrecta3;
	private JTextArea textRespuestaIncorrecta2;
	private JTextArea textRespuestaIncorrecta;
	private JTextArea txtRespuestaCorrecta;
	private JTextArea txtPregunta;
	private JLabel lblRespuestasIncorrectas;
	private JLabel lblIntroduceLaRespuesta;
	private JLabel lblPregunta;
	private JScrollPane scrollPaneRCorrecta;
	private JScrollPane scrollPaneRIncorrecta2;
	private JScrollPane scrollPaneRIncorrecta3;
	private int id_p;
	private int id_r1;
	private int id_r2;
	private int id_r3;
	
	public ModificarPreguntas() {
		setBackground(Color.WHITE);
		inicializar();
	}
	
	@Override
	public void inicializar() {
		
		//Tamaño del panel
		setBounds(0, 0, 784, 521);
		setLayout(null);
		
		//Scroll Pane Pregunta
		scrollPanePregunta = new JScrollPane();
		scrollPanePregunta.setBorder(new LineBorder(new Color(AppColorStyles.APP_MAIN_COLOR[0], AppColorStyles.APP_MAIN_COLOR[1] ,AppColorStyles.APP_MAIN_COLOR[2]), 3));
		scrollPanePregunta.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPanePregunta.setBounds(58, 49, 699, 51);
		add(scrollPanePregunta);
		
		//Text Area Pregunta
		txtPregunta = new JTextArea();
		txtPregunta.setFont(new Font("Arial", Font.PLAIN, 12));
		scrollPanePregunta.setViewportView(txtPregunta);
		
		//Label Pregunta
		lblPregunta = new JLabel("Introduce la Pregunta");
		lblPregunta.setHorizontalAlignment(SwingConstants.CENTER);
		lblPregunta.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPregunta.setBounds(59, 11, 698, 41);
		add(lblPregunta);
		
		//Label Respuesta
		lblIntroduceLaRespuesta = new JLabel("Introduce la Respuesta Correcta");
		lblIntroduceLaRespuesta.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroduceLaRespuesta.setFont(new Font("Arial", Font.PLAIN, 14));
		lblIntroduceLaRespuesta.setBounds(58, 111, 698, 41);
		add(lblIntroduceLaRespuesta);
		
		//ScrollPane Respuesta Correcta
		scrollPaneRCorrecta = new JScrollPane();
		scrollPaneRCorrecta.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneRCorrecta.setBorder(new LineBorder(new Color(AppColorStyles.APP_MAIN_COLOR[0], AppColorStyles.APP_MAIN_COLOR[1] ,AppColorStyles.APP_MAIN_COLOR[2]), 3));
		scrollPaneRCorrecta.setBounds(58, 149, 699, 51);
		add(scrollPaneRCorrecta);
		
		//Text Area Respuesta Correcta
		txtRespuestaCorrecta = new JTextArea();
		txtRespuestaCorrecta.setBorder(null);
		scrollPaneRCorrecta.setViewportView(txtRespuestaCorrecta);
		
		//Label Respuestas Incorrectas
		lblRespuestasIncorrectas = new JLabel("Introduce las 3 Respuestas Incorrectas");
		lblRespuestasIncorrectas.setHorizontalAlignment(SwingConstants.CENTER);
		lblRespuestasIncorrectas.setFont(new Font("Arial", Font.PLAIN, 14));
		lblRespuestasIncorrectas.setBounds(59, 216, 698, 41);
		add(lblRespuestasIncorrectas);
		
		//Scroll Pane Respuesta Incorrecta 1
		scrollPaneRIncorrecta1 = new JScrollPane();
		scrollPaneRIncorrecta1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneRIncorrecta1.setBorder(new LineBorder(new Color(AppColorStyles.APP_MAIN_COLOR[0], AppColorStyles.APP_MAIN_COLOR[1] ,AppColorStyles.APP_MAIN_COLOR[2]), 3));
		scrollPaneRIncorrecta1.setBounds(58, 254, 699, 51);
		add(scrollPaneRIncorrecta1);
		
		//Text Area Respuesta Incorrecta 1
		textRespuestaIncorrecta = new JTextArea();
		scrollPaneRIncorrecta1.setViewportView(textRespuestaIncorrecta);
		
		//Scroll Pane Respuesta Incorrecta 1
		scrollPaneRIncorrecta2 = new JScrollPane();
		scrollPaneRIncorrecta2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneRIncorrecta2.setBorder(new LineBorder(new Color(AppColorStyles.APP_MAIN_COLOR[0], AppColorStyles.APP_MAIN_COLOR[1] ,AppColorStyles.APP_MAIN_COLOR[2]), 3));
		scrollPaneRIncorrecta2.setBounds(58, 316, 699, 51);
		add(scrollPaneRIncorrecta2);
		
		//Text Area Respuesta Incorrecta 2
		textRespuestaIncorrecta2 = new JTextArea();
		scrollPaneRIncorrecta2.setViewportView(textRespuestaIncorrecta2);
		
		//Scroll Pane Respuesta Incorrecta 1
		scrollPaneRIncorrecta3 = new JScrollPane();
		scrollPaneRIncorrecta3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneRIncorrecta3.setBorder(new LineBorder(new Color(AppColorStyles.APP_MAIN_COLOR[0], AppColorStyles.APP_MAIN_COLOR[1] ,AppColorStyles.APP_MAIN_COLOR[2]), 3));
		scrollPaneRIncorrecta3.setBounds(58, 378, 699, 51);
		add(scrollPaneRIncorrecta3);
		
		//Text Area Respuesta Incorrecta 3
		textRespuestaIncorrecta3 = new JTextArea();
		scrollPaneRIncorrecta3.setViewportView(textRespuestaIncorrecta3);
		
		//JButton Agregar Pregunta
		btnAgregarPregunta = new JButton("ACEPTAR");
		btnAgregarPregunta.setPreferredSize(new Dimension(150, 50));
		btnAgregarPregunta.setIconTextGap(40);
		btnAgregarPregunta.setFont(new Font("Arial", Font.PLAIN, 13));
		btnAgregarPregunta.setBackground(new Color(AppColorStyles.APP_MAIN_COLOR[0], AppColorStyles.APP_MAIN_COLOR[1] ,AppColorStyles.APP_MAIN_COLOR[2]));
		btnAgregarPregunta.setBounds(256, 449, 150, 50);
		add(btnAgregarPregunta);
		
		//JButton Reset
		btnBorrar = new JButton("BORRAR");
		btnBorrar.addActionListener(new ActionListener() {
			
			//Accion que resetea los campos del formulario
			public void actionPerformed(ActionEvent e) {
				textRespuestaIncorrecta.setText(EMPTY_STRING);
				textRespuestaIncorrecta2.setText(EMPTY_STRING);
				textRespuestaIncorrecta3.setText(EMPTY_STRING);
				txtPregunta.setText(EMPTY_STRING);
				txtRespuestaCorrecta.setText(EMPTY_STRING);
			}
		});
		btnBorrar.setPreferredSize(new Dimension(150, 50));
		btnBorrar.setFont(new Font("Arial", Font.PLAIN, 13));
		btnBorrar.setBackground(new Color(AppColorStyles.APP_MAIN_COLOR[0], AppColorStyles.APP_MAIN_COLOR[1] ,AppColorStyles.APP_MAIN_COLOR[2]));
		btnBorrar.setBounds(411, 449, 150, 50);
		add(btnBorrar);
	}

	@Override
	public void setControlador(ControllerQuiz control) {
		btnAgregarPregunta.addActionListener(control);
	}
	
	public Pregunta getDatos() {
		
		//Condición para que se envíen los datos del formulario al controlador
		if (txtPregunta.getText().isEmpty() || txtRespuestaCorrecta.getText().isEmpty() 
				|| textRespuestaIncorrecta.getText().isEmpty() || textRespuestaIncorrecta2.getText().isEmpty() 
				|| textRespuestaIncorrecta3.getText().isEmpty()) {
			
			JOptionPane.showMessageDialog(getParent(), AppMessages.EMPTY_FIELDS);
			
			return null;
			
		} else {
			
			//Array De respuestas incorrectas
			ArrayList<RespuestaIncorrecta> listRespuestasIncorrectas = new ArrayList<RespuestaIncorrecta>();
			
			RespuestaIncorrecta r1 = new RespuestaIncorrecta(id_r1, textRespuestaIncorrecta.getText());
			RespuestaIncorrecta r2 = new RespuestaIncorrecta(id_r2, textRespuestaIncorrecta2.getText());
			RespuestaIncorrecta r3 = new RespuestaIncorrecta(id_r3, textRespuestaIncorrecta3.getText());
			
			listRespuestasIncorrectas.add(r1);
			listRespuestasIncorrectas.add(r2);
			listRespuestasIncorrectas.add(r3);
			
			return new Pregunta(id_p, txtPregunta.getText(), txtRespuestaCorrecta.getText(), listRespuestasIncorrectas);
		}
	}
	
	public void cargarPregunta(Pregunta pregunta) {
		id_p = pregunta.getId_p();
		id_r1 = pregunta.getListRespuestaIncorrecta().get(0).getId_r();
		id_r2 = pregunta.getListRespuestaIncorrecta().get(1).getId_r();
		id_r3 = pregunta.getListRespuestaIncorrecta().get(2).getId_r();
		
		lblPregunta.setText("Introduce el nombre para la pregunta con identificador " + pregunta.getId_p());
		txtPregunta.setText(pregunta.getPregunta());
		txtRespuestaCorrecta.setText(pregunta.getRespuestaCorrecta());
		textRespuestaIncorrecta.setText(pregunta.getListRespuestaIncorrecta().get(0).getRespuestaIncorrecta());
		textRespuestaIncorrecta2.setText(pregunta.getListRespuestaIncorrecta().get(1).getRespuestaIncorrecta());
		textRespuestaIncorrecta3.setText(pregunta.getListRespuestaIncorrecta().get(2).getRespuestaIncorrecta());
	}
	
	public void recycle() {
		textRespuestaIncorrecta.setText(EMPTY_STRING);
		textRespuestaIncorrecta2.setText(EMPTY_STRING);
		textRespuestaIncorrecta3.setText(EMPTY_STRING);
		txtPregunta.setText(EMPTY_STRING);
		txtRespuestaCorrecta.setText(EMPTY_STRING);
	}
	
	public JButton getBtnAgregarPregunta() {
		return btnAgregarPregunta;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}
}
