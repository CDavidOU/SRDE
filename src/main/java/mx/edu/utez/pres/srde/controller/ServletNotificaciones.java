package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanNotificacion;
import mx.edu.utez.pres.srde.model.BeanPeriodo;
import mx.edu.utez.pres.srde.model.BeanTipoDocumento;

import mx.edu.utez.pres.srde.service.ServicioDocente;
import mx.edu.utez.pres.srde.service.ServicioNotificacion;
import mx.edu.utez.pres.srde.service.ServicioNotificaciones;
import mx.edu.utez.pres.srde.service.ServicioPeriodos;
import mx.edu.utez.pres.srde.service.ServicioTiposDocumento;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "servletNotificaciones", urlPatterns = {"/servlet-crear-notificacion", "/servlet-notificaciones"})
public class ServletNotificaciones extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path = req.getServletPath();

        if ("/servlet-crear-notificacion".equals(path)) {
            ServicioDocente datosDocente = new ServicioDocente();
            List<BeanDocente> listaDocentes = datosDocente.listaDocente();
            ServicioTiposDocumento servicioTiposDocumento = new ServicioTiposDocumento();
            List<BeanTipoDocumento> listaTiposDocs = servicioTiposDocumento.consultarTiposDocumento();

            req.setAttribute("listaTiposDocs", listaTiposDocs);
            req.setAttribute("docentesDisponibles", listaDocentes);
            req.getRequestDispatcher("/WEB-INF/Admin/programar-documentacion.jsp").forward(req, res);
        }
        else if ("/servlet-notificaciones".equals(path)) {
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ServicioPeriodos servicioPeriodos = new ServicioPeriodos();
        BeanPeriodo periodoActivo = servicioPeriodos.automatizacionPeriodos();
        req.setAttribute("periodoActivo", periodoActivo);

        String tipoDocParam = req.getParameter("tipoDoc");
        int tipoDocId = (tipoDocParam != null && !tipoDocParam.isEmpty()) ? Integer.parseInt(tipoDocParam) : 0;

        int docenteId = 0;
        if (req.getParameter("idDocenteSelect") != null && !req.getParameter("idDocenteSelect").isEmpty()) {
            docenteId = Integer.parseInt(req.getParameter("idDocenteSelect"));
        }

        String comentario = req.getParameter("comentario");
        String fechaLimite = req.getParameter("fechaLimite");

        BeanNotificacion creandoNotificacion = new BeanNotificacion();
        ServicioNotificacion servicioNotificacion = new ServicioNotificacion();
        creandoNotificacion.setDescripcion(comentario);

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

        // Prueba de datos
        System.out.println("====== PROBANDO DATOS ENTRANTES ======");
        System.out.println("Docente ID: " + creandoNotificacion.getId_usuario_docente());
        System.out.println("Comentario: " + creandoNotificacion.getDescripcion());
        System.out.println("Fecha Límite: " + creandoNotificacion.getFechaLimite());
        System.out.println("tipoDocumento: " + creandoNotificacion.getTipo_doc());
        System.out.println("Periodo ID: " + creandoNotificacion.getId_periodo());
        System.out.println("======================================");

        BeanNotificacion nuevaNoti = servicioNotificacion.crearNotificacion(creandoNotificacion);

        // AQUÍ ES DONDE ESTABA EL PROBLEMA (Se dejó limpio con una sola validación)
        if (nuevaNoti != null) {
            // ÉXITO: Guardamos un mensaje en la sesión y redirigimos a la lista de notificaciones
            req.getSession().setAttribute("mensajeExito", "¡Notificación enviada correctamente al docente!");
            res.sendRedirect(req.getContextPath() + "/servlet-notificaciones");
        } else {
            // ERROR: Si algo falla (ej. faltan datos), regresamos a la pantalla de redactar
            System.out.println("Faltan datos o falla algo en la notificacion");
            req.getSession().setAttribute("mensajeError", "Ocurrió un error al enviar la notificación. Verifica los datos.");
            res.sendRedirect(req.getContextPath() + "/servlet-enviar-mensaje");
        }
    }
}