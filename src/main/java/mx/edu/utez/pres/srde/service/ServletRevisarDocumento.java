package mx.edu.utez.pres.srde.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "servletRevisarDocumento", value = "/servlet-revisar-documento")
public class ServletRevisarDocumento extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException,ServletException {

        int idAsignacion = Integer.parseInt(request.getParameter("idAsignacion"));
        int idTipoDoc = Integer.parseInt(request.getParameter("idTipoDoc"));
        String matricula = request.getParameter("matricula");

        ServicioDocumento servicio = new ServicioDocumento();
        servicio.procesoMarcarRevisado(idAsignacion, idTipoDoc);

        // Redirige de vuelta a la ficha del estudiante
        response.sendRedirect("servlet-datos-estudiante?matricula=" + matricula);
    }
}
