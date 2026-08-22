package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.*;
import mx.edu.utez.pres.srde.service.ServicioDatosEstudiantes;
import mx.edu.utez.pres.srde.service.ServicioDocente;
import mx.edu.utez.pres.srde.service.ServicioDocumento;
import mx.edu.utez.pres.srde.service.ServicioPeriodos;
import java.io.IOException;
import java.util.List;
<<<<<<< HEAD

=======
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
@WebServlet(name = "servletDatosEstudiante", value = "/servlet-datos-estudiante")
public class ServletDatosEstudiante extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try {
            HttpSession sesion = req.getSession(false);

<<<<<<< HEAD
        // 1. Validar sesión activa
        if (sesion == null || (sesion.getAttribute("docenteLogueado") == null && sesion.getAttribute("adminLogueado") == null)) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        Object adminLogueado = sesion.getAttribute("adminLogueado");
        String matricula = req.getParameter("matricula");

        // 2. Buscar datos del estudiante
        if (matricula != null && !matricula.trim().isEmpty()) {
            ServicioDatosEstudiantes servicioDatosEstudiante = new ServicioDatosEstudiantes();
            BeanEstudiante estudiante = servicioDatosEstudiante.datosEstudiante(matricula);

            if (estudiante != null) {
                req.setAttribute("datosEstudiante", estudiante);

                ServicioDocumento servicioDoc = new ServicioDocumento();
                List<BeanArchivo> listaDocumentos = servicioDoc.obtenerDocumentosPorMatricula(matricula);
                req.setAttribute("listaDocumentos", listaDocumentos);

                // Rutas con diagonal inicial (/) obligatoria
                if (adminLogueado != null) {
                    req.getRequestDispatcher("/WEB-INF/Admin/vista-datos-estudiante-admin.jsp").forward(req, res);
                } else {
                    req.getRequestDispatcher("/WEB-INF/Docente/vista-datos-estudiante.jsp").forward(req, res);
                }
=======
            // 1. Validar sesión
            if (sesion == null || (sesion.getAttribute("docenteLogueado") == null && sesion.getAttribute("adminLogueado") == null)) {
                res.sendRedirect(req.getContextPath() + "/index.jsp");
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
                return;
            }

<<<<<<< HEAD
        // 3. Redirección si la matrícula es inválida o no existe
        System.out.println("Matrícula inválida o no encontrada");

        if (adminLogueado != null) {
            res.sendRedirect(req.getContextPath() + "/servlet-admin-estudiantes");
        } else {
            res.sendRedirect(req.getContextPath() + "/servlet-lista-estudiantes");
=======
            Object adminLogueado = sesion.getAttribute("adminLogueado");
            String matricula = req.getParameter("matricula");
            String idPeriodoParam = req.getParameter("idPeriodo");

            ServicioDatosEstudiantes servicioDatosEstudiante = new ServicioDatosEstudiantes();

            if (matricula != null && !matricula.trim().isEmpty()) {
                BeanEstudiante estudiante = servicioDatosEstudiante.datosEstudiante(matricula);

                if (estudiante != null) {
                    req.setAttribute("datosEstudiante", estudiante);

                    ServicioPeriodos servicioPeriodos = new ServicioPeriodos();

                    // Obtener los períodos donde el estudiante SI tiene historial/asignación
                    List<BeanPeriodo> listaPeriodos = servicioPeriodos.obtenerPeriodosPorEstudiante(matricula);
                    req.setAttribute("listaPeriodosEstudiante", listaPeriodos);

                    int idPeriodo = 0;

                    // CASO A: El usuario seleccionó un período del dropdown
                    if (idPeriodoParam != null && !idPeriodoParam.trim().isEmpty()) {
                        idPeriodo = Integer.parseInt(idPeriodoParam);
                    }
                    // CASO B: Es la primera carga -> Tomar el período más reciente del ESTUDIANTE
                    else if (listaPeriodos != null && !listaPeriodos.isEmpty()) {
                        idPeriodo = listaPeriodos.get(0).getId_periodo();
                    }
                    // CASO C: Si no tiene historial, respaldar con el período activo
                    else {
                        BeanPeriodo periodoActivo = servicioPeriodos.automatizacionPeriodos();
                        idPeriodo = (periodoActivo != null) ? periodoActivo.getId_periodo() : 0;
                    }

                    req.setAttribute("periodoSeleccionado", idPeriodo);

                    // Obtener documentos
                    ServicioDocumento servicioDoc = new ServicioDocumento();
                    List<BeanArchivo> listaDocumentos = servicioDoc.obtenerDocumentosPorMatricula(matricula, idPeriodo);

                    req.setAttribute("listaDocumentos", listaDocumentos);

                    // Cargar lista de docentes activos si el usuario es un Admin
                    if (adminLogueado != null) {
                        ServicioDocente servicioDocente = new ServicioDocente();
                        List<BeanDocente> listaDocentesActivos = servicioDocente.obtenerDocentesActivos();
                        req.setAttribute("listaDocentesActivos", listaDocentesActivos);

                        req.getRequestDispatcher("/WEB-INF/Admin/vista-datos-estudiante-admin.jsp").forward(req, res);
                    } else {
                        req.getRequestDispatcher("/WEB-INF/Docente/vista-datos-estudiante.jsp").forward(req, res);
                    }
                    return;
                } else {
                    // SI EL ESTUDIANTE ES NULL
                    res.setContentType("text/html;charset=UTF-8");
                    res.getWriter().println("<h3 style='color:red;'>Error: No se encontró ningún estudiante con la matrícula: " + matricula + "</h3>");
                    return;
                }
            } else {
                // SI LA MATRICULA LLEGA VACÍA
                res.setContentType("text/html;charset=UTF-8");
                res.getWriter().println("<h3 style='color:red;'>Error: El parámetro 'matricula' llegó nulo o vacío desde la URL.</h3>");
                return;
            }

        } catch (Throwable t) {
            // CAPTURA CUALQUIER EXCEPCIÓN O ERROR DEL JSP/SERVLET
            t.printStackTrace();
            res.setContentType("text/html;charset=UTF-8");
            res.getWriter().println("<h2 style='color:red;'>Excepción capturada:</h2>");
            res.getWriter().println("<pre style='background:#f4f4f4; padding:10px;'>");
            t.printStackTrace(res.getWriter());
            res.getWriter().println("</pre>");
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
        }
    }
}