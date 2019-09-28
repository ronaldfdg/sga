package com.ronaldfdg.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ronaldfdg.bd.DataBaseConnection;
import com.ronaldfdg.bd.PersonaJDBC;
import com.ronaldfdg.dto.Persona;

public class Testing {

	public static void main(String[] args) {
		
		try {
			Connection connection = DataBaseConnection.getConnection();
			PersonaJDBC personaJDBC = new PersonaJDBC(connection);
			List<Persona> listPeople = personaJDBC.getAllPeople();
			for(Persona persona : listPeople)
				System.out.println(persona);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
