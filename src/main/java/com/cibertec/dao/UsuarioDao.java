package com.cibertec.dao;

import com.cibertec.models.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioDao {

//    List<Usuario> listarUsuarios() throws SQLException;
    Usuario obtenerUsuario (String nombre, String contrasena) throws SQLException;
//    void registrarUsuario(Usuario usuario) throws SQLException;


}
