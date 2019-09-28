package com.ronaldfdg.services;

import java.sql.SQLException;
import java.util.List;

import com.ronaldfdg.dto.Persona;

public interface PersonaService {
	
	public List<Persona> getAllPeople() throws SQLException;
	public Persona getPersonaById(int idPersona) throws SQLException;
	public void savePerson(Persona persona) throws SQLException;
	public void deletePerson(int idPersona) throws SQLException;
	

}
