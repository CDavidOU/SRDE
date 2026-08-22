package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.BeanAdmin;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanUsuario;
import mx.edu.utez.pres.srde.service.ServiceUsuario;
import mx.edu.utez.pres.srde.service.ServicioAdmin;
import mx.edu.utez.pres.srde.service.ServicioDocente;

import java.io.IOException;
@WebServlet(name = "servletinicio", value = "/servlet-inicio")
public class ServletInicio extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession sesion = req.getSession(false);

        if (sesion != null) {
            if (sesion.getAttribute("docenteLogueado") != null) {
                req.getRequestDispatcher("/WEB-INF/Docente/perfilDocente.jsp").forward(req, res);
                return;
            } else if (sesion.getAttribute("adminLogueado") != null) {
                req.getRequestDispatcher("/WEB-INF/Admin/perfil.jsp").forward(req, res);
                return;
            }
        }

        res.sendRedirect(req.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        String correoStr = req.getParameter("correoUsuario");
        String passwordStr = req.getParameter("password");

        ServiceUsuario servicioUsuario = new ServiceUsuario();
        BeanUsuario usuarioLogueado = servicioUsuario.verificarUsuario(correoStr, passwordStr);

        if (usuarioLogueado != null) {
            // Invalidar sesión previa
            HttpSession sesionPrevia = req.getSession(false);
            if (sesionPrevia != null) {
                sesionPrevia.invalidate();
            }

            // Nueva sesión limpia
            HttpSession sesion = req.getSession(true);

            if ("Administrador".equals(usuarioLogueado.getRol())) {
                ServicioAdmin servicioAdmin = new ServicioAdmin();
                BeanAdmin admin = servicioAdmin.datosAdmin(usuarioLogueado.getId());
                sesion.setAttribute("adminLogueado", admin);

            } else {
                ServicioDocente serviceDocente = new ServicioDocente();
                BeanDocente datosDocente = serviceDocente.datosDocente(usuarioLogueado.getId());
                sesion.setAttribute("docenteLogueado", datosDocente);
            }
            res.sendRedirect(req.getContextPath() + "/servlet-inicio");

        } else {
<<<<<<< HEAD
            req.setAttribute("mensajeError", "Credenciales incorrectas o cuenta inactiva.");
=======
            req.setAttribute("mensajeError", "Usuario o contraseña incorrectos, o cuenta inactiva.");
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
            req.getRequestDispatcher("/index.jsp").forward(req, res);
        }
    }
}