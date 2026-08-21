package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.pres.srde.dao.DaoDatosEstudiantes;
import mx.edu.utez.pres.srde.model.BeanEstudiante;
import mx.edu.utez.pres.srde.service.ServicioDatosEstudiantes;

import java.io.IOException;
@WebServlet(name = "ServletModificarEstudiante", value = "/servlet-modificar-estudiante")
public class ServletModificarEstudiante extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 1. Recoger parámetros
        String matriculaOriginal = request.getParameter("matriculaOriginal");
        String matriculaNueva = request.getParameter("matricula");

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String carrera = request.getParameter("carrera");

        int cuatrimestre = 0;
        try {
            cuatrimestre = Integer.parseInt(request.getParameter("cuatrimestre"));
        } catch (NumberFormatException ignored) {}

        String grupo = request.getParameter("grupo");
        String correo = request.getParameter("correo");
        String estado = request.getParameter("estado");

        // Capturamos el ID del docente elegido en el combo HTML
        int idDocente = 0;
        String idDocenteParam = request.getParameter("idDocente");
        if (idDocenteParam != null && !idDocenteParam.trim().isEmpty()) {
            try {
                idDocente = Integer.parseInt(idDocenteParam);
            } catch (NumberFormatException ignored) {}
        }

        // 2. Construir el Bean con sus campos normales de la base de datos
        BeanEstudiante estudiante = new BeanEstudiante();
        estudiante.setMatricula(matriculaNueva);
        estudiante.setNombre(nombre);
        estudiante.setApellido(apellido);
        estudiante.setCarrera(carrera);
        estudiante.setCuatrimestre(cuatrimestre);
        estudiante.setGrupo(grupo);
        estudiante.setCorreo(correo);
        estudiante.setEstado(estado);

        // 3. Enviar a la capa de servicio pasando el idDocente como parámetro independiente
        ServicioDatosEstudiantes servicio = new ServicioDatosEstudiantes();
        boolean exito = servicio.actualizarEstudiante(matriculaOriginal, estudiante, idDocente);

        // 4. Redirección
        if (exito) {
            response.sendRedirect(request.getContextPath() + "/servlet-datos-estudiante?matricula=" + matriculaNueva + "&msg=success");
        } else {
            response.sendRedirect(request.getContextPath() + "/servlet-datos-estudiante?matricula=" + matriculaOriginal + "&msg=error");
        }
    }
}