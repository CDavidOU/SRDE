package mx.edu.utez.pres.srde.controller;

import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanPeriodo;
import mx.edu.utez.pres.srde.model.CalendarioBean;
import mx.edu.utez.pres.srde.model.NotificacionBean;
import mx.edu.utez.pres.srde.service.ServicioNotificaciones;
import mx.edu.utez.pres.srde.service.ServicioPeriodos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
<<<<<<< HEAD
import mx.edu.utez.pres.srde.model.*;
import mx.edu.utez.pres.srde.service.ServicioNotificaciones;
import mx.edu.utez.pres.srde.service.ServicioPeriodos;

import java.io.IOException;
import java.util.List;@WebServlet(name = "servletMostrarNotificaciones", value = "/servlet-mostrar-notificaciones")
=======
import java.io.IOException;
import java.util.List;

@WebServlet(name = "servletMostrarNotificaciones", value = "/servlet-mostrar-notificaciones")
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
public class ServletMostrarNotificaciones extends HttpServlet {

    private final ServicioNotificaciones servicioNotificaciones = new ServicioNotificaciones();
    private final ServicioPeriodos servicioPeriodos = new ServicioPeriodos();

<<<<<<< HEAD
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        BeanDocente docente = (session != null) ? (BeanDocente) session.getAttribute("docenteLogueado") : null;

        // Validamos que el docente esté en sesión
        if (docente == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        // Obtener periodo activo para filtrar los calendarios
        BeanPeriodo periodoActivo = servicioPeriodos.automatizacionPeriodos();
        int idPeriodo = (periodoActivo != null) ? periodoActivo.getId_periodo() : 0;

        // Consultamos la lista de calendarios/avisos asignados a ese periodo y no ocultados
        List<CalendarioBean> listaNotificaciones = servicioNotificaciones.buscarListaNotificaciones(docente.getId(), idPeriodo);

        req.setAttribute("listaNotificaciones", listaNotificaciones);

        if (listaNotificaciones == null || listaNotificaciones.isEmpty()) {
            req.setAttribute("mensajeNoti", "No hay notificaciones disponibles por el momento.");
        }

        req.getRequestDispatcher("/WEB-INF/Docente/notificaciones.jsp").forward(req, res);
    }

    @Override
=======
    public ServletMostrarNotificaciones() {
        super();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        BeanDocente docente = (session != null) ? (BeanDocente) session.getAttribute("docenteLogueado") : null;

        if (docente == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        BeanPeriodo periodoActivo = servicioPeriodos.automatizacionPeriodos();
        int idPeriodo = (periodoActivo != null) ? periodoActivo.getId_periodo() : 0;

        List<CalendarioBean> listaNotificaciones = servicioNotificaciones.buscarListaNotificaciones(docente.getId(), idPeriodo);
        req.setAttribute("listaNotificaciones", listaNotificaciones);

        if (listaNotificaciones == null || listaNotificaciones.isEmpty()) {
            req.setAttribute("mensajeNoti", "No hay notificaciones disponibles por el momento.");
        }

        req.getRequestDispatcher("/WEB-INF/Docente/notificaciones.jsp").forward(req, res);
    }

    @Override
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        BeanDocente docente = (session != null) ? (BeanDocente) session.getAttribute("docenteLogueado") : null;

        if (docente == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        String idParam = req.getParameter("idNoti");
<<<<<<< HEAD
        String accion = req.getParameter("accion"); // "ocultar" o "restaurar"
=======
        String accion = req.getParameter("accion");
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f

        if (idParam != null && !idParam.isEmpty()) {
            int idCalendario = Integer.parseInt(idParam);

            NotificacionBean notif = new NotificacionBean();
            notif.setId_docente(docente.getId());
            notif.setIdCalendario(idCalendario);

<<<<<<< HEAD
            ServicioNotificaciones servicio = new ServicioNotificaciones();

            if ("restaurar".equals(accion)) {
                servicio.desocultarNotificacion(notif);
            } else {
                servicio.ocultarNotificacion(notif);
=======
            if ("restaurar".equals(accion)) {
                servicioNotificaciones.desocultarNotificacion(notif);
            } else {
                servicioNotificaciones.ocultarNotificacion(notif);
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
            }
        }

        res.sendRedirect(req.getContextPath() + "/servlet-mostrar-notificaciones");
    }
}