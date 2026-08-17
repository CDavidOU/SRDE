package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.BeanAdmin;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanEstudiante;
import mx.edu.utez.pres.srde.model.BeanPeriodo;
import mx.edu.utez.pres.srde.service.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "servletRegistroEstudiantes", value = "/servlet-registro-estudiante")
public class ServletRegistroEstudiante extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession sesion = req.getSession(false);
        if (sesion == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        BeanAdmin adminLogueado = (BeanAdmin) sesion.getAttribute("adminLogueado");
        BeanDocente docenteLogueado = (BeanDocente) sesion.getAttribute("docenteLogueado");
        //dependiendo si es admin o docente lo enviara a la vista puesta
        if (adminLogueado == null && docenteLogueado == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        if (adminLogueado != null) {
            ServicioDocente servicioDocente = new ServicioDocente();
            List<BeanDocente> listaDocente = servicioDocente.listaDocente();
            req.setAttribute("listaDocente", listaDocente);
            req.getRequestDispatcher("WEB-INF/Admin/registro-estudiantes-admin.jsp").forward(req, res);
        } else {
            req.getRequestDispatcher("WEB-INF/Docente/registro-estudiantes.jsp").forward(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession sesion = req.getSession(false);
        if (sesion == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        BeanAdmin adminLogueado = (BeanAdmin) sesion.getAttribute("adminLogueado");
        BeanDocente docenteLogueado = (BeanDocente) sesion.getAttribute("docenteLogueado");

        if (adminLogueado == null && docenteLogueado == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        int idDocenteDestino = 0;
        if (docenteLogueado != null) {
            idDocenteDestino = docenteLogueado.getId();
        } else if (adminLogueado != null) {
            String strIdDocente = req.getParameter("idDocenteAsignado");
            if (strIdDocente != null && !strIdDocente.trim().isEmpty()) {
                try {
                    idDocenteDestino = Integer.parseInt(strIdDocente.trim());
                } catch (NumberFormatException e) {
                    System.err.println("Error al parsear el ID del docente: " + e.getMessage());
                }
            }
        }

        ServicioPeriodos servicioPeriodos = new ServicioPeriodos();
        BeanPeriodo periodoActual = servicioPeriodos.automatizacionPeriodos();

        if (idDocenteDestino > 0 && periodoActual != null) {
            ServicioAsignacionEstadias servicioAsignacion = new ServicioAsignacionEstadias();
            int totalActual = servicioAsignacion.contarEstudiantes(idDocenteDestino, periodoActual.getId_periodo(), null);

            if (totalActual >= 10) {
                req.setAttribute("mensajeError", "El docente seleccionado ya ha alcanzado el límite máximo permitido (10 estudiantes).");
                recargarVistaFormulario(req, res, adminLogueado);
                return;
            }
        }

        // Parseo de cuatrimestre
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
        estudiante.setCorreo(req.getParameter("correo"));
        estudiante.setCuatrimestre(cuatrimestre);
        estudiante.setMatricula(req.getParameter("matricula"));
        estudiante.setGrupo(req.getParameter("grupo"));

        ServicioRegistroEstudiante nuevoRegistro = new ServicioRegistroEstudiante();
        BeanEstudiante registroEstudiante = null;
        String errorOracle = null;

        try {
            registroEstudiante = nuevoRegistro.registrarEstudiante(estudiante);
        } catch (Exception e) {
            errorOracle = e.getMessage();
            System.err.println("Error capturado al insertar en BD: " + errorOracle);
        }

        if (registroEstudiante != null) {
            boolean asignadoConExito = false;

            if (idDocenteDestino > 0 && periodoActual != null) {
                ServicioAsignacionEstadias servicioAsignacionEstadias = new ServicioAsignacionEstadias();

                int idAsignacionGenerada = servicioAsignacionEstadias.registroAsignacionEstadias(
                        idDocenteDestino,
                        periodoActual.getId_periodo(),
                        registroEstudiante.getMatricula()
                );

                if (idAsignacionGenerada > 0) {
                    asignadoConExito = true;
                    ServicioDocumento servicioDoc = new ServicioDocumento();
                    servicioDoc.inicializarDocumento(idAsignacionGenerada, idDocenteDestino);
                }
            }

            if (asignadoConExito) {
                sesion.setAttribute("mensajeOk", "Se ha ingresado el estudiante correctamente.");
                if (adminLogueado != null) {
                    res.sendRedirect(req.getContextPath() + "/servlet-admin-estudiantes");
                } else {
                    res.sendRedirect(req.getContextPath() + "/servlet-lista-estudiantes");
                }
            } else {
                req.setAttribute("mensajeError", "El estudiante se registró, pero no se pudo asignar debido a que se alcanzó el límite de 10 alumnos.");
                recargarVistaFormulario(req, res, adminLogueado);
            }

        } else {
            String mensajeFinal = (errorOracle != null && errorOracle.contains("UQ_ESTUDIANTE_NOMBRE"))
                    ? "Error: Ya existe un estudiante registrado con ese mismo nombre."
                    : "Error: La matrícula ya existe o faltan campos obligatorios.";

            req.setAttribute("mensajeError", mensajeFinal);
            recargarVistaFormulario(req, res, adminLogueado);
        }
    }

    private void recargarVistaFormulario(HttpServletRequest req, HttpServletResponse res, BeanAdmin adminLogueado)
            throws ServletException, IOException {
        if (adminLogueado != null) {
            ServicioDocente servicioDocente = new ServicioDocente();
            List<BeanDocente> listaDocente = servicioDocente.listaDocente();
            req.setAttribute("listaDocente", listaDocente); // Nombre corregido (decía listaDocent en tu código anterior)
            req.getRequestDispatcher("WEB-INF/Admin/registro-estudiantes-admin.jsp").forward(req, res);
        } else {
            req.getRequestDispatcher("WEB-INF/Docente/registro-estudiantes.jsp").forward(req, res);
        }
    }
}