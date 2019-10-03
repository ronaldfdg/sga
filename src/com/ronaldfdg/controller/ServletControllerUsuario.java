package com.ronaldfdg.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ronaldfdg.dto.Usuario;
import com.ronaldfdg.services.UsuarioService;
import com.ronaldfdg.servicesImpl.UsuarioServiceImpl;

@WebServlet(name = "ServletControllerUsuario", urlPatterns = "/ServletControllerUsuario")
public class ServletControllerUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletControllerUsuario() {
		super();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		String accion = request.getParameter("accion");

		if ("validarUsuario".equals(accion)) {
			this.validarUsuario(request, response);
		} else if ("cerrarSesion".equals(accion)) {
			this.cerrarSesion(request, response);
		}
	}

	protected void validarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String mensaje = null;

		Usuario usuario = new Usuario();
		usuario.setUsername(username);
		usuario.setPassword(password);

		UsuarioService usuarioService = new UsuarioServiceImpl();
		boolean usuarioValidado = false;

		usuarioValidado = usuarioService.validarUsuario(usuario);

		if (usuarioValidado) {
			HttpSession session = request.getSession();
			session.setAttribute("usuario", usuario.getUsername());
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else {
			mensaje = "Username o password incorrectos";
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

	protected void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/login.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			this.processRequest(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			this.processRequest(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
