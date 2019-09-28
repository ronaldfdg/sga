package com.ronaldfdg.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ronaldfdg.dto.Persona;
import com.ronaldfdg.services.PersonaService;
import com.ronaldfdg.servicesImpl.PersonaServiceImpl;

@WebServlet(name = "ServletController", urlPatterns = { "/ServletController" })
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletController() {
		super();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		String accion = request.getParameter("accion");

		if ("listarPersonas".equals(accion)) {
			this.listarPersonas(request, response);
		} else if ("eliminarPersona".equals(accion)) {
			this.eliminarPersona(request, response);
		}
	}

	protected void listarPersonas(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		PersonaService personaService = new PersonaServiceImpl();
		List<Persona> listPeople = personaService.getAllPeople();
		String mensaje = null;
		
		if (listPeople != null && listPeople.size() > 0) 
			request.setAttribute("listPeople", listPeople);
		else 
			mensaje = "No hay registro de alguna persona";
		
		
		request.setAttribute("mensaje", mensaje);
		
		request.getRequestDispatcher("/WEB-INF/pages/listadoPersonas.jsp").forward(request, response);
	
	}
	
	protected void eliminarPersona(HttpServletRequest request, HttpServletResponse response) 
		throws SQLException, IOException, ServletException {
		
		PersonaService personaService = new PersonaServiceImpl();
		int idPersona = Integer.parseInt(request.getParameter("idPersona"));
		List<Persona> listPeople = personaService.getAllPeople();
		int currentPeople = listPeople.size()-1;
		String mensaje = null;
		
		personaService.deletePerson(idPersona);
		
		listPeople = personaService.getAllPeople();
		
		if(currentPeople == listPeople.size()) 
			mensaje="La persona ha sido eliminada correctamente";
		
		
		
		request.setAttribute("mensaje", mensaje);
		request.setAttribute("listPeople", listPeople);
		request.getRequestDispatcher("/WEB-INF/pages/listadoPersonas.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
