package com.cibertec.dao.Impl;

import com.cibertec.dao.UsuarioDao;
import com.cibertec.models.Usuario;
import com.cibertec.repository.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoImpl implements UsuarioDao {
//    @Override
//    public List<Usuario> listarUsuarios() throws SQLException {
//           List<Usuario> resultado = new ArrayList<>();
//           String query = "SELECT id ,nombre, contrasena FROM Usuarios";
//
//
//        try(
//                Connection connection = DBConnection.getConnection();
//                PreparedStatement statement = connection.prepareStatement(query);
//                ResultSet result = statement.executeQuery();
//        ) {
//
//            while (result.next()) {
//                Usuario itemUsuario = new Usuario(
//                        result.getInt("id"),
//                        result.getString("nombre"),
//                        result.getString("contrasena")
//                );
//                resultado.add(itemUsuario);
//            }
//        }
//        catch (ClassNotFoundException e){
//               throw  new RuntimeException(e);
//        }
//        return resultado;
//}

    @Override
    public Usuario obtenerUsuario(String nombre, String contrasena) throws SQLException {
        Usuario usuario = null;
        String query = "SELECT id, nombre, contrasena FROM Usuarios WHERE nombre = ? AND contrasena = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, nombre);
            statement.setString(2, contrasena);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    usuario = new Usuario(
                            result.getInt("id"),
                            result.getString("nombre"),
                            result.getString("contrasena")
                    );
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return usuario;
    }

//    @Override
//    public void registrarUsuario(Usuario usuario) throws SQLException {
//        String query = "INSERT INTO Usuarios (nombre, contrasena) VALUES (?, ?)";
//
//        try(
//                //establecemos la conexion momentanea
//                Connection connection = DBConnection.getConnection();
//                PreparedStatement statement = connection.prepareStatement(query);
//        ){
//
//            statement.setString(1, usuario.getNombre());
//            statement.setString(2, usuario.getContrasena());
//
//            //finalmento actualizamos la consulta
//            statement.executeUpdate();
//
//
//        }
//        //capturamos posibles errores que puedan suceder
//        catch (ClassNotFoundException e){
//            throw new RuntimeException(e);
//        }
//    }
}
