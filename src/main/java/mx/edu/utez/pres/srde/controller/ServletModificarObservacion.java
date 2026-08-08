package mx.edu.utez.pres.srde.controller;

import mx.edu.utez.pres.srde.model.BeanAdmin;
import mx.edu.utez.pres.srde.model.BeanDocente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.service.ServicioDocumento;

import java.io.IOException;

@WebServlet(name = "servletModificarObservacion", value = "/servlet-modificar-observacion")
public class ServletModificarObservacion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Para que los acentos en las observaciones se guarden bien

        try {
            // 1. Recibir datos del formulario (JSP)
            int idAsignacion = Integer.parseInt(request.getParameter("idAsignacion"));
            String matricula = request.getParameter("matricula");
            int idTipoDoc = Integer.parseInt(request.getParameter("idTipoDoc"));
            String observaciones = request.getParameter("observaciones");

            // 2. Sacar el ID del docente o admin logueado desde el OBJETO de la sesión
            HttpSession session = request.getSession();
            int idUsuarioModificador = 0;

            BeanDocente docenteLogueado = (BeanDocente) session.getAttribute("docenteLogueado");
            BeanAdmin adminLogueado = (BeanAdmin) session.getAttribute("adminLogueado");

            if (docenteLogueado != null) {
                idUsuarioModificador = docenteLogueado.getId(); // Sacamos el ID del objeto docente
            } else if (adminLogueado != null) {
                idUsuarioModificador = adminLogueado.getId(); // Sacamos el ID del objeto admin
            } else {
                System.err.println("Error: No se encontró sesión activa de Admin ni Docente.");
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                return; // Detiene la ejecución aquí si no hay nadie logueado
            }

            // 3. Llamar al servicio
            ServicioDocumento servicio = new ServicioDocumento();
            boolean exito = servicio.procesoModificarObservaciones(idAsignacion, idTipoDoc, observaciones, idUsuarioModificador);

            // 4. Redirigir al controlador (SOLUCIÓN AL ERROR 404)
            // CAMBIA "ServletQueCargaAlEstudiante" POR EL NOMBRE REAL DE TU SERVLET
            response.sendRedirect(request.getContextPath() + "/servlet-datos-estudiante?matricula=" + matricula);

        } catch (Exception e) {
            System.err.println("Error en ServletModificarObservacion: " + e.getMessage());
            // También cambia aquí la redirección al Servlet controlador
            response.sendRedirect(request.getContextPath() + "/servlet-lista-estudiantes?error=true");
        }
    }
}