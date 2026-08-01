package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanNotificacion;
import mx.edu.utez.pres.srde.model.BeanPeriodo;
import mx.edu.utez.pres.srde.service.ServicioDocente;
import mx.edu.utez.pres.srde.service.ServicioNotificacion;
import mx.edu.utez.pres.srde.service.ServicioPeriodos;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "servletNotificaciones",value = "/servlet-crear-notificacion")
public class ServletNotificaciones extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
        ServicioDocente datosDocente = new ServicioDocente();
        List<BeanDocente> listaDocentes = datosDocente.listaDocente();
        req.setAttribute("docentesDisponibles", listaDocentes);
        req.getRequestDispatcher("/WEB-INF/Admin/programar-notificacion.jsp").forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
        ServicioPeriodos servicioPeriodos = new ServicioPeriodos();
        BeanPeriodo periodoActivo = servicioPeriodos.automatizacionPeriodos();
        req.setAttribute("periodoActivo", periodoActivo);

        int docenteId = Integer.parseInt(req.getParameter("idDocenteSelect"));
        String comentario =req.getParameter("comentario");
        String fechaLimite =req.getParameter("fechaLimite");
        BeanNotificacion creandoNotificacion = new BeanNotificacion();
        ServicioNotificacion servicioNotificacion = new ServicioNotificacion();
        creandoNotificacion.setDescripcion(comentario);
        if (fechaLimite != null && !fechaLimite.trim().isEmpty()) {
            creandoNotificacion.setFechaLimite(Date.valueOf(LocalDate.parse(fechaLimite)));
        }
        if (periodoActivo != null) {
            creandoNotificacion.setId_periodo(periodoActivo.getId_periodo()); // O como se llame tu getter en BeanPeriodo
        }
        if (docenteId!=0) {
            creandoNotificacion.setId_usuario_docente((docenteId));
        }
        //Prueba de datos notificaciones
        System.out.println("====== PROBANDO DATOS ENTRANTES ======");
        System.out.println("Docente ID: " + creandoNotificacion.getId_usuario_docente());
        System.out.println("Comentario: " + creandoNotificacion.getDescripcion());
        System.out.println("Fecha Límite: " + creandoNotificacion.getFechaLimite());
        System.out.println("tipoDocumento: " + creandoNotificacion.getTipo_doc());
        System.out.println("Periodo ID: " + creandoNotificacion.getId_periodo());
        System.out.println("======================================");
        BeanNotificacion nuevaNoti=servicioNotificacion.crearNotificacion(creandoNotificacion);
        if (nuevaNoti != null) {
            req.getRequestDispatcher("WEB-INF/Admin/perfil.jsp");
        } else {
            System.out.println("faltan datos o falla algo en la notificacion");
            req.getRequestDispatcher("WEB-INF/Admin/programar-notificacion.jsp");
        }
    }
}

