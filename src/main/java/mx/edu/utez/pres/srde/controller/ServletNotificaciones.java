package mx.edu.utez.pres.srde.controller;

import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanNotificacion;
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
import mx.edu.utez.pres.srde.service.ServicioDocente;
import mx.edu.utez.pres.srde.service.ServicioNotificaciones;
import mx.edu.utez.pres.srde.service.ServicioPeriodos;
import mx.edu.utez.pres.srde.service.ServicioTiposDocumento;

=======
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
import java.io.IOException;
import java.util.List;

<<<<<<< HEAD
@WebServlet(name = "servletNotificaciones", urlPatterns = {"/servlet-crear-notificacion", "/servlet-notificaciones"})
=======
@WebServlet(name = "NotificacionesServlet", value = {"/servlet-notificaciones", "/servlet-notificaciones-admin"})
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
public class ServletNotificaciones extends HttpServlet {

    private final ServicioNotificaciones servicioNotificaciones = new ServicioNotificaciones();
    private final ServicioPeriodos servicioPeriodos = new ServicioPeriodos();

<<<<<<< HEAD
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession sesion = req.getSession(false);

        // Validación de sesión
        if (sesion == null || (sesion.getAttribute("docenteLogueado") == null && sesion.getAttribute("adminLogueado") == null)) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        String path = req.getServletPath();

        // =========================================================================
        // RUTA 1: VISTA PARA CREAR NOTIFICACIONES (ADMINISTRADOR)
        // =========================================================================
        if ("/servlet-crear-notificacion".equals(path)) {
            ServicioDocente datosDocente = new ServicioDocente();
            List<BeanDocente> listaDocentes = datosDocente.listaDocente();

            ServicioTiposDocumento servicioTiposDocumento = new ServicioTiposDocumento();
            List<BeanTipoDocumento> listaTiposDocs = servicioTiposDocumento.consultarTiposDocumento();

            req.setAttribute("listaTiposDocs", listaTiposDocs);
            req.setAttribute("docentesDisponibles", listaDocentes);
            req.getRequestDispatcher("/WEB-INF/Admin/programar-documentacion.jsp").forward(req, res);
        }
        // =========================================================================
        // RUTA 2: LISTAR LAS NOTIFICACIONES (DOCENTE Y ADMIN)
        // =========================================================================
        else if ("/servlet-notificaciones".equals(path)) {
            if (sesion.getAttribute("docenteLogueado") != null) {
                // Lógica Docente
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
                // Lógica Admin
                List<BeanNotificacion> listaPendientesGlobal = servicioNotificaciones.listarPendientesGlobal();
                req.setAttribute("listaPendientes", listaPendientesGlobal);
            }

            req.getRequestDispatcher("/WEB-INF/Plantillas/notificaciones.jsp").forward(req, res);
=======
    public ServletNotificaciones() {
        super();
    }

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
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
        }

        req.getRequestDispatcher("/WEB-INF/Plantillas/notificaciones.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession sesion = req.getSession(false);
<<<<<<< HEAD
        if (sesion == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        String path = req.getServletPath();

        // =========================================================================
        // RUTA 1: ACCIÓN PARA OCULTAR UNA NOTIFICACIÓN (DOCENTE)
        // =========================================================================
        if ("/servlet-notificaciones".equals(path)) {
            if (sesion.getAttribute("docenteLogueado") != null) {
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
        // =========================================================================
        // RUTA 2: ACCIÓN PARA CREAR Y GUARDAR UNA NOTIFICACIÓN (ADMINISTRADOR)
        // =========================================================================
        else if ("/servlet-crear-notificacion".equals(path)) {
            BeanPeriodo periodoActivo = servicioPeriodos.automatizacionPeriodos();
            BeanNotificacion creandoNotificacion = new BeanNotificacion();

            // Recibimos los parámetros del formulario
            String paramTipoDoc = req.getParameter("tipo_doc");
            int tipoDocId = (paramTipoDoc != null && !paramTipoDoc.isEmpty()) ? Integer.parseInt(paramTipoDoc) : 0;

            String fechaLimite = req.getParameter("fechaLimite");

            String paramDocente = req.getParameter("id_usuario_docente");
            int docenteId = (paramDocente != null && !paramDocente.isEmpty()) ? Integer.parseInt(paramDocente) : 0;

            String descripcion = req.getParameter("descripcion");

            // Llenamos el Bean
            creandoNotificacion.setDescripcion(descripcion);

            if (tipoDocId != 0) {
                creandoNotificacion.setTipo_doc(tipoDocId);
            }
            if (fechaLimite != null && !fechaLimite.trim().isEmpty()) {
                creandoNotificacion.setFechaLimite(Date.valueOf(LocalDate.parse(fechaLimite)));
            }
            if (periodoActivo != null) {
                creandoNotificacion.setId_periodo(periodoActivo.getId_periodo());
            }
            if (docenteId != 0) {
                creandoNotificacion.setId_usuario_docente(docenteId);
            }

            // Prueba de datos en consola
            System.out.println("====== PROBANDO DATOS ENTRANTES ======");
            System.out.println("Docente ID: " + creandoNotificacion.getId_usuario_docente());
            System.out.println("Comentario: " + creandoNotificacion.getDescripcion());
            System.out.println("Fecha Límite: " + creandoNotificacion.getFechaLimite());
            System.out.println("tipoDocumento: " + creandoNotificacion.getTipo_doc());
            System.out.println("Periodo ID: " + creandoNotificacion.getId_periodo());
            System.out.println("======================================");

            // Guardamos en Base de Datos
            BeanNotificacion nuevaNoti = servicioNotificaciones.crearNotificacion(creandoNotificacion);

            if (nuevaNoti != null) {
                // ÉXITO: Guardamos un mensaje en la sesión y redirigimos a la lista de notificaciones
                req.getSession().setAttribute("mensajeExito", "¡Notificación enviada correctamente al docente!");
                res.sendRedirect(req.getContextPath() + "/servlet-notificaciones");
            } else {
                // ERROR: Si algo falla (ej. faltan datos), regresamos a la pantalla de redactar
                System.out.println("Faltan datos o falla algo en la notificacion");
                req.getSession().setAttribute("mensajeError", "Ocurrió un error al enviar la notificación. Verifica los datos.");
                res.sendRedirect(req.getContextPath() + "/servlet-crear-notificacion");
            }
        }
=======

        if (sesion != null && sesion.getAttribute("docenteLogueado") != null) {
            BeanDocente docenteLogueado = (BeanDocente) sesion.getAttribute("docenteLogueado");

            String idCalParam = req.getParameter("idCalendario");
            int idCalendario = (idCalParam != null && !idCalParam.isEmpty()) ? Integer.parseInt(idCalParam) : 0;

            if (idCalendario > 0) {
                NotificacionBean notifToHide = new NotificacionBean();
                notifToHide.setId_docente(docenteLogueado.getId());
                notifToHide.setIdCalendario(idCalendario);

                servicioNotificaciones.ocultarNotificacion(notifToHide);
            }
        }

        res.sendRedirect(req.getContextPath() + "/servlet-notificaciones");
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
    }
}