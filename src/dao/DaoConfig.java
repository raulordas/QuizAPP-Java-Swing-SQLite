package dao;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteConfig.Pragma;
import config.AppMessages;

public class DaoConfig {
	private String url;
	private String driver;
	
	public DaoConfig() {
		Properties props = new Properties();
		InputStreamReader file = null;
		
		//Carga el Fichero de propiedades que contiene la información de la base de datos
		try {
			file = new FileReader("quiz.properties");
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, AppMessages.ERROR_CARGA);
		}
		
		//Extrae las propiedades del fichero mediante el uso de la clase Properties
		try {
			props.load(file);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, AppMessages.ERROR_FICHERO);
		}
		
		//Asigna a los atributos de la clase el contenido del fichero de propiedades
		this.url = props.getProperty("url");
		this.driver = props.getProperty("driver");
	}
	
	public Connection connectDao() throws SQLException, ClassNotFoundException {
		SQLiteConfig sqLiteConfig = new SQLiteConfig();
		Properties properties = sqLiteConfig.toProperties();
		properties.setProperty(Pragma.DATE_STRING_FORMAT.pragmaName, "yyyy-MM-dd");
	
		Connection con = null;
		
		Class.forName(driver);
		con = DriverManager.getConnection(url, properties);
		
		return con;
	}
}
