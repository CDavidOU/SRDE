package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.pres.srde.dao.DaoDatosEstudiantes;
import mx.edu.utez.pres.srde.model.BeanEstudiante;

import java.io.IOException;

@WebServlet (name = "ServletModificarEstudiante", value = "/servlet-modificar-estudiante")
public class ServletModificarEstudiante extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 1. Recoger parámetros del formulario de la vista
        String matricula = request.getParameter("matricula");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String carrera = request.getParameter("carrera");
        int cuatrimestre = Integer.parseInt(request.getParameter("cuatrimestre"));
        String grupo = request.getParameter("grupo");
        String correo = request.getParameter("correo");
        String estado = request.getParameter("estado");

        // 2. Construir el Bean
        BeanEstudiante estudiante = new BeanEstudiante();
        estudiante.setMatricula(matricula);
        estudiante.setNombre(nombre);
        estudiante.setApellido(apellido);
        estudiante.setCarrera(carrera);
        estudiante.setCuatrimestre(cuatrimestre);
        estudiante.setGrupo(grupo);
        estudiante.setCorreo(correo);
        estudiante.setEstado(estado);

        // 3. Mandar a guardar al DAO
        DaoDatosEstudiantes dao = new DaoDatosEstudiantes();
        boolean exito = dao.actualizarEstudiante(estudiante);

        // 4. Redirigir de vuelta a la vista del estudiante (Ajusta el nombre de tu servlet de vista si es diferente)
        if(exito){
            response.sendRedirect(request.getContextPath() + "/servlet-datos-estudiante?matricula=" + matricula + "&msg=success");
        } else {
            response.sendRedirect(request.getContextPath() + "/servlet-datos-estudiante?matricula=" + matricula + "&msg=error");
        }
    }
}
