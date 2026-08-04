package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.dao.DaoListaEstudiantes;

import java.io.IOException;

@WebServlet(name = "servletEliminarEstudiante", value = "/servlet-eliminar-asignacion")
public class ServletEliminarEstudiante extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession(false);
        if (sesion == null || sesion.getAttribute("docenteLogueado") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        String matricula = request.getParameter("matricula");

        if (matricula != null && !matricula.trim().isEmpty()) {
            DaoListaEstudiantes dao = new DaoListaEstudiantes();

            // Eliminamos al estudiante de ambas tablas
            boolean eliminado = dao.eliminarEstudianteCompleto(matricula);

            if (eliminado) {
                // Redirigimos a la lista para recargar la tabla vacía
                response.sendRedirect(request.getContextPath() + "/servlet-lista-estudiantes");
                return;
            }
        }

        // Si falló, volvemos a la lista
        response.sendRedirect(request.getContextPath() + "/servlet-lista-estudiantes");
    }
}