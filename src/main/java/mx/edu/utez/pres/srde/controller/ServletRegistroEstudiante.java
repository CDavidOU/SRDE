package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.BeanAsignacionEstadias;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanEstudiante;
import mx.edu.utez.pres.srde.model.BeanPeriodo;
import mx.edu.utez.pres.srde.service.ServicioAsignacionEstadias;
import mx.edu.utez.pres.srde.service.ServicioPeriodos;
import mx.edu.utez.pres.srde.service.ServicioRegistroEstudiante;

import java.io.IOException;

@WebServlet(name = "servletRegistroEstudiantes", value = "/servlet-registro-estudiante")
public class ServletRegistroEstudiante extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession sesion = req.getSession(false);

        // Validamos sesión
        if (sesion == null || sesion.getAttribute("docenteLogueado") == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        // Solo redirige a la pantalla donde está el formulario para llenar los datos
        req.getRequestDispatcher("WEB-INF/Docente/registro-estudiantes.jsp").forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        /* Vemos si alguien esta conectado */
        HttpSession sesion = req.getSession();
        if (sesion == null || sesion.getAttribute("docenteLogueado") == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }


        BeanEstudiante estudiante = new BeanEstudiante();
        BeanDocente docenteLogueado= (BeanDocente) sesion.getAttribute("docenteLogueado");
        int idDocente = docenteLogueado.getId();

        estudiante.setNombre(req.getParameter("nombre"));
        estudiante.setApellido(req.getParameter("apellido"));
        estudiante.setCarrera(req.getParameter("carrera"));
        estudiante.setCuatrimestre(Integer.parseInt(req.getParameter("cuatrimestre")));
        estudiante.setMatricula(req.getParameter("matricula"));
        estudiante.setCorreo(req.getParameter("correo"));

        ServicioPeriodos servicioPeriodos = new ServicioPeriodos();
        BeanPeriodo periodoActual =servicioPeriodos.automatizacionPeriodos();

        ServicioRegistroEstudiante nuevoRegistro = new ServicioRegistroEstudiante();
        BeanEstudiante registroEstudiante = nuevoRegistro.registrarEstudiante(estudiante);


        if(registroEstudiante!=null){

            req.setAttribute("estudiante",registroEstudiante);
            System.out.println("--- DATOS DE ASIGNACIÓN ---");
            System.out.println("ID Docente de la sesión: " + idDocente);
            System.out.println("ID Periodo calculado: " + periodoActual.getId_periodo());
            System.out.println("Matrícula insertada: " + registroEstudiante.getMatricula());
            System.out.println("---------------------------");
            ServicioAsignacionEstadias servicioAsignacionEstadias = new ServicioAsignacionEstadias();
            BeanAsignacionEstadias asignacionEstadias=servicioAsignacionEstadias.registroAsignacionEstadias(idDocente, periodoActual.getId_periodo(), registroEstudiante.getMatricula() );

            req.getRequestDispatcher("/servlet-lista-estudiantes").forward(req,res);



        }else {
            req.setAttribute("mnsjeError","No se registro el alumno faltan campos o limite de estudiantes alcanzado");
            req.getRequestDispatcher("WEB-INF/Docente/registro-estudiantes.jsp").forward(req,res);
        }

    }
}
