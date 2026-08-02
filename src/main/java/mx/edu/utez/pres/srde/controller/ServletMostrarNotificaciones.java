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

        System.out.println("====== PRUEBA DE NOTIFICACIONES EN CONSOLA ======");
        System.out.println("🔍 BUSCANDO NOTIFICACIONES PARA EL ID_DOCENTE: " + idDocente);

        if (listaNotificaciones != null && !listaNotificaciones.isEmpty()) {
            for (BeanNotificacion noti : listaNotificaciones) {
                System.out.println("------------------------------------");
                System.out.println("Comentario: " + noti.getDescripcion());
                System.out.println("Fecha Límite: " + noti.getFechaLimite());
                System.out.println("ID Docente: " + noti.getId_usuario_docente());
            }
        } else {
            System.out.println("❌ No se encontraron notificaciones en consola.");
        }
        System.out.println("=================================================");

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

        // 1. Jalamos el ID que viene desde el <input type="hidden"> del formulario
        String idParam = req.getParameter("idNoti");

        if (idParam != null && !idParam.isEmpty()) {
            int idNoti = Integer.parseInt(idParam);
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

