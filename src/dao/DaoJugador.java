package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import config.AppMessages;
import logic.PasswordEncDec;
import model.Jugador;

public class DaoJugador {
	private DaoConfig daoConfig;

	public DaoJugador() {
		daoConfig = new DaoConfig();
	}
	
	// Método que verifica si hay algún jugador con los datos facilitados por el
	// cliente
	public ArrayList<Jugador> getAllPlayers() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Jugador> listJugadores = new ArrayList<Jugador>();
		Jugador jugadorAux = null;

		String query = "SELECT * FROM " + DaoTableContracts.TableJugadores.TABLE_JUGADORES;

		try {
			con = daoConfig.connectDao();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				jugadorAux = new Jugador(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getInt(6), rs.getLong(7));
				listJugadores.add(jugadorAux);
				
			}

		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, AppMessages.CLASS_EXCEPTION);

		} catch (SQLException e) {
			e.printStackTrace();
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
		return listJugadores;
	}

	// Método que verifica si hay algún jugador con los datos facilitados por el
	// cliente
	public Jugador checkPlayer(Jugador jugador) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Jugador jugadorAux = null;

		String query = "SELECT * FROM " + DaoTableContracts.TableJugadores.TABLE_JUGADORES + " WHERE "
				+ DaoTableContracts.TableJugadores.DNI + " = ? AND " + DaoTableContracts.TableJugadores.PASSWORD
				+ " = ?";

		try {
			con = daoConfig.connectDao();
			ps = con.prepareStatement(query);
			ps.setString(1, jugador.getDni());
			ps.setString(2, new PasswordEncDec().EncryptPassword(jugador.getPassword()));
			rs = ps.executeQuery();

			if (rs.next()) {
				jugadorAux = new Jugador(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getInt(6), rs.getLong(7));
			}

		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, AppMessages.CLASS_EXCEPTION);

		} catch (SQLException e) {
			e.printStackTrace();
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
		return jugadorAux;
	}

	// Método para insertar un JUGADOR en la Persistencia
	public int insertPlayer(Jugador jugador) {
		Connection con = null;
		PreparedStatement ps = null;
		String query = "INSERT INTO " + DaoTableContracts.TableJugadores.TABLE_JUGADORES + "(NOMBRE, DNI, PASSWORD) "
				+ " VALUES(?,?,?)";
		int res = 0;

		try {
			con = daoConfig.connectDao();
			ps = con.prepareStatement(query);
			ps.setString(1, jugador.getNombre());
			ps.setString(2, jugador.getDni());
			ps.setString(3, jugador.getPassword());
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

	// Método para actualizar el registro de un jugador en la persistencia
	public int updateJugador(Jugador jugador) {
		Connection con = null;
		Statement st = null;

		String query = "UPDATE " + DaoTableContracts.TableJugadores.TABLE_JUGADORES + " SET "
				+ DaoTableContracts.TableJugadores.FECHA + "='" + jugador.getFecha() + "', "
				+ DaoTableContracts.TableJugadores.ACIERTOS + "=" + jugador.getAciertos() + ", "
				+ DaoTableContracts.TableJugadores.TIEMPO_RECORD + "=" + jugador.getTiempo_Record() 
				+ " WHERE " + DaoTableContracts.TableJugadores.ID_JUGADOR + "=" + jugador.getId_j();

		int res = 0;

		try {
			con = daoConfig.connectDao();
			st = con.createStatement();
			res = st.executeUpdate(query);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, AppMessages.CLASS_EXCEPTION);

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, AppMessages.SQL_EXCEPTION);

		} finally {

			try {

				if (st != null) {
					st.close();
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
	
	// Método que revalida si el jugador con los datos facilitados por el
		// cliente
		public Jugador revalidatePlayer(Jugador jugador) {
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			Jugador jugadorAux = null;

			String query = "SELECT * FROM " + DaoTableContracts.TableJugadores.TABLE_JUGADORES + " WHERE "
					+ DaoTableContracts.TableJugadores.DNI + " = ? AND " + DaoTableContracts.TableJugadores.PASSWORD
					+ " = ?";

			try {
				con = daoConfig.connectDao();
				ps = con.prepareStatement(query);
				ps.setString(1, jugador.getDni());
				ps.setString(2, jugador.getPassword());
				rs = ps.executeQuery();

				if (rs.next()) {
					jugadorAux = new Jugador(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
							rs.getInt(6), rs.getLong(7));
				}

			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, AppMessages.CLASS_EXCEPTION);

			} catch (SQLException e) {
				e.printStackTrace();
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
			return jugadorAux;
		}
}
