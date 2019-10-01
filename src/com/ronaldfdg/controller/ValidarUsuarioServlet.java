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

/**
 * Servlet implementation class ValidarUsuarioServlet
 */
@WebServlet(name="ValidarUsuarioServlet",urlPatterns="/ValidarUsuarioServlet")
public class ValidarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ValidarUsuarioServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String mensaje = null;
		
		Usuario usuario = new Usuario();
		usuario.setUsername(username);
		usuario.setPassword(password);
		
		UsuarioService usuarioService = new UsuarioServiceImpl();
		boolean usuarioValidado = false;
		
		try {
			usuarioValidado = usuarioService.validarUsuario(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(usuarioValidado) {
			HttpSession session = request.getSession();
			session.setAttribute("usuario", usuario.getUsername());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			mensaje="Username o password incorrectos";
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}	
	}

}
