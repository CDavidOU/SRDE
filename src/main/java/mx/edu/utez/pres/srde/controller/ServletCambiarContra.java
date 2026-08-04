package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.BeanAdmin;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanPersona;
import mx.edu.utez.pres.srde.model.BeanUsuario;
import mx.edu.utez.pres.srde.service.ServiceUsuario;

import java.io.IOException;

@WebServlet(name ="servletcambiarcontra", value = "/servlet-cambiar-contra")
public class ServletCambiarContra extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession sesion = req.getSession(false);

        if (sesion != null && (sesion.getAttribute("docenteLogueado") != null || sesion.getAttribute("adminLogueado") != null)) {
            req.getRequestDispatcher("/WEB-INF/cambiar-contrasena.jsp").forward(req, res);
        } else {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession sesion = req.getSession(false);

        if (sesion == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        String passActual = req.getParameter("password");
        String passNueva = req.getParameter("newPassword");
        String passConfirm = req.getParameter("confirmPassword");

        BeanDocente docente = (BeanDocente) sesion.getAttribute("docenteLogueado");
        BeanAdmin admin = (BeanAdmin) sesion.getAttribute("adminLogueado");

        // Obtenemos el correo del usuario en sesión
        String correo = "";
        int idUsuario = 0;
        if (docente != null) {
            correo = docente.getCorreo();
            idUsuario = docente.getId();
        } else if (admin != null) {
            correo = admin.getCorreo();
            idUsuario = admin.getId();
        }

        ServiceUsuario servicioUsuario = new ServiceUsuario();

        // Llamamos al método usando el objeto BeanUsuario (coincide con tu Service)
        BeanUsuario usuarioValido = servicioUsuario.verificarUsuario(correo, passActual);

        if (usuarioValido == null) {
            req.setAttribute("mensajeError", "La contraseña actual es incorrecta.");
            req.getRequestDispatcher("/WEB-INF/cambiar-contrasena.jsp").forward(req, res);
            return;
        }

        if (passNueva != null && !passNueva.equals(passConfirm)) {
            req.setAttribute("mensajeError", "Las contraseñas no coinciden");
            req.getRequestDispatcher("/WEB-INF/cambiar-contrasena.jsp").forward(req, res);
            return;
        }

        boolean actualizado = servicioUsuario.cambiarContrasena(idUsuario, passNueva);

        if (actualizado) {
            sesion.invalidate();
            res.sendRedirect(req.getContextPath() + "/index.jsp?exito=true");
        } else {
            req.setAttribute("mensajeError", "Ocurrió un error al actualizar la contraseña");
            req.getRequestDispatcher("/WEB-INF/cambiar-contrasena.jsp").forward(req, res);
        }
    }
}