package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.pres.srde.service.ServiceUsuario;

import java.io.IOException;

@WebServlet(name = "servletrestablecer", value = "/servlet-restablecer")
public class ServletRestablecer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/restablecer-contrasena.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String correo = req.getParameter("correo");
        String nuevaContrasena = req.getParameter("nuevaContrasena");
        String confirmarContrasena = req.getParameter("confirmarContrasena");

        if (correo == null || correo.trim().isEmpty()
                || nuevaContrasena == null || nuevaContrasena.trim().isEmpty()) {
            req.setAttribute("mensajeError", "Todos los campos son obligatorios.");
            req.getRequestDispatcher("/WEB-INF/restablecer-contrasena.jsp").forward(req, res);
            return;
        }

        if (!nuevaContrasena.equals(confirmarContrasena)) {
            req.setAttribute("mensajeError", "Las contraseñas no coinciden.");
            req.getRequestDispatcher("/WEB-INF/restablecer-contrasena.jsp").forward(req, res);
            return;
        }

        ServiceUsuario servicioUsuario = new ServiceUsuario();

        if (!servicioUsuario.existeCorreo(correo.trim())) {
            req.setAttribute("mensajeError", "No existe ninguna cuenta con ese correo.");
            req.getRequestDispatcher("/WEB-INF/restablecer-contrasena.jsp").forward(req, res);
            return;
        }

        boolean actualizado = servicioUsuario.restablecerContrasena(correo.trim(), nuevaContrasena);

        if (actualizado) {
            res.sendRedirect(req.getContextPath() + "/index.jsp?exito=true");
        } else {
            req.setAttribute("mensajeError", "Ocurrió un error al restablecer la contraseña.");
            req.getRequestDispatcher("/WEB-INF/restablecer-contrasena.jsp").forward(req, res);
        }
    }
}
