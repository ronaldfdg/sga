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
	public Persona getPersonaById(int idPersona) throws SQLException{
		 PersonaJDBC personaJDBC = new PersonaJDBC(DataBaseConnection.getConnection());
		 return personaJDBC.getPersonById(idPersona);
	}

	@Override
	public void savePerson(Persona persona) throws SQLException{
		PersonaJDBC personaJDBC = new PersonaJDBC(DataBaseConnection.getConnection());
		personaJDBC.insert(persona);
		 		
	}

	@Override
	public void deletePerson(int idPersona) throws SQLException{
		PersonaJDBC personaJDBC = new PersonaJDBC(DataBaseConnection.getConnection());
		personaJDBC.delete(idPersona);
		
	}

	@Override
	public int updatePerson(Persona persona) throws SQLException {
		 PersonaJDBC personaJDBC = new PersonaJDBC(DataBaseConnection.getConnection());
		 return personaJDBC.update(persona);
		
	}
	
	

}
