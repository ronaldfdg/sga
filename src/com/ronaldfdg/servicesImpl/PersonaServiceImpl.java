package com.ronaldfdg.servicesImpl;

import java.sql.SQLException;
import java.util.List;

import com.ronaldfdg.bd.DataBaseConnection;
import com.ronaldfdg.bd.PersonaJDBC;
import com.ronaldfdg.dto.Persona;
import com.ronaldfdg.services.PersonaService;

public class PersonaServiceImpl implements PersonaService{

	@Override
	public List<Persona> getAllPeople() throws SQLException{
		PersonaJDBC personaJDBC = new PersonaJDBC(DataBaseConnection.getConnection());
		return personaJDBC.getAllPeople();
	}

	@Override
	public Persona getPersonaById(int idPersona) {
		 
		return null;
	}

	@Override
	public void savePerson(Persona persona) {
		 
		
	}

	@Override
	public void deletePerson(int idPersona) {
		
		try {
			PersonaJDBC personaJDBC = new PersonaJDBC(DataBaseConnection.getConnection());
			personaJDBC.delete(idPersona);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
