package com.ronaldfdg.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ronaldfdg.dto.Persona;

public class PersonaJDBC {
	
	private Connection connection;
	private static final String SQL_SELECT = "select * from persona order by id_persona asc";
	
	public PersonaJDBC() {
		
	}
	
	public PersonaJDBC(Connection connection) {
		this.connection = connection;
	}
	
	public List<Persona> getAllPeople(){
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet;
		Persona persona = null;
		List<Persona> listPeople = new ArrayList<>();
		
		try {
			connection = (this.connection!=null) ? this.connection : DataBaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int idPersona = resultSet.getInt("id_persona");
				String nombre = resultSet.getString("nombre");
				String apellido = resultSet.getString("apellido");
				persona = new Persona();
				persona.setIdPersona(idPersona);
				persona.setNombre(nombre);
				persona.setApellidos(apellido);
				listPeople.add(persona);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.close(connection);
			DataBaseConnection.close(preparedStatement);
		}
		
		return listPeople;
	}
	
	public void delete(int idPersona) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = (this.connection!=null) ? this.connection : DataBaseConnection.getConnection();
			preparedStatement = connection.prepareStatement("delete from persona where id_persona = ?");
			preparedStatement.setInt(1, idPersona);
			int rows = preparedStatement.executeUpdate();
			System.out.println("Filas afectadas: "+rows);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.close(connection);
			DataBaseConnection.close(preparedStatement);
		}
	}

}
