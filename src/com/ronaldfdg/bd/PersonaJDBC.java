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
	private static final String SQL_DELETE = "delete from persona where id_persona = ?";
	private static final String SQL_INSERT = "insert into persona (id_persona,nombre,apellido) values (?,?,?)";
	private static final String SQL_SELECT_BY_IDPERSONA = "select * from persona where id_persona = ?";
	private static final String SQL_UPDATE = "update persona set nombre = ?, apellido = ? where id_persona = ?";

	public PersonaJDBC() {

	}

	public PersonaJDBC(Connection connection) {
		this.connection = connection;
	}

	public List<Persona> getAllPeople() {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet;
		Persona persona = null;
		List<Persona> listPeople = new ArrayList<>();

		try {
			connection = (this.connection != null) ? this.connection : DataBaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
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
			connection = (this.connection != null) ? this.connection : DataBaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(SQL_DELETE);
			preparedStatement.setInt(1, idPersona);
			int rows = preparedStatement.executeUpdate();
			System.out.println("Filas afectadas: " + rows);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.close(connection);
			DataBaseConnection.close(preparedStatement);
		}
	}

	public void insert(Persona persona) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int rows = 0;
		int index = 1;

		try {
			connection = (this.connection != null) ? this.connection : DataBaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(SQL_INSERT);

			preparedStatement.setInt(index++, persona.getIdPersona());
			preparedStatement.setString(index++, persona.getNombre());
			preparedStatement.setString(index, persona.getApellidos());

			rows = preparedStatement.executeUpdate();
			System.out.println("Filas afectadas: " + rows);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.close(connection);
			DataBaseConnection.close(preparedStatement);
		}
	}

	public Persona getPerson(int idPersona) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Persona persona = null;

		try {
			connection = (this.connection != null) ? this.connection : DataBaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT_BY_IDPERSONA);
			preparedStatement.setInt(1, idPersona);
			resultSet = preparedStatement.executeQuery();

			persona = new Persona();

			while (resultSet.next()) {

				String nombre = resultSet.getString("nombre");
				String apellido = resultSet.getString("apellido");

				persona.setIdPersona(idPersona);
				persona.setNombre(nombre);
				persona.setApellidos(apellido);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.close(connection);
			DataBaseConnection.close(preparedStatement);
			DataBaseConnection.close(resultSet);
		}

		return persona;
	}

	public int update(Persona persona) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int rows = 0;
		int index = 1;

		try {
			connection = (this.connection != null) ? this.connection : DataBaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(SQL_UPDATE);
			
			preparedStatement.setString(index++, persona.getNombre());
			preparedStatement.setString(index++, persona.getApellidos());
			preparedStatement.setInt(index, persona.getIdPersona());
			
			rows = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.close(connection);
			DataBaseConnection.close(preparedStatement);
		}
		
		return rows;
	}

}
