package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanNotificacion;
import mx.edu.utez.pres.srde.model.BeanUsuario;
import mx.edu.utez.pres.srde.service.ServicioNotificacion;

import java.io.IOException;
import java.util.List;
@WebServlet(name = "servletMostrarNotificaciones", value = "/servlet-mostrar-notificaciones")
public class ServletMostrarNotificaciones extends HttpServlet {
    @Override
    public void doGet (HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        ServicioNotificacion servicioListaNotificaciones = new ServicioNotificacion();
        HttpSession session = req.getSession();
        BeanDocente docente = (BeanDocente) session.getAttribute("docenteLogueado");
        int idDocente = (docente != null) ? docente.getId() : 0;

        List<BeanNotificacion> listaNotificaciones = servicioListaNotificaciones.buscarListaNotificaciones(idDocente);
        req.setAttribute("listaNotificaciones", listaNotificaciones);
        if (listaNotificaciones != null && !listaNotificaciones.isEmpty()) {
            req.getRequestDispatcher("WEB-INF/Docente/notificaciones.jsp").forward(req, res);
        } else {
            System.out.println("Redireccionando al perfil por falta de datos.");
            req.setAttribute("mensajeNoti","No hay notificaciones");
            req.getRequestDispatcher("WEB-INF/Docente/notificaciones.jsp").forward(req, res);
        }
    }

    @Override
    public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ServicioNotificacion servicio = new ServicioNotificacion();
        String idParam = req.getParameter("idNoti");

        if (idParam != null && !idParam.isEmpty()) {
            int idNoti = Integer.parseInt(idParam);
            servicio.ocultarNotificacion(idNoti);
        }
        HttpSession session = req.getSession();
        BeanDocente docente = (BeanDocente) session.getAttribute("docenteLogueado");
        int idDocente = (docente != null) ? docente.getId() : 0;
        List<BeanNotificacion> listaNotificaciones = servicio.buscarListaNotificaciones(idDocente);
        req.setAttribute("listaNotificaciones", listaNotificaciones);

        if (listaNotificaciones != null && !listaNotificaciones.isEmpty()) {
            req.getRequestDispatcher("WEB-INF/Docente/notificaciones.jsp").forward(req, res);
        } else {
            req.setAttribute("mensajeNoti", "No hay notificaciones");
            req.getRequestDispatcher("WEB-INF/Docente/notificaciones.jsp").forward(req, res);
        }
    }
}

