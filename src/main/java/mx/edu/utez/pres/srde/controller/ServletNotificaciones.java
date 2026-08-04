package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanNotificacion;
import mx.edu.utez.pres.srde.service.ServicioNotificaciones;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "servletnotificaciones", value = "/servlet-notificaciones")
public class ServletNotificaciones extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession sesion = req.getSession(false);

        if (sesion == null || (sesion.getAttribute("docenteLogueado") == null && sesion.getAttribute("adminLogueado") == null)) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        ServicioNotificaciones servicioNotificaciones = new ServicioNotificaciones();
        List<BeanNotificacion> listaNotificaciones;

        if (sesion.getAttribute("docenteLogueado") != null) {
            BeanDocente docenteLogueado = (BeanDocente) sesion.getAttribute("docenteLogueado");
            listaNotificaciones = servicioNotificaciones.listarPendientesDocente(docenteLogueado.getId());
        } else {
            listaNotificaciones = servicioNotificaciones.listarPendientesGlobal();
        }

        if (listaNotificaciones != null && !listaNotificaciones.isEmpty()) {
            req.setAttribute("listaNotificaciones", listaNotificaciones);
        }

        req.getRequestDispatcher("/WEB-INF/Plantillas/notificaciones.jsp").forward(req, res);
    }
}
