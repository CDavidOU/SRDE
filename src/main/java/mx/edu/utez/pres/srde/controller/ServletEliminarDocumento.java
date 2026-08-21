package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.BeanAdmin;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.service.ServicioDocumento;

import java.io.IOException;

@WebServlet(name = "servletEliminarDocumento", value = "/servlet-eliminar-documento")
public class ServletEliminarDocumento extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricula = request.getParameter("matricula");

        try {
            // 1. Recibir parámetros y validar que no vengan vacíos
            String paramIdAsignacion = request.getParameter("idAsignacion");
            String paramIdTipoDoc = request.getParameter("idTipoDoc");

            if (paramIdAsignacion == null || paramIdTipoDoc == null || matricula == null) {
                throw new IllegalArgumentException("Parámetros incompletos para elimainar el documento.");
            }

            int idAsignacion = Integer.parseInt(paramIdAsignacion);
            int idTipoDoc = Integer.parseInt(paramIdTipoDoc);

            // 2. Obtener el ID del usuario en sesión (Docente o Admin)
            HttpSession session = request.getSession(false);
            if (session == null) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                return;
            }

            int idUsuarioModificador = 0;
            BeanDocente docenteLogueado = (BeanDocente) session.getAttribute("docenteLogueado");
            BeanAdmin adminLogueado = (BeanAdmin) session.getAttribute("adminLogueado");

            if (docenteLogueado != null) {
                idUsuarioModificador = docenteLogueado.getId();
            } else if (adminLogueado != null) {
                idUsuarioModificador = adminLogueado.getId();
            } else {
                System.err.println("Error: No se encontró sesión activa de Admin ni Docente.");
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                return;
            }

            // 3. Llamar al servicio
            ServicioDocumento servicio = new ServicioDocumento();
            boolean exito = servicio.procesoEliminarDocumento(idAsignacion, idTipoDoc, idUsuarioModificador);

            // 4. Redirigir de vuelta a los detalles del estudiante
            if (exito) {
                response.sendRedirect(request.getContextPath() + "/servlet-datos-estudiante?matricula=" + matricula + "&msg=eliminado");
            } else {
                response.sendRedirect(request.getContextPath() + "/servlet-datos-estudiante?matricula=" + matricula + "&error=true");
            }

        } catch (Exception e) {
        System.err.println("Error en ServletEliminarDocumento: " + e.getMessage());
        e.printStackTrace();

        // Si ocurrió un error pero tenemos la matrícula, volvemos a la vista del alumno
        if (matricula != null && !matricula.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/servlet-datos-estudiante?matricula=" + matricula + "&error=true");
        } else {
            // Recuperar la sesión para saber a dónde redirigir y evitar el error 403
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("adminLogueado") != null) {
                response.sendRedirect(request.getContextPath() + "/servlet-admin-estudiantes?error=true");
            } else {
                response.sendRedirect(request.getContextPath() + "/servlet-lista-estudiantes?error=true");
            }
        }
    }
    }
}