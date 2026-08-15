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
        // Atrapamos el correo que viene de la redirección y lo mandamos a la vista
        req.setAttribute("correo", req.getParameter("correo"));
        req.getRequestDispatcher("/WEB-INF/restablecer-contrasena.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String correo = req.getParameter("correo");
        String token = req.getParameter("token"); // Atrapamos el PIN de 4 dígitos
        String nuevaContrasena = req.getParameter("nuevaContrasena");
        String confirmarContrasena = req.getParameter("confirmarContrasena");

        // Verificamos que no falte ningún dato, incluyendo el token
        if (correo == null || correo.trim().isEmpty()
                || token == null || token.trim().isEmpty()
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

        // VALIDACIÓN DE SEGURIDAD: Comprobamos si el PIN coincide en la base de datos
        if (!servicioUsuario.validarToken(correo.trim(), token.trim())) {
            req.setAttribute("mensajeError", "El PIN de recuperación es incorrecto.");
            req.getRequestDispatcher("/WEB-INF/restablecer-contrasena.jsp").forward(req, res);
            return;
        }

        // Si el PIN es correcto, procedemos a cambiar la contraseña
        boolean actualizado = servicioUsuario.restablecerContrasena(correo.trim(), nuevaContrasena);

        if (actualizado) {
            res.sendRedirect(req.getContextPath() + "/index.jsp?exito=true");
        } else {
            req.setAttribute("mensajeError", "Ocurrió un error al guardar la nueva contraseña.");
            req.getRequestDispatcher("/WEB-INF/restablecer-contrasena.jsp").forward(req, res);
        }
    }
}