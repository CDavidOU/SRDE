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
        //este nos servira en caso de ser el form del admin asignara el docente
        int idDocenteDestino = 0;

        if (docenteLogueado != null) {
            // Si es docente, se autoasigna
            idDocenteDestino = docenteLogueado.getId();
        } else if (adminLogueado != null) {
            // Si es admin, recuperamos el ID del profesor seleccionado en el <select>
            String strIdDocente = req.getParameter("idDocenteAsignado");
            if (strIdDocente != null && !strIdDocente.trim().isEmpty()) {
                try {
                    idDocenteDestino = Integer.parseInt(strIdDocente.trim());
                } catch (NumberFormatException e) {
                    System.err.println("Error al parsear el ID del docente seleccionado por el Admin: " + e.getMessage());
                }
            }
        }

        // Parseo del cuatrimestre
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

        // Controlamos la excepción de la base de datos (Unique Constraint) para evitar caídas en el servidor
        try {
            registroEstudiante = nuevoRegistro.registrarEstudiante(estudiante);
        } catch (Exception e) {
            errorOracle = e.getMessage();
            System.err.println("Error capturado al insertar en base de datos: " + errorOracle);
        }

        if (registroEstudiante != null) {
            if (idDocenteDestino > 0) {
                ServicioPeriodos servicioPeriodos = new ServicioPeriodos();
                BeanPeriodo periodoActual = servicioPeriodos.automatizacionPeriodos();

                if (periodoActual != null) {
                    ServicioAsignacionEstadias servicioAsignacionEstadias = new ServicioAsignacionEstadias();

                    int idAsignacionGenerada = servicioAsignacionEstadias.registroAsignacionEstadias(
                            idDocenteDestino,
                            periodoActual.getId_periodo(),
                            registroEstudiante.getMatricula()
                    );

                    // Inicializar documentos con el ID del docente a cargo
                    if (idAsignacionGenerada > 0) {
                        ServicioDocumento servicioDoc = new ServicioDocumento();
                        servicioDoc.inicializarDocumento(idAsignacionGenerada, idDocenteDestino);
                    }
                }
            }

            // GUARDAR EN SESIÓN PARA SOBREVIVIR AL REDIRECT
            System.out.println("Estoy en servletRegistro y si ingrese al estudiante");
            System.out.println("Estudiante registrado y asignado correctamente.");
            sesion.setAttribute("mensajeOk", "Se ha ingresado el estudiante correctamente.");

            if (adminLogueado != null) {
                // Ruta corregida:
                res.sendRedirect(req.getContextPath() + "/servlet-admin-estudiantes");
            } else {
                // Verifica que esta ruta también sea correcta para el docente
                res.sendRedirect(req.getContextPath() + "/servlet-lista-estudiantes");
            }

        } else {
            // Determinamos el mensaje exacto si ocurrió la restricción del nombre único de Oracle
            String mensajeFinal = (errorOracle != null && errorOracle.contains("UQ_ESTUDIANTE_NOMBRE"))
                    ? "Error: Ya existe un estudiante registrado con ese mismo nombre."
                    : "Error: La matrícula ya existe o faltan campos obligatorios.";

            req.setAttribute("mensajeError", mensajeFinal);

            if (adminLogueado != null) {
                ServicioDocente servicioDocente = new ServicioDocente();
                List<BeanDocente> listaDocente = servicioDocente.listaDocente();
                req.setAttribute("listaDocent", listaDocente);

                req.getRequestDispatcher("WEB-INF/Admin/registro-estudiantes-admin.jsp").forward(req, res);
            } else {
                req.getRequestDispatcher("WEB-INF/Docente/registro-estudiantes.jsp").forward(req, res);
            }
        }
    }
}