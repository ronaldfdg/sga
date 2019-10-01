package com.ronaldfdg.services;

import java.sql.SQLException;

import com.ronaldfdg.dto.Usuario;

public interface UsuarioService {

	public boolean validarUsuario(Usuario usuario) throws SQLException;
	
}
