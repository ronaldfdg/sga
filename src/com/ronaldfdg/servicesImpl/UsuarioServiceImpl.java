package com.ronaldfdg.servicesImpl;

import java.sql.SQLException;

import com.ronaldfdg.bd.DataBaseConnection;
import com.ronaldfdg.bd.UsuarioJDBC;
import com.ronaldfdg.dto.Usuario;
import com.ronaldfdg.services.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService{
	
	@Override
	public boolean validarUsuario(Usuario usuario) throws SQLException{
		UsuarioJDBC usuarioJDBC = new UsuarioJDBC(DataBaseConnection.getConnection());
		return usuarioJDBC.getUsuarioByNameAndPassword(usuario);
	}

}
