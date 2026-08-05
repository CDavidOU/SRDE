package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanEstadistica;
import mx.edu.utez.pres.srde.model.BeanPeriodo;
import mx.edu.utez.pres.srde.service.ServicioDocente;
import mx.edu.utez.pres.srde.service.ServicioGrafica;
import mx.edu.utez.pres.srde.service.ServicioPeriodos;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "servletadmingrafica", value = "/servlet-admin-grafica")
public class ServletAdminGrafica extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession sesion = req.getSession(false);

        if (sesion == null || sesion.getAttribute("adminLogueado") == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        ServicioPeriodos servicioPeriodos = new ServicioPeriodos();
        BeanPeriodo periodoActual = servicioPeriodos.automatizacionPeriodos();

        ServicioDocente servicioDocente = new ServicioDocente();
        List<BeanDocente> listaDocentes = servicioDocente.listaDocentes(periodoActual.getId_periodo());

        ServicioGrafica servicioGrafica = new ServicioGrafica();
        int totalEstudiantesActivos = servicioGrafica.contarEstudiantesActivos();
        int totalDocentes = servicioGrafica.contarDocentes();
        List<BeanEstadistica> listaCarreras = servicioGrafica.estudiantesPorCarrera();

        int maxAlumnosPorDocente = 1;
        if (listaDocentes != null) {
            for (BeanDocente docente : listaDocentes) {
                if (docente.getNumAlumnos() > maxAlumnosPorDocente) {
                    maxAlumnosPorDocente = docente.getNumAlumnos();
                }
            }
        }

        int maxAlumnosPorCarrera = 1;
        if (listaCarreras != null) {
            for (BeanEstadistica estadistica : listaCarreras) {
                if (estadistica.getCantidad() > maxAlumnosPorCarrera) {
                    maxAlumnosPorCarrera = estadistica.getCantidad();
                }
            }
        }

        req.setAttribute("periodoActual", periodoActual);
        req.setAttribute("totalEstudiantesActivos", totalEstudiantesActivos);
        req.setAttribute("totalDocentes", totalDocentes);
        req.setAttribute("listaDocentes", listaDocentes);
        req.setAttribute("listaCarreras", listaCarreras);
        req.setAttribute("maxAlumnosPorDocente", maxAlumnosPorDocente);
        req.setAttribute("maxAlumnosPorCarrera", maxAlumnosPorCarrera);

        req.getRequestDispatcher("WEB-INF/Admin/grafica.jsp").forward(req, res);
    }
}
