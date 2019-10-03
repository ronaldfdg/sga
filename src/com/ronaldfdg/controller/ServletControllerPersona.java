package com.ronaldfdg.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.ronaldfdg.dto.Persona;
import com.ronaldfdg.services.PersonaService;
import com.ronaldfdg.servicesImpl.PersonaServiceImpl;

@WebServlet(name = "ServletControllerPersona", urlPatterns = { "/ServletControllerPersona" })
public class ServletControllerPersona extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletControllerPersona() {
		super();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, FileUploadException {
		String accion = request.getParameter("accion");

		if ("listarPersonas".equals(accion)) {
			this.listarPersonas(request, response);
		} else if ("eliminarPersona".equals(accion)) {
			this.eliminarPersona(request, response);
		} else if ("agregarPersona".equals(accion)) {
			this.agregarPersona(request, response);
		} else if ("obtenerPersona".equals(accion)) {
			this.obtenerPersona(request, response);
		} else if ("editarPersona".equals(accion)) {
			this.editarPersona(request, response);
		} else if ("obtenerImagen".equals(accion)) {
			this.obtenerImagen(request, response);
		}

	}

	protected void listarPersonas(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		PersonaService personaService = new PersonaServiceImpl();
		List<Persona> listPeople = personaService.getAllPeople();
		HttpSession session = request.getSession();
		String usuario = (String) session.getAttribute("usuario");
		String mensaje = null;

		if (listPeople != null && listPeople.size() > 0)
			request.setAttribute("listPeople", listPeople);
		else
			mensaje = "No hay registro de alguna persona";

		request.setAttribute("mensaje", mensaje);

		if (usuario != null)
			request.getRequestDispatcher("/WEB-INF/pages/listadoPersonas.jsp").forward(request, response);
		else
			response.sendRedirect(request.getServletPath() + "/login.jsp");

	}

	protected void eliminarPersona(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		PersonaService personaService = new PersonaServiceImpl();
		int idPersona = Integer.parseInt(request.getParameter("idPersona"));
		List<Persona> listPeople = personaService.getAllPeople();
		int amountPeople = listPeople.size() - 1;
		String mensaje = null;

		personaService.deletePerson(idPersona);

		listPeople = personaService.getAllPeople();

		if (amountPeople == listPeople.size())
			mensaje = "La persona ha sido eliminada correctamente";

		request.setAttribute("mensaje", mensaje);
		request.setAttribute("listPeople", listPeople);
		request.getRequestDispatcher("/WEB-INF/pages/listadoPersonas.jsp").forward(request, response);
	}

	protected void agregarPersona(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, FileUploadException {
		PersonaService personaService = new PersonaServiceImpl();

		List<Persona> listPeople = personaService.getAllPeople();
		int amountPeople = listPeople.size() + 1;
		String mensaje = null;

		Persona persona = new Persona();

		FileItemFactory itemFactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(itemFactory);
		List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));

		for (FileItem item : items) {
			if (item.getFieldName().equals("idPersona")) {
				persona.setIdPersona(Integer.parseInt(new String(item.get())));
			} else if (item.getFieldName().equals("nombre")) {
				persona.setNombre(new String(item.get()));
			} else if (item.getFieldName().equals("apellido")) {
				persona.setApellidos(new String(item.get()));
			} else if (item.getFieldName().equals("foto")) {
				String contentType = item.getContentType();
				if (contentType != null) {
					if (contentType.equals("image/png") || contentType.equals("image/jpg")
							|| contentType.equals("image/jpeg")) {
						persona.setFoto(item.get());
					}
				}
			}
		}

		personaService.savePerson(persona);

		listPeople = personaService.getAllPeople();

		if (amountPeople == listPeople.size())
			mensaje = "Se agrego la persona correctamente";

		request.setAttribute("mensaje", mensaje);
		request.setAttribute("listPeople", listPeople);
		request.getRequestDispatcher("/WEB-INF/pages/listadoPersonas.jsp").forward(request, response);
	}

	protected void obtenerPersona(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		PersonaService personaService = new PersonaServiceImpl();
		int idPersona = Integer.parseInt(request.getParameter("idPersona"));
		Persona persona = personaService.getPersonaById(idPersona);
		request.setAttribute("persona", persona);
		request.getRequestDispatcher("/WEB-INF/pages/editarPersona.jsp").forward(request, response);

	}

	protected void editarPersona(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, FileUploadException {

		PersonaService personaService = new PersonaServiceImpl();
		int rows = 0;
		String mensaje = null;

		FileItemFactory itemFactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(itemFactory);
		List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));

		Persona persona = new Persona();

		for (FileItem item : items) {
			if (item.getFieldName().equals("idPersona"))
				persona.setIdPersona(Integer.parseInt(new String(item.get())));
			else if (item.getFieldName().equals("nombre"))
				persona.setNombre(new String(item.get()));
			else if (item.getFieldName().equals("apellido"))
				persona.setApellidos(new String(item.get()));
			else if (item.getFieldName().equals("foto")) {
				String contentType = item.getContentType();
				if (contentType != null) {
					if (contentType.equals("image/jpg") || contentType.equals("image/jpeg")
							|| contentType.equals("image/png"))
						persona.setFoto(item.get());
				}
			}
		}

		rows = personaService.updatePerson(persona);
		List<Persona> listPeople = personaService.getAllPeople();

		if (rows > 0) {
			mensaje = "Persona modificada";
		}

		request.setAttribute("mensaje", mensaje);
		request.setAttribute("listPeople", listPeople);
		request.getRequestDispatcher("/WEB-INF/pages/listadoPersonas.jsp").forward(request, response);

	}

	protected void obtenerImagen(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		int idPersona = Integer.parseInt(request.getParameter("idPersona"));

		PersonaService personaService = new PersonaServiceImpl();
		Persona persona = personaService.getPersonaById(idPersona);

		response.setContentType("image/gif");
		OutputStream outputStream = response.getOutputStream();

		if (persona.getFoto() != null) {
			outputStream.write(persona.getFoto());
			outputStream.flush();
		}
		
		outputStream.close();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

}
