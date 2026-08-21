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
import java.io.IOException;
import java.util.List;

@WebServlet(name = "servletMostrarNotificaciones", value = "/servlet-mostrar-notificaciones")
public class ServletMostrarNotificaciones extends HttpServlet {

    private final ServicioNotificaciones servicioNotificaciones = new ServicioNotificaciones();
    private final ServicioPeriodos servicioPeriodos = new ServicioPeriodos();

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
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        BeanDocente docente = (session != null) ? (BeanDocente) session.getAttribute("docenteLogueado") : null;

        if (docente == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        String idParam = req.getParameter("idNoti");
        String accion = req.getParameter("accion");

        if (idParam != null && !idParam.isEmpty()) {
            int idCalendario = Integer.parseInt(idParam);

            NotificacionBean notif = new NotificacionBean();
            notif.setId_docente(docente.getId());
            notif.setIdCalendario(idCalendario);

            if ("restaurar".equals(accion)) {
                servicioNotificaciones.desocultarNotificacion(notif);
            } else {
                servicioNotificaciones.ocultarNotificacion(notif);
            }
        }

        res.sendRedirect(req.getContextPath() + "/servlet-mostrar-notificaciones");
    }
}