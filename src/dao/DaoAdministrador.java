package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import config.AppMessages;
import logic.PasswordEncDec;
import model.Administrador;

public class DaoAdministrador {
	private DaoConfig daoConfig;
	
	public DaoAdministrador() {
		daoConfig = new DaoConfig();
	}
	
	//Método que verifica si hay algún administrador con la información facilitada por el cliente
	public int checkUser(Administrador admin) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM " + DaoTableContracts.TableAdministrador.TABLE_ADMINISTRADOR + 
				" WHERE "  + DaoTableContracts.TableAdministrador.USUARIO +  " = ? AND " 
				+  DaoTableContracts.TableAdministrador.PASSWORD + " = ?";
		
		int res = 0;
		
		try {
			con = daoConfig.connectDao();
			ps = con.prepareStatement(query);
			ps.setString(1, admin.getUsuario());
			ps.setString(2, new PasswordEncDec().EncryptPassword(admin.getPassword()));
			rs = ps.executeQuery();
			
			if (rs.next()) {
				res = 1;
			}
			
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, AppMessages.CLASS_EXCEPTION);
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, AppMessages.SQL_EXCEPTION);
		
		} finally {
			
			try {
				if (rs != null) {

					rs.close();
				}

				if (ps != null) {
					ps.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, AppMessages.SQL_EXCEPTION);
			}
		}
		return res;
	}
	
	//Método para incrustar un administrador en la base de datos. No accesible desde la app.
	public int insertUser(Administrador admin) {
		Connection con = null;
		PreparedStatement ps = null;
		String query = "INSERT INTO " + DaoTableContracts.TableAdministrador.TABLE_ADMINISTRADOR + "(USUARIO, PASSWORD) " + " VALUES(?,?)";
		int res = 0;
		
		try {
			con = daoConfig.connectDao();
			ps = con.prepareStatement(query);
			ps.setString(1, admin.getUsuario());
			ps.setString(2, new PasswordEncDec().EncryptPassword(admin.getPassword()));
			res = ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, AppMessages.CLASS_EXCEPTION);
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, AppMessages.SQL_EXCEPTION);
		
		} finally {
			
			try {

				if (ps != null) {
					ps.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, AppMessages.SQL_EXCEPTION);
			}
		}
		return res;
	}	
}
