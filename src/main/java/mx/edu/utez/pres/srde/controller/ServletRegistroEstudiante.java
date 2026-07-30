package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanEstudiante;
import mx.edu.utez.pres.srde.model.BeanPeriodo;
import mx.edu.utez.pres.srde.service.ServicioAsignacionEstadias;
import mx.edu.utez.pres.srde.service.ServicioPeriodos;
import mx.edu.utez.pres.srde.service.ServicioRegistroEstudiante;

import java.io.IOException;

@WebServlet(name = "servletRegistroEstudiantes", value = "/servlet-registro-estudiante")
public class ServletRegistroEstudiante extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession sesion = req.getSession(false);
        if (sesion == null || sesion.getAttribute("docenteLogueado") == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }
        req.getRequestDispatcher("WEB-INF/Docente/registro-estudiantes.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession sesion = req.getSession(false);
        if (sesion == null || sesion.getAttribute("docenteLogueado") == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        BeanDocente docenteLogueado = (BeanDocente) sesion.getAttribute("docenteLogueado");
        int idDocente = docenteLogueado.getId();

        String strCuatrimestre = req.getParameter("cuatrimestre");
        int cuatrimestre = 0;
        if (strCuatrimestre != null && !strCuatrimestre.trim().isEmpty()) {
            try {
                cuatrimestre = Integer.parseInt(strCuatrimestre.trim());
            } catch (NumberFormatException e) {
                System.err.println("Error parseando cuatrimestre: " + e.getMessage());
            }
        }

        BeanEstudiante estudiante = new BeanEstudiante();
        estudiante.setNombre(req.getParameter("nombre"));
        estudiante.setApellido(req.getParameter("apellido"));
        estudiante.setCarrera(req.getParameter("carrera"));
        estudiante.setCuatrimestre(cuatrimestre);
        estudiante.setMatricula(req.getParameter("matricula"));
        estudiante.setCorreo(req.getParameter("correo"));

        ServicioPeriodos servicioPeriodos = new ServicioPeriodos();
        BeanPeriodo periodoActual = servicioPeriodos.automatizacionPeriodos();

        // El servicio invoca a existeMatricula internamente
        ServicioRegistroEstudiante nuevoRegistro = new ServicioRegistroEstudiante();
        BeanEstudiante registroEstudiante = nuevoRegistro.registrarEstudiante(estudiante);

        // En ServletRegistroEstudiante.java (dentro del doPost):
        if (registroEstudiante != null) {
            ServicioAsignacionEstadias servicioAsignacionEstadias = new ServicioAsignacionEstadias();
            servicioAsignacionEstadias.registroAsignacionEstadias(
                    idDocente,
                    periodoActual.getId_periodo(),
                    registroEstudiante.getMatricula()
            );

            // GUARDAR EN SESIÓN PARA SOBREVIVIR AL REDIRECT
            sesion.setAttribute("mensajeOk", "Se ha ingresado el estudiante correctamente.");
            res.sendRedirect(req.getContextPath() + "/servlet-lista-estudiantes");
        } else {
            req.setAttribute("mensajeError", "Error: La matrícula ya existe o faltan campos obligatorios.");
            req.getRequestDispatcher("WEB-INF/Docente/registro-estudiantes.jsp").forward(req, res);
        }
    }
}