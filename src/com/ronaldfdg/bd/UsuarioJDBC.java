package com.ronaldfdg.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ronaldfdg.dto.Usuario;

public class UsuarioJDBC {

	private Connection connection;
	private static final String SQL_SELECT_BY_USERNAME_AND_PASS = "select * from usuario where username = ? and password = ?";

	public UsuarioJDBC() {

	}

	public UsuarioJDBC(Connection connection) {
		this.connection = connection;
	}

	public boolean getUsuarioByNameAndPassword(Usuario usuario) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = (this.connection != null) ? this.connection : DataBaseConnection.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT_BY_USERNAME_AND_PASS);
			preparedStatement.setString(1, usuario.getUsername());
			preparedStatement.setString(2, usuario.getPassword());
			resultSet = preparedStatement.executeQuery();

			if(resultSet.absolute(1)) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.close(connection);
			DataBaseConnection.close(preparedStatement);
			DataBaseConnection.close(resultSet);
		}

		return false;
	}

}
