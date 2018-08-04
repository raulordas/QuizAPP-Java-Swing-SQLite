package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import config.AppMessages;
import model.Pregunta;
import model.RespuestaIncorrecta;

public class DaoPreguntas {
private DaoConfig daoConfig;
	
	public DaoPreguntas() {
		daoConfig = new DaoConfig();
	}
	
	
	public ArrayList<Pregunta> getAllPreguntas() {
		ArrayList<Pregunta> listPreguntas = new ArrayList<Pregunta>();
		Pregunta preguntaAux;
		RespuestaIncorrecta respuestaAux;
		ArrayList<RespuestaIncorrecta> listRespuestasIncorrectas;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM " + DaoTableContracts.TablePreguntas.TABLE_PREGUNTAS;
				
		//Obtiene todas las preguntas y las agrega a un ArrayList
		try {
			con = daoConfig.connectDao();
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			while (rs.next()) {
				preguntaAux = new Pregunta(rs.getInt(1), rs.getString(2), rs.getString(3));
				listPreguntas.add(preguntaAux);
			}
			
			for(int i = 0; i < listPreguntas.size(); i++) {
				
				listRespuestasIncorrectas = new ArrayList<RespuestaIncorrecta>();
				query = "SELECT * FROM " + DaoTableContracts.TableRespuestasIncorrectas.TABLE_RESPUESTAS_INCORRECTAS 
				+ " WHERE " + listPreguntas.get(i).getId_p() + "=" + DaoTableContracts.TableRespuestasIncorrectas.ID_PREGUNTA_FK_ABSOLUTE;
				
				st = con.createStatement();
				rs = st.executeQuery(query);
				
				while (rs.next()) {
					respuestaAux = new RespuestaIncorrecta(rs.getInt(1), rs.getString(2));
					listRespuestasIncorrectas.add(respuestaAux);
				}
				
				listPreguntas.get(i).setListRespuestaIncorrecta(listRespuestasIncorrectas);
				rs.close();
				st.close();
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
				
				if(st != null) {
					st.close();
				}
				
				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, AppMessages.SQL_EXCEPTION);
			}
		}	
		return listPreguntas;
	}

	//Método para insertar una pregunta y sus respuestas correctas e incorrectas.
	public int insertPregunta(Pregunta pregunta) {
		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "INSERT INTO " + DaoTableContracts.TablePreguntas.TABLE_PREGUNTAS + "(" + DaoTableContracts.TablePreguntas.PREGUNTA 
				+ "," + DaoTableContracts.TablePreguntas.RESPUESTA_CORRECTA + ")" + " VALUES(?,?)";
		int res = 0;
		
		try {
			con = daoConfig.connectDao();
			ps = con.prepareStatement(query);
			ps.setString(1, pregunta.getPregunta());
			ps.setString(2, pregunta.getRespuestaCorrecta());
			res = ps.executeUpdate();
			
			//Ejecuta el bloque para insertar respuestas incorrectas si se ha insertado la pregunta adecuadamente.
			if (res == 1) {
				query = "SELECT MAX(" + DaoTableContracts.TablePreguntas.ID_PREGUNTA + ") FROM " + DaoTableContracts.TablePreguntas.TABLE_PREGUNTAS;
				st = con.createStatement();
				rs = st.executeQuery(query);
				
				//Busca el identificador de la última pregunta insertada para asociarlo a las respuestas como FK
				if (rs.next()) {
					int id_p = rs.getInt(1);
					query =  "INSERT INTO " + DaoTableContracts.TableRespuestasIncorrectas.TABLE_RESPUESTAS_INCORRECTAS + "(" + DaoTableContracts.TableRespuestasIncorrectas.RESPUESTA_INCORRECTA 
							+ "," + DaoTableContracts.TableRespuestasIncorrectas.ID_PREGUNTA_FK + ")" + " VALUES(?,?)";
					
					//Inserta las 3 preguntas incorrectas asociadas a la pregunta
					for (int i = 0; i < 3; i++) {
	
						ps = con.prepareStatement(query);
						ps.setString(1, pregunta.getListRespuestaIncorrecta().get(i).getRespuestaIncorrecta());
						ps.setInt(2, id_p);
						ps.executeUpdate();
					}	
				}
			}
			
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, AppMessages.CLASS_EXCEPTION);
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, AppMessages.SQL_EXCEPTION);
		
		} finally {
			
			try {

				if (ps != null) {
					ps.close();
				}
				
				if (rs != null) {
					rs.close();
				}
				
				if(st != null) {
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
	
	//Método para actualizar una pregunta y sus respuestas correctas e incorrectas.
		public int updatePregunta(Pregunta pregunta) {
			Connection con = null;
			Statement st = null;
			
			String query = "UPDATE " + DaoTableContracts.TablePreguntas.TABLE_PREGUNTAS + " SET " + DaoTableContracts.TablePreguntas.PREGUNTA 
					+ "='" + pregunta.getPregunta() + "', " + DaoTableContracts.TablePreguntas.RESPUESTA_CORRECTA + "='" + pregunta.getRespuestaCorrecta() 
					+ "' WHERE " + DaoTableContracts.TablePreguntas.ID_PREGUNTA + "=" + pregunta.getId_p();
			
			int res = 0;
			
			try {
				con = daoConfig.connectDao();
				st = con.createStatement();
				res = st.executeUpdate(query);
				
				System.out.println(res);
				//Ejecuta el bloque para insertar respuestas incorrectas si se ha insertado la pregunta adecuadamente.
				if (res == 1) {
					
					for (int i = 0; i < pregunta.getListRespuestaIncorrecta().size(); i++) {
						 query = "UPDATE " + DaoTableContracts.TableRespuestasIncorrectas.TABLE_RESPUESTAS_INCORRECTAS + " SET " + DaoTableContracts.TableRespuestasIncorrectas.RESPUESTA_INCORRECTA 
									+ " ='" + pregunta.getListRespuestaIncorrecta().get(i).getRespuestaIncorrecta()  
									+ "' WHERE " + DaoTableContracts.TableRespuestasIncorrectas.ID_RESPUESTA_INCORRECTA + "=" + pregunta.getListRespuestaIncorrecta().get(i).getId_r();
						 
						 st = con.createStatement();	
						 res = st.executeUpdate(query);
					}
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, AppMessages.CLASS_EXCEPTION);
			
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, AppMessages.SQL_EXCEPTION);
			
			} finally {
				
				try {
					
					if(st != null) {
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
		
	// Método para actualizar una pregunta y sus respuestas correctas e incorrectas.
		public int deletePregunta(Pregunta pregunta) {
			Connection con = null;
			Statement st = null;
			String query = "";
			int res = 0;
			
			try {
				con = daoConfig.connectDao();
			
				for (int i = 0; i < pregunta.getListRespuestaIncorrecta().size(); i++) {
					query = "DELETE FROM " + DaoTableContracts.TableRespuestasIncorrectas.TABLE_RESPUESTAS_INCORRECTAS
						+ " WHERE " + DaoTableContracts.TableRespuestasIncorrectas.ID_RESPUESTA_INCORRECTA + "=" + pregunta.getListRespuestaIncorrecta().get(i).getId_r();
					
					st = con.createStatement();
					res = st.executeUpdate(query);
					
					if (st != null) {
						st.close();
					}
				}
	
				if (res == 1) {
					
					if (st != null) {
						st.close();
					}
					
					query = "DELETE FROM " + DaoTableContracts.TablePreguntas.TABLE_PREGUNTAS 
							+ " WHERE " + DaoTableContracts.TablePreguntas.ID_PREGUNTA + "=" + pregunta.getId_p();
				
					st = con.createStatement();
					res = st.executeUpdate(query);
				}
	
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
}
