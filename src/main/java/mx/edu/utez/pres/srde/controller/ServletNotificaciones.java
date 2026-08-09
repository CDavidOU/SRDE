package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import mx.edu.utez.pres.srde.model.*;

import mx.edu.utez.pres.srde.service.ServicioNotificaciones;
import mx.edu.utez.pres.srde.service.ServicioPeriodos;

import java.io.IOException;
import java.util.List;

// Mapeamos el Servlet a ambas URLs para manejar ambas funcionalidades
// Cambiar en NotificacionesServlet.java
@WebServlet(name = "NotificacionesServlet", value = "/servlet-notificaciones-admin")
public class ServletNotificaciones extends HttpServlet {
    @WebServlet(name = "NotificacionesServlet", value = "/servlet-notificaciones")
    public class NotificacionesServlet extends HttpServlet {

        private final ServicioNotificaciones servicioNotificaciones = new ServicioNotificaciones();
        private final ServicioPeriodos servicioPeriodos = new ServicioPeriodos();

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            HttpSession sesion = req.getSession(false);

            if (sesion == null || (sesion.getAttribute("docenteLogueado") == null && sesion.getAttribute("adminLogueado") == null)) {
                res.sendRedirect(req.getContextPath() + "/index.jsp");
                return;
            }
            if (sesion.getAttribute("docenteLogueado") != null) {
                BeanDocente docenteLogueado = (BeanDocente) sesion.getAttribute("docenteLogueado");
                BeanPeriodo periodoActivo = servicioPeriodos.automatizacionPeriodos();

                if (periodoActivo != null) {
                    List<CalendarioBean> listaAvisosCalendario = servicioNotificaciones.buscarListaNotificaciones(
                            docenteLogueado.getId(),
                            periodoActivo.getId_periodo()
                    );
                    req.setAttribute("listaAvisosCalendario", listaAvisosCalendario);
                }
                List<BeanNotificacion> listaPendientes = servicioNotificaciones.listarPendientesDocente(docenteLogueado.getId());
                req.setAttribute("listaPendientes", listaPendientes);

            } else {
                List<BeanNotificacion> listaPendientesGlobal = servicioNotificaciones.listarPendientesGlobal();
                req.setAttribute("listaPendientes", listaPendientesGlobal);
            }

            req.getRequestDispatcher("/WEB-INF/Plantillas/notificaciones.jsp").forward(req, res);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            HttpSession sesion = req.getSession(false);

            if (sesion != null && sesion.getAttribute("docenteLogueado") != null) {
                BeanDocente docenteLogueado = (BeanDocente) sesion.getAttribute("docenteLogueado");

                String idCalParam = req.getParameter("idCalendario");
                int idCalendario = (idCalParam != null && !idCalParam.isEmpty()) ? Integer.parseInt(idCalParam) : 0;

                if (idCalendario > 0) {
                    NotificacionBean notifToHide = new NotificacionBean();
                    notifToHide.setId_docente(docenteLogueado.getId());
                    notifToHide.setIdCalendario(idCalendario);

                    // Ejecuta la inserción/actualización en NOTIFICACION_DOCENTE con VISTO = 0
                    servicioNotificaciones.ocultarNotificacion(notifToHide);
                }
            }

            res.sendRedirect(req.getContextPath() + "/servlet-notificaciones");
        }
    }
}