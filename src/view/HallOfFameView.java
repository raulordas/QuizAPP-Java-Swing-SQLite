package view;

import javax.swing.*;
import java.awt.*;
import controller.ControllerQuiz;
import model.HallFameTableModel;
import model.Jugador;
import java.util.ArrayList;

public class HallOfFameView extends JPanel implements IGuiQuizApp {
	private JLabel lblHall;
	private JScrollPane scrollPane;
	private JTable tableHallOfFame;
	private HallFameTableModel model;
	
	public HallOfFameView() {
		setBackground(Color.WHITE);
		inicializar();
	}
	
	@Override
	public void inicializar() {
		
		//Tamaño del panel
		setBounds(0, 0, 784, 521);
		setLayout(null);
		
		//Label HallOfFame
		lblHall = new JLabel("Hall Of Fame");
		lblHall.setHorizontalAlignment(SwingConstants.CENTER);
		lblHall.setFont(new Font("Arial", Font.PLAIN, 14));
		lblHall.setBounds(59, 11, 698, 41);
		add(lblHall);
		
		//ScrollPane
		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(59, 63, 698, 367);
		add(scrollPane);
		
		//JTable
		tableHallOfFame = new JTable();
		tableHallOfFame.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableHallOfFame.setFillsViewportHeight(true);
		scrollPane.setViewportView(tableHallOfFame);
	}

	@Override
	public void setControlador(ControllerQuiz control) {

	}
	
	public void cargarTabla(ArrayList<Jugador> listJugadores) {
		model = new HallFameTableModel(listJugadores);
		tableHallOfFame.setModel(model);
	}

}
