package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.dao.DaoUsuario;
import mx.edu.utez.pres.srde.model.BeanAdmin;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanUsuario;

import java.io.IOException;
@WebServlet(name ="servletcambiarcontra", value = "/servlet-cambiar-contra")
public class ServletCambiarContra extends HttpServlet {
    // 1. MUESTRA LA VISTA DE CAMBIAR CONTRASEÑA (Para los enlaces <a href="...">)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession sesion = req.getSession(false);

        // Validamos que haya una sesión activa antes de dejarlo ver la pantalla
        if (sesion != null && (sesion.getAttribute("docenteLogueado") != null || sesion.getAttribute("adminLogueado") != null)) {
            // Hacemos forward al JSP que está dentro de WEB-INF
            req.getRequestDispatcher("/WEB-INF/cambiar-contrasena.jsp").forward(req, res);
        } else {
            // Si no está logueado, lo mandamos al inicio
            res.sendRedirect(req.getContextPath() + "/index.jsp");
        }
    }

    // 2. PROCESA EL FORMULARIO (Para cuando presiona el botón "Confirmar")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession sesion = req.getSession(false);

        if (sesion == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        // Recuperar los datos del formulario (deben coincidir con el 'name' en tu JSP)
        String passActual = req.getParameter("password");
        String passNueva = req.getParameter("newPassword");
        String passConfirm = req.getParameter("confirmPassword");

        // TODO: Aquí invocarás tu Servicio / DAO para:

        // Obtener los datos del usuario logueado desde la sesión
        // (Ejemplo si es un docente, obtienes su correo o ID)
        BeanDocente docente = (BeanDocente) sesion.getAttribute("docenteLogueado");
        BeanAdmin admin = (BeanAdmin) sesion.getAttribute("adminLogueado");

        DaoUsuario daoUsuario = new DaoUsuario();

        BeanUsuario usuarioValido = null;

        if(docente != null){
            usuarioValido = daoUsuario.verificarUsuario(docente.getCorreo(),passActual);
        }else if (admin != null){
            usuarioValido = daoUsuario.verificarUsuario(admin.getCorreo(),passActual);
        }

        if (usuarioValido == null) {
            req.setAttribute("mensajeError", "La contraseña actual es incorrecta.");
            req.getRequestDispatcher("/WEB-INF/cambiar-contrasena.jsp").forward(req, res);
            return;
        }

        if(passNueva != null && !passNueva.equals(passConfirm)){
            req.setAttribute("mensajeError","Las contraseñas no coinciden");
            req.getRequestDispatcher("/WEB-INF/cambiar-contrasena.jsp").forward(req, res);
            return;
        }

        boolean actualizado = daoUsuario.cambiarContrasena(usuarioValido.getId(), passNueva);

        if (actualizado){
            sesion.invalidate();

            res.sendRedirect(req.getContextPath() + "/index.jsp?exito=true");
            return;
        } else {
            req.setAttribute("mensajeError", "Ocurrio un error al actualizar la contraseña");
            req.getRequestDispatcher("/WEB-INF/cambiar-contrasena.jsp").forward(req, res);
        }
    }
}