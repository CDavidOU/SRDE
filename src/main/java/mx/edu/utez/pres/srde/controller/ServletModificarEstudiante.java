package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
<<<<<<< HEAD
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.dao.DaoDatosEstudiantes;
import mx.edu.utez.pres.srde.model.BeanEstudiante;

import java.io.IOException;

@WebServlet(name = "ServletModificarEstudiante", value = "/servlet-modificar-estudiante")
public class ServletModificarEstudiante extends HttpServlet {
=======
import mx.edu.utez.pres.srde.dao.DaoDatosEstudiantes;
import mx.edu.utez.pres.srde.model.BeanEstudiante;
import mx.edu.utez.pres.srde.service.ServicioDatosEstudiantes;

import java.io.IOException;
@WebServlet(name = "ServletModificarEstudiante", value = "/servlet-modificar-estudiante")
public class ServletModificarEstudiante extends HttpServlet {

>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

<<<<<<< HEAD
        HttpSession sesion = request.getSession(false);
        boolean esAdmin = (sesion != null && sesion.getAttribute("adminLogueado") != null);

        // 1. Recoger parámetros del formulario
        String matricula = request.getParameter("matricula");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String carrera = request.getParameter("carrera");
        int cuatrimestre = Integer.parseInt(request.getParameter("cuatrimestre"));
        String grupo = request.getParameter("grupo");
        String correo = request.getParameter("correo");

        DaoDatosEstudiantes dao = new DaoDatosEstudiantes();

        // 2. Determinar el estado
        String estado;
        if (esAdmin) {
            // El admin sí envía el valor desde su <select name="estado">
            estado = request.getParameter("estado");
        } else {
            // El docente no puede alterarlo; conservamos el estado actual de la BD
            BeanEstudiante estudianteActual = dao.datosEstudiante(matricula);
            estado = (estudianteActual != null) ? estudianteActual.getEstado() : "Activo";
        }

        // 3. Construir el Bean
        BeanEstudiante estudiante = new BeanEstudiante();
        estudiante.setMatricula(matricula);
=======
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
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
        estudiante.setNombre(nombre);
        estudiante.setApellido(apellido);
        estudiante.setCarrera(carrera);
        estudiante.setCuatrimestre(cuatrimestre);
        estudiante.setGrupo(grupo);
        estudiante.setCorreo(correo);
        estudiante.setEstado(estado);

<<<<<<< HEAD
        // 4. Guardar en base de datos
        boolean exito = dao.actualizarEstudiante(estudiante);

        // 5. Redireccionar
        if (exito) {
            response.sendRedirect(request.getContextPath() + "/servlet-datos-estudiante?matricula=" + matricula + "&msg=success");
        } else {
            response.sendRedirect(request.getContextPath() + "/servlet-datos-estudiante?matricula=" + matricula + "&msg=error");
=======
        // 3. Enviar a la capa de servicio pasando el idDocente como parámetro independiente
        ServicioDatosEstudiantes servicio = new ServicioDatosEstudiantes();
        boolean exito = servicio.actualizarEstudiante(matriculaOriginal, estudiante, idDocente);

        // 4. Redirección
        if (exito) {
            response.sendRedirect(request.getContextPath() + "/servlet-datos-estudiante?matricula=" + matriculaNueva + "&msg=success");
        } else {
            response.sendRedirect(request.getContextPath() + "/servlet-datos-estudiante?matricula=" + matriculaOriginal + "&msg=error");
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
        }
    }
}