package com.cibertec;

import com.cibertec.dao.Impl.UsuarioDaoImpl;
import com.cibertec.dao.UsuarioDao;
import com.cibertec.models.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "ValidarSesionServlet", urlPatterns = "/ValidarSesionServlet")
public class ValidarSesionServlet extends HttpServlet {

    UsuarioDao usuarioDao = new UsuarioDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        String contrasena = req.getParameter("contrasena");

        try {
            Usuario usuario = usuarioDao.obtenerUsuario(nombre, contrasena);

            if (usuario != null) {
                // Usuario encontrado, redirigir a la página de bienvenida
                req.setAttribute("nombre", usuario.getNombre());
                req.setAttribute("contrasena", usuario.getContrasena());
                req.getRequestDispatcher("Validacion.jsp").forward(req, resp);
            } else {
                // Credenciales incorrectas, mostrar error
                req.setAttribute("error", "Usuario o contraseña incorrectos");
                req.getRequestDispatcher("formulario.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
