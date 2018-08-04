package view;

import javax.swing.*;
import java.awt.*;
import controller.ControllerQuiz;
import model.Jugador;
import model.Pregunta;
import javax.swing.border.LineBorder;
import config.AppColorStyles;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

public class JugarView extends JPanel implements IGuiQuizApp {
	private static final String EMPTY_STRING = "";
	private JScrollPane scrollPanePregunta;
	private JButton btnNext;
	private JTextArea txtPregunta;
	private JLabel lblIntroduceLaRespuesta;
	private JLabel lblPregunta;
	private JScrollPane scrollPaneRCorrecta;
	private JLabel labelTime;
	private long tComienzo;
	private long tFinal;
	private JButton btnComenzar;
	private JList<String> list;
	private DefaultListModel<String> model;
	private ArrayList<Pregunta> listPreguntas;
	private ArrayList<Integer> randomList;
	private ArrayList<Integer> randomListAnswers;
	private int cont = 1;
	private int aciertos = 0;
	private Pregunta loadedPregunta;
	private Timer timer;
	
	public JugarView() {
		setBackground(Color.WHITE);
		inicializar();
	}
	
	public JugarView(ControllerQuiz control) {
		setBackground(Color.WHITE);
		inicializar();
		setControlador(control);
		enableDisable(false);
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
		lblIntroduceLaRespuesta = new JLabel("Selecciona la Respuesta Correcta");
		lblIntroduceLaRespuesta.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroduceLaRespuesta.setFont(new Font("Arial", Font.PLAIN, 14));
		lblIntroduceLaRespuesta.setBounds(58, 111, 698, 41);
		add(lblIntroduceLaRespuesta);
		
		//ScrollPane Respuesta Correcta
		scrollPaneRCorrecta = new JScrollPane();
		scrollPaneRCorrecta.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneRCorrecta.setBorder(new LineBorder(new Color(AppColorStyles.APP_MAIN_COLOR[0], AppColorStyles.APP_MAIN_COLOR[1] ,AppColorStyles.APP_MAIN_COLOR[2]), 3));
		scrollPaneRCorrecta.setBounds(58, 149, 699, 168);
		add(scrollPaneRCorrecta);
		
		model = new DefaultListModel<String>();
		list = new JList<String>();
		list.setFont(new Font("Arial", Font.PLAIN, 10));
		list.setVisibleRowCount(45);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setValueIsAdjusting(true);
		list.setModel(model);
		scrollPaneRCorrecta.setViewportView(list);
		
		randomList = new ArrayList<Integer>();
		randomListAnswers = new ArrayList<Integer>();
		
		//JButton Agregar Pregunta
		btnNext = new JButton("SIGUIENTE");
		btnNext.setForeground(Color.WHITE);
		btnNext.setPreferredSize(new Dimension(150, 50));
		btnNext.setIconTextGap(40);
		btnNext.setFont(new Font("Arial", Font.PLAIN, 13));
		btnNext.setBackground(new Color(AppColorStyles.APP_MAIN_COLOR[0], AppColorStyles.APP_MAIN_COLOR[1] ,AppColorStyles.APP_MAIN_COLOR[2]));
		btnNext.setBounds(301, 421, 203, 50);
		add(btnNext);
		
		labelTime = new JLabel("");
		labelTime.setFont(new Font("Arial", Font.PLAIN, 26));
		labelTime.setBounds(58, 372, 181, 51);
		add(labelTime);
		
		btnComenzar = new JButton("PULSA PARA COMENZAR");
		btnComenzar.setForeground(Color.WHITE);
		btnComenzar.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				tComienzo = System.currentTimeMillis();
				enableDisable(true);
				timer = new Timer();
				timer.schedule(new TimerTask() {
					
					@Override
					public void run() {
						int seconds = (int) (((System.currentTimeMillis() - tComienzo) / 1000) % 60);
						int minutes = (int) (((System.currentTimeMillis() -tComienzo) / (1000*60)) % 60);
						int hours   = (int) (((System.currentTimeMillis() - tComienzo) / (1000*60*60)) % 24);
						
						labelTime.setText(hours + ":" + minutes + ":" + seconds);
						
					}
				}, 1000, 1000);
			}
		});
		btnComenzar.setPreferredSize(new Dimension(150, 50));
		btnComenzar.setIconTextGap(40);
		btnComenzar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnComenzar.setBackground(new Color(223, 86, 86));
		btnComenzar.setBounds(244, 189, 316, 151);
		add(btnComenzar);
	}

	@Override
	public void setControlador(ControllerQuiz control) {
		btnNext.addActionListener(control);
	}
	
	public Pregunta getDatos(Jugador jugador) {
		return null;
	}
	
	public void cargarPregunta(Pregunta pregunta) {
		ArrayList<String> listaRespuestas = new ArrayList<String>();
		listaRespuestas.add(pregunta.getRespuestaCorrecta());
		listaRespuestas.add(pregunta.getListRespuestaIncorrecta().get(0).getRespuestaIncorrecta());
		listaRespuestas.add(pregunta.getListRespuestaIncorrecta().get(1).getRespuestaIncorrecta());
		listaRespuestas.add(pregunta.getListRespuestaIncorrecta().get(2).getRespuestaIncorrecta());
		
		this.loadedPregunta = pregunta;
		
		for (int i = 0; i < listaRespuestas.size(); i++) {
			model.addElement(listaRespuestas.get(randomAnswer(listaRespuestas)));
		}
		
		lblPregunta.setText("Pregunta " + cont);
		txtPregunta.setText(pregunta.getPregunta());
	}
	
	public void recycle() {
		txtPregunta.setText(EMPTY_STRING);
	}
	
	public JButton getBtnAgregarPregunta() {
		return btnNext;
	}
	
	public void enableDisable(boolean estado) {
		lblIntroduceLaRespuesta.setVisible(estado);
		lblPregunta.setVisible(estado);
		labelTime.setVisible(estado);
		txtPregunta.setVisible(estado);
		list.setVisible(estado);
		scrollPanePregunta.setVisible(estado);
		scrollPaneRCorrecta.setVisible(estado);
		btnNext.setVisible(estado);
		btnComenzar.setVisible(!estado);
	}

	public JButton getBtnNext() {
		return btnNext;
	}
	
	//Método que recibe un ArrayList y lo almacena en el atributo de la clase. Carga la primera pregunta del juego.
	public void loadListPreguntas(ArrayList<Pregunta> listPreguntas) {
		this.listPreguntas = listPreguntas;
		
		cargarPregunta(this.listPreguntas.get(randomQuestion(this.listPreguntas)));
		
	}
	
	public void checkAciertos(Pregunta pregunta) {
		if (pregunta.getRespuestaCorrecta().equals(list.getSelectedValue())) {
			aciertos++;
		}
	}
	
	//Método que devuelve un entero asociado a un índice aleatorio del ArrayList
	public int randomQuestion(ArrayList<Pregunta> listPreguntas) {
		Random r = new Random();
		int num;
		
		do  {
			num = r.nextInt(listPreguntas.size());
		
		} while (randomList.contains(num));
		
		randomList.add(num);
		
		return num;
	}
	
	//Método que devuelve un entero asociado a un índice aleatorio del ArrayList
	public int randomAnswer(ArrayList<String> listRespuestas) {
			Random r = new Random();
			int num;
			
			do  {
				num = r.nextInt(listRespuestas.size());
			
			} while (randomListAnswers.contains(num));
			
			randomListAnswers.add(num);
			
			return num;
		}
	
	
	public void clearRandomAnswers() {
		randomListAnswers.clear();
	}
	
	public void reset() {
		resetModel();
		cont = 1;
		aciertos = 0;
		randomList.clear();
		randomListAnswers.clear();
	}

	public int getCont() {
		return this.cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}
	
	public void resetModel() {
		model.removeAllElements();
	}

	public ArrayList<Pregunta> getListPreguntas() {
		return listPreguntas;
	}

	public int getAciertos() {
		return aciertos;
	}

	public void setAciertos(int aciertos) {
		this.aciertos = aciertos;
	}
	
	public long finalizarTimer() {
		tFinal = System.currentTimeMillis() - tComienzo;
		timer = null;
		return tFinal;
	}

	public Pregunta getLoadedPregunta() {
		return loadedPregunta;
	}
}
